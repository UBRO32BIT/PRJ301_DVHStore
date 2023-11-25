/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author ubro3
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(MainController.class);
    private static final String HOME = "HomePage";
    private static final String HOME_PAGE = "home.jsp";
    private static final String GOTO_LOGIN = "LoginPage";
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String GOTO_REGISTER = "RegisterPage";
    private static final String REGISTER_PAGE = "register.jsp";
    private static final String GOTO_ADMIN = "AdminPage";
    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String GOTO_PRODUCT_SEARCH = "ProductSearchPage";
    private static final String PRODUCT_SEARCH_PAGE = "productSearch.jsp";
    private static final String GOTO_VIEW_CART = "ViewCartPage";
    private static final String VIEW_CART_PAGE = "viewCart.jsp";
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String USER_CREATE = "Register";
    private static final String USER_CREATE_CONTROLLER = "UserCreateController";
    private static final String USER_SEARCH = "Search User";
    private static final String USER_SEARCH_CONTROLLER = "UserSearchController";
    private static final String USER_EDIT = "Edit";
    private static final String USER_EDIT_CONTROLLER = "UserEditController";
    private static final String USER_DELETE = "Delete";
    private static final String USER_DELETE_CONTROLLER = "UserDeleteController";
    private static final String LOGOUT = "Sign out";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String SEARCH = "Search";
    private static final String SEARCH_CONTROLLER = "ProductUserSearchController";
    private static final String CART_ADD = "Add to Cart";
    private static final String CART_ADD_CONTROLLER = "CartAddController";
    private static final String CART_EDIT = "Edit Item";
    private static final String CART_EDIT_CONTROLLER = "CartEditController";
    private static final String CART_DELETE = "Remove Item";
    private static final String CART_DELETE_CONTROLLER = "CartDeleteController";
    private static final String CHECKOUT = "Checkout";
    private static final String CHECKOUT_CONTROLLER = "CheckOutController";
    private static final String ORDER_VIEW = "ShowLatestOrder";
    private static final String ORDER_VIEW_CONTROLLER = "OrderViewController";
    private static final String ORDER_CREATE = "CreateOrder";
    private static final String ORDER_CREATE_CONTROLLER = "OrderCreateController";
    private static final String NUM = "num";
    private static final String NUM_CONTROLLER = "NumberController";
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
        String url = HOME_PAGE;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = HOME_PAGE;
            }
            else if (HOME.equals(action)) {
                url = HOME_PAGE;
            }
            else if (GOTO_LOGIN.equals(action)) {
                url = LOGIN_PAGE;
            }
            else if (GOTO_REGISTER.equals(action)) {
                url = REGISTER_PAGE;
            }
            else if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            }
            else if (GOTO_ADMIN.equals(action)) {
                url = ADMIN_PAGE;
            }
            else if (GOTO_PRODUCT_SEARCH.equals(action)) {
                url = PRODUCT_SEARCH_PAGE;
            }
            else if (GOTO_VIEW_CART.equals(action)) {
                url = VIEW_CART_PAGE;
            }
            else if (USER_CREATE.equals(action)) {
                url = USER_CREATE_CONTROLLER;
            }
            else if (USER_SEARCH.equals(action)) {
                url = USER_SEARCH_CONTROLLER;
            }
            else if (USER_DELETE.equals(action)) {
                url = USER_DELETE_CONTROLLER;
            }
            else if (USER_EDIT.equals(action)) {
                url = USER_EDIT_CONTROLLER;
            }
            else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            }
            else if (SEARCH.equals(action)) {
                url = SEARCH_CONTROLLER;
            }
            else if (CART_ADD.equals(action)) {
                url = CART_ADD_CONTROLLER;
            }
            else if (CART_EDIT.equals(action)) {
                url = CART_EDIT_CONTROLLER;
            }
            else if (CART_DELETE.equals(action)) {
                url = CART_DELETE_CONTROLLER;
            }
            else if (ORDER_VIEW.equals(action)) {
                url = ORDER_VIEW_CONTROLLER;
            }
            else if (CHECKOUT.equals(action)) {
                url = CHECKOUT_CONTROLLER;
            }
            else if (ORDER_CREATE.equals(action)) {
                url = ORDER_CREATE_CONTROLLER;
            }
            else if (NUM.equals(action)) {
                url = NUM_CONTROLLER;
            }
        }
        catch (Exception e) {
            LOGGER.error("Error at MainController " + e.toString());
        }
        finally {
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
