<%-- 
    Document   : navbar
    Created on : Oct 30, 2023, 7:50:48 PM
    Author     : ubro3
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="loginUser" value="${sessionScope.LOGIN_USER}"/>
        <style>
            @keyframes spinning {
                from { transform: rotate(0deg) }
                to { transform: rotate(360deg) }
            }
            .spin {
                animation-name: spinning;
                animation-duration: 1.25s;
                animation-iteration-count: infinite;
                /* linear | ease | ease-in | ease-out | ease-in-out */
                animation-timing-function: linear;
            }
            .navbar {
                background-color: rgb(238, 238, 238)
            }
            .custom-label {

                border: none;
                background: #084cdf;
                padding: 10px 20px;
                border-radius: 10px;
                color: #fff;
                cursor: pointer;
                transition: background .2s ease-in-out;
            }

            .custom-label:hover {
                background: #0d45a5;
            }
        </style>
<div class="navbar px-3 py-3 d-flex justify-content-between shadow fixed-top">
    <a href="MainController"class="col-lg-8 col-12 d-inline-flex align-items-center justify-content-center justify-content-lg-start text-decoration-none">
        <img src="https://ubro32bit.github.io/MyFirstWebpage/other-site/logo.png" alt="logo" width="48px" height="48px" class="spin mx-3" />
        <h4 class=" text-black">Dam Vinh Hung's Store</h4>
    </a>
    <div class="col-lg-4 col-12 d-inline-flex justify-content-center justify-content-lg-end align-items-center align-items-center">
        <c:choose>
            <c:when test="${empty loginUser}">
                <a href="MainController?action=LoginPage" class="mx-lg-2 mx-3 text-decoration-none"><b>LOGIN</b></a>
            </c:when>
            <c:otherwise>
                <div class="dropdown">
                    <a href="#" class="d-flex align-items-center text-black text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                        <c:choose>
                            <c:when test="${empty loginUser.profileImage}">
                                <img src="https://ubro32bit.github.io/MyFirstWebpage/other-site/logo.png" alt="" width="32" height="32" class="rounded-circle me-2">
                            </c:when>
                            <c:otherwise>
                                <img src="${loginUser.profileImage}" alt="" width="32" height="32" class="rounded-circle me-2">
                            </c:otherwise>
                        </c:choose>
                        <strong>${loginUser.userName}</strong>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
                        <li><a class="dropdown-item" href="#">Profile</a></li>
                        <li><a class="dropdown-item" href="#">Settings</a></li>
                            <c:if test="${loginUser.roleID eq 'AD'}">
                            <li><a class="dropdown-item" href="MainController?action=AdminPage">Admin Dashboard</a></li>
                            </c:if>
                        <li><hr class="dropdown-divider"></li>
                        <li>
                            <form action="MainController" method="POST">
                                <input class="dropdown-item" type="submit" name="action" value="Sign out">
                            </form>
                        </li>
                    </ul>
                </div>
            </c:otherwise>
        </c:choose>
        <%--<a href="#"><i class="fa-solid fa-user text-dark"></i></a>--%>
        <a href="MainController?action=ViewCartPage" class="position-relative mx-4 rounded-5">
            <i class="fa-solid fa-cart-shopping p-2 text-dark"></i>
            <span class="badge position-absolute top-0 start-100 translate-middle bg-danger text-white rounded-pill">${sessionScope.CART.cart.size()}</span>
        </a>
    </div>
</div>
