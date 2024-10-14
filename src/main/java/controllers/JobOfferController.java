package controllers;

import DAO.implementations.JobOfferDaoImpl;
import models.JobOffer;
import services.implementations.JobOfferServiceImpl;
import services.interfaces.JobOfferService;
import utils.JpaUtil;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class JobOfferController extends HttpServlet {

    private JobOfferService jobOfferService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        EntityManagerFactory entityManagerFactory = JpaUtil.getInstance().getEntityManagerFactory();
        this.jobOfferService = new JobOfferServiceImpl(new JobOfferDaoImpl(entityManagerFactory));
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listJobOffers(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteJobOffer(request, response);
                break;
            case "new":
                showNewForm(request, response);
                break;
            default:
                listJobOffers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertJobOffer(request, response);
        } else if ("update".equals(action)) {
            updateJobOffer(request, response);
        }
    }

    private void listJobOffers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<JobOffer> jobOffers = jobOfferService.getAllJobOffers();
        request.setAttribute("jobOffers", jobOffers);
        request.getRequestDispatcher("/WEB-INF/views/jobOfferList.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        JobOffer existingJobOffer = jobOfferService.getJobOfferById(id);
        request.setAttribute("jobOffer", existingJobOffer);
        request.getRequestDispatcher("/WEB-INF/views/jobOfferForm.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/jobOfferForm.jsp").forward(request, response);
    }

    private void insertJobOffer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String requirement = request.getParameter("requirement");
        String publicationDate = request.getParameter("publicationDate");
        String expiryDate = request.getParameter("expiryDate");

        JobOffer newJobOffer = new JobOffer();
        newJobOffer.setTitle(title);
        newJobOffer.setDescription(description);
        newJobOffer.setRequirement(requirement);
        newJobOffer.setPublicationDate(LocalDate.parse(publicationDate));
        newJobOffer.setExpiryDate(LocalDate.parse(expiryDate));
        newJobOffer.setStatus("Open");

        jobOfferService.saveJobOffer(newJobOffer);
        response.sendRedirect("jobOffers?action=list");
    }

    private void updateJobOffer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String requirement = request.getParameter("requirement");
        String publicationDate = request.getParameter("publicationDate");
        String expiryDate = request.getParameter("expiryDate");
        String status = request.getParameter("status");

        JobOffer jobOffer = jobOfferService.getJobOfferById(id);
        jobOffer.setTitle(title);
        jobOffer.setDescription(description);
        jobOffer.setRequirement(requirement);
        jobOffer.setPublicationDate(LocalDate.parse(publicationDate));
        jobOffer.setExpiryDate(LocalDate.parse(expiryDate));
        jobOffer.setStatus(status);

        jobOfferService.updateJobOffer(jobOffer);
        response.sendRedirect("jobOffers?action=list");
    }

    private void deleteJobOffer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        jobOfferService.deleteJobOffer(id);
        response.sendRedirect("jobOffers?action=list");
    }
}
