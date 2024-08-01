package org.example.models;

import org.example.entities.Passenger;

import java.util.List;

public class PassengersModelImpl implements IPassengersModel {
    @Override
    public Passenger create(Passenger basePassenger) {
        return null;
    }

    @Override
    public List<Passenger> findAllPassengerByName(String nameToFind) {
        return List.of();
    }

    @Override
    public boolean update(int id, Passenger passengerToUpdate) {
        return false;
    }

    @Override
    public boolean delete(int passengerIdToDelete) {
        return false;
    }
}
