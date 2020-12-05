/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinlm.error;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class ErrorObj implements Serializable{
    String nameError;
    String dateError;
    String expirationDateError;
    String priceError;
    String quantityError;
    String categoryName;
    String createDateError;
    String imageError;
    String addressError;
    String phoneNoError;
    String confirmError;
    String emailError;
    String passwordError;
    String cakeIDError;

    public String getCakeIDError() {
        return cakeIDError;
    }

    public void setCakeIDError(String cakeIDError) {
        this.cakeIDError = cakeIDError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPhoneNoError() {
        return phoneNoError;
    }

    public void setPhoneNoError(String phoneNoError) {
        this.phoneNoError = phoneNoError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getImageError() {
        return imageError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }

    public String getCreateDateError() {
        return createDateError;
    }

    public void setCreateDateError(String createDateError) {
        this.createDateError = createDateError;
    }

    public ErrorObj(String nameError, String dateError, String expirationDateError, String priceError, String quantityError, String categoryName) {
        this.nameError = nameError;
        this.dateError = dateError;
        this.expirationDateError = expirationDateError;
        this.priceError = priceError;
        this.quantityError = quantityError;
        this.categoryName = categoryName;
    }

    public ErrorObj() {
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getDateError() {
        return dateError;
    }

    public void setDateError(String dateError) {
        this.dateError = dateError;
    }

    public String getExpirationDateError() {
        return expirationDateError;
    }

    public void setExpirationDateError(String expirationDateError) {
        this.expirationDateError = expirationDateError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    
}
