package org.example;

import org.example.controllers.BookingsController;
import org.example.controllers.FlightsController;
import org.example.controllers.PassengersController;
import org.example.controllers.PlanesController;
import org.example.entities.Booking;
import org.example.entities.Flight;
import org.example.entities.Passenger;
import org.example.entities.Plane;
import org.example.models.BookingsModel;
import org.example.models.FlightsModel;
import org.example.models.PassengersModel;
import org.example.models.PlanesModel;
import org.example.models.interfaces.IBookingsModel;
import org.example.models.interfaces.IFlightsModel;
import org.example.models.interfaces.IPassengersModel;
import org.example.models.interfaces.IPlanesModel;
import org.example.persistence.Database;
import org.example.utils.DateStringParser;
import org.example.utils.Prompts;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("***********************************************************");
        System.out.println("Reservaciones de vuelos APP");
        System.out.println("***********************************************************");

        var host = args[0];
        var port = args[1];
        var dbName = args[2];
        var dbUser = args[3];
        var dbPassword = args[4];

        // -****************************
        var database = new Database(host, port, dbName, dbUser, dbPassword);
        // Models
        IPlanesModel planesModel = new PlanesModel(database);
        IPassengersModel passengersModel = new PassengersModel(database);
        IFlightsModel flightsModel = new FlightsModel(database);
        IBookingsModel bookingsModel = new BookingsModel(database);

        // Controllers
        var planesController = new PlanesController(planesModel);
        var passengersController = new PassengersController(passengersModel);
        var flightsController = new FlightsController(flightsModel);
        var bookingsController = new BookingsController(bookingsModel);



        // -****************************

        boolean isMenuOpened = true;
        while (isMenuOpened) {
            var menuOptionsMessage = """
                    ********************* Menu *********************
                    0. Salir.
                    1. Crear avión. ✅
                    2. Crear pasajero. ✅
                    3. Crear vuelo. ✅
                    4. Crear reservacion.
                    5. Buscar vuelos por destino. ✅
                    6. Buscar pasajeros por nombre. ✅
                    7. Mostrar todas las reservas de un vuelo. ✅
                    8. Cambiar fecha de salida de un vuelo. ✅
                    9. Actualizar pasajero. ✅
                    10. Actualizar asiento de una reservación. ✅
                    11. Eliminar un avión. ✅
                    12. Eliminar un vuelo. ✅
                    13. Eliminar un pasajero. ✅
                    14. Eliminar una reservación. ✅

                    Ingresa la opción que deseas hacer:
                    """;
            var option = JOptionPane.showInputDialog(menuOptionsMessage);
            if (option == null) return;

            switch (option) {
                case "0" -> isMenuOpened = false;
                // Create plane
                case "1" -> {
                    var basePrompt = "Creando un avión......";
                    var modelPrompt = Prompts.getPromptMessage(basePrompt, "Ingresa el modelo del avión:");
                    var model = JOptionPane.showInputDialog(modelPrompt);

                    var capacityPrompt = Prompts.getPromptMessage(basePrompt, "Ingresa la capacidad del avión:");
                    var capacity = JOptionPane.showInputDialog(capacityPrompt);

                    var basePlane = new Plane(model, Integer.parseInt(capacity));
                    var createdPlane = planesController.create(basePlane);

                    var outputMessage = String.format("Avión creado satisfactoriamente!%n%s", createdPlane);
                    JOptionPane.showMessageDialog(null, outputMessage);
                }
                // Create passenger
                case "2" -> {
                    var basePrompt = "Creando un pasajero......";
                    var namePrompt = Prompts.getPromptMessage(basePrompt, "Ingresa el nombre del pasajero:");
                    var name = JOptionPane.showInputDialog(namePrompt);

                    var lastnamePrompt = Prompts.getPromptMessage(basePrompt, "Ingresa el apellido del pasajero:");
                    var lastname = JOptionPane.showInputDialog(lastnamePrompt);

                    var documentNumberPrompt = Prompts.getPromptMessage(basePrompt, "Ingresa el número de documento del pasajero:");
                    var documentNumber = JOptionPane.showInputDialog(documentNumberPrompt);

                    var basePassenger = new Passenger(name, lastname, documentNumber);
                    var createdPassenger = passengersController.create(basePassenger);

                    if (createdPassenger.isEmpty()) {
                        var duplicatedPassengerMessage = String.format("El pasajero con el número de documento '%s' ya ha sido creado", documentNumber);
                        JOptionPane.showMessageDialog(null, duplicatedPassengerMessage);
                        break;
                    }

                    var outputMessage = String.format("Pasajero creado satisfactoriamente!%n%s", createdPassenger.get());
                    JOptionPane.showMessageDialog(null, outputMessage);
                }
                // Create flight
                case "3" -> {
                    var basePrompt = "Creando un vuelo......";

                    var planesIdPrompt = Prompts.getPromptMessage(basePrompt, "Ingresa el id del avión asociado al vuelo:");
                    var planesId = JOptionPane.showInputDialog(planesIdPrompt);

                    var plane = planesController.findById(Integer.parseInt(planesId));

                    if (plane.isEmpty()) {
                        var errorMessage = String.format("Avión con id '%s' no encontrado", planesId);
                        JOptionPane.showMessageDialog(null, errorMessage);
                        break;
                    }

                    var planeFoundMessage = String.format("Avión encontrado!%n%s", plane.get());
                    JOptionPane.showMessageDialog(null, planeFoundMessage);

                    var destinationPrompt = Prompts.getPromptMessage(basePrompt, "Ingresa el destino del vuelo:");
                    var destination = JOptionPane.showInputDialog(destinationPrompt);

                    var departureDatePrompt = Prompts.getPromptMessage(basePrompt,
                            "Ingresa la fecha de salida del vuelo (Formato YYYY-MM-DD) :");
                    var departureDateInLocalDateTime = askForLocalDate(departureDatePrompt);
                    var departureDate = Date.valueOf(departureDateInLocalDateTime);

                    var departureTimePrompt = Prompts.getPromptMessage(basePrompt,
                            "Ingresa la hora de salida del vuelo (Formato hh:mm:ss) :");
                    var departureTime = askForTime(departureTimePrompt);

                    var baseFlight = new Flight(destination, departureDate, departureTime, Integer.parseInt(planesId));
                    var createdFlight = flightsController.create(baseFlight);

                    var outputMessage = String.format("Vuelo creado satisfactoriamente!%n%s", createdFlight);
                    JOptionPane.showMessageDialog(null, outputMessage);
                }
                // Create booking
                case "4" -> {
                    var basePrompt = "Creando reservación......";

                    String passengerIdPrompt = Prompts.getPromptMessage(basePrompt, "Ingresa el id del pasajero:");
                    String passengerId = JOptionPane.showInputDialog(passengerIdPrompt);
                    var passenger = passengersController.findById(Integer.parseInt(passengerId));
                    if (passenger.isEmpty()) {
                        var errorMessage = String.format("Pasajero con id '%s' no encontrado", passengerId);
                        JOptionPane.showMessageDialog(null, errorMessage);
                        break;
                    }

                    String flightIdPrompt = Prompts.getPromptMessage(basePrompt, "Ingresa el id del vuelo:");
                    String flightId = JOptionPane.showInputDialog(flightIdPrompt);
                    var flight = flightsController.findById(Integer.parseInt(flightId));
                    if (flight.isEmpty()) {
                        var errorMessage = String.format("Vuelo con id '%s' no encontrado", flightId);
                        JOptionPane.showMessageDialog(null, errorMessage);
                        break;
                    }
                    
                    var bookingDate = Timestamp.valueOf(LocalDateTime.now());

                    String seatPrompt = Prompts.getPromptMessage(basePrompt, "Ingresa el asiento:");
                    String seat = JOptionPane.showInputDialog(seatPrompt);
                    boolean isSeatAvailable = bookingsController.checkSeatAvailability(Integer.parseInt(flightId), seat);
                    if (!isSeatAvailable) {
                        String seatTakenMessage = "El asiento ingresado ya ha sido reservado";
                        JOptionPane.showMessageDialog(null, seatTakenMessage);
                        break;
                    }
                    
                    var baseBooking = new Booking(Integer.parseInt(passengerId), Integer.parseInt(flightId), bookingDate, seat);
                    var createdBooking = bookingsController.create(baseBooking);

                    String outputMessage = String.format("Reserva creada satisfactoriamente!%n%s", createdBooking);
                    JOptionPane.showMessageDialog(null, outputMessage);
                }
                // Search flights by destination
                case "5" -> {
                    System.out.println("Search flights by destination");
                    var basePrompt = "Buscando vuelos por destino......";

                    String destinationPrompt = Prompts.getPromptMessage(basePrompt, "Ingresa el destino por el cual quieres buscar:");
                    String destination = JOptionPane.showInputDialog(destinationPrompt);
                    var searchedFlights = flightsController.findAllByDestination(destination);
                    
                    if (searchedFlights.isEmpty()) {
                        var errorMessage = String.format("No se encontraron vuelos disponbles a '%s'", destination);
                        JOptionPane.showMessageDialog(null, errorMessage);
                        break;
                    }
                    var flightsAsStringList = searchedFlights.stream().map(Flight::toString).toList();
                    var flightListMessage = String.join("\n--------------\n", flightsAsStringList);
                    JOptionPane.showMessageDialog(null, flightListMessage);
                }
                // Search passenger by name
                case "6" -> {
                    System.out.println("Searching passenger by name");
                    var basePrompt = "Buscando pasajeros por nombre......";

                    String namePrompt = Prompts.getPromptMessage(basePrompt, "Ingresa el nombre por el cual quieres buscar:");
                    String name = JOptionPane.showInputDialog(namePrompt);
                    var searchedPassengers = passengersController.findAllByName(name);

                    if (searchedPassengers.isEmpty()) {
                        var errorMessage = String.format("No se encontraron pasajeros cone el nombre '%s'", name);
                        JOptionPane.showMessageDialog(null, errorMessage);
                        break;
                    }
                    var passengersAsStringList = searchedPassengers.stream().map(Passenger::toString).toList();
                    var passengersListMessage = String.join("\n--------------\n", passengersAsStringList);
                    JOptionPane.showMessageDialog(null, passengersListMessage);

                }
                // Show all bookings by flight
                case "7" -> {
                    System.out.println("Showing all bookings by flight");
                    var basePrompt = "Mostrando reservas de un vuelo...";
                    String flightIdPrompt = Prompts.getPromptMessage(basePrompt, "Ingresa el id del vuelo:");
                    String flightId = JOptionPane.showInputDialog(flightIdPrompt);
                    var flight = flightsController.findById(Integer.parseInt(flightId));
                    if (flight.isEmpty()) {
                        var errorMessage = String.format("Vuelo con id '%s' no encontrado", flightId);
                        JOptionPane.showMessageDialog(null, errorMessage);
                        break;
                    }

                    var searchedBookings = bookingsController.findAllByFlightId(Integer.parseInt(flightId));

                    if (searchedBookings.isEmpty()) {
                        var errorMessage = String.format("No se encontraron reservas asociadas al vuelo: '%s'", flightId);
                        JOptionPane.showMessageDialog(null, errorMessage);
                        break;
                    }
                    var bookingsAsStringList = searchedBookings.stream().map(Booking::toString).toList();
                    var bookingsListMessage = String.join("\n--------------\n", bookingsAsStringList);
                    JOptionPane.showMessageDialog(null, bookingsListMessage);

                }
                // Update flight departure date
                case "8" -> {
                    System.out.println("Updating flight departure date...");

                    var basePrompt = "Actualizando la fecha de sálida de un vuelo....";
                    String flightIdPrompt = Prompts.getPromptMessage(basePrompt, "Ingresa el id del vuelo:");
                    String flightId = JOptionPane.showInputDialog(flightIdPrompt);
                    int intFlightId = Integer.parseInt(flightId);

                    var flight = flightsController.findById(intFlightId);
                    if (flight.isEmpty()) {
                        var errorMessage = String.format("Vuelo con id '%s' no encontrado", flightId);
                        JOptionPane.showMessageDialog(null, errorMessage);
                        break;
                    }

                    var newDepartureDatePrompt = Prompts.getPromptMessage(basePrompt,
                            "Ingresa la NUEVA fecha de salida del vuelo (Formato YYYY-MM-DD) :");
                    var newDepartureDateInLocalDateTime = askForLocalDate(newDepartureDatePrompt);
                    var newDepartureDate = Date.valueOf(newDepartureDateInLocalDateTime);


                    var newDepartureTimePrompt = Prompts.getPromptMessage(basePrompt,
                            "Ingresa la hora de salida del vuelo (Formato hh:mm:ss) :");
                    var newDepartureTime = askForTime(newDepartureTimePrompt);

                    var flightVal = flight.get();

                    flightVal.setDepartureDate(newDepartureDate);
                    flightVal.setDepartureTime(newDepartureTime);

                    boolean couldUpdateFlight = flightsController.update(flightVal, intFlightId);

                    var flightDestination = flightVal.getDestination();
                    if (couldUpdateFlight) {
                        var successMessage = String.format("Vuelo con destino a '%s' actualizado con éxito!", flightDestination);
                        JOptionPane.showMessageDialog(null, successMessage);
                    }
                    else {
                        var errorMessage = String.format("Error al actualizar el vuelo con destino a '%s'.", flightDestination);
                        JOptionPane.showMessageDialog(null, errorMessage);
                    }
                }
                // Update passenger
                case "9" -> {
                    System.out.println("Updating passenger");

                    var basePrompt = "Actualizando pasajero ...";
                    var promptMessage = Prompts.getPromptMessage(basePrompt,
                            "Ingresa el id del usuario a actualizar: ");
                    var id = JOptionPane.showInputDialog(promptMessage);
                    if (id.isBlank()) break;

                    var foundPassenger = passengersController.findById(Integer.parseInt(id));

                    if (foundPassenger.isEmpty()) {
                        var notFoundMessage = String.format("Pasajero con id '%s' no encontrado", id);
                        JOptionPane.showMessageDialog(null, notFoundMessage);
                        break;
                    }

                    var promptToUpdateName = Prompts.getPromptMessage(
                            foundPassenger.get(), "Ingresa el NUEVO nombre del pasajero (Presiona ENTER para omitir cambio): ");
                    var newName = JOptionPane.showInputDialog(promptToUpdateName);

                    var promptToUpdateLastname = Prompts.getPromptMessage(
                            foundPassenger.get(), "Ingresa el NUEVO apellido del pasajero (Presiona ENTER para omitir cambio): ");
                    var newLastname = JOptionPane.showInputDialog(promptToUpdateLastname);

                    var foundPassengerVal = foundPassenger.get();

                    foundPassengerVal.setName(newName.isBlank() ? foundPassengerVal.getName() : newName);
                    foundPassengerVal.setLastname(newLastname.isBlank() ? foundPassengerVal.getLastname() : newLastname);

                    System.out.printf("Updating passenger with id: %d", foundPassengerVal.getId());

                    var couldUpdatePassenger = passengersController.update(foundPassengerVal.getId(), foundPassengerVal);

                    if (couldUpdatePassenger)
                        JOptionPane.showMessageDialog(null, "Pasajero actualizado con éxito!");
                    else
                        JOptionPane.showMessageDialog(null, "Error al actualizar el pasajero");

                }
                // Update booking's seat
                case "10" -> {
                    System.out.println("Updating booking's seat");

                    var basePrompt = "Actualizando asiento de una reserva ...";
                    var promptMessage = Prompts.getPromptMessage(basePrompt,
                            "Ingresa el id de la reserva a actualizar: ");
                    var bookingId = JOptionPane.showInputDialog(promptMessage);
                    if (bookingId.isBlank()) break;

                    int intBookingId = Integer.parseInt(bookingId);

                    var foundBooking = bookingsController.findById(intBookingId);

                    if (foundBooking.isEmpty()) {
                        var notFoundMessage = String.format("Reservac con id '%s' no encontrada", bookingId);
                        JOptionPane.showMessageDialog(null, notFoundMessage);
                        break;
                    }

                    var promptToUpdateSeat = Prompts.getPromptMessage(
                            foundBooking.get(), "Ingresa el NUEVO asiento para la reserva (Presiona ENTER para cancelar cambio): ");
                    var newSeat = JOptionPane.showInputDialog(promptToUpdateSeat);

                    var foundBookingVal = foundBooking.get();

                    // Check if update is omitted
                    var isUpdateNotNeeded = newSeat.equals(foundBookingVal.getSeat()) || newSeat.isBlank();
                    if (isUpdateNotNeeded) {
                        JOptionPane.showMessageDialog(null, "Actualización de asiento cancelada!");
                        break;
                    }

                    // Check seat availability
                    boolean isSeatAvailable = bookingsController.checkSeatAvailability(foundBookingVal.getFlightsId(), newSeat);
                    if (!isSeatAvailable) {
                        var seatNotAvailableMessage = String.format("El asiento '%s' no está disponible", newSeat);
                        JOptionPane.showMessageDialog(null, seatNotAvailableMessage);
                        break;
                    }
                    foundBookingVal.setSeat(newSeat);

                    System.out.printf("Updating passenger with id: %d", foundBookingVal.getId());

                    var couldUpdateBooking = bookingsController.updateBookingSeat(intBookingId, newSeat);

                    if (couldUpdateBooking)
                        JOptionPane.showMessageDialog(null, "Asiento actualizado con éxito!");
                    else
                        JOptionPane.showMessageDialog(null, "Error al actualizar el asiento.");

                }
                // Delete plane
                case "11" -> {
                    System.out.println("Deleting plane...");

                    var promptMessage = Prompts.getPromptMessage("Eliminando un avión...",
                            "Ingresa el id del avión a eliminar: ");
                    var planeId = JOptionPane.showInputDialog(promptMessage);
                    if (planeId.isBlank()) break;

                    var couldDeletePlane = planesController.delete(Integer.parseInt(planeId));

                    if (couldDeletePlane)
                        JOptionPane.showMessageDialog(null, "Avión eliminado con éxito!");
                    else
                        JOptionPane.showMessageDialog(null, "Error al eliminar el avión");

                }
                // Delete flight
                case "12" -> {
                    System.out.println("Deleting flight...");

                    var promptMessage = Prompts.getPromptMessage("Eliminando un vuelo...",
                            "Ingresa el id del vuelo a eliminar: ");
                    var flightId = JOptionPane.showInputDialog(promptMessage);
                    if (flightId.isBlank()) break;

                    var couldDeleteFlight = flightsController.delete(Integer.parseInt(flightId));

                    if (couldDeleteFlight)
                        JOptionPane.showMessageDialog(null, "Vuelo eliminado con éxito!");
                    else
                        JOptionPane.showMessageDialog(null, "Error al eliminar el vuelo");

                }
                // Delete passenger
                case "13" -> {
                    System.out.println("Deleting passenger...");

                    var promptMessage = Prompts.getPromptMessage("Eliminando un pasajero...",
                            "Ingresa el id del pasajero a eliminar: ");
                    var passengerId = JOptionPane.showInputDialog(promptMessage);
                    if (passengerId.isBlank()) break;

                    var couldDeletePassenger = passengersController.delete(Integer.parseInt(passengerId));

                    if (couldDeletePassenger)
                        JOptionPane.showMessageDialog(null, "Pasajero eliminado con éxito!");
                    else
                        JOptionPane.showMessageDialog(null, "Error al eliminar el pasajero");
                }
                // Delete booking
                case "14" -> {
                    System.out.println("Deleting booking...");

                    var promptMessage = Prompts.getPromptMessage("Eliminando un reserva...",
                            "Ingresa el id de la reserva a eliminar: ");
                    var bookingId = JOptionPane.showInputDialog(promptMessage);
                    if (bookingId.isBlank()) break;

                    var couldDeleteBooking = bookingsController.delete(Integer.parseInt(bookingId));

                    if (couldDeleteBooking)
                        JOptionPane.showMessageDialog(null, "Reserva eliminada con éxito!");
                    else
                        JOptionPane.showMessageDialog(null, "Error al eliminar la reserva");

                }
                default -> JOptionPane.showMessageDialog(null, "Opción inválida. Inténtalo de nuevo.");
            }
        }
    }
    public static LocalDate askForLocalDate(String prompt) {
        while (true) {
            var dateStringInput = JOptionPane.showInputDialog(prompt);
            var parsingResult = DateStringParser.parse(dateStringInput);

            if (parsingResult.isPresent()) return parsingResult.get();

            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Inténtalo de nuevo");
        }
    }

    public static Time askForTime(String prompt) {
        Time parsingResult;
        while (true) {
            try {
                var timeStringInput = JOptionPane.showInputDialog(prompt);
                parsingResult = Time.valueOf(timeStringInput);
                break;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Formato de hora inválido. Inténtalo de nuevo");
            }
        }
        return parsingResult;
    }
}