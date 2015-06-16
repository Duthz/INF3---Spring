/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

/**
 *
 * @author Arles Mathieu
 */
public class BookException extends Exception {

    /**
     * Creates a new instance of <code>BookException</code> without detail
     * message.
     */
    public BookException() {
    }

    /**
     * Constructs an instance of <code>BookException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public BookException(String msg) {
        super(msg);
    }
}
