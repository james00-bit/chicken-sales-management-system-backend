package com.auca.chickensales.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auca.chickensales.entity.ChickenOrder;

@Repository
public interface ChickenOrderRepository extends JpaRepository<ChickenOrder, UUID> {
}
