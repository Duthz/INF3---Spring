/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import bean.Employe;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author Arles Mathieu
 */
@Stateless
public class EmployeModel implements EmployeModelRemote {

    private final String DB = "jdbc:derby://localhost:1527/sample";
    private final String PW = "app";
    private final String USER = "app";

    private Connection connexion() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            return DriverManager.getConnection(DB, USER, PW);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Connection getConnection(DataSource datasource) throws SQLException {
        return datasource.getConnection();
    }

    /**
     *
     * @param employee the value of employee
     * @throws ejb.ModelExceptionEJB
     */
    @Override
    public void insertEmployee(bean.Employe employee) throws ModelExceptionEJB {
        try {
            String user = null;
            Connection conn = this.connexion();
            Statement stmt;

            stmt = conn.createStatement();

            StringBuilder sqlString = new StringBuilder("insert into employes values ('");

            sqlString.append(employee.getUsername()).append("', ");
            sqlString.append("'").append(employee.getPassword()).append("', ");
            sqlString.append("'").append(employee.getName()).append("', ");
            sqlString.append(employee.getRoleid()).append(", ");
            sqlString.append("'").append(employee.getPhone()).append("', ");
            sqlString.append("'").append(employee.getEmail()).append("', ");
            sqlString.append(employee.getDepid()).append(")");

            stmt.execute(sqlString.toString());

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            throw new ModelExceptionEJB(ex.getMessage());
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void deleteEmployee(String username) throws ModelExceptionEJB {
        try {
            String user = null;
            Connection conn = this.connexion();
            Statement stmt;

            stmt = conn.createStatement();

            StringBuilder sqlString = new StringBuilder("delete from employes where username='");
            sqlString.append(username).append("'");

            boolean execute = stmt.execute(sqlString.toString());

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            throw new ModelExceptionEJB(ex.getMessage());
        }
    }

    @Override
    public void updateEmployee(Employe employe) throws ModelExceptionEJB {

        try {
            String user = null;
            Connection conn = connexion();
            Statement stmt;

            stmt = conn.createStatement();

            StringBuilder sqlString
                    = new StringBuilder("update employes set password='");

            sqlString.append(employe.getPassword());
            sqlString.append("', ");
            sqlString.append("roleid=");
            sqlString.append(employe.getRoleid());
            sqlString.append(", ");
            sqlString.append("name='");
            sqlString.append(employe.getName());
            sqlString.append("', ");
            sqlString.append("phone='");
            sqlString.append(employe.getPhone());
            sqlString.append("', ");
            sqlString.append("email='");
            sqlString.append(employe.getEmail());
            sqlString.append("', ");
            sqlString.append("depid=");
            sqlString.append(employe.getDepid());
            sqlString.append(" where username='");
            sqlString.append(employe.getUsername());
            sqlString.append("'");
            stmt.execute(sqlString.toString());

            stmt.close();

            conn.close();
        } catch (SQLException ex) {
            throw new ModelExceptionEJB(ex.getMessage());
        }

    }

    @Override
    public Employe getEmployeByUserName(String username) throws ModelExceptionEJB {
        try {
            String user = null;
            Connection conn = connexion();
            Statement stmt;
            ResultSet rs;
            Employe e;

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

                throw new ModelExceptionEJB("Employe " + username + " non trouve!");
            }

            rs.close();

            stmt.close();

            conn.close();

            return e;
        } catch (SQLException ex) {
            throw new ModelExceptionEJB(ex.getMessage());
        }
    }

    @Override
    public String getUser(String username, String password) throws ModelExceptionEJB {

        try {
            String user = null;
            Connection conn = connexion();
            Statement stmt;
            ResultSet rs;

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
            throw new ModelExceptionEJB(ex.getMessage());
        }
    }

    @Override
    public List getEmployes() throws ModelExceptionEJB {
        try {
            Employe employe;
            ArrayList employes = new ArrayList();
            Connection conn = connexion();
            Statement stmt;
            ResultSet rs;

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
            throw new ModelExceptionEJB(ex.getMessage());
        }
    }

}
