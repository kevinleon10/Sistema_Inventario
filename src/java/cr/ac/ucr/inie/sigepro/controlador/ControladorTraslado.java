/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.controlador;

import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionTraslado;
import cr.ac.ucr.inie.sigepro.modelo.ModeloBienInstitucional;
import cr.ac.ucr.inie.sigepro.modelo.ModeloTraslado;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiBienInstitucional;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiPersona;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiTraslado;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiUnidadAcademica;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DualListModel;

/**
 *
 * @author lskev
 */

@ManagedBean
@ViewScoped
public class ControladorTraslado implements Serializable{

    private final HttpSession sesionHttp;
    private SiUnidadAcademica siUnidadAcademica;
    private SiPersona siPersona;
    private int numero;
    private int anno;
    private String numOficio;
    private String observaciones;
    private String estado;
    private Date fechaTraslado;    

    private DualListModel<SiBienInstitucional> listasBienesInstitucionales;
    /**
     * Creates a new instance of ControladorTraslado
     */
    public ControladorTraslado() {
        sesionHttp = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    @PostConstruct
    public void init() {
                if(sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_INSERTAR_TRASLADO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_CONSULTAR_TRASLADO") || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_EDITAR_TRASLADO"))) {
            try {
                ModeloBienInstitucional modeloBienInstitucional = new ModeloBienInstitucional();
                List<SiBienInstitucional> listaBienesActivos = modeloBienInstitucional.getListaBienesInstitucionalesActivos();
                List<SiBienInstitucional> listaBienesSeleccionados = new ArrayList<>();
                this.listasBienesInstitucionales = new DualListModel<>(listaBienesActivos, listaBienesSeleccionados);

            } catch (ExcepcionTraslado ex) {
                Logger.getLogger(ControladorTraslado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public DualListModel<SiBienInstitucional> getListasBienesInstitucionales() {
        if(sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_INSERTAR_TRASLADO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_CONSULTAR_TRASLADO") || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_EDITAR_TRASLADO"))) {
            if (listasBienesInstitucionales == null) {
                listasBienesInstitucionales = new DualListModel<>();
            }
            ModeloBienInstitucional modeloBienInstitucional = new ModeloBienInstitucional();
            try {
                List<SiBienInstitucional> listaBienesActivos = modeloBienInstitucional.getListaBienesInstitucionalesActivos();
                List<SiBienInstitucional> listaBienesSeleccionados = new ArrayList<>();
                if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_EDITAR_TRASLADO")
                        || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_CONSULTAR_TRASLADO"))) {
                    listaBienesSeleccionados = this.setListaBienesPertenecientesAUnTraslado(((SiTraslado) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_TRASLADO")).getId().getNumero(), ((SiTraslado) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_TRASLADO")).getId().getAnno());
                }
                for (int i = listaBienesActivos.size() - 1; i >= 0; i--) {
                    for (int j = listaBienesSeleccionados.size() - 1; j >= 0; j--) {
                        if (listaBienesSeleccionados.get(j).getConsecutivo() == listaBienesActivos.get(i).getConsecutivo()) {
                        } else {
                            listaBienesActivos.remove(i);
                            break;
                        }
                    }
                }
                listasBienesInstitucionales = new DualListModel<SiBienInstitucional>(listaBienesActivos, listaBienesSeleccionados);
            } catch (ExcepcionTraslado ex) {
                Logger.getLogger(ControladorTraslado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listasBienesInstitucionales;
    }
    
    public void setListasBienesInstitucionales(DualListModel<SiBienInstitucional> listasBienesInstitucionales) {
        this.listasBienesInstitucionales = listasBienesInstitucionales;
    }

    public List<SiBienInstitucional> setListaBienesPertenecientesAUnTraslado(int num, int anno) throws ExcepcionTraslado {
        List<SiBienInstitucional> bienesTrasladados = null;
        if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_INSERTAR_TRASLADO"))) {
            bienesTrasladados = null;
        } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_EDITAR_TRASLADO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_CONSULTAR_TRASLADO"))) {
            ModeloBienInstitucional modeloBienInstitucional = new ModeloBienInstitucional();
            bienesTrasladados = modeloBienInstitucional.getBienesInstitucionalesConTraslado(num, anno);
        }

        return bienesTrasladados;
    }
    
    public SiUnidadAcademica getSiUnidadAcademica() {
        if(sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_CONSULTAR_TRASLADO") || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_EDITAR_TRASLADO"))){
            ModeloTraslado modeloTraslado = new ModeloTraslado();
            try{
                this.siUnidadAcademica = modeloTraslado.getUnidadAcademicaTraslado(((SiTraslado) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_TRASLADO")));
            }
            catch (ExcepcionTraslado ex) {
                Logger.getLogger(ControladorTraslado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return siUnidadAcademica;
    }

    public void setSiUnidadAcademica(SiUnidadAcademica siUnidadAcademica) {
        this.siUnidadAcademica = siUnidadAcademica;
    }

    public SiPersona getSiPersona() {
        if(sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_CONSULTAR_TRASLADO") || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_EDITAR_TRASLADO"))){
           this.siPersona = ((SiTraslado) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_TRASLADO")).getSiPersona();
        }
        return siPersona;
    }

    public void setSiPersona(SiPersona siPersona) {
        this.siPersona = siPersona;
    }

    public int getNumero() {
        if(sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_CONSULTAR_TRASLADO") || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_EDITAR_TRASLADO"))){
            this.numero = ((SiTraslado) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_TRASLADO")).getId().getNumero();
        }
        else if(sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_INSERTAR_TRASLADO"))){
            this.numero = this.getUltimoNumTraslado();
        }
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAnno(){
        if(sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_CONSULTAR_TRASLADO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_EDITAR_TRASLADO")) && (sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_TRASLADO")!=null)){
            this.anno = ((SiTraslado) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_TRASLADO")).getId().getAnno();
        }
        else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_INSERTAR_TRASLADO"))){
            this.anno = Calendar.getInstance().get(Calendar.YEAR);
        }
        
        return anno;
    }

    public String getNumOficio() {
        if(sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_CONSULTAR_TRASLADO") || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_EDITAR_TRASLADO"))){
           this.numOficio = ((SiTraslado) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_TRASLADO")).getObservaciones();
        }
        return numOficio;
    }

    public void setNumOficio(String numOficio) {
        this.numOficio = numOficio;
    }

    public String getObservaciones() {
        if(sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_CONSULTAR_TRASLADO") || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_EDITAR_TRASLADO"))){
           this.observaciones = ((SiTraslado) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_TRASLADO")).getObservaciones();
        }
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        if(sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_CONSULTAR_TRASLADO") || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_EDITAR_TRASLADO"))){
           this.estado = ((SiTraslado) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_TRASLADO")).getEstado();
        }
        else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_INSERTAR_TRASLADO"))){
            this.estado = "NO ANULADO";
        }
        return estado;
    }

    public Date getFechaTraslado() {
        if(sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_CONSULTAR_TRASLADO") || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_EDITAR_TRASLADO"))){
           this.fechaTraslado = ((SiTraslado) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_TRASLADO")).getFechaTraslado();
        }
        return fechaTraslado;
    }

    public void setFechaTraslado(Date fechaTraslado) {
        this.fechaTraslado = fechaTraslado;
    }
    
    
    public void insertarTraslado() {
        try {
            ModeloTraslado traslado = new ModeloTraslado();
            traslado.insertarTraslado(this.numero, this.anno, this.numOficio, this.observaciones, this.siPersona.getCedula(), this.fechaTraslado, this.estado, this.siUnidadAcademica.getCodigo(), this.listasBienesInstitucionales.getTarget());
            this.mostrarFrmTraslados();
        } catch (ExcepcionTraslado ex) {
            Logger.getLogger(ControladorTraslado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarFrmTraslados() {
        try {
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO", "BIEN_TRASLADO");
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_TRASLADO", "Traslados");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema_Inventario/contenido/Traslado/frmTraslados.do");
        } catch (IOException ex) {
            Logger.getLogger(ControladorAdministrativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarFrmInsertarTraslado() {
        try {
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO", "BIEN_INSERTAR_TRASLADO");
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_TRASLADO", "Agregar Traslado");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema_Inventario/contenido/Traslado/frmMantenimientoIMECTraslados.do");
        } catch (IOException ex) {
            Logger.getLogger(ControladorAdministrativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public String getTituloPagina() {
        String tituloPagina = "";
        if (sesionHttp.getAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_TRASLADO") != null) {
            tituloPagina = (String) sesionHttp.getAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_TRASLADO");
        }
        return tituloPagina;
    }
    
    public List<SiTraslado> getListaTraslados() throws ExcepcionTraslado {
        List<SiTraslado> listaTraslados = null;
        try {
            ModeloTraslado traslado = new ModeloTraslado();
            listaTraslados = traslado.getListaTraslados();
        } catch (ExcepcionTraslado ex) {
            Logger.getLogger(ControladorTraslado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaTraslados;
    }
    
    public int getUltimoNumTraslado() {
        try {
            ModeloTraslado traslado = new ModeloTraslado();
            this.numero = traslado.getUltimoNum();
            this.getListaTraslados();
        } catch (ExcepcionTraslado ex) {
            Logger.getLogger(ControladorTraslado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (this.numero);
    }
    
    public String getTipoTraslado() {
        String tituloPagina = "";
        if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null) {
            tituloPagina = (String) sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO");
        }
        return tituloPagina;
    }
    
    public void setMostrarEditarTraslado(SiTraslado traslado) throws IOException {
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_TRASLADO", "Editar Traslado");
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO", "BIEN_EDITAR_TRASLADO");
            sesionHttp.setAttribute("MOD_BIEN_OBJETO_IMEC_TRASLADO", traslado);
            this.getListasBienesInstitucionales();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema_Inventario/contenido/Traslado/frmMantenimientoIMECTraslados.do");
    }

    public void setMostrarConsultarTraslado(SiTraslado traslado) throws IOException {
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_TRASLADO", "Consultar Traslado");
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO", "BIEN_CONSULTAR_TRASLADO");
            sesionHttp.setAttribute("MOD_BIEN_OBJETO_IMEC_TRASLADO", traslado);
            this.getListasBienesInstitucionales();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema_Inventario/contenido/Traslado/frmMantenimientoIMECTraslados.do");
    }
}
