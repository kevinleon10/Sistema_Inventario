/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.servicio;

import cr.ac.ucr.inie.sigepro.controlador.ControladorPersona;
import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionPersona;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiPersona;
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
@FacesConverter("personaConverter")
public class PersonaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Object personaToReturn = null;
        if(value != null && value.trim().length() > 0) {
             try {
                ControladorPersona controladorPersona = new ControladorPersona();
                List<SiPersona> persona;
                persona = controladorPersona.getListaPersonas();
                boolean encontrada = false;
                int i = 0;
                while (encontrada == false && i < persona.size()) {
                    if(persona.get(i).getCedula().equals(value)){
                    encontrada = true;
                    personaToReturn = persona.get(i);
                    }else{
                    i++;
                    }
                }
            } catch (ExcepcionPersona ex) {
                Logger.getLogger(PersonaConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
            personaToReturn = null;
        }
        return personaToReturn;
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((SiPersona) object).getCedula());
        }
        else {
            return null;
        }
    }   
    
    
    
}

