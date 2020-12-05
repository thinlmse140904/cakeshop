/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import thinlm.cart.Cart;
import thinlm.daos.AccountDAO;
import thinlm.daos.CakeDAO;
import thinlm.daos.OrderDAO;
import thinlm.daos.OrderDetailDAO;
import thinlm.daos.PaymentDAO;
import thinlm.dtos.AccountDTO;
import thinlm.dtos.OrderDTO;
import thinlm.dtos.OrderDetailDTO;
import thinlm.dtos.PaymentDTO;
import thinlm.error.ErrorObj;

/**
 *
 * @author Admin
 */
public class CheckOutAllController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(CheckOutAllController.class);
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
        String url = "";
        boolean quantityValid = true;
        HttpSession session = request.getSession();
        ArrayList<ErrorObj> listError = new ArrayList<>();
        try {
            String name = request.getParameter("txtName");
            String address = request.getParameter("txtAddress");
            String phoneNo = request.getParameter("txtPhoneNo");
            String payment  = request.getParameter("txtPayment");
            String orderID = request.getParameter("txtOrderID");
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACCOUNT");
            boolean isAdmin = false;
            if(accountDTO != null){
                if(accountDTO.getRole().equals("admin")){
                    isAdmin = true;
                }
            }
            if(!isAdmin){
                Cart cart = (Cart) session.getAttribute("CART");

                PaymentDAO paymentDAO = new PaymentDAO();
                Date date = new Date();
                Timestamp time = new Timestamp(date.getTime());
                ErrorObj error = new ErrorObj();
                listError = new ArrayList<>();
                CakeDAO cakeDAO = new CakeDAO();
                for (String cakeID : cart.getShoppingCart().keySet()) {
                    int quantityInSystem = cakeDAO.getCakeByID(cakeID).getQuantity();
                    if(cart.getShoppingCart().get(cakeID) > quantityInSystem){
                        error.setQuantityError("Sorry, we only have " + quantityInSystem + "cake(s) left in our store");
                        error.setCakeIDError(cakeID);
                        listError.add(error);
                        //if system doesn't have enough cake for this step
                        //redirect to view Cart Controller
                        quantityValid = false;
                        }
                    }
                //if user have login, then no need to get user information
                if(quantityValid){
                    if(accountDTO != null){
                        //if user have login as admin and try to use this function 
                        //then redirect to index page
                        if(accountDTO.getRole().equals("member")){
                            //define and set all property for order
                            OrderDTO orderDTO = new OrderDTO();
                            orderDTO.setAddress(address);
                            orderDTO.setDateOfCreate(time);
                            orderDTO.setEmail(accountDTO.getEmail());
                            orderDTO.setName(name);
                            orderDTO.setOrderID(orderID);
                            orderDTO.setPhoneNo(phoneNo);
                            orderDTO.setTotal(cart.getTotal());


                            //Insert order into database
                            OrderDAO orderDAO = new OrderDAO();
                            orderDAO.createOrder(orderDTO);

                            //create order detail
                            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                            for (String cakeID : cart.getShoppingCart().keySet()) {
                                //set all property for order detail
                                //create order detail id
                                String id = orderDetailDAO.getOrderDetailID();
                                int orderNo = 0;
                                if (id.trim().length() > 0) {
                                    orderNo = Integer.parseInt(id.split("_")[1])+1;
                                }
                                id = cakeDAO.getCakeByID(cakeID).getName() + "_" + orderNo;
                                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                                orderDetailDTO.setCakeID(cakeID);
                                orderDetailDTO.setDateOfCreate(orderDTO.getDateOfCreate());
                                orderDetailDTO.setDetailID(id);
                                orderDetailDTO.setQuantity(cart.getShoppingCart().get(cakeID));
                                orderDetailDTO.setOrderID(orderDTO.getOrderID());
                                if( orderDetailDAO.createOrderDetail(orderDetailDTO)){
                                    cakeDAO.reduceQuantityForThisCake(cakeID, cakeDAO.getCakeByID(cakeID).getQuantity() - orderDetailDTO.getQuantity());
                                    url = "PagingController";
                                }
                            }
                            //define and set property for payment object
                            PaymentDTO paymentDTO = new PaymentDTO();
                            paymentDTO.setOrderID(orderDTO.getOrderID());
                            paymentDTO.setPayment(payment);

                            //insert payment obj to database
                            paymentDAO.createPaymentMethod(paymentDTO);

                        }
                        else{
                            LOGGER.info("CheckOutAllController_Warning : you must not be admin to use this function !!!");
                            url = "PagingController";
                        }
                    }
                    else{
                        AccountDAO accountDAO = new AccountDAO();
                        ErrorObj errorObj = new ErrorObj();
                        AccountDTO memberAccount = new AccountDTO();
                        memberAccount.setEmail(accountDAO.getIDForGuest());
                        memberAccount.setAddress(address);
                        memberAccount.setName(name);
                        memberAccount.setRole("guest");
                        memberAccount.setStatus("guest");
                        memberAccount.setPhoneNo(phoneNo);
                        memberAccount.setDateOfCreate(new Timestamp(new Date().getTime()));
                        accountDAO.createGuestAccount(memberAccount);
                        //validdate for guest 
                        boolean guestValid = true;

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
                        if(guestValid){
                            //if user is not admin then process checkout function
                            OrderDTO orderDTO = new OrderDTO();
                            orderDTO.setAddress(address);
                            orderDTO.setDateOfCreate(time);
                            orderDTO.setEmail(memberAccount.getEmail());
                            orderDTO.setName(name);
                            orderDTO.setPhoneNo(phoneNo);
                            orderDTO.setTotal(cart.getTotal());
                            orderDTO.setOrderID(orderID);

                            //insert into database
                            OrderDAO orderDAO = new OrderDAO();
                            orderDAO.createOrder(orderDTO);

                            //create order detail
                            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                            for (String cakeID : cart.getShoppingCart().keySet()) {
                                //set all property for order detail
                                //define id for detail 
                                String id = orderDetailDAO.getOrderDetailID();
                                int orderNo = 0;
                                if (id.trim().length() > 0) {
                                    orderNo = Integer.parseInt(id.split("_")[1])+1;
                                }
                                id = cakeDAO.getCakeByID(cakeID).getName() + "_" + orderNo;
                                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                                orderDetailDTO.setCakeID(cakeID);
                                orderDetailDTO.setDetailID(id);
                                orderDetailDTO.setOrderID(orderDTO.getOrderID());
                                orderDetailDTO.setQuantity(cart.getShoppingCart().get(cakeID));
                                //if nothing happen, url'll still be view order page
                                if(orderDetailDAO.createOrderDetail(orderDetailDTO)){
                                    cakeDAO.reduceQuantityForThisCake(cakeID, cakeDAO.getCakeByID(cakeID).getQuantity() - orderDetailDTO.getQuantity());
                                    url = "PagingController";
                                }   
                            }
                            //define and set property for payment object
                            PaymentDTO paymentDTO = new PaymentDTO();
                            paymentDTO.setOrderID(orderDTO.getOrderID());
                            paymentDTO.setPayment(payment);

                            //insert payment obj to database
                            paymentDAO.createPaymentMethod(paymentDTO);

                        }


                        else{
                            request.setAttribute("ERROR_GUEST_ALL", errorObj);
                            request.setAttribute("IS_SHOW_GUEST_INFO_ALL", true);
                            url = "ViewCartController";
                        }
                    }
                }
                else{
                    
                }
            }
            else{
                request.setAttribute("ADMIN_ERROR", "Admin can't not go shopping");
                request.setAttribute("IS_SHOW_ADMIN_MSG", "block");
                LOGGER.info("AddtoCartController_Exception : admin can't go shopping");
            }
        } catch (NamingException e) {
            LOGGER.info("CheckOutAllController_NamingException : " + e.getMessage());
        }
        catch (SQLException e) {
            LOGGER.info("CheckOutAllController_SQLException : " + e.getMessage());
        }
        catch(NullPointerException e){
            url = "PagingController";
            LOGGER.info("CheckOutAllController_NullPointerException : " + e.getMessage());
        }
        finally{
            if(!quantityValid){
                url = "ViewCartController";
                request.setAttribute("LIST_QUANTITY_ERROR", listError);
            }
            session.removeAttribute("CART");
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
