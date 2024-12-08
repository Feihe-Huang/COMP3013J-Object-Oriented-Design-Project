package com.example.demo.repositories;

/**
 * This is an interface for customer entities querying.
 * Auto query powered by the JPA and SpringBoots.
 */

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
        List<Customer> findAll();
        Optional<Customer> findById(Long id);
        Customer findCustomerByEmail(String email);
        List<Customer> findCustomersByAddress(String address);
        List<Customer> findCustomersByName(String name);
}

