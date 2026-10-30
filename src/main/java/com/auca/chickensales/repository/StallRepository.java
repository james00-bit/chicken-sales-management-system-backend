package com.auca.chickensales.repository;

import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auca.chickensales.entity.Stall;


@Repository
public interface StallRepository extends JpaRepository<Stall, UUID> {
    @Query("SELECT l FROM Stall l WHERE NOT EXISTS "
        + "(SELECT p FROM SalesAgent p WHERE p.stall = l) ORDER BY l.stallNumber")
    List<Stall> findFreeStalls();
}


