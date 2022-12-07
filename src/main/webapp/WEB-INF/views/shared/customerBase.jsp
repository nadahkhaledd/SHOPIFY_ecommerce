<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<html xmlns:layout="http://kwonnam.pe.kr/jsp/template-inheritance"  xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><layout:block name="title">base_head_title</layout:block></title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">


        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="${pageContext.request.contextPath}/resources/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <link href="${pageContext.request.contextPath}/resources/scss/style.scss" rel="stylesheet">
        <!-- Customized Bootstrap Stylesheet -->
        <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">

        <layout:block name="style">base_head_style</layout:block>
    </head>

    <body>
    <!-- Topbar Start -->
    <div class="container-fluid">
        <div class="row bg-secondary py-2 px-xl-5">
            <div class="col-lg-6 d-none d-lg-block">
                <div class="d-inline-flex align-items-center">
                    <a class="text-dark" href="">FAQs</a>
                    <span class="text-muted px-2">|</span>
                    <a class="text-dark" href="">Help</a>
                    <span class="text-muted px-2">|</span>
                    <a class="text-dark" href="">Support</a>
                </div>
            </div>
            <div class="col-lg-6 text-center text-lg-right">
                <div class="d-inline-flex align-items-center">
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
                        <i class="fab fa-instagram"></i>
                    </a>
                    <a class="text-dark pl-2" href="">
                        <i class="fab fa-youtube"></i>
                    </a>
                </div>
            </div>
        </div>
        <div class="row align-items-center py-3 px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a href="/e-commerce/home" class="text-decoration-none">
                    <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                </a>
            </div>
            <div class="col-lg-6 col-6 text-left">
                <form action="/e-commerce/search" method="POST">
                    <div class="input-group">
                        <input type="text" id="searchValue" name="searchValue" class="form-control" placeholder="Search for products">
                        <div class="input-group-append">
                                <span class="input-group-text bg-transparent text-primary">
                                    <i class="fa fa-search"></i>
                                </span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-lg-3 col-6 text-right">
                    <a href="${pageContext.request.contextPath}/user/profile" class="btn border">
                        <i class="fas fa-user text-primary"></i>
                    </a>
                    <a href="${pageContext.request.contextPath}/cart/view" class="btn border">
                        <i class="fas fa-shopping-cart text-primary"></i>
                    </a>
                </div>
            </div>
        </div>
        <!-- Topbar End -->

        <!-- Content start-->
            <div id="content">
                <layout:block name="content">
                    base_body_content
                </layout:block>
            </div>
        <!-- Content end-->

        <!-- Featured Start -->
                    <div class="container-fluid pt-5">
                        <div class="row px-xl-5 pb-3">
                            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                                <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                                    <h1 class="fa fa-check text-primary m-0 mr-3"></h1>
                                    <h5 class="font-weight-semi-bold m-0">Quality Product</h5>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                                <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                                    <h1 class="fa fa-shipping-fast text-primary m-0 mr-2"></h1>
                                    <h5 class="font-weight-semi-bold m-0">Free Shipping</h5>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                                <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                                    <h1 class="fas fa-exchange-alt text-primary m-0 mr-3"></h1>
                                    <h5 class="font-weight-semi-bold m-0">14-Day Return</h5>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                                <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                                    <h1 class="fa fa-phone-volume text-primary m-0 mr-3"></h1>
                                    <h5 class="font-weight-semi-bold m-0">24/7 Support</h5>
                                </div>
                            </div>
                        </div>
                    </div>
        <!-- Featured End -->

        <!-- Vendor Start -->
            <div class="container-fluid py-5">
                <div class="row px-xl-5">
                    <div class="col">
                        <div class="owl-carousel vendor-carousel">
                            <div class="vendor-item border p-4">
                                <img src="https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/others%2Fvendor-1.jpg?alt=media&token=3508f088-e221-49a7-88d2-d70b5203a9d1" alt="">
                            </div>
                            <div class="vendor-item border p-4">
                                <img src="https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/others%2Fvendor-2.jpg?alt=media&token=a2bc966d-9837-46ac-9881-5bd857778d54" alt="">
                            </div>
                            <div class="vendor-item border p-4">
                                <img src="https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/others%2Fvendor-3.jpg?alt=media&token=198d8e76-9556-4b99-9c5e-a4ae152f9b53" alt="">
                            </div>
                            <div class="vendor-item border p-4">
                                <img src="https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/others%2Fvendor-4.jpg?alt=media&token=bd189bf4-ab09-4296-8bfd-dd12e95817e2" alt="">
                            </div>
                            <div class="vendor-item border p-4">
                                <img src="https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/others%2Fvendor-5.jpg?alt=media&token=15f945ac-1574-4150-9ce4-83bb041104d7" alt="">
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        <!-- Vendor End -->


        <!-- Footer Start -->
            <div class="container-fluid bg-secondary text-dark mt-5 pt-5">
                <div class="row px-xl-5 pt-5">
                    <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
                        <a href="/e-commerce/home" class="text-decoration-none">
                            <h1 class="mb-4 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border border-white px-3 mr-1">E</span>Shopper</h1>
                        </a>
                        <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>VOIS Dallah building</p>
                        <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>superadmin@shopify.com</p>
                        <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>+012 345 67890</p>
                    </div>
                    <div class="col-lg-8 col-md-12">
                        <div class="row">
                            <div class="col-md-4 mb-5">
                                <h5 class="font-weight-bold text-dark mb-4">Quick Links</h5>
                                <div class="d-flex flex-column justify-content-start">
                                    <a class="text-dark mb-2" href="e-commerce/home"><i class="fa fa-angle-right mr-2"></i>Home</a>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="row border-top border-light mx-xl-5 py-4">
                    <div class="col-md-6 px-xl-0 text-center text-md-right">
                        <img class="img-fluid" src="https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/others%2Fpayments.png?alt=media&token=cb9e4ab8-0a2b-4c20-9ceb-9b172700900b" alt="">
                    </div>
                </div>
            </div>
        <!-- Footer End -->

         <!-- Back to Top -->
            <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


            <!-- JavaScript Libraries -->
            <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
            <script src="${pageContext.request.contextPath}/resources/lib/easing/easing.min.js"></script>
            <script src="${pageContext.request.contextPath}/resources/lib/owlcarousel/owl.carousel.min.js"></script>

            <!-- Contact Javascript File -->
            <script src="${pageContext.request.contextPath}/resources/mail/jqBootstrapValidation.min.js"></script>
            <script src="${pageContext.request.contextPath}/resources/mail/contact.js"></script>

            <!-- Template Javascript -->
            <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    </body>
</html>