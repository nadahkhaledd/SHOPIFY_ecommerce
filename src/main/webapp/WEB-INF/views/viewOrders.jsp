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
        <td>Order date</td>
        <td>Status</td>
        <td>Total</td>
        <td>View details</td>
    </tr>
    </thead>
    <tbody>
    <c:if test="${!empty addresses}">
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.date}</td>
                <td>${order.status}</td>
                <td>${order.total}</td>
                <td><a role="button" type="button" class="btn btn-dark" href="/e-commerce/orderdetails/view/${order.id}?id=1">View details</a></td>
            </tr>
    </c:forEach>

    </c:if>
    </tbody>

</table>
</body>
</html>