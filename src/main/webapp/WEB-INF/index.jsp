<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page d'accueil</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/styles.css">
</head>
<body>

<h1>Bienvenue sur le système de gestion</h1>

<ul>
    <li><a href="<%=request.getContextPath()%>/employees?action=list">Gestion des employés</a></li>
    <li><a href="<%=request.getContextPath()%>/vacation?action=list">Liste des congés</a></li>
    <li><a href="<%=request.getContextPath()%>/vacation">Demande de congé</a></li>
    <li><a href="<%=request.getContextPath()%>/offres?action=list">Liste des offres</a></li>
    <li><a href="<%=request.getContextPath()%>/offres?action=manage">Gestion des offres</a></li>
</ul>


</body>
</html>
