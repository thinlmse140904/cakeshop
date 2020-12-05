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
import thinlm.daos.AccountDAO;
import thinlm.dtos.AccountDTO;

/**
 *
 * @author Admin
 */
public class GoogleLoginController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(GoogleLoginController.class);
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
            String name = request.getParameter("txtName");
            String email = request.getParameter("txtEmail");
            AccountDAO accountDAO = new AccountDAO();
            if(name!= null && email != null){
                HttpSession session = request.getSession();
                AccountDTO accountDTO = new AccountDTO();
                accountDTO.setName(name);
                accountDTO.setEmail(email);
//                accountDTO.setAddress("113, Binh thanh");
//                accountDTO.setPhoneNo("0779799555");
                accountDTO.setStatus("Actived");
                accountDTO.setRole("member");
                if(!accountDAO.checkEmailExist(email)){
                    request.setAttribute("IS_SHOW_GG_INFO", "true");
                }
                else{
                    accountDTO = accountDAO.getAccountByEmail(email);
                }
                session.setAttribute("ACCOUNT", accountDTO);
                session.setAttribute("IS_LOGIN", "login");
            }
        } catch (Exception e) {
            LOGGER.info("GoogleLoginController_Exception : " + e.getMessage());
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
