package com.auca.chickensales.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auca.chickensales.entity.SaleRecord;
import com.auca.chickensales.service.SaleRecordService;

@RestController
@RequestMapping("/api/sale-records")
public class SaleRecordController {

    @Autowired
    private SaleRecordService saleRecordService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID chickenOrderId,
            @RequestBody SaleRecord saleRecord) {
        String message = saleRecordService.save(chickenOrderId, saleRecord);
        if (message.equals(SaleRecordService.ORDER_NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (message.equals(SaleRecordService.NOT_DELIVERED)) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<SaleRecord>> getAll() {
        return new ResponseEntity<>(saleRecordService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/customer/{phone}")
    public ResponseEntity<List<SaleRecord>> getByCustomerPhone(@PathVariable String phone) {
        return new ResponseEntity<>(saleRecordService.getByCustomerPhone(phone), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = saleRecordService.delete(id);
        if (message.equals(SaleRecordService.NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
     }
    }
}
