package com.auca.chickensales.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.auca.chickensales.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
     List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
@Query("SELECT DISTINCT o.customer FROM ChickenOrder o WHERE o.salesAgent.id = :salesAgentId")
List<Customer> findCustomersBySalesAgentId(@Param("salesAgentId") UUID salesAgentId);
}
