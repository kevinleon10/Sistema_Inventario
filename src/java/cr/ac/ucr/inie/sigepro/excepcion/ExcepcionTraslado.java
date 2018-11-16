/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.excepcion;

/**
 *
 * @author lskev
 */
public class ExcepcionTraslado extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionTraslado</code> without detail
     * message.
     */
    public ExcepcionTraslado() {
    }

    /**
     * Constructs an instance of <code>ExcepcionTraslado</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionTraslado(String msg) {
        super(msg);
    }
}
