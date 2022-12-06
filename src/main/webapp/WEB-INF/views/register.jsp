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
            <form:input path="firstName" id="firstName" type="text" name="firstName" placeholder="First Name"/>
            <form:input path="lastName" id="lastName" type="text" name="lastName" placeholder="Last Name"/>
            <form:input path="email" id="email" type="email" name="email" placeholder="Email"/>
            <form:input path="password" id="password" type="text" name="password" placeholder="Password"/>
            <form:input path="dateOfBirth" id="dateOfBirth" type="date" name="dateOfBirth" placeholder="Date of birth"/>
            <ul class="nav">
                <li><a href="#clients">
                    <form:radiobutton path="gender" value="male" id="male" name="gender"/>
                    <label for="male" class="radio"><span>male</span></label>
                </a></li>
                <li><a href="#contact"></a>
                    <form:radiobutton path="gender" value="female" id="female" name="gender"/>
                    <label for="female" class="radio"><span>female</span></label>
                </li>
            </ul>

            <button>Sign Up</button>
        </form:form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-right">
                <h1>Hello, Friend!</h1>
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