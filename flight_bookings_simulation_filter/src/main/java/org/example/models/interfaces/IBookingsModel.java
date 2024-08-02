package org.example.models.interfaces;

import org.example.entities.Booking;

import java.util.List;
import java.util.Optional;

public interface IBookingsModel {
    Booking create(Booking baseBooking);

    List<Booking> findAllByFlightId(int flightIdQuery);

    Optional<Booking> findById(int bookingId);

    boolean checkSeatAvailability(int flightId, String seatToCheck);

    boolean updateBookingSeat(int bookingId, String newSeat);

    boolean delete(int bookingIdToDelete);
}
