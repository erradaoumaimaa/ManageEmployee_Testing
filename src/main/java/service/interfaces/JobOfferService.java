package service.interfaces;

import entities.JobOffer;

import java.util.List;
import java.util.Optional;

public interface JobOfferService {

    void createJobOffer(JobOffer jobOffer);

    void updateJobOfferStatus();

    Optional<JobOffer> findJobOfferById(Long id);

    List<JobOffer> findAllJobOffers();

    void updateJobOffer(JobOffer jobOffer);

    void deleteJobOffer(JobOffer jobOffer);
}
