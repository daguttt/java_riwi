package org.example.controllers;

import org.example.entities.Passenger;
import org.example.models.interfaces.IPassengersModel;

import java.util.List;
import java.util.Optional;

public class PassengersController {
    private final IPassengersModel passengersModel;

    public PassengersController(IPassengersModel passengersModel) {
        this.passengersModel = passengersModel;
    }

    public Optional<Passenger> create(Passenger basePassenger) {
        return this.passengersModel.create(basePassenger);
    }

    public Optional<Passenger> findById(int passengerIdToFind) {
        return this.passengersModel.findById(passengerIdToFind);
    }

    public List<Passenger> findAllByName(String name) {
        return this.passengersModel.findAllByName(name);
    }

    public boolean update(int passengerId, Passenger passengerToUpdate) {
        return this.passengersModel.update(passengerId, passengerToUpdate);
    }

    public boolean delete(int passengerId) {
        return this.passengersModel.delete(passengerId);
    }
}
