package dao.implementations;

import dao.interfaces.JobApplicationDao;
import entities.JobApplication;

public class JobApplicationDaoImpl extends GenericDAOImpl<JobApplication> implements JobApplicationDao {

    public JobApplicationDaoImpl() {
        super(JobApplication.class);
    }
}
