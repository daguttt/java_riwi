package org.example;

import org.example.controllers.BookingsController;
import org.example.controllers.FlightsController;
import org.example.controllers.PassengersController;
import org.example.controllers.PlanesController;
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
import java.sql.Timestamp;
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
                    5. Buscar vuelos por destino.
                    6. Buscar pasajeros por nombre.
                    7. Mostrar todas las reservas de un vuelo.
                    8. Cambiar fecha de un vuelo.
                    9. Actualizar pasajero.
                    10. Actualizar asiento de una reservación.
                    11. Eliminar un avión.
                    12. Eliminar un vuelo.
                    13. Eliminar un pasajero.
                    14. Eliminar una reservación.

                    Ingresa la opción que deseas hacer:
                    """;
            var option = JOptionPane.showInputDialog(menuOptionsMessage);
            if (option == null) return;

            switch (option) {
                case "0" -> isMenuOpened = false;
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

                    var outputMessage = String.format("Pasajero creado satisfactoriamente!%n%s", createdPassenger);
                    JOptionPane.showMessageDialog(null, outputMessage);
                }
                case "3" -> {
                    var basePrompt = "Creando un vuelo......";

                    var planesIdPrompt = Prompts.getPromptMessage(basePrompt, "Ingresa el id del avión asociado al vuelo:");
                    var planesId = JOptionPane.showInputDialog(planesIdPrompt);

                    var plane = planesController.findById(Integer.parseInt(planesId));

                    if (plane == null) {
                        var errorMessage = String.format("Avión con id '%s' no encontrado", planesId);
                        JOptionPane.showMessageDialog(null, errorMessage);
                        break;
                    }

                    var planeFoundMessage = String.format("Avión encontrado!%n%s", plane);
                    JOptionPane.showMessageDialog(null, planeFoundMessage);

                    var destinationPrompt = Prompts.getPromptMessage(basePrompt, "Ingresa el destino del vuelo:");
                    var destination = JOptionPane.showInputDialog(destinationPrompt);

                    var startingDatePrompt = Prompts.getPromptMessage(basePrompt,
                            "Ingresa la fecha + hora de inicio del vuelo (Formato YYYY-MM-DDTHH:MM:SS) :");
                    var startingDateInLocalDateTime = askForLocalDateTime(startingDatePrompt);
                    var startingDate = Timestamp.valueOf(startingDateInLocalDateTime);

                    var endingDatePrompt = Prompts.getPromptMessage(basePrompt,
                            "Ingresa la fecha + hora de fin del vuelo (Formato YYYY-MM-DDTHH:MM:SS) :");
                    var endingDateInLocalDateTime = askForLocalDateTime(endingDatePrompt);
                    var endingDate = Timestamp.valueOf(endingDateInLocalDateTime);

                    var baseFlight = new Flight(destination, startingDate, endingDate, Integer.parseInt(planesId));
                    var createdFlight = flightsController.create(baseFlight);

                    var outputMessage = String.format("Vuelo creado satisfactoriamente!%n%s", createdFlight);
                    JOptionPane.showMessageDialog(null, outputMessage);
                }
                case "4" -> {
                    System.out.println("Hello World 4!");
                }
                case "5" -> {
                    System.out.println("Hello World 5!");
                }
                case "6" -> {
                    System.out.println("Hello World 6!");
                }
                case "7" -> {
                    System.out.println("Hello World 7!");
                }
                case "8" -> {
                    System.out.println("Hello World 8!");
                }
                case "9" -> {
                    System.out.println("Hello World 9!");
                }
                case "10" -> {
                    System.out.println("Hello World 10!");
                }
                case "11" -> {
                    System.out.println("Hello World 11!");
                }
                case "12" -> {
                    System.out.println("Hello World 12!");
                }
                case "13" -> {
                    System.out.println("Hello World 13!");
                }
                case "14" -> {
                    System.out.println("Hello World 14!");
                }
                default -> JOptionPane.showMessageDialog(null, "Opción inválida. Inténtalo de nuevo.");
            }
        }
    }
    public static LocalDateTime askForLocalDateTime(String prompt) {
        while (true) {
            var dateStringInput = JOptionPane.showInputDialog(prompt);
            var parsingResult = DateStringParser.parse(dateStringInput);

            if (parsingResult.isPresent()) return parsingResult.get();

            JOptionPane.showMessageDialog(null, "Invalid date format. Try it again.");
        }
    }

}