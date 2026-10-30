package com.auca.chickensales.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.chickensales.entity.ChickenOrder;
import com.auca.chickensales.entity.OrderStatus;
import com.auca.chickensales.entity.SaleRecord;
import com.auca.chickensales.repository.ChickenOrderRepository;
import com.auca.chickensales.repository.SaleRecordRepository;

@Service
public class SaleRecordService {

    public static final String ORDER_NOT_FOUND = "The chicken order with that id does not exist";
    public static final String NOT_DELIVERED = "Sale records are only for DELIVERED chicken orders";
    public static final String NOT_FOUND = "The sale record with that id does not exist";

    @Autowired
    private SaleRecordRepository saleRecordRepository;

    @Autowired
    private ChickenOrderRepository chickenOrderRepository;


    public String save(UUID chickenOrderId, SaleRecord saleRecord) {
        Optional<ChickenOrder> found = chickenOrderRepository.findById(chickenOrderId);
        if (found.isEmpty()) {
            return ORDER_NOT_FOUND;
        }
        ChickenOrder order = found.get();
        if (order.getStatus() != OrderStatus.DELIVERED) {
            return NOT_DELIVERED;
        }
        saleRecord.setId(null);
        saleRecord.setSaleDate(LocalDate.now());
        saleRecord.setChickenOrder(order);
        saleRecordRepository.save(saleRecord);
        return "Sale record saved successfully";
    }

    public List<SaleRecord> getAll() {
        return saleRecordRepository.findAll();
    }

    public List<SaleRecord> getByCustomerPhone(String phone) {
        return saleRecordRepository.findByCustomerPhone(phone);
    }

    
    public String delete(UUID id) {
        Optional<SaleRecord> found = saleRecordRepository.findById(id);
        if (found.isEmpty()) {
            return NOT_FOUND;
        }
        saleRecordRepository.deleteById(id);
        return "Sale record deleted successfully";
    }
}