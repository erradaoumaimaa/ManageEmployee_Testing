package DAO.interfaces;

import models.JobApplication;
import java.util.List;

public interface JobApplicationDAO {
    void save(JobApplication jobApplication);
    List<JobApplication> findAll(); 
}
