package service.implementations;

import dao.interfaces.JobApplicationDao;
import dao.interfaces.JobOfferDao;
import entities.JobApplication;
import entities.JobOffer;
import service.interfaces.JobApplicationService;

import java.util.Optional;

public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationDao jobApplicationDao;
    private final JobOfferDao jobOfferDao;

    public JobApplicationServiceImpl(JobApplicationDao jobApplicationDao, JobOfferDao jobOfferDao) {
        this.jobApplicationDao = jobApplicationDao;
        this.jobOfferDao = jobOfferDao;
    }

    @Override
    public void applyForJob(JobApplication application) {
        Optional<JobOffer> jobOffer = jobOfferDao.findById(application.getJobOffer().getId());
        if (jobOffer.isPresent()) {
            jobApplicationDao.save(application);
        } else {
            throw new IllegalArgumentException("Job offer not found.");
        }
    }

    @Override
    public void acceptApplication(Long applicationId) {
        Optional<JobApplication> application = jobApplicationDao.findById(applicationId);
        if (application.isPresent()) {
            JobApplication jobApplication = application.get();
            jobApplication.setStatus("Accepted");
            jobApplicationDao.update(jobApplication);
        } else {
            throw new IllegalArgumentException("Application not found.");
        }
    }

    @Override
    public void rejectApplication(Long applicationId) {
        Optional<JobApplication> application = jobApplicationDao.findById(applicationId);
        if (application.isPresent()) {
            JobApplication jobApplication = application.get();
            jobApplication.setStatus("Rejected");
            jobApplicationDao.update(jobApplication);
        } else {
            throw new IllegalArgumentException("Application not found.");
        }
    }
}
