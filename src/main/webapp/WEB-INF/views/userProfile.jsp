<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<layout:extends name="customerBase">
<html xmlns:layout="http://kwonnam.pe.kr/jsp/template-inheritance" xmlns:form="http://www.springframework.org/tags/form">

    <head>
        <head>
            <title><layout:put block="title" type="REPLACE">Profile</layout:put></title>
            <layout:put block="style" type="REPLACE"></layout:put>
        </head>
    </head>

    <body>
        <layout:put block="content" type="REPLACE">

            <!-- Navbar End -->


            <!-- Page Header Start -->
            <div class="container-fluid bg-secondary mb-5">
                <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
                    <h1 class="font-weight-semi-bold text-uppercase mb-3">Profile</h1>
                </div>
            </div>
            <!-- Page Header End -->


            <!-- Profile Start -->
            <div class="profile-container" style="    height: auto;
                                                      margin-bottom: 20px;
                                                      padding: 20px 50px 20px 50px;">
                        <div class="profile-main">
                                <div class="profile-card" style="    background-color: #fff;
                                                                     border-radius: 18px;
                                                                     box-shadow: 1px 1px 8px 0 grey;
                                                                     height: auto;
                                                                     margin-bottom: 20px;
                                                                     padding: 20px 0 20px 50px;">
                                    <div class="profile-card-body" style="text-align: center;">
                                        <h2 style="color: #333;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-size: 24px;
    margin-bottom: 10px;">  Hello ${userInfo.firstName} ${userInfo.lastName}  </h2>


                                        <button type="button" class="address-button"
                                            onclick="location.href = '${pageContext.request.contextPath}/address/view'"
                                            style="outline:0;border:0;background-color: #fff;">
                                        <i class="fa fa-address-book fa-xs"></br>Addresses</i>
                                        </button>
                                        <button type="button" class="orders-button"
                                            onclick="location.href='${pageContext.request.contextPath}/orders/view'"
                                            style="outline:0;border:0;background-color: #fff;">
                                        <i class="fa fa-truck fa-xs"></br>Orders</i>
                                        </button>
                                        <div class="profile-card-body-table">
                                        <table>
                                            <tbody>
                                                <tr>
                                                    <td>Name</td>
                                                    <td>:</td>
                                                    <td>${userInfo.firstName} ${userInfo.lastName}</td>
                                                </tr>
                                                <tr>
                                                    <td>Email</td>
                                                    <td>:</td>
                                                    <td>${userInfo.email}</td>
                                                </tr>
                                                <tr>
                                                    <td>Gender</td>
                                                    <td>:</td>
                                                    <td>${userInfo.gender}</td>
                                                </tr>
                                                <tr>
                                                    <td>Date of birth</td>
                                                    <td>:</td>
                                                    <td><fmt:formatDate type="date" value="${userInfo.dateOfBirth}" /></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        </div>
                                    </div>
                                </div>
                        </div>
            </div>
                        <!-- Profile End -->
        </layout:put>
    </body>
</html>
</layout:extends>