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
    <title><layout:put block="title" type="REPLACE">Update category</layout:put></title>
  </head>
  <body>
     <layout:put block="content" type="REPLACE">
        <div class="container" id="container">
            <div class="testbox">
              <form:form modelAttribute="category" method="POST">
                <div class="banner" style="background-color:maroon">
                  <h1>Update Category <i>${category.name}</i></h1>
                </div>

                <div class="columns">
                  <div class="item">
                    <label for="name">Category name<span>*</span></label>
                    <form:input path="name" value="${name}" id="name" type="text" name="name"/>
                    <form:errors path="name" cssClass="error"/>
                  </div>
                  <div class="item">
                    <label for="imagePath">Image path<span>*</span></label>
                    <form:input path="imagePath" value="${imagePath}" id="imagePath" type="text" name="imagePath"/>
                    <form:errors path="imagePath" cssClass="error"/>
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