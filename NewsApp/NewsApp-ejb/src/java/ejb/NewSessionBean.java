/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author Arles Mathieu
 */
@Stateless
public class NewSessionBean implements NewSessionBeanRemote {

    @Resource(name = "sample")
    private DataSource sample;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void create(String title, String body) throws EJBException {
        Connection cnx = null;
        Statement stmt = null;
        ResultSet res = null;
        try {
            cnx = sample.getConnection();
//perform	the	calcultation	of	 the	biggest	identifier	(idLong)
            stmt = cnx.createStatement();
            String query = "Select	max(ID)	from APP.NEWSENTITY";
            res = stmt.executeQuery(query);
            long idLong;
            if (res != null) {
                res.next();
                idLong = res.getLong(1);
            } else {
                idLong = 0;
            }
            stmt.close();
//	insert	the	news	into	the	table	NEWSENTITY
            stmt = cnx.createStatement();
            idLong++;
            query = "insert	into	APP.NEWSENTITY	values	(" + idLong + ",'" + title + "','" + body + "')";
            int status = stmt.executeUpdate(query);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            throw new EJBException();
        } finally {
            if (res != null) {
                try {
                    res.close();
                } catch (SQLException sqle) {
                    System.err.println(sqle.getMessage());
                }
                res = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqle) {
                    System.err.println(sqle.getMessage());
                }
                stmt = null;
            }
            if (cnx != null) {
                try {
                    cnx.close();
                } catch (SQLException sqle) {
                    System.err.println(sqle.getMessage());
                }
                cnx = null;
            }
        }
    }

    @Override
    public ArrayList findAll() throws EJBException {
        return null;
    }

}
