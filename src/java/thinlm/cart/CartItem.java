/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.cart;

import java.io.Serializable;
import thinlm.dtos.CakeDTO;

/**
 *
 * @author Admin
 */
public class CartItem implements Serializable{
    CakeDTO cakeDTO;
    int quantity;

    public CakeDTO getCakeDTO() {
        return cakeDTO;
    }

    public void setCakeDTO(CakeDTO cakeDTO) {
        this.cakeDTO = cakeDTO;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartItem(CakeDTO cakeDTO, int quantity) {
        this.cakeDTO = cakeDTO;
        this.quantity = quantity;
    }

    public CartItem() {
    }
    
}
