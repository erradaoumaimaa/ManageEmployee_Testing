package controller;

import dao.implementations.EmployeeDAOImpl;
import dao.implementations.VacationDAOImpl;
import dao.interfaces.EmployeeDAO;
import dao.interfaces.VacationDAO;
import entities.Employee;
import entities.Vacation;
import service.implementations.VacationServiceImpl;
import service.interfaces.VacationService;
import service.interfaces.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@WebServlet("/vacation")
@MultipartConfig
public class VacationController extends HttpServlet {

    private VacationService vacationService;
    private EmployeeDAO employeeDAO;
    private VacationDAO vacationDAO;

    @Override
    public void init() throws ServletException {
        employeeDAO = new EmployeeDAOImpl();
        vacationDAO = new VacationDAOImpl();
        vacationService = new VacationServiceImpl(vacationDAO, employeeDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list"; // Action par défaut pour afficher la liste
        }

        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
//                showEditForm(request, response);
                break;
            case "approve":
                approveVacation(request, response);
                break;
            case "reject":
                rejectVacation(request, response);
                break;
            case "list":
            default:
//                listVacations(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "save":
                saveVacation(request, response);
                break;
            case "update":
                updateVacation(request, response);
                break;
            case "approve":
                approveVacation(request, response);
                break;
            case "reject":
                rejectVacation(request, response);
                break;
            default:
//                listVacations(request, response);
                break;
        }
    }

    // Méthode pour lister les vacances
//    private void listVacations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Vacation> vacations = vacationService.findAllVacations();
//        request.setAttribute("vacations", vacations);
//        request.getRequestDispatcher("/WEB-INF/views/vacation/vacationList.jsp").forward(request, response);
//    }

    // Méthode pour afficher le formulaire d'ajout de congé
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/vacation/addVacation.jsp").forward(request, response);
    }

    // Méthode pour afficher le formulaire de modification de congé
//    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Long vacationId = Long.parseLong(request.getParameter("id"));
//        Vacation vacation = vacationService.findVacationById(vacationId);
//        request.setAttribute("vacation", vacation);
//        request.getRequestDispatcher("/WEB-INF/views/vacation/editVacation.jsp").forward(request, response);
//    }

    // Méthode pour sauvegarder un congé
    private void saveVacation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long employeeId = Long.parseLong(request.getParameter("employeeId"));
            LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
            LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
            String reason = request.getParameter("reason");
            Part document = request.getPart("document");

            Optional<Employee> optionalEmployee = employeeDAO.findById(employeeId);
            if (optionalEmployee.isEmpty()) {
                throw new Exception("Employé non trouvé.");
            }

            String documentPath = saveUploadedFile(document);

            Employee employee = optionalEmployee.get();
            Vacation vacation = new Vacation(employee, startDate, endDate, reason, documentPath);

            vacationService.requestVacation(employee, vacation);

            response.sendRedirect(request.getContextPath() + "/vacation?action=list");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/vacation/addVacation.jsp").forward(request, response);
        }
    }

    // Méthode pour mettre à jour un congé
    private void updateVacation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            Long vacationId = Long.parseLong(request.getParameter("id"));
//            LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
//            LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
//            String reason = request.getParameter("reason");
//
//            Vacation vacation = vacationService.findVacationById(vacationId);
//            vacation.setStartDate(startDate);
//            vacation.setEndDate(endDate);
//            vacation.setReason(reason);
//
//            vacationService.updateVacation(vacation);
//
//            response.sendRedirect(request.getContextPath() + "/vacation?action=list");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            request.setAttribute("errorMessage", e.getMessage());
//            request.getRequestDispatcher("/WEB-INF/views/vacation/editVacation.jsp").forward(request, response);
//        }
    }

    private void approveVacation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long vacationId = Long.parseLong(request.getParameter("id"));
            vacationService.approveVacation(vacationId);
            response.sendRedirect(request.getContextPath() + "/vacation?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/vacation/vacationList.jsp").forward(request, response);
        }
    }

    private void rejectVacation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long vacationId = Long.parseLong(request.getParameter("id"));
            vacationService.rejectVacation(vacationId);
            response.sendRedirect(request.getContextPath() + "/vacation?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/vacation/vacationList.jsp").forward(request, response);
        }
    }

    private String saveUploadedFile(Part document) throws IOException {
        String uploadDir = getServletContext().getRealPath("/") + "uploads";
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = Paths.get(document.getSubmittedFileName()).getFileName().toString();
        Path filePath = uploadPath.resolve(fileName);

        document.write(filePath.toString());

        return filePath.toString();
    }
}
