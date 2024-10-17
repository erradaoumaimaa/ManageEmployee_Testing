package service.interfaces;

import entities.Employee;
import entities.Vacation;

import java.util.List;

public interface VacationService {

    void requestVacation(Employee employee, Vacation vacation) throws Exception;

    List<Vacation> getVacationsByEmployee(Employee employee);

    boolean canTakeVacation(Employee employee, Vacation vacation)throws Exception;

    int calculateVacationDays(Vacation vacation);

    boolean isOverlapping(Employee employee, Vacation vacation);

    List<Vacation> findByEmployee(Employee employee);

    void approveVacation(Long vacationId) throws Exception;
    void rejectVacation(Long vacationId) throws Exception;


}
