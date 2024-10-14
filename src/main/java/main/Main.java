package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("main-unit");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            // Si nous atteignons ce point, la connexion est réussie.
            System.out.println("Connexion à la base de données réussie!");

            // Ici, vous pouvez ajouter des opérations supplémentaires, comme des requêtes.

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
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

