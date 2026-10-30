package com.auca.chickensales.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.chickensales.entity.SalesAgent;
import com.auca.chickensales.repository.SalesAgentRepository;

@Service
public class SalesAgentService {

    public static final String NOT_FOUND = "The sales agent with that id does not exist";

    @Autowired
    private SalesAgentRepository salesAgentRepository;

    public String save(SalesAgent salesAgent) {
        salesAgentRepository.save(salesAgent);
        return "Sales agent saved successfully";
    }

    public List<SalesAgent> getAll() {
        return salesAgentRepository.findAll();
    }

    public Optional<SalesAgent> getById(UUID id) {
        return salesAgentRepository.findById(id);
    }

    public String update(UUID id, SalesAgent salesAgent) {
        Optional<SalesAgent> found = salesAgentRepository.findById(id);
        if (found.isEmpty()) {
            return NOT_FOUND;
        }
        SalesAgent old = found.get();
        old.setFirstName(salesAgent.getFirstName());
        old.setLastName(salesAgent.getLastName());
        old.setPhone(salesAgent.getPhone());
        old.setStall(salesAgent.getStall());
        old.setBreeds(salesAgent.getBreeds());
        salesAgentRepository.save(old);
        return "Sales agent updated successfully";
    }

    public String delete(UUID id) {
        Optional<SalesAgent> found = salesAgentRepository.findById(id);
        if (found.isEmpty()) {
            return NOT_FOUND;
        }
        salesAgentRepository.deleteById(id);
        return "Sales agent deleted successfully";
    }
}
