/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.controlador;

import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiAdquisicion;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionAdquisicion;
import cr.ac.ucr.inie.sigepro.modelo.ModeloAdquisicion;
import cr.ac.ucr.inie.sigepro.modelo.ModeloBienInstitucional;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiAdquisicionPorTraslado;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiBienInstitucional;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiFundacionUcr;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiOficinaSuministros;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiPresupuestoOrdinario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Jorge Remón
 */
@ManagedBean
@ViewScoped
public class ControladorAdquisicion implements Serializable {

    private final HttpSession sesionHttp;
    private int id;
    private Date fechaAdquisicion;
    private SiPresupuestoOrdinario siPresupuestoOrdinario;
    private SiAdquisicionPorTraslado siAdquisicionPorTraslado;
    private SiOficinaSuministros siOficinaSuministros;
    private SiFundacionUcr siFundacionUcr;

    private DualListModel<SiBienInstitucional> listasBienesInstitucionales;

    /**
     * Creates a new instance of controladorAdquisicion
     */
    public ControladorAdquisicion() {
        sesionHttp = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    @PostConstruct
    public void init() {
        if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_INGRESAR_FUNDACION")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_INGRESAR_PRESUPUESTO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_INGRESAR_SUMINISTROS")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_INGRESAR_TRASLADO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_PRESUPUESTO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_SUMINISTROS")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_FUNDACION")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_TRASLADO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_PRESUPUESTO_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_SUMINISTROS_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_FUNDACION_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_TRASLADO_ESPECIFICA"))) {
            try {
                ModeloBienInstitucional modeloBienInstitucional = new ModeloBienInstitucional();
                List<SiBienInstitucional> listaBienesSinAdquisicion = modeloBienInstitucional.getListaBienesInstitucionalesSinAdquisicion();
                List<SiBienInstitucional> listaBienesSeleccionados = new ArrayList<SiBienInstitucional>();
                this.listasBienesInstitucionales = new DualListModel<SiBienInstitucional>(listaBienesSinAdquisicion, listaBienesSeleccionados);
            } catch (ExcepcionAdquisicion ex) {
                Logger.getLogger(ControladorAdquisicion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public DualListModel<SiBienInstitucional> getListasBienesInstitucionales() {
        if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_INGRESAR_FUNDACION")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_INGRESAR_PRESUPUESTO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_INGRESAR_SUMINISTROS")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_INGRESAR_TRASLADO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_PRESUPUESTO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_SUMINISTROS")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_FUNDACION")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_TRASLADO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_PRESUPUESTO_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_SUMINISTROS_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_FUNDACION_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_TRASLADO_ESPECIFICA"))) {
            if (listasBienesInstitucionales == null) {
                listasBienesInstitucionales = new DualListModel<>();
            }
            ModeloBienInstitucional modeloBienInstitucional = new ModeloBienInstitucional();
            try {
                List<SiBienInstitucional> listaBienesSinAdquisicion = modeloBienInstitucional.getListaBienesInstitucionalesSinAdquisicion();
                List<SiBienInstitucional> listaBienesSeleccionados = new ArrayList<>();
                if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_PRESUPUESTO")
                        || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_SUMINISTROS")
                        || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_FUNDACION")
                        || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_TRASLADO")
                        || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_PRESUPUESTO_ESPECIFICA")
                        || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_SUMINISTROS_ESPECIFICA")
                        || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_FUNDACION_ESPECIFICA")
                        || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_TRASLADO_ESPECIFICA"))) {
                    listaBienesSeleccionados = this.setListaBienesPertenecientesAUnaAdquisicion(((SiAdquisicion) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_ADQUISICION")).getId());
                }
                for (int i = listaBienesSinAdquisicion.size() - 1; i >= 0; i--) {
                    for (int j = listaBienesSeleccionados.size() - 1; j >= 0; j--) {
                        if (listaBienesSeleccionados.get(j).getConsecutivo() == listaBienesSinAdquisicion.get(i).getConsecutivo()) {
                            listaBienesSinAdquisicion.remove(i);
                            break;
                        }
                    }
                }
                listasBienesInstitucionales = new DualListModel<SiBienInstitucional>(listaBienesSinAdquisicion, listaBienesSeleccionados);
            } catch (ExcepcionAdquisicion ex) {
                Logger.getLogger(ControladorAdquisicion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listasBienesInstitucionales;
    }

    public void setListasBienesInstitucionales(DualListModel<SiBienInstitucional> listasBienesInstitucionales) {
        this.listasBienesInstitucionales = listasBienesInstitucionales;
    }

    public List<SiBienInstitucional> setListaBienesPertenecientesAUnaAdquisicion(int idAdquisicion) throws ExcepcionAdquisicion {
        List<SiBienInstitucional> bienesAdquiridos = null;
        if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_INGRESAR_FUNDACION")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_INGRESAR_PRESUPUESTO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_INGRESAR_SUMINISTROS")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_INGRESAR_TRASLADO"))) {
            bienesAdquiridos = null;
        } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_PRESUPUESTO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_SUMINISTROS")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_FUNDACION")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_TRASLADO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_PRESUPUESTO_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_SUMINISTROS_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_FUNDACION_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_TRASLADO_ESPECIFICA"))) {
            ModeloBienInstitucional modeloBienInstitucional = new ModeloBienInstitucional();
            bienesAdquiridos = modeloBienInstitucional.getNumeroBienesInstitucionalesConAdquisicion(idAdquisicion);
        }

        return bienesAdquiridos;
    }

    public int getId() {
        if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_PRESUPUESTO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_SUMINISTROS")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_FUNDACION")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_TRASLADO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_PRESUPUESTO_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_SUMINISTROS_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_FUNDACION_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_TRASLADO_ESPECIFICA"))) {

            id = ((SiAdquisicion) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_ADQUISICION")).getId();
        } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_INGRESAR_FUNDACION")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_INGRESAR_PRESUPUESTO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_INGRESAR_SUMINISTROS")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_INGRESAR_TRASLADO"))) {
            try {
                ModeloAdquisicion adquisicion = new ModeloAdquisicion();
                this.id = adquisicion.getUltimoId() + 1;
            } catch (ExcepcionAdquisicion ex) {
                Logger.getLogger(ControladorAdquisicion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaAdquisicion() {
        if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_PRESUPUESTO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_SUMINISTROS")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_FUNDACION")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_TRASLADO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_PRESUPUESTO_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_SUMINISTROS_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_FUNDACION_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_TRASLADO_ESPECIFICA"))) {

            fechaAdquisicion = ((SiAdquisicion) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_ADQUISICION")).getFechaAdquisicion();
        }
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public SiPresupuestoOrdinario getSiPresupuestoOrdinario() {
        if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_PRESUPUESTO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_SUMINISTROS")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_FUNDACION")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_TRASLADO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_PRESUPUESTO_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_SUMINISTROS_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_FUNDACION_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_TRASLADO_ESPECIFICA"))) {

            siPresupuestoOrdinario = ((SiAdquisicion) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_ADQUISICION")).getSiPresupuestoOrdinario();
        } else {
            siPresupuestoOrdinario = new SiPresupuestoOrdinario();
        }
        return siPresupuestoOrdinario;
    }

    public void setSiPresupuestoOrdinario(SiPresupuestoOrdinario siPresupuestoOrdinario) {
        this.siPresupuestoOrdinario = siPresupuestoOrdinario;
    }

    public SiAdquisicionPorTraslado getSiAdquisicionPorTraslado() {
        if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_PRESUPUESTO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_SUMINISTROS")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_FUNDACION")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_TRASLADO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_PRESUPUESTO_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_SUMINISTROS_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_FUNDACION_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_TRASLADO_ESPECIFICA"))) {

            siAdquisicionPorTraslado = ((SiAdquisicion) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_ADQUISICION")).getSiAdquisicionPorTraslado();
        } else {
            siAdquisicionPorTraslado = new SiAdquisicionPorTraslado();
        }
        return siAdquisicionPorTraslado;
    }

    public void setSiAdquisicionPorTraslado(SiAdquisicionPorTraslado siAdquisicionPorTraslado) {
        this.siAdquisicionPorTraslado = siAdquisicionPorTraslado;
    }

    public SiOficinaSuministros getSiOficinaSuministros() {
        if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_PRESUPUESTO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_SUMINISTROS")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_FUNDACION")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_TRASLADO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_PRESUPUESTO_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_SUMINISTROS_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_FUNDACION_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_TRASLADO_ESPECIFICA"))) {

            siOficinaSuministros = ((SiAdquisicion) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_ADQUISICION")).getSiOficinaSuministros();
        } else {
            siOficinaSuministros = new SiOficinaSuministros();
        }
        return siOficinaSuministros;
    }

    public void setSiOficinaSuministros(SiOficinaSuministros siOficinaSuministros) {
        this.siOficinaSuministros = siOficinaSuministros;
    }

    public SiFundacionUcr getSiFundacionUcr() {
        if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") != null && (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_PRESUPUESTO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_SUMINISTROS")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_FUNDACION")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_EDITAR_TRASLADO")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_PRESUPUESTO_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_SUMINISTROS_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_FUNDACION_ESPECIFICA")
                || sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION").equals("BIEN_ADQUISICION_CONSULTAR_TRASLADO_ESPECIFICA"))) {

            siFundacionUcr = ((SiAdquisicion) sesionHttp.getAttribute("MOD_BIEN_OBJETO_IMEC_ADQUISICION")).getSiFundacionUcr();
        } else {
            siFundacionUcr = new SiFundacionUcr();
        }
        return siFundacionUcr;
    }

    public void setSiFundacionUcr(SiFundacionUcr siFundacionUcr) {
        this.siFundacionUcr = siFundacionUcr;
    }

    public void mostrarFrmAdquisicionesTraslado() {
        try {
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION", "BIEN_ADQUISICION_CONSULTAR_TRASLADO");
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION", "Adquisiciones por Traslado");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema_Inventario/contenido/Adquisicion/frmAdquisiciones.do");
        } catch (IOException ex) {
            Logger.getLogger(ControladorAdministrativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarFrmAdquisicionesSuministros() {
        try {
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION", "BIEN_ADQUISICION_CONSULTAR_SUMINISTROS");
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION", "Adquisiciones por Oficina de Suministros");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema_Inventario/contenido/Adquisicion/frmAdquisiciones.do");
        } catch (IOException ex) {
            Logger.getLogger(ControladorAdministrativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarFrmAdquisicionesPresupuesto() {
        try {
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION", "BIEN_ADQUISICION_CONSULTAR_PRESUPUESTO");
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION", "Adquisiciones por Presupuesto Ordinario");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema_Inventario/contenido/Adquisicion/frmAdquisiciones.do");
        } catch (IOException ex) {
            Logger.getLogger(ControladorAdministrativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarFrmAdquisicionesFundacion() {
        try {
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION", "BIEN_ADQUISICION_CONSULTAR_FUNDACION");
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION", "Adquisiciones por Fundación UCR");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema_Inventario/contenido/Adquisicion/frmAdquisiciones.do");
        } catch (IOException ex) {
            Logger.getLogger(ControladorAdministrativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarFrmInsertarAdquisicion() {
        try {
            if (this.getNumeroBienesSinAdquisicion() > 0) {
                if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_CONSULTAR_FUNDACION") {
                    sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION", "Nueva Adquisición por Fundación UCR");
                    sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION", "BIEN_ADQUISICION_INGRESAR_FUNDACION");

                } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_CONSULTAR_PRESUPUESTO") {
                    sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION", "Nueva Adquisición por Presupuesto Ordinario");
                    sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION", "BIEN_ADQUISICION_INGRESAR_PRESUPUESTO");

                } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_CONSULTAR_SUMINISTROS") {
                    sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION", "Nueva Adquisición por Oficina de Suministros");
                    sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION", "BIEN_ADQUISICION_INGRESAR_SUMINISTROS");

                } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_CONSULTAR_TRASLADO") {
                    sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION", "Nueva Adquisición por Traslado");
                    sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION", "BIEN_ADQUISICION_INGRESAR_TRASLADO");
                }

                FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema_Inventario/contenido/Adquisicion/frmMantenimientoIMECAdquisiciones.do");

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se necesitan Bienes Institucionales", "Todos los bienes se encuentran enlazados a alguna adquisición."));
            }
        } catch (IOException ex) {
            Logger.getLogger(ControladorAdministrativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getTituloPagina() {
        String tituloPagina = "";
        if (sesionHttp.getAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION") != null) {
            tituloPagina = (String) sesionHttp.getAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION");
        }
        return tituloPagina;
    }

    public String getTipoAdquisicion() {
        String tituloPagina = "";
        if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") != null) {
            tituloPagina = (String) sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION");
        }
        return tituloPagina;
    }

    public List<SiAdquisicion> getListaAdquisiciones() throws ExcepcionAdquisicion {
        List<SiAdquisicion> listaAdquisiciones = null;

        try {
            if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_CONSULTAR_FUNDACION") {
                ModeloAdquisicion adquisicion = new ModeloAdquisicion();
                listaAdquisiciones = adquisicion.getListaAdquisicionesFundacion();
            } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_CONSULTAR_PRESUPUESTO") {
                ModeloAdquisicion adquisicion = new ModeloAdquisicion();
                listaAdquisiciones = adquisicion.getListaAdquisicionesPresupuesto();
            } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_CONSULTAR_SUMINISTROS") {
                ModeloAdquisicion adquisicion = new ModeloAdquisicion();
                listaAdquisiciones = adquisicion.getListaAdquisicionesSuministro();
            } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_CONSULTAR_TRASLADO") {
                ModeloAdquisicion adquisicion = new ModeloAdquisicion();
                listaAdquisiciones = adquisicion.getListaAdquisicionesTraslado();
            }
        } catch (ExcepcionAdquisicion ex) {
            Logger.getLogger(ControladorAdquisicion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaAdquisiciones;
    }

    public void insertarAdquisicion() {
        try {
            ModeloAdquisicion adquisicion = new ModeloAdquisicion();
            if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_INGRESAR_FUNDACION") {
                adquisicion.insertarAdquisicionFundacionUcr(this.id, this.getFechaAdquisicion(), this.siFundacionUcr.getEmpresa(), this.siFundacionUcr.getNumeroFactura(), this.listasBienesInstitucionales.getTarget());
                this.mostrarFrmAdquisicionesFundacion();
            } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_INGRESAR_PRESUPUESTO") {
                adquisicion.insertarAdquisicionPresupuestoOrdinario(this.id, this.getFechaAdquisicion(), this.siPresupuestoOrdinario.getNumeroProyecto(), this.siPresupuestoOrdinario.getNumeroFactura(), this.siPresupuestoOrdinario.getEmpresa(), this.listasBienesInstitucionales.getTarget());
                this.mostrarFrmAdquisicionesPresupuesto();
            } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_INGRESAR_SUMINISTROS") {
                adquisicion.insertarAdquisicionOficinaSuministros(this.id, this.getFechaAdquisicion(), this.siOficinaSuministros.getEmpresa(), this.siOficinaSuministros.getOrdenCompra(), this.siOficinaSuministros.getNumeroContratacion(), this.listasBienesInstitucionales.getTarget());
                this.mostrarFrmAdquisicionesSuministros();
            } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_INGRESAR_TRASLADO") {
                adquisicion.insertarAdquisicionTraslado(this.id, this.getFechaAdquisicion(), this.siAdquisicionPorTraslado.getNumeroOficio(), this.siAdquisicionPorTraslado.getSiUnidadAcademica().getCodigo(), this.siAdquisicionPorTraslado.getSiPersona().getCedula(), this.listasBienesInstitucionales.getTarget());
                this.mostrarFrmAdquisicionesTraslado();
            } 
        } catch (ExcepcionAdquisicion ex) {
            Logger.getLogger(ControladorAdquisicion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getNumeroBienesSinAdquisicion() {
        int num = 0;
        try {
            ModeloBienInstitucional bien = new ModeloBienInstitucional();
            num = bien.getNumeroBienesInstitucionalesSinAdquisicion();
        } catch (ExcepcionAdquisicion ex) {
            Logger.getLogger(ControladorAdquisicion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num;
    }

    public void setMostrarEditarAdquisicion(SiAdquisicion adquisicion) throws IOException {
        if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_CONSULTAR_FUNDACION") {
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION", "Editar Adquisición por Fundación");
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION", "BIEN_ADQUISICION_EDITAR_FUNDACION");
            sesionHttp.setAttribute("MOD_BIEN_OBJETO_IMEC_ADQUISICION", adquisicion);
            this.getListasBienesInstitucionales();
        } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_CONSULTAR_PRESUPUESTO") {
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION", "Editar Adquisición por Presupuesto");
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION", "BIEN_ADQUISICION_EDITAR_PRESUPUESTO");
            sesionHttp.setAttribute("MOD_BIEN_OBJETO_IMEC_ADQUISICION", adquisicion);
            this.getListasBienesInstitucionales();
        } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_CONSULTAR_SUMINISTROS") {
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION", "Editar Adquisición por Suministros");
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION", "BIEN_ADQUISICION_EDITAR_SUMINISTROS");
            sesionHttp.setAttribute("MOD_BIEN_OBJETO_IMEC_ADQUISICION", adquisicion);
            this.getListasBienesInstitucionales();
        } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_CONSULTAR_TRASLADO") {
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION", "Editar Adquisición por Traslado");
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION", "BIEN_ADQUISICION_EDITAR_TRASLADO");
            sesionHttp.setAttribute("MOD_BIEN_OBJETO_IMEC_ADQUISICION", adquisicion);
            this.getListasBienesInstitucionales();
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema_Inventario/contenido/Adquisicion/frmMantenimientoIMECAdquisiciones.do");
    }

    public void setMostrarConsultarAdquisicion(SiAdquisicion adquisicion) throws IOException {
        if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_CONSULTAR_FUNDACION") {
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION", "Consultar Adquisición");
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION", "BIEN_ADQUISICION_CONSULTAR_FUNDACION_ESPECIFICA");
            sesionHttp.setAttribute("MOD_BIEN_OBJETO_IMEC_ADQUISICION", adquisicion);
            this.getListasBienesInstitucionales();
        } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_CONSULTAR_PRESUPUESTO") {
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION", "Consultar Adquisición");
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION", "BIEN_ADQUISICION_CONSULTAR_PRESUPUESTO_ESPECIFICA");
            sesionHttp.setAttribute("MOD_BIEN_OBJETO_IMEC_ADQUISICION", adquisicion);
            this.getListasBienesInstitucionales();
        } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_CONSULTAR_SUMINISTROS") {
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION", "Consultar Adquisición");
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION", "BIEN_ADQUISICION_CONSULTAR_SUMINISTROS_ESPECIFICA");
            sesionHttp.setAttribute("MOD_BIEN_OBJETO_IMEC_ADQUISICION", adquisicion);
            this.getListasBienesInstitucionales();
        } else if (sesionHttp.getAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION") == "BIEN_ADQUISICION_CONSULTAR_TRASLADO") {
            sesionHttp.setAttribute("MOD_BIEN_TITULO_MANTENIMIENTO_ADQUISICION", "Consultar Adquisición");
            sesionHttp.setAttribute("MOD_BIEN_ACCION_IMEC_ADQUISICION", "BIEN_ADQUISICION_CONSULTAR_TRASLADO_ESPECIFICA");
            sesionHttp.setAttribute("MOD_BIEN_OBJETO_IMEC_ADQUISICION", adquisicion);
            this.getListasBienesInstitucionales();
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Sistema_Inventario/contenido/Adquisicion/frmMantenimientoIMECAdquisiciones.do");
    }

}
