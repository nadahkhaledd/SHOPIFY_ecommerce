<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">
<head>
    <title>Registration Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <style>
        span.error {
            color: red;
            display: inline-block;
        }
    </style>

</head>
<body>
<div class="container">
    <form:form modelAttribute="address" method="post">
        <div class="from-row md-4">
            <label for="street">Street</label>
            <form:input path="street" cssClass="form-control" required="true" id="street"/>
            <form:errors path="street" cssClass="error"/>
        </div>
        <div class="from-row">
            <label for="buildingNumber">Building Number </label>
            <form:input path="buildingNumber" required="true" cssClass="form-control" id="buildingNumber" type="number" min="0" step="1"/>
            <form:errors path="buildingNumber" cssClass="error"/>
        </div>

        <div>
            <label for="city">City </label>
            <form:input path="city" required="true" cssClass="form-control" id="city" />
            <form:errors path="city" cssClass="error"/>
        </div>
        <br>
        <input type="submit" value="add address">
    </form:form>

</div>
</body>
</html>