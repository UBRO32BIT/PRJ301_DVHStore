/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.order;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.auth.User;
import models.dao.OrderDAO;
import models.dao.OrderDetailDAO;
import models.dao.ProductDAO;
import models.order.Order;
import models.order.OrderDetail;
import models.product.Cart;
import models.product.Product;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import utils.ZaloPay;

/**
 *
 * @author ubro3
 */
@WebServlet(name = "OrderCreateController", urlPatterns = {"/OrderCreateController"})
public class OrderCreateController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(OrderCreateController.class);
    private static final String ZALO_PAY = "ZaloPay";
    private static final String COD = "COD";
    private static final String PENDING = "PENDING";
    private static final String ERROR = "checkout.jsp";
    private static final String ZALOPAY_SUCCESS = "waitPayment.jsp";
    private static final String COD_SUCCESS = "orderSuccess.jsp";

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
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            //IMPLEMENT CREATE ORDER FROM CART HERE
            //WARNING: DO NOT RUN THIS SERVLET + ADD VALIDATION FOR CART AMOUNT
            User loginUser = (User) session.getAttribute("LOGIN_USER");
            if (loginUser != null) {
                if ("US".equals(loginUser.getRoleID())) {
                    boolean isValid = true;
                    ProductDAO productDAO = new ProductDAO();
                    OrderDAO orderDAO = new OrderDAO();
                    // Check cart quantity and product's quantity
                    for (Product cartProduct : cart.getCart().values()) {
                        Product product = productDAO.getProductById(cartProduct.getProductID());
                        if (product == null || product.getQuantity() < cartProduct.getQuantity()) {
                            isValid = false;
                            request.setAttribute("ERROR", "Invalid product in cart (Invalid info or quantity is more than item in stock!)");
                            break;
                        }
                    }
                    // If the product
                    if (isValid) {
                        // Create new order
                        double total = 0;
                        for (Product product : cart.getCart().values()) {
                            total += product.getPrice() * product.getQuantity();
                        }
                        String paymentMethod = request.getParameter("paymentMethod");
                        if (ZALO_PAY.equals(paymentMethod) || COD.equals(paymentMethod)) {
                            Order order = new Order(0, null, paymentMethod, PENDING, loginUser.getUserID());
                            Long orderID = orderDAO.insertOrder(order);
                            // Call ZaloPay API for payment
                            if (orderID != null) {
                                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                                // Update product's quantity
                                for (Product cartProduct : cart.getCart().values()) {
                                    Product product = productDAO.getProductById(cartProduct.getProductID());
                                    productDAO.updateProductQuantity(product, cartProduct.getQuantity());
                                    OrderDetail orderDetail = new OrderDetail(0, product.getProductID(), orderID, cartProduct.getQuantity(), cartProduct.getPrice());
                                    orderDetailDAO.insertOrderDetail(orderDetail);
                                }
                                if (ZALO_PAY.equals(paymentMethod)) {
                                    JSONObject APIResult = ZaloPay.createOrder(order, total);
                                    if (APIResult.getInt("return_code") == 1) {
                                        request.setAttribute("ORDER_URL", APIResult.getString("order_url"));
                                        url = ZALOPAY_SUCCESS;
                                    }
                                } else if (COD.equals(paymentMethod)) {
                                    url = COD_SUCCESS;
                                }
                            }
                        } else {
                            request.setAttribute("ERROR", "Invalid Payment method, Please choose one!");
                        }
                    }

                } else {
                    request.setAttribute("ERROR", "Your Role is not allowed to visit this page!");
                }
            } else {
                request.setAttribute("ERROR", "You must login to continue!");
            }
        } catch (Exception e) {
            LOGGER.error(e);
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
