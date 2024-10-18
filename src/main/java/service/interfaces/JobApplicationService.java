package service.interfaces;

import entities.JobApplication;

public interface JobApplicationService {
    void applyForJob(JobApplication application);
    void acceptApplication(Long applicationId);
    void rejectApplication(Long applicationId);
}
