/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import thinlm.cart.Cart;
import thinlm.cart.CartItem;
import thinlm.daos.CakeDAO;
import thinlm.dtos.AccountDTO;
import thinlm.dtos.CakeDTO;

/**
 *
 * @author Admin
 */
public class ViewCartController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(ViewCartController.class);
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
        String url = "cart.jsp";
        try {
            HttpSession session = request.getSession();
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACCOUNT");
            boolean isAdmin = false;
            if (accountDTO != null) {
                if (accountDTO.getRole().equals("admin")) {
                    isAdmin = true;
                }
            }
            if(!isAdmin){
            Cart cart = (Cart) session.getAttribute("CART");
            
            if(cart == null || cart.getShoppingCart()==null || cart.getShoppingCart().isEmpty()){
                request.setAttribute("ERROR_CART_MSG", "Your cart is empty!!");
                request.setAttribute("IS_SHOW_CART_MSG", "true");
            }
            else{
                ArrayList<CartItem> listCart = new ArrayList<>();
                CakeDAO cakeDAO = new CakeDAO();
                for (String cakeID : cart.getShoppingCart().keySet()) {
                    CakeDTO cakeDTO = cakeDAO.getCakeByID(cakeID);
                    CartItem cartItem = new CartItem(cakeDTO, cart.getShoppingCart().get(cakeID));
                    listCart.add(cartItem);
                }
                request.setAttribute("LIST_CART", listCart);
            }
            }
            else{
                url = "PagingController";
                request.setAttribute("ADMIN_ERROR", "Admin can't go shopping");
                request.setAttribute("IS_SHOW_ADMIN_MSG", "block");
                LOGGER.info("AddtoCartController_Exception : Admin can't use this function"); 
            }
        } catch (NamingException e) {
            LOGGER.info("ViewCartController_NamingController : " + e.getMessage());
        }
        catch(SQLException e){
            LOGGER.info("ViewCartController_SQLRException : " + e.getMessage());
        }
        finally{
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
