package service.implementations;

import entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import dao.interfaces.EmployeeDAO;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeDAO employeeDAO; // Crée un mock pour EmployeeDAO

    @Test
    public void saveEmployee() {
        Employee employee = new Employee();
        employeeService.saveEmployee(employee);

        verify(employeeDAO, times(1)).save(employee);
    }

    @Test
    public void updateEmployee() {
        Employee employee = new Employee();
        employeeService.updateEmployee(employee);

        verify(employeeDAO, times(1)).update(employee);
    }

    @Test
    public void deleteEmployee() {
        Employee employee = new Employee();
        employeeService.deleteEmployee(employee);

        verify(employeeDAO, times(1)).delete(employee);
    }


}
