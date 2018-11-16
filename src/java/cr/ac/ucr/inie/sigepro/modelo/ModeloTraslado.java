/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.modelo;

import cr.ac.ucr.inie.sigepro.bd.HibernateUtil;
import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionTraslado;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiBienInstitucional;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiTraslado;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiUnidadAcademica;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author lskev
 */
public class ModeloTraslado {
    public ModeloTraslado(){}
    
    public List<SiTraslado> getListaTraslados() throws ExcepcionTraslado {
        List<SiTraslado> trasladoList;
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();

        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT * FROM SI_TRASLADO").addEntity(SiTraslado.class);
            trasladoList = q.list();

        } catch (HibernateException ex) {
            throw new ExcepcionTraslado("Error al obtener el listado de Traslados: " + ex.getCause().getLocalizedMessage());

        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return trasladoList;
    }
    
  public void insertarTraslado(int num, int anno, String numeroOficio, String observaciones, String cedula, Date fechaTraslado, String estado, String codUnidad, List<SiBienInstitucional> bienes) throws ExcepcionTraslado {
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();
        try {
            int nuevoNum = num+1;
            Transaction tx = null;
            tx = sesionHibernate.beginTransaction();
            Query q = sesionHibernate.createSQLQuery("INSERT INTO SI_TRASLADO (NUMERO, ANNO, NUMERO_OFICIO, REPORTE, OBSERVACIONES, CEDULA, FECHA_TRASLADO, ESTADO) VALUES (:numTraslado, :annoTraslado, :numOficio, :reporte, :observaciones, :cedula, :fechaTraslado, :estado)");
            q.setParameter("numTraslado", nuevoNum);
            q.setParameter("annoTraslado", anno);
            q.setParameter("numOficio", numeroOficio);
            q.setParameter("reporte", null);
            q.setParameter("observaciones", observaciones);
            q.setParameter("cedula", cedula);
            q.setParameter("fechaTraslado", ((Date) fechaTraslado));
            q.setParameter("estado", estado);
            q.executeUpdate();
            tx.commit();

            for (int i = bienes.size() - 1; i >= 0; i--) {
                tx = null;
                tx = sesionHibernate.beginTransaction();
                q = sesionHibernate.createSQLQuery("INSERT INTO SI_SE_TRASLADA VALUES (:numTraslado, :annoTraslado, :codigoUnidad, :consecutivoBien)");
                q.setParameter("numTraslado", nuevoNum);
                q.setParameter("annoTraslado", anno);
                q.setParameter("codigoUnidad", codUnidad);
                q.setParameter("consecutivoBien", bienes.get(i).getConsecutivo());
                q.executeUpdate();
                tx.commit();
                
                tx = null;
                tx = sesionHibernate.beginTransaction();
                q = sesionHibernate.createSQLQuery("UPDATE SI_BIEN_INSTITUCIONAL SET PROCESO='TRASLADO' WHERE CONSECUTIVO=:consecutivoBien");
                q.setParameter("consecutivoBien", bienes.get(i).getConsecutivo());
                q.executeUpdate();
                tx.commit();
                bienes.remove(i);
            }
        } catch (HibernateException ex) {
            throw new ExcepcionTraslado("Error al agregar el traslado: " + ex.getCause().getLocalizedMessage());
        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
    }
  
    public int getUltimoNum() throws ExcepcionTraslado {
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();
        int num = 0;
        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT COUNT(*) FROM SI_TRASLADO");
            if ((int) q.uniqueResult() != 0) {
                q = sesionHibernate.createSQLQuery("SELECT MAX(NUMERO) FROM SI_TRASLADO");
                num = (int) q.uniqueResult();
            } else {
                num = 0;
            }
        } catch (HibernateException ex) {
            try {
                throw new ExcepcionTraslado("Error al obtener el último num: " + ex.getCause().getLocalizedMessage());
            } catch (ExcepcionTraslado ex1) {
                Logger.getLogger(ModeloTraslado.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return num;
    }
    
    public SiUnidadAcademica getUnidadAcademicaTraslado(SiTraslado siTraslado) throws ExcepcionTraslado{
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();
        SiUnidadAcademica siUnidadAcademica = new SiUnidadAcademica();
        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT U.CODIGO, U.NOMBRE  \n" +
                                                        "FROM SI_TRASLADO T JOIN SI_SE_TRASLADA S ON T.NUMERO = S.NUMERO AND T.ANNO = S.ANNO\n" +
                                                        "JOIN SI_UNIDAD_ACADEMICA U ON U.CODIGO = S.CODIGO" +
                                                        "WHERE T.NUMERO = :num AND T.ANNO = :anno").addEntity(SiUnidadAcademica.class);
            q.setParameter("num", siTraslado.getId().getNumero());
            q.setParameter("anno", siTraslado.getId().getAnno());
            siUnidadAcademica = (SiUnidadAcademica) q.uniqueResult();
        } catch (HibernateException ex) {
            try {
                throw new ExcepcionTraslado("Error al obtener la UnidadAcademica: " + ex.getCause().getLocalizedMessage());
            } catch (ExcepcionTraslado ex1) {
                Logger.getLogger(ModeloTraslado.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return siUnidadAcademica;
    }
}
