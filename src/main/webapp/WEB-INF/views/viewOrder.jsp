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
            <!-- Navbar Start -->
            <div class="container-fluid">
                <div class="row border-top px-xl-5">
                    <div class="col-lg-3 d-none d-lg-block">
                        <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                            <h6 class="m-0">Categories</h6>
                            <i class="fa fa-angle-down text-dark"></i>
                        </a>
                        <nav class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0 bg-light" id="navbar-vertical" style="width: calc(100% - 30px); z-index: 1;">
                            <div class="navbar-nav w-100 overflow-hidden" style="height: 410px">
                                <div class="nav-item dropdown">
                                    <a href="#" class="nav-link" data-toggle="dropdown">Dresses <i class="fa fa-angle-down float-right mt-1"></i></a>
                                    <div class="dropdown-menu position-absolute bg-secondary border-0 rounded-0 w-100 m-0">
                                        <a href="" class="dropdown-item">Men's Dresses</a>
                                        <a href="" class="dropdown-item">Women's Dresses</a>
                                        <a href="" class="dropdown-item">Baby's Dresses</a>
                                    </div>
                                </div>
                                <a href="" class="nav-item nav-link">Shirts</a>
                                <a href="" class="nav-item nav-link">Jeans</a>
                                <a href="" class="nav-item nav-link">Swimwear</a>
                                <a href="" class="nav-item nav-link">Sleepwear</a>
                                <a href="" class="nav-item nav-link">Sportswear</a>
                                <a href="" class="nav-item nav-link">Jumpsuits</a>
                                <a href="" class="nav-item nav-link">Blazers</a>
                                <a href="" class="nav-item nav-link">Jackets</a>
                                <a href="" class="nav-item nav-link">Shoes</a>
                            </div>
                        </nav>
                    </div>
                    <div class="col-lg-9">
                        <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                            <a href="" class="text-decoration-none d-block d-lg-none">
                                <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                            </a>
                            <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                                <div class="navbar-nav mr-auto py-0">
                                    <a href="home.jsp" class="nav-item nav-link">Home</a>
                                    <a href="shop.jsp" class="nav-item nav-link">Shop</a>
                                    <a href="detail.jsp" class="nav-item nav-link">Shop Detail</a>
                                    <div class="nav-item dropdown">
                                        <a href="#" class="nav-link dropdown-toggle active" data-toggle="dropdown">Pages</a>
                                        <div class="dropdown-menu rounded-0 m-0">
                                            <a href="cart.jsp" class="dropdown-item">Shopping Cart</a>
                                            <a href="checkout.jsp" class="dropdown-item">Checkout</a>
                                        </div>
                                    </div>
                                    <a href="contact.jsp" class="nav-item nav-link">Contact</a>
                                </div>
                                <div class="navbar-nav ml-auto py-0">
                                    <a href="" class="nav-item nav-link">Login</a>
                                    <a href="" class="nav-item nav-link">Register</a>
                                </div>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            <!-- Navbar End -->


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