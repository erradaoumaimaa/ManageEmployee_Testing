package DAO.interfaces;

import java.util.List;
import java.util.Optional;

import models.Employee;

public interface EmployeeDAO {
    Optional<Employee> findById(int id);
    void addEmployee(Employee employee);
    void removeEmployee(int id);
    void updateEmployee(Employee employee);
    List<Employee> getAllEmployees();
    List<Employee> findAllEmployees(String search, String position, String department);

}
