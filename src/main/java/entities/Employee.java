package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Employee")
public class Employee extends User {
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vacation> vacations = new ArrayList<>();

    public Employee() {
        super();
    }

    public Employee(String name, String SSN, LocalDate birthDate, String password, LocalDate hireDate,
                    int numberOfChildren, double salary, String email, String phone,
                    String department, String position, int vacationTaken,int vacationDays) {
        super(name, SSN, birthDate, password, hireDate, numberOfChildren, salary, email, phone,
                department, position, vacationTaken, vacationDays);
    }

    public List<Vacation> getVacations() {
        return vacations;
    }

    public void setVacations(List<Vacation> vacations) {
        this.vacations = vacations;
    }

}
