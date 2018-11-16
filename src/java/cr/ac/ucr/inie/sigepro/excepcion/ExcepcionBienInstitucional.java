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
public class ExcepcionBienInstitucional extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionBienInstitucional</code> without
     * detail message.
     */
    public ExcepcionBienInstitucional() {
    }

    /**
     * Constructs an instance of <code>ExcepcionBienInstitucional</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionBienInstitucional(String msg) {
        super(msg);
    }
}
