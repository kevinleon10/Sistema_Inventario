/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jorge Remón
 */
@ManagedBean
@ViewScoped
public class ControladorAdministrativo  implements Serializable {

    private final HttpSession sesionHttp;
    
    /**
     * Creates a new instance of SI_Controlador_Administrativo
     */
    public ControladorAdministrativo() {
        sesionHttp = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
    
    public void mostrarInicio() {
        try {
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADMINISTRATIVO", "BIEN_ADMINISTRACION_INICIO");
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADMINISTRATIVO", "Inicio");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema_Inventario/homePage.do");
        } catch (IOException ex) {
            Logger.getLogger(ControladorAdministrativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
