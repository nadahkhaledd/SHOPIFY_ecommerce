<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<layout:extends name="customerBase">
<html xmlns:layout="http://kwonnam.pe.kr/jsp/template-inheritance" xmlns:form="http://www.springframework.org/tags/form">

    <head>
        <head>
            <title><layout:put block="title" type="REPLACE">Addresses</layout:put></title>
            <layout:put block="style" type="REPLACE"></layout:put>
        </head>
    </head>

    <body>
        <layout:put block="content" type="REPLACE">


            <!-- Page Header Start -->
            <div class="container-fluid bg-secondary mb-5">
                <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
                    <h1 class="font-weight-semi-bold text-uppercase mb-3">Addresses</h1>
                    <div class="d-inline-flex">
                        <p class="m-0"><a href="${pageContext.request.contextPath}/user/profile">Profile</a></p>
                        <p class="m-0 px-2">-</p>
                        <p class="m-0">Addresses</p>
                    </div>
                </div>
            </div>
            <!-- Page Header End -->


            <!-- Addresses Start -->
            <div class="addresses-container" style="    height: auto;
                                                      margin-bottom: 20px;
                                                      padding: 20px 50px 20px 50px;">
                        <div class="addresses-main">
                                <div class="addresses-card" style="    background-color: #fff;
                                                                     border-radius: 18px;
                                                                     box-shadow: 1px 1px 8px 0 grey;
                                                                     height: auto;
                                                                     margin-bottom: 20px;
                                                                     padding: 20px 0 20px 50px;">
                                    <div class="addresses-card-body" style="text-align: center;">
                                        <h2 style="color: #333;
                                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                                            font-size: 24px;
                                            margin-bottom: 10px;">  Address book</h2>
                                        <div class="addresses-card-body-table">
                                        <button type="button" class="add-button"
                                            onclick="location.href='${pageContext.request.contextPath}/address/add'"
                                            style="outline:0;border:0;background-color: #fff;">
                                        <i class="fa fa-pen fa-xs"></br>Add new address</i>
                                        </button>
                                        <table>
                                            <tbody>
                                            <c:if test="${!empty addresses}">
                                                <c:forEach var="address" items="${addresses}">
                                                <tr>
                                                    <td>Street</td>
                                                    <td>:</td>
                                                    <td>${address.street}</td>
                                                </tr>
                                                <tr>
                                                    <td>Building number</td>
                                                    <td>:</td>
                                                    <td>${address.buildingNumber}</td>
                                                </tr>
                                                <tr>
                                                    <td>City</td>
                                                    <td>:</td>
                                                    <td>${address.city}</td>
                                                </tr>
                                                <tr>
                                                <td><button type="button" class="edit-button"
                                                    onclick="location.href='${pageContext.request.contextPath}/address/update/${address.id}'"
                                                    style="outline:0;border:0;background-color: #fff;">
                                                <i class="fa fa-pen fa-xs"></br>Edit</i>
                                                </button></td>
                                                <td></td>
                                                <td><button type="button" class="delete-button"
                                                    onclick="location.href='${pageContext.request.contextPath}/address/delete/${address.id}'"
                                                    style="outline:0;border:0;background-color: #fff;">
                                                <i class="fa fa-trash fa-xs"></br>Delete</i>
                                                </button></td>
                                                </tr>
                                            </c:forEach>
                                            </c:if>
                                            </tbody>
                                        </table>
                                        </div>
                                    </div>
                                </div>
                        </div>
            </div>
                        <!-- Addresses End -->
        </layout:put>
    </body>
</html>
</layout:extends>