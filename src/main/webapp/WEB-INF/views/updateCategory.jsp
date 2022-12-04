<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<layout:extends name="base">
<html xmlns:layout="http://kwonnam.pe.kr/jsp/template-inheritance" xmlns:form="http://www.springframework.org/tags/form" xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:spring="http://www.springframework.org/tags">
    <head>
        <layout:put block="style" type="REPLACE">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        </layout:put>
        <title><layout:put block="title" type="REPLACE">Update category</layout:put></title>
    </head>

    <body>
        <layout:put block="content" type="REPLACE">
            <div class="container">
				<form:form  method="post"  modelAttribute="category">
					<fieldset class="form-group">
						<label path="name"> Category name </label>
						<form:input type="text" value="${name}" path="name" />
						<form:errors path="name" cssClass="error" />
					</fieldset>
					<fieldset class="form-group">
						<label path="imagePath">Image path</label>
						<form:input path="imagePath" value="${imagePath}" type="text"/>
						<form:errors path="imagePath" cssClass="text-warning" />
					</fieldset>
					<input type="submit" value="submit" class="btn btn-success"/>
				</form:form>
			</div>
		</layout:put>
    </body>
</html>
</layout:extends>