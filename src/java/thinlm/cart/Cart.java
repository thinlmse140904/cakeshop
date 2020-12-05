/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import thinlm.daos.CakeDAO;
import thinlm.dtos.CakeDTO;

/**
 *
 * @author Admin
 */
public class Cart implements Serializable{
    Map<String,Integer> shoppingCart;
    float total;
    String costumerName;

    public Cart( float total, String costumerName) {
        this.shoppingCart = new HashMap<>();
        this.total = total;
        this.costumerName = costumerName;
    }

    public Cart(String costumerName) {
        this.shoppingCart = new HashMap<>();
        this.costumerName = costumerName;
    }
    
    
    public Cart() {
        this.shoppingCart = new HashMap<>();
    }

    public Map<String, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Map<String, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public float getTotal() throws NamingException, SQLException {
        CakeDAO cakeDAO = new CakeDAO();
        float result = 0;
        for (String cakeID : this.shoppingCart.keySet()) {
            CakeDTO cakeDTO = cakeDAO.getCakeByID(cakeID);
            result += this.shoppingCart.get(cakeID) * cakeDTO.getPrice();
        }
        return result;
    }
    public void addToCart(String cakeID, int quantity){
        if(this.shoppingCart == null){
            this.shoppingCart = new HashMap<>();
        }
        if(this.shoppingCart.containsKey(cakeID)){
            int quan = this.shoppingCart.get(cakeID);
            quantity += quan;
        }
        this.shoppingCart.put(cakeID, quantity);
    }

    public boolean removeFromCart(String cakeID){
        boolean result = false;
        if(this.shoppingCart.containsKey(cakeID)){
            this.shoppingCart.remove(cakeID);
            result = true;
        }
        if(this.shoppingCart.isEmpty()){
            this.shoppingCart = null;
        }
        return result;
    }
    public boolean updateQuantity(int quantity, String cakeID){
        if(this.shoppingCart.containsKey(cakeID)){
            for (String key : this.shoppingCart.keySet()) {
                if(key.equals(cakeID)){
                    shoppingCart.put(key, quantity);
                    return true;
                }
            }
        }
        return false;
    }
    public void setTotal(float total) {
        this.total = total;
    }

    public String getCostumerName() {
        return costumerName;
    }

    public void setCostumerName(String costumerName) {
        this.costumerName = costumerName;
    }
    
 
}
