package org.example.models;

import org.example.entities.Booking;

import java.util.List;

public class BookingsModelImpl implements IBookingsModel {
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
