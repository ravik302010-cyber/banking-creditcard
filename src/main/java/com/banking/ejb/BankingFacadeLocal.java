package com.banking.ejb;

import javax.ejb.EJBLocalObject;
import java.math.BigDecimal;
import java.util.List;

/**
 * Local interface for Banking Facade Session Bean (EJB 2.x)
 * Provides business logic for banking operations
 */
public interface BankingFacadeLocal extends EJBLocalObject {
    
    // Customer operations
    Long createCustomer(String firstName, String lastName, String email, 
                       String phone, String address, String username, String password);
    
    CustomerLocal findCustomerByUsername(String username);
    
    CustomerLocal findCustomerById(Long customerId);
    
    boolean authenticateCustomer(String username, String password);
    
    // Account operations
    Long createAccount(String accountNumber, String accountType, 
                      BigDecimal initialBalance, Long customerId);
    
    AccountLocal findAccountByNumber(String accountNumber);
    
    AccountLocal findAccountById(Long accountId);
    
    List<AccountLocal> findAccountsByCustomerId(Long customerId);
    
    // Transaction operations
    void deposit(Long accountId, BigDecimal amount);
    
    void withdraw(Long accountId, BigDecimal amount) throws InsufficientFundsException;
    
    void transferFunds(Long fromAccountId, Long toAccountId, BigDecimal amount) 
        throws InsufficientFundsException;
    
    BigDecimal getAccountBalance(Long accountId);
}

// Made with Bob
