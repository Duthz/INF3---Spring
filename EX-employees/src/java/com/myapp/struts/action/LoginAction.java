package com.myapp.struts.action;

import com.myapp.struts.formbean.LoginForm;
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

public class LoginAction extends Action {

  protected String getUser(String username, String password) {

    String user = null;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;


    try {

      Class.forName ("org.apache.derby.jdbc.ClientDriver");
      conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
      stmt = conn.createStatement();
      rs = stmt.executeQuery("select * from employes where username=\'"
        + username + "' "
        + "and password='" + password + "'");

      if ( rs.next() ) {

        user = rs.getString("username");
        // Iteration sur le resultat
        System.err.println("Username : "
          + user
          + " Password : " + rs.getString("password"));
      }
      else {

        System.err.println("---->Utilisateur non trouve<----");
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
    return user;
  }

  @Override
  public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response)
    throws IOException, ServletException {

    String user;

    // Cible par defaut en cas de succes
    String target = "success";

    // Utilisation de LoginForm pour obtenir les parametres
    // de la requete
    String username = ((LoginForm)form).getUsername();
    String password = ((LoginForm)form).getPassword();

    user = getUser(username, password);

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
