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
    <title><layout:put block="title" type="REPLACE">Add new product</layout:put></title>
  </head>
  <body>
    <layout:put block="content" type="REPLACE">
        <div class="container" id="container">
            <div class="testbox">
              <form:form modelAttribute="product" method="POST" >
                <div class="banner">
                  <h1>Add New Product</h1>
                </div>
                <div class="columns">
                  <div class="item">
                    <label for="name">Product name<span>*</span></label>
                    <form:input path="name" id="name" type="text" name="name" required="true"/>
                    <form:errors path="name" cssClass="error"/>
                  </div>

                  <div class="item">
                    <label for="imagePath">Image path<span>*</span></label>
                    <form:input path="imagePath" id="imagePath" type="text" name="imagePath" required="true"/>
                    <form:errors path="imagePath" cssClass="error"/>
                  </div>

                  <div class="item">
                    <label for="price">Price<span>*</span></label>
                    <form:input path="price" id="price" type="number" min="1" step="1" name="price" required="true"/>
                    <form:errors path="price" cssClass="error"/>
                  </div>

                  <div class="item">
                    <label for="category">Category<span>*</span></label>
                    <form:select path="category" multiple="false" id="category" name="category" required="true" items="${categories}" />
                    <form:errors path="category" cssClass="error"/>
                  </div>

                  <div class="item">
                    <label for="availableQuantity">availableQuantity<span>*</span></label>
                    <form:input path="availableQuantity" id="availableQuantity" required="true" type="number" min="0" step="1" name="availableQuantity"/>
                    <form:errors path="availableQuantity" cssClass="error"/>
                  </div>

                  <small style="color:red; text-align: center;">${ErrorMessage}</small>
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