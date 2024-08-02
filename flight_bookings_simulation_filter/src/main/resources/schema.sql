START TRANSACTION;

CREATE DATABASE IF NOT EXISTS flight_bookings;

USE flight_bookings;

CREATE TABLE IF NOT EXISTS passengers(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    lastname VARCHAR(150) NOT NULL,
    document_number VARCHAR(15) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS planes(
    id INT PRIMARY KEY AUTO_INCREMENT,
    model VARCHAR(150) NOT NULL,
    capacity INT NOT NULL,
    CONSTRAINT capacity_chk CHECK(capacity > 0)
);

CREATE TABLE IF NOT EXISTS flights(
    id INT PRIMARY KEY AUTO_INCREMENT,
    destination VARCHAR(255) NOT NULL,
    departure_date DATE NOT NULL,
    departure_time TIME NOT NULL,
    planes_id INT NOT NULL,
    CONSTRAINT fk_planes_id FOREIGN KEY (planes_id) REFERENCES planes(id)
);

CREATE TABLE IF NOT EXISTS bookings(
    id INT PRIMARY KEY AUTO_INCREMENT,
    seat VARCHAR(10) NOT NULL,
    booking_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    passengers_id INT NOT NULL,
    flights_id INT NOT NULL,
    CONSTRAINT fk_passengers_id FOREIGN KEY (passengers_id) REFERENCES passengers(id),
    CONSTRAINT fk_flights_id FOREIGN KEY (flights_id) REFERENCES flights(id),
    CONSTRAINT un_seat_in_flight UNIQUE(flights_id, seat)
);

COMMIT;