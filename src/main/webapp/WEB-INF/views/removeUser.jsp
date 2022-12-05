<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<layout:extends name="base">
<html xmlns:layout="http://kwonnam.pe.kr/jsp/template-inheritance" xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">
  <head>
    <layout:put block="style" type="REPLACE">
        <link href="${pageContext.request.contextPath}/resources/css/newMember.css" rel="stylesheet">
    </layout:put>
    <title><layout:put block="title" type="REPLACE">Remove user</layout:put></title>
  </head>
  <body>
     <layout:put block="content" type="REPLACE">
        <div class="container" id="container">
            <div class="testbox">
              <form:form modelAttribute="fields" method="POST">
                <div class="banner">
                  <h1>Remove User</h1>
                </div>
                <div class="columns">

                  <div class="item">
                    <label for="userType">user type<span>*</span></label>
                    <form:select path="userType" id="userType" name="userType" items="${userTypes}" />
                    <form:errors path="userType" cssClass="error"/>
                  </div>

                  <div class="item">
                    <label for="userID">user id<span>*</span></label>
                    <form:input path="userID" id="userID" type="text" name="userID" required="true"/>
                    <form:errors path="userID" cssClass="error"/>
                  </div>

                  <div class="item">
                    <label for="userEmail">user email<span>*</span></label>
                    <form:input path="userEmail" id="userEmail" type="email" name="userEmail" required="true"/>
                    <form:errors path="userEmail" cssClass="error"/>
                  </div>

                </div>

                <div class="btn-block">
                  <button type="submit" >Submit</button>
                </div>
              </form:form>
            </div>
        <div>
    </layout:put>
  </body>
</html>
</layout:extends>