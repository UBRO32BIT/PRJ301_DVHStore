<%-- 
    Document   : login.jsp
    Created on : Oct 19, 2023, 9:15:09 PM
    Author     : ubro3
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/f1de9b34a8.js" crossorigin="anonymous"></script>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <c:if test="${empty error}">
            <c:set var="error" value=""/>
        </c:if>
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
        <div class="container"> 
            <div class="row">
                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div class="card border-0 shadow rounded-3 my-5">
                        <div class="card-body p-4 p-sm-5">
                            <h5 class="card-title text-center mb-5 fw-light fs-5">Sign In</h5>
                            <h6 class="text-danger">${error}</h6>
                            <form method="POST" action="MainController">
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" id="floatingInput" name="userName" required="" placeholder="Username">
                                    <label for="floatingInput">Username</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="password" class="form-control" id="floatingPassword" name="password" required="" placeholder="Password">
                                    <label for="floatingPassword">Password</label>
                                </div>
                                <div class="g-recaptcha" data-sitekey="6Lft_cYoAAAAALg8atJ2NyywfuNbN7lIk3IqtFrx"></div>
                                <div class="d-grid py-2">
                                    <input type="submit" name="action" value="Login" class="btn btn-primary btn-login text-uppercase fw-bold"/>
                                </div>
                                Don't have any account? <a href="MainController?action=RegisterPage">Register</a>
                                <hr class="my-4">
                                <script src="https://accounts.google.com/gsi/client" async defer></script>
                                <div id="g_id_onload"
                                     data-client_id="962860350375-3kmuahhf5gaook7058dcb09vdutbptom.apps.googleusercontent.com"
                                     data-context="signin"
                                     data-ux_mode="popup"
                                     data-login_uri="http://localhost:8080/PRJ301_DVHStore/MainController?action=Login"
                                     data-auto_prompt="false">
                                </div>
                                <div class="d-flex justify-content-center">
                                    <div class="g_id_signin"
                                         data-type="standard"
                                         data-shape="rectangular"
                                         data-theme="filled_black"
                                         data-text="signin_with"
                                         data-size="large"
                                         data-logo_alignment="left">
                                    </div>
                                </div>
                            </form>
                            <a href="MainController?action=num">Final Test</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
