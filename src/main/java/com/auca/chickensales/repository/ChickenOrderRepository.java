package com.auca.chickensales.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.auca.chickensales.entity.ChickenOrder;
import com.auca.chickensales.entity.OrderStatus;

@Repository
public interface ChickenOrderRepository extends JpaRepository<ChickenOrder, UUID> {

    List<ChickenOrder> findByStatusOrderByOrderDateAsc(OrderStatus status);

    List<ChickenOrder> findByOrderDateBetweenOrderByOrderDateAsc(LocalDate start, LocalDate end);

    boolean existsBySalesAgentIdAndOrderDateAndStatusNot(UUID salesAgentId, LocalDate orderDate, OrderStatus status);

    @Query("SELECT o.status, COUNT(o) FROM ChickenOrder o GROUP BY o.status")
    List<Object[]> countOrdersByStatus();

    @Modifying
    @Query("UPDATE ChickenOrder o SET o.status = :cancelled "
            + "WHERE o.salesAgent.id = :salesAgentId AND o.orderDate = :date AND o.status <> :delivered")
    int cancelOrdersOfDay(@Param("salesAgentId") UUID salesAgentId,
            @Param("date") LocalDate date,
            @Param("cancelled") OrderStatus cancelled,
            @Param("delivered") OrderStatus delivered);
}

