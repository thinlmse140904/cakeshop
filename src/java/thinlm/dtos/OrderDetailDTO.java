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
public class OrderDetailDTO implements Serializable{
    String detailID;
    String orderID;
    String cakeID;
    int quantity;
    Timestamp dateOfCreate;

    public Timestamp getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Timestamp dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String detailID, String orderID, String cakeID, int quantity) {
        this.detailID = detailID;
        this.orderID = orderID;
        this.cakeID = cakeID;
        this.quantity = quantity;
    }

    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCakeID() {
        return cakeID;
    }

    public void setCakeID(String cakeID) {
        this.cakeID = cakeID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
