package models;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@DiscriminatorValue("admin")
public class Admin extends Employee {

    public Admin() {
        super();
    }

    public Admin(String name, String SSN, LocalDate birthDate, String password, LocalDate hireDate,
                 int numberOfChildren, double salary, String email, String phone, String department, String position) {
        super(name, SSN, birthDate, password, hireDate, numberOfChildren, salary, email, phone, department, position);
    }


}
