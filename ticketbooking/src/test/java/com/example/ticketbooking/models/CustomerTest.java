package com.example.ticketbooking.models;

import com.example.ticketbooking.domain.Result;
import jakarta.validation.*;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void shouldFailOnNullName() {
        //Arrange
        Customer customer = makeValidCustomer();
        customer.setFirstName(null);
        customer.setLastName(null);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //Act
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        Result<Customer> result = new Result<>();

        for(ConstraintViolation violation : violations) {
            result.addErrorMessage(violation.getMessage());
        }

        //Assert
        assertTrue(result.getErrorMessages().size() > 0);
    }

    @Test
    void shouldFailOnBlankName() {
        //Arrange
        Customer customer = makeValidCustomer();
        customer.setFirstName(" ");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //Act
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        Result<Customer> result = new Result<>();

        for(ConstraintViolation violation : violations) {
            result.addErrorMessage(violation.getMessage());
        }

        //Assert
        assertTrue(result.getErrorMessages().size() > 0);
    }

    @Test
    void shouldFailOnInvalidEmail() {
        //Arrange
        Customer customer = makeValidCustomer();
        customer.setEmail(null);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //Act
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        Result<Customer> result = new Result<>();

        for(ConstraintViolation violation : violations) {
            result.addErrorMessage(violation.getMessage());
        }

        //Assert
        assertTrue(result.getErrorMessages().size() > 0);
    }

    private Customer makeValidCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId(0);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("test@email.com");
        return customer;
    }

}