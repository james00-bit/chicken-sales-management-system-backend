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

import com.auca.chickensales.entity.SalesAgent;
import com.auca.chickensales.service.SalesAgentService;

@RestController
@RequestMapping("/api/sales-agents")
public class SalesAgentController {

    @Autowired
    private SalesAgentService salesAgentService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody SalesAgent salesAgent) {
        String message = salesAgentService.save(salesAgent);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SalesAgent>> getAll() {
        return new ResponseEntity<>(salesAgentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<SalesAgent> found = salesAgentService.getById(id);
        if (found.isPresent()) {
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(SalesAgentService.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody SalesAgent salesAgent) {
        String message = salesAgentService.update(id, salesAgent);
        if (message.equals(SalesAgentService.NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = salesAgentService.delete(id);
        if (message.equals(SalesAgentService.NOT_FOUND)) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
    @GetMapping("/bybreed")
    public ResponseEntity<List<SalesAgent>> getByBreed(@RequestParam String name) {
        return new ResponseEntity<>(salesAgentService.getByBreed(name), HttpStatus.OK);
    }
}
