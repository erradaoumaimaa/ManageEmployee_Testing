<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Apply for Job</title>
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
        max-width: 500px; /* Limité à une largeur de 500px pour un formulaire compact */
    }

    h2 {
        text-align: center;
        color: #023047;
        margin-bottom: 20px;
    }

    form {
        display: flex;
        flex-direction: column;
    }

    .form-group {
        display: flex;
        flex-direction: column;
        margin-bottom: 15px;
    }

    label {
        font-weight: bold;
        margin-bottom: 5px;
        color: #555;
    }

    input[type="text"],
    input[type="email"],
    input[type="tel"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
        font-size: 14px;
        transition: border-color 0.3s;
    }

    input[type="text"]:focus,
    input[type="email"]:focus,
    input[type="tel"]:focus {
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

    @media (max-width: 768px) {
        .form-container {
            padding: 20px;
        }
    }

</style>
</head>
<body>
<div class="form-container">
    <h2>Apply for Job</h2>
    <form action="/ManageEmployees/applyJob" method="post">
        <input type="hidden" name="jobOfferId" value="${jobOfferId}">

        <div class="form-group">
            <label for="name">Nom:</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="phone">Téléphone:</label>
            <input type="text" id="phone" name="phone" required>
        </div>

        <button type="submit">Apply</button>
    </form>
</div>
</body>
</html>
