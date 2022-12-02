<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">
  <head>
    <title>Add new Category</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <!-- Customized Bootstrap Stylesheet -->
    <link href="${pageContext.request.contextPath}/resources/css/newMember.css" rel="stylesheet">
  </head>
  <body>
    <div class="container" id="container">
        <div class="testbox">
          <form:form modelAttribute="category" method="POST" >
            <div class="banner">
              <h1>New Admin Registration</h1>
            </div>
            <div class="columns">

              <div class="item">
                <label for="name"> First Name<span>*</span></label>
                <form:input path="name" id="name" type="text" name="name"/>
              </div>
              <div class="item">
                <label for="imagePath"> Last Name<span>*</span></label>
                <form:input path="imagePath" id="imagePath" type="text" name="imagePath"/>
              </div>
            </div>

            <h2>Terms and Conditions</h2>
            <div class="btn-block">
              <button type="submit" >Submit</button>
            </div>
          </form:form>
        </div>
    <div>
  </body>
</html>