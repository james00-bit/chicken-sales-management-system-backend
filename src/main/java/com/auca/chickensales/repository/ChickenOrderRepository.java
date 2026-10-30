package com.auca.chickensales.repository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auca.chickensales.entity.ChickenOrder;
import com.auca.chickensales.entity.OrderStatus;

@Repository
public interface ChickenOrderRepository extends JpaRepository<ChickenOrder, UUID> {
    List<ChickenOrder> findByStatusOrderByOrderDateAsc(OrderStatus status);
    List<ChickenOrder> findByOrderDateBetweenOrderByOrderDateAsc(LocalDate start, LocalDate end);
    boolean existsBySalesAgentIdAndOrderDateAndStatusNot(UUID salesAgentId, LocalDate orderDate, OrderStatus status);
}

