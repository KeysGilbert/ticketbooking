package com.example.ticketbooking.models;

import jakarta.validation.constraints.NotNull;

public class Ticket {

    @NotNull(message = "A ticket id is required.")
    private int ticket_id;

    @NotNull(message = "A movie id is required.")
    private int movie_id;

    @NotNull(message = "A customer id is required.")
    private int customer_id;

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
}
