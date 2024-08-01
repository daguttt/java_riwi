package org.example.entities;

import java.sql.Date;

public class Booking {
    private int id;
    private int passengersId;
    private int flightsId;
    private Date bookingDate;
    private String seat;

    public Booking(int id, int passengersId, int flightsId, Date bookingDate, String seat) {
        this.id = id;
        this.passengersId = passengersId;
        this.flightsId = flightsId;
        this.bookingDate = bookingDate;
        this.seat = seat;
    }

    public Booking(int passengersId, int flightsId, Date bookingDate, String seat) {
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

    public void setPassengersId(int passengersId) {
        this.passengersId = passengersId;
    }

    public int getFlightsId() {
        return flightsId;
    }

    public void setFlightsId(int flightsId) {
        this.flightsId = flightsId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
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
                String.format("bookingDate: %1$Te/%1$Tm/%1$TY", getBookingDate()),
                String.format("seat: %s", getSeat()),
        };
        return String.join("\n", lines);

    }
}
