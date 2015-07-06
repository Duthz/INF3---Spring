/*
 * ModelExceptionEJB.java
 *
 * Created on 28 mars 2007, 12:10
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.EJBException;

/**
 *
 * @author carine
 */
public class ModelExceptionEJB extends EJBException{
    
    /** Creates a new instance of ModelException */
    public ModelExceptionEJB() {
    };
    public ModelExceptionEJB(String msg) {super(msg);
    }
    
}
