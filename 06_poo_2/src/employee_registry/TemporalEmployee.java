package employee_registry;

import java.time.LocalDate;

public class TemporalEmployee extends Employee {
    private LocalDate endingContractDate;

    public TemporalEmployee(int id, String name, byte age, float salary, LocalDate startingContractDate, LocalDate endingContractDate) {
        super(id, name, age, salary, startingContractDate);
        this.endingContractDate = endingContractDate;
    }

    public LocalDate getEndingContractDate() {
        return endingContractDate;
    }

    public void setEndingContractDate(LocalDate endingContractDate) {
        this.endingContractDate = endingContractDate;
    }
}
