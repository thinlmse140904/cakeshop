/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.dtos;

import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class UpdateDTO {
    String email;
    Timestamp lastUpdateDate;
    String cakeID;
    String updateID;

    public UpdateDTO(String email, Timestamp lastUpdateDate, String cakeID, String updateID) {
        this.email = email;
        this.lastUpdateDate = lastUpdateDate;
        this.cakeID = cakeID;
        this.updateID = updateID;
    }

    public UpdateDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getCakeID() {
        return cakeID;
    }

    public void setCakeID(String cakeID) {
        this.cakeID = cakeID;
    }

    public String getUpdateID() {
        return updateID;
    }

    public void setUpdateID(String updateID) {
        this.updateID = updateID;
    }
    
    
}
