package models;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@DiscriminatorValue("recruiter")
public class Recruiter extends Employee {

    public Recruiter() {
        super();
    }

    public Recruiter(String name, String SSN, LocalDate birthDate, String password, LocalDate hireDate,
                     int numberOfChildren, double salary, String email, String phone, String department, String position) {
        super(name, SSN, birthDate, password, hireDate, numberOfChildren, salary, email, phone, department, position);
    }

}
