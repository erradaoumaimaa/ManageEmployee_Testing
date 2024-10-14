package models;

import javax.persistence.*;
import java.time.LocalDate;

    @Entity
    @Table(name = "employees")
    @Inheritance(strategy = InheritanceType.JOINED)
    @DiscriminatorColumn(name = "role")
    public class Employee {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        protected int id;

        @Column(name = "name", nullable = false)
        protected String name;

        @Column(name = "SSN", nullable = false)
        protected String SSN; // Social Security Number

        @Column(name = "birth_date")
        protected LocalDate birthDate;

        @Column(name = "password")
        protected String password;

        @Column(name = "hire_date")
        protected LocalDate hireDate;

        @Column(name = "number_of_children")
        protected int numberOfChildren;

        @Column(name = "salary")
        protected double salary;

        @Column(name = "email", unique = true, nullable = false)
        protected String email;

        @Column(name = "phone")
        protected String phone;

        @Column(name = "department")
        protected String department;

        @Column(name = "position")
        protected String position;

    public Employee() {}

    public Employee(String name, String SSN, LocalDate birthDate, String password, LocalDate hireDate,
                    int numberOfChildren, double salary, String email, String phone, String department, String position) {
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
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
