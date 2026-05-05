package com.banking.ejb;

import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.CreateException;
import java.util.Date;

/**
 * Customer Entity Bean (EJB 2.x CMP)
 * Container-Managed Persistence
 */
public abstract class CustomerBean implements EntityBean {
    
    private EntityContext context;
    
    // Abstract accessor methods for CMP fields (container-managed)
    public abstract Long getCustomerId();
    public abstract void setCustomerId(Long customerId);
    
    public abstract String getFirstName();
    public abstract void setFirstName(String firstName);
    
    public abstract String getLastName();
    public abstract void setLastName(String lastName);
    
    public abstract String getEmail();
    public abstract void setEmail(String email);
    
    public abstract String getPhone();
    public abstract void setPhone(String phone);
    
    public abstract String getAddress();
    public abstract void setAddress(String address);
    
    public abstract String getUsername();
    public abstract void setUsername(String username);
    
    public abstract String getPassword();
    public abstract void setPassword(String password);
    
    public abstract Date getCreatedDate();
    public abstract void setCreatedDate(Date createdDate);
    
    // Business methods
    public boolean validatePassword(String password) {
        if (password == null || getPassword() == null) {
            return false;
        }
        return getPassword().equals(password);
    }
    
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
    
    // EJB lifecycle methods
    public Long ejbCreate(Long customerId, String firstName, String lastName,
                         String email, String phone, String address,
                         String username, String password) throws CreateException {
        setCustomerId(customerId);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhone(phone);
        setAddress(address);
        setUsername(username);
        setPassword(password);
        setCreatedDate(new Date());
        return null;
    }
    
    public void ejbPostCreate(Long customerId, String firstName, String lastName,
                             String email, String phone, String address,
                             String username, String password) {
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
