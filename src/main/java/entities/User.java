package entities;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Table(name = "users")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false, unique = true)
    protected String SSN;

    @Column(nullable = false)
    protected LocalDate birthDate;

    @Column(nullable = false)
    protected String password;

    @Column
    protected LocalDate hireDate;

    @Column
    protected int numberOfChildren;

    @Column
    protected double salary;

    @Column(nullable = false, unique = true)
    protected String email;

    @Column
    protected String phone;

    @Column
    protected String department;

    @Column
    protected String position;

    @Column(name = "vacation_taken")
    private int vacationTaken;
    @Column(name = "vacationDays")
    private int vacationDays;
    public User() {
    }

    public User(String name, String SSN, LocalDate birthDate, String password, LocalDate hireDate,
                int numberOfChildren, double salary, String email, String phone, String department, String position,
                int vacationTaken,int vacationDays) {
            this.name = name;
            this.SSN = SSN;
            this.birthDate = birthDate;
            this.password = password;
            this.hireDate = hireDate;
            this.numberOfChildren = numberOfChildren;
            this.salary = salary;
            this.email = email;
            this.phone = phone;
            this.department = department;
            this.position = position;
            this.vacationTaken =  0;
            this.vacationDays = vacationDays;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getVacationTaken() {
        return vacationTaken;
    }
    public void setVacationTaken(int vacationTaken) {
        this.vacationTaken = vacationTaken;
    }
    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

}


