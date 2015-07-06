/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import bean.Employe;
import java.sql.Connection;
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




    private Connection getConnection(DataSource datasource) throws SQLException {
        return datasource.getConnection();
    }

    /**
     *
     * @param employee the value of employee
     * @throws ejb.ModelException
     */
    @Override
    public void insertEmployee(DataSource datasource, bean.Employe employee) throws ModelException {
        try {
            String user = null;
            Connection conn;
            Statement stmt;

            conn = getConnection(datasource);
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
            throw new ModelException(ex.getMessage());
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void deleteEmployee(DataSource datasource, String username) throws ModelException {
        try {
            String user = null;
            Connection conn;
            Statement stmt;

            conn = getConnection(datasource);
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
    public void updateEmployee( DataSource datasource, Employe employe) throws ModelException {

        try {
            String user = null;
            Connection conn;
            Statement stmt;

            conn = getConnection(datasource);
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
            throw new ModelException(ex.getMessage());
        }

    }

    @Override
    public Employe getEmployeByUserName(DataSource datasource, String username) throws ModelException {
        try {
            String user = null;
            Connection conn;
            Statement stmt;
            ResultSet rs;
            Employe e;

            conn = getConnection(datasource);
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
    public String getUser(DataSource datasource, String username, String password) throws ModelException {

        try {
            String user = null;
            Connection conn;
            Statement stmt;
            ResultSet rs;

            conn = getConnection(datasource);
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
    public List getEmployes(DataSource datasource) throws ModelException {
        try {
            Employe employe;
            ArrayList employes = new ArrayList();
            Connection conn;
            Statement stmt;
            ResultSet rs;

            conn = getConnection(datasource);
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
