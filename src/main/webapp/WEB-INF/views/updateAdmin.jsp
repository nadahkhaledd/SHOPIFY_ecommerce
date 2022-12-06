<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<layout:extends name="base">
    <html language="java" xmlns:layout="http://kwonnam.pe.kr/jsp/template-inheritance"
          xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">
    <head>
        <layout:put block="style" type="REPLACE">
            <link href="${pageContext.request.contextPath}/resources/css/newMember.css" rel="stylesheet">
        </layout:put>
        <title><layout:put block="title" type="REPLACE">Add new admin</layout:put></title>
    </head>
    <body>
    <layout:put block="content" type="REPLACE">
        <div class="container" id="container">
            <div class="testbox">
                <form:form modelAttribute="admin" method="POST" >
                    <div class="banner" style="background-color:maroon">
                        <h1>Update Admin</h1>
                    </div>
                    <div class="columns">
                        <div class="item">
                            <label for="firstName"> First Name<span>*</span></label>
                            <form:input required="true" value="${firstName}" path="firstName" id="firstName" type="text" name="firstName"/>
                            <form:errors path="firstName" cssClass="error"/>
                        </div>
                        <div class="item">
                            <label for="lastName"> Last Name<span>*</span></label>
                            <form:input required="true" value="${lastName}" path="lastName" id="lastName" type="text" name="lastName"/>
                            <form:errors path="lastName" cssClass="error"/>
                        </div>

                        <div class="item">
                            <label for="email">Email<span>*</span></label>
                            <form:input path="email" value="${email}" required="required" id="email" type="email" name="email"/>
                            <form:errors path="email" cssClass="error"/>

                        </div>
                        <div class="item">
                            <label for="password">Password<span>*</span></label>
                            <form:input path="password" value="${password}" required="required" id="password" type="text" name="password"/>
                            <form:errors path="password" cssClass="error"/>
                        </div>

                        <div class="item">
                            <label for="dateOfBirth">Birth Date <span>*</span></label>
                            <form:input path="dateOfBirth" id="dateOfBirth" type="date" name="dateOfBirth"
                                        placeholder="yyyy-MM-dd" value="${dateOfBirth}" required="true"
                                        min="1960-01-01" max="${date}"/>
                            <i class="fas fa-calendar-alt" style="color:maroon"></i>
                            <form:errors path="dateOfBirth" cssClass="error"/>
                        </div>

                        <div class="question">
                            <label>gender</label>
                            <div class="question-answer">
                                <div>
                                    <form:radiobutton path="gender" value="male" id="male" name="gender" required="true"/>
                                    <label for="male" class="radio"><span>male</span></label>
                                    <form:errors path="gender" cssClass="error"/>
                                </div>
                                <div>
                                    <form:radiobutton path="gender" value="female" id="female"
                                                      name="gender"/>
                                    <label for="female" class="radio"><span>female</span></label>
                                    <form:errors path="gender" cssClass="error"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <small style="color:red; text-align: center;">${ErrorMessage}</small>
                    <div class="btn-block">
                        <button type="submit" style="background: maroon">Update</button>
                    </div>
                </form:form>
            </div>
            <div>
    </layout:put>
    </body>
    </html>
</layout:extends>