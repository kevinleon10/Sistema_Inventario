/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.modelo;

import cr.ac.ucr.inie.sigepro.bd.HibernateUtil;
import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionUnidadAcademica;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiUnidadAcademica;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;


/**
 *
 * @author Jorge Remón
 */
public class ModeloUnidadAcademica {
     public List<SiUnidadAcademica> getListaUnidadesAcademicasQueTrasladan() throws ExcepcionUnidadAcademica {
        List<SiUnidadAcademica> unidadAcademicaList;
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();

        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT * FROM SI_UNIDAD_ACADEMICA WHERE CODIGO != 101").addEntity(SiUnidadAcademica.class);
            unidadAcademicaList = q.list();

        } catch (HibernateException ex) {
            throw new ExcepcionUnidadAcademica("Error al obtener el listado de Unidades Academicas: " + ex.getCause().getLocalizedMessage());

        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return unidadAcademicaList;
    }
}
