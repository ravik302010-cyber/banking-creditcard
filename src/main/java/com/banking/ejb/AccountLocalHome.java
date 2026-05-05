package com.banking.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Local Home interface for Account Entity Bean (EJB 2.x)
 */
public interface AccountLocalHome extends EJBLocalHome {
    
    /**
     * Create a new account
     */
    AccountLocal create(Long accountId, String accountNumber, String accountType, 
                       BigDecimal balance, Long customerId) throws CreateException;
    
    /**
     * Find account by primary key
     */
    AccountLocal findByPrimaryKey(Long accountId) throws FinderException;
    
    /**
     * Find account by account number
     */
    AccountLocal findByAccountNumber(String accountNumber) throws FinderException;
    
    /**
     * Find all accounts for a customer
     */
    Collection findByCustomerId(Long customerId) throws FinderException;
}

// Made with Bob
