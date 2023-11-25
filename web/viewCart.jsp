<%-- 
    Document   : viewCart
    Created on : Oct 25, 2023, 3:41:30 PM
    Author     : ubro3
--%>

<%@page import="models.product.Cart"%>
<%@page import="models.product.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <script src="https://kit.fontawesome.com/f1de9b34a8.js" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>
        <style>
            input[type="number"] {
                width: 64px;
            }
        </style>
        <%-- CONFIGURE TAGS --%>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <c:if test="${empty error}">
            <c:set var="error" value=""/>
        </c:if>
        <c:set var="loginUser" value="${sessionScope.LOGIN_USER}"/>
        <div class="container">
            <%@include file="./components/navbar.jsp" %>
            <div class="p-lg-5"></div>
            <c:choose>
                <c:when test="${not empty sessionScope.CART and not empty sessionScope.CART.cart}">
                    <h2 class="text-center">Your shopping cart</h2>
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
                    <div class="d-flex justify-content-center">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th class="text-center" style="background-color: #6FD890;">No.</th>
                                    <th class="text-center" style="background-color: #6FD890;">Preview</th>
                                    <th class="text-center" style="background-color: #6FD890;">Name</th>
                                    <th class="text-center" style="background-color: #6FD890;">Price</th>
                                    <th class="text-center" style="background-color: #6FD890;">Quantity</th>
                                    <th class="text-center" style="background-color: #6FD890;">Total</th>
                                    <th class="text-center" style="background-color: #F2F07E;">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="count" value="1" />
                                <c:set var="total" value="0" />
                                <c:forEach var="products" items="${sessionScope.CART.cart}">
                                    <c:set var="subtotal" value="${products.value.price * products.value.quantity}" />
                                    <c:set var="productImage" value="${products.value.image}"/>
                                    <c:if test="${empty productImage}">
                                        <c:set var="productImage" value="https://ubro32bit.github.io/MyFirstWebpage/other-site/logo.png"/>
                                    </c:if>
                                <form action="MainController" method="POST">
                                    <tr class="border-bottom">
                                        <td class="px-3">${count}</td>
                                    <input type="hidden" name="id" value="${products.key}" readonly="">
                                    <td>
                                        <div class="d-flex justify-content-center">
                                            <div class="d-inline-block">
                                                <img src="${productImage}" height="100px">
                                            </div>

                                        </div>
                                    </td>
                                    <td class="px-5 fw-bold">${products.value.name}</td>
                                    <td class="px-3 fs-5 text-center"><fmt:formatNumber value="${products.value.price}" type="number" /></td>
                                    <td class="px-3"><input type="number" name="quantity" value="${products.value.quantity}" min="1" required="" width="50%"></td>
                                    <td class="px-5 fs-5 text-center"><fmt:formatNumber value="${subtotal}" type="number" /></td>
                                    <td>
                                        <div style="display: flex; min-width: 15vw; justify-content: space-evenly;">
                                            <input class="btn btn-primary" type="submit" name="action" value="Edit Item"/>
                                            <input class="btn btn-danger" type="submit" name="action" value="Remove Item"/>
                                        </div>

                                    </td>
                                    </tr>
                                </form>
                                <c:set var="count" value="${count + 1}" />
                                <c:set var="total" value="${total + subtotal}" />
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <h1>Total: <fmt:formatNumber value="${total}" type="number" />₫</h1>
                    <a class="btn btn-danger" href="MainController?action=Checkout">Check out</a>
                </c:when>
                <c:otherwise>
                    <h2 class="fs-1">Your shopping cart is empty.</h2>
                    <p class="fs-5">Check out our awesome products here!</p>
                </c:otherwise>
            </c:choose>
            <a href="MainController?action=ProductSearchPage">Back to shopping page</a>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>