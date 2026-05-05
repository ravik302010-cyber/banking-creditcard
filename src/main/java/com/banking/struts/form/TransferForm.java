package com.banking.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * Struts ActionForm for fund transfer
 */
public class TransferForm extends ActionForm {
    
    private static final long serialVersionUID = 1L;
    
    private String fromAccountId;
    private String toAccountId;
    private String amount;
    
    public String getFromAccountId() {
        return fromAccountId;
    }
    
    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }
    
    public String getToAccountId() {
        return toAccountId;
    }
    
    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }
    
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.fromAccountId = null;
        this.toAccountId = null;
        this.amount = null;
    }
    
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        if (fromAccountId == null || fromAccountId.trim().length() == 0) {
            errors.add("fromAccountId", new ActionMessage("error.fromAccount.required"));
        }
        
        if (toAccountId == null || toAccountId.trim().length() == 0) {
            errors.add("toAccountId", new ActionMessage("error.toAccount.required"));
        }
        
        if (fromAccountId != null && toAccountId != null && 
            fromAccountId.equals(toAccountId)) {
            errors.add("toAccountId", new ActionMessage("error.sameAccount"));
        }
        
        if (amount == null || amount.trim().length() == 0) {
            errors.add("amount", new ActionMessage("error.amount.required"));
        } else {
            try {
                double amt = Double.parseDouble(amount);
                if (amt <= 0) {
                    errors.add("amount", new ActionMessage("error.amount.positive"));
                }
            } catch (NumberFormatException e) {
                errors.add("amount", new ActionMessage("error.amount.invalid"));
            }
        }
        
        return errors;
    }
}

// Made with Bob
