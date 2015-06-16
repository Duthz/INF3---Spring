/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Vector;
import javax.ejb.Stateful;

/**
 *
 * @author Arles Mathieu
 */
@Stateful
public class CartBean implements CartBeanRemote {

    private String customerName;
    private String customerId;
    private Vector contents;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void initialize(String person) throws BookException {
        if (person == null) {
            throw new BookException("Null person not allowed.");
        } else {
            customerName = person;
            customerId = "0";
            contents = new Vector();
        }
    }

    @Override
    public void initialize(String person, String id) throws BookException {
        if (person == null) {
            throw new BookException("Null person not allowed.");
        } else {
            customerName = person;
        }
        customerId = "0";
        IdVerifier idChecker = new IdVerifier();
        if (idChecker.validate(id)) {
            customerId = id;
        } else {
            throw new BookException("Invalid id: " + id);
        }
        contents = new Vector();
    }

    @Override
    public void addBook(String title) {
        contents.add(title);
    }

    @Override
    public void removeBook(String title) throws BookException {
        boolean result = contents.remove(title);
        if (result == false) {
            throw new BookException(title + "not in cart.");
        }
    }

    @Override
    public Vector getContent() {
        return contents;
    }

    @Override
    public void remove() {
        contents = null;
    }

    
}
