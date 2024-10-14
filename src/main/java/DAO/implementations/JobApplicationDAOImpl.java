package DAO.implementations;

import DAO.interfaces.JobApplicationDAO;
import models.JobApplication;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class JobApplicationDAOImpl implements JobApplicationDAO {

    @Override
    public void save(JobApplication jobApplication) {
        EntityManager entityManager = JpaUtil.getInstance().getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(jobApplication);
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
    public List<JobApplication> findAll() {
        EntityManager entityManager = JpaUtil.getInstance().getEntityManagerFactory().createEntityManager();

        try {
            TypedQuery<JobApplication> query = entityManager.createQuery("SELECT ja FROM JobApplication ja", JobApplication.class);
            return query.getResultList();
        } finally {
            entityManager.close(); 
        }
    }
}
