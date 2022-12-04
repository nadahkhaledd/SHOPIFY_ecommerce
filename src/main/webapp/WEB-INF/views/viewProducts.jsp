<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<layout:extends name="customerBase">
<html xmlns:layout="http://kwonnam.pe.kr/jsp/template-inheritance"  xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">
    <head>
        <title><layout:put block="title" type="REPLACE">Category products</layout:put></title>
    </head>

    <body>
        <layout:put block="content" type="REPLACE">
            <!-- Products Start -->
            <div class="container-fluid pt-5">
                <div class="text-center mb-4">
                    <h2 class="section-title px-5"><span class="px-2">Products</span></h2>
                </div>
                <div class="row px-xl-5 pb-3">
                    <c:forEach   items="${products}"  var="product"  >
                        <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                            <div class="card product-item border-0 mb-4">
                                <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                    <img class="img-fluid w-100" src="${product.imagePath}" alt="">
                                </div>
                                <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                    <h6 class="text-truncate mb-3">${product.name}</h6>
                                    <div class="d-flex justify-content-center">
                                        <h6>$${product.price}</h6>
                                    </div>
                                </div>
                                <div class="card-footer d-flex justify-content-between bg-light border">
                                    <a href="${pageContext.request.contextPath}/products/productDetails?productId=${product.id}" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>
                                    <a href="${pageContext.request.contextPath}/cart/add/1?productId=${product.id}" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <!-- Products End -->

        </layout:put>
    </body>
</html>
</layout:extends>