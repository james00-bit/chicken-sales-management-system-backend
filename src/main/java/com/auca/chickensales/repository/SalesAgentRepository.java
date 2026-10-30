package com.auca.chickensales.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.auca.chickensales.entity.SalesAgent;

@Repository
public interface SalesAgentRepository extends JpaRepository<SalesAgent, UUID> {
    @Query("SELECT s FROM SalesAgent s JOIN s.breeds b WHERE LOWER(b.name) = LOWER(:name)")
    List<SalesAgent> findByBreedName(@Param("name") String name);
}
