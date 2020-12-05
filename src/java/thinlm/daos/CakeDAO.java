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
import thinlm.dtos.CakeDTO;
import thinlm.utils.MyConnection;

/**
 *
 * @author Admin
 */
public class CakeDAO implements Serializable{
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
    
    public ArrayList<CakeDTO> getAllCake() throws NamingException, SQLException{
        ArrayList<CakeDTO> list = new ArrayList<>();
        try{
            String sql = "SELECT cakeID, dateOfCreate, expirationDate, price, category, name, quantity , image, status "
                    + "FROM tbl_Cake WHERE status = ?";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, "Actived");
            rs = pst.executeQuery();
            while(rs.next()){
                CakeDTO cakeDTO = new CakeDTO();
                cakeDTO.setCakeID(rs.getString("cakeID"));
                cakeDTO.setDateOfCreate(rs.getTimestamp("dateOfCreate"));
                cakeDTO.setExpirationDate(rs.getTimestamp("expirationDate"));
                cakeDTO.setCategory(rs.getString("category"));
                cakeDTO.setImage(rs.getString("image"));
                cakeDTO.setName(rs.getString("name"));
                cakeDTO.setPrice(rs.getFloat("price"));
                cakeDTO.setQuantity(rs.getInt("quantity"));
                cakeDTO.setStatus("Actived");
                list.add(cakeDTO);
            }
        }
        finally{
            closeConnection();
        }
        return list;
    }
    public boolean createCake(CakeDTO cake) throws NamingException, SQLException{
        boolean result = false;
        try{
            String sql = "INSERT INTO tbl_Cake(cakeID, dateOfCreate, expirationDate, price, category, name, quantity , image,status) VALUES (?,?,?,?,?,?,?,?,?)";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            pst.setString(1, time.toString());
            pst.setTimestamp(2, cake.getDateOfCreate());
            pst.setTimestamp(3, cake.getExpirationDate());
            pst.setFloat(4, cake.getPrice());
            pst.setString(5, cake.getCategory());
            pst.setString(6, cake.getName());
            pst.setInt(7, cake.getQuantity());
            pst.setString(8, cake.getImage());
            pst.setString(9, "Actived");
            
            result = pst.executeUpdate() > 0;
            
        }
        finally{
            closeConnection();
        }
        return result;
    }
    public CakeDTO getCakeByID(String cakeID) throws NamingException, SQLException{
        CakeDTO cakeDTO = null;
        try{
            String sql = "SELECT  dateOfCreate, expirationDate, price, category, name, quantity , image, status "
                    + "FROM tbl_Cake WHERE cakeID = ? ";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, cakeID);
            rs = pst.executeQuery();
            if(rs.next()){
                cakeDTO = new CakeDTO();
                cakeDTO.setCakeID(cakeID);
                cakeDTO.setDateOfCreate(rs.getTimestamp("dateOfCreate"));
                cakeDTO.setExpirationDate(rs.getTimestamp("expirationDate"));
                cakeDTO.setCategory(rs.getString("category"));
                cakeDTO.setImage(rs.getString("image"));
                cakeDTO.setName(rs.getString("name"));
                cakeDTO.setPrice(rs.getFloat("price"));
                cakeDTO.setQuantity(rs.getInt("quantity"));
                cakeDTO.setStatus(rs.getString("status"));
            }
        }
        finally{
            closeConnection();
        }
        return cakeDTO;
    }
    public CakeDTO getCakeByIDForCostumer(String cakeID) throws NamingException, SQLException{
        CakeDTO cakeDTO = null;
        try{
            String sql = "SELECT  dateOfCreate, expirationDate, price, category, name, quantity , image, status "
                    + "FROM tbl_Cake WHERE cakeID = ? AND expirationDate > ? AND status = ?";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, cakeID);
            pst.setTimestamp(2, new Timestamp(new Date().getTime()));
            pst.setString(3, "Actived");
            rs = pst.executeQuery();
            if(rs.next()){
                cakeDTO = new CakeDTO();
                cakeDTO.setCakeID(cakeID);
                cakeDTO.setDateOfCreate(rs.getTimestamp("dateOfCreate"));
                cakeDTO.setExpirationDate(rs.getTimestamp("expirationDate"));
                cakeDTO.setCategory(rs.getString("category"));
                cakeDTO.setImage(rs.getString("image"));
                cakeDTO.setName(rs.getString("name"));
                cakeDTO.setPrice(rs.getFloat("price"));
                cakeDTO.setQuantity(rs.getInt("quantity"));
                cakeDTO.setStatus(rs.getString("status"));
            }
        }
        finally{
            closeConnection();
        }
        return cakeDTO;
    }
    public boolean UpdateCake(CakeDTO cakeDTO) throws NamingException, SQLException{
        boolean result = false;
        try{
            String sql = "UPDATE tbl_Cake SET  dateOfCreate = ? , expirationDate = ?, price = ?, category = ?, name = ?, quantity = ?, image = ?, status =?"
                    + " WHERE cakeID = ?";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setTimestamp(1, cakeDTO.getDateOfCreate());
            pst.setTimestamp(2, cakeDTO.getExpirationDate());
            pst.setFloat(3, cakeDTO.getPrice());
            pst.setString(4, cakeDTO.getCategory());
            pst.setString(5, cakeDTO.getName());
            pst.setInt(6, cakeDTO.getQuantity());
            pst.setString(7, cakeDTO.getImage());
            pst.setString(8,cakeDTO.getStatus());
            pst.setString(9, cakeDTO.getCakeID());
            
            result = pst.executeUpdate() > 0 ;
        }
        finally{
            closeConnection();
        }
        return result;
    }
    public ArrayList<CakeDTO> searchCakeWithNameAndCategory(String name,String category ,int pageFloor) throws SQLException, NamingException{
        ArrayList<CakeDTO> list = new ArrayList<>();
        try{
            String sql = "SELECT cakeID, dateOfCreate, expirationDate, price, category, name, quantity , image ,status "
                    + "FROM tbl_Cake WHERE status = ? and name LIKE ? and category LIKE ?  "
                    + " ORDER BY dateOfCreate "
                    + "OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, "Actived");
            pst.setString(2, "%" + name + "%");
            pst.setString(3, "%" + category + "%");
            pst.setInt(4, pageFloor);
            rs = pst.executeQuery();
            while(rs.next()){
                CakeDTO cakeDTO = new CakeDTO();
                cakeDTO.setCakeID(rs.getString("cakeID"));
                cakeDTO.setDateOfCreate(rs.getTimestamp("dateOfCreate"));
                cakeDTO.setExpirationDate(rs.getTimestamp("expirationDate"));
                cakeDTO.setCategory(rs.getString("category"));
                cakeDTO.setImage(rs.getString("image"));
                cakeDTO.setName(rs.getString("name"));
                cakeDTO.setPrice(rs.getFloat("price"));
                cakeDTO.setQuantity(rs.getInt("quantity"));
                cakeDTO.setStatus(rs.getString("status"));
                list.add(cakeDTO);
            }
        }
        finally{
            closeConnection();
        }
        return list;
    }
    public ArrayList<CakeDTO> searchCakeWithNameAndCategoryForCostumer(String name,String category ,int pageFloor) throws SQLException, NamingException{
        ArrayList<CakeDTO> list = new ArrayList<>();
        try{
            String sql = "SELECT cakeID, dateOfCreate, expirationDate, price, category, name, quantity , image ,status "
                    + "FROM tbl_Cake WHERE status = ? and name LIKE ? and category LIKE ?  AND expirationDate > ? AND quantity > 0 "
                    + " ORDER BY dateOfCreate "
                    + "OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, "Actived");
            pst.setString(2, "%" + name + "%");
            pst.setString(3, "%" + category + "%");
            pst.setTimestamp(4, new Timestamp(new Date().getTime()));
            pst.setInt(5, pageFloor);
            rs = pst.executeQuery();
            while(rs.next()){
                CakeDTO cakeDTO = new CakeDTO();
                cakeDTO.setCakeID(rs.getString("cakeID"));
                cakeDTO.setDateOfCreate(rs.getTimestamp("dateOfCreate"));
                cakeDTO.setExpirationDate(rs.getTimestamp("expirationDate"));
                cakeDTO.setCategory(rs.getString("category"));
                cakeDTO.setImage(rs.getString("image"));
                cakeDTO.setName(rs.getString("name"));
                cakeDTO.setPrice(rs.getFloat("price"));
                cakeDTO.setQuantity(rs.getInt("quantity"));
                cakeDTO.setStatus(rs.getString("status"));
                list.add(cakeDTO);
            }
        }
        finally{
            closeConnection();
        }
        return list;
    }
    public ArrayList<CakeDTO> searchCakeWithPriceAndCategoryAndName(float priceFloor, float priceCeil ,String name, String category,int pageFloor) throws SQLException, NamingException{
        ArrayList<CakeDTO> list = new ArrayList<>();
        try{
            String sql = "SELECT cakeID, dateOfCreate, expirationDate, price, category, name, quantity , image ,status "
                    + "FROM tbl_Cake WHERE status = ? AND name LIKE ? AND category LIKE ? AND price BETWEEN ? AND ? "
                    + " ORDER BY dateOfCreate "
                    + "OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, "Actived");
            pst.setString(2, "%" + name + "%");
            pst.setString(3, "%" + category + "%");
            pst.setFloat(4, priceFloor);
            pst.setFloat(5, priceCeil);
            pst.setInt(6, pageFloor);
            rs = pst.executeQuery();
            while(rs.next()){
                CakeDTO cakeDTO = new CakeDTO();
                cakeDTO.setCakeID(rs.getString("cakeID"));
                cakeDTO.setDateOfCreate(rs.getTimestamp("dateOfCreate"));
                cakeDTO.setExpirationDate(rs.getTimestamp("expirationDate"));
                cakeDTO.setCategory(rs.getString("category"));
                cakeDTO.setImage(rs.getString("image"));
                cakeDTO.setName(rs.getString("name"));
                cakeDTO.setPrice(rs.getFloat("price"));
                cakeDTO.setQuantity(rs.getInt("quantity"));
                cakeDTO.setStatus(rs.getString("status"));
                list.add(cakeDTO);
            }
        }
        finally{
            closeConnection();
        }
        return list;
    }
    public ArrayList<CakeDTO> searchCakeWithPriceAndCategoryAndNameForCostumer(float priceFloor, float priceCeil ,String name, String category,int pageFloor) throws SQLException, NamingException{
        ArrayList<CakeDTO> list = new ArrayList<>();
        try{
            String sql = "SELECT cakeID, dateOfCreate, expirationDate, price, category, name, quantity , image ,status "
                    + "FROM tbl_Cake WHERE status = ? AND name LIKE ? AND category LIKE ? AND expirationDate > ? AND quantity > 0  AND price BETWEEN ? AND ? "
                    + " ORDER BY dateOfCreate "
                    + "OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, "Actived");
            pst.setString(2, "%" + name + "%");
            pst.setString(3, "%" + category + "%");
            pst.setTimestamp(4, new Timestamp(new Date().getTime()));
            pst.setFloat(5, priceFloor);
            pst.setFloat(6, priceCeil);
            pst.setInt(7, pageFloor);
            rs = pst.executeQuery();
            while(rs.next()){
                CakeDTO cakeDTO = new CakeDTO();
                cakeDTO.setCakeID(rs.getString("cakeID"));
                cakeDTO.setDateOfCreate(rs.getTimestamp("dateOfCreate"));
                cakeDTO.setExpirationDate(rs.getTimestamp("expirationDate"));
                cakeDTO.setCategory(rs.getString("category"));
                cakeDTO.setImage(rs.getString("image"));
                cakeDTO.setName(rs.getString("name"));
                cakeDTO.setPrice(rs.getFloat("price"));
                cakeDTO.setQuantity(rs.getInt("quantity"));
                cakeDTO.setStatus(rs.getString("status"));
                list.add(cakeDTO);
            }
        }
        finally{
            closeConnection();
        }
        return list;
    }
    public boolean reduceQuantityForThisCake(String cakeID, int quantity) throws NamingException, SQLException{
        boolean result = false;
        try{
            String sql = "UPDATE tbl_Cake SET quantity = ? WHERE cakeID = ?";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setInt(1, quantity);
            pst.setString(2, cakeID);
            result = pst.executeUpdate() > 0;
        }
        finally{
            closeConnection();
        }
        return result;
    }
    public ArrayList<CakeDTO> getCakeInPage(int floor) throws NamingException, SQLException{
        ArrayList<CakeDTO> list = new ArrayList<>();
        try{
            String sql = "SELECT cakeID, dateOfCreate, expirationDate, price, category, name, quantity , image, status "
                    + "FROM tbl_Cake "
                    + " ORDER BY dateOfCreate "
                    + "OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setInt(1, floor);
            rs = pst.executeQuery();
            while(rs.next()){
                CakeDTO cakeDTO = new CakeDTO();
                cakeDTO.setCakeID(rs.getString("cakeID"));
                cakeDTO.setDateOfCreate(rs.getTimestamp("dateOfCreate"));
                cakeDTO.setExpirationDate(rs.getTimestamp("expirationDate"));
                cakeDTO.setCategory(rs.getString("category"));
                cakeDTO.setImage(rs.getString("image"));
                cakeDTO.setName(rs.getString("name"));
                cakeDTO.setPrice(rs.getFloat("price"));
                cakeDTO.setQuantity(rs.getInt("quantity"));
                cakeDTO.setStatus("Actived");
                list.add(cakeDTO);
            }
        }
        finally{
            closeConnection();
        }
        return list;
    }
    public ArrayList<CakeDTO> getCakeInPageForCostumer(int floor) throws NamingException, SQLException{
        ArrayList<CakeDTO> list = new ArrayList<>();
        try{
            String sql = "SELECT cakeID, dateOfCreate, expirationDate, price, category, name, quantity , image, status "
                    + "FROM tbl_Cake WHERE status = ? AND expirationDate > ? AND quantity > 0"
                    + " ORDER BY dateOfCreate "
                    + "OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY ";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, "Actived");
            pst.setTimestamp(2, new Timestamp(new Date().getTime()));
            pst.setInt(3, floor);
            rs = pst.executeQuery();
            while(rs.next()){
                CakeDTO cakeDTO = new CakeDTO();
                cakeDTO.setCakeID(rs.getString("cakeID"));
                cakeDTO.setDateOfCreate(rs.getTimestamp("dateOfCreate"));
                cakeDTO.setExpirationDate(rs.getTimestamp("expirationDate"));
                cakeDTO.setCategory(rs.getString("category"));
                cakeDTO.setImage(rs.getString("image"));
                cakeDTO.setName(rs.getString("name"));
                cakeDTO.setPrice(rs.getFloat("price"));
                cakeDTO.setQuantity(rs.getInt("quantity"));
                cakeDTO.setStatus("Actived");
                list.add(cakeDTO);
            }
        }
        finally{
            closeConnection();
        }
        return list;
    }
    
//    public boolean updateQuantityForThisCake(String cakeID, int quantity) throws NamingException, SQLException{
//        boolean result = false;
//        try{
//            String sql = "UPDATE tbl_Cake SET quantity = ? WHERE cakeID = ?";
//            cn = MyConnection.getMyConnection();
//            pst = cn.prepareStatement(sql);
//            pst.setInt(1, quantity);
//            pst.setString(2, cakeID);
//            result = pst.executeUpdate() > 0;
//        }
//        finally{
//            closeConnection();
//        }
//        return result;
//    }
}
