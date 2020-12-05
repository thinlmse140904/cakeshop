/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class CakeDTO implements Serializable, Comparable<CakeDTO>{
    String name;
    String cakeID;
    float price;
    String image;
    Timestamp dateOfCreate;
    Timestamp expirationDate;
    int quantity;
    String category;
    String status;

    public CakeDTO(String name, String cakeID, float price, String image, Timestamp dateOfCreate, Timestamp expirationDate, int quantity, String category, String status) {
        this.name = name;
        this.cakeID = cakeID;
        this.price = price;
        this.image = image;
        this.dateOfCreate = dateOfCreate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.category = category;
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    public CakeDTO() {
    }

    public CakeDTO(String name, String cakeID, float price, String image, Timestamp dateOfCreate, Timestamp expirationDate, int quantity, String category) {
        this.name = name;
        this.cakeID = cakeID;
        this.price = price;
        this.image = image;
        this.dateOfCreate = dateOfCreate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCakeID() {
        return cakeID;
    }

    public void setCakeID(String cakeID) {
        this.cakeID = cakeID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public CakeDTO(String name, float price, String image, Timestamp dateOfCreate, Timestamp expirationDate, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.dateOfCreate = dateOfCreate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Timestamp dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @Override
    public int compareTo(CakeDTO cakeDTO){
        return this.dateOfCreate.compareTo(cakeDTO.getDateOfCreate());
    }

}
