DROP DATABASE IF EXISTS ticketbookingdb;

CREATE DATABASE ticketbookingdb;

USE ticketbookingdb;


CREATE TABLE Customers (
	customer_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(75) NOT NULL,
    PRIMARY KEY (customer_id)
);

CREATE TABLE Movies (
	movie_id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    ticket_price DECIMAL(8,2) NOT NULL,
    movie_date DATE NOT NULL,
    PRIMARY KEY (movie_id)
);

CREATE TABLE Tickets (
	ticket_id INT NOT NULL AUTO_INCREMENT,
    movie_id INT,
    customer_id INT,
    CONSTRAINT fk_movie FOREIGN KEY (movie_id) REFERENCES Movies(movie_id),
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
    PRIMARY KEY (ticket_id)
);

-- bridge table for many to many relationship
CREATE TABLE Movies_tickets (
	movie_id INT,
    ticket_id INT,
    FOREIGN KEY (movie_id) REFERENCES Movies(movie_id),
    FOREIGN KEY (ticket_id) REFERENCES Tickets(ticket_id), 
    PRIMARY KEY (movie_id, ticket_id)
);