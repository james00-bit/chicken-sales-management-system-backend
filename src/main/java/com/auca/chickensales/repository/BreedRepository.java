package com.auca.chickensales.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auca.chickensales.entity.Breed;

@Repository
public interface BreedRepository extends JpaRepository<Breed, UUID> {
}
