/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.formbean.LoginForm;
import com.myapp.struts.model.IEmployeModel;
import com.myapp.struts.model.ModelException;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author Arles Mathieu
 */
public class EmployeAction extends SuperAction {

    public ActionForward addEmploye(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        IEmployeModel model = (IEmployeModel) super.getModel(MODEL_EMPLOYE);
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

            model.insertEmploye(form);
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

    public ActionForward deleteEmploye(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        IEmployeModel model = (IEmployeModel) super.getModel(MODEL_EMPLOYE);
        // Cible par defaut en cas de succ�s
        String target = "success";

        // Teste si l'utilisateur est identifie
        HttpSession session = request.getSession();
        if (session.getAttribute("USER") == null) {

            // L'utilisateur n'est pas identifie
            target = "login";
            ActionMessages errors = new ActionMessages();

            errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage("errors.login.required"));

            // Signaler les erreurs eventuelles
            // au formulaire originel
            if (!errors.isEmpty()) {

                saveErrors(request, errors);
            }

        }

        try {
            model.deleteEmploye(request.getParameter("username"));
        } catch (Exception e) {

            target = "error";
            ActionMessages errors = new ActionMessages();

            errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage("errors.database.error",
                            e.getMessage()));

            // Signaler les erreurs eventuelles
            if (!errors.isEmpty()) {

                saveErrors(request, errors);
            }
        }
        // Transmission a la vue appropriee
        return (mapping.findForward(target));
    }

    public ActionForward editEmploye(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        IEmployeModel model = (IEmployeModel) super.getModel(MODEL_EMPLOYE);
        // Cible par defaut en cas de succes
        String target = "success";

        // Teste sur l'identification de l'utilisateur
        HttpSession session = request.getSession();
        if (session.getAttribute("USER") == null) {

            // L'utilisateur n'est pas identifie
            target = "login";
            ActionMessages errors = new ActionMessages();

            errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage("errors.login.required"));

            // Retourner les erreurs eventuelles au formulaire
            // d'origine.
            if (!errors.isEmpty()) {

                saveErrors(request, errors);
            }
            // Transmettre la requete a la vue appropriee
            return (mapping.findForward(target));
        }

        if (isCancelled(request)) {

            // Action annulee
            return (mapping.findForward(target));
        }

        try {

            model.updateUser(form);
        } catch (Exception e) {

            target = "error";
            ActionMessages errors = new ActionMessages();

            errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage("errors.database.error",
                            e.getMessage()));

            // Signaler les erreurs eventuelles
            if (!errors.isEmpty()) {

                saveErrors(request, errors);
            }
        }
        // Transmettre la requete a la vue appropriee
        return (mapping.findForward(target));
    }

    public ActionForward getListeEmploye(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException, ModelException {

        IEmployeModel model = (IEmployeModel) super.getModel(MODEL_EMPLOYE);
        // Default target to success
        String target = "success";

        // Teste si l'utilisateur est identifie
        HttpSession session = request.getSession();
        if (session.getAttribute("USER") == null) {

            // The user is not logged in
            target = "login";
            ActionMessages errors = new ActionMessages();

            errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage("errors.login.required"));

            // Report any errors we have discovered back to
            // the original form
            if (!errors.isEmpty()) {

                saveErrors(request, errors);
            }

        }

        ArrayList employes;

        employes = model.getEmployes();

        // Set the target to failure
        if (employes == null) {

            target = "login";
        } else {

            System.out.println("employes" + employes);
            request.setAttribute("employes", employes);
        }
        // Forward to the appropriate View
        return (mapping.findForward(target));
    }

    public ActionForward login(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException, ModelException {

        IEmployeModel model = (IEmployeModel) super.getModel(MODEL_EMPLOYE);
        String user;

        // Cible par defaut en cas de succes
        String target = "success";

    // Utilisation de LoginForm pour obtenir les parametres
        // de la requete
        String username = ((LoginForm) form).getUsername();
        String password = ((LoginForm) form).getPassword();

        user = model.getUser(username, password);

        // Cible en cas d'echec
        if (user == null) {

            target = "login";
            ActionMessages errors = new ActionMessages();

            errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage("errors.login.unknown", username));

            // Enregistrer les erreurs  trouvees dans le formulaire original
            if (!errors.isEmpty()) {

                saveErrors(request, errors);
            }
        } else {

            HttpSession session = request.getSession();
            session.setAttribute("USER", user);
        }
        // Transmission de la requete a la vue appropriee
        return (mapping.findForward(target));
    }
}
