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
import thinlm.daos.CakeDAO;
import thinlm.dtos.AccountDTO;
import thinlm.dtos.CakeDTO;

/**
 *
 * @author Admin
 */
public class LoadPageController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(LoadPageController.class);
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
        String url = "index.jsp";
        try {
            
            //in case user hit home button, cancle the searching process
            HttpSession session = request.getSession();
            session.removeAttribute("ON_SEARCH_PROCESS");
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACCOUNT");
            CakeDAO cakeDAO = new CakeDAO();
            String tmp = request.getParameter("action");
            //in case user doesn't hit page button then by default system will show 1st page
            if(tmp == null){
                tmp = "1";
            }
            int page = Integer.parseInt(tmp.split("_")[0]);
            int cakeFloor = (page - 1) * 12;
            ArrayList<CakeDTO> listCakeToShow ;
            if(accountDTO == null || accountDTO.getRole().equals("member")){
                listCakeToShow = cakeDAO.getCakeInPageForCostumer(cakeFloor);
            }
            else{
                listCakeToShow = cakeDAO.getCakeInPage(cakeFloor);
            }
            request.setAttribute("LIST_CAKE", listCakeToShow);
        }
        catch(NullPointerException e){
            LOGGER.info("LoadPageController_NullPointerException : " + e.getMessage());
            e.printStackTrace();
        }
        catch(SQLException e){
            LOGGER.info("LoadPageController_SQLException : " + e.getMessage());
        }
        catch(NamingException e){
            LOGGER.info("LoadPageController_NamingException : " + e.getMessage());
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
