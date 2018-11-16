/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.modelo;

import cr.ac.ucr.inie.sigepro.bd.HibernateUtil;
import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionPersona;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiPersona;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Jorge Remón
 */
public class ModeloPersona {
    
    public List<SiPersona> getListaPersonasFueraDelINIE() throws ExcepcionPersona {
        List<SiPersona> personas;
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();

        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT * FROM SI_PERSONA WHERE FUNCIONARIO_INIE != 1").addEntity(SiPersona.class);
            personas = q.list();

        } catch (HibernateException ex) {
            throw new ExcepcionPersona("Error al obtener el listado de Personas ajenas al INIE: " + ex.getCause().getLocalizedMessage());

        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return personas;
    }
    
    
     public List<SiPersona> getListaPersonasDentroDelINIE() throws ExcepcionPersona {
        List<SiPersona> personas;
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();

        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT * FROM SI_PERSONA WHERE FUNCIONARIO_INIE != 0").addEntity(SiPersona.class);
            personas = q.list();

        } catch (HibernateException ex) {
            throw new ExcepcionPersona("Error al obtener el listado de Personas dentro del INIE: " + ex.getCause().getLocalizedMessage());

        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return personas;
    }
     
     public List<SiPersona> getListaPersonas() throws ExcepcionPersona {
        List<SiPersona> personas;
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();

        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT * FROM SI_PERSONA").addEntity(SiPersona.class);
            personas = q.list();

        } catch (HibernateException ex) {
            throw new ExcepcionPersona("Error al obtener el listado de Personas: " + ex.getCause().getLocalizedMessage());

        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return personas;
    }
     
    
}
