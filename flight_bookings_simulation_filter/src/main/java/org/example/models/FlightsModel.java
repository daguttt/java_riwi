package org.example.models;

import org.example.entities.Flight;
import org.example.models.interfaces.IFlightsModel;
import org.example.persistence.Database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FlightsModel implements IFlightsModel {
    private final Database database;
    public FlightsModel(Database database) {
        this.database = database;
    }

    @Override
    public Flight create(Flight baseFlight) {
        var connection = database.openConnection();
        var sql = """
                INSERT INTO flights (destination, starting_date, ending_date, planes_id)\s
                    VALUES (?, ?, ?, ?);
                """;

        try (var statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, baseFlight.getDestination());
            statement.setObject(2, baseFlight.getStartingDate());
            statement.setObject(3, baseFlight.getEndingDate());
            statement.setInt(4, baseFlight.getPlanesId());

            statement.execute();

            var resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                var givenFlightId = resultSet.getInt(1);
                baseFlight.setId(givenFlightId);
            } else throw new SQLException("Couldn't create flight");

            resultSet.close();

        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return baseFlight;
    }

    @Override
    public List<Flight> findAllByDestination(String destination) {
        return List.of();
    }

    @Override
    public boolean delete(int flightIdToDelete) {
        return false;
    }

    @Override
    public boolean updateFlightDate(int flightIdToUpdate, Date newDate) {
        return false;
    }
}
