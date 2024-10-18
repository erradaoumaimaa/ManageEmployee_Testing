package service.implementations;

import dao.interfaces.EmployeeDAO;
import entities.Employee;
import service.interfaces.EmployeeService;

import java.util.*;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeDAO.save(employee);
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeDAO.findById(id);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeDAO.findAll();
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDAO.update(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeDAO.delete(employee);
    }

    public double calculateFamilyAllowance(int numberOfChildren, double salary) {
        double allowance = 0;

        if (salary < 6000) {
            allowance = (Math.min(numberOfChildren, 3) * 300) + (Math.max(0, numberOfChildren - 3) * 150);
        } else if (salary == 6000) {
            allowance = (Math.min(numberOfChildren, 3) * 300) + (Math.max(0, numberOfChildren - 3) * 150);
        } else if (salary > 8000) {
            allowance = (Math.min(numberOfChildren, 3) * 200) + (Math.max(0, numberOfChildren - 3) * 110);
        } else {
            allowance = (Math.min(numberOfChildren, 3) * 200) + (Math.max(0, numberOfChildren - 3) * 100);
        }

        return allowance;
    }

    public void enterFamilyAllowanceForEmployee(Long employeeId, int numberOfChildren) {
        Optional<Employee> employeeOptional = findEmployeeById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            double familyAllowance = calculateFamilyAllowance(numberOfChildren, employee.getSalary());
            System.out.println("Allocations familiales pour " + employee.getName() + ": " + familyAllowance + " DH");
        } else {
            System.out.println("Employé non trouvé");
        }
    }
}
