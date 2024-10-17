package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("admin")
public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(String name, String SSN, LocalDate birthDate, String password, LocalDate hireDate,
                 int numberOfChildren, double salary, String email, String phone,
                 String department, String position,int vacationTaken,int vacationDays) {
        super(name, SSN, birthDate, password, hireDate, numberOfChildren, salary, email,
                phone, department, position,vacationTaken,vacationDays);
    }


}
