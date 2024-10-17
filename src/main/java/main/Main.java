package main;

import entities.Employee;
import entities.Vacation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("employee-management-unit");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            // Connexion réussie
            System.out.println("Connexion à la base de données réussie!");

            // Étape 1 : Créer un employé avec les champs corrects
            Employee employee = new Employee();
            employee.setName("John Doe");
            employee.setSSN("123-45-6789");
            employee.setBirthDate(LocalDate.of(1985, 6, 15));
            employee.setPassword("password123");
            employee.setHireDate(LocalDate.of(2010, 1, 1));
            employee.setNumberOfChildren(2);
            employee.setSalary(50000.00);
            employee.setEmail("john.doe@example.com");
            employee.setPhone("555-1234");
            employee.setDepartment("IT");
            employee.setPosition("Software Engineer");
            employee.setVacationTaken(0);
            employee.setVacationDays(20);
            // Persister l'employé dans la base
            entityManager.persist(employee);

            // Étape 2 : Créer une vacation liée à cet employé
            Vacation vacation = new Vacation();
            vacation.setEmployee(employee);
            vacation.setStartDate(LocalDate.of(2024, 10, 1));
            vacation.setEndDate(LocalDate.of(2024, 10, 10));
            vacation.setReason("Vacances annuelles");
            vacation.setDocument("vacation_doc.pdf");
            // Persister la vacation
            entityManager.persist(vacation);

            // Commit de la transaction
            entityManager.getTransaction().commit();

            // Affichage de confirmation
            System.out.println("Vacation ajoutée avec succès!");

        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }
}
