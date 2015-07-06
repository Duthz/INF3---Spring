/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import bean.Employe;
import java.util.List;
import javax.ejb.Remote;
import javax.sql.DataSource;

/**
 *
 * @author Arles Mathieu
 */
@Remote
public interface EmployeModelRemote {

    void insertEmployee(DataSource datasource, Employe employee) throws ModelException;

    void deleteEmployee(DataSource datasource, String username) throws ModelException;

    void updateEmployee(DataSource datasource, Employe employe) throws ModelException;

    Employe getEmployeByUserName( DataSource datasource,String username) throws ModelException;

    String getUser( DataSource datasource,String username, String password) throws ModelException;

    List getEmployes(DataSource datasource) throws ModelException;
    
    
}
