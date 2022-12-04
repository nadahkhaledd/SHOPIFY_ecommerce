<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<layout:extends name="base">
<html xmlns:layout="http://kwonnam.pe.kr/jsp/template-inheritance" xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:spring="http://www.springframework.org/tags">
    <head>
        <layout:put block="style" type="REPLACE">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        </layout:put>
        <title><layout:put block="title" type="REPLACE">Categories</layout:put></title>
    </head>

    <body>
        <layout:put block="content" type="REPLACE">
            <div class="container">
				<table class="table table-striped">
				   <caption>All Categories</caption>
				   <thead>
				        <tr>
				            <th>name</th>
				            <th>image path</th>
				        </tr>
				   </thead>
				   <tbody>
				        <c:forEach items="${categories}" var="category">
				            <tr>
				                <td>${category.name}</td>
				                <td>${category.imagePath}</td>

				                <th><a href="" class="btn btn-danger">Delete</a></th>
				                <th>
				                <th><a href="" class="btn btn-danger">Update</a></th>
				            </tr>
				        </c:forEach>
				   </tbody>
				</table>
				<div>
				    <a class="btn btn-success" href="/e-commerce/admin/addCategory">Add</a>
				</div>
    		</div>

		   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
		   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.js" integrity="sha512-CX7sDOp7UTAq+i1FYIlf9Uo27x4os+kGeoT7rgwvY+4dmjqV0IuE/Bl5hVsjnQPQiTOhAX1O2r2j5bjsFBvv/A==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        </layout:put>
    </body>
</html>
</layout:extends>