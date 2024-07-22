package employee_registry;

import utils.InputGetter;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EmployeeRegistry {
    private final ArrayList<Employee> employees;
    private int idToAssignToEmployees = 1;

    public EmployeeRegistry() {
        employees = new ArrayList<>(
                Arrays.asList(
                        new TemporalEmployee(idToAssignToEmployees++, "Nicolas", (byte) 25, 8_000_000.00f,
                                LocalDate.of(2024, Month.FEBRUARY, 19),
                                LocalDate.of(2024, Month.JULY, 21)
                        ),
                        new PermanentEmployee(idToAssignToEmployees++, "Yamile", (byte) 23, 8_000_000.00f,
                                LocalDate.of(2024, Month.FEBRUARY, 19)
                        ),
                        new PermanentEmployee(idToAssignToEmployees++, "Antony", (byte) 23, 8_000_000.00f,
                                LocalDate.of(2024, Month.JUNE, 1)
                        )
                )
        );
    }


    public static void showMenu(EmployeeRegistry employeeRegistry) {
        try (var scanner = new Scanner(System.in)) {
            var inputGetter = new InputGetter(scanner);
            System.out.println("***********************************************************");
            System.out.println("Bienvenido a tu sistema de registro y control de empleados");
            System.out.println("***********************************************************");
            System.out.println();


            boolean isMenuOpened = true;
            while (isMenuOpened) {
                var optionsMessage = """
                        0. Salir.
                        1. Añadir empleado.
                        2. Listar empleados.
                        3. Eliminar empleado.
                        """;
                System.out.println(optionsMessage);
                int option = inputGetter.getInput("Ingresa la opción que deseas hacer: ", Scanner::nextInt);


                switch (option) {
                    case 0 -> isMenuOpened = false;
                    case 1 -> {
                        System.out.println("Añadiendo empleado");
                    }
                    case 2 -> {
                        System.out.println("Listando empleados");
                    }
                    case 3 -> {
                        System.out.println("Eliminando empleado");
                    }
                    default -> {
                        System.out.println("Opción seleccionada inválida. Inténtalo de nuevo.");
                        System.out.println();
                    }
                }
            }
        }
    }

    public List<Employee> getEmployees() {
        return employees.stream().toList();
    }
}
