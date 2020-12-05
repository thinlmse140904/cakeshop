/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import thinlm.cart.Cart;
import thinlm.daos.CakeDAO;
import thinlm.dtos.AccountDTO;
import thinlm.dtos.CakeDTO;
import thinlm.error.ErrorObj;

/**
 *
 * @author Admin
 */
public class AddToCartController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(AddToCartController.class);

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
        try {
            //get cart
            HttpSession session = request.getSession();
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACCOUNT");
            boolean isAdmin = false;
            if(accountDTO != null){
                if(accountDTO.getRole().equals("admin")){
                    isAdmin = true;
                }
            }
            if (!isAdmin) {
                Cart cart = (Cart) session.getAttribute("CART");
                //if account have login then cart will have email of costumer
                if (cart == null) {
                    if (accountDTO != null) {
                        cart = new Cart(accountDTO.getEmail());
                    } else {
                        cart = new Cart();
                    }
                }
                int quantity = 0;
                String cakeID = request.getParameter("txtCakeID");
                if (request.getParameter("txtQuantity").length() == 0) {
                    quantity = 1;
                } else {
                    quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                }

                boolean valid = true;
                ErrorObj errorObj = new ErrorObj();
                //call DAO
                CakeDAO cakeDAO = new CakeDAO();
                CakeDTO cakeDTO = cakeDAO.getCakeByIDForCostumer(cakeID);
                if(cakeDTO != null){
                    if (cakeDTO.getQuantity() < quantity) {
                        valid = false;
                        errorObj.setQuantityError("Sorry, there is only " + cakeDTO.getQuantity() + " cakes left in our store");
                    }
                    if (valid) {
                        cart.addToCart(cakeID, quantity);
                    } else {
                        request.setAttribute("ERROR_QUANTITY", errorObj);
                        request.setAttribute("CAKE_ID_CANT_ADD", cakeID);
                    }
                    session.setAttribute("CART", cart);
                }
                else{
                    request.setAttribute("COSTUMER_ERROR", "Cake not found !!!");
                    request.setAttribute("IS_SHOW_COSTUMER_MSG", "block");
                    LOGGER.info("AddtoCartController_Exception : Cake not found");
                }
            }
            else{
                request.setAttribute("ADMIN_ERROR", "Admin can't not go shopping");
                request.setAttribute("IS_SHOW_ADMIN_MSG", "block");
                LOGGER.info("AddtoCartController_Exception : addmin can't go shopping");
            }
        } catch (SQLException e) {
            LOGGER.info("AddToCartController_SQLException : " + e.getMessage());
        } catch (NamingException e) {
            LOGGER.info("AddToCartController_NamingException : " + e.getMessage());
        } finally {
            request.getRequestDispatcher("PagingController").forward(request, response);
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
