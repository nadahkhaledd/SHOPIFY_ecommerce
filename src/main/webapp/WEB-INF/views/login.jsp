<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="ltr" xmlns:spring="http://www.springframework.org/tags"
      xmlns:form="http://www.springframework.org/tags/form">

<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet">
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
            <h1>Sign in</h1>
            <form:input path="email" id="email" type="email" name="email" required="true"/>
            <form:input path="password" id="password" type="password" name="password" required="true"/>
            <small style="color:red; margin-bottom:10px">${error}</small>
            <button type="submit" style="padding-top:10px">Sign In</button>
        </form:form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-right">
                <h1>Welcome Back!</h1>
                <p>To keep connected with us please login with your personal info</p>
                <h5><a style="color: black" href="/e-commerce/register">or signup as new customer</a></h5>
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
	const signInButton = document.getElementById('signIn');
	const container = document.getElementById('container');


	signInButton.addEventListener('click', () => {
		container.classList.remove("right-panel-active");
	});


</script>
</body>
</html>