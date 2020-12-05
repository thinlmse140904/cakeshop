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
import static thinlm.controllers.CheckOutAllController.LOGGER;
import thinlm.customer.Customer;
import thinlm.customer.CustomerProduct;
import thinlm.daos.CakeDAO;
import thinlm.daos.OrderDAO;
import thinlm.daos.OrderDetailDAO;
import thinlm.dtos.AccountDTO;
import thinlm.dtos.OrderDTO;
import thinlm.dtos.OrderDetailDTO;

/**
 *
 * @author Admin
 */
public class ViewOrderDetail extends HttpServlet {

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
        String url = "order.jsp";
        try {
            HttpSession session = request.getSession();
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACCOUNT");
            boolean isMember = false;
            if (accountDTO != null) {
                if (accountDTO.getRole().equals("member")) {
                    isMember = true;
                }
            }
            if (isMember) {
                CakeDAO cakeDAO = new CakeDAO();
                OrderDAO orderDAO = new OrderDAO();
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                String orderID = request.getParameter("txtOrderID");
                float total = Float.parseFloat(request.getParameter("txtTotal"));
                Customer customer = new Customer();
                customer.setAddress(accountDTO.getAddress());
                customer.setName(accountDTO.getName());

                OrderDTO orderDTO = orderDAO.getOrderByOrderID(orderID);
                ArrayList<OrderDetailDTO> list = orderDetailDAO.getOrderDetailByOrderID(orderID);
                ArrayList<CustomerProduct> listProducts = new ArrayList<>();
                for (OrderDetailDTO orderDetailDTO : list) {
                    CustomerProduct customerProduct = new CustomerProduct();
                    customerProduct.setCakeName(cakeDAO.getCakeByID(orderDetailDTO.getCakeID()).getName());
                    customerProduct.setQuantity(orderDetailDTO.getQuantity());
                    listProducts.add(customerProduct);
                }
                request.setAttribute("CUSTOMER", customer);
                request.setAttribute("ORDER", orderDTO);
                request.setAttribute("TOTAL", total);
                request.setAttribute("LIST_CAKE_ORDER", listProducts);
            } else {
                request.setAttribute("COSTUMER_ERROR", "Only member can use this function !!!");
                request.setAttribute("IS_SHOW_COSTUMER_MSG", "block");
                LOGGER.info("ViewOrderDetailController_Exception : Only member can use this function !!!");
                url = "PagingController";
            }
        } catch (Exception e) {
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
