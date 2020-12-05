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
import thinlm.dtos.CakeDTO;
import thinlm.error.ErrorObj;

/**
 *
 * @author Admin
 */
public class EditQuantityController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(EditQuantityController.class);
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
        String url = "ViewCartController";
        try{
            String cakeID = request.getParameter("txtCakeID");
            
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            
            //if user try to update quantity through link when cart is empty
            //then redirect to the shopping page
            if(cart.getShoppingCart().isEmpty()){
                url = "index.jsp";
            }
            
            //parse quantity value from String to Int
            String tmp = request.getParameter("txtQuantity");
            int quantity = Integer.parseInt(tmp);
            ErrorObj errorObj = new ErrorObj();
            boolean valid = true;
            CakeDAO cakeDAO = new CakeDAO();
            CakeDTO cakeDTO = cakeDAO.getCakeByID(cakeID);
            
            //quantity must be a positive number
            if(quantity < 0){
                errorObj.setQuantityError("quantity must be a positive number");
                LOGGER.info("EditQuantityController_NumberFormatException : quantity must be a positive number");
                valid = false;
            }
            
            //quantity must not pass the quantity of available cakes in store
            if(quantity > cakeDTO.getQuantity()){
                errorObj.setQuantityError("sorry, we only have " + cakeDTO.getQuantity()+ " left in our store");
                request.setAttribute("ERROR_QUANTITY", errorObj);
                request.setAttribute("CAKE_ID", cakeID);
                LOGGER.info("EditQuantityController_NumberFormatException : quantity must smaller than " + cakeDTO.getQuantity());
                valid = false;
            }
            
            if(valid){
                cart.updateQuantity(quantity, cakeID);
            }
        }
        catch(NamingException e ){
            LOGGER.info("EditQuantityController_NamingException : " + e.getMessage());
        }
        catch(SQLException e ){
            LOGGER.info("EditQuantityController_SQLException : " + e.getMessage());
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
