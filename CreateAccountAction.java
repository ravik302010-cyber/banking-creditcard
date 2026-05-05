package com.banking.struts.action;

import com.banking.ejb.BankingFacadeLocal;
import com.banking.ejb.BankingFacadeLocalHome;
import com.banking.struts.form.AccountForm;
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
import java.math.BigDecimal;

/**
 * Struts Action for creating a new account
 */
public class CreateAccountAction extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        AccountForm accountForm = (AccountForm) form;
        HttpSession session = request.getSession();
        Long customerId = (Long) session.getAttribute("customerId");
        
        if (customerId == null) {
            return mapping.findForward("login");
        }
        
        try {
            // Lookup Banking Facade EJB
            InitialContext ic = new InitialContext();
            BankingFacadeLocalHome home = (BankingFacadeLocalHome) 
                ic.lookup("java:comp/env/ejb/BankingFacade");
            BankingFacadeLocal facade = home.create();
            
            // Create account
            String accountNumber = accountForm.getAccountNumber();
            String accountType = accountForm.getAccountType();
            BigDecimal balance = new BigDecimal(accountForm.getBalance());
            
            Long accountId = facade.createAccount(accountNumber, accountType, balance, customerId);
            
            // Set success message
            ActionMessages messages = new ActionMessages();
            messages.add("account", new ActionMessage("message.account.created", accountNumber));
            saveMessages(request, messages);
            
            request.setAttribute("accountId", accountId);
            
            return mapping.findForward("success");
            
        } catch (Exception e) {
            ActionMessages errors = new ActionMessages();
            errors.add("account", new ActionMessage("error.account.create", e.getMessage()));
            saveErrors(request, errors);
            return mapping.findForward("failure");
        }
    }
}

// Made with Bob
