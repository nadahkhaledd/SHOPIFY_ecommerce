<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">
  <head>
    <title>Add new admin</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <!-- Customized Bootstrap Stylesheet -->
    <link href="${pageContext.request.contextPath}/resources/css/newadmin.css" rel="stylesheet">
  </head>
  <body>
    <div class="container" id="container">
        <div class="testbox">
          <form:form modelAttribute="admin" method="POST" >
            <div class="banner">
              <h1>New Admin Registration</h1>
            </div>
            <div class="colums">
              <div class="item">
                <label for="firstName"> First Name<span>*</span></label>
                <form:input path="firstName" id="firstName" type="text" name="firstName"/>
              </div>
              <div class="item">
                <label for="lastName"> Last Name<span>*</span></label>
                <form:input path="lastName" id="lastName" type="text" name="lastName"/>
              </div>

              <div class="item">
                <label for="email">Email<span>*</span></label>
                <form:input path="email" id="email" type="email" name="email"/>
              </div>
              <div class="item">
                <label for="password">Password<span>*</span></label>
                <form:input path="password" id="password" type="text" name="password"/>
              </div>

               <div class="item">
                    <label for="dateOfBirth">Birth Date <span>*</span></label>
                    <form:input path="dateOfBirth" id="dateOfBirth" type="date" name="dateOfBirth"
                     placeholder="dd-mm-yyyy" value=""
                     min="1960-01-01" max="2030-12-31"/>
                    <i class="fas fa-calendar-alt"></i>
               </div>

               <div class="question">
                    <label>gender</label>
                    <div class="question-answer">
                        <div>
                            <form:radiobutton path="gender" value="male" id="male" name="gender"/>
                            <label for="male" class="radio"><span>male</span></label>
                        </div>
                        <div>
                            <form:radiobutton path="gender" value="female" id="female" name="gender"/>
                            <label for="female" class="radio"><span>female</span></label>
                        </div>
                    </div>
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