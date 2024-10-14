package DAO.implementations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;
import DAO.interfaces.JobOfferDao;
import models.JobOffer;

public class JobOfferDaoImpl implements JobOfferDao {

    private EntityManagerFactory entityManagerFactory;

    public JobOfferDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public JobOffer findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        JobOffer jobOffer = null;
        try {
            jobOffer = entityManager.find(JobOffer.class, id);
        } finally {
            entityManager.close();
        }
        return jobOffer;
    }

    @Override
    public List<JobOffer> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<JobOffer> jobOffers = null;
        try {
            jobOffers = entityManager.createQuery("SELECT j FROM JobOffer j", JobOffer.class).getResultList();
        } finally {
            entityManager.close();
        }
        return jobOffers;
    }

    @Override
    public void save(JobOffer jobOffer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(jobOffer);
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
    public void update(JobOffer jobOffer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(jobOffer);
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
    public void delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            JobOffer jobOffer = entityManager.find(JobOffer.class, id);
            if (jobOffer != null) {
                entityManager.remove(jobOffer);
            }
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
}
