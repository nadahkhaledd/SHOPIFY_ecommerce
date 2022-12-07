<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="ltr" xmlns:spring="http://www.springframework.org/tags"
      xmlns:form="http://www.springframework.org/tags/form">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<head>
    <meta charset="UTF-8">
    <title>Register</title>

    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet">
    <style>
.nav {
          list-style-type: none;
          text-align: center;
          margin: 0;
          padding: 0;
        }
        .nav li {
          display: inline-block;
          font-size: 20px;
          padding: 20px;
        }
</style>

</head>

<body>
<!-- partial:index.partial.html -->
<h2>SHOPIFY</h2>
<div class="container" id="container">
    <div class="form-container sign-up-container">
        <form action="#">
            <h1>Create Account</h1>
            <button>Sign Up</button>
        </form>
    </div>
    <div class="form-container sign-in-container">
        <form:form modelAttribute="user" method="POST">
            <h1>Sign Up</h1>
            <form:input path="firstName" id="firstName" type="text" name="firstName" placeholder="First Name" required="required" />
            <form:input path="lastName" id="lastName" type="text" name="lastName" placeholder="Last Name" required="required"/>
            <form:input path="email" id="email" type="email" name="email" placeholder="Email" required="required"/>
            <form:input path="password" id="password" type="password" name="password" placeholder="Password" required="required"/>
            <form:input path="dateOfBirth" id="dateOfBirth" type="date" name="dateOfBirth" placeholder="Date of birth" min="1960-01-01" max="${date}" required="required"/>
            <ul class="nav">
                <li>
                    <form:radiobutton path="gender" value="male" id="male" name="gender" required="true"/>
                    <label for="male" class="radio"><span>male</span></label>
                </a></li>
                <li>
                    <form:radiobutton path="gender" value="female" id="female" name="gender"/>
                    <label for="female" class="radio"><span>female</span></label>
                </li>
            </ul>
            <small style="color:red; text-align: center">${registerError}</small>
            <button type="submit">Sign Up</button>
        </form:form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-right">
                <h1>Hello, Friend!</h1>
                <h5>already have an account? <a style="color: black" href="/e-commerce/login">Login</a></h5>

            </div>
        </div>
    </div>
</div>

<footer>
    <p>
        _VOIS E-Commerce website
    </p>
</footer>
<!-- partial -->
<script type="text/javascript">
  	const signUpButton = document.getElementById('signUp');
	const container = document.getElementById('container');

	signUpButton.addEventListener('click', () => {
		container.classList.add("right-panel-active");
	});
</script>

</body>