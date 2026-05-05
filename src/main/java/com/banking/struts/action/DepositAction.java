package com.banking.struts.action;

import com.banking.ejb.*;
import com.banking.struts.form.AccountForm;
import org.apache.struts.action.*;
import javax.naming.InitialContext;
import javax.servlet.http.*;
import java.math.BigDecimal;

public class DepositAction extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        AccountForm accountForm = (AccountForm) form;
        HttpSession session = request.getSession();
        if (session.getAttribute("customerId") == null) return mapping.findForward("login");
        
        try {
            InitialContext ic = new InitialContext();
            BankingFacadeLocalHome home = (BankingFacadeLocalHome) ic.lookup("java:comp/env/ejb/BankingFacade");
            BankingFacadeLocal facade = home.create();
            
            Long accountId = Long.parseLong(accountForm.getAccountId());
            BigDecimal amount = new BigDecimal(accountForm.getAmount());
            facade.deposit(accountId, amount);
            
            ActionMessages messages = new ActionMessages();
            messages.add("deposit", new ActionMessage("message.deposit.success"));
            saveMessages(request, messages);
            return mapping.findForward("success");
        } catch (Exception e) {
            ActionMessages errors = new ActionMessages();
            errors.add("deposit", new ActionMessage("error.deposit.failed"));
            saveErrors(request, errors);
            return mapping.findForward("failure");
        }
    }
}
