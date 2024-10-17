<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />

    <title>Manage Employees</title>
    <style>
        @import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");

        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            font-family: "Poppins", sans-serif;
        }
        body {
            background: #ffffff;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }
        .container {
            max-width: 1440px;
            width: 100%;
            background: #fff;
            box-shadow: 2px 5px 10px rgba(0, 0, 0, 0.5);
        }
        .container h2 {
            padding: 2rem 1rem;
            text-align: center;
            background: #023047;
            color: #ffffff;
            font-size: 2.5rem;
        }
        .tbl {
            width: 100%;
            border-collapse: collapse;
        }
        .tbl thead {
            background: #023047;
            color: #ffffff;
        }
        .tbl thead tr th {
            font-size: 0.9rem;
            padding: 0.8rem;
            letter-spacing: 0.2rem;
            vertical-align: top;
            border: 1px solid #aab7b8;
        }
        .tbl tbody tr td {
            font-size: 1rem;
            letter-spacing: 0.2rem;
            font-weight: normal;
            text-align: center;
            border: 1px solid #aab7b8;
            padding: 0.8rem;
        }
        .tbl tr:nth-child(even) {
            background: #8ecae6;
            transition: all 0.3s ease-in;
            cursor: pointer;
        }

        .tbl button {
            display: inline-block;
            border: none;
            margin: 0 auto;
            padding: 0.4rem;
            border-radius: 1px;
            outline: none;
            cursor: pointer;
        }
        .btn_trash {
            color: #d00000;
        }
        .btn_edit {
            color: #ffb703;
        }
        .btn_add {
            color: #008000;
            padding: 10px 15px;
            text-decoration: none;
            font-size: 14px;
        }

        .btn_add:hover {

            color: #d00000;
        }

        @media (max-width: 768px) {
            .tbl thead {
                display: none;
            }
            .tbl tr,
            .tbl td {
                display: block;
                width: 100%;
            }
            .tbl tr {
                margin-bottom: 1rem;
            }
            .tbl tbody tr td {
                text-align: right;
                position: relative;
                transition: all 0.2s ease-in;
            }
            .tbl td::before {
                content: attr(data-label);
                position: absolute;
                left: 0;
                width: 50%;
                padding-left: 1.2rem;
                text-align: left;
            }
            .tbl tbody tr td:hover {
                background: #ccc;
                color: #000;
            }
            .tbl_content {
                padding: 1rem;
            }


        }
    </style>
</head>
<body>
<div class="container">
    <div class="tbl_content">
        <h2>Manage Employees</h2>
        <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 10px; padding: 10px;">
            <form action="${pageContext.request.contextPath}/employees" method="get" style="display: flex; gap: 10px;">
                <input type="text" name="search" placeholder="Search by name or email" value="${param.search}" />
                <select style="background: #023047; color: #fff; border-radius: 12px; padding: 10px 15px;" name="position">
                    <option value="">Select Position</option>
                    <option value="Manager" <c:if test="${param.position == 'Manager'}">selected</c:if>>Manager</option>
                    <option value="Developer" <c:if test="${param.position == 'Developer'}">selected</c:if>>Developer</option>
                    <option value="Designer" <c:if test="${param.position == 'Designer'}">selected</c:if>>Designer</option>
                </select>

                <select style="background: #023047; color: #fff; border-radius: 12px; padding: 10px 15px;" name="department">
                    <option value="">Select Department</option>
                    <option value="HR" <c:if test="${param.department == 'HR'}">selected</c:if>>HR</option>
                    <option value="IT" <c:if test="${param.department == 'IT'}">selected</c:if>>IT</option>
                    <option value="Finance" <c:if test="${param.department == 'Finance'}">selected</c:if>>Finance</option>
                </select>

                <button style="background: #023047; color: #fff; border-radius: 12px; padding: 10px 15px;" type="submit">Search</button>
            </form>

            <a style="background: #008000; color: #fff; border-radius: 12px;" href="${pageContext.request.contextPath}/employees?action=add" class="btn_add">
                <i class="fa fa-plus"></i> Add Employee
            </a>
        </div>

        <table class="tbl">
            <thead>
            <tr>
                <th>SSN</th>
                <th>Name</th>
                <th>Email</th>
                <th>Department</th>
                <th>Position</th>
                <th>Salary</th>
                <th>Number of Children</th>
                <th colspan="2">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty employees}">
                <c:forEach var="employee" items="${employees}">
                    <tr>
                        <td data-label="SSN"><c:out value="${employee.SSN}" /></td>
                        <td data-label="Name"><c:out value="${employee.name}" /></td>
                        <td data-label="Email"><c:out value="${employee.email}" /></td>
                        <td data-label="Department"><c:out value="${employee.department}" /></td>
                        <td data-label="Position"><c:out value="${employee.position}" /></td>
                        <td data-label="Salary"><c:out value="${employee.salary}" /></td>
                        <td data-label="Number of Children"><c:out value="${employee.numberOfChildren}" /></td>
                        <td data-label="Edit">
                            <a href="${pageContext.request.contextPath}/employees?action=edit&id=${employee.id}" class="btn_edit">
                                <i class="fa fa-pencil"></i>
                            </a>
                        </td>
                        <td data-label="Delete">
                            <a href="${pageContext.request.contextPath}/employees?action=delete&id=${employee.id}" class="btn_trash"
                               onclick="return confirm('Are you sure you want to delete this employee?');">
                                <i class="fa fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty employees}">
                <tr>
                    <td colspan="12">No employees found.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
