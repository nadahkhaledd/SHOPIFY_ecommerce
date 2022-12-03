<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>all users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body class="container">
<table class="table">
    <thead class="table table-dark">
    <tr>
        <td>Street</td>
        <td>Building number</td>
        <td>City</td>
        <td>Update</td>
        <td>Delete</td>
    </tr>
    </thead>
    <tbody>
    <c:if test="${!empty addresses}">
        <c:forEach var="address" items="${addresses}">
            <tr>
                <td>${address.street}</td>
                <td>${address.buildingNumber}</td>
                <td>${address.city}</td>
                <td><a role="button" type="button" class="btn btn-dark" href="/e-commerce/address/update/1?id=${address.id}">Update</a></td>
                <td><a role="button" type="button" class="btn btn-dark" href="/e-commerce/address/delete/1?id=${address.id}">Delete</a></td>
            </tr>
    </c:forEach>

    </c:if>
    </tbody>

</table>
</body>
</html>