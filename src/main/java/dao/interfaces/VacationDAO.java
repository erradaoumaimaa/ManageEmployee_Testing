package dao.interfaces;

import entities.Employee;
import entities.Vacation;

import java.util.List;

public interface VacationDAO extends GenericDAO<Vacation>{

    List<Vacation> findByEmployee(Employee employee);


}
