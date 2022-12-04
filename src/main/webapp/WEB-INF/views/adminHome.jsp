<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<layout:extends name="base">
<html xmlns:layout="http://kwonnam.pe.kr/jsp/template-inheritance" xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">
  <head>
    <layout:put block="style" type="REPLACE">
        <link href="${pageContext.request.contextPath}/resources/css/newMember.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/homeAdmin.css" rel="stylesheet">
    </layout:put>
    <title><layout:put block="title" type="REPLACE">Admin Home</layout:put></title>
  </head>

  <body>
       <layout:put block="content" type="REPLACE">
            <div class="container" id="container">
            <div class="welcome" style="text-align:center;
                                                    float: center;
                                                    margin-bottom: 50px
                                                    padding-top: 20px;
                                                    padding-bottom: 20px;
                                                    margin-top: 50px;">
                <h2>Hello ${name}</h2>
            </div>
                <div class="row">
                  <div class="column" style="float: left;
                                               width: 25%;
                                               padding: 25px 10px;">
                    <div class="card">
                        <h4>Add new admin</h4>
                        <a role="button" type="button" class="btn btn-dark" href="/e-commerce/admin/addAdmin" style="

                                    height: 40px;
                                    display: inline;
                                    align-items: center;
                                    color: antiquewhite;
                                    justify-content: space-between;

                                    background-image: linear-gradient(to right, cadetblue 0%, #79CBCA 51%, #77A1D3 100%);
                                    border: none;
                                    transition: 0.5s;
                                    background-size: 200% auto;">Go</a>
                    </div>
                  </div>

                  <div class="column" style="float: left;
                                               width: 25%;
                                               padding: 25px 10px;">
                    <div class="card">
                        <h4>Add new category</h4>
                        <a role="button" type="button" class="btn btn-dark" href="/e-commerce/admin/addCategory" style="

                                  height: 40px;
                                  display: inline;
                                  align-items: center;
                                  color: antiquewhite;
                                  justify-content: space-between;

                                  background-image: linear-gradient(to right, cadetblue 0%, #79CBCA 51%, #77A1D3 100%);
                                  border: none;
                                  transition: 0.5s;
                                  background-size: 200% auto;">Go</a>
                    </div>
                  </div>

                  <div class="column" style="float: left;
                                               width: 25%;
                                               padding: 25px 10px;">
                    <div class="card">
                        <h4>Show all categories</h4>
                        <a role="button" type="button" class="btn btn-dark" href="/e-commerce/admin/showCategories" style="

                                 height: 40px;
                                 display: inline;
                                 align-items: center;
                                 color: antiquewhite;
                                 justify-content: space-between;

                                 background-image: linear-gradient(to right, cadetblue 0%, #79CBCA 51%, #77A1D3 100%);
                                 border: none;
                                 transition: 0.5s;
                                 background-size: 200% auto;">Go</a>
                    </div>
                  </div>

                  <div class="column" style="float: left;
                                               width: 25%;
                                               padding: 25px 10px;">
                    <div class="card">
                        <h4>Add new product</h4>
                        <a role="button" type="button" class="btn btn-dark" href="/e-commerce/admin/addProduct" style="

                                 height: 40px;
                                 display: inline;
                                 align-items: center;
                                 color: antiquewhite;
                                 justify-content: space-between;

                                 background-image: linear-gradient(to right, cadetblue 0%, #79CBCA 51%, #77A1D3 100%);
                                 border: none;
                                 transition: 0.5s;
                                 background-size: 200% auto;">Go</a>
                    </div>
                  </div>

                  <div class="column" style="float: left;
                                               width: 25%;
                                               padding: 25px 10px;">
                    <div class="card">
                        <h4>Remove User</h4>
                        <a role="button" type="button" class="btn btn-dark" href="/e-commerce/admin/removeUser" style="

                                  height: 40px;
                                  display: inline;
                                  align-items: center;
                                  color: antiquewhite;
                                  justify-content: space-between;

                                  background-image: linear-gradient(to right, cadetblue 0%, #79CBCA 51%, #77A1D3 100%);
                                  border: none;
                                  transition: 0.5s;
                                  background-size: 200% auto;">Go</a>
                    </div>
                  </div>


                </div>
            <div>
       </layout:put>
  </body>
</html>
</layout:extends>