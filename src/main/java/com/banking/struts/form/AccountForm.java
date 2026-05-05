package com.banking.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * Struts ActionForm for account operations
 */
public class AccountForm extends ActionForm {
    
    private static final long serialVersionUID = 1L;
    
    private String accountId;
    private String accountNumber;
    private String accountType;
    private String balance;
    private String amount;
    private String customerId;
    
    public String getAccountId() {
        return accountId;
    }
    
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public String getAccountType() {
        return accountType;
    }
    
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    public String getBalance() {
        return balance;
    }
    
    public void setBalance(String balance) {
        this.balance = balance;
    }
    
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.accountId = null;
        this.accountNumber = null;
        this.accountType = null;
        this.balance = null;
        this.amount = null;
        this.customerId = null;
    }
    
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        String path = mapping.getPath();
        
        if ("/createAccount".equals(path)) {
            if (accountNumber == null || accountNumber.trim().length() == 0) {
                errors.add("accountNumber", new ActionMessage("error.accountNumber.required"));
            }
            
            if (accountType == null || accountType.trim().length() == 0) {
                errors.add("accountType", new ActionMessage("error.accountType.required"));
            }
            
            if (balance == null || balance.trim().length() == 0) {
                errors.add("balance", new ActionMessage("error.balance.required"));
            } else {
                try {
                    double bal = Double.parseDouble(balance);
                    if (bal < 0) {
                        errors.add("balance", new ActionMessage("error.balance.negative"));
                    }
                } catch (NumberFormatException e) {
                    errors.add("balance", new ActionMessage("error.balance.invalid"));
                }
            }
        }
        
        if ("/deposit".equals(path) || "/withdraw".equals(path)) {
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
        }
        
        return errors;
    }
}

// Made with Bob
