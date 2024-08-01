package org.example;

import javax.swing.*;

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
        var database = new persistence.Database(host, port, dbName, dbUser, dbPassword);

        boolean isMenuOpened = true;
        while (isMenuOpened) {
            var menuOptionsMessage = """
                    ********************* Menu *********************
                    0. Salir.
                    1. Crear avión.
                    2. Crear vuelo.
                    3. Crear pasajero.
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
                    System.out.println("Hello World 1!");
                }
                case "2" -> {
                    System.out.println("Hello World 2!");
                }
                case "3" -> {
                    System.out.println("Hello World 3!");
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
}