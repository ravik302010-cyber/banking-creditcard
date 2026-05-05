package com.banking.ejb;

import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.CreateException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Account Entity Bean (EJB 2.x CMP)
 * Container-Managed Persistence
 */
public abstract class AccountBean implements EntityBean {
    
    private EntityContext context;
    
    // Abstract accessor methods for CMP fields (container-managed)
    public abstract Long getAccountId();
    public abstract void setAccountId(Long accountId);
    
    public abstract String getAccountNumber();
    public abstract void setAccountNumber(String accountNumber);
    
    public abstract String getAccountType();
    public abstract void setAccountType(String accountType);
    
    public abstract BigDecimal getBalance();
    public abstract void setBalance(BigDecimal balance);
    
    public abstract Long getCustomerId();
    public abstract void setCustomerId(Long customerId);
    
    public abstract Date getCreatedDate();
    public abstract void setCreatedDate(Date createdDate);
    
    public abstract String getStatus();
    public abstract void setStatus(String status);
    
    // Business methods
    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        BigDecimal currentBalance = getBalance();
        setBalance(currentBalance.add(amount));
    }
    
    public void withdraw(BigDecimal amount) throws InsufficientFundsException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        BigDecimal currentBalance = getBalance();
        if (currentBalance.compareTo(amount) < 0) {
            throw new InsufficientFundsException(
                "Insufficient funds. Current balance: " + currentBalance + 
                ", Requested: " + amount);
        }
        
        setBalance(currentBalance.subtract(amount));
    }
    
    // EJB lifecycle methods
    public Long ejbCreate(Long accountId, String accountNumber, String accountType,
                         BigDecimal balance, Long customerId) throws CreateException {
        setAccountId(accountId);
        setAccountNumber(accountNumber);
        setAccountType(accountType);
        setBalance(balance);
        setCustomerId(customerId);
        setCreatedDate(new Date());
        setStatus("ACTIVE");
        return null;
    }
    
    public void ejbPostCreate(Long accountId, String accountNumber, String accountType,
                             BigDecimal balance, Long customerId) {
        // Post-creation logic if needed
    }
    
    public void setEntityContext(EntityContext context) {
        this.context = context;
    }
    
    public void unsetEntityContext() {
        this.context = null;
    }
    
    public void ejbActivate() {
        // Called when bean is activated from passive state
    }
    
    public void ejbPassivate() {
        // Called when bean is passivated
    }
    
    public void ejbLoad() {
        // Called when bean state is loaded from database
    }
    
    public void ejbStore() {
        // Called when bean state is stored to database
    }
    
    public void ejbRemove() {
        // Called before bean is removed
    }
}

// Made with Bob
