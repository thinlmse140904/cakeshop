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
import thinlm.daos.CategoryDAO;
import thinlm.dtos.CakeDTO;
import thinlm.dtos.CategoryDTO;
import thinlm.error.ErrorObj;

/**
 *
 * @author Admin
 */
public class PagingController extends HttpServlet {
    final static Logger LOGGER = Logger.getLogger(PagingController.class);
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
            CakeDAO cakeDAO = new CakeDAO();
            ArrayList<CakeDTO> listAllCake = cakeDAO.getAllCake();
            
            //calculate number of page(s)
            double numberOfCake = listAllCake.size();
            int numberOfPage = (int) Math.ceil(numberOfCake/12);
            HttpSession session = request.getSession();
            
            //set the number of page to the session scope
            session.setAttribute("NUMBER_OF_PAGE", numberOfPage);
            ErrorObj error = (ErrorObj) session.getAttribute("ERROR_CREATE");

            CategoryDAO categoryDAO = new CategoryDAO();
            ArrayList<CategoryDTO> categoryList = categoryDAO.getAllCategory();
            
            //due to the sendredirect in CreateCakeController, we've set an attribute to the session scope
            //now we need to transfer that error from session to req scope
            session.setAttribute("CATEGORY_LIST", categoryList);
            if(error!= null){
                request.setAttribute("ERROR_CREATE", error);
                session.removeAttribute("ERROR_CREATE");
            }
        } 
        catch(NamingException e){
            LOGGER.info("PagingController_NamingException: " + e.getMessage());
        }
        catch(SQLException e){
            LOGGER.info("PagingController_SQLException: " + e.getMessage());
        }
        finally{
            request.getRequestDispatcher("LoadPageController?action=1_home_page").forward(request, response);
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
