package com.banking.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * Struts ActionForm for customer registration
 */
public class CustomerForm extends ActionForm {
    
    private static final long serialVersionUID = 1L;
    
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String username;
    private String password;
    private String confirmPassword;
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.phone = null;
        this.address = null;
        this.username = null;
        this.password = null;
        this.confirmPassword = null;
    }
    
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        if (firstName == null || firstName.trim().length() == 0) {
            errors.add("firstName", new ActionMessage("error.firstName.required"));
        }
        
        if (lastName == null || lastName.trim().length() == 0) {
            errors.add("lastName", new ActionMessage("error.lastName.required"));
        }
        
        if (email == null || email.trim().length() == 0) {
            errors.add("email", new ActionMessage("error.email.required"));
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.add("email", new ActionMessage("error.email.invalid"));
        }
        
        if (phone == null || phone.trim().length() == 0) {
            errors.add("phone", new ActionMessage("error.phone.required"));
        }
        
        if (username == null || username.trim().length() == 0) {
            errors.add("username", new ActionMessage("error.username.required"));
        } else if (username.length() < 4) {
            errors.add("username", new ActionMessage("error.username.length"));
        }
        
        if (password == null || password.trim().length() == 0) {
            errors.add("password", new ActionMessage("error.password.required"));
        } else if (password.length() < 6) {
            errors.add("password", new ActionMessage("error.password.length"));
        }
        
        if (confirmPassword == null || confirmPassword.trim().length() == 0) {
            errors.add("confirmPassword", new ActionMessage("error.confirmPassword.required"));
        } else if (!password.equals(confirmPassword)) {
            errors.add("confirmPassword", new ActionMessage("error.password.mismatch"));
        }
        
        return errors;
    }
}

// Made with Bob
