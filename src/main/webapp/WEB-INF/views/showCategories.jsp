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
            <div style="display: flex;
                              justify-content: center;
                              align-items: center;
                              height: inherit;
                              padding: 20px;">
                <div style="padding: 0;
                                  margin: 0;
                                  outline: none;
                                  font-family: Arial, Helvetica, sans-serif;
                                  font-size: 14px;
                                  color: #666;
                                  line-height: 22px;
                                  width: 100%;
                                  padding: 20px;
                                  border-radius: 6px;
                                  background: #fff;
                                  box-shadow: 0 0 8px  #669999;">
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
                                        <td><a href="${category.imagePath}">${category.imagePath}</a></td>

                                        <th><a href="/e-commerce/admin/deleteCategory/${category.id}" class="btn btn-danger">Delete</a></th>
                                        <th>
                                        <th><a href="/e-commerce/admin/updateCategory/${category.id}" class="btn" style="background: #purple; color:white">Update</a></th>
                                    </tr>
                                </c:forEach>
                           </tbody>
                        </table>
                        <div>
                            <a class="btn btn-success" href="/e-commerce/admin/addCategory">Add</a>
                        </div>
                    </div>
                </div>
            </div>

		   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
		   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.js" integrity="sha512-CX7sDOp7UTAq+i1FYIlf9Uo27x4os+kGeoT7rgwvY+4dmjqV0IuE/Bl5hVsjnQPQiTOhAX1O2r2j5bjsFBvv/A==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        </layout:put>
    </body>
</html>
</layout:extends>