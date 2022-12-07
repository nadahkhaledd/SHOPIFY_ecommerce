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
                    <h1 class="font-weight-semi-bold text-uppercase mb-3">Addresses</h1>
                    <div class="d-inline-flex">
                        <p class="m-0"><a href="${pageContext.request.contextPath}/user/profile">Profile</a></p>
                        <p class="m-0 px-2">-</p>
                        <p class="m-0">Addresses</p>
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
                                       <form:form modelAttribute="address" method="post">
                                               <div class="from-row md-4">
                                                    <label for="street">Street<span>*</span></label><br>
                                                    <form:input required="true" path="street" type="text" id="street" />
                                                    <form:errors path="street" cssClass="error"/>
                                               </div>
                                               <div class="from-row">
                                                   <label for="buildingNumber">Building Number<span>*</span></label><br>
                                                   <form:input required="true" path="buildingNumber" id="buildingNumber" type="number" min="0" step="1"/>
                                                   <form:errors path="buildingNumber" cssClass="error"/>
                                               </div>

                                               <div>
                                                   <label for="city">City<span>*</span></label><br>
                                                   <form:input required="true" path="city" type="text" id="city" />
                                                   <form:errors path="city" cssClass="error"/>
                                               </div>
                                               <br>
                                               <input type="submit" value="add address">
                                           </form:form>
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