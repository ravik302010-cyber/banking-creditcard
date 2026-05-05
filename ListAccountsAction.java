package com.banking.struts.action;

import com.banking.ejb.*;
import org.apache.struts.action.*;
import javax.naming.InitialContext;
import javax.servlet.http.*;
import java.util.List;

public class ListAccountsAction extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) return mapping.findForward("login");
        
        try {
            InitialContext ic = new InitialContext();
            BankingFacadeLocalHome home = (BankingFacadeLocalHome) ic.lookup("java:comp/env/ejb/BankingFacade");
            BankingFacadeLocal facade = home.create();
            
            List accounts = facade.findAccountsByCustomerId(customerId);
            request.setAttribute("accounts", accounts);
            return mapping.findForward("success");
        } catch (Exception e) {
            return mapping.findForward("failure");
        }
    }
}
