package org.example.models;

import org.example.entities.Plane;
import org.example.models.interfaces.IPlanesModel;
import org.example.persistence.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlanesModel implements IPlanesModel {
    private final Database database;

    public PlanesModel(Database database) {
        this.database = database;
    }

    @Override
    public Plane create(Plane basePlane) {
        var connection = database.openConnection();
        var sql = """
                INSERT INTO planes (model, capacity)\s
                    VALUES (?, ?);
                """;

        try (var statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, basePlane.getModel());
            statement.setInt(2, basePlane.getCapacity());

            statement.execute();

            var resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                var givenPlaneId = resultSet.getInt(1);
                basePlane.setId(givenPlaneId);
            } else throw new SQLException("Couldn't create plane");

            resultSet.close();

        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return basePlane;

    }

    @Override
    public Plane findById(int planeIdToFind) {
        var connection = database.openConnection();
        var sql = "SELECT id, model, capacity FROM planes WHERE id = ?";

        Plane plane = null;
        try(var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, planeIdToFind);

            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var id = resultSet.getInt("id");
                var model = resultSet.getString("model");
                var capacity = resultSet.getInt("capacity");
                plane = new Plane(id, model, capacity);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return plane;
    }

    @Override
    public boolean delete(int planeIdToRemove) {
        return false;
    }
}
