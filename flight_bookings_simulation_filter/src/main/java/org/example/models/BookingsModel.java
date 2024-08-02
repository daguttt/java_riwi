package org.example.models;

import org.example.entities.Booking;
import org.example.models.interfaces.IBookingsModel;
import org.example.persistence.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingsModel implements IBookingsModel {
    private final Database database;

    public BookingsModel(Database database) {
        this.database = database;
    }

    @Override
    public Booking create(Booking baseBooking) {
        var connection = database.openConnection();
        var sql = """
                INSERT INTO bookings (passengers_id, flights_id, booking_date, seat)\s
                    VALUES (?, ?, ?, ?);
                """;

        try (var statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, baseBooking.getPassengersId());
            statement.setInt(2, baseBooking.getFlightsId());
            statement.setTimestamp(3, baseBooking.getBookingDate());
            statement.setString(4, baseBooking.getSeat());

            statement.execute();

            var resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                var givenBookingId = resultSet.getInt(1);
                baseBooking.setId(givenBookingId);
            } else throw new SQLException("Couldn't create booking");

            resultSet.close();

        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return baseBooking;

    }

    @Override
    public List<Booking> findAllByFlightId(int flightIdQuery) {
        var connection = database.openConnection();
        var sql = """
                SELECT id, passengers_id, flights_id, booking_date, seat\s
                FROM bookings WHERE flights_id = ?
                """;

        var bookingList = new ArrayList<Booking>();

        try (var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, flightIdQuery);

            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                var id = resultSet.getInt("id");
                var passengersId = resultSet.getInt("passengers_id");
                var flightsId = resultSet.getInt("flights_id");
                var bookingDate = resultSet.getTimestamp("booking_date");
                var seat = resultSet.getString("seat");
                var booking = new Booking(id, passengersId, flightsId, bookingDate, seat);
                bookingList.add(booking);
            }
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return bookingList.stream().toList();

    }

    @Override
    public Optional<Booking> findById(int bookingId) {
        var connection = database.openConnection();
        var sql = """
                SELECT id, passengers_id, flights_id, booking_date, seat\s
                FROM bookings WHERE id = ?
                """;

        Optional<Booking> booking = Optional.empty();
        try(var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookingId);

            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var id = resultSet.getInt("id");
                var passengersId = resultSet.getInt("passengers_id");
                var flightsId = resultSet.getInt("flights_id");
                var bookingDate = resultSet.getTimestamp("booking_date");
                var seat = resultSet.getString("seat");
                booking = Optional.of(new Booking(id, passengersId, flightsId, bookingDate, seat));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return booking;

    }

    @Override
    public boolean checkSeatAvailability(int flightId, String seatToCheck) {
        var connection = database.openConnection();
        var sql = "SELECT id FROM bookings WHERE flights_id = ? AND seat = ?";

        boolean isAvailable;
        try(var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, flightId);
            statement.setString(2, seatToCheck);

            var resultSet = statement.executeQuery();
            isAvailable = !resultSet.next();

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return isAvailable;
    }

    @Override
    public boolean updateBookingSeat(int bookingId, String newSeat) {
        var connection = database.openConnection();
        var sql = """
                UPDATE LOW_PRIORITY bookings\s
                SET
                    seat = ?
                WHERE id = ?;
               """;

        boolean couldUpdate = false;
        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, newSeat);
            statement.setInt(2, bookingId);

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
    public boolean delete(int bookingIdToDelete) {
        var connection = database.openConnection();
        var sql = """
                DELETE FROM bookings\s
                WHERE id = ?;
               """;

        boolean couldDelete = false;
        try (var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookingIdToDelete);

            var affectedRows = statement.executeUpdate();
            statement.close();

            if (affectedRows == 1) couldDelete = true;

        } catch (SQLException e) {
            System.out.printf("Error deleting booking (Error %s): %s%nbookingId: %d", e.getClass(), e.getMessage(), bookingIdToDelete);
            couldDelete = false;
        }

        database.closeConnection();
        return couldDelete;

    }
}
