<%-- 
    Document   : register
    Created on : Oct 20, 2023, 10:54:08 AM
    Author     : ubro3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="models.auth.UserError"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/f1de9b34a8.js" crossorigin="anonymous"></script>
</head>
<body>
    <style>
        body {
            background: #007bff;
            background: linear-gradient(to right, #0062E6, #33AEFF);
        }

        .btn-login {
            font-size: 0.9rem;
            letter-spacing: 0.05rem;
            padding: 0.75rem 1rem;
        }

        .btn-google {
            color: white !important;
            background-color: #ea4335;
        }

        .btn-facebook {
            color: white !important;
            background-color: #3b5998;
        }
    </style>
    <c:set var="userError" value="${requestScope.USER_ERROR}" />
    <div class="container"> 
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                <div class="card border-0 shadow rounded-3 my-5">
                    <div class="card-body p-4 p-sm-5">
                        <h5 class="card-title text-center mb-5 fw-light fs-5">Create an account</h5>
                        <c:out value="${error}"/>
                        <form method="POST" action="MainController">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="floatingInput" name="userName" required="" placeholder="Username">
                                <label for="floatingInput">Username</label>
                                <span class="text-danger">${userError.userNameError}</span>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="floatingFullName" name="fullName" required="" placeholder="Full Name">
                                <label for="floatingFullName">Full Name</label>
                                <span class="text-danger">${userError.fullNameError}</span>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="password" class="form-control" id="floatingPassword" name="password" required="" placeholder="Password">
                                <label for="floatingPassword">Password</label>
                                <span class="text-danger">${userError.passwordError}</span>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="password" class="form-control" id="floatingRePassword" name="rePassword" required="" placeholder="Retype Password">
                                <label for="floatingRePassword">Retype Password</label>
                                <span class="text-danger">${userError.rePasswordError}</span>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="floatingAddress" name="address" required="" placeholder="Address">
                                <label for="floatingAddress">Address</label>
                                <span class="text-danger">${userError.addressError}</span>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="floatingPhone" name="phone" required="" placeholder="Phone Number">
                                <label for="floatingPhone">Phone Number</label>
                                <span class="text-danger">${userError.phoneError}</span>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="email" class="form-control" id="floatingEmail" name="email" required="" placeholder="Email Address">
                                <label for="floatingEmail">Email Address</label>
                                <span class="text-danger">${userError.emailError}</span>
                            </div>
                            <input type="hidden" name="roleID" value="US">
                            <div class="d-grid py-2">
                                <input type="submit" name="action" value="Register" class="btn btn-primary btn-login text-uppercase fw-bold"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>