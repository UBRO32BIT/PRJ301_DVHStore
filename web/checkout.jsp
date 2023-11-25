<%-- 
    Document   : checkout.jsp
    Created on : Oct 25, 2023, 8:53:36 PM
    Author     : ubro3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/icheck.min.js"></script>
        <script src="https://kit.fontawesome.com/f1de9b34a8.js" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>
        <%-- CONFIGURE TAGS --%>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <c:if test="${empty error}">
            <c:set var="error" value=""/>
        </c:if>
        <c:set var="loginUser" value="${sessionScope.LOGIN_USER}"/>
        <div class="container">
            <c:if test="${not empty error}">
                    <%@include file="./components/notificationSvg.jsp" %>
                    <div class="alert alert-danger d-flex align-items-center justify-content-between" role="alert">
                        <div>
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                            ${error}
                        </div>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
            </c:if>
            <div class="row">
                <div class="col-12 col-md-6">
                    <form action="MainController" method="POST">
                        <div class="py-3">
                            <h2>Delivery Details</h2>
                            Full Name <input type="text" name="address" required="" value="${loginUser.fullName}"><br/>
                            Address <input type="text" name="address" required="" value="${loginUser.address}"><br>
                            Zip Code <input type="number" name="zipCode"><br>
                            Phone number <input type="text" name="phone" required="" value="${loginUser.phone}"><br>
                        </div>
                        <div class="py-3">
                            <h5>Payment method</h5>
                            <label>
                                <input type="radio" name="paymentMethod" value="ZaloPay"> ZaloPay Wallet
                            </label><br>

                            <label>
                                <input type="radio" name="paymentMethod" value="COD"> Cash on delivery
                            </label><br>
                        </div>
                        <input type="hidden" name="action" value="CreateOrder">
                        <input type="submit" value="Confirm Payment" class="btn btn-danger">
                    </form>
                </div>
                <div class="col-12 col-md-6">
                    <c:set var="total" value="0" />
                    <b><h3>In your cart</h3></b>
                    <c:forEach var="products" items="${sessionScope.CART.cart}">
                        <c:set var="subtotal" value="${products.value.price * products.value.quantity}" />
                        <div>
                            <h4>${products.value.name}</h4>
                            <p><fmt:formatNumber value="${products.value.price}" type="number" />₫</p>
                            <p>${products.value.quantity}</td>
                            <p><fmt:formatNumber value="${subtotal}" type="number" />₫</p>
                        </div>
                        <c:set var="total" value="${total + subtotal}" />
                    </c:forEach>
                    <h3>Total to pay: <fmt:formatNumber value="${total}" type="number" />₫</h3>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
