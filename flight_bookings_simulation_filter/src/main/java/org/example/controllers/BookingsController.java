package org.example.controllers;

import org.example.entities.Booking;
import org.example.models.interfaces.IBookingsModel;

import java.util.List;
import java.util.Optional;

public class BookingsController {
    private final IBookingsModel bookingsModel;

    public BookingsController(IBookingsModel bookingsModel) {
        this.bookingsModel = bookingsModel;
    }

    public Booking create(Booking baseBooking) {
        return this.bookingsModel.create(baseBooking);
    }

    public Optional<Booking> findById(int bookingId) {
        return this.bookingsModel.findById(bookingId);
    }

    public boolean checkSeatAvailability(int flightId, String seatToCheck) {
        return this.bookingsModel.checkSeatAvailability(flightId, seatToCheck);
    }

    public List<Booking> findAllByFlightId(int flightIdQuery) {
        return this.bookingsModel.findAllByFlightId(flightIdQuery);
    }

    public boolean updateBookingSeat(int bookingId, String newSeat) {
        return this.bookingsModel.updateBookingSeat(bookingId, newSeat);
    }

    public boolean delete(int bookingId) {
        return this.bookingsModel.delete(bookingId);
    }

}
