package com.myapp.struts.action;

import com.myapp.struts.formbean.EmployeForm;
import com.myapp.struts.model.IEmployeModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;

public class AddEmployeAction extends SuperAction {

    @Override
    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        IEmployeModel model = (IEmployeModel) super.getIModel();
        // Cible par defaut en cas de succes
        String target = "success";

    // teste l'identification de l'utilisateur ?
        HttpSession session = request.getSession();
        if (session.getAttribute("USER") == null) {

            // L'utilisateur n'est pas identifie
            target = "login";
            ActionMessages errors = new ActionMessages();

            errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage("errors.login.required"));

            // Renvoyer les erreurs au formulaire originel
            if (!errors.isEmpty()) {

                saveErrors(request, errors);
            }
            // Transmission a la vue appropriee
            return (mapping.findForward(target));
        }

        if (isCancelled(request)) {

            // Annulation. Retour a la liste des employes
            return (mapping.findForward(target));
        }

        try {
            EmployeForm eForm = (EmployeForm) form;
            model.insertEmploye(eForm.formToEmploye());
        } catch (Exception e) {

            System.err.println("Setting target to error");
            target = "error";
            ActionMessages errors = new ActionMessages();

            errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage("errors.database.error", e.getMessage()));

            // Signalement des erreurs eventuelles
            if (!errors.isEmpty()) {

                saveErrors(request, errors);
            }
        }
        // Transmission a la vue appropriee
        return (mapping.findForward(target));
    }
}
