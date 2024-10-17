package service.implementations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import dao.interfaces.EmployeeDAO;
import dao.interfaces.VacationDAO;
import entities.Employee;
import entities.Vacation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class VacationServiceImplTest {

    private VacationServiceImpl vacationService;
    private VacationDAO vacationDAO;
    private EmployeeDAO employeeDAO;

    @BeforeEach
    void setUp() {
        vacationDAO = Mockito.mock(VacationDAO.class);
        employeeDAO = Mockito.mock(EmployeeDAO.class);
        vacationService = new VacationServiceImpl(vacationDAO, employeeDAO);
    }
    @Test
    void requestVacation_Success() throws Exception {
        // Créer un objet Employee avec les paramètres attendus
        Employee employee = new Employee(
                "John Doe",
                "123-45-6789",
                LocalDate.of(1985, 5, 20),
                "password123",
                LocalDate.of(2020, 1, 1),
                2,
                50000,
                "john.doe@example.com",
                "555-1234",
                "IT",
                "Developer",
                5,
                15
        );

        Vacation vacation = new Vacation(
                employee,
                LocalDate.now().plusDays(5),
                LocalDate.now().plusDays(14),  // End date ajustée pour 10 jours de congé
                "Family event",
                "document.pdf"
        );


        doNothing().when(vacationDAO).save(vacation);
        when(vacationDAO.findByEmployee(employee)).thenReturn(List.of());

        vacationService.requestVacation(employee, vacation);

        verify(vacationDAO, times(1)).save(vacation);
        verify(employeeDAO, times(1)).update(employee);

        assertEquals(10, employee.getVacationTaken());
    }

}
