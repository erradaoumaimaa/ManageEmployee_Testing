package service.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import dao.interfaces.EmployeeDAO;
import dao.interfaces.VacationDAO;
import entities.Employee;
import entities.Vacation;
import org.mockito.Mockito;
import service.interfaces.EmailService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

class VacationServiceImplTest {

    private VacationServiceImpl vacationService;
    private VacationDAO vacationDAO;
    private EmployeeDAO employeeDAO;
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        vacationDAO = Mockito.mock(VacationDAO.class);
        employeeDAO = Mockito.mock(EmployeeDAO.class);
        emailService = Mockito.mock(EmailService.class); // Ensure this is mocked
        vacationService = new VacationServiceImpl(vacationDAO, employeeDAO, emailService); // Inject the mock here
    }

    @Test
    void requestVacation_Success() throws Exception {
        Employee employee = new Employee("John Doe", "123-45-6789", LocalDate.of(1985, 5, 20), "password123", LocalDate.of(2020, 1, 1), 2, 50000, "john.doe@example.com", "555-1234", "IT", "Developer", 5, 15);

        Vacation vacation = new Vacation(employee, LocalDate.now().plusDays(5), LocalDate.now().plusDays(14), "Family event", "document.pdf");

        doNothing().when(vacationDAO).save(vacation);
        when(vacationDAO.findByEmployee(employee)).thenReturn(List.of());

        vacationService.requestVacation(employee, vacation);

        verify(vacationDAO, times(1)).save(vacation);
        verify(employeeDAO, times(1)).update(employee);

        assertEquals(10, employee.getVacationTaken());
    }

    @Test
    void approveVacation_Success() throws Exception {
        Employee employee = new Employee("John Doe", "123-45-6789", LocalDate.of(1985, 5, 20), "password123", LocalDate.of(2020, 1, 1), 2, 50000, "john.doe@example.com", "555-1234", "IT", "Developer", 5, 15);
        Vacation vacation = new Vacation(employee, LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), "Family event", "document.pdf");
        vacation.setId(1L);

        when(vacationDAO.findById(1L)).thenReturn(Optional.of(vacation));

        vacationService.approveVacation(1L);

        verify(vacationDAO).save(vacation);
        assertEquals("APPROVED", vacation.getStatus());
        verify(emailService).sendEmail(eq(employee.getEmail()), contains("Demande de congé approuvée"), contains("votre demande de congé"));
    }

    @Test
    void rejectVacation_Success() throws Exception {
        Employee employee = new Employee("Jane Doe", "987-65-4321", LocalDate.of(1990, 1, 1), "password456", LocalDate.of(2021, 5, 1), 5, 60000, "jane.doe@example.com", "555-5678", "HR", "Manager", 10, 20);
        Vacation vacation = new Vacation(employee, LocalDate.now().plusDays(3), LocalDate.now().plusDays(8), "Personal event", "document.pdf");
        vacation.setId(2L);

        when(vacationDAO.findById(2L)).thenReturn(Optional.of(vacation));

        vacationService.rejectVacation(2L);

        verify(vacationDAO).save(vacation);
        assertEquals("REJECTED", vacation.getStatus());
        verify(emailService).sendEmail(eq(employee.getEmail()), contains("Demande de congé refusée"), contains("votre demande de congé"));
    }
}
