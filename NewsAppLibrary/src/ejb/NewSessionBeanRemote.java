/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import javax.ejb.EJBException;
import javax.ejb.Remote;

/**
 *
 * @author Arles Mathieu
 */
@Remote
public interface NewSessionBeanRemote {

    void create(String title, String body) throws EJBException;

    ArrayList findAll() throws EJBException;
    
}
