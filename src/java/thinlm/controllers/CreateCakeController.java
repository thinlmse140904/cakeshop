/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import thinlm.error.ErrorObj;
import thinlm.utils.MyFile;

/**
 *
 * @author Admin
 */
public class CreateCakeController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(CreateCakeController.class);
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
        ErrorObj error = new ErrorObj();
        HttpSession session = request.getSession();
        boolean valid = true;
        try {
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACCOUNT");
            boolean isAdmin = false;
            if(accountDTO != null){
                if(accountDTO.getRole().equals("admin")){
                    isAdmin = true;
                }
            }
            if(isAdmin){
            String name = request.getParameter("txtCakeName");
            Timestamp dateOfCreate = Timestamp.valueOf(request.getParameter("txtDateOfCreate") + " 00:00:00.00");
            Timestamp expirationDate = Timestamp.valueOf(request.getParameter("txtExpirationDate") + " 00:00:00.00");
            String category = request.getParameter("txtCategory");
            float price = Float.parseFloat(request.getParameter("txtPrice"));
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            String image = request.getParameter("txtImage");
            CakeDAO cakeDAO = new CakeDAO();
            
            //Validation 
            
            
            if(name.trim().length() == 0){
                error.setNameError("Cake's name cant be blank !");
                valid = false;
            }
            if(dateOfCreate.toString().length() == 0){
                error.setDateError("Create date can't be a blank");
                valid = false;
            }
            if(expirationDate.toString().length() == 0) {
                error.setExpirationDateError("Expiration date can't be a blank!");
                valid = false;
            }
            if(expirationDate.compareTo(dateOfCreate) <=0){
                error.setDateError("Expiration date must greater than date of create");
                valid = false;
            }
            if(category.trim().length() == 0 ){
                error.setCategoryName("Category can't be a blank!");
                valid = false;
            }
            if(price < 0){
                error.setPriceError("Price must be positive!");
                valid = false;
            }
            if(quantity < 0){
                error.setQuantityError("Quantity must be a positive!");
                valid = false;
            }
            if(image.trim().length() == 0){
                error.setImageError("Image can't be a blank");
                valid = false;
            }
            if(!image.contains(".png") && !image.contains(".jpg") && !image.contains(".img")){
                error.setImageError("this is not a image");
                valid = false;
            }
            //if everything is fine, copy image to system directory
            if (valid) {
                File source = new File(image);
                String fileName = source.getName();
                File dest = new File("C:\\Users\\Admin\\Documents\\NetBeansProjects\\MoonCakeShop\\web\\image\\cake\\" + fileName);
                //create a link of image for <img> tags on jsp page
                image = "image\\cake\\" + fileName;
                MyFile.copyFileUsingStream(source, dest);
                CakeDTO cakeDTO = new CakeDTO(name, price, image, dateOfCreate, expirationDate, quantity, category);
                if (cakeDAO.createCake(cakeDTO)) {
                }
            }
            }
            else{
                request.setAttribute("COSTUMER_ERROR", "Costumer can't create product");
                request.setAttribute("IS_SHOW_COSTUMER_MSG", "block");
                LOGGER.info("AddtoCartController_Exception : Costumer can't Create product");
            }
        } catch (NamingException e) {
            LOGGER.info("CreateCakeController_NamingException : " + e.getMessage());
        } 
        catch(SQLException e){
            LOGGER.info("CreateCakeController_SQLException: " + e.getMessage());
        }
        catch(IllegalArgumentException e){
            valid = false;
            error.setDateError("date can't be a blank");
            LOGGER.info("CreateCakeController_IllegalArgumentException: " + e.getMessage());
        }
        finally {
            
            if(!valid){
                session.setAttribute("ERROR_CREATE", error);
            }
            else{
                session.removeAttribute("ERROR_CREATE");
            }
            response.sendRedirect("Paging");
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
