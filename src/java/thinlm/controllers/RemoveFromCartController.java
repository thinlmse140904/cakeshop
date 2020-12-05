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
import thinlm.daos.CakeDAO;
import thinlm.dtos.AccountDTO;
import thinlm.dtos.CakeDTO;

/**
 *
 * @author Admin
 */
//this servlet not really remove cake from cart
//only load information of cake that need to remove
public class RemoveFromCartController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(RemoveFromCartController.class);

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
            if (accountDTO != null) {
                if (accountDTO.getRole().equals("admin")) {
                    isAdmin = true;
                }
            }
            if (!isAdmin) {
                String quantity = request.getParameter("txtQuantity");
                String cakeID = request.getParameter("txtCakeID");
                CakeDAO cakeDAO = new CakeDAO();
                CakeDTO cakeDTO = cakeDAO.getCakeByID(cakeID);
                request.setAttribute("QUANTITY", quantity);
                request.setAttribute("CAKE_DTO", cakeDTO);
                request.setAttribute("IS_SHOW_CONFIRM", true);
            }
            else{
                url = "PagingController";
                request.setAttribute("ADMIN_ERROR", "Admin can't use this function");
                request.setAttribute("IS_SHOW_ADMIN_MSG", "block");
                LOGGER.info("AddtoCartController_Exception : Admin can't use this function"); 
            }
        } catch (NamingException e) {
            LOGGER.info("RemoveFromCartController_NamingException : " + e.getMessage());
        } catch (SQLException e) {
            LOGGER.info("RemoveFromCartController_SQLException : " + e.getMessage());
        } finally {
            request.getRequestDispatcher("ViewCartController").forward(request, response);
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
