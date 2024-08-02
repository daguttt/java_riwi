package org.example.models;

import org.example.entities.Passenger;
import org.example.models.interfaces.IPassengersModel;
import org.example.persistence.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
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
    public Optional<Passenger> findById(int passengerIdToFind) {
        var connection = database.openConnection();
        var sql = "SELECT id, name, lastname, document_number FROM passengers WHERE id = ?";

        Optional<Passenger> passenger = Optional.empty();
        try(var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, passengerIdToFind);

            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var id = resultSet.getInt("id");
                var name = resultSet.getString("name");
                var lastname = resultSet.getString("lastname");
                var documentNumber = resultSet.getString("document_number");
                passenger = Optional.of(new Passenger(id, name, lastname, documentNumber));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return passenger;
    }

    @Override
    public List<Passenger> findAllByName(String nameQuery) {
        var connection = database.openConnection();
        var sql = """
                SELECT id, name, lastname, document_number\s
                FROM passengers WHERE name LIKE ?;
                """;

        var passengerList = new ArrayList<Passenger>();

        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, nameQuery);

            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                var id = resultSet.getInt("id");
                var name = resultSet.getString("name");
                var lastname = resultSet.getString("lastname");
                var documentNumber = resultSet.getString("document_number");
                var passenger = new Passenger(id, name, lastname, documentNumber);
                passengerList.add(passenger);
            }
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return passengerList.stream().toList();

    }

    @Override
    public boolean update(int passengerId, Passenger passengerToUpdate) {
        var connection = database.openConnection();
        var sql = """
                UPDATE LOW_PRIORITY passengers\s
                SET
                    name = ?,
                    lastname = ?
                WHERE id = ?;
               """;

        boolean couldUpdate = false;
        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, passengerToUpdate.getName());
            statement.setString(2, passengerToUpdate.getLastname());
            statement.setInt(3, passengerId);

            var affectedRows = statement.executeUpdate();
            statement.close();

            if (affectedRows == 1) couldUpdate = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return couldUpdate;
    }

    @Override
    public boolean delete(int passengerIdToDelete) {
        var connection = database.openConnection();
        var sql = """
                DELETE FROM passengers\s
                WHERE id = ?;
               """;

        boolean couldDelete = false;
        try (var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, passengerIdToDelete);

            var affectedRows = statement.executeUpdate();
            statement.close();

            if (affectedRows == 1) couldDelete = true;

        } catch (SQLException e) {
            System.out.printf("Error deleting passenger (Error %s): %s%npassengerId: %d", e.getClass(), e.getMessage(), passengerIdToDelete);
            couldDelete = false;
        }

        database.closeConnection();
        return couldDelete;

    }
}
