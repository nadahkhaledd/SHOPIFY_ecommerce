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
        <td>Product name</td>
        <td>Product image</td>
        <td>Product price</td>
    </tr>
    </thead>
    <tbody>
    <c:if test="${!empty orderDetails}">
        <c:forEach var="orderDetail" items="${orderDetails}">
            <tr>
                <td>${orderDetail.productName}</td>
                <td><img src="${orderDetail.productImage}" alt="" style="width: 50px;"></td>
                <td>${orderDetail.productPrice}</td>
            </tr>
    </c:forEach>

    </c:if>
    </tbody>

</table>
</body>
</html>