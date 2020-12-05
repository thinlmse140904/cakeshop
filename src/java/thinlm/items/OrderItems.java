/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.items;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class OrderItems implements Serializable{
    String username;
    String orderID;
    Timestamp dateOfCreate;
    String payment;
    String paymentStatus;
    String address;
    float total;

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public OrderItems(String username, String orderID, Timestamp dateOfCreate, String payment, String paymentStatus, String address) {
        this.username = username;
        this.orderID = orderID;
        this.dateOfCreate = dateOfCreate;
        this.payment = payment;
        this.paymentStatus = paymentStatus;
        this.address = address;
    }

    public OrderItems() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Timestamp getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Timestamp dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
