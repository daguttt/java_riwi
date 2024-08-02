package org.example.entities;

import java.sql.Date;
import java.sql.Time;

public class Flight {
    private int id;
    private final String destination;
    private Date departureDate;
    private Time departureTime;
    private final int planesId;

    public Flight(int id, String destination, Date departureDate, Time departureTime, int planesId) {
        this.id = id;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.planesId = planesId;
    }

    public Flight(String destination, Date departureDate, Time departureTime, int planesId) {
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.planesId = planesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public int getPlanesId() {
        return planesId;
    }

    @Override
    public String toString() {
        var lines = new String[] {
                String.format("id: %d", getId()),
                String.format("destination: %s", getDestination()),
                String.format("departure: %1$Te/%1$Tm/%1$TY %2$Tr", getDepartureDate(), getDepartureTime()),
                String.format("planesId: %d", getPlanesId()),
        };
        return String.join("\n", lines);

    }
}
