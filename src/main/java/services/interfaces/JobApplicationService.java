package services.interfaces;

import models.JobApplication;
import java.util.List;

public interface JobApplicationService {
    void applyToJob(JobApplication jobApplication);
    List<JobApplication> getAllApplications();
}
