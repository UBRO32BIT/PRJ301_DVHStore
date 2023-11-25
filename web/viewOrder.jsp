<%-- 
    Document   : viewOrder
    Created on : Oct 19, 2023, 9:33:06 PM
    Author     : ubro3
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
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
        <c:if test="${empty loginUser || loginUser.roleID ne 'AD'}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <c:set var="search" value="${param.search}"/>
        <c:if test="${empty search}">
            <c:set var="search" value=""/>
        </c:if>
        <%-- END CONFIGURE TAGS --%>
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
        </style>
        <div class="">
            <div class="row">
                <div class="col-2">
                    <div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark" style="height: 100dvh;">
                        <a href="/" class="d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                            <img src="https://ubro32bit.github.io/MyFirstWebpage/other-site/logo.png" alt="Page Logo" class="w-25">
                            <span class="fs-4 px-3">Dashboard</span>
                        </a>
                        <hr>
                        <ul class="nav nav-pills flex-column mb-auto">
                            <li class="nav-item">
                                <a href="admin.jsp" class="nav-link text-white" aria-current="page">
                                    <svg class="bi me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
                                    Home
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-link active">
                                    <svg class="bi me-2" width="16" height="16"><use xlink:href="#table"></use></svg>
                                    Orders
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-link text-white">
                                    <svg class="bi me-2" width="16" height="16"><use xlink:href="#grid"></use></svg>
                                    Products
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-link text-white">
                                    <svg class="bi me-2" width="16" height="16"><use xlink:href="#people-circle"></use></svg>
                                    Customers
                                </a>
                            </li>
                        </ul>
                        <hr>
                        <div class="dropdown">
                            <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="https://ubro32bit.github.io/MyFirstWebpage/other-site/logo.png" alt="" width="32" height="32" class="rounded-circle me-2">
                                <strong>${loginUser.userName}</strong>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
                                <li><hr class="dropdown-divider"></li>
                                <li>                                    
                                    <form action="MainController" method="POST">
                                        <input class="dropdown-item" type="submit" name="action" value="Sign out">
                                    </form>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-10 text-center">
                    <h2>View Orders</h2>
                    <div class="d-flex justify-content-center">
                                            <form action="MainController" id="searchForm" class="w-50">
                        <div class="d-flex form-inputs">
                            <input class="form-control" type="text" name="search" placeholder="Search any order...">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </div>
                        <input type="hidden" name="action" value="Search Order">
                        <input type="submit" class="btn btn-primary my-1" value="Search">
                    </form>
                    </div>
                    
                    <div>
                        <h6>${error}</h6>
                    </div>
                    <div class="d-flex justify-content-center">
                        <div class="">
                            <c:if test="${requestScope.LIST_ORDER.size() > 0}">
                                <hr class="my-4 mx-5">
                                <h5 class="text-center">Search result of "${search}", ${requestScope.LIST_ORDER.size()} found!</h5>
                                <table border="1" class="shadow rounded-1">
                                    <tr>
                                        <th class="text-center" style="background-color: #6FD890;">ID</th>
                                        <th class="text-center" style="background-color: #6FD890;">User ID</th>
                                        <th class="text-center" style="background-color: #6FD890;">Order Date</th>
                                        <th class="text-center" style="background-color: #F2F07E;">Operations</th>
                                    </tr>

                                    <c:forEach items="${requestScope.LIST_ORDER}" var="order">
                                        <tr style="height: 8vh; border-bottom-width: 0.8px; border-bottom-color: rgb(222, 226, 230);">
                                        <form action="MainController">
                                            <td class="px-3">${order.orderID}</td>
                                            <td class="px-1">${order.userID}</td>
                                            <td class="px-3">${order.orderDate}</td>
                                            <td>
                                                <div style="display: flex; min-width: 10vw; justify-content: space-evenly;">
                                                    <a class="btn btn-secondary" href="MainController?orderID=${order.orderID}&action=ViewOrderDetail&search=${search}">View Detail</a>
                                                </div>
                                            </td>
                                        </form>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
