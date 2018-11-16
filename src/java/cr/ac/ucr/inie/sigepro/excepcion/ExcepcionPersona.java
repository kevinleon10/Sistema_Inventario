/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.excepcion;

/**
 *
 * @author Jorge Remón
 */
public class ExcepcionPersona extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionPersona</code> without detail
     * message.
     */
    public ExcepcionPersona() {
    }

    /**
     * Constructs an instance of <code>ExcepcionPersona</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionPersona(String msg) {
        super(msg);
    }
}
