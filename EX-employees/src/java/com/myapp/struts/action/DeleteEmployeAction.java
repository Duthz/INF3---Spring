package com.myapp.struts.action;

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



public class DeleteEmployeAction extends SuperAction {

 

  @Override
  public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response)
    throws IOException, ServletException {
      
              IEmployeModel model = (IEmployeModel) super.getModel();


    // Cible par defaut en cas de succ�s
    String target = "success";

    // Teste si l'utilisateur est identifie
      HttpSession session = request.getSession();
      if ( session.getAttribute("USER") == null ) {

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
    // Transmission a la vue appropriee
    return (mapping.findForward(target));
  }
}