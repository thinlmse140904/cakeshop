/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import thinlm.dtos.OrderDTO;
import thinlm.utils.MyConnection;

/**
 *
 * @author Admin
 */
public class OrderDAO implements Serializable{
    ResultSet rs ;
    PreparedStatement pst;
    Connection cn;
    
    private void closeConnection() throws SQLException{
        if(rs != null){
            rs.close();
        }
        if(pst != null){
            pst.close();
        }
        if(cn != null){
            cn.close();
        }
    }
    public boolean createOrder(OrderDTO orderDTO) throws NamingException, SQLException{
        boolean result = false;
        try{
            String sql = "INSERT INTO tbl_Order(orderID ,email, phoneNo, address, dateOfCreate, total,name) VALUES (?,?,?,?,?,?,?)";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, orderDTO.getOrderID());
            pst.setString(2, orderDTO.getEmail());
            pst.setString(3, orderDTO.getPhoneNo());
            pst.setString(4, orderDTO.getAddress());
            pst.setTimestamp(5, orderDTO.getDateOfCreate());
            pst.setFloat(6, orderDTO.getTotal());
            pst.setString(7, orderDTO.getName());
            
            result = pst.executeUpdate() > 0;
            
        }
        finally{
            closeConnection();
        }
        return result;
    }
    public ArrayList<OrderDTO> getOrderByIDOnThisAccount(String orderID, String email) throws NamingException, SQLException{
        ArrayList<OrderDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT email, phoneNo, address, dateOfCreate, total,name,orderID FROM tbl_Order WHERE orderID LIKE ? AND email = ?";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1,"%" + orderID + "%");
            pst.setString(2, email);
            rs = pst.executeQuery();
            while(rs.next()){
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setAddress(rs.getString("address"));
                orderDTO.setDateOfCreate(rs.getTimestamp("dateOfCreate"));
                orderDTO.setEmail(rs.getString("email"));
                orderDTO.setName(rs.getString("name"));
                orderDTO.setOrderID(rs.getString("orderID"));
                orderDTO.setPhoneNo(rs.getString("phoneNo"));
                orderDTO.setTotal(rs.getFloat("total"));
                list.add(orderDTO);
            }
        } finally{
            closeConnection();
        }
        return list;
    }
    public OrderDTO getOrderByOrderID(String orderID) throws NamingException, SQLException{
        OrderDTO orderDTO = null;
        try {
            String sql = "SELECT orderID ,email, phoneNo, address, dateOfCreate, total,name FROM tbl_Order WHERE orderID = ?";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, orderID);
            rs = pst.executeQuery();
            if(rs.next()){
                orderDTO = new OrderDTO();
                orderDTO.setAddress(rs.getString("address"));
                orderDTO.setDateOfCreate(rs.getTimestamp("dateOfCreate"));
                orderDTO.setEmail(rs.getString("email"));
                orderDTO.setName(rs.getString("name"));
                orderDTO.setOrderID(rs.getString("orderID"));
                orderDTO.setPhoneNo(rs.getString("phoneNo"));
                orderDTO.setTotal(rs.getFloat("total"));
            }
        } finally{
            closeConnection();
        }
        return orderDTO;
    }
}
