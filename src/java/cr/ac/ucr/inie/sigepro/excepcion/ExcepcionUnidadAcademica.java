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
public class ExcepcionUnidadAcademica extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionUnidadAcademica</code> without
     * detail message.
     */
    public ExcepcionUnidadAcademica() {
    }

    /**
     * Constructs an instance of <code>ExcepcionUnidadAcademica</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionUnidadAcademica(String msg) {
        super(msg);
    }
}
