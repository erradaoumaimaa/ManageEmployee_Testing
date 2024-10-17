<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Demande de Congé</title>
</head>
<body>
<h1>Demande de Congé</h1>

<% String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) { %>
<div style="color: red;"><%= errorMessage %></div>
<% } %>

<form action="${pageContext.request.contextPath}/vacation" method="post" enctype="multipart/form-data">
    <label for="employeeId">ID de l'employé:</label>
    <input type="text" id="employeeId" name="employeeId" required/><br/>

    <label for="startDate">Date de début:</label>
    <input type="date" id="startDate" name="startDate" required/><br/>

    <label for="endDate">Date de fin:</label>
    <input type="date" id="endDate" name="endDate" required/><br/>

    <label for="reason">Raison:</label>
    <input type="text" id="reason" name="reason" required/><br/>

    <label for="document">Télécharger le document:</label>
    <input type="file" id="document" name="document" required/><br/>

    <input type="submit" value="Soumettre la demande"/>
</form>

</body>
</html>
