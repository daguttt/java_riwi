package org.example.entities;

import java.sql.Timestamp;

public class Booking {
    private int id;
    private final int passengersId;
    private final int flightsId;
    private final Timestamp bookingDate;
    private String seat;

    public Booking(int id, int passengersId, int flightsId, Timestamp bookingDate, String seat) {
        this.id = id;
        this.passengersId = passengersId;
        this.flightsId = flightsId;
        this.bookingDate = bookingDate;
        this.seat = seat;
    }

    public Booking(int passengersId, int flightsId, Timestamp bookingDate, String seat) {
        this.passengersId = passengersId;
        this.flightsId = flightsId;
        this.bookingDate = bookingDate;
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassengersId() {
        return passengersId;
    }

    public int getFlightsId() {
        return flightsId;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        var lines = new String[] {
                String.format("id: %d", getId()),
                String.format("passengersId: %d", getPassengersId()),
                String.format("flightsId: %d", getFlightsId()),
                String.format("bookingDate: %1$Te/%1$Tm/%1$TY %1$Tr", getBookingDate()),
                String.format("seat: %s", getSeat()),
        };
        return String.join("\n", lines);

    }
}
