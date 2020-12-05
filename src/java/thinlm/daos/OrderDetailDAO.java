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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.naming.NamingException;
import thinlm.dtos.OrderDetailDTO;
import thinlm.utils.MyConnection;

/**
 *
 * @author Admin
 */
public class OrderDetailDAO implements Serializable{
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
    
    public boolean createOrderDetail(OrderDetailDTO orderDetailDTO) throws  SQLException, NamingException{
        boolean result = false;
        try{
            String sql = "INSERT INTO tbl_OrderDetail(detailID , orderID, cakeID, quantity,dateOfCreate) VALUES (?,?,?,?,?)";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            Date date = new Date();
            Timestamp time1 = new Timestamp(date.getTime());
            pst.setString(1, orderDetailDTO.getDetailID());
            pst.setString(2, orderDetailDTO.getOrderID());
            pst.setString(3, orderDetailDTO.getCakeID());
            pst.setInt(4, orderDetailDTO.getQuantity());
            pst.setTimestamp(5, time1);
            
            result = pst.executeUpdate() > 0;
            
        }
        finally{
            closeConnection();
        }
        return result;
    }
    public String getOrderDetailID() throws NamingException, SQLException{
        String result = "";
        try{
            String sql = "SELECT detailID FROM tbl_OrderDetail WHERE dateOfCreate = (SELECT MAX (dateOfCreate) FROM tbl_OrderDetail)";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                result = rs.getString("detailID");
            }
        }
        finally{
            closeConnection();
        }
        return result;
    }
    public ArrayList<OrderDetailDTO> getOrderDetailByOrderID(String orderID) throws NamingException, SQLException{
        ArrayList<OrderDetailDTO> list = new ArrayList<>();
        try{
            String sql = "SELECT cakeID, quantity, dateOfCreate, detailID FROM tbl_OrderDetail WHERE orderID = ?";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, orderID);
            rs = pst.executeQuery();
            while(rs.next()){
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setCakeID(rs.getString("cakeID"));
                orderDetailDTO.setDateOfCreate(rs.getTimestamp("dateOfCreate"));
                orderDetailDTO.setDetailID(rs.getString("detailID"));
                orderDetailDTO.setOrderID(orderID);
                orderDetailDTO.setQuantity(rs.getInt("quantity"));
                list.add(orderDetailDTO);
            }
        }
        finally{
            closeConnection();
        }
        return list;
    }
}
