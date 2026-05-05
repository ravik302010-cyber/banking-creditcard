package com.banking.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.util.Collection;

/**
 * Local Home interface for Customer Entity Bean (EJB 2.x)
 */
public interface CustomerLocalHome extends EJBLocalHome {
    
    /**
     * Create a new customer
     */
    CustomerLocal create(Long customerId, String firstName, String lastName,
                        String email, String phone, String address,
                        String username, String password) throws CreateException;
    
    /**
     * Find customer by primary key
     */
    CustomerLocal findByPrimaryKey(Long customerId) throws FinderException;
    
    /**
     * Find customer by username
     */
    CustomerLocal findByUsername(String username) throws FinderException;
    
    /**
     * Find customer by email
     */
    CustomerLocal findByEmail(String email) throws FinderException;
    
    /**
     * Find all customers
     */
    Collection findAll() throws FinderException;
}

// Made with Bob
