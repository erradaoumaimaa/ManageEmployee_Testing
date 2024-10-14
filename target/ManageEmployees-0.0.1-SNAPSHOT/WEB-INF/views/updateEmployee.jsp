<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Employee</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webressources/masters/styles/styleform.css?v=1.0" />
    <style>
        .form-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 800px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #023047;
        }

        .form-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
        }

        .form-group {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }

        input[type="text"],
        input[type="email"],
        input[type="date"],
        input[type="number"] {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus,
        input[type="email"]:focus,
        input[type="date"]:focus,
        input[type="number"]:focus {
            border-color: #023047;
        }

        button {
            background-color: #ffb703;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
            margin-top: 20px;
        }

        button:hover {
            background-color: #faa307;
        }

        a {
            display: inline-block;
            padding: 10px;
            color: white;
            background-color: #023047;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 10px;
            transition: background-color 0.3s;
        }

        a:hover {
            background-color: #035e8b;
        }

    </style>
</head>
<body>
<div class="form-container">
    <h2>Update Employee Information</h2>
    <c:if test="${not empty errorMessage}">
        <p>${errorMessage}</p>
    </c:if>
    <form action="${pageContext.request.contextPath}/employees?action=update" method="post">
        <input type="hidden" name="id" value="${employee.id}" />

        <div class="form-grid">
            <!-- Champ pour le nom -->
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${employee.name}" required />
            </div>

            <!-- Champ pour le SSN -->
            <div class="form-group">
                <label for="SSN">SSN:</label>
                <input type="text" id="SSN" name="SSN" value="${employee.SSN}" required />
            </div>

            <!-- Champ pour la date de naissance -->
            <div class="form-group">
                <label for="birth_date">Birth Date:</label>
                <input type="date" id="birth_date" name="birth_date" value="${employee.birthDate}" required />
            </div>

            <!-- Champ pour l'email -->
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${employee.email}" required />
            </div>

            <!-- Champ pour le téléphone -->
            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone" value="${employee.phone}" required />
            </div>

            <!-- Champ pour le département -->
            <div class="form-group">
                <label for="department">Department:</label>
                <input type="text" id="department" name="department" value="${employee.department}" required />
            </div>

            <!-- Champ pour le poste -->
            <div class="form-group">
                <label for="position">Position:</label>
                <input type="text" id="position" name="position" value="${employee.position}" required />
            </div>

            <!-- Champ pour la date d'embauche -->
            <div class="form-group">
                <label for="hire_date">Hire Date:</label>
                <input type="date" id="hire_date" name="hire_date" value="${employee.hireDate}" required />
            </div>

            <!-- Champ pour le salaire -->
            <div class="form-group">
                <label for="salary">Salary:</label>
                <input type="number" id="salary" name="salary" step="0.01" value="${employee.salary}" required />
            </div>

            <!-- Champ pour le nombre d'enfants -->
            <div class="form-group">
                <label for="numberOfChildren">Number of Children:</label>
                <input type="number" id="numberOfChildren" name="numberOfChildren" value="${employee.numberOfChildren}" required />
            </div>
        </div>

        <button type="submit">Update</button>
        <a href="${pageContext.request.contextPath}/employees?action=list">Cancel</a>
    </form>
</div>
</body>
</html>
