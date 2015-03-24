/*
 * ModelException.java
 *
 * Created on 28 mars 2007, 12:10
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.myapp.struts.model;

/**
 *
 * @author carine
 */
public class ModelException extends Exception{
    
    /** Creates a new instance of ModelException */
    public ModelException() {
    };
    public ModelException(String msg) {super(msg);
    }
    
}
