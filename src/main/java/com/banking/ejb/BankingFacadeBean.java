package com.banking.ejb;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Banking Facade Session Bean (EJB 2.x Stateless)
 * Provides business logic facade for banking operations
 */
public class BankingFacadeBean implements SessionBean {
    
    private SessionContext context;
    private AccountLocalHome accountHome;
    private CustomerLocalHome customerHome;
    
    // Customer operations
    public Long createCustomer(String firstName, String lastName, String email,
                              String phone, String address, String username, String password) {
        try {
            Long customerId = generateCustomerId();
            CustomerLocal customer = customerHome.create(customerId, firstName, lastName,
                                                        email, phone, address, username, password);
            return customer.getCustomerId();
        } catch (CreateException e) {
            throw new RuntimeException("Failed to create customer", e);
        }
    }
    
    public CustomerLocal findCustomerByUsername(String username) {
        try {
            return customerHome.findByUsername(username);
        } catch (FinderException e) {
            return null;
        }
    }
    
    public CustomerLocal findCustomerById(Long customerId) {
        try {
            return customerHome.findByPrimaryKey(customerId);
        } catch (FinderException e) {
            return null;
        }
    }
    
    public boolean authenticateCustomer(String username, String password) {
        CustomerLocal customer = findCustomerByUsername(username);
        if (customer != null) {
            return customer.validatePassword(password);
        }
        return false;
    }
    
    // Account operations
    public Long createAccount(String accountNumber, String accountType,
                             BigDecimal initialBalance, Long customerId) {
        try {
            Long accountId = generateAccountId();
            AccountLocal account = accountHome.create(accountId, accountNumber, accountType,
                                                     initialBalance, customerId);
            return account.getAccountId();
        } catch (CreateException e) {
            throw new RuntimeException("Failed to create account", e);
        }
    }
    
    public AccountLocal findAccountByNumber(String accountNumber) {
        try {
            return accountHome.findByAccountNumber(accountNumber);
        } catch (FinderException e) {
            return null;
        }
    }
    
    public AccountLocal findAccountById(Long accountId) {
        try {
            return accountHome.findByPrimaryKey(accountId);
        } catch (FinderException e) {
            return null;
        }
    }
    
    public List<AccountLocal> findAccountsByCustomerId(Long customerId) {
        try {
            Collection accounts = accountHome.findByCustomerId(customerId);
            List<AccountLocal> accountList = new ArrayList<AccountLocal>();
            Iterator iterator = accounts.iterator();
            while (iterator.hasNext()) {
                accountList.add((AccountLocal) iterator.next());
            }
            return accountList;
        } catch (FinderException e) {
            return new ArrayList<AccountLocal>();
        }
    }
    
    // Transaction operations
    public void deposit(Long accountId, BigDecimal amount) {
        AccountLocal account = findAccountById(accountId);
        if (account == null) {
            throw new RuntimeException("Account not found: " + accountId);
        }
        account.deposit(amount);
    }
    
    public void withdraw(Long accountId, BigDecimal amount) throws InsufficientFundsException {
        AccountLocal account = findAccountById(accountId);
        if (account == null) {
            throw new RuntimeException("Account not found: " + accountId);
        }
        account.withdraw(amount);
    }
    
    public void transferFunds(Long fromAccountId, Long toAccountId, BigDecimal amount)
            throws InsufficientFundsException {
        AccountLocal fromAccount = findAccountById(fromAccountId);
        AccountLocal toAccount = findAccountById(toAccountId);
        
        if (fromAccount == null) {
            throw new RuntimeException("Source account not found: " + fromAccountId);
        }
        if (toAccount == null) {
            throw new RuntimeException("Destination account not found: " + toAccountId);
        }
        
        // Withdraw from source account
        fromAccount.withdraw(amount);
        
        // Deposit to destination account
        toAccount.deposit(amount);
    }
    
    public BigDecimal getAccountBalance(Long accountId) {
        AccountLocal account = findAccountById(accountId);
        if (account == null) {
            throw new RuntimeException("Account not found: " + accountId);
        }
        return account.getBalance();
    }
    
    // Helper methods
    private Long generateAccountId() {
        // Simple ID generation - in production, use sequence or database generator
        return System.currentTimeMillis();
    }
    
    private Long generateCustomerId() {
        // Simple ID generation - in production, use sequence or database generator
        return System.currentTimeMillis();
    }
    
    // EJB lifecycle methods
    public void ejbCreate() throws CreateException {
        try {
            InitialContext ic = new InitialContext();
            accountHome = (AccountLocalHome) ic.lookup("java:comp/env/ejb/AccountEntity");
            customerHome = (CustomerLocalHome) ic.lookup("java:comp/env/ejb/CustomerEntity");
        } catch (NamingException e) {
            throw new CreateException("Failed to lookup EJB homes: " + e.getMessage());
        }
    }
    
    public void setSessionContext(SessionContext context) {
        this.context = context;
    }
    
    public void ejbRemove() {
        // Cleanup if needed
    }
    
    public void ejbActivate() {
        // Called when bean is activated
    }
    
    public void ejbPassivate() {
        // Called when bean is passivated
    }
}

// Made with Bob
