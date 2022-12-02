<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<html xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">
  <head>
    <title>Add new admin</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <!-- Customized Bootstrap Stylesheet -->
    <link href="${pageContext.request.contextPath}/resources/css/newMember.css" rel="stylesheet">
  </head>
  <body>
    <div class="container" id="container">
        <div class="testbox">
          <form:form modelAttribute="product" method="POST" >
            <div class="banner">
              <h1>Add New Product</h1>
            </div>
            <div class="columns">
              <div class="item">
                <label for="name">Product name<span>*</span></label>
                <form:input path="name" id="name" type="text" name="name"/>
                <form:errors path="name" cssClass="error"/>
              </div>
              <div class="item">
                <label for="imagePath">Image path<span>*</span></label>
                <form:input path="imagePath" id="imagePath" type="text" name="imagePath"/>
                <form:errors path="imagePath" cssClass="error"/>
              </div>

              <div class="item">
                <label for="price">Price<span>*</span></label>
                <form:input path="price" id="price" type="number" min="1" step="1" name="price"/>
                <form:errors path="price" cssClass="error"/>
              </div>

              <div class="item">
                <label for="category">Category<span>*</span></label>
                <form:select path="category" id="category" name="category" items="${categories}" />
                <form:errors path="category" cssClass="error"/>
              </div>

              <div class="item">
                <label for="availableQuantity">availableQuantity<span>*</span></label>
                <form:input path="availableQuantity" id="availableQuantity" type="number" min="0" step="1" name="availableQuantity"/>
                <form:errors path="availableQuantity" cssClass="error"/>
              </div>

            <div class="btn-block">
              <button type="submit" >Submit</button>
            </div>
          </form:form>
        </div>
    <div>
  </body>
</html>