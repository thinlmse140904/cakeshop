/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import thinlm.daos.AccountDAO;
import thinlm.dtos.AccountDTO;

/**
 *
 * @author Admin
 */
public class CreateAccountForGGController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(CreateAccountForGGController.class);
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
        String url = "PagingController";
        try {
            AccountDAO accountDAO = new AccountDAO();
            String email = request.getParameter("txtEmail");
            //if account haven't exist in the system then insert to the 
            if(!accountDAO.checkEmailExist(email)){
            String address = request.getParameter("txtAddress");
            String name = request.getParameter("txtName");
            String phoneNo = request.getParameter("txtPhoneNo");
            HttpSession session = request.getSession();
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACCOUNT");
            if(accountDTO != null){
                AccountDTO ggAccount = new AccountDTO();
                ggAccount.setAddress(address);
                ggAccount.setDateOfCreate(new Timestamp(new Date().getTime()));
                ggAccount.setEmail(email);
                ggAccount.setPhoneNo(phoneNo);
                ggAccount.setStatus("member");
                ggAccount.setRole("member");
                ggAccount.setName(name);
                if(accountDAO.createGuestAccount(ggAccount)){
                    session.removeAttribute("ACCOUNT");
                    session.setAttribute("ACCOUNT", ggAccount);
                }
                
                }
            else{
                request.setAttribute("COSTUMER_ERROR", "Only gmail account can use this function");
                request.setAttribute("IS_SHOW_COSTUMER_MSG", "block");
                LOGGER.info("AddtoCartController_Exception : Only gmail account can use this function");
            }
            }
            
        } catch (Exception e) {
            LOGGER.info("CreateAccountForGGController_Exception : " + e.getMessage());
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
