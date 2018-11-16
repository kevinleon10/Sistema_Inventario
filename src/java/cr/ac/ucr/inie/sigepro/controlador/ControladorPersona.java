/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.controlador;

import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionPersona;
import cr.ac.ucr.inie.sigepro.modelo.ModeloPersona;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiPersona;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jorge Remón
 */
@ManagedBean
@javax.faces.bean.ViewScoped
public class ControladorPersona implements Serializable{

    private final HttpSession sesionHttp;

    
     private String cedula;
     private String nombre;
     private String primerApellido;
     private String segundoApellido;
     private boolean funcionarioInie;
     
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public boolean isFuncionarioInie() {
        return funcionarioInie;
    }

    public void setFuncionarioInie(boolean funcionarioInie) {
        this.funcionarioInie = funcionarioInie;
    }
    
   
    /**
     * Creates a new instance of ControladorUnidadAcademica
     */
    public ControladorPersona() {
        sesionHttp = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public List<SiPersona> getListaPersonasFueraDelINIE() throws ExcepcionPersona {
        List<SiPersona> listaPersonas = null;
        try {
            ModeloPersona persona = new ModeloPersona();
            listaPersonas = persona.getListaPersonasFueraDelINIE();
        } catch (ExcepcionPersona ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaPersonas;
    }
    
    public List<SiPersona> getListaPersonasDentroDelINIE() throws ExcepcionPersona {
        List<SiPersona> listaPersonas = null;
        try {
            ModeloPersona persona = new ModeloPersona();
            listaPersonas = persona.getListaPersonasDentroDelINIE();
        } catch (ExcepcionPersona ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaPersonas;
    }
    
    public List<SiPersona> getListaPersonas() throws ExcepcionPersona {
        List<SiPersona> listaPersonas = null;
        try {
            ModeloPersona persona = new ModeloPersona();
            listaPersonas = persona.getListaPersonas();
        } catch (ExcepcionPersona ex) {
            Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaPersonas;
    }
}
