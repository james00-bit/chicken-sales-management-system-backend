package com.auca.chickensales.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.chickensales.entity.Breed;
import com.auca.chickensales.repository.BreedRepository;


@Service
public class BreedService {

    public static final String NOT_FOUND = "The breed with that id does not exist";

    @Autowired
    private BreedRepository breedRepository;

    public String save(Breed breed) {
        breedRepository.save(breed);
        return "Breed saved successfully";
    }

    public List<Breed> getAll() {
        return breedRepository.findAll();
    }


    public Optional<Breed> getById(UUID id) {
        return breedRepository.findById(id);
    }

    public String update(UUID id, Breed breed) {
        Optional<Breed> found = breedRepository.findById(id);
        if (found.isEmpty()) {
            return NOT_FOUND;
        }
        Breed old = found.get();
        old.setName(breed.getName());
        breedRepository.save(old);
        return "Breed updated successfully";
    }


    public String delete(UUID id) {
        Optional<Breed> found = breedRepository.findById(id);
        if (found.isEmpty()) {
            return NOT_FOUND;
        }
        breedRepository.deleteById(id);
        return "Breed deleted successfully";
    }
    public List<Breed> getUnusedBreeds() {
        return breedRepository.findUnusedBreeds();
    }
}
