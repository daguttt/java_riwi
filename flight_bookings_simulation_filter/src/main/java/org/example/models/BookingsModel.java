package org.example.models;

import org.example.entities.Booking;
import org.example.models.interfaces.IBookingsModel;
import org.example.persistence.Database;

import java.util.List;

public class BookingsModel implements IBookingsModel {
    private final Database database;

    public BookingsModel(Database database) {
        this.database = database;
    }

    @Override
    public Booking create(Booking baseBooking) {
        return null;
    }

    @Override
    public List<Booking> findAllByFlightId(int flightId) {
        return List.of();
    }

    @Override
    public boolean updateBookingSeat(String newSeat) {
        return false;
    }

    @Override
    public boolean delete(int bookingIdToDelete) {
        return false;
    }
}
