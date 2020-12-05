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
import thinlm.customer.Customer;
import thinlm.customer.CustomerProduct;
import thinlm.daos.CakeDAO;
import thinlm.dtos.AccountDTO;
import thinlm.dtos.OrderDTO;
import thinlm.error.ErrorObj;

/**
 *
 * @author Admin
 */
public class SubCheckOutAllController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(SubCheckOutAllController.class);
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
        boolean valid = true;
        ArrayList<ErrorObj> listError = null;
        try {
            String name = request.getParameter("txtName");
            String address = request.getParameter("txtAddress");
            String phoneNo = request.getParameter("txtPhoneNo");
            String payment = request.getParameter("txtPayment");
            
            HttpSession session = request.getSession();
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACCOUNT");
            boolean isAdmin = false;
            if(accountDTO != null){
                if(accountDTO.getRole().equals("admin")){
                    isAdmin = true;
                }
            }
            if(!isAdmin){
                ErrorObj error = new ErrorObj();
                listError = new ArrayList<>();
                Cart cart = (Cart) session.getAttribute("CART");
                CakeDAO cakeDAO = new CakeDAO();
                for (String cakeID : cart.getShoppingCart().keySet()) {
                    int quantityInSystem = cakeDAO.getCakeByID(cakeID).getQuantity();
                    if(cart.getShoppingCart().get(cakeID) > quantityInSystem){
                        error.setQuantityError("Sorry, we only have " + quantityInSystem + "cake(s) left in our store");
                        error.setCakeIDError(cakeID);
                        listError.add(error);
                        valid = false;
                    }
                }
                if(valid){
                    url = "order.jsp";
                    //if there no error for the quantity, forward to view order page
                    Customer customer = new Customer();
                    customer.setAddress(address);
                    customer.setName(name);
                    customer.setPaymentMethod(payment);
                    customer.setPhoneNo(phoneNo);
                    customer.setPaymentStatus("on-progress");
                    ArrayList<CustomerProduct> listProducts = new ArrayList<>();
                    for (String cakeID : cart.getShoppingCart().keySet()) {
                        CustomerProduct customerProduct = new CustomerProduct();
                        customerProduct.setCakeName(cakeDAO.getCakeByID(cakeID).getName());
                        customerProduct.setQuantity(cart.getShoppingCart().get(cakeID));
                        listProducts.add(customerProduct);
                    }
                    OrderDTO orderDTO = new OrderDTO();
                    orderDTO.setDateOfCreate(new Timestamp(new Date().getTime()));
                    
                    if(accountDTO != null){
                        if(accountDTO.getRole().equals("member")){
                            orderDTO.setOrderID(orderDTO.getDateOfCreate() + "_M_" + accountDTO.getEmail());
                        }
                    }
                    else{
                        orderDTO.setOrderID(orderDTO.getDateOfCreate() + "_G_" + name);
                    }
                    
                    request.setAttribute("CUSTOMER", customer);
                    request.setAttribute("ORDER", orderDTO);
                    request.setAttribute("TOTAL", cart.getTotal());
                    request.setAttribute("LIST_CAKE_ORDER", listProducts);
                }
            }
            else{
                url = "PagingController";
                request.setAttribute("ADMIN_ERROR", "Admin can't not go shopping");
                request.setAttribute("IS_SHOW_ADMIN_MSG", "block");
                LOGGER.info("AddtoCartController_Exception : admin can't go shopping");
            }
            
        } catch (NamingException e) {
            LOGGER.info("SubCheckOutAllController_NamingException : " + e.getMessage());
        }
        catch(SQLException e){
            LOGGER.info("SubCheckOutAllController_SQLException : " + e.getMessage()); 
        }
        finally{
            if(!valid){
                url = "ViewCartController";
                request.setAttribute("LIST_QUANTITY_ERROR", listError);
            }
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
