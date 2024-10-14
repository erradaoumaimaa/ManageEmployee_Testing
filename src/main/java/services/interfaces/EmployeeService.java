package services.interfaces;

import models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void addEmployee(Employee employee);

    void removeEmployee(int id);

    void updateEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Optional<Employee> findById(int id);

    List<Employee> filterEmployees(String search, String position, String department);
    List<Employee> searchEmployees(String search, String position, String department);

}
