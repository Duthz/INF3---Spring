package com.myapp.struts.action;

import com.myapp.struts.formbean.EmployeForm;
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


public class EditEmployeAction extends Action {

  protected void updateUser(ActionForm form)
    throws Exception {

    String user = null;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    

    try {

      EmployeForm eForm = (EmployeForm)form;
      Class.forName ("org.apache.derby.jdbc.ClientDriver");
      conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
      stmt = conn.createStatement();

      StringBuilder sqlString =
        new StringBuilder("update employes set password='");

      sqlString.append(eForm.getPassword());
      sqlString.append("', ");
      sqlString.append("roleid=");
      sqlString.append(eForm.getRoleid());
      sqlString.append(", ");
      sqlString.append("name='");
      sqlString.append(eForm.getName());
      sqlString.append("', ");
      sqlString.append("phone='");
      sqlString.append(eForm.getPhone());
      sqlString.append("', ");
      sqlString.append("email='");
      sqlString.append(eForm.getEmail());
      sqlString.append("', ");
      sqlString.append("depid=");
      sqlString.append(eForm.getDepid());
      sqlString.append(" where username='");
      sqlString.append(eForm.getUsername());
      sqlString.append("'");
      stmt.execute(sqlString.toString());
    }
    finally {

      if (rs != null) {

          rs.close();
      }
      if (stmt != null) {

          stmt.close();
      }
      if (conn != null) {

          conn.close();
      }
    }
  }

  @Override
  public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response)
    throws IOException, ServletException {

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

        updateUser(form);
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