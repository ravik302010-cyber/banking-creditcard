package com.banking.struts.action;

import com.banking.ejb.*;
import com.banking.struts.form.AccountForm;
import org.apache.struts.action.*;
import javax.naming.InitialContext;
import javax.servlet.http.*;
import java.math.BigDecimal;

public class WithdrawAction extends Action {
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
            facade.withdraw(accountId, amount);
            
            ActionMessages messages = new ActionMessages();
            messages.add("withdraw", new ActionMessage("message.withdraw.success"));
            saveMessages(request, messages);
            return mapping.findForward("success");
        } catch (InsufficientFundsException e) {
            ActionMessages errors = new ActionMessages();
            errors.add("withdraw", new ActionMessage("error.insufficient.funds"));
            saveErrors(request, errors);
            return mapping.findForward("failure");
        } catch (Exception e) {
            ActionMessages errors = new ActionMessages();
            errors.add("withdraw", new ActionMessage("error.withdraw.failed"));
            saveErrors(request, errors);
            return mapping.findForward("failure");
        }
    }
}
