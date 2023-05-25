package com.example.ticketbooking.models;

import com.example.ticketbooking.domain.Result;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void shouldFailOnNullTitle() {
        //Arrange
        Movie movie = makeValidMovie();
        movie.setTitle(null);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //Act
        Result<Movie> result = new Result<>();
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);

        for(ConstraintViolation violation : violations) {
            result.addErrorMessage(violation.getMessage());
        }

        //Assert
        assertTrue(result.getErrorMessages().size() > 0);
    }

    @Test
    void shouldFailOnBlankTitle() {
        //Arrange
        Movie movie = makeValidMovie();
        movie.setTitle(" ");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //Act
        Result<Movie> result = new Result<>();
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);

        for(ConstraintViolation violation : violations) {
            result.addErrorMessage(violation.getMessage());
        }

        //Assert
        assertTrue(result.getErrorMessages().size() > 0);
    }

    @Test
    void shouldFailOnInvalidTicketPrice() {
        //Arrange
        Movie movie = makeValidMovie();
        movie.setTicketPrice(BigDecimal.ZERO);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //Act
        Result<Movie> result = new Result<>();
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);

        for(ConstraintViolation violation : violations) {
            result.addErrorMessage(violation.getMessage());
        }

        //Assert
        assertTrue(result.getErrorMessages().size() > 0);
    }

    @Test
    void shouldFailOnInvalidDate() {
        //Arrange
        Movie movie = makeValidMovie();
        movie.setMovieDate(LocalDate.of(2020, 1, 1));
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //Act
        Result<Movie> result = new Result<>();
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);

        for(ConstraintViolation violation : violations) {
            result.addErrorMessage(violation.getMessage());
        }

        //Assert
        assertTrue(result.getErrorMessages().size() > 0);
    }

    private Movie makeValidMovie() {
        Movie movie = new Movie();
        movie.setMovieId(0);
        movie.setTitle("The Avengers");
        movie.setMovieDate(LocalDate.of(2012, 5, 4));
        movie.setTicketPrice(BigDecimal.valueOf(15.00));
        return movie;
    }
}