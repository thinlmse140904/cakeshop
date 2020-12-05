/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.controllers;

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
import thinlm.cart.Cart;
import thinlm.daos.CakeDAO;
import thinlm.daos.OrderDAO;
import thinlm.daos.OrderDetailDAO;
import thinlm.daos.PaymentDAO;
import thinlm.dtos.AccountDTO;
import thinlm.dtos.CakeDTO;
import thinlm.dtos.OrderDTO;
import thinlm.dtos.OrderDetailDTO;
import thinlm.dtos.PaymentDTO;
import thinlm.error.ErrorObj;

/**
 *
 * @author Admin
 */
public class CheckOutController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(CheckOutController.class);

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
            AccountDTO accountDTO= (AccountDTO) session.getAttribute("ACCOUNT");
            boolean isAdmin = false;
            if(accountDTO != null){
                if(accountDTO.getRole().equals("admin")){
                    isAdmin = true;
                }
            }
            if(!isAdmin){
            String cakeID = request.getParameter("txtCakeID");
            //parse quantity value from String to Int
            String tmp = request.getParameter("txtQuantity");
            int quantity = Integer.parseInt(tmp);
            ErrorObj errorObj = new ErrorObj();
            String payment = request.getParameter("txtPayment");
            boolean valid = true;
            CakeDAO cakeDAO = new CakeDAO();
            CakeDTO cakeDTO = cakeDAO.getCakeByID(cakeID);
            String name = request.getParameter("txtName");
            String address = request.getParameter("txtAddress");
            String phoneNo = request.getParameter("txtPhoneNo");
            
            //validdate for guest 
            boolean guestValid = true;
            if (accountDTO != null) {
                if (!accountDTO.getRole().equals("member")) {

                    if (name.trim().length() == 0) {
                        errorObj.setNameError("Name can't be a blank");
                        guestValid = false;
                    }
                    if (address.trim().length() == 0) {
                        errorObj.setAddressError("Address can't be a blank");
                        guestValid = false;
                    }
                    if (phoneNo.trim().length() == 0) {
                        errorObj.setPhoneNoError("Phone No can't be a blank");
                        guestValid = false;
                    }
                }
            }
            //quantity must be a positive number
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

            if (valid) {
                
                PaymentDAO paymentDAO = new PaymentDAO();
                Cart cart = (Cart) session.getAttribute("CART");

                boolean isLoginAsMember = false;
                if (accountDTO != null) {
                    if (accountDTO.getRole().equals("member")) {
                        isLoginAsMember = true;
                    }
                }
                if (isLoginAsMember || (!isLoginAsMember && guestValid)) {
                    OrderDTO orderDTO = new OrderDTO();
                    OrderDAO orderDAO = new OrderDAO();
                    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                    //get date of create for both order and order detail
                    Date date = new Date();
                    Timestamp time = new Timestamp(date.getTime());

                    //set all property for orderDTO
                    //case user don't have account : no email, they must enter address, phone number, name ,...
                    orderDTO.setDateOfCreate(time);
                    orderDTO.setTotal(cakeDTO.getPrice() * quantity);
                    if (!isLoginAsMember) {
                        orderDTO.setAddress(address);
                        orderDTO.setName(name);
                        orderDTO.setPhoneNo(phoneNo);
                        orderDTO.setOrderID(orderDTO.getDateOfCreate() + "_G_" + orderDTO.getPhoneNo());
                        orderDTO.setEmail("");
                    } 
                    //if user have login then order will have information base on their account in4
                    else {
                        orderDTO.setName(accountDTO.getName());
                        orderDTO.setAddress(accountDTO.getAddress());
                        orderDTO.setEmail(accountDTO.getEmail());
                        orderDTO.setPhoneNo(accountDTO.getPhoneNo());
                        orderDTO.setOrderID(orderDTO.getDateOfCreate() + "_M_" + accountDTO.getEmail());
                    }
                    //date_M_email_num
                    //set all property for orderDetailDTO
                    OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                    //define id for detail                        
                    String id = orderDetailDAO.getOrderDetailID();
                    int orderNo = 0;
                    if (id.trim().length() > 0) {
                        orderNo = Integer.parseInt(id.split("_")[1]) + 1;
                    }
                    id = cakeDAO.getCakeByID(cakeID).getName() + "_" + orderNo;
                    orderDetailDTO.setDateOfCreate(time);
                    orderDetailDTO.setCakeID(cakeID);
                    orderDetailDTO.setOrderID(orderDTO.getOrderID());
                    orderDetailDTO.setDetailID(id + "_" + orderNo);
                    orderDetailDTO.setQuantity(quantity);
                    

                    //set all property for payment too
                    PaymentDTO paymentDTO = new PaymentDTO();
                    paymentDTO.setOrderID(orderDTO.getOrderID());
                    paymentDTO.setPayment(payment);
                    
                    //insert to database
                    orderDAO.createOrder(orderDTO);
                    orderDetailDAO.createOrderDetail(orderDetailDTO);
                    paymentDAO.createPaymentMethod(paymentDTO);
                    //after insert done, remove this from cart!!!
                    cart.removeFromCart(cakeID);

                    //reduce the quantity of cake in store
                    int quantityInStore = cakeDAO.getCakeByID(cakeID).getQuantity();
                    int quantityAfterCheckout = quantityInStore - quantity;
                    cakeDAO.reduceQuantityForThisCake(cakeID, quantityAfterCheckout);
                }
            } else {
                request.setAttribute("CAKE_ID", cakeID);
                request.setAttribute("ERROR_QUANTITY", errorObj);
            }
            if(!guestValid){
                request.setAttribute("CAKE_ID", cakeID);
                request.setAttribute("QUANTITY", quantity);
                request.setAttribute("ERROR_GUEST", errorObj);
                request.setAttribute("IS_SHOW_GUEST_INFO", true);
            }
            }
            else{
                request.setAttribute("ADMIN_ERROR", "Admin can't not go shopping");
                request.setAttribute("IS_SHOW_ADMIN_MSG", "block");
                LOGGER.info("AddtoCartController_Exception : admin can't go shopping");
            }
        } catch (NamingException e) {
            LOGGER.info("CheckOutController_NamingException : " + e.getMessage());
        } catch (SQLException e) {
            LOGGER.info("CheckOutController_SQLException : " + e.getMessage());
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
