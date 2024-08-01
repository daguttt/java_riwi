package org.example.models.interfaces;

import org.example.entities.Passenger;

import java.util.List;
import java.util.Optional;

public interface IPassengersModel {

    Optional<Passenger> create(Passenger basePassenger);

    List<Passenger> findAllPassengerByName(String nameToFind);

    boolean update(int id, Passenger passengerToUpdate);

    boolean delete(int passengerIdToDelete);
}
