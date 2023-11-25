/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.auth.User;
import models.dao.UserDAO;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import utils.GoogleLogin;
import utils.VerifyCaptcha;

/**
 *
 * @author ubro3
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private static final String ERROR = "login.jsp";
    private static final String US = "US";
    private static final String AD = "AD";
    private static final String US_PAGE = "home.jsp";
    private static final String AD_PAGE = "admin.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String idToken = request.getParameter("credential");
            User loginUser = null;
            if (idToken != null) { //SIGN IN USING GOOGLE ACCOUNTS
                UserDAO dao = new UserDAO();
                JSONObject googleUserInfo = GoogleLogin.checkAccount(idToken);
                if (googleUserInfo != null) {
                    loginUser = dao.checkGoogleLogin(googleUserInfo.getString("sub"));
                    // If the user not exists, create a new one
                    if (loginUser == null) {
                        LOGGER.info("Google authenticate successfully, creating an account...");
                        User newUser = new User(
                                0,
                                googleUserInfo.getString("email").split("@")[0],
                                googleUserInfo.getString("name"),
                                null,
                                null,
                                null,
                                googleUserInfo.getString("email"),
                                "US",
                                googleUserInfo.getString("sub"),
                                googleUserInfo.getString("picture")
                        );
                        boolean result = dao.insertGoogle(newUser);
                        if (result) {
                            loginUser = dao.checkGoogleLogin(googleUserInfo.getString("sub"));
                        } else {
                            request.setAttribute("ERROR", "LOGIN SUCCESSFULLY, BUT YOUR ACCOUNT FAILED TO CREATE!");
                        }
                    }
                } else {
                    request.setAttribute("ERROR", "INVALID GOOGLE ACCOUNT!");
                }
            } else { //SIGN IN USING USERNAME AND PASSWORD
                String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
                // Verify CAPTCHA.
                boolean valid = VerifyCaptcha.isCaptchaValid("6Lft_cYoAAAAALOmEoCisGRGQkmYimyuqZgTQwZ4", gRecaptchaResponse);
                if (valid) {
                    UserDAO dao = new UserDAO();
                    loginUser = dao.checkLogin(userName, password);
                    if (loginUser == null) {
                        request.setAttribute("ERROR", "Wrong username or password, please try again!");
                    }
                } else {
                    request.setAttribute("ERROR", "Recaptcha failed, please try again!");
                }
            }
            if (loginUser != null) {
                LOGGER.info("Login successfully for user " + loginUser.getUserName());
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);
                String roleID = loginUser.getRoleID();
                if (AD.equals(roleID)) {
                    url = AD_PAGE;
                } else if (US.equals(roleID)) {
                    url = US_PAGE;
                } else {
                    request.setAttribute("ERROR", "Your account's role is not allowed!");
                    LOGGER.warn("User " + loginUser.getUserName() + " have role ID " + loginUser.getRoleID() + " which is not allowed");
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error at LoginController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
