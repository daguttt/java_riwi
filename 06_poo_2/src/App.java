import employee_registry.EmployeeRegistry;
import inventory.Inventory;
import utils.InputGetter;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        try (var scanner = new Scanner(System.in)) {
            var inputGetter = new InputGetter(scanner);
            boolean isMenuOpened = true;
            do {
                System.out.println();
                System.out.println();
                System.out.println("**************** EXERCISE MENU ****************");
                System.out.println();
                System.out.println();
                var menuOptionsMessage = """
                        0. Salir
                        1. Abrir inventario de productos.
                        2. Abrir gestión de registro de empleados.
                        """;
                System.out.println(menuOptionsMessage);
                var option = inputGetter.get("Ingresa el número del sistema que quieres abrir: ", Scanner::nextInt);
                switch (option) {
                    case 0 -> isMenuOpened = false;
                    case 1 -> {
                        // Inventory Management System
                        var inventory = new Inventory();
                        Inventory.showMenu(inventory, scanner);
                    }
                    case 2 -> {
                        // Employee registry
                        var employeeRegistry = new EmployeeRegistry();
                        EmployeeRegistry.showMenu(employeeRegistry, scanner);
                    }
                    default -> {
                        System.out.println("Opción seleccionada inválida. Inténtalo de nuevo.");
                        System.out.println();
                    }
                }

            } while (isMenuOpened);
        }

    }
}
