package service.implementations;

import dao.interfaces.JobOfferDao;
import entities.JobOffer;
import service.interfaces.JobOfferService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferDao jobOfferDao;

    public JobOfferServiceImpl(JobOfferDao jobOfferDao) {
        this.jobOfferDao = jobOfferDao;
    }

    @Override
    public void createJobOffer(JobOffer jobOffer) {
        if (jobOffer.getTitle() == null || jobOffer.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Le titre est obligatoire.");
        }

        if (jobOffer.getDescription() == null || jobOffer.getDescription().isEmpty()) {
            throw new IllegalArgumentException("La description est obligatoire.");
        }

        if (jobOffer.getRequirement() == null || jobOffer.getRequirement().isEmpty()) {
            throw new IllegalArgumentException("Les exigences sont obligatoires.");
        }

        if (jobOffer.getPublicationDate() == null) {
            throw new IllegalArgumentException("La date de publication est obligatoire.");
        }

        if (jobOffer.getExpiryDate() == null) {
            throw new IllegalArgumentException("La date d'expiration est obligatoire.");
        }

        if (jobOffer.getPublicationDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La date de publication ne peut pas être dans le futur.");
        }

        if (jobOffer.getExpiryDate().isAfter(LocalDate.now())) {
            jobOffer.setStatus("Active");
        } else {
            jobOffer.setStatus("Expired");
        }

        jobOfferDao.save(jobOffer);
    }

    @Override
    public void updateJobOfferStatus() {
        List<JobOffer> jobOffers = jobOfferDao.findAll();
        for (JobOffer jobOffer : jobOffers) {
            if (jobOffer.getExpiryDate().isBefore(LocalDate.now()) && "Active".equals(jobOffer.getStatus())) {
                jobOffer.setStatus("Expired");
                jobOfferDao.update(jobOffer);
            }
        }
    }

    @Override
    public Optional<JobOffer> findJobOfferById(Long id) {
        return jobOfferDao.findById(id);
    }

    @Override
    public List<JobOffer> findAllJobOffers() {
        return jobOfferDao.findAll();
    }

    @Override
    public void updateJobOffer(JobOffer jobOffer) {
        jobOfferDao.update(jobOffer);
    }

    @Override
    public void deleteJobOffer(JobOffer jobOffer) {
        jobOfferDao.delete(jobOffer);
    }
}
