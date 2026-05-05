package com.banking.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * Struts ActionForm for login
 */
public class LoginForm extends ActionForm {
    
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String password;
    
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
    
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.username = null;
        this.password = null;
    }
    
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        if (username == null || username.trim().length() == 0) {
            errors.add("username", new ActionMessage("error.username.required"));
        }
        
        if (password == null || password.trim().length() == 0) {
            errors.add("password", new ActionMessage("error.password.required"));
        }
        
        return errors;
    }
}

// Made with Bob
