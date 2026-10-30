package com.auca.chickensales.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auca.chickensales.entity.ChickenOrder;
import com.auca.chickensales.entity.OrderStatus;
import com.auca.chickensales.service.ChickenOrderService;

@RestController
@RequestMapping("/api/chicken-orders")
public class ChickenOrderController {

    @Autowired
    private ChickenOrderService chickenOrderService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ChickenOrder chickenOrder) {
        String message = chickenOrderService.save(chickenOrder);
        if (message.equals(ChickenOrderService.PAST_DATE)) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (message.equals(ChickenOrderService.AGENT_BUSY)) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChickenOrder>> getAll() {
        return new ResponseEntity<>(chickenOrderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<ChickenOrder> found = chickenOrderService.getById(id);
        if (found.isPresent()) {
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ChickenOrderService.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody ChickenOrder chickenOrder) {
        String message = chickenOrderService.update(id, chickenOrder);
        if (message.equals(ChickenOrderService.NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = chickenOrderService.delete(id);
        if (message.equals(ChickenOrderService.NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (message.equals(ChickenOrderService.HAS_RECORDS)) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
    @GetMapping("/by-status")
    public ResponseEntity<List<ChickenOrder>> getByStatus(@RequestParam OrderStatus status) {
        return new ResponseEntity<>(chickenOrderService.getByStatus(status), HttpStatus.OK);
    }
    @GetMapping("/between")
    public ResponseEntity<List<ChickenOrder>> getBetween(@RequestParam String start,
            @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);  
        LocalDate endDate = LocalDate.parse(end);
        return new ResponseEntity<>(chickenOrderService.getBetween(startDate, endDate), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus( @PathVariable UUID id, @RequestParam OrderStatus status) {
        String message = chickenOrderService.updateStatus(id, status);
        if (message.equals(ChickenOrderService.NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (message.startsWith("Cannot change status")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}


