package org.example.models;

import org.example.entities.Flight;

import java.sql.Date;
import java.util.List;

public interface IFlightModel {

    Flight create(Flight baseFlight);

    List<Flight> findAllByDestination(String destination);

    boolean delete(int flightIdToDelete);

    boolean updateFlightDate(int flightIdToUpdate, Date newDate);
}
