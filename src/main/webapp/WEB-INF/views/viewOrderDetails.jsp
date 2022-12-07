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
            <title><layout:put block="title" type="REPLACE">Profile</layout:put></title>
            <layout:put block="style" type="REPLACE"></layout:put>
        </head>
    </head>

    <body>
        <layout:put block="content" type="REPLACE">
            <!-- Page Header Start -->

            <div class="container-fluid bg-secondary mb-5">
                <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
                    <h1 class="font-weight-semi-bold text-uppercase mb-3">Order details</h1>
                    <div class="d-inline-flex">
                        <p class="m-0"><a href="${pageContext.request.contextPath}/orders/view">Orders</a></p>
                        <p class="m-0 px-2">-</p>
                        <p class="m-0">Order details</p>
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
                                        <table>
                                            <tbody>
                                                <c:if test="${!empty orderDetails}">
                                                    <c:forEach var="orderDetail" items="${orderDetails}">
                                                <tr>
                                                    <td>Product</td>
                                                    <td>:</td>
                                                    <td>${orderDetail.productName}     <img src="${orderDetail.productImage}" alt="" style="width: 50px;"></td>
                                                </tr>
                                                <tr>
                                                    <td>Product price</td>
                                                    <td>:</td>
                                                    <td><fmt:formatNumber value = "${orderDetail.productPrice}" type = "currency"/>(x${orderDetail.productQuantity})</td>
                                                </tr>
                                            </c:forEach>
                                            </c:if>
                                            <div>
                                            <p>Subtotal</br><fmt:formatNumber value = "${orderDetails[0].order.total+10}" type = "currency"/></p>
                                            <p>Address</br>
                                            &nbsp;Street: ${orderDetails[0].order.address.street}<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;City: ${orderDetails[0].order.address.city}<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Building number: ${orderDetails[0].order.address.buildingNumber}</p>
                                            </div>

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