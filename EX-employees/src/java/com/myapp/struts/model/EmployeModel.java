/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.model;

import com.myapp.struts.bean.Employe;
import com.myapp.struts.formbean.EmployeForm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author Arles Mathieu
 */
public class EmployeModel implements IEmployeModel {

    @Override
    public void deleteEmploye(String username) {

        try {
            String user = null;
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            stmt = conn.createStatement();

            StringBuilder sqlString
                    = new StringBuilder("delete from employes where username='");
            sqlString.append(username).append("'");

            boolean execute = stmt.execute(sqlString.toString());

            if (rs != null) {

                rs.close();

            }
            if (stmt != null) {

                stmt.close();
            }
            if (conn != null) {

                conn.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeModel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void insertEmploye(ActionForm form) {
        try {
            String user = null;
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            
            EmployeForm eForm = (EmployeForm) form;
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            stmt = conn.createStatement();
            
            StringBuilder sqlString
                    = new StringBuilder("insert into employes values ('");
            
            sqlString.append(eForm.getUsername()).append("', ");
            sqlString.append("'").append(eForm.getPassword()).append("', ");
            sqlString.append("'").append(eForm.getName()).append("', ");
            sqlString.append(eForm.getRoleid()).append(", ");
            sqlString.append("'").append(eForm.getPhone()).append("', ");
            sqlString.append("'").append(eForm.getEmail()).append("', ");
            sqlString.append(eForm.getDepid()).append(")");
            
            stmt.execute(sqlString.toString());
            
            if (rs != null) {
                
                rs.close();
            }
            if (stmt != null) {
                
                stmt.close();
            }
            if (conn != null) {
                
                conn.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeModel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void updateUser(ActionForm form) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ActionForm buildEmployeForm(String username) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUser(String username, String password){

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
    public ArrayList getEmployes()  {

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

}
