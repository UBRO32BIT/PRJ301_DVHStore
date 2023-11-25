/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.product.Cart;
import models.product.Product;
import org.apache.log4j.Logger;

/**
 *
 * @author ubro3
 */
@WebServlet(name = "CartAddController", urlPatterns = {"/CartAddController"})
public class CartAddController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(CartAddController.class);
    private static final String ERROR = "ProductUserSearchController";
    private static final String SUCCESS = "ProductUserSearchController";

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
            String clothesString = request.getParameter("cmbProduct");
            String tmp[] = clothesString.split("-");
            Long id = Long.parseLong(tmp[0]);
            String name = tmp[1];
            double price = Double.parseDouble(tmp[2]);
            int quantity = Integer.parseInt(request.getParameter("cmbQuantity"));
            String image = request.getParameter("cmbImage");
            
            Product product = new Product(id, name, "", price, quantity, 0, image);
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();
            }
            cart.add(product);
            session.setAttribute("CART", cart);
            url = SUCCESS;
            String message = "DA CHON THANH CONG " + quantity + " " + name;
            request.setAttribute("SHOPPING_MESSAGE", message);
        }
        catch (Exception e) {
            LOGGER.error("Error at CartAddController: " + e.toString());
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
