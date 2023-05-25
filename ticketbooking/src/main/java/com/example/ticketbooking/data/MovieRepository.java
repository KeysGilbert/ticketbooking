package com.example.ticketbooking.data;

import com.example.ticketbooking.models.Movie;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MovieRepository {

    List<Movie> findAll();

    Movie findById(int id);

    @Transactional
    Movie add(Movie movie);

    @Transactional
    boolean update(Movie movie);

    @Transactional
    boolean delete(int id);
}
