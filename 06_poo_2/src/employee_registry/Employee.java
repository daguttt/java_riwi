package employee_registry;

import java.time.LocalDate;

public abstract class Employee extends Person {
    private int id;
    private float salary;
    private LocalDate contractStartingDate;

    public Employee(int id, String name, byte age, float salary, LocalDate contractStartingDate) {
        super(name, age);
        this.id = id;
        this.salary = salary;
        this.contractStartingDate = contractStartingDate;
    }

    public String introduce() {
        return String.format("Hola soy %s.", this.getName());
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

    public LocalDate getContractStartingDate() {
        return contractStartingDate;
    }

    public void setContractStartingDate(LocalDate contractStartingDate) {
        this.contractStartingDate = contractStartingDate;
    }
}

