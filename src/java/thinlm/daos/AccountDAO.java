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
import javax.naming.NamingException;
import thinlm.dtos.AccountDTO;
import thinlm.utils.MyConnection;

/**
 *
 * @author Admin
 */
public class AccountDAO implements Serializable{
    ResultSet rs;
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
    public AccountDTO checkLogin(String email,String password) throws NamingException, SQLException{
        AccountDTO accountDTO = null;
        try{
            String sql = "SELECT name, address , phoneNo, avatar, role, status FROM tbl_Account_1 where email = ? and password = ?";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if(rs.next()){
                accountDTO = new AccountDTO();
                accountDTO.setAddress(rs.getString("address"));
                accountDTO.setAvatar(rs.getString("avatar"));
                accountDTO.setEmail(email);
                accountDTO.setName(rs.getString("name"));
                accountDTO.setPassword(password);
                accountDTO.setPhoneNo(rs.getString("phoneNo"));
                accountDTO.setRole(rs.getString("role"));
                accountDTO.setStatus(rs.getString("status"));
            }
        }
        finally{
            closeConnection();
        }
        return accountDTO;
    }
//    public boolean signUpAccount=]
    public String getIDForGuest() throws NamingException, SQLException{
        String id = "1";
        try{
            String sql = "SELECT email from tbl_Account_1 WHERE status = ? AND dateOfCreate = (SELECT MAX(dateOfCreate) FROM tbl_Account_1 WHERE status = ?)";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, "guest");
            pst.setString(2, "guest");
            rs = pst.executeQuery();
            if(rs.next()){
                int tmp = Integer.parseInt(rs.getString("email")) + 1;
                id = tmp +"";
            }
        }
        finally{
            closeConnection();
        }
        return id;
    }
    public boolean createGuestAccount(AccountDTO guestAccount) throws NamingException, SQLException{
        boolean result = false;
        try {
            String sql = "INSERT INTO tbl_Account_1(name,email,address,phoneNo,status,role,dateOfCreate) VALUES(?,?,?,?,?,?,?)";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, guestAccount.getName());
            pst.setString(2, guestAccount.getEmail());
            pst.setString(3, guestAccount.getAddress());
            pst.setString(4, guestAccount.getPhoneNo());
            pst.setString(5, guestAccount.getStatus());
            pst.setString(6, guestAccount.getRole());
            pst.setTimestamp(7, guestAccount.getDateOfCreate());
            result = pst.executeUpdate() > 0;
        } finally{
            closeConnection();
        }
        return result;
    }
    public boolean checkEmailExist(String email) throws NamingException, SQLException{
        boolean result = false;
        try {
            String sql = "SELECT email FROM tbl_Account_1 WHERE status = ? AND role = ?";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, "member");
            pst.setString(2, "member");
            rs = pst.executeQuery();
            while(rs.next()){
                if(rs.getString("email").equals(email)){
                    result = true;
                }
            }
        } finally{
            closeConnection();
        }
        return result;
    }
    public AccountDTO getAccountByEmail(String email) throws NamingException, SQLException{
        AccountDTO accountDTO = null;
        try{
            String sql = "SELECT name, address , phoneNo, avatar, role, status FROM tbl_Account_1 where email = ? ";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if(rs.next()){
                accountDTO = new AccountDTO();
                accountDTO.setAddress(rs.getString("address"));
                accountDTO.setAvatar(rs.getString("avatar"));
                accountDTO.setEmail(email);
                accountDTO.setName(rs.getString("name"));
                accountDTO.setPhoneNo(rs.getString("phoneNo"));
                accountDTO.setRole(rs.getString("role"));
                accountDTO.setStatus(rs.getString("status"));
            }
        }
        finally{
            closeConnection();
        }
        return accountDTO;
    }
}
