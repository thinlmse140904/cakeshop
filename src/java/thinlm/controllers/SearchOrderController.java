/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import thinlm.daos.OrderDAO;
import thinlm.daos.PaymentDAO;
import thinlm.dtos.AccountDTO;
import thinlm.dtos.OrderDTO;
import thinlm.items.OrderItems;

/**
 *
 * @author Admin
 */
public class SearchOrderController extends HttpServlet {
    final static Logger LOGGER = Logger.getLogger(SearchController.class);
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
        try {
            HttpSession session = request.getSession();
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACCOUNT");
            boolean isMember = false;
            if(accountDTO != null){
                if(accountDTO.getRole().equals("member")){
                    isMember = true;
                }
            }
            if(isMember){
                String orderID = request.getParameter("txtOrderID");
                System.out.println(orderID);
                PaymentDAO paymentDAO = new PaymentDAO();
                OrderDAO orderDAO = new OrderDAO();
                ArrayList<OrderDTO> list = orderDAO.getOrderByIDOnThisAccount(orderID, accountDTO.getEmail());
                ArrayList<OrderItems> listToShow = new ArrayList<>();
                for (OrderDTO orderDTO : list) {
                    System.out.println(orderDTO.getOrderID());
                    OrderItems orderItems = new OrderItems();
                    orderItems.setDateOfCreate(orderDTO.getDateOfCreate());
                    orderItems.setOrderID(orderDTO.getOrderID());
                    orderItems.setPayment(paymentDAO.getPaymentMethodByOrderID(orderID).getPayment());
                    orderItems.setTotal(orderDTO.getTotal());
                    listToShow.add(orderItems);
                }
                request.setAttribute("ORDER_LIST", listToShow);
            }
            else{
                LOGGER.info("Only member can use this function");
                url = "PagingContoller";
                request.setAttribute("COSTUMER_ERROR", "Only member can track their order");
                request.setAttribute("IS_SHOW_COSTUMER_MSG", "block");
            }
        } catch (Exception e) {
            LOGGER.info("SearchOrderController_Exception : " + e.getMessage());
        }
        finally{
            request.getRequestDispatcher("orderTracking.jsp").forward(request, response);
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
