package com.auca.chickensales.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.auca.chickensales.entity.ChickenOrder;
import com.auca.chickensales.entity.SaleRecord;

@Repository
public interface SaleRecordRepository extends JpaRepository<SaleRecord, UUID> {

    @Query("SELECT d FROM SaleRecord d WHERE d.chickenOrder.customer.phone = :phone "
            + "ORDER BY d.saleDate DESC")
    List<SaleRecord> findByCustomerPhone(@Param("phone") String phone);

    boolean existsByChickenOrder(ChickenOrder chickenOrder);
}
