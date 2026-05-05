package com.banking.struts.action;

import com.banking.ejb.*;
import com.banking.struts.form.AccountForm;
import org.apache.struts.action.*;
import javax.naming.InitialContext;
import javax.servlet.http.*;

public class ViewAccountAction extends Action {
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
            AccountLocal account = facade.findAccountById(accountId);
            
            if (account != null) {
                request.setAttribute("account", account);
                return mapping.findForward("success");
            }
            return mapping.findForward("failure");
        } catch (Exception e) {
            return mapping.findForward("failure");
        }
    }
}
