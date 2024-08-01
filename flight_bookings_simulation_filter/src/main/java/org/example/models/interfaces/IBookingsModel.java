package org.example.models.interfaces;

import org.example.entities.Booking;

import java.util.List;

public interface IBookingsModel {
    Booking create(Booking baseBooking);

    List<Booking> findAllByFlightId(int flightId);

    boolean updateBookingSeat(String newSeat);

    boolean delete(int bookingIdToDelete);
}
