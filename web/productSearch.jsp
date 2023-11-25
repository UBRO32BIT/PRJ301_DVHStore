<%-- 
    Document   : productSearchResult
    Created on : Oct 24, 2023, 9:16:04 AM
    Author     : ubro3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Result</title>
        <script src="https://kit.fontawesome.com/f1de9b34a8.js" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <c:set var="search" value="${param.search}"/>
    <c:set var="productList" value="${requestScope.LIST_PRODUCT}"/>
    <body>
        <style>
            .form-inputs{
                position:relative;
            }
            .form-inputs .form-control{
                height:45px; 
            }

            .form-inputs .form-control:focus{
                box-shadow:none;
                border:1px solid #000;
            }

            .form-inputs i{
                position:absolute;
                right:10px;
                top:15px;
            }
            .product-image {
                height: 35vh;
            }
            .product-image img {
                object-fit: cover; /* This will scale and crop the image to fit within the div */
                width: 100%; /* Make sure the image takes up the full width of the div */
                height: 100%; /* Make sure the image takes up the full height of the div */
            }
            input[type="number"] {
                width: 64px;
            }
        </style>
    <%-- CONFIGURE TAGS --%>
    <c:set var="error" value="${requestScope.ERROR}"/>
    <c:if test="${empty error}">
        <c:set var="error" value=""/>
    </c:if>
    <%@include file="./components/navbar.jsp" %>
    <div class="p-lg-5"></div>
    <div>
        <div class="d-flex justify-content-center align-items-center">
            <form action="MainController" id="searchForm" class="w-50">
                <div class="d-flex form-inputs">
                    <input class="form-control" type="text" name="search" placeholder="Search any product...">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </div>
                <input type="hidden" name="action" value="Search">
                <input type="submit" class="btn btn-primary my-1">
            </form>
        </div>
    </div>
    <div class="container">
        <%@include file="./components/notificationSvg.jsp" %>
        <c:if test="${not empty requestScope.SHOPPING_MESSAGE}">
            <div class="alert alert-success d-flex align-items-center justify-content-between" role="alert">
                <div>
                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                    ${requestScope.SHOPPING_MESSAGE}
                </div>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        <c:if test="${not empty search}">
            <h2 class="text-center">Search Result for ${search}</h2>
        </c:if>
        <c:if test="${productList.size() > 0}">
            <div class="row gx-5 gy-2">
                <c:forEach items="${productList}" var="product">
                    <div class="product col-3 py-4 position-relative">
                        <div class="product-image ">
                            <c:if test="${not empty product.image}">
                                <img class="rounded-2" src="${product.image}" alt="${product.name}">
                            </c:if>
                        </div>
                        <div class="product-info">
                            <p class="fw-bold fs-5">${product.name}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div>
                                    <div>Price</div>
                                    <p class="fw-bold"><fmt:formatNumber value="${product.price}" type="number" />₫</p>
                                </div>
                                <div class="text-end">
                                    <div>In stock</div>
                                    <p class="fw-bold">${product.quantity}</p>
                                </div>
                            </div>
                            <form action="MainController">
                                <input type="hidden" name="search" value="${search}"/>
                                <input type="hidden" name="cmbProduct" value="${product.productID}-${product.name}-${product.price}"/>
                                <input type="hidden" name="cmbImage" value="${product.image}">
                                <div>
                                    <c:choose>
                                        <c:when test="${product.quantity > 0}">
                                            <div class="d-flex justify-content-between align-items-center">
                                                <input type="number" min="1" max="${product.quantity}" name="cmbQuantity" value="1">
                                                <input class="btn btn-light border" type="submit" name="action" value="Add to Cart"/>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="fw-bold text-warning float-end">SOLD OUT</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
