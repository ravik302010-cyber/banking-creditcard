package com.banking.ejb;

import javax.ejb.EJBLocalObject;
import java.util.Date;

/**
 * Local interface for Customer Entity Bean (EJB 2.x)
 */
public interface CustomerLocal extends EJBLocalObject {
    
    // Getter methods for CMP fields
    Long getCustomerId();
    
    String getFirstName();
    void setFirstName(String firstName);
    
    String getLastName();
    void setLastName(String lastName);
    
    String getEmail();
    void setEmail(String email);
    
    String getPhone();
    void setPhone(String phone);
    
    String getAddress();
    void setAddress(String address);
    
    String getUsername();
    void setUsername(String username);
    
    String getPassword();
    void setPassword(String password);
    
    Date getCreatedDate();
    void setCreatedDate(Date createdDate);
    
    // Business methods
    boolean validatePassword(String password);
    String getFullName();
}

// Made with Bob
