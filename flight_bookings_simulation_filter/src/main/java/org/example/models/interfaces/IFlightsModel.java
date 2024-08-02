package org.example.models.interfaces;

import org.example.entities.Flight;

import java.util.List;
import java.util.Optional;

public interface IFlightsModel {

    Flight create(Flight baseFlight);

    List<Flight> findAllByDestination(String destinationQuery);

    Optional<Flight> findById(int flightIdToFind);

    boolean update(int flightId, Flight flightToUpdate);

    boolean delete(int flightIdToDelete);
}
