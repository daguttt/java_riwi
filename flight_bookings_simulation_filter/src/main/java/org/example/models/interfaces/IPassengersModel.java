package org.example.models.interfaces;

import org.example.entities.Passenger;

import java.util.List;
import java.util.Optional;

public interface IPassengersModel {

    Optional<Passenger> create(Passenger basePassenger);

    Optional<Passenger> findById(int passengerIdToFind);

    List<Passenger> findAllByName(String nameQuery);

    boolean update(int passengerId, Passenger passengerToUpdate);

    boolean delete(int passengerIdToDelete);
}
