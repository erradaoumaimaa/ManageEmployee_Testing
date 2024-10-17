package dao.implementations;

import dao.interfaces.VacationDAO;
import entities.Employee;
import entities.Vacation;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class VacationDAOImpl extends GenericDAOImpl<Vacation> implements VacationDAO {

    public VacationDAOImpl() {
        super(Vacation.class);
    }

    @Override
    public List<Vacation> findByEmployee(Employee employee) {
        EntityManager entityManager = null;
        List<Vacation> vacations = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<Vacation> query = entityManager.createQuery(
                    "SELECT v FROM Vacation v WHERE v.employee = :employee", Vacation.class);
            query.setParameter("employee", employee);
            vacations = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return vacations;
    }
}
