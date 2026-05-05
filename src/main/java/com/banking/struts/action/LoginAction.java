package com.banking.struts.action;

import com.banking.ejb.BankingFacadeLocal;
import com.banking.ejb.BankingFacadeLocalHome;
import com.banking.ejb.CustomerLocal;
import com.banking.struts.form.LoginForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Struts Action for user login
 */
public class LoginAction extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        LoginForm loginForm = (LoginForm) form;
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        
        try {
            // Lookup Banking Facade EJB
            InitialContext ic = new InitialContext();
            BankingFacadeLocalHome home = (BankingFacadeLocalHome) 
                ic.lookup("java:comp/env/ejb/BankingFacade");
            BankingFacadeLocal facade = home.create();
            
            // Authenticate user
            if (facade.authenticateCustomer(username, password)) {
                // Get customer details
                CustomerLocal customer = facade.findCustomerByUsername(username);
                
                // Store customer in session
                HttpSession session = request.getSession();
                session.setAttribute("customerId", customer.getCustomerId());
                session.setAttribute("customerName", customer.getFullName());
                session.setAttribute("username", username);
                
                return mapping.findForward("success");
            } else {
                ActionMessages errors = new ActionMessages();
                errors.add("login", new ActionMessage("error.login.invalid"));
                saveErrors(request, errors);
                return mapping.findForward("failure");
            }
            
        } catch (Exception e) {
            ActionMessages errors = new ActionMessages();
            errors.add("login", new ActionMessage("error.system", e.getMessage()));
            saveErrors(request, errors);
            return mapping.findForward("failure");
        }
    }
}

// Made with Bob
