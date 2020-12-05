/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JFileChooser;
import org.apache.log4j.Logger;
import thinlm.daos.CakeDAO;
import thinlm.date.MyDate;
import thinlm.dtos.AccountDTO;
import thinlm.dtos.CakeDTO;

/**
 *
 * @author Admin
 */
public class ChooseImageController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(ChooseImageController.class);

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
            HttpSession session = request.getSession();
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACCOUNT");
            boolean isAdmin = false;
            if(accountDTO != null){
                if(accountDTO.getRole().equals("admin")){
                    isAdmin = true;
                }
            }
            if (isAdmin) {
                JFileChooser jFileChooser = new JFileChooser();
                int index = jFileChooser.showOpenDialog(jFileChooser);
                String action = request.getParameter("action");
                File f = null;
                if (index == JFileChooser.APPROVE_OPTION) {
                    f = jFileChooser.getSelectedFile();
                }
                request.setAttribute("FILE_PATH", f.getAbsolutePath());
                if (action.equals("Choose Image Create")) {
                    request.setAttribute("IS_SHOW_CREATE", true);
                }
                if (action.equals("Choose Image Update")) {
                    CakeDAO cakeDAO = new CakeDAO();
                    String cakeID = request.getParameter("txtCakeID");
                    CakeDTO cakeDTO = cakeDAO.getCakeByID(cakeID);
                    cakeDTO.setImage(f.getAbsolutePath());
                    MyDate date = new MyDate();
                    date.setDateOfCreate(cakeDTO.getDateOfCreate().toString().split(" ")[0]);
                    date.setExpirationDate(cakeDTO.getExpirationDate().toString().split(" ")[0]);
                    request.setAttribute("MY_DATE", date);
                    request.setAttribute("CAKE_TO_UPDATE", cakeDTO);
                    request.setAttribute("IS_SHOW_UPDATE", true);
                    request.setAttribute("IS_CHANGE", "true");
                }

            } else {
                request.setAttribute("COSTUMER_ERROR", "Costumer can't update product");
                request.setAttribute("IS_SHOW_COSTUMER_MSG", "block");
                LOGGER.info("AddtoCartController_Exception : Costumer can't update product");
            }
        } catch (NullPointerException e) {
            LOGGER.info("ChooseImageController_NullPointerException: " + e.getMessage());
        } catch (NamingException e) {
            LOGGER.info("ChooseImageController_NamingException : " + e.getMessage());
        } catch (SQLException e) {
            LOGGER.info("ChooseImageController_SQLException : " + e.getMessage());
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
