package controller;

import dao.implementations.EmployeeDAOImpl;
import entities.Employee;
import service.implementations.EmployeeServiceImpl;
import service.interfaces.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet("/employees")
public class EmployeeController extends HttpServlet {

    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        employeeService = new EmployeeServiceImpl(new EmployeeDAOImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            case "list":
            default:
                listEmployees(request, response);
                break;
        }
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = employeeService.findAllEmployees();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/WEB-INF/views/employee/listEmployee.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/employee/addEmployee.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<Employee> employee = employeeService.findEmployeeById(id);
        if (employee.isPresent()) {
            request.setAttribute("employee", employee.get());
            request.getRequestDispatcher("/WEB-INF/views/employee/updateEmployee.jsp").forward(request, response);
        } else {
            response.sendRedirect("employees?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "save":
                saveEmployee(request, response);
                break;
            case "update":
                updateEmployee(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            default:
                listEmployees(request, response);
                break;
        }
    }

    private void saveEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Employee employee = extractEmployeeFromRequest(request);
        employeeService.saveEmployee(employee);
        response.sendRedirect("employees?action=list");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        Employee employee = extractEmployeeFromRequest(request);
        employee.setId(id);
        employeeService.updateEmployee(employee);
        response.sendRedirect("employees?action=list");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<Employee> employee = employeeService.findEmployeeById(id);
        employee.ifPresent(employeeService::deleteEmployee);
        response.sendRedirect("employees?action=list");
    }
    private Employee extractEmployeeFromRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        String SSN = request.getParameter("SSN");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birth_date"));
        String password = request.getParameter("password");
        LocalDate hireDate = LocalDate.parse(request.getParameter("hire_date"));
        int numberOfChildren = Integer.parseInt(request.getParameter("numberOfChildren"));
        double salary = Double.parseDouble(request.getParameter("salary"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String department = request.getParameter("department");
        String position = request.getParameter("position");

        int vacationDays = Integer.parseInt(request.getParameter("vacationDays"));
        System.out.println("Vacation Days récupérés : " + vacationDays);

        int vacationTaken = Integer.parseInt(request.getParameter("vacationTaken"));

        Employee employee = new Employee();
        employee.setName(name);
        employee.setSSN(SSN);
        employee.setBirthDate(birthDate);
        employee.setPassword(password);
        employee.setHireDate(hireDate);
        employee.setNumberOfChildren(numberOfChildren);
        employee.setSalary(salary);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setDepartment(department);
        employee.setPosition(position);

        // Ajouter les vacances à l'employé
        employee.setVacationDays(vacationDays);
        employee.setVacationTaken(vacationTaken);

        return employee;
    }

}
