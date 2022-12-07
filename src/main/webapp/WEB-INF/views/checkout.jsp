<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<layout:extends name="customerBase">
<html xmlns:layout="http://kwonnam.pe.kr/jsp/template-inheritance"   xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">

    <head>
        <title><layout:put block="title" type="REPLACE">Checkout</layout:put></title>
        <layout:put block="style" type="REPLACE"></layout:put>
    </head>

    <body>
        <layout:put block="content" type="REPLACE">

            <!-- Page Header Start -->
                    <div class="container-fluid bg-secondary mb-5">
                        <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
                            <h1 class="font-weight-semi-bold text-uppercase mb-3">Checkout</h1>
                            <div class="d-inline-flex">
                                <p class="m-0"><a href="${pageContext.request.contextPath}/cart/view">Cart</a></p>
                                <p class="m-0 px-2">-</p>
                                <p class="m-0">Checkout</p>
                            </div>
                        </div>
                    </div>
            <!-- Page Header End -->


            <!-- Checkout Start -->
            <div class="container-fluid pt-5">
                <form action="${pageContext.request.contextPath}/orders/placeOrder" method="post" modelAttribute="newOrder">
                <div class="row px-xl-5">
                    <div class="col-lg-8">
                        <div class="mb-4">
                            <h4 class="font-weight-semi-bold mb-4">Addresses</h4>
                            <div class="row">
                                <div class="col-md-6 form-group">
                                        <c:if test="${empty addresses}">
                                            <h2>No addresses found.<br> Please add an address before placing your order.</h2>
                                            <p class="m-0"><a href="${pageContext.request.contextPath}/address/add">Add address</a></p>
                                        </c:if>
                                        <c:if test="${!empty addresses}">
                                        <c:forEach var="address" items="${addresses}">
                                            <input type="radio" name="address" required="true" path="address" value="${address.id}">
                                            &nbsp;Street: ${address.street}<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;City: ${address.city}<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Building number: ${address.buildingNumber}
                                            <br/><br/>
                                        </c:forEach>
                                        </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="card border-secondary mb-5">
                            <div class="card-header bg-secondary border-0">
                                <h4 class="font-weight-semi-bold m-0">Order Total</h4>
                            </div>
                            <div class="card-body">
                                <h5 class="font-weight-medium mb-3">Products</h5>
                                <c:forEach var="cartItem" items="${cartProducts}">
                                    <div class="d-flex justify-content-between">
                                        <p>${cartItem.product.name} (x${cartItem.productQuantity})</p>
                                        <p><fmt:formatNumber value = "${cartItem.product.price * cartItem.productQuantity}" type = "currency"/></p>
                                    </div>
                                </c:forEach>
                                <hr class="mt-0">
                                <div class="d-flex justify-content-between mb-3 pt-1">
                                    <h6 class="font-weight-medium">Subtotal</h6>
                                    <h6 class="font-weight-medium"><fmt:formatNumber value = "${cartTotal}" type = "currency"/></h6>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <h6 class="font-weight-medium">Shipping</h6>
                                    <h6 class="font-weight-medium">$10</h6>
                                </div>
                            </div>
                            <div class="card-footer border-secondary bg-transparent">
                                <div class="d-flex justify-content-between mt-2">
                                    <h5 class="font-weight-bold">Total</h5>
                                    <h5 class="font-weight-bold"><fmt:formatNumber value = "${cartTotal+10}" type = "currency"/></h5>
                                </div>
                            </div>
                            <div class="card-footer border-secondary bg-transparent">
                                        <c:if test="${empty addresses}">
                                        <button class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3"
                                        type="submit" disabled="true">Place Order</button>
                                        </c:if>
                                        <c:if test="${!empty addresses}">
                                        <button class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3"
                                        type="submit">Place Order</button>
                                        </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                                    </form>
            </div>
            <!-- Checkout End -->
        </layout:put>

    </body>

</html>
</layout:extends>