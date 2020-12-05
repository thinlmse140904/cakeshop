/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import thinlm.dtos.PaymentDTO;
import thinlm.utils.MyConnection;

/**
 *
 * @author Admin
 */
public class PaymentDAO {
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
    
    public boolean createPaymentMethod(PaymentDTO paymentDTO) throws NamingException, SQLException{
        boolean result = false;
        try{
            String sql = "INSERT INTO tbl_Payment(method,orderID) VALUES (?,?)";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, paymentDTO.getPayment());
            pst.setString(2, paymentDTO.getOrderID());
            result = pst.executeUpdate() > 0;
        }
        finally{
            closeConnection();
        }
        return result;
    }
    public PaymentDTO getPaymentMethodByOrderID(String orderID) throws NamingException, SQLException{
        PaymentDTO paymentDTO = new PaymentDTO();
        try {
            String sql = "SELECT method, orderID FROM tbl_Payment WHERE orderID = ?";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1,orderID);
            rs = pst.executeQuery();
            while(rs.next()){
                paymentDTO = new PaymentDTO();
                paymentDTO.setOrderID(rs.getString("orderID"));
                paymentDTO.setPayment(rs.getString("method"));
            }
        } finally{
            closeConnection();
        }
        return paymentDTO;
    }
}
