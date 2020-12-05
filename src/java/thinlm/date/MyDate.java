/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.date;

/**
 *
 * @author Admin
 */
public class MyDate {
    String dateOfCreate;
    String expirationDate;

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(String dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public MyDate() {
    }

    public MyDate(String dateOfCreate, String expirationDate) {
        this.dateOfCreate = dateOfCreate;
        this.expirationDate = expirationDate;
    }
}
