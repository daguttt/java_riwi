package employee_registry;

import java.time.LocalDate;

public class PermanentEmployee extends Employee {
    public PermanentEmployee(int id, String name, byte age, float salary, LocalDate startingContractDate) {
        super(id, name, age, salary, startingContractDate);
    }
}
