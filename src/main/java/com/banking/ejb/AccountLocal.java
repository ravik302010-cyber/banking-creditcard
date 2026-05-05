package com.banking.ejb;

import javax.ejb.EJBLocalObject;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Local interface for Account Entity Bean (EJB 2.x)
 */
public interface AccountLocal extends EJBLocalObject {
    
    // Getter methods for CMP fields
    Long getAccountId();
    
    String getAccountNumber();
    void setAccountNumber(String accountNumber);
    
    String getAccountType();
    void setAccountType(String accountType);
    
    BigDecimal getBalance();
    void setBalance(BigDecimal balance);
    
    Long getCustomerId();
    void setCustomerId(Long customerId);
    
    Date getCreatedDate();
    void setCreatedDate(Date createdDate);
    
    String getStatus();
    void setStatus(String status);
    
    // Business methods
    void deposit(BigDecimal amount);
    void withdraw(BigDecimal amount) throws InsufficientFundsException;
}

// Made with Bob
