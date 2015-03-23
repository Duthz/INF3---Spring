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


public class GetEmployeAction extends Action {

  protected ActionForm buildEmployeForm(String username) throws Exception {

    String user = null;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    EmployeForm form = null;


    try {

      Class.forName ("org.apache.derby.jdbc.ClientDriver");
      conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
      stmt = conn.createStatement();
      rs = stmt.executeQuery("select * from employes where username=\'"
        + username + "'");

      if ( rs.next() ) {

        form = new EmployeForm();

        form.setUsername(rs.getString("username"));
        form.setPassword(rs.getString("password"));
        form.setDepid(rs.getString("depid"));
        form.setRoleid(rs.getString("roleid"));
        String name = rs.getString("name");
        System.err.println("---->" + name + "<----");
        form.setName(name);
        form.setPhone(rs.getString("phone"));
        form.setEmail(rs.getString("email"));
      }
      else {

        throw new Exception("Employe " + username + " non trouve!");
      }
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
    return form;
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

        // Retourner les erreurs eventuelles au formulaire d'origine
        if (!errors.isEmpty()) {

          saveErrors(request, errors);
        }
 
    }

    if ( isCancelled(request) ) {

      // Annulation depuis la liste des employes
      return (mapping.findForward(target));
    }

    try {

      form = buildEmployeForm(request.getParameter("username"));
      
      if ( form == null ) {
      
        System.err.println("---->form est null<----");
      }
      
      if ("request".equals(mapping.getScope())) {
      
        System.err.println("---->request<----");
        request.setAttribute(mapping.getAttribute(), form);
        System.err.println("---->request<----");
      }
      else {
      
        System.err.println("---->session<----");
        session = request.getSession();
        System.err.println("---->session<----");
        session.setAttribute(mapping.getAttribute(), form);
      }
    }
    catch (Exception e) {

      System.err.println("Setting target to error");
      System.err.println("---->" + e.getMessage() + "<----");
      target = "error";
      ActionMessages errors = new ActionMessages();

      errors.add(ActionMessages.GLOBAL_MESSAGE,
        new ActionMessage("errors.database.error", e.getMessage()));

      // Retourner les erreurs eventuelles
      if (!errors.isEmpty()) {

        saveErrors(request, errors);
      }
    }
    // Transmettre la requete a la vue appropriee
    return (mapping.findForward(target));
  }
}