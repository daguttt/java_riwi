package employee_registry;

import java.time.LocalDate;

public abstract class Employee extends Person {
    private int id;
    private float salary;
    private LocalDate startingContractDate;

    public Employee(int id, String name, byte age, float salary, LocalDate startingContractDate) {
        super(name, age);
        this.id = id;
        this.salary = salary;
        this.startingContractDate = startingContractDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public LocalDate getStartingContractDate() {
        return startingContractDate;
    }

    public void setStartingContractDate(LocalDate startingContractDate) {
        this.startingContractDate = startingContractDate;
    }
}
