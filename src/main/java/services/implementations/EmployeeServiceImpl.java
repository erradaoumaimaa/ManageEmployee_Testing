package services.implementations;

import DAO.interfaces.EmployeeDAO;
import models.Employee;
import services.interfaces.EmployeeService;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDAO.addEmployee(employee);
    }

    @Override
    public void removeEmployee(int id) {
        Optional<Employee> existingEmployee = employeeDAO.findById(id);
        if (existingEmployee.isPresent()) {
            employeeDAO.removeEmployee(id);
        } else {
            System.out.println("Employee not found for id: " + id);
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeDAO.findById(employee.getId());
        if (existingEmployee.isPresent()) {
            employeeDAO.updateEmployee(employee);
        } else {
            System.out.println("Employee not found for id: " + employee.getId());
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public Optional<Employee> findById(int id) {
        return employeeDAO.findById(id);
    }

    @Override
    public List<Employee> filterEmployees(String search, String position, String department) {
        return employeeDAO.findAllEmployees(search, position, department);
    }

    @Override
    public List<Employee> searchEmployees(String search, String position, String department) {
        return employeeDAO.findAllEmployees(search, position, department);
    }
}
