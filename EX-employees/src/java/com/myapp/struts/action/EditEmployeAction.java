package com.myapp.struts.action;

import com.myapp.struts.formbean.EmployeForm;
import com.myapp.struts.model.IEmployeModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;


public class EditEmployeAction extends SuperAction {

  @Override
  public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response)
    throws IOException, ServletException {
      
    IEmployeModel model = (IEmployeModel) super.getIModel();

    // Cible par defaut en cas de succes
    String target = "success";


    // Teste sur l'identification de l'utilisateur
	  HttpSession session = request.getSession();
      if ( session.getAttribute("USER") == null ) {

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

    if ( isCancelled(request) ) {

      // Action annulee
      return (mapping.findForward(target));
    }

    try {
        EmployeForm eForm = (EmployeForm) form;
        model.updateUser(eForm.formToEmploye());
    }
    catch (Exception e) {

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
}