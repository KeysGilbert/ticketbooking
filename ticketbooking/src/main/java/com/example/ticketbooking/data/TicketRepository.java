package com.example.ticketbooking.data;

import com.example.ticketbooking.models.Customer;
import com.example.ticketbooking.models.Ticket;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TicketRepository {

    List<Ticket> findAll();

    Ticket findById(int id);

    Ticket findByCustomer(Customer customer);

    @Transactional
    boolean delete(int id);
}
