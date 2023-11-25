/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.auth.User;
import models.auth.UserError;
import models.dao.UserDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author ubro3
 */
@WebServlet(name = "UserCreateController", urlPatterns = {"/UserCreateController"})
public class UserCreateController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(UserCreateController.class);
    private static final String SUCCESS = "home.jsp";
    private static final String ERROR = "register.jsp";

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
        UserError u = new UserError();
        try {
            boolean check = true;
            String userName = request.getParameter("userName");
            String fullName = request.getParameter("fullName");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String rePassword = request.getParameter("rePassword");
            UserDAO dao = new UserDAO();
            if (userName.length() < 3 || userName.length() > 20) {
                check = false;
                u.setUserNameError("Username must be from 3-20 characters!");
            }
            if (!password.equals(rePassword)) {
                check = false;
                u.setRePasswordError("Password don't match!");
            }
            if (fullName.length() < 5 || fullName.length() > 50) {
                check = false;
                u.setFullNameError("Full Name must be from 5-50 characters!");
            }
            if (dao.checkDuplicateUserName(userName)) {
                check = false;
                u.setUserNameError("Duplicate username, please choose other username!");
            }
            if (check) {
                boolean result = dao.insert(new User(0, userName, fullName, password, address, phone, email, roleID, null, null));
                if (result) {
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at CreateController: " + e.toString());
        } finally {
            request.setAttribute("USER_ERROR", u);
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
