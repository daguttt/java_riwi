package employee_registry;

import java.time.LocalDate;

public class TemporalEmployee extends Employee {
    private LocalDate contractEndingDate;

    public TemporalEmployee(int id, String name, byte age, float salary, LocalDate startingContractDate, LocalDate contractEndingDate) {
        super(id, name, age, salary, startingContractDate);
        this.contractEndingDate = contractEndingDate;
    }

    @Override
    public String introduce() {
        return String.format("Mi nombre es %1$s. Estoy trabajando aquí desde %2$tB %2$te, %2$tY hasta %3$tB %3$te, %3$tY", this.getName(), this.getContractStartingDate(), this.getContractEndingDate());
    }

    public LocalDate getContractEndingDate() {
        return contractEndingDate;
    }

    public void setContractEndingDate(LocalDate contractEndingDate) {
        this.contractEndingDate = contractEndingDate;
    }

    public static class SubEmployee {
        public static String WTFisThis = "WTF is this?";
    }
}
