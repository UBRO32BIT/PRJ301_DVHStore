<%-- 
    Document   : waitPayment
    Created on : Oct 30, 2023, 5:05:44 AM
    Author     : ubro3
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Detail</title>
        <script src="qrcode.min.js"></script>
    </head>
    <body>
        <%-- CONFIGURE TAGS --%>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <c:if test="${empty error}">
            <c:set var="error" value=""/>
        </c:if>
        <c:set var="loginUser" value="${sessionScope.LOGIN_USER}"/>
        <c:if test="${empty loginUser || loginUser.roleID ne 'US'}">
            <c:set var="ERROR" value="${error}"/>
            <c:redirect url="login.jsp"/>
        </c:if>
        <c:set var="orderURL" value="${requestScope.ORDER_URL}"/>
        <h1>Scan the following QR Code to continue</h1>
        <div id="qrcode"></div>
        <script type="text/javascript">
            new QRCode(document.getElementById("qrcode"), "${orderURL}");
        </script>
        or <a href="${orderURL}">Click Here</a>
    </body>
</html>
