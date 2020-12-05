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
import javax.naming.NamingException;
import thinlm.dtos.UpdateDTO;
import thinlm.utils.MyConnection;

/**
 *
 * @author Admin
 */
public class UpdateDAO {
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
    
    public boolean checkUpdate(String email, String cakeID) throws NamingException, SQLException{
        boolean result = false;
        try{
            String sql = "SELECT updateID FROM tbl_Update WHERE email = ? and cakeID = ?";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, cakeID);
            rs = pst.executeQuery();
            if(rs.next()){
                result = true;
            }
        }
        finally{
            closeConnection();
        }
        return result;
    }
    
    public boolean createUpdateDetail(UpdateDTO updateDTO) throws NamingException, SQLException{
        boolean result = false;
        try{
            String sql = "INSERT INTO tbl_Update(updateID,cakeID,email,lastUpdateDate) VALUES (?,?,?,?)";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, updateDTO.getUpdateID());
            pst.setString(2, updateDTO.getCakeID());
            pst.setString(3, updateDTO.getEmail());
            pst.setTimestamp(4, updateDTO.getLastUpdateDate());
            
            result = pst.executeUpdate() > 0;
            
        }
        finally{
            closeConnection();
        }
        return result;
    }
    public boolean updateLastUpdateDate(UpdateDTO updateDTO) throws NamingException, SQLException{
        boolean result = false;
        try{
            String sql = "UPDATE tbl_Update SET lastUpdateDate = ? WHERE email = ? and cakeID = ?";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setTimestamp(1, updateDTO.getLastUpdateDate());
            pst.setString(2, updateDTO.getEmail());
            pst.setString(3, updateDTO.getCakeID());
            result = pst.executeUpdate() > 0;
        }
        finally{
            closeConnection();
        }
        return result;
    }
}
