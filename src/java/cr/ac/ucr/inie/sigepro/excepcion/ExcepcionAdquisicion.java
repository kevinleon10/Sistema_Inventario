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
public class ExcepcionAdquisicion extends Exception {

    /**
     * Creates a new instance of <code>SI_Excepcion_Adquiscion</code> without
     * detail message.
     */
    public ExcepcionAdquisicion() {
    }

    /**
     * Constructs an instance of <code>SI_Excepcion_Adquiscion</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionAdquisicion(String msg) {
        super(msg);
    }
}
