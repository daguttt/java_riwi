package org.example.entities;

import java.sql.Timestamp;

public class Flight {
    private int id;
    private String destination;
    private Timestamp startingDate;
    private Timestamp endingDate;
    private int planesId;

    public Flight(int id, String destination, Timestamp startingDate, Timestamp endingDate, int planesId) {
        this.id = id;
        this.destination = destination;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.planesId = planesId;
    }

    public Flight(String destination, Timestamp startingDate, Timestamp endingDate, int planesId) {
        this.destination = destination;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
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

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Timestamp getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Timestamp startingDate) {
        this.startingDate = startingDate;
    }

    public Timestamp getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Timestamp endingDate) {
        this.endingDate = endingDate;
    }

    public int getPlanesId() {
        return planesId;
    }

    public void setPlanesId(int planesId) {
        this.planesId = planesId;
    }

    @Override
    public String toString() {
        var lines = new String[] {
                String.format("id: %d", getId()),
                String.format("destination: %s", getDestination()),
                String.format("startingDate: %1$Te/%1$Tm/%1$TY %1$Tr", getStartingDate()),
                String.format("endingDate: %1$Te/%1$Tm/%1$TY %1$Tr", getEndingDate()),
                String.format("planesId: %d", getPlanesId()),
        };
        return String.join("\n", lines);

    }
}
