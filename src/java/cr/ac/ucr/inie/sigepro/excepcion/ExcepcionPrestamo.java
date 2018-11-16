/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.excepcion;

/**
 *
 * @author Daniel
 */
public class ExcepcionPrestamo extends Exception {

    /**
     * Creates a new instance of
     * <code>Sistema_Inventario_Excepcion_Prestamo</code> without detail
     * message.
     */
    public ExcepcionPrestamo() {
    }

    /**
     * Constructs an instance of
     * <code>Sistema_Inventario_Excepcion_Prestamo</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionPrestamo(String msg) {
        super(msg);
    }
}
