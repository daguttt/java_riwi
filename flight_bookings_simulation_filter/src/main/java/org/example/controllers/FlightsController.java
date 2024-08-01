package org.example.controllers;

import org.example.entities.Flight;
import org.example.models.interfaces.IFlightsModel;

public class FlightsController {
    private final IFlightsModel flightsModel;

    public FlightsController(IFlightsModel flightsModel) {
        this.flightsModel = flightsModel;
    }

    public Flight create(Flight baseFlight) {
        return this.flightsModel.create(baseFlight);
    }

}
