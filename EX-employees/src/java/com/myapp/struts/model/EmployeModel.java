/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.model;

import com.myapp.struts.bean.Employe;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;

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
    public void insertEmploye(Employe e) throws ModelException {
        try {
            String user = null;
            Connection conn;
            Statement stmt;

            conn = this.getConnection();
            stmt = conn.createStatement();

            StringBuilder sqlString = new StringBuilder("insert into employes values ('");

            sqlString.append(e.getUsername()).append("', ");
            sqlString.append("'").append(e.getPassword()).append("', ");
            sqlString.append("'").append(e.getName()).append("', ");
            sqlString.append(e.getRoleid()).append(", ");
            sqlString.append("'").append(e.getPhone()).append("', ");
            sqlString.append("'").append(e.getEmail()).append("', ");
            sqlString.append(e.getDepid()).append(")");

            stmt.execute(sqlString.toString());

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    @Override
    public void updateUser(Employe e) throws ModelException {

        try {
            String user = null;
            Connection conn;
            Statement stmt;

            conn = this.getConnection();
            stmt = conn.createStatement();

            StringBuilder sqlString
                    = new StringBuilder("update employes set password='");

            sqlString.append(e.getPassword());
            sqlString.append("', ");
            sqlString.append("roleid=");
            sqlString.append(e.getRoleid());
            sqlString.append(", ");
            sqlString.append("name='");
            sqlString.append(e.getName());
            sqlString.append("', ");
            sqlString.append("phone='");
            sqlString.append(e.getPhone());
            sqlString.append("', ");
            sqlString.append("email='");
            sqlString.append(e.getEmail());
            sqlString.append("', ");
            sqlString.append("depid=");
            sqlString.append(e.getDepid());
            sqlString.append(" where username='");
            sqlString.append(e.getUsername());
            sqlString.append("'");
            stmt.execute(sqlString.toString());

            stmt.close();

            conn.close();
        } catch (SQLException ex) {
            throw new ModelException(ex.getMessage());
        }

    }

    @Override
    public Employe getEmployeByUserName(String username) throws ModelException {
        try {
            String user = null;
            Connection conn;
            Statement stmt;
            ResultSet rs;
            Employe e;

            conn = this.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from employes where username=\'"
                    + username + "'");

            if (rs.next()) {

                e = new Employe();

                e.setUsername(rs.getString("username"));
                e.setPassword(rs.getString("password"));
                e.setDepid(Integer.parseInt(rs.getString("depid")));
                e.setRoleid(Integer.parseInt(rs.getString("roleid")));
                String name = rs.getString("name");
                System.err.println("---->" + name + "<----");
                e.setName(name);
                e.setPhone(rs.getString("phone"));
                e.setEmail(rs.getString("email"));
            } else {

                throw new ModelException("Employe " + username + " non trouve!");
            }

            rs.close();

            stmt.close();

            conn.close();

            return e;
        } catch (SQLException ex) {
            throw new ModelException(ex.getMessage());
        }
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
