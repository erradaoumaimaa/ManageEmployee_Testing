package DAO.implementations;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import DAO.interfaces.EmployeeDAO;
import models.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManagerFactory entityManagerFactory;

    public EmployeeDAOImpl() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("employee-management-unit");
    }

    @Override
    public Optional<Employee> findById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Employee employee = null;

        try {
            employee = entityManager.find(Employee.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return Optional.ofNullable(employee);
    }

    @Override
    public void addEmployee(Employee employee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void removeEmployee(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Employee employee = entityManager.find(Employee.class, id);
            if (employee != null) {
                entityManager.remove(employee);
                transaction.commit();
            } else {
                System.out.println("Employee not found id: " + id);
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Employee existingEmployee = entityManager.find(Employee.class, employee.getId());
            if (existingEmployee != null) {
                entityManager.merge(employee);
                transaction.commit();
            } else {
                System.out.println("Employee not found id: " + employee.getId());
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> employees = null;

        try {
            employees = entityManager.createQuery("FROM Employee", Employee.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return employees;
    }

    @Override
    public List<Employee> findAllEmployees(String search, String position, String department) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> employees = null;

        search = "%" + search.toLowerCase() + "%";
        String jpql = "FROM Employee e WHERE (LOWER(e.name) LIKE :search OR LOWER(e.email) LIKE :search) "
                + "AND (:position IS NULL OR LOWER(e.position) = :position) "
                + "AND (:department IS NULL OR LOWER(e.department) = :department)";

        try {
            employees = entityManager.createQuery(jpql, Employee.class)
                    .setParameter("search", search)
                    .setParameter("position", position != null && !position.isEmpty() ? position.toLowerCase() : null)
                    .setParameter("department", department != null && !department.isEmpty() ? department.toLowerCase() : null)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return employees;
    }
}
