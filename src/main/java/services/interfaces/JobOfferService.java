package services.interfaces;

import models.JobOffer;

import java.util.List;

public interface JobOfferService {
    JobOffer getJobOfferById(Long id);
    List<JobOffer> getAllJobOffers();
    void saveJobOffer(JobOffer jobOffer);
    void updateJobOffer(JobOffer jobOffer);
    void deleteJobOffer(Long id);
}
