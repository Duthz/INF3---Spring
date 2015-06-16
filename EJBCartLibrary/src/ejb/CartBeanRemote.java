/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Vector;
import javax.ejb.Remote;

/**
 *
 * @author Arles Mathieu
 */
@Remote
public interface CartBeanRemote {

    void initialize(String person) throws BookException;

    void initialize(String person, String id) throws BookException;

    void addBook(String title);

    void removeBook(String title) throws BookException;

    Vector getContent();

    void remove();

    
    
}
