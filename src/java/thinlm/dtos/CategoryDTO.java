/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.dtos;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class CategoryDTO implements Serializable{
    String categoryID;
    String category;

    public CategoryDTO(String categoryID, String category) {
        this.categoryID = categoryID;
        this.category = category;
    }

    public CategoryDTO() {
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
}
