package com.auca.chickensales.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.chickensales.entity.Breed;
import com.auca.chickensales.entity.SalesAgent;
import com.auca.chickensales.entity.Stall;
import com.auca.chickensales.repository.BreedRepository;
import com.auca.chickensales.repository.SalesAgentRepository;
import com.auca.chickensales.repository.StallRepository;
@Service
public class SalesAgentService {

    public static final String NOT_FOUND = "The sales agent with that id does not exist";
    public static final String BREED_NOT_FOUND = "That breed does not exist";
    public static final String ALREADY_HAS_BREED = "The sales agent already has that breed";
    public static final String STALL_NOT_FOUND = "The stall with that stallNumber does not exist";
    public static final String STALL_TAKEN = "That stall is taken by another sales agent";

    @Autowired
    private SalesAgentRepository salesAgentRepository;

    public String save(SalesAgent salesAgent) {
        salesAgentRepository.save(salesAgent);
        return "Sales agent saved successfully";
    }
@Autowired
private BreedRepository breedRepository;

public String addBreed(UUID salesAgentId, UUID breedId) {
        Optional<SalesAgent> foundAgent = salesAgentRepository.findById(salesAgentId);
        if (foundAgent.isEmpty()) {
            return NOT_FOUND;
        }
        Optional<Breed> foundBreed = breedRepository.findById(breedId);
        if (foundBreed.isEmpty()) {
            return BREED_NOT_FOUND;
        }
        SalesAgent agent = foundAgent.get();
        Breed breed = foundBreed.get();
        if (agent.getBreeds() == null) {
        agent.setBreeds(new ArrayList<>());
        }
        if (agent.getBreeds().contains(breed)) {
            return ALREADY_HAS_BREED;
        }
        agent.getBreeds().add(breed);
        salesAgentRepository.save(agent);
        return "Breed added to the sales agent";
    }
    @Autowired
    private StallRepository stallRepository;
    public String changeStall(UUID salesAgentId, int stallNumber) {
        Optional<SalesAgent> foundAgent = salesAgentRepository.findById(salesAgentId);
        if (foundAgent.isEmpty()) {
            return NOT_FOUND;
        }
        Optional<Stall> foundStall = stallRepository.findByStallNumber(stallNumber);
        if (foundStall.isEmpty()) {
            return STALL_NOT_FOUND;
        }
        SalesAgent agent = foundAgent.get();
        Stall stall = foundStall.get();
    
        if (agent.getStall() != null && agent.getStall().getId().equals(stall.getId())) {
            return "Stall changed successfully";
        }
        if (salesAgentRepository.existsByStall(stall)) {
            return STALL_TAKEN;
        }
        agent.setStall(stall);
        salesAgentRepository.save(agent);
        return "Stall changed successfully";
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
    public List<SalesAgent> getByBreed(String name) {
        return salesAgentRepository.findByBreedName(name);
    }
}
