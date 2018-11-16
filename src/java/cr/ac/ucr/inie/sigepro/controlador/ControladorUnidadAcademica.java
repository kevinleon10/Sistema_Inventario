/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.controlador;

import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionUnidadAcademica;
import cr.ac.ucr.inie.sigepro.modelo.ModeloUnidadAcademica;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiUnidadAcademica;
import java.io.Serializable;
import java.util.List;
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
public class ControladorUnidadAcademica implements Serializable{

    private final HttpSession sesionHttp;
    
     private String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     private String nombre;
     
    /**
     * Creates a new instance of ControladorUnidadAcademica
     */
    public ControladorUnidadAcademica() {
        sesionHttp = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public List<SiUnidadAcademica> getListaUnidadesAcademicasQueTrasladan() throws ExcepcionUnidadAcademica {
        List<SiUnidadAcademica> listaUnidadesAcademicas = null;
        try {
            ModeloUnidadAcademica unidadaAcademica = new ModeloUnidadAcademica();
            listaUnidadesAcademicas = unidadaAcademica.getListaUnidadesAcademicasQueTrasladan();
        } catch (ExcepcionUnidadAcademica ex) {
            Logger.getLogger(ControladorAdquisicion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaUnidadesAcademicas;
    }
}
