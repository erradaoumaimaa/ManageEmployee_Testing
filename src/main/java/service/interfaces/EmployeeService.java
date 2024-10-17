package service.interfaces;

import entities.Employee;

import java.util.*;

public interface EmployeeService {
    void saveEmployee(Employee employee);
    Optional<Employee> findEmployeeById(Long id);
    List<Employee> findAllEmployees();
    void updateEmployee(Employee employee);
    void deleteEmployee(Employee employee);
}
