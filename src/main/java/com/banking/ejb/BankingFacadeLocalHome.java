package com.banking.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 * Local Home interface for Banking Facade Session Bean (EJB 2.x)
 */
public interface BankingFacadeLocalHome extends EJBLocalHome {
    
    /**
     * Create the session bean instance
     */
    BankingFacadeLocal create() throws CreateException;
}

// Made with Bob
