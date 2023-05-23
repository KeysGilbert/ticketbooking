package com.example.ticketbooking.models;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Movie {

    @NotNull(message = "An id is required.")
    private int movieId;

    @NotNull(message = "A movie must have a title.")
    @NotBlank(message = "Title cannot be blank.")
    private String title;

    @NotNull(message = "A ticket price is required.")
    @Min(value = 1, message = "ticket price cannot be lower than $1.00.")
    private BigDecimal ticketPrice;

    @NotNull(message = "Movie must have a date.")
    @FutureOrPresent(message = "Date cannot be in the past.")
    private LocalDate movieDate;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public LocalDate getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(LocalDate movieDate) {
        this.movieDate = movieDate;
    }
}
