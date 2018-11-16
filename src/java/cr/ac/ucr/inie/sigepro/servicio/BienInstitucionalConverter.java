/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.servicio;

import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiBienInstitucional;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jorge Remón
 */
@FacesConverter("bienInstitucionalConverter")
public class BienInstitucionalConverter implements Converter {

    private static final Logger LOG = LoggerFactory.getLogger(BienInstitucionalConverter.class);

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LOG.trace("String value: {}", value);

        return getObjectFromUIPickListComponent(component, value);
    }

    /**
     *
     * @param context
     * @param component
     * @param object
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        String string;
        LOG.trace("Object value: {}", object);
        if (object == null) {
            string = "";
        } else {
            try {
                string = String.valueOf(((SiBienInstitucional) object).getConsecutivo());
            } catch (ClassCastException cce) {
                throw new ConverterException();
            }
        }
        return string;
    }

    @SuppressWarnings("unchecked")
    private SiBienInstitucional getObjectFromUIPickListComponent(UIComponent component, String value) {
        final DualListModel<SiBienInstitucional> dualList;
        try {
            dualList = (DualListModel<SiBienInstitucional>) ((PickList) component).getValue();
            SiBienInstitucional bienInstitucional = getObjectFromList(dualList.getSource(), Integer.valueOf(value));
            if (bienInstitucional == null) {
                bienInstitucional = getObjectFromList(dualList.getTarget(), Integer.valueOf(value));
            }

            return bienInstitucional;
        } catch (ClassCastException cce) {
            throw new ConverterException();
        } catch (NumberFormatException nfe) {
            throw new ConverterException();
        }
    }

    private SiBienInstitucional getObjectFromList(final List<?> list, final Integer identifier) {
        for (final Object object : list) {
            final SiBienInstitucional bienInstitucional = (SiBienInstitucional) object;
            if (Integer.valueOf(bienInstitucional.getConsecutivo()).equals(identifier)) {
                return bienInstitucional;
            }
        }
        return null;
    }
}