package com.example.ticketbooking.data;

import com.example.ticketbooking.models.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class MovieJdbcTemplateRepository implements MovieRepository {

    private JdbcTemplate jdbcTemplate;

    public MovieJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> findAll() {
        final String sql = "SELECT movie_id, title, ticket_price, movie_date FROM Movies;";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Movie movie = new Movie();
            movie.setMovieId(resultSet.getInt("movie_id"));
            movie.setTitle(resultSet.getString("title"));
            movie.setTicketPrice(resultSet.getBigDecimal("ticket_price"));
            movie.setMovieDate(resultSet.getDate("movie_date").toLocalDate());
            return movie;
        });
    }

    @Override
    public Movie findById(int id) {
        final String sql = "SELECT movie_id, title, ticket_price, movie_date FROM Movies WHERE movie_id = ?;";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Movie movie = new Movie();
            movie.setMovieId(resultSet.getInt("movie_id"));
            movie.setTitle(resultSet.getString("title"));
            movie.setTicketPrice(resultSet.getBigDecimal("ticket_price"));
            movie.setMovieDate(resultSet.getDate("movie_date").toLocalDate());
            return movie;
        }, id).stream().findFirst().orElse(null);
    }

    @Override
    @Transactional
    public Movie add(Movie movie) {
        KeyHolder key = new GeneratedKeyHolder();
        final String sql = "INSERT INTO Movies (title, ticket_price, movie_date) " +
                "VALUES (?, ?, ?);";
        int rowsInserted = jdbcTemplate.update((connect) -> {
            PreparedStatement ps = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, movie.getTitle());
            ps.setBigDecimal(2, movie.getTicketPrice());
            ps.setDate(3, java.sql.Date.valueOf(movie.getMovieDate()));
            return ps;
        }, key);

        //if insert fails
        if(rowsInserted <= 0 ) {
            return null;
        }

        //store generated id
        movie.setMovieId(key.getKey().intValue());
        return movie;
    }

    @Override
    @Transactional
    public boolean update(Movie movie) {
        final String sql = "UPDATE Movies SET title = ?, ticket_price = ?, movie_date = ? " +
                "WHERE movie_id = ?;";
        int rowsUpdated = jdbcTemplate.update(sql, movie.getTitle(), movie.getTicketPrice(), movie.getMovieDate(), movie.getMovieId());
        return rowsUpdated > 0;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        final String sql = "DELETE FROM Movies WHERE movie_id = ?;";
        int rowsDeleted = jdbcTemplate.update(sql, id);
        return rowsDeleted > 0;
    }
}
