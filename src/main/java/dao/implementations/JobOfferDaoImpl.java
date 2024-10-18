package dao.implementations;

import dao.interfaces.JobOfferDao;
import entities.JobOffer;

public class JobOfferDaoImpl extends GenericDAOImpl<JobOffer> implements JobOfferDao {

    public JobOfferDaoImpl() {
        super(JobOffer.class);
    }
}
