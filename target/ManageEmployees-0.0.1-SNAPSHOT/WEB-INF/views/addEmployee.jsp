<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Employee</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webressources/masters/styles/styleform.css?v=1.0" />
<style>
    @import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");

    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
        font-family: "Poppins", sans-serif;
    }

    body {
        background-color: #f0f0f0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

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
        color: #023047;
        margin-bottom: 20px;
    }

    form {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
    }

    .form-group {
        display: flex;
        flex-direction: column;
        width: calc(50% - 20px); /* Chaque champ occupe 50% de la largeur avec un espace */
    }

    label {
        font-weight: bold;
        margin-bottom: 5px;
        color: #555;
    }

    input[type="text"],
    input[type="email"],
    input[type="date"],
    input[type="number"] {
        width: 100%;
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
        border: none;
        padding: 10px;
        font-size: 16px;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
        margin-top: 20px;
        width: 100%;
    }

    button:hover {
        background-color: #faa307;
    }

    a {
        display: inline-block;
        text-align: center;
        padding: 10px;
        text-decoration: none;
        color: white;
        background-color: #023047;
        border-radius: 5px;
        margin-top: 10px;
        transition: background-color 0.3s;
        width: 100%;
        text-align: center;
    }

    a:hover {
        background-color: #035e8b;
    }

    @media (max-width: 768px) {
        .form-group {
            width: 100%; /* Pour les petits écrans, chaque champ prend toute la largeur */
        }
    }

</style>
</head>
<body>
<div class="form-container">
    <h2>Add Employee</h2>
    <form action="${pageContext.request.contextPath}/employees?action=create" method="post">

        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required />
        </div>

        <div class="form-group">
            <label for="SSN">SSN:</label>
            <input type="text" id="SSN" name="SSN" required />
        </div>

        <div class="form-group">
            <label for="birth_date">Birth Date:</label>
            <input type="date" id="birth_date" name="birth_date" required />
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required />
        </div>

        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" required />
        </div>

        <div class="form-group">
            <label for="department">Department:</label>
            <input type="text" id="department" name="department" required />
        </div>

        <div class="form-group">
            <label for="position">Position:</label>
            <input type="text" id="position" name="position" required />
        </div>

        <div class="form-group">
            <label for="hire_date">Hire Date:</label>
            <input type="date" id="hire_date" name="hire_date" required />
        </div>

        <div class="form-group">
            <label for="salary">Salary:</label>
            <input type="number" id="salary" name="salary" step="0.01" required />
        </div>

        <div class="form-group">
            <label for="numberOfChildren">Number of Children:</label>
            <input type="number" id="numberOfChildren" name="numberOfChildren" required />
        </div>

        <button type="submit">Add</button>
        <a href="${pageContext.request.contextPath}/employees?action=list">Cancel</a>
    </form>
</div>

</body>
</html>
