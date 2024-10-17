<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>Error Occurred</h1>
<p><c:out value="${errorMessage}" /></p>
<a href="home">Return to Vacation Page</a>
</body>
</html>
