<%-- 
    Document   : number
    Created on : Nov 10, 2023, 10:13:00 AM
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
    </head>
    <body>
        <c:set var="num" value="${requestScope.NUMBER}"/>
        <h1>Number: ${num}</h1>
    </body>
</html>
