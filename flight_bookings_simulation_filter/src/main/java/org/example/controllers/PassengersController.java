package org.example.controllers;

import org.example.entities.Passenger;
import org.example.models.interfaces.IPassengersModel;

import java.util.Optional;

public class PassengersController {
    private final IPassengersModel passengersModel;

    public PassengersController(IPassengersModel passengersModel) {
        this.passengersModel = passengersModel;
    }

    public Optional<Passenger> create(Passenger basePassenger) {
        return this.passengersModel.create(basePassenger);
    }
}
