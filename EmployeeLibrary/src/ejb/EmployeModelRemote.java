/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import bean.Employe;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Arles Mathieu
 */
@Remote
public interface EmployeModelRemote {

    void insertEmployee(Employe employee) throws ModelExceptionEJB;

    void deleteEmployee(String username) throws ModelExceptionEJB;

    void updateEmployee(Employe employe) throws ModelExceptionEJB;

    Employe getEmployeByUserName(String username) throws ModelExceptionEJB;

    String getUser(String username, String password) throws ModelExceptionEJB;

    List getEmployes() throws ModelExceptionEJB;
    
    
}
