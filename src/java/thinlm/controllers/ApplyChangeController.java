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
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import thinlm.daos.CakeDAO;
import thinlm.daos.UpdateDAO;
import thinlm.dtos.AccountDTO;
import thinlm.dtos.CakeDTO;
import thinlm.dtos.UpdateDTO;
import thinlm.error.ErrorObj;
import thinlm.utils.MyFile;

/**
 *
 * @author Admin
 */
//This servlet will handle the updating information of cake on system
public class ApplyChangeController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(ApplyChangeController.class);

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
        AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACCOUNT");
        boolean isAdmin = false;
            if(accountDTO != null){
                if(accountDTO.getRole().equals("admin")){
                    isAdmin = true;
                }
            }
        boolean valid = true;
        String url = "";
        try {
            if(isAdmin){
            //get parameter that need to update process
            String cakeID = request.getParameter("txtCakeID");
            String name = request.getParameter("txtCakeName");
            Timestamp dateOfCreate = Timestamp.valueOf(request.getParameter("txtDateOfCreate") + " 00:00:00.00");
            Timestamp expirationDate = Timestamp.valueOf(request.getParameter("txtExpirationDate") + " 00:00:00.00");
            String category = request.getParameter("txtCategory");
            float price = Float.parseFloat(request.getParameter("txtPrice"));
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            String image = request.getParameter("txtImage");
            String status = request.getParameter("txtStatus");
            CakeDTO cakeDTO = new CakeDTO(name, cakeID, price, image, dateOfCreate, expirationDate, quantity, category, status);
            CakeDAO cakeDAO = new CakeDAO();
            String updateID = request.getParameter("txtUpdateID");
            String isChange = request.getParameter("txtIsChange");
            //Validation 

            if (name.trim().length() == 0) {
                error.setNameError("Cake's name cant be blank !");
                valid = false;
            }
            if (dateOfCreate.toString().length() == 0) {
                error.setDateError("Create date can't be a blank");
                valid = false;
            }
            if (expirationDate.toString().length() == 0) {
                error.setExpirationDateError("Expiration date can't be a blank!");
                valid = false;
            }
            if (expirationDate.compareTo(dateOfCreate) <= 0) {
                error.setDateError("Expiration date must greater than date of create");
                valid = false;
            }
            if (category.trim().length() == 0) {
                error.setCategoryName("Category can't be a blank!");
                valid = false;
            }
            if (price < 0) {
                error.setPriceError("Price must be positive!");
                valid = false;
            }
            if (quantity < 0) {
                error.setQuantityError("Quantity must be a positive!");
                valid = false;
            }
            if (image.trim().length() == 0) {
                error.setImageError("Image can't be a blank");
                valid = false;
            }
            if (!image.contains(".png") && !image.contains(".jpg") && !image.contains(".img")) {
                error.setImageError("this is not a image");
                valid = false;
            }
            //if costumer change the image, then copy new image to the system, process the image link
            //before store in database
            if (isChange != null) {
                File source = new File(image);
                String fileName = source.getName();
                File dest = new File("C:\\Users\\Admin\\Documents\\NetBeansProjects\\MoonCakeShop\\web\\image\\cake\\" + category + "\\" + fileName);
                //create a link of image for <img> tags on jsp page
                if (source.exists()) {
                    image = "image\\cake\\" + fileName;
                    MyFile.copyFileUsingStream(source, dest);
                } else {
                    valid = false;
                    error.setImageError("this file not exist!!");
                    
                }
                cakeDTO.setImage(image);
                request.removeAttribute("IS_CHANGE");
            }

            //call DAO to update
            //if there's no missing information to update,process the update and redirect to home page
            if (valid) {
                if (cakeDAO.UpdateCake(cakeDTO)) {
                    //url = "PagingController";
                    //define updateDTO and add property
                    UpdateDAO updateDAO = new UpdateDAO();
                    UpdateDTO updateDTO = new UpdateDTO();
                    updateDTO.setCakeID(cakeID);
                    updateDTO.setEmail(accountDTO.getEmail());
                    updateDTO.setLastUpdateDate(new Timestamp(new Date().getTime()));
                    //if email have update this cake in the past, then only update information in the system, do not change the updateID
                    if (updateDAO.checkUpdate(updateDTO.getEmail(), cakeID)) {
                        updateDTO.setUpdateID(updateID);
                        if (updateDAO.updateLastUpdateDate(updateDTO)) {
                            url = "PagingController";
                        }
                    } else {
                        updateDTO.setUpdateID(updateDTO.getCakeID() + "_" + updateDTO.getEmail());
                        if (updateDAO.createUpdateDetail(updateDTO)) {
                            url = "PagingController";
                        }
                    }
                }
            } //else show update form again
            else {
                url = "UpdateCakeController?txtCakeID=" + cakeID;
                request.setAttribute("ERROR_UPDATE", error);
            }
            }
            else{
                url = "PagingController";
                request.setAttribute("COSTUMER_ERROR", "Costumer can't update product");
                request.setAttribute("IS_SHOW_COSTUMER_MSG", "block");
                LOGGER.info("AddtoCartController_Exception : Costumer can't update product");
            }
        } catch (NamingException e) {
            LOGGER.info("ApplyChangeController_NamingException : " + e.getMessage());
        } catch (SQLException e) {
            LOGGER.info("ApplyChangeController_SQLException : " + e.getMessage());
        } finally {
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
