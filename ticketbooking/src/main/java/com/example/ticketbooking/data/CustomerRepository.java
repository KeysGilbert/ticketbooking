package com.example.ticketbooking.data;

import com.example.ticketbooking.models.Customer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerRepository {

    List<Customer> findAll();

    Customer findById(int id);

    @Transactional
    Customer add(Customer customer);

    @Transactional
    boolean update(Customer customer);

    @Transactional
    boolean delete(int id);
}
