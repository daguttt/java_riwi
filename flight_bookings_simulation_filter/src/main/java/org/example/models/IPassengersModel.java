package org.example.models;

import org.example.entities.Passenger;

import java.util.List;

public interface IPassengersModel {

    Passenger create(Passenger basePassenger);

    List<Passenger> findAllPassengerByName(String nameToFind);

    boolean update(int id, Passenger passengerToUpdate);

    boolean delete(int passengerIdToDelete);
}
