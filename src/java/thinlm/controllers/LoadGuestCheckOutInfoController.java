/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.controllers;

import java.io.IOException;
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
public class LoadGuestCheckOutInfoController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(LoadGuestCheckOutInfoController.class);
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
        try {
            HttpSession session = request.getSession();
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACCOUNT");
            boolean isAdmin = false;
            if(accountDTO != null){
                if(accountDTO.getRole().equals("admin") && accountDTO.getRole().equals("member")){
                    isAdmin = true;
                }
            }
            if(!isAdmin){
            Cart cart = (Cart) session.getAttribute("CART");
            String cakeID = request.getParameter("txtCakeID");
            //parse quantity String type from String to Integer
            String tmp = request.getParameter("txtQuantity");
            int quantity = Integer.parseInt(tmp);
            ErrorObj errorObj = new ErrorObj();
            boolean valid = true;
            
            //validate quantity
            CakeDAO cakeDAO = new CakeDAO();
            CakeDTO cakeDTO = cakeDAO.getCakeByID(cakeID);
            if (quantity < 0) {
                errorObj.setQuantityError("quantity must be a positive number");
                LOGGER.info("EditQuantityController_NumberFormatException : quantity must be a positive number");
                valid = false;
            }

            //quantity must not pass the quantity of available cakes in store
            if (quantity > cakeDTO.getQuantity()) {
                errorObj.setQuantityError("sorry, we only have " + cakeDTO.getQuantity() + " cakes left in our store");
                LOGGER.info("EditQuantityController_NumberFormatException : quantity must smaller than " + cakeDTO.getQuantity());
                valid = false;
            }
            //a attribute is set for cake ID wether true or false
            //if true : cake id will be used for check out
            //if false : cake id will be used for displaying error
            request.setAttribute("CAKE_ID", cakeID);
            if(valid){
                cart.updateQuantity(quantity, cakeID);
                request.setAttribute("IS_SHOW_GUEST_INFO", true);
                request.setAttribute("QUANTITY", quantity);
                
            }
            else{
                request.setAttribute("ERROR_QUANTITY", errorObj);
            }
            }else{
                url = "PagingController";
                request.setAttribute("ADMIN_ERROR", "logged in account don't need to provide more information");
                request.setAttribute("IS_SHOW_ADMIN_MSG", "block");
                LOGGER.info("AddtoCartController_Exception : Logged in account can't use this function product");
            }
        } catch (Exception e) {
            LOGGER.info("LoadGuestCheckOutInfoController_Exception : " + e.getMessage());
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
