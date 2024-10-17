<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Vacation List</title>
  <style>
    table {
      width: 100%;
      border-collapse: collapse;
    }
    th, td {
      border: 1px solid #ddd;
      padding: 8px;
      text-align: left;
    }
    th {
      background-color: #f2f2f2;
    }
    .message {
      margin: 10px 0;
      padding: 10px;
      border-radius: 5px;
    }
    .success {
      color: green;
      background-color: #d4edda;
    }
    .error {
      color: red;
      background-color: #f8d7da;
    }
  </style>
</head>
<body>
<h1>Vacation List</h1>


<c:if test="${not empty successMessage}">
  <div class="message success">${successMessage}</div>
</c:if>
<c:if test="${not empty errorMessage}">
  <div class="message error">${errorMessage}</div>
</c:if>

<table>
  <thead>
  <tr>
    <th>Employee</th>
    <th>Start Date</th>
    <th>End Date</th>
    <th>Reason</th>
    <th>Document</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="vacation" items="${vacations}">
    <tr>
      <td>${vacation.employee.name}</td>
      <td>${vacation.startDate}</td>
      <td>${vacation.endDate}</td>
      <td>${vacation.reason}</td>
      <td><a href="${pageContext.request.contextPath}/uploads/${vacation.document}">View Document</a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>


<br>
<a href="#">Request New Vacation</a>
</body>
</html>
