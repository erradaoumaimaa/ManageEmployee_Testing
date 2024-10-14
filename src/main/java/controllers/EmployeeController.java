package controllers;

import DAO.implementations.EmployeeDAOImpl;
import models.Employee;
import services.implementations.EmployeeServiceImpl;
import services.interfaces.EmployeeService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

@WebServlet("/employees")
public class EmployeeController extends HttpServlet {

    private EmployeeService employeeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.employeeService = new EmployeeServiceImpl(new EmployeeDAOImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {
            listEmployees(request, response);
        } else if (action.equals("delete")) {
            deleteEmployee(request, response);
        } else if (action.equals("edit")) {
            showUpdateForm(request, response);
        } else if (action.equals("create")) {
            showAddForm(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employeeList = employeeService.getAllEmployees();
        request.setAttribute("employees", employeeList);
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int employeeId = Integer.parseInt(request.getParameter("id"));
            employeeService.removeEmployee(employeeId);
            response.sendRedirect("employees?action=list"); // Rediriger après suppression
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid employee ID");
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Optional<Employee> optionalEmployee = employeeService.findById(id);

            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                request.setAttribute("employee", employee);
                request.getRequestDispatcher("/WEB-INF/views/updateEmployee.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Employee not found.");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid employee ID format");
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/addEmployee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createEmployee(request, response);
        } else if ("update".equals(action)) {
            updateEmployee(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void createEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String name = request.getParameter("name");
            String ssn = request.getParameter("SSN");
            String birthDateStr = request.getParameter("birth_date");
            String hireDateStr = request.getParameter("hire_date");
            String salaryStr = request.getParameter("salary");
            String numberOfChildrenStr = request.getParameter("numberOfChildren");

            double salary = 0;
            int numberOfChildren = 0;

            if (salaryStr != null && !salaryStr.isEmpty()) {
                salary = Double.parseDouble(salaryStr);
            } else {
                throw new NumberFormatException("Invalid salary format");
            }
            if (numberOfChildrenStr != null && !numberOfChildrenStr.isEmpty()) {
                numberOfChildren = Integer.parseInt(numberOfChildrenStr);
            } else {
                throw new NumberFormatException("Invalid number of children format");
            }

            Employee newEmployee = new Employee(
                    name,
                    ssn,
                    LocalDate.parse(birthDateStr),
                    request.getParameter("password"),
                    LocalDate.parse(hireDateStr),
                    numberOfChildren,
                    salary,
                    request.getParameter("email"),
                    request.getParameter("phone"),
                    request.getParameter("department"),
                    request.getParameter("position")
            );

            employeeService.addEmployee(newEmployee);
            response.sendRedirect("employees?action=list");

        } catch (DateTimeParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String idParam = request.getParameter("id");
            System.out.println("Employee ID: " + idParam);

            if (idParam == null || idParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing employee ID");
                return;
            }

            int employeeId;
            try {
                employeeId = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid employee ID format");
                return;
            }

            // Récupération et validation du salaire
            String salaryStr = request.getParameter("salary");
            if (salaryStr == null || salaryStr.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing salary");
                return;
            }

            double salary;
            try {
                salary = Double.parseDouble(salaryStr);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid salary format");
                return;
            }

            // Récupération et validation du nombre d'enfants
            String numberOfChildrenStr = request.getParameter("numberOfChildren");
            if (numberOfChildrenStr == null || numberOfChildrenStr.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing number of children");
                return;
            }

            int numberOfChildren;
            try {
                numberOfChildren = Integer.parseInt(numberOfChildrenStr);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number of children format");
                return;
            }

            // Récupération et validation des dates
            String birthDateStr = request.getParameter("birth_date");
            String hireDateStr = request.getParameter("hire_date");

            LocalDate birthDate, hireDate;
            try {
                birthDate = LocalDate.parse(birthDateStr);
            } catch (DateTimeParseException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid birth date format");
                return;
            }

            try {
                hireDate = LocalDate.parse(hireDateStr);
            } catch (DateTimeParseException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid hire date format");
                return;
            }

            Employee updatedEmployee = new Employee(

                    request.getParameter("name"),
                    request.getParameter("SSN"),
                    birthDate,
                    request.getParameter("password"),
                    hireDate,
                    numberOfChildren,
                    salary,
                    request.getParameter("email"),
                    request.getParameter("phone"),
                    request.getParameter("department"),
                    request.getParameter("position")
            );

            updatedEmployee.setId(employeeId);
            employeeService.updateEmployee(updatedEmployee);
            response.sendRedirect("employees?action=list");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid employee ID or number format: " + e.getMessage());
            e.printStackTrace();
        } catch (DateTimeParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
