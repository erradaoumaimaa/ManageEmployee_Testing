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
    private EmployeeDAO employeeDAO;

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

    @Test
    public void calculateFamilyAllowance_Under6000() {
        double allowance = employeeService.calculateFamilyAllowance(5, 5000);
        assertEquals(1200.0, allowance, 0.01); // 3 * 300 + 2 * 150
    }

    @Test
    public void calculateFamilyAllowance_Above8000() {
        double allowance = employeeService.calculateFamilyAllowance(5, 9000);
        assertEquals(1450.0, allowance, 0.01); // 3 * 200 + 2 * 110
    }

    @Test
    public void calculateFamilyAllowance_Exactly6000() {
        double allowance = employeeService.calculateFamilyAllowance(5, 6000);
        assertEquals(1200.0, allowance, 0.01); // 3 * 300 + 2 * 150
    }

    @Test
    public void enterFamilyAllowanceForEmployee_ValidEmployee() {
        Long employeeId = 1L;
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setSalary(5000);

        when(employeeDAO.findById(employeeId)).thenReturn(Optional.of(employee));

        employeeService.enterFamilyAllowanceForEmployee(employeeId, 4);

        verify(employeeDAO, times(1)).findById(employeeId);
    }

    @Test
    public void enterFamilyAllowanceForEmployee_InvalidEmployee() {
        Long employeeId = 1L;

        when(employeeDAO.findById(employeeId)).thenReturn(Optional.empty());

        employeeService.enterFamilyAllowanceForEmployee(employeeId, 4);

        verify(employeeDAO, times(1)).findById(employeeId);
    }
}
