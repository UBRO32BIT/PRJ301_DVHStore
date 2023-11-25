<%-- 
    Document   : orderSuccess
    Created on : Nov 8, 2023, 11:21:33 PM
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>
        <style>
            @keyframes spinning {
                from { transform: rotate(0deg) }
                to { transform: rotate(360deg) }
            }
            .spin {
                animation-name: spinning;
                animation-duration: 0.75s;
                animation-iteration-count: infinite;
                /* linear | ease | ease-in | ease-out | ease-in-out */
                animation-timing-function: linear;
            }
        </style>
        <c:set var="loginUser" value="${sessionScope.LOGIN_USER}"/>
        <c:if test="${empty loginUser || loginUser.roleID ne 'US'}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <div class="d-flex justify-content-center">
            <div>
                <img src="https://ubro32bit.github.io/MyFirstWebpage/other-site/logo.png" alt="logo" width="128px" height="128px" class="spin mx-3" />
            </div>
        </div>
        <div class="text-center">
            <h3>Your order has been created successfully</h3>
            <p>We will deliver to you ASAP, thanks for choosing us!</p>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
