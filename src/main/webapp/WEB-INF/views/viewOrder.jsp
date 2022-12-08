<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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


            <!-- Page Header Start -->
            <div class="container-fluid bg-secondary mb-5">
                <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
                    <h1 class="font-weight-semi-bold text-uppercase mb-3">Orders</h1>
                    <div class="d-inline-flex">
                        <p class="m-0"><a href="${pageContext.request.contextPath}/user/profile">Profile</a></p>
                        <p class="m-0 px-2">-</p>
                        <p class="m-0">Orders</p>
                    </div>
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
                                        <div class="profile-card-body-table">
                                        <c:if test="${empty orders}">
                                            <h2>No orders placed.</h2>
                                        </c:if>
                                        <c:if test="${!empty orders}">
                                        <table>
                                            <tbody>
                                                <c:forEach var="order" items="${orders}">
                                                <tr>
                                                    <td>Order date</td>
                                                    <td>:</td>
                                                    <td>${order.date}</td>
                                                </tr>
                                                <tr>
                                                    <td>Status</td>
                                                    <td>:</td>
                                                    <td>${order.status}</td>
                                                </tr>
                                                <tr>
                                                    <td>Price</td>
                                                    <td>:</td>
                                                    <td><fmt:formatNumber value = "${order.total+10}" type = "currency"/></td>
                                                </tr>
                                                <tr>
                                                    <td><a role="button" type="button" class="btn btn-dark" href="/e-commerce/orders/details/${order.id}">View details</a></td>
                                                    <td><a role="button" type="button" class="btn btn-dark" href="/e-commerce/orders/cancel/${order.id}">Cancel order</a></td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                            </c:if>
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