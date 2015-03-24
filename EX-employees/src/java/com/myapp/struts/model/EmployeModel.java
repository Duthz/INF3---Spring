/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.model;

import com.myapp.struts.bean.Employe;
import com.myapp.struts.formbean.EmployeForm;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author Arles Mathieu
 */
public class EmployeModel implements IEmployeModel {

    private DataSource datasource;

    public DataSource getDatasource() {
        return datasource;
    }

    public void setDatasource(DataSource datasource) {
        this.datasource = datasource;
    }

    private Connection getConnection() throws SQLException {
        return this.getDatasource().getConnection();
    }

    @Override
    public void deleteEmploye(String username) throws ModelException {
        try {
            String user = null;
            Connection conn;
            Statement stmt;

            conn = this.getConnection();
            stmt = conn.createStatement();

            StringBuilder sqlString = new StringBuilder("delete from employes where username='");
            sqlString.append(username).append("'");

            boolean execute = stmt.execute(sqlString.toString());

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            throw new ModelException(ex.getMessage());
        }

    }

    @Override
    public void insertEmploye(ActionForm form) throws ModelException {
        try {
            String user = null;
            Connection conn;
            Statement stmt;

            EmployeForm eForm = (EmployeForm) form;
            conn = this.getConnection();
            stmt = conn.createStatement();

            StringBuilder sqlString = new StringBuilder("insert into employes values ('");

            sqlString.append(eForm.getUsername()).append("', ");
            sqlString.append("'").append(eForm.getPassword()).append("', ");
            sqlString.append("'").append(eForm.getName()).append("', ");
            sqlString.append(eForm.getRoleid()).append(", ");
            sqlString.append("'").append(eForm.getPhone()).append("', ");
            sqlString.append("'").append(eForm.getEmail()).append("', ");
            sqlString.append(eForm.getDepid()).append(")");

            stmt.execute(sqlString.toString());

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    @Override
    public void updateUser(ActionForm form) throws ModelException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ActionForm buildEmployeForm(String username) throws ModelException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUser(String username, String password) throws ModelException {

        try {
            String user = null;
            Connection conn;
            Statement stmt;
            ResultSet rs;

            conn = this.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from employes where username=\'"
                    + username + "' "
                    + "and password='" + password + "'");

            if (rs.next()) {

                user = rs.getString("username");
                // Iteration sur le resultat
                System.err.println("Username : "
                        + user
                        + " Password : " + rs.getString("password"));
            } else {

                System.err.println("---->Utilisateur non trouve<----");
            }

            rs.close();
            stmt.close();
            conn.close();

            return user;
        } catch (SQLException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    @Override
    public ArrayList getEmployes() throws ModelException {
        try {
            Employe employe;
            ArrayList employes = new ArrayList();
            Connection conn;
            Statement stmt;
            ResultSet rs;

            conn = this.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from employes, roles, "
                    + "services where employes.roleid=roles.roleid "
                    + "and employes.depid=services.depid");

            while (rs.next()) {
                employe = new Employe();

                employe.setUsername(rs.getString("username"));
                employe.setName(rs.getString("name"));
                employe.setRolename(rs.getString("rolename"));
                employe.setPhone(rs.getString("phone"));
                employe.setEmail(rs.getString("email"));
                employe.setRoleid((Integer) (rs.getInt("roleid")));
                employe.setDepid((Integer) (rs.getInt("depid")));
                employe.setDepartment(rs.getString("depname"));

                employes.add(employe);

                System.err.println("Username : " + employe.getUsername()
                        + " Department : " + employe.getDepartment());
            }

            rs.close();
            stmt.close();
            conn.close();

            return employes;
        } catch (SQLException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
}
