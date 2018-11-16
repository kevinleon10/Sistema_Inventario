/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.modelo;

import cr.ac.ucr.inie.sigepro.bd.HibernateUtil;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiPrestamo;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiPrestamoId;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiPersona;
import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionPrestamo;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Daniel
 */
public class ModeloPrestamo {
    
    public List<SiPrestamo> getListaPrestamo() throws ExcepcionPrestamo {
        List<SiPrestamo> listaSiPrestamo;
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();

        try {
            Query q = sesionHibernate.createSQLQuery("SELECT * FROM SI_PRESTAMO")
                    .addEntity(SiPrestamo.class);

            listaSiPrestamo = (List<SiPrestamo>) q.list();
            
            if (listaSiPrestamo != null) {
                Iterator<SiPrestamo> it = listaSiPrestamo.iterator();
                while (it.hasNext()) {
                    SiPrestamo siPrestamo = it.next();
                }
            }

        } catch (HibernateException ex) {
            throw new ExcepcionPrestamo("Error al obtener el listado de prestamos: " + ex.getCause().getLocalizedMessage());

        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return listaSiPrestamo;
    }
    
    public void agregaNuevoPrestamo(int num, int anno, String cedula, Date fechaInic, Date fechaFin, String estado, String condicion) throws ExcepcionPrestamo {
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();

        try {   
            Transaction tx = null;
            tx = sesionHibernate.beginTransaction();
            Query q = sesionHibernate.createSQLQuery("INSERT INTO SiPrestamo (NUMERO, ANNO, CEDULA, FECHA_INICIO, FECHAR_FINAL, ESTADO, EN_CONDICION_DE) "
                                                    + "VALUES (:num, :anno, ced:, :fecha_inic, :fecha_fin, :estado, :en_cond_de)");
            q.setParameter("num", num);
            q.setParameter("anno", anno);
            q.setParameter("ced", cedula);
            q.setParameter("fecha_inic", fechaInic);
            q.setParameter("fecha_fin", fechaFin);
            q.setParameter("estado", estado);
            q.setParameter("en_cond_de", condicion);
            q.executeUpdate();
            tx.commit();
            
        } catch (HibernateException ex) {
            throw new ExcepcionPrestamo("Error al agregar un prestamo: " + ex.getCause().getLocalizedMessage());
     
        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
    }
    
    public int getUltimoNum() throws ExcepcionPrestamo {
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();
        int num = 0;
        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT COUNT(*) FROM SI_PPRESTAMO");
            if ((int) q.uniqueResult() != 0) {
                q = sesionHibernate.createSQLQuery("SELECT MAX(NUMERO) FROM SI_PPRESTAMO");
                num = (int) q.uniqueResult();
            } else {
                num = 0;
            }
        } catch (HibernateException ex) {
            try {
                throw new ExcepcionPrestamo("Error al obtener el último num: " + ex.getCause().getLocalizedMessage());
            } catch (ExcepcionPrestamo ex1) {
                Logger.getLogger(ModeloTraslado.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return num;
    }
}
