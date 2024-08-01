package org.example.models;

import org.example.entities.Flight;

import java.sql.Date;
import java.util.List;

public class FlightModelImpl implements IFlightModel {
    @Override
    public Flight create(Flight baseFlight) {
        return null;
    }

    @Override
    public List<Flight> findAllByDestination(String destination) {
        return List.of();
    }

    @Override
    public boolean delete(int flightIdToDelete) {
        return false;
    }

    @Override
    public boolean updateFlightDate(int flightIdToUpdate, Date newDate) {
        return false;
    }
}
