package com.auca.chickensales.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.chickensales.entity.ChickenOrder;
import com.auca.chickensales.entity.OrderStatus;
import com.auca.chickensales.repository.ChickenOrderRepository;


@Service
public class ChickenOrderService {

    public static final String NOT_FOUND = "The chicken order with that id does not exist";

    @Autowired
    private ChickenOrderRepository chickenOrderRepository;

    public String save(ChickenOrder chickenOrder) {
        chickenOrderRepository.save(chickenOrder);
        return "Chicken order saved successfully";
    }

    public List<ChickenOrder> getAll() {
        return chickenOrderRepository.findAll();
    }


    public Optional<ChickenOrder> getById(UUID id) {
        return chickenOrderRepository.findById(id);
    }

    public String update(UUID id, ChickenOrder chickenOrder) {
        Optional<ChickenOrder> found = chickenOrderRepository.findById(id);
        if (found.isEmpty()) {
            return NOT_FOUND;
        }
        ChickenOrder old = found.get();
        old.setOrderDate(chickenOrder.getOrderDate());
        old.setStatus(chickenOrder.getStatus());
        old.setChickens(chickenOrder.getChickens());
        old.setTotalPrice(chickenOrder.getTotalPrice());
        old.setCustomer(chickenOrder.getCustomer());
        old.setSalesAgent(chickenOrder.getSalesAgent());
        chickenOrderRepository.save(old);
        return "Chicken order updated successfully";
    }

    public String delete(UUID id) {
        Optional<ChickenOrder> found = chickenOrderRepository.findById(id);
        if (found.isEmpty()) {
            return NOT_FOUND;
        }
        chickenOrderRepository.deleteById(id);
        return "Chicken order deleted successfully";
    }
    public List<ChickenOrder> getByStatus(OrderStatus status) {
        return chickenOrderRepository.findByStatusOrderByOrderDateAsc(status);
    }
    public List<ChickenOrder> getBetween(LocalDate start, LocalDate end) {
        return chickenOrderRepository.findByOrderDateBetweenOrderByOrderDateAsc(start, end);
    }
}
