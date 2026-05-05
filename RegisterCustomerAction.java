package com.banking.struts.action;

import com.banking.ejb.*;
import com.banking.struts.form.CustomerForm;
import org.apache.struts.action.*;
import javax.naming.InitialContext;
import javax.servlet.http.*;

public class RegisterCustomerAction extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerForm customerForm = (CustomerForm) form;
        
        try {
            InitialContext ic = new InitialContext();
            BankingFacadeLocalHome home = (BankingFacadeLocalHome) ic.lookup("java:comp/env/ejb/BankingFacade");
            BankingFacadeLocal facade = home.create();
            
            Long customerId = facade.createCustomer(
                customerForm.getFirstName(), customerForm.getLastName(),
                customerForm.getEmail(), customerForm.getPhone(),
                customerForm.getAddress(), customerForm.getUsername(),
                customerForm.getPassword()
            );
            
            request.setAttribute("customerId", customerId);
            ActionMessages messages = new ActionMessages();
            messages.add("register", new ActionMessage("message.registration.success"));
            saveMessages(request, messages);
            return mapping.findForward("success");
        } catch (Exception e) {
            ActionMessages errors = new ActionMessages();
            errors.add("register", new ActionMessage("error.registration.failed"));
            saveErrors(request, errors);
            return mapping.findForward("failure");
        }
    }
}
