package org.example.controllers;

import org.example.entities.Flight;
import org.example.models.interfaces.IFlightsModel;

import java.util.List;
import java.util.Optional;

public class FlightsController {
    private final IFlightsModel flightsModel;

    public FlightsController(IFlightsModel flightsModel) {
        this.flightsModel = flightsModel;
    }

    public Flight create(Flight baseFlight) {
        return this.flightsModel.create(baseFlight);
    }

    public List<Flight> findAllByDestination(String destination) {
        return this.flightsModel.findAllByDestination(destination);
    }

    public Optional<Flight> findById(int flightIdToFind) {
        return this.flightsModel.findById(flightIdToFind);
    }

    public boolean update(Flight flightToUpdate, int flightId) {
        return this.flightsModel.update(flightId, flightToUpdate);
    }

    public boolean delete(int flightId) {
        return this.flightsModel.delete(flightId);
    }
}
