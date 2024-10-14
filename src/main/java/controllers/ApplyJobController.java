package controllers;

import models.JobApplication;
import models.JobOffer;
import services.implementations.JobApplicationServiceImpl;
import services.interfaces.JobApplicationService;
import utils.JpaUtil;
import DAO.interfaces.JobApplicationDAO;
import DAO.implementations.JobApplicationDAOImpl;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/applyJob")
public class ApplyJobController extends HttpServlet {
    private JobApplicationService jobApplicationService;

    public ApplyJobController() {
        JobApplicationDAO jobApplicationDAO = new JobApplicationDAOImpl();
        this.jobApplicationService = new JobApplicationServiceImpl(jobApplicationDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jobIdStr = request.getParameter("id");
        if (jobIdStr == null || jobIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de l'offre d'emploi non fourni.");
            return;
        }

        long jobId;
        try {
            jobId = Long.parseLong(jobIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de l'offre d'emploi invalide.");
            return;
        }

        EntityManager entityManager = JpaUtil.getInstance().getEntityManagerFactory().createEntityManager();
        try {
            JobOffer jobOffer = entityManager.find(JobOffer.class, jobId);
            if (jobOffer == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Offre d'emploi non trouvée.");
                return;
            }

            request.setAttribute("jobOffer", jobOffer);
            request.setAttribute("jobOfferId", jobId);
            request.getRequestDispatcher("/WEB-INF/views/applyJobForm.jsp").forward(request, response);
        } finally {
            entityManager.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicantName = request.getParameter("name");
        String applicantEmail = request.getParameter("email");
        String applicantPhone = request.getParameter("phone");
        String jobOfferIdStr = request.getParameter("jobOfferId");

        if (jobOfferIdStr == null || jobOfferIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de l'offre d'emploi non fourni.");
            return;
        }

        long jobOfferId;
        try {
            jobOfferId = Long.parseLong(jobOfferIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de l'offre d'emploi invalide.");
            return;
        }

        JobApplication jobApplication = new JobApplication(applicantName, applicantEmail, applicantPhone, null, null);

        EntityManager entityManager = JpaUtil.getInstance().getEntityManagerFactory().createEntityManager();
        try {
            JobOffer jobOffer = entityManager.find(JobOffer.class, jobOfferId);
            if (jobOffer == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Offre d'emploi non trouvée.");
                return;
            }
            jobApplication.setJobOffer(jobOffer);

            jobApplicationService.applyToJob(jobApplication);

            response.sendRedirect("jobOffers?message=Candidature soumise avec succès !");
        } finally {
            entityManager.close();
        }
    }
}
