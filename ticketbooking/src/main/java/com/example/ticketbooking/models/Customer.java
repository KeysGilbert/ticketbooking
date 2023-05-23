package com.example.ticketbooking.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Customer {

    @NotNull(message = "An id is required.")
    private int customerId;

    @NotNull(message = "A first name is required.")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotNull(message = "A last name is required.")
    @NotBlank(message = "Last name cannot be blank.")
    private String lastName;

    @NotNull(message = "Email is required.")
    @NotBlank(message = "Email cannot be blank.")
    private String email;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
