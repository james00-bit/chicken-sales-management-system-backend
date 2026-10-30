package com.auca.chickensales.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auca.chickensales.entity.Breed;

@Repository
public interface BreedRepository extends JpaRepository<Breed, UUID> {
@Query("SELECT b FROM Breed b WHERE b.salesAgents IS EMPTY")
    List<Breed> findUnusedBreeds();
}
