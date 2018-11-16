/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.controlador;

import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiPersona;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiPrestamoId;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiPrestamo;
import cr.ac.ucr.inie.sigepro.modelo.ModeloPrestamo;
import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionPrestamo;
import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionBienInstitucional;
import cr.ac.ucr.inie.sigepro.modelo.ModeloBienInstitucional;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiBienInstitucional;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiPrestamo;
import java.io.IOException;
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
 * @author Daniel
 */
@ManagedBean
@ViewScoped
public class ControladorPrestamo {

    /**
     * Creates a new instance of ControladorPrestamo
     */
    private final HttpSession sesionHttp;

    private int anno;
    private int num;
    private SiPersona siPersona;
    private Date fechaInicio;
    private Date fecharFinal;
    private byte[] reporte;
    private String estado;
    private String enCondicionDe;
    private DualListModel<SiBienInstitucional> listasBienesInstitucionales;
    
    public ControladorPrestamo() {
        sesionHttp = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
    
    @PostConstruct
    public void init() {
        if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_PRESTAMO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_PRESTAMO").equals("BIEN_PRESTAMO"))) {
            ModeloBienInstitucional modeloBienInstitucional = new ModeloBienInstitucional();
            try {
                List<SiBienInstitucional> listaBienesActivos = modeloBienInstitucional.getListaBienesInstitucionalesActivos();
                List<SiBienInstitucional> listaBienesSeleccionados = new ArrayList<SiBienInstitucional>();
                this.listasBienesInstitucionales = new DualListModel<SiBienInstitucional>(listaBienesActivos, listaBienesSeleccionados);

            } catch (ExcepcionBienInstitucional ex) {
                Logger.getLogger(ControladorPrestamo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public DualListModel<SiBienInstitucional> getListasBienesInstitucionales() {
        if(sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_INSERTAR_TRASLADO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_CONSULTAR_TRASLADO") || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_EDITAR_TRASLADO"))) {
            if (listasBienesInstitucionales == null) {
                listasBienesInstitucionales = new DualListModel<SiBienInstitucional>();
            }
            ModeloBienInstitucional modeloBienInstitucional = new ModeloBienInstitucional();
            try {
                List<SiBienInstitucional> listaBienesActivos = modeloBienInstitucional.getListaBienesInstitucionalesActivos();
                List<SiBienInstitucional> listaBienesSeleccionados = new ArrayList<SiBienInstitucional>();
                /*if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_EDITAR_TRASLADO")
                        || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_TRASLADO").equals("BIEN_CONSULTAR_TRASLADO"))) {
                    listaBienesSeleccionados = this.setListaBienesPertenecientesAUnTraslado(((SiTraslado) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_TRASLADO")).getId().getNumero(), ((SiTraslado) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_TRASLADO")).getId().getAnno());
                }*/
                for (int i = listaBienesActivos.size() - 1; i >= 0; i--) {
                    for (int j = listaBienesSeleccionados.size() - 1; j >= 0; j--) {
                        if (listaBienesSeleccionados.get(j).getConsecutivo() == listaBienesActivos.get(i).getConsecutivo()) {
                        } else {
                            listaBienesActivos.remove(i);
                        }
                    }
                }
                listasBienesInstitucionales = new DualListModel<SiBienInstitucional>(listaBienesActivos, listaBienesSeleccionados);
            } catch (ExcepcionBienInstitucional ex) {
                Logger.getLogger(ControladorTraslado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listasBienesInstitucionales;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    public int getAnno() {
        this.anno = Calendar.getInstance().get(Calendar.YEAR);
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public SiPersona getSiPersona() {
        return siPersona;
    }

    public void setSiPersona(SiPersona siPersona) {
        this.siPersona = siPersona;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFecharFinal() {
        return fecharFinal;
    }

    public void setFecharFinal(Date fecharFinal) {
        this.fecharFinal = fecharFinal;
    }

    public byte[] getReporte() {
        return reporte;
    }

    public void setReporte(byte[] reporte) {
        this.reporte = reporte;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEnCondicionDe() {
        return enCondicionDe;
    }

    public void setEnCondicionDe(String enCondicionDe) {
        this.enCondicionDe = enCondicionDe;
    }
    
    public void mostrarFrmPrestamo() {
        try {
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_PRESTAMO", "BIEN_PRESTAMO");
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_PRESTAMO", "Prestamos");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema_Inventario/contenido/Prestamo/frmPrestamo.do");
        } catch (IOException ex) {
            Logger.getLogger(ControladorPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setMostrarFrmMantenimientoAgregarPrestamo() {
        try {
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_PRESTAMO", "BIEN_PRESTAMO");
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_PRESTAMO", "Agregar Prestamo");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema_Inventario/contenido/Prestamo/frmMantenimientoAgregarPrestamo.do");
        } catch (IOException ex) {
            Logger.getLogger(ControladorPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<SiPrestamo> getListaPrestamos() {
        List<SiPrestamo> listaPrestamos = null;

        try {
            ModeloPrestamo Modelo_Prestamo = new ModeloPrestamo();
            listaPrestamos = Modelo_Prestamo.getListaPrestamo();
        } catch (ExcepcionPrestamo ex) {
            Logger.getLogger(ControladorPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPrestamos;
    }
    
    public void setNuevoPrestamo() {      
        try {
            ModeloPrestamo Modelo_Prestamo = new ModeloPrestamo();
            Modelo_Prestamo.agregaNuevoPrestamo(getUltimoNumPrestamo(), this.anno, this.siPersona.getCedula(), this.fechaInicio, this.fecharFinal, this.estado, this.enCondicionDe);
            this.setMostrarFrmMantenimientoAgregarPrestamo();
        } catch (ExcepcionPrestamo ex) {
            Logger.getLogger(ControladorPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getUltimoNumPrestamo() {
        try {
            ModeloPrestamo traslado = new ModeloPrestamo();
            this.num = traslado.getUltimoNum();
            this.getListaPrestamos();
        } catch (ExcepcionPrestamo ex) {
            Logger.getLogger(ControladorTraslado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (this.num+1);
    }
    
}

