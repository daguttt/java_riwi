package employee_registry;

import java.time.LocalDate;

public class PermanentEmployee extends Employee {
    public PermanentEmployee(int id, String name, byte age, float salary, LocalDate startingContractDate) {
        super(id, name, age, salary, startingContractDate);
    }

    @Override
    public String introduce() {
        return String.format("Mi nombre es %s. Trabajo aquí desde %2$tB %2$te, %2$tY", this.getName(), this.getContractStartingDate());
    }
}
