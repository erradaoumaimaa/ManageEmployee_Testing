package services.implementations;

import DAO.interfaces.JobApplicationDAO;
import DAO.implementations.JobApplicationDAOImpl;
import models.JobApplication;
import services.interfaces.JobApplicationService;

import java.util.List;

public class JobApplicationServiceImpl implements JobApplicationService {
    private JobApplicationDAO jobApplicationDAO;

    public JobApplicationServiceImpl(JobApplicationDAO jobApplicationDAO) {
        this.jobApplicationDAO = jobApplicationDAO;
    }

    @Override
    public void applyToJob(JobApplication jobApplication) {
        jobApplicationDAO.save(jobApplication);
    }

    @Override
    public List<JobApplication> getAllApplications() {
        return jobApplicationDAO.findAll();
    }
}
