<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="ltr"  xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
  background-color: black;
}

* {
  box-sizing: border-box;
}

/* Add padding to containers */
.container {
  padding: 16px;
  background-color: white;
}

/* Full-width input fields */
input[type=email], input[type=password],input[type=text] ,input[date]{
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Overwrite default styles of hr */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* Set a style for the submit button */
.registerbtn {
  background-color: #04AA6D;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

.registerbtn:hover {
  opacity: 1;
}

/* Add a blue text color to links */
a {
  color: dodgerblue;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
  background-color: #f1f1f1;
  text-align: center;
}
</style>
</head>
<body>

<form:form modelAttribute="user" method="POST">
  <div class="container">
    <h1>Register</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>

    <label><b>First name</b></label>
    <form:input path="firstName" id="firstName" type="text" name="firstName"/>

    <label><b>Last name name</b></label>
    <form:input path="lastName" id="lastName" type="text" name="lastName"/>

    <label><b>Email</b></label>
    <form:input path="email" id="email" type="email" name="email"/>

    <label><b>Password</b></label>
    <form:input path="password" id="password" type="text" name="password"/>

     <label><b>Password</b></label>
     <form:input path="dateOfBirth" id="dateOfBirth" type="date" name="dateOfBirth"/>

  <div>
      <form:radiobutton path="gender" value="male" id="male" name="gender"/>
      <label for="male" class="radio"><span>male</span></label>
      <form:errors path="gender" cssClass="error"/>
  </div>
  <div>
      <form:radiobutton path="gender" value="female" id="female" name="gender"/>
      <label for="female" class="radio"><span>female</span></label>
      <form:errors path="gender" cssClass="error"/>
  </div>

    <hr>
    <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>

    <button type="submit" class="registerbtn">Register</button>
  </div>
  <div class="container signin">
    <p>Already have an account? <a href="#">Sign in</a>.</p>
  </div>
</form:form>

</body>
