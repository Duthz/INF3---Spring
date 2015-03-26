package com.myapp.struts.action;

import com.myapp.struts.formbean.LoginForm;
import com.myapp.struts.model.IEmployeModel;
import com.myapp.struts.model.ModelException;
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
import java.sql.SQLException;

public class LoginAction extends SuperAction {

  @Override
  public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response)
    throws IOException, ServletException, ModelException {
    
    IEmployeModel model = (IEmployeModel) super.getIModel();

    String user;

    // Cible par defaut en cas de succes
    String target = "success";

    // Utilisation de LoginForm pour obtenir les parametres
    // de la requete
    String username = ((LoginForm)form).getUsername();
    String password = ((LoginForm)form).getPassword();

    user = model.getUser(username, password);

    // Cible en cas d'echec
    if ( user == null ) {

      target = "login";
      ActionMessages errors = new ActionMessages();

        errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("errors.login.unknown", username));
        

      // Enregistrer les erreurs  trouvees dans le formulaire original
      if (!errors.isEmpty()) {

        saveErrors(request, errors);
      }
    }
    else {

      HttpSession session = request.getSession();
      session.setAttribute("USER", user);
    }
    // Transmission de la requete a la vue appropriee
    return (mapping.findForward(target));
  }
}
