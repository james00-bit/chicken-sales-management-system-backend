package com.auca.chickensales.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.chickensales.entity.Stall;
import com.auca.chickensales.repository.StallRepository;

@Service
public class StallService {

    public static final String NOT_FOUND = "The stall with that id does not exist";

    @Autowired
    private StallRepository stallRepository;

    public String save(Stall stall) {
        stallRepository.save(stall);
        return "Stall saved successfully";
    }

    public List<Stall> getAll() {
        return stallRepository.findAll();
    }

    public Optional<Stall> getById(UUID id) {
        return stallRepository.findById(id);
    }

    public String update(UUID id, Stall stall) {
        Optional<Stall> found = stallRepository.findById(id);
        if (found.isEmpty()) {
            return NOT_FOUND;
        }
        Stall old = found.get();
        old.setStallNumber(stall.getStallNumber());
        old.setName(stall.getName());
        stallRepository.save(old);
        return "Stall updated successfully";
    }

    public String delete(UUID id) {
        Optional<Stall> found = stallRepository.findById(id);
        if (found.isEmpty()) {
            return NOT_FOUND;
        }
        stallRepository.deleteById(id);
        return "Stall deleted successfully";
    }
    public List<Stall> getFreeStalls() {
        return stallRepository.findFreeStalls();
    }
}
