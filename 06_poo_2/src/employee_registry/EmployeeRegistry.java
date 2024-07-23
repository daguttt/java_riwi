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

//    public static void showMenu(EmployeeRegistry employeeRegistry) {
////        var employees = employeeRegistry.getEmployees();
////
////        for (var employee : employees) {
////            System.out.println(employee.introduce());
////            System.out.println();
////        }
//        try (var scanner = new Scanner(System.in)) {
//            var inputGetter = new InputGetter(scanner);
//            System.out.println("***********************************************************");
//            System.out.println("Bienvenido a tu sistema de registro y control de empleados");
//            System.out.println("***********************************************************");
//            System.out.println();
//
//
//            boolean isMenuOpened = true;
//            while (isMenuOpened) {
//                var optionsMessage = """
//                        0. Salir.
//                        1. Añadir empleado.
//                        2. Listar empleados.
//                        3. Eliminar empleado.
//                        """;
//                System.out.println(optionsMessage);
//                int option = inputGetter.get("Ingresa la opción que deseas hacer: ", Scanner::nextInt);
//
//
//                switch (option) {
//                    case 0 -> isMenuOpened = false;
//                    case 1 -> {
////                        var promptContractEndingDate =
////                                "Fecha de fin del contrato (Escribe 'saltar' si tiene contrato a término indefinido): ";
////
////                        scanner.nextLine();
////                        var contractEndingDateInput = inputGetter.get(promptContractEndingDate, Scanner::nextLine)
////                                .trim().toLowerCase();
////
////                        var contractEndingDate = LocalDate.parse(contractEndingDateInput);
////
////                        System.out.printf("%1$tB %1$td, %1$tY%n", contractEndingDate);
//                        System.out.println("Añadiendo empleado");
//                        System.out.println();
//                        var nameInput = inputGetter.get("Nombre: ", Scanner::nextLine);
//                        var ageInput = inputGetter.get("Edad: ", Scanner::nextByte);
//                        var salaryInput = inputGetter.get("Salario: ", Scanner::nextFloat);
//
//                        // Contract starting date
//                        scanner.nextLine();
//                        var contractStartingDate = askForLocalDate("Fecha de inicio del contrato (Formato: YYYY-MM-DD): ", inputGetter, scanner);
//
//                        // Contract ending date
//                        scanner.nextLine();
//                        var promptContractEndingDate =
//                                String.format("Fecha de fin del contrato (Formato: YYYY-MM-DD)%n  OTA:Escribe 'saltar' si tiene contrato a término indefinido): ");
//                        var contractEndingDateInput = inputGetter.get(promptContractEndingDate, Scanner::nextLine)
//                                .trim().toLowerCase();
//
//                        var SUCCESS_MESSAGE = "¡Empleado añadido con éxito!";
//
//                        if (contractEndingDateInput.equals("saltar")) {
//                            employeeRegistry.addEmployee(nameInput, ageInput, salaryInput, contractStartingDate);
//                            System.out.println(SUCCESS_MESSAGE);
//                            break;
//                        }
//
//                        var parsingResultContractEndingDate = DateStringParser.parse(contractEndingDateInput);
//
//                        if (parsingResultContractEndingDate.isEmpty()) {
//                            System.out.println("Formato de fecha inválido.");
//                            break;
//                        }
//
//                        var contractEndingDate = parsingResultContractEndingDate.get();
//
//                        employeeRegistry.addEmployee(nameInput, ageInput, salaryInput, contractStartingDate, contractEndingDate);
//                        System.out.println(SUCCESS_MESSAGE);
//
//                    }
//                    case 2 -> {
//                        System.out.println("Listando empleados");
//                        var employees = employeeRegistry.getEmployees();
//                        var tableHeader = String.format("| %-3s | %-15s | %-2s | %-10s | %-15s | %-15s |%n", "ID", "Nombre", "Edad", "Salario", "Fecha inicio contrato", "Fecha fin contrato");
//                        System.out.println(tableHeader);
//                        System.out.println("-".repeat(tableHeader.length()));
//
//                        for (int i = 0; i < employees.size(); i++) {
//                            var employee = employees.get(i);
//
//                            switch (employee) {
//                                case TemporalEmployee e -> {
//                                    System.out.printf("");
//
//                                }
//                                case PermanentEmployee e -> {
//
//                                }
//                                default -> {
//                                    throw new ClassNotFoundException("Derived Employee subclass not found ");
//                                }
//
//                            }
//
//                            System.out.printf("| %-3d | ", i + 1, employee.getName(), employee.getAge(), employee.getSalary(), employee.getContractStartingDate());
//                        }
//
//                        // Table Body
//                        for (var product : products) {
//                            var productName = Character.toUpperCase(product.getName().charAt(0)) + product.getName().substring(1).toLowerCase();
//                            System.out.printf("| %-15s | %-15.2f |%n", productName, product.getPrice());
//                        }
//                    }
//                    case 3 -> {
//                        System.out.println("Eliminando empleado");
//                    }
//                    default -> {
//                        System.out.println("Opción seleccionada inválida. Inténtalo de nuevo.");
//                        System.out.println();
//                    }
//                }
//            }
//        }
//    }

    public static LocalDate askForLocalDate(String prompt, InputGetter inputGetter, Scanner scanner) {
        while (true) {
            scanner.nextLine();
            var dateInput = inputGetter.get(prompt, Scanner::nextLine)
                    .trim().toLowerCase();

            var parsingResult = DateStringParser.parse(dateInput);

            if (parsingResult.isPresent()) return parsingResult.get();

            System.out.println("Formato de fecha inválido. Intántalo de nuevo.");
        }

    }

    public void addEmployee(String name, byte age, float salary, LocalDate contractStartingDate) {

    }

    public void addEmployee(String name, byte age, float salary, LocalDate contractStartingDate, LocalDate contractEndingDate) {
    }

    public List<Employee> getEmployees() {
        return employees.stream().toList();
    }
}
