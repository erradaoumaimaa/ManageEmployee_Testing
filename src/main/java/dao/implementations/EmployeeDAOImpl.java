package dao.implementations;

import dao.interfaces.EmployeeDAO;
import entities.Employee;

public class EmployeeDAOImpl extends GenericDAOImpl<Employee> implements EmployeeDAO {

    public EmployeeDAOImpl() {
        super(Employee.class);
    }

}