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
            <title><layout:put block="title" type="REPLACE">Cart</layout:put></title>
            <layout:put block="style" type="REPLACE"></layout:put>
        </head>
    </head>

    <body>
        <layout:put block="content" type="REPLACE">
            <!-- Cart Start -->
            <div class="container-fluid pt-5">
                <div class="row px-xl-5">
                    <div class="col-lg-8 table-responsive mb-5">
                        <c:if test="${empty cartProducts}">
                            <h2> No items in cart. </h2>
                        </c:if>
                        <c:if test="${!empty cartProducts}">
                        <table class="table table-bordered text-center mb-0">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th>Products</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <c:forEach var="product" items="${cartProducts}">
                                    <tr>
                                        <td class="align-middle"><img src="${product.product.imagePath}" alt="" style="width: 50px;">
                                            <a href="${pageContext.request.contextPath}/products/productDetails?productId=${product.product.id}">${product.product.name}</a> </td>
                                        <td class="align-middle"><fmt:formatNumber value = "${product.product.price}" type = "currency"/></td>
                                        <td class="align-middle">
                                            <div class="input-group quantity mx-auto" style="width: 100px;">
                                                <div class="input-group-btn">
                                                    <button class="btn btn-sm btn-primary btn-minus"
                                                    onclick="location.href = '${pageContext.request.contextPath}/cart/update/${product.id}?quantity=${product.productQuantity-1}'">
                                                    <i class="fa fa-minus"></i>
                                                    </button>
                                                </div>
                                                <input type="text" class="form-control form-control-sm bg-secondary text-center" readonly value=${product.productQuantity}>
                                                <div class="input-group-btn">
                                                    <button class="btn btn-sm btn-primary btn-plus"
                                                    onclick="location.href = '${pageContext.request.contextPath}/cart/update/${product.id}?quantity=${product.productQuantity+1}'">
                                                        <i class="fa fa-plus"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="align-middle"><fmt:formatNumber value = "${product.product.price*product.productQuantity}" type = "currency"/></td>
                                        <td class="align-middle"><button class="btn btn-sm btn-primary" onclick="location.href = '${pageContext.request.contextPath}/cart/delete/${product.id}'"><i class="fa fa-times"></i></button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                            </c:if>
                    </div>
                    <div class="col-lg-4">
                        <div class="card border-secondary mb-5">
                            <div class="card-header bg-secondary border-0">
                                <h4 class="font-weight-semi-bold m-0">Cart Summary</h4>
                            </div>
                            <div class="card-body">
                                <div class="d-flex justify-content-between mb-3 pt-1">
                                    <h6 class="font-weight-medium">Subtotal</h6>
                                    <h6 class="font-weight-medium"><fmt:formatNumber value = "${cartTotal}" type = "currency"/></h6>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <h6 class="font-weight-medium">Shipping</h6>
                                    <h6 class="font-weight-medium"><fmt:formatNumber value = "${shipping}" type = "currency"/></h6>
                                </div>
                            </div>
                            <div class="card-footer border-secondary bg-transparent">
                                <div class="d-flex justify-content-between mt-2">
                                    <h5 class="font-weight-bold">Total</h5>
                                    <h5 class="font-weight-bold"><fmt:formatNumber value = "${cartTotal+shipping}" type = "currency"/></h5>
                                </div>
                        <c:if test="${empty cartProducts}">
                                <button class="btn btn-block btn-primary my-3 py-3"
                                onclick="location.href = '${pageContext.request.contextPath}/cart/checkout'"
                                disabled="true">
                                Proceed To Checkout</button>
                        </c:if>
                        <c:if test="${!empty cartProducts}">
                                <button class="btn btn-block btn-primary my-3 py-3"
                                onclick="location.href = '${pageContext.request.contextPath}/cart/checkout'">
                                Proceed To Checkout</button>
                        </c:if>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Cart End -->
        </layout:put>
    </body>
</html>
</layout:extends>