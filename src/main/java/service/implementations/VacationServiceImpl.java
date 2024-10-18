package service.implementations;

import dao.interfaces.EmployeeDAO;
import service.interfaces.EmailService;
import service.interfaces.VacationService;
import dao.interfaces.VacationDAO;
import entities.Employee;
import entities.Vacation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class VacationServiceImpl implements VacationService {

    private final VacationDAO vacationDAO;
    private final EmployeeDAO employeeDAO;
    private final EmailService emailService; // Ensure you have this variable

    public VacationServiceImpl(VacationDAO vacationDAO, EmployeeDAO employeeDAO, EmailService emailService) {
        this.vacationDAO = vacationDAO;
        this.employeeDAO = employeeDAO;
        this.emailService = emailService; // Initialize here
    }

    @Override
    public void requestVacation(Employee employee, Vacation vacation) throws Exception {
        if (canTakeVacation(employee, vacation)) {
            vacationDAO.save(vacation);

            int daysRequested = calculateVacationDays(vacation);
            employee.setVacationTaken(employee.getVacationTaken() + daysRequested);
            employee.setVacationDays(employee.getVacationDays() - daysRequested);

            employeeDAO.update(employee);
        } else {
            throw new Exception("Les dates de congé ne sont pas valides ou dépassent le solde de congés.");
        }
    }

    @Override
    public List<Vacation> getVacationsByEmployee(Employee employee) {
        return vacationDAO.findByEmployee(employee);
    }

    @Override
    public boolean canTakeVacation(Employee employee, Vacation vacation) throws Exception {
        if (vacation.getStartDate().isBefore(LocalDate.now()) || vacation.getEndDate().isBefore(LocalDate.now())) {
            throw new Exception("Les dates de congé doivent être dans le futur.");
        }

        int daysRequested = calculateVacationDays(vacation);

        if (employee.getVacationDays() < daysRequested) {
            return false;
        }

        return !isOverlapping(employee, vacation);
    }

    @Override
    public int calculateVacationDays(Vacation vacation) {
        if (vacation.getStartDate().isAfter(vacation.getEndDate())) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin.");
        }
        return (int) ChronoUnit.DAYS.between(vacation.getStartDate(), vacation.getEndDate()) + 1;
    }


    @Override
    public boolean isOverlapping(Employee employee, Vacation vacation) {
        List<Vacation> existingVacations = vacationDAO.findByEmployee(employee);
        for (Vacation v : existingVacations) {
            if (vacation.getStartDate().isBefore(v.getEndDate()) && vacation.getEndDate().isAfter(v.getStartDate())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Vacation> findByEmployee(Employee employee) {
        return vacationDAO.findByEmployee(employee);
    }

    public void approveVacation(Long vacationId) throws Exception {
        Optional<Vacation> optionalVacation = vacationDAO.findById(vacationId);

        if (optionalVacation.isEmpty()) {
            throw new Exception("Demande de vacances non trouvée.");
        }

        Vacation vacation = optionalVacation.get();
        vacation.setStatus("APPROVED");
        vacationDAO.save(vacation);

        String subject = "Demande de congé approuvée";
        String messageText = "Bonjour " + vacation.getEmployee().getName() +
                ", votre demande de congé du " + vacation.getStartDate() +
                " au " + vacation.getEndDate() + " a été approuvée.";
        emailService.sendEmail(vacation.getEmployee().getEmail(), subject, messageText);
    }

    public void rejectVacation(Long vacationId) throws Exception {
        Optional<Vacation> optionalVacation = vacationDAO.findById(vacationId);

        if (optionalVacation.isEmpty()) {
            throw new Exception("Demande de vacances non trouvée.");
        }

        Vacation vacation = optionalVacation.get();
        vacation.setStatus("REJECTED");
        vacationDAO.save(vacation);

        String subject = "Demande de congé refusée";
        String messageText = "Bonjour " + vacation.getEmployee().getName() +
                ", votre demande de congé du " + vacation.getStartDate() +
                " au " + vacation.getEndDate() + " a été refusée.";
        emailService.sendEmail(vacation.getEmployee().getEmail(), subject, messageText);
    }
}
