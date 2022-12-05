<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<layout:extends name="customerBase">
<html xmlns:layout="http://kwonnam.pe.kr/jsp/template-inheritance" xmlns="http://www.w3.org/1999/html">

    <head>
        <title><layout:put block="title" type="REPLACE">product details</layout:put></title>
        <!-- Customized Bootstrap Stylesheet -->
        <layout:put block="style" type="REPLACE">
            <style>
                .center {
                text-align: center;
                border: 3px solid green;
                }
            </style>
        </layout:put>
    </head>

    <body>
        <layout:put block="content" type="REPLACE">
            <!-- Navbar Start -->
            <div class="container-fluid">
                <div class="row border-top px-xl-5">
                    <div class="col-lg-9">
                        <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                            <a href="" class="text-decoration-none d-block d-lg-none">
                                <h1 class="m-0 display-5 font-weight-semi-bold"><span
                                        class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                            </a>
                            <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                                <div class="navbar-nav mr-auto py-0">
                                    <a href="" class="nav-item nav-link">Home</a>
                                    <a href="" class="nav-item nav-link">Shop</a>
                                    <a href="" class="nav-item nav-link active">Shop Detail</a>
                                    <div class="nav-item dropdown">
                                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages</a>
                                        <div class="dropdown-menu rounded-0 m-0">
                                            <a href="" class="dropdown-item">Shopping Cart</a>
                                            <a href="" class="dropdown-item">Checkout</a>
                                        </div>
                                    </div>
                                    <a href="" class="nav-item nav-link">Contact</a>
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
                    <h1 class="font-weight-semi-bold text-uppercase mb-3">Product Detail</h1>
                    <div class="d-inline-flex">
                        <p class="m-0"><a href="">Home</a></p>
                        <p class="m-0 px-2">-</p>
                        <p class="m-0">Product Detail</p>
                    </div>
                </div>
            </div>
            <!-- Page Header End -->

            <!-- Shop Detail Start -->
            <div class="container-fluid py-5">
                <div class="row px-xl-5">
                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                        <img class="img-fluid w-100" src=${product.imagePath} alt="">
                    </div>

                    <div class="col-lg-7 pb-5">
                        <h3 class="font-weight-semi-bold"> ${product.name}</h3>
                        <div class="d-flex mb-3">

                            <c:forEach var="i" begin="1" end="${stars.numberOfFullStars}">
                                <small class="fas fa-star" style="color:yellow"></small>
                            </c:forEach>

                            <c:if test="${stars.hasHalfStar == true}">
                                <small class="fas fa-star-half-alt" style="color:yellow"></small>
                            </c:if>
                            <c:forEach var="i" begin="1" end="${stars.numberOfEmptyStars}">
                                <small class="far fa-star" style="color:yellow"></small>
                            </c:forEach>
                            <small class="pt-1">( ${fn:length(product.rates)} Reviews )</small>
                        </div>

                        <h3 class="font-weight-semi-bold mb-4">$${product.price}</h3>


                        <div class="d-flex align-items-center mb-4 pt-2">
                            <div class="input-group quantity mr-3" style="width: 130px;">
                                <div class="input-group-btn">
                                    <button class="btn btn-primary btn-minus">
                                        <i class="fa fa-minus"></i>
                                    </button>
                                </div>
                                <input type="text" class="form-control bg-secondary text-center" value="1">
                                <div class="input-group-btn">
                                    <button class="btn btn-primary btn-plus">
                                        <i class="fa fa-plus"></i>
                                    </button>
                                </div>
                            </div>
                            <button class="btn btn-primary px-3"
                                    onclick="location.href = '${pageContext.request.contextPath}/cart/add/1?productId=${product.id}'">
                                <i class="fa fa-shopping-cart mr-1"></i> Add To Cart
                            </button>
                        </div>
                        <div class="d-flex pt-2">
                            <p class="text-dark font-weight-medium mb-0 mr-2">Share on:</p>
                            <div class="d-inline-flex">
                                <a class="text-dark px-2" href="">
                                    <i class="fab fa-facebook-f"></i>
                                </a>
                                <a class="text-dark px-2" href="">
                                    <i class="fab fa-twitter"></i>
                                </a>
                                <a class="text-dark px-2" href="">
                                    <i class="fab fa-linkedin-in"></i>
                                </a>
                                <a class="text-dark px-2" href="">
                                    <i class="fab fa-pinterest"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">

                    <div class="col-md-6">
                        <h4 class="mb-4">Leave a review</h4>
                         <form method="POST" action="/e-commerce/rate?productId=${product.id}">
                            <div class="d-flex my-3">

                                  <p class="mb-0 mr-2">Your Rating * :</p>
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <input type="radio" value="1" class="custom-control-input" required="required" id="rate-1" name="rate">
                                            <label class="custom-control-label" for="rate-1">1</label>
                                        </div>
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <input type="radio" value="2" class="custom-control-input" id="rate-2" name="rate">
                                            <label class="custom-control-label" for="rate-2">2</label>
                                        </div>
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <input type="radio" value="3" class="custom-control-input" id="rate-3" name="rate">
                                            <label class="custom-control-label" for="rate-3">3</label>
                                        </div>
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <input type="radio" value="4" class="custom-control-input" id="rate-4" name="rate">
                                            <label class="custom-control-label" for="rate-4">4</label>
                                        </div>
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <input type="radio" value="5" class="custom-control-input" id="rate-5" name="rate">
                                            <label class="custom-control-label" for="rate-5">5</label>
                                        </div>

                            </div>

                            <div class="form-group">
                                <label for="message">Your Review *</label>
                                <textarea id="message" required="true" name="message" cols="30" rows="5" class="form-control"></textarea>
                            </div>
                            <div class="form-group mb-0">
                                <input type="submit" value="Leave Your Review" class="btn btn-primary px-3">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- Shop Detail End -->
        </layout:put>
    </body>

</html>
</layout:extends>