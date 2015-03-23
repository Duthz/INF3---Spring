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


public class AddEmployeAction extends Action {

  protected void insertUser(ActionForm form)
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
        new StringBuilder("insert into employes values ('");

      sqlString.append(eForm.getUsername()).append("', ");
      sqlString.append("'").append(eForm.getPassword()).append("', ");
      sqlString.append("'").append(eForm.getName()).append("', ");
      sqlString.append(eForm.getRoleid()).append(", ");
      sqlString.append("'").append(eForm.getPhone()).append("', ");
      sqlString.append("'").append(eForm.getEmail()).append("', ");
      sqlString.append(eForm.getDepid()).append(")");

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

    // teste l'identification de l'utilisateur ?
    
      HttpSession session = request.getSession();
      if ( session.getAttribute("USER") == null ) {

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

    if ( isCancelled(request) ) {

      // Annulation. Retour a la liste des employes
      return (mapping.findForward(target));
    }

    try {

      insertUser(form);
    }
    catch (Exception e) {

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