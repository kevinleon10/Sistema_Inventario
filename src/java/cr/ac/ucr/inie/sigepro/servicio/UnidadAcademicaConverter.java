package cr.ac.ucr.inie.sigepro.servicio;

import cr.ac.ucr.inie.sigepro.controlador.ControladorUnidadAcademica;
import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionUnidadAcademica;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiUnidadAcademica;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Jorge Remón
 */
@FacesConverter("unidadAcademicaConverter")
public class UnidadAcademicaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Object unidadToReturn = null;
        if (value != null && value.trim().length() > 0) {
            try {
                ControladorUnidadAcademica controladorUnidad = new ControladorUnidadAcademica();
                List<SiUnidadAcademica> unidad;
                unidad = controladorUnidad.getListaUnidadesAcademicasQueTrasladan();
                boolean encontrada = false;
                int i = 0;
                while (encontrada == false && i < unidad.size()) {
                    if(unidad.get(i).getCodigo().equals(value)){
                    encontrada = true;
                    unidadToReturn = unidad.get(i);
                    }else{
                    i++;
                    }
                }
            } catch (ExcepcionUnidadAcademica ex) {
                Logger.getLogger(UnidadAcademicaConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            unidadToReturn = null;
        }
        return unidadToReturn;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return String.valueOf(((SiUnidadAcademica) object).getCodigo());
        } else {
            return null;
        }
    }

}
