<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Spring MVC RSS Content Negotiation Example</title>
</head>
<body>

<h1>Spring MVC RSS Content Negotiation Example</h1>

<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Link</th>
    </tr>
    <c:forEach var="d" items="${documents}">
        <tr>
            <td>${d.feedId}</td>
            <td>${d.title}</td>
            <td>${d.description}</td>
            <td>${d.link}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>