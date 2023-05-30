package com.example.ticketbooking.data;

import com.example.ticketbooking.models.Customer;
import com.example.ticketbooking.models.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CustomerJdbcTemplateRepository implements CustomerRepository {

    private JdbcTemplate jdbcTemplate;

    public CustomerJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Customer> findAll() {
        final String sql = "SELECT customer_id, first_name, last_name, email FROM Customers;";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Customer customer = new Customer();
            customer.setCustomerId(resultSet.getInt("customer_id"));
            customer.setFirstName(resultSet.getString("first_name"));
            customer.setLastName(resultSet.getString("last_name"));
            customer.setEmail(resultSet.getString("email"));
            return customer;
        });
    }

    @Override
    public Customer findById(int id) {
        final String sql = "SELECT customer_id, first_name, last_name, email FROM Customers WHERE customer_id = ?;";
        Customer result = jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Customer customer = new Customer();
            customer.setCustomerId(resultSet.getInt("customer_id"));
            customer.setFirstName(resultSet.getString("first_name"));
            customer.setLastName(resultSet.getString("last_name"));
            customer.setEmail(resultSet.getString("email"));
            return customer;
        }, id).stream().findFirst().orElse(null);

        if(result != null) {
            addTickets(result);
        }

        return result;
    }

    @Override
    @Transactional
    public Customer add(Customer customer) {
        final String sql = "INSERT INTO Customers (first_name, last_name, email) " +
                "VALUES (?, ?, ?);";
        KeyHolder key = new GeneratedKeyHolder();

        int rowsInserted = jdbcTemplate.update((connect) -> {
            PreparedStatement ps = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            return ps;
        }, key);

        //if failed
        if(rowsInserted <= 0) {
            return null;
        }

        //store generated key
        customer.setCustomerId(key.getKey().intValue());
        return customer;
    }

    @Override
    @Transactional
    public boolean update(Customer customer) {
        final String sql = "UPDATE Customers SET first_name = ?, last_name = ?, email = ? WHERE customer_id = ?;";
        int rowsUpdated = jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getCustomerId());
        return rowsUpdated > 0;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        final String sql = "DELETE FROM Customers WHERE customer_id = ?;";
        int rowsDeleted = jdbcTemplate.update(sql, id);
        return rowsDeleted > 0;
    }

    public void addTickets(Customer customer) {
        //grab ticket and customer info for particular customer
        final String sql = "SELECT t.ticket_id, t.movie_id, t.customer_id, c.first_name, c.last_name FROM Tickets t " +
                "INNER JOIN Customers c ON c.customer_id = t.customer_id WHERE c.customer_id = ?;";
        var tickets = jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Ticket ticket = new Ticket();
            ticket.setTicket_id(resultSet.getInt("ticket_id"));
            ticket.setCustomer_id(resultSet.getInt("customer_id"));
            ticket.setMovie_id(resultSet.getInt("movie_id"));
            return ticket;
        }, customer.getCustomerId());

        customer.setTickets(tickets);
    }
}
