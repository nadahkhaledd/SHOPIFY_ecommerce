<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<layout:extends name="base">
<html xmlns:layout="http://kwonnam.pe.kr/jsp/template-inheritance" xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">
  <head>
    <title><layout:put block="title" type="REPLACE">Add new Category</layout:put></title>
  </head>
  <body>
     <layout:put block="content" type="REPLACE">
        <div class="container" id="container">
            <div class="testbox">
              <form:form modelAttribute="category" method="POST">
                <div class="banner">
                  <h1>Add New Category</h1>
                </div>
                <div class="columns">

                  <div class="item">
                    <label for="name">Category name<span>*</span></label>
                    <form:input path="name" id="name" type="text" name="name"/>
                    <form:errors path="name" cssClass="error"/>
                  </div>
                  <div class="item">
                    <label for="imagePath">Image path<span>*</span></label>
                    <form:input path="imagePath" id="imagePath" type="text" name="imagePath"/>
                    <form:errors path="imagePath" cssClass="error"/>
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