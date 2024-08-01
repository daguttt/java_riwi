package org.example.models;

import org.example.entities.Passenger;
import org.example.models.interfaces.IPassengersModel;
import org.example.persistence.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public class PassengersModel implements IPassengersModel {
    private final Database database;
    public PassengersModel(Database database) {
        this.database = database;
    }

    @Override
    public Optional<Passenger> create(Passenger basePassenger) {
        var connection = database.openConnection();
        var sql = """
                INSERT INTO passengers (name, lastname, document_number)\s
                    VALUES (?, ?, ?);
                """;

        Optional<Passenger> createdPassenger;

        try (var statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, basePassenger.getName());
            statement.setString(2, basePassenger.getLastname());
            statement.setString(3, basePassenger.getDocumentNumber());

            statement.execute();

            var resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                var givenPlaneId = resultSet.getInt(1);
                basePassenger.setId(givenPlaneId);
                createdPassenger = Optional.of(basePassenger);
            } else throw new SQLException("Couldn't create passenger");

            resultSet.close();

        }
        catch (SQLIntegrityConstraintViolationException e) {
            createdPassenger = Optional.empty();
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return createdPassenger;
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
