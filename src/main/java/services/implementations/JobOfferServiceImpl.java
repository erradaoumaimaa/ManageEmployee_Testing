package services.implementations;

import DAO.interfaces.JobOfferDao;
import models.JobOffer;
import services.interfaces.JobOfferService;

import java.util.List;

public class JobOfferServiceImpl implements JobOfferService {

    private JobOfferDao jobOfferDao;

    public JobOfferServiceImpl(JobOfferDao jobOfferDao) {
        this.jobOfferDao = jobOfferDao;
    }

    @Override
    public JobOffer getJobOfferById(Long id) {
        return jobOfferDao.findById(id);
    }

    @Override
    public List<JobOffer> getAllJobOffers() {
        return jobOfferDao.findAll();
    }

    @Override
    public void saveJobOffer(JobOffer jobOffer) {
        jobOfferDao.save(jobOffer);
    }

    @Override
    public void updateJobOffer(JobOffer jobOffer) {
        jobOfferDao.update(jobOffer);
    }

    @Override
    public void deleteJobOffer(Long id) {
        jobOfferDao.delete(id);
    }
}

