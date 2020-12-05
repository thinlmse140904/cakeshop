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
public class PaymentDTO implements Serializable{
    String orderID;
    String payment;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public PaymentDTO() {
    }

    public PaymentDTO(String orderID, String payment) {
        this.orderID = orderID;
        this.payment = payment;
    }
    
}
