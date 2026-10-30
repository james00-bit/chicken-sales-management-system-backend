package com.auca.chickensales.controller;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auca.chickensales.entity.Customer;
import com.auca.chickensales.service.CustomerService;
import com.auca.chickensales.service.SalesAgentService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Customer customer) {
        String message = customerService.save(customer);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAll() {
        return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<Customer> found = customerService.getById(id);
        if (found.isPresent()) {
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(CustomerService.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Customer customer) {
        String message = customerService.update(id, customer);
        if (message.equals(CustomerService.NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = customerService.delete(id);
        if (message.equals(CustomerService.NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
        @GetMapping("/by-last-name")
    public ResponseEntity<List<Customer>> getByLastName(@RequestParam String lastName) {
        return new ResponseEntity<>(customerService.getByLastName(lastName), HttpStatus.OK);
    }
@Autowired
    private SalesAgentService salesAgentService;

    @GetMapping("/of-sales-agent/{salesAgentId}")
    public ResponseEntity<Object> getBySalesAgent(@PathVariable UUID salesAgentId) {
        if (salesAgentService.getById(salesAgentId).isEmpty()) {
            return new ResponseEntity<>(SalesAgentService.NOT_FOUND, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(customerService.getBySalesAgent(salesAgentId), HttpStatus.OK);
        }
    }
}
