package dao.implementations;

import dao.interfaces.GenericDAO;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class GenericDAOImpl<T> implements GenericDAO<T> {
    protected EntityManagerFactory entityManagerFactory;
    private Class<T> entityClass;

    public GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityManagerFactory = Persistence.createEntityManagerFactory("employee-management-unit");
    }

    @Override
    public void save(T e) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(e);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Optional<T> findById(Long id) {
        EntityManager entityManager = null;
        T entity = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            entity = entityManager.find(entityClass, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return Optional.ofNullable(entity);
    }

    @Override
    public List<T> findAll() {
        EntityManager entityManager = null;
        List<T> results = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            results = entityManager.createQuery("FROM " + entityClass.getName(), entityClass).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return results;
    }

    @Override
    public void update(T entity) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void delete(T entity) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
