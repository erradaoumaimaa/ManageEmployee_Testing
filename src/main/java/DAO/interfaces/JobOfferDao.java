package DAO.interfaces;

import models.JobOffer;
import java.util.List;

public interface JobOfferDao {
    JobOffer findById(Long id);
    List<JobOffer> findAll();
    void save(JobOffer jobOffer);
    void update(JobOffer jobOffer);
    void delete(Long id);
}
