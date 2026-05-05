package com.banking.ejb;

/**
 * Exception thrown when account has insufficient funds for withdrawal
 */
public class InsufficientFundsException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    public InsufficientFundsException() {
        super("Insufficient funds in account");
    }
    
    public InsufficientFundsException(String message) {
        super(message);
    }
    
    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Made with Bob
