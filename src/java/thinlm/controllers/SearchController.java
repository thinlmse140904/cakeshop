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
public class SearchController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(SearchController.class);
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
        try{
            //we to set an atrribute to the server, so that when user hit page button
            //it won't cancel the search process(show result that haven't gone through filter)
            HttpSession session = request.getSession();
           
            
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACCOUNT");
            boolean isAdmin = false;
            if(accountDTO != null){
                if(accountDTO.getRole().equals("admin")){
                    isAdmin = true;
                }
            }
            String search = request.getParameter("txtSearch");
            String category = request.getParameter("txtCategory");
            String isCheck = request.getParameter("txtIsCheck");
            String tmp = request.getParameter("action");
            //case user doesn't hit page button, then default will be first page of search result
            if(tmp == null){
                tmp ="1";
            }
            
            int page = Integer.parseInt(tmp.split("_")[0]);
            if(page == 0){
                page = 1;
            }
            int pageFloor = (page - 1) * 12;
            CakeDAO cakeDAO = new CakeDAO();
            if(category.equals("All") ){
                category = "";
            }
            //search for cake with name and category
            ArrayList<CakeDTO> listToShow = new ArrayList<>();
            if(isCheck == null){
                if(!isAdmin){
                    listToShow = cakeDAO.searchCakeWithNameAndCategoryForCostumer(search, category, pageFloor);
                }
                else{
                    listToShow = cakeDAO.searchCakeWithNameAndCategory(search, category,pageFloor);
                }
            }
            //copy cake to list that need to show
            //if user check the filter with price check box
            //system will search for another list cake 
            //the result should be show will be the familiar cake between 2 lists
            if(isCheck != null){
                String tmpFloor = request.getParameter("txtPriceFloor");
                String tmpCeil = request.getParameter("txtPriceCeil");
                
                //in case user input nothing, default for floor value is 0 and ceil value is (2^31)-1
                if(tmpFloor.trim().length() == 0){
                    tmpFloor = "0";
                }
                if(tmpCeil.trim().length() == 0){
                    tmpCeil = "" + Integer.MAX_VALUE;
                }
                float priceFloor = Float.parseFloat(tmpFloor);
                float priceCeil = Float.parseFloat(tmpCeil);
                listToShow = cakeDAO.searchCakeWithPriceAndCategoryAndName(priceFloor, priceCeil, search, category,pageFloor);
            }
            session.setAttribute("ON_SEARCH_PROCESS", true);
            request.setAttribute("LIST_CAKE", listToShow);
        }
        catch(SQLException e){
            LOGGER.info("SearchController_SQLException : " + e.getMessage());
            e.printStackTrace();
        }
        catch(NamingException e){
            LOGGER.info("SearchController_NamingException : " + e.getMessage());
        }
        finally{
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
