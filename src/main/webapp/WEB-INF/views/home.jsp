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
        <title><layout:put block="title" type="REPLACE">SHOPIFY</layout:put></title>
        <layout:put block="style" type="REPLACE"></layout:put>
    </head>

    <body>
        <layout:put block="content" type="REPLACE">
            <!-- Navbar Start -->
            <div class="container-fluid mb-5">
                <div class="row border-top px-xl-5">
                    <div class="col-lg-3 d-none d-lg-block">
                        <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                            <h6 class="m-0">Categories</h6>
                            <i class="fa fa-angle-down text-dark"></i>
                        </a>
                        <nav class="collapse show navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0" id="navbar-vertical">
                            <div class="navbar-nav w-100 overflow-hidden" style="height: 410px">
                                <c:forEach   items="${categories}"  var="category"  >
                                    <a href="/e-commerce/products/getCategoryProducts?categoryId=${category.id}" class="nav-item nav-link">${category.name}</a>
                                </c:forEach>
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
                                    <a href="" class="nav-item nav-link active">Home</a>
                                    <a href="" class="nav-item nav-link">Shop</a>
                                    <a href="" class="nav-item nav-link">Shop Detail</a>
                                    <div class="nav-item dropdown">
                                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages</a>
                                        <div class="dropdown-menu rounded-0 m-0">
                                            <a href="" class="dropdown-item">Shopping Cart</a>
                                            <a href="" class="dropdown-item">Checkout</a>
                                        </div>
                                    </div>
                                    <a href="" class="nav-item nav-link">Contact</a>
                                </div>
                                <div class="navbar-nav ml-auto py-0">
                                    <a href="" class="nav-item nav-link">Login</a>
                                    <a href="" class="nav-item nav-link">Register</a>
                                </div>
                            </div>
                        </nav>
                        <div id="header-carousel" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner">
                                <div class="carousel-item active" style="height: 410px;">
                                    <img class="img-fluid" src="https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/others%2Fcarousel-1.jpg?alt=media&token=d959f240-0bc8-430b-9ca3-21448fe38d3e" alt="Image">
                                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                                        <div class="p-3" style="max-width: 700px;">
                                            <h4 class="text-light text-uppercase font-weight-medium mb-3">10% Off Your First Order</h4>
                                            <h3 class="display-4 text-white font-weight-semi-bold mb-4">Fashionable Dress</h3>
                                            <a href="" class="btn btn-light py-2 px-3">Shop Now</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="carousel-item" style="height: 410px;">
                                    <img class="img-fluid" src="https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/others%2Fcarousel-2.jpg?alt=media&token=319e2ff7-3025-4a5e-bc95-9fd1fefe411c" alt="Image">
                                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                                        <div class="p-3" style="max-width: 700px;">
                                            <h4 class="text-light text-uppercase font-weight-medium mb-3">10% Off Your First Order</h4>
                                            <h3 class="display-4 text-white font-weight-semi-bold mb-4">Reasonable Price</h3>
                                            <a href="" class="btn btn-light py-2 px-3">Shop Now</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <a class="carousel-control-prev" href="#header-carousel" data-slide="prev">
                                <div class="btn btn-dark" style="width: 45px; height: 45px;">
                                    <span class="carousel-control-prev-icon mb-n2"></span>
                                </div>
                            </a>
                            <a class="carousel-control-next" href="#header-carousel" data-slide="next">
                                <div class="btn btn-dark" style="width: 45px; height: 45px;">
                                    <span class="carousel-control-next-icon mb-n2"></span>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Navbar End -->

            <!-- Categories Start -->
            <div class="container-fluid pt-5">
                    <div class="row px-xl-5 pb-3">
                        <c:forEach   items="${categories}"  var="category"  >
                        <div class="col-lg-4 col-md-6 pb-1">
                            <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">

                                <p class="text-right">${fn:length(category.products)} products</p>
                                <a href="products/getCategoryProducts?categoryId=${category.id}" class="cat-img position-relative overflow-hidden mb-3">
                                    <img class="img-fluid" src=${category.imagePath} alt="">

                                </a>
                                <h5 class="font-weight-semi-bold m-0">${category.name} </h5>
                            </div>

                        </div>
                        </c:forEach>
                    </div>

            </div>
            <!-- Categories End -->


            <!-- Offer Start -->
            <div class="container-fluid offer pt-5">
                <div class="row px-xl-5">
                    <div class="col-md-6 pb-4">
                        <div class="position-relative bg-secondary text-center text-md-right text-white mb-2 py-5 px-5">
                            <img src="https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/products%2Foffer-1.png?alt=media&token=f9774fef-ed80-41d5-a01d-411011d25604" alt="">
                            <div class="position-relative" style="z-index: 1;">
                                <h5 class="text-uppercase text-primary mb-3">20% off the all order</h5>
                                <h1 class="mb-4 font-weight-semi-bold">Spring Collection</h1>
                                <a href="" class="btn btn-outline-primary py-md-2 px-md-3">Shop Now</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 pb-4">
                        <div class="position-relative bg-secondary text-center text-md-left text-white mb-2 py-5 px-5">
                            <img src="https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/products%2Foffer-2.png?alt=media&token=cde2e020-e78c-41a9-a36e-43edb110619b" alt="">
                            <div class="position-relative" style="z-index: 1;">
                                <h5 class="text-uppercase text-primary mb-3">20% off the all order</h5>
                                <h1 class="mb-4 font-weight-semi-bold">Winter Collection</h1>
                                <a href="" class="btn btn-outline-primary py-md-2 px-md-3">Shop Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Offer End -->
        </layout:put>
    </body>
</html>
</layout:extends>