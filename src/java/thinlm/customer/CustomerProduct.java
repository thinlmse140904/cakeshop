/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.customer;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class CustomerProduct implements Serializable{
    String cakeName;
    int quantity;

    public CustomerProduct(String cakeName, int quantity) {
        this.cakeName = cakeName;
        this.quantity = quantity;
    }

    public String getCakeName() {
        return cakeName;
    }

    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CustomerProduct() {
    }
    
}
