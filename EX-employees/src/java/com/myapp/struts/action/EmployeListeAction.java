package com.myapp.struts.action;

import com.myapp.struts.bean.Employe;
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
import java.util.ArrayList;

public class EmployeListeAction extends Action {

  protected ArrayList getEmployes() {

    Employe employe;
    ArrayList employes = new ArrayList();
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;


    try {

      Class.forName ("org.apache.derby.jdbc.ClientDriver");
      conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
      stmt = conn.createStatement();
      rs =
        stmt.executeQuery("select * from employes, roles, "
        + "services where employes.roleid=roles.roleid "
        + "and employes.depid=services.depid");

      while ( rs.next() ) {

        employe = new Employe();

        employe.setUsername(rs.getString("username"));
        employe.setName(rs.getString("name"));
        employe.setRolename(rs.getString("rolename"));
        employe.setPhone(rs.getString("phone"));
        employe.setEmail(rs.getString("email"));
        employe.setRoleid((Integer)(rs.getInt("roleid")));
        employe.setDepid((Integer)(rs.getInt("depid")));
        employe.setDepartment(rs.getString("depname"));

        employes.add(employe);

        System.err.println("Username : "+ employe.getUsername()
          + " Department : " + employe.getDepartment());
      }
    }
    catch (ClassNotFoundException | SQLException e) {

      System.err.println(e.getMessage());
    }
    
    finally {

      if (rs != null) {

        try {

          rs.close();
        }
        catch (SQLException sqle) {

          System.err.println(sqle.getMessage());
        }
      }
      if (stmt != null) {

        try {

          stmt.close();
        }
        catch (SQLException sqle) {

          System.err.println(sqle.getMessage());
        }
      }
      if (conn != null) {

        try {

          conn.close();
        }
        catch (SQLException sqle) {

          System.err.println(sqle.getMessage());
        }
      }
    }

    return employes;
  }

  @Override
  public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response)
    throws IOException, ServletException {

    // Default target to success
    String target = "success";
    
    // Teste si l'utilisateur est identifie
      HttpSession session = request.getSession();
      if ( session.getAttribute("USER") == null ) {

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

    employes = getEmployes();

    // Set the target to failure
    if ( employes == null ) {

      target = "login";
    }
    else {

	  System.out.println ("employes" + employes);
      request.setAttribute("employes", employes);
    }
    // Forward to the appropriate View
    return (mapping.findForward(target));
  }
}