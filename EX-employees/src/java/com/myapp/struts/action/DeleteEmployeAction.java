package com.myapp.struts.action;

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


public class DeleteEmployeAction extends Action {

  protected void deleteEmploye(String username)
    throws Exception {

    String user = null;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    

    try {

      Class.forName ("org.apache.derby.jdbc.ClientDriver");
      conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
      stmt = conn.createStatement();

      StringBuilder sqlString =
        new StringBuilder("delete from employes where username='");
      sqlString.append(username).append("'");

        boolean execute = stmt.execute(sqlString.toString());
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

      deleteEmploye(request.getParameter("username"));
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