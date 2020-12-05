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
public class OrderDTO implements Serializable{
    String orderID;
    String email;
    String address;
    String phoneNo;
    Timestamp dateOfCreate;
    float total;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderDTO(String orderID, String email, String address, String phoneNo, Timestamp dateOfCreate, float total) {
        this.orderID = orderID;
        this.email = email;
        this.address = address;
        this.phoneNo = phoneNo;
        this.dateOfCreate = dateOfCreate;
        this.total = total;
    }

    public OrderDTO(String orderID, String email, String address, Timestamp dateOfCreate, float total) {
        this.orderID = orderID;
        this.email = email;
        this.address = address;
        this.dateOfCreate = dateOfCreate;
        this.total = total;
    }

    public OrderDTO() {
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Timestamp getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Timestamp dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
