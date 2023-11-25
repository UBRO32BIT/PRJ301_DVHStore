/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.auth;

/**
 *
 * @author ubro3
 */
public class UserError {
    String userIDError;
    String userNameError;
    String fullNameError;
    String addressError;
    String phoneError;
    String emailError;
    String roleIDError;
    String passwordError;
    String rePasswordError;
    String error;

    public UserError() {
        this.userIDError = "";
        this.userNameError = "";
        this.fullNameError = "";
        this.addressError = "";
        this.phoneError = "";
        this.passwordError = "";
        this.rePasswordError = "";
        this.error = "";
    }

    public UserError(String userIDError, String userNameError, String fullNameError, String addressError, String phoneError, String emailError, String roleIDError, String passwordError, String rePasswordError, String error) {
        this.userIDError = userIDError;
        this.userNameError = userNameError;
        this.fullNameError = fullNameError;
        this.addressError = addressError;
        this.phoneError = phoneError;
        this.emailError = emailError;
        this.roleIDError = roleIDError;
        this.passwordError = passwordError;
        this.rePasswordError = rePasswordError;
        this.error = error;
    }
    

    public String getUserIDError() {
        return userIDError;
    }

    public String getUserNameError() {
        return userNameError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public String getAddressError() {
        return addressError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getRePasswordError() {
        return rePasswordError;
    }

    public String getEmailError() {
        return emailError;
    }
    

    public String getError() {
        return error;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setRePasswordError(String rePasswordError) {
        this.rePasswordError = rePasswordError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }
    
    public void setError(String error) {
        this.error = error;
    }
    
}
