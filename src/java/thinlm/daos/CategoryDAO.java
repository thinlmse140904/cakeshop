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
import thinlm.dtos.CategoryDTO;
import thinlm.utils.MyConnection;

/**
 *
 * @author Admin
 */
public class CategoryDAO implements Serializable{
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
    
    public ArrayList<CategoryDTO> getAllCategory() throws NamingException, SQLException{
        ArrayList<CategoryDTO> list = new ArrayList<>();
        try{
            String sql = "SELECT category, categoryID FROM tbl_Category";
            cn = MyConnection.getMyConnection();
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setCategory(rs.getString("category"));
                categoryDTO.setCategoryID(rs.getString("categoryID"));
                list.add(categoryDTO);
            }
        }
        finally{
            closeConnection();
        }
        return list;
    }
}
