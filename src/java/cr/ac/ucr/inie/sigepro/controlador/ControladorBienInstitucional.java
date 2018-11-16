/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.controlador;

import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionAdquisicion;
import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionBienInstitucional;
import cr.ac.ucr.inie.sigepro.modelo.ModeloBienInstitucional;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiBienInstitucional;
import java.io.IOException;
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
public class ControladorBienInstitucional implements Serializable{
    
    private final HttpSession sesionHttp;
    private int consecutivo;
    private String marca;
    private String modelo;
    private String condicionEstado;
    private String descripcionEstado;
    private String ubicacion;
    private String placa;
    private String proceso;
    private String observacione;
    private boolean equipoComputo;
    private String cedula;
    private String descripcion;
    
    private int num;
    /**
     * Creates a new instance of ControladorBienInstitucional
     */
    public ControladorBienInstitucional() {
                sesionHttp = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
    
    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProceso() {
        this.proceso = "Activo";
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }
    
    public List<SiBienInstitucional> getListaBienesSinAdquisicion() throws ExcepcionBienInstitucional {
        List<SiBienInstitucional> listaBienes = null;
        try {
            ModeloBienInstitucional Bien = new ModeloBienInstitucional();
            listaBienes = Bien.getListaBienesInstitucionalesSinAdquisicion();
        } catch (ExcepcionAdquisicion ex) {
            Logger.getLogger(ControladorBienInstitucional.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaBienes;
    }
    
    public List<SiBienInstitucional> getListaBienes() throws ExcepcionBienInstitucional {
        List<SiBienInstitucional> listaBienes = null;
        try {
            ModeloBienInstitucional Bien = new ModeloBienInstitucional();
            listaBienes = Bien.getListaBienes();
        } catch (ExcepcionBienInstitucional ex) {
            Logger.getLogger(ControladorBienInstitucional.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaBienes;
    }
    
    public void mostrarFrmBienesInstitucionales() {
        try {
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_BIEN", "BIEN_INSTITUCIONAL");
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_BIEN", "Bienes Institucionales");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema_Inventario/contenido/Activo/frmActivos.do");
        } catch (IOException ex) {
            Logger.getLogger(ControladorAdministrativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getTituloPagina() {
        String tituloPagina = "";
        if (sesionHttp.getAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_BIEN") != null) {
            tituloPagina = (String) sesionHttp.getAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_BIEN");
        }
        return tituloPagina;
    }
    
    public void mostrarFrmAgregarBienInstitucional() {
        try {
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_BIEN", "BIEN_INSTITUCIONAL");
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_BIEN", "Agregar Bien Institucional");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema_Inventario/contenido/Activo/frmMantenimientoAgregarActivos.do");
        } catch (IOException ex) {
            Logger.getLogger(ControladorAdministrativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public int getUltimoNumBienInstitucional() {
        try {
            ModeloBienInstitucional bien = new ModeloBienInstitucional();
            this.num = bien.getUltimoNum();
            this.getListaBienes();
        } catch (ExcepcionBienInstitucional ex) {
            Logger.getLogger(ControladorTraslado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (this.num+1);
    }
}

