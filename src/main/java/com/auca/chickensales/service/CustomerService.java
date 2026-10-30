package com.auca.chickensales.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.chickensales.entity.Customer;
import com.auca.chickensales.repository.CustomerRepository;

@Service
public class CustomerService {

    public static final String NOT_FOUND = "The customer with that id does not exist";

    @Autowired
    private CustomerRepository customerRepository;

    public String save(Customer customer) {
        customerRepository.save(customer);
        return "Customer saved successfully";
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }


    public Optional<Customer> getById(UUID id) {
        return customerRepository.findById(id);
    }

    
    public String update(UUID id, Customer customer) {
        Optional<Customer> found = customerRepository.findById(id);
        if (found.isEmpty()) {
            return NOT_FOUND;
        }
        Customer old = found.get();
        old.setFirstName(customer.getFirstName());
        old.setLastName(customer.getLastName());
        old.setPhone(customer.getPhone());
        old.setAddress(customer.getAddress());
        customerRepository.save(old);
        return "Customer updated successfully";
    }

    public String delete(UUID id) {
        Optional<Customer> found = customerRepository.findById(id);
        if (found.isEmpty()) {
            return NOT_FOUND;
        }
        customerRepository.deleteById(id);
        return "Customer deleted successfully";
    }
}
