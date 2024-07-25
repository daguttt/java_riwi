package employee_registry;

import utils.InputGetter;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EmployeeRegistry {
    private ArrayList<Employee> employees;
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

    public static void showMenu(EmployeeRegistry employeeRegistry, Scanner scanner) {
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
            int option = inputGetter.get("Ingresa la opción que deseas hacer: ", Scanner::nextInt);


            switch (option) {
                case 0 -> {
                    isMenuOpened = false;
                }
                case 1 -> {
                    System.out.println();
                    System.out.println("Añadiendo empleado");
                    System.out.println();
                    scanner.nextLine();
                    var nameInput = inputGetter.get("Nombre: ", Scanner::nextLine);
                    var ageInput = inputGetter.get("Edad: ", Scanner::nextByte);
                    var salaryInput = inputGetter.get("Salario: ", Scanner::nextFloat);

                    // Contract starting date
                    var contractStartingDate = askForLocalDate("Fecha de inicio del contrato (Formato: YYYY-MM-DD): ", inputGetter, scanner);

                    // Contract ending date
                    scanner.nextLine();
                    var promptContractEndingDate =
                            String.format("Fecha de fin del contrato (Formato: YYYY-MM-DD)%nNOTA: Escribe 'saltar' si tiene contrato a término indefinido): ");
                    var contractEndingDateInput = inputGetter.get(promptContractEndingDate, Scanner::nextLine)
                            .trim().toLowerCase();

                    var SUCCESS_MESSAGE = "¡Empleado añadido con éxito!";

                    if (contractEndingDateInput.equals("saltar")) {
                        employeeRegistry.addEmployee(nameInput, ageInput, salaryInput, contractStartingDate);
                        System.out.println(SUCCESS_MESSAGE);
                        break;
                    }

                    var parsingResultContractEndingDate = DateStringParser.parse(contractEndingDateInput);

                    if (parsingResultContractEndingDate.isEmpty()) {
                        System.out.println("Formato de fecha inválido.");
                        break;
                    }

                    var contractEndingDate = parsingResultContractEndingDate.get();

                    employeeRegistry.addEmployee(nameInput, ageInput, salaryInput, contractStartingDate, contractEndingDate);
                    System.out.println(SUCCESS_MESSAGE);

                }
                case 2 -> {
                    System.out.println("Listando empleados");
                    var employees = employeeRegistry.getEmployees();
                    listEmployees(employees);
                    System.out.println();
                    System.out.println();
                }
                case 3 -> {
                    System.out.println("Eliminando empleado");
                    var employees = employeeRegistry.getEmployees();
                    listEmployees(employees);
                    var employeeIdToRemove = inputGetter.get("Ingresa el ID del empleado que deseas eliminar: ", Scanner::nextInt);
                    var couldRemoveEmployee = employeeRegistry.removeEmployeeById(employeeIdToRemove);

                    if (couldRemoveEmployee)
                        System.out.printf("Empleado con ID '%d' eliminado con éxito%n%n", employeeIdToRemove);
                    else System.out.printf("No se encontró un empleado con ID '%d'%n%n", employeeIdToRemove);
                }
                default -> {
                    System.out.println("Opción seleccionada inválida. Inténtalo de nuevo.");
                    System.out.println();
                }
            }
        }
    }

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

    public static void listEmployees(List<Employee> employees) {
        var tableHeader = String.format("| %-3s | %-15s | %-4s | %-20s | %-15s | %-15s |", "ID", "Nombre", "Edad", "Salario", "Fecha inicio contrato", "Fecha fin contrato");
        System.out.println(tableHeader);
        System.out.println("-".repeat(tableHeader.length()));

        // Table Body
        for (Employee employee : employees) {
            switch (employee) {
                case TemporalEmployee e -> {
                    var tableRow = String.format("| %-3d | %-15s | %-4d | %,-20.2f | %5$Td/%5$Tm/%5$-14TY  | %6$Td/%6$Tm/%6$-12TY |",
                            e.getId(), e.getName(), e.getAge(), e.getSalary(), e.getContractStartingDate(), e.getContractEndingDate());
                    System.out.println(tableRow);
                }
                case PermanentEmployee e -> {
                    var tableRow = String.format("| %-3d | %-15s | %-4d | %,-20.2f | %5$Td/%5$Tm/%5$-14TY  | %6$-18s |",
                            e.getId(), e.getName(), e.getAge(), e.getSalary(), e.getContractStartingDate(), "n/a");
                    System.out.println(tableRow);

                }
                default -> {
                    try {
                        throw new ClassNotFoundException("Derived Employee subclass not found ");
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

            }

        }
    }


    public void addEmployee(String name, byte age, float salary, LocalDate contractStartingDate) {
        var newEmployee = new PermanentEmployee(idToAssignToEmployees++, name, age, salary, contractStartingDate);
        this.employees.add(newEmployee);
    }

    public void addEmployee(String name, byte age, float salary, LocalDate contractStartingDate, LocalDate contractEndingDate) {
        var newEmployee = new TemporalEmployee(idToAssignToEmployees++, name, age, salary, contractStartingDate, contractEndingDate);
        this.employees.add(newEmployee);
    }

    public boolean removeEmployeeById(int employeeIdToRemove) {
        var employeeToRemoveSearch = this.employees.stream().filter(e -> e.getId() == employeeIdToRemove).findFirst();

        return employeeToRemoveSearch.map(employee -> this.employees.remove(employee)).orElse(false);
    }

    public List<Employee> getEmployees() {
        return employees.stream().toList();
    }
}
