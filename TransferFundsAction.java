package com.banking.struts.action;

import com.banking.ejb.BankingFacadeLocal;
import com.banking.ejb.BankingFacadeLocalHome;
import com.banking.ejb.InsufficientFundsException;
import com.banking.struts.form.TransferForm;
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
 * Struts Action for transferring funds between accounts
 */
public class TransferFundsAction extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        TransferForm transferForm = (TransferForm) form;
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
            
            // Transfer funds
            Long fromAccountId = Long.parseLong(transferForm.getFromAccountId());
            Long toAccountId = Long.parseLong(transferForm.getToAccountId());
            BigDecimal amount = new BigDecimal(transferForm.getAmount());
            
            facade.transferFunds(fromAccountId, toAccountId, amount);
            
            // Set success message
            ActionMessages messages = new ActionMessages();
            messages.add("transfer", new ActionMessage("message.transfer.success", 
                amount.toString()));
            saveMessages(request, messages);
            
            return mapping.findForward("success");
            
        } catch (InsufficientFundsException e) {
            ActionMessages errors = new ActionMessages();
            errors.add("transfer", new ActionMessage("error.insufficient.funds"));
            saveErrors(request, errors);
            return mapping.findForward("failure");
        } catch (Exception e) {
            ActionMessages errors = new ActionMessages();
            errors.add("transfer", new ActionMessage("error.transfer.failed", e.getMessage()));
            saveErrors(request, errors);
            return mapping.findForward("failure");
        }
    }
}

// Made with Bob
