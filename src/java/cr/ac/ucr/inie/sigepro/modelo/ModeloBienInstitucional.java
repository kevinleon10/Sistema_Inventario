/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.modelo;

import cr.ac.ucr.inie.sigepro.bd.HibernateUtil;
import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionAdquisicion;
import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionBienInstitucional;
import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionTraslado;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiBienInstitucional;
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
 * @author Jorge Remón
 */
public class ModeloBienInstitucional {
    
    
    public List<SiBienInstitucional> getListaBienesInstitucionalesSinAdquisicion() throws ExcepcionAdquisicion {
        List<SiBienInstitucional> adquisicionList;
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();

        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT *  \n" +
                                                        "FROM SI_BIEN_INSTITUCIONAL B \n" +
                                                        "WHERE \n" +
                                                        "NOT EXISTS \n" +
                                                            "(SELECT A.CONSECUTIVO \n" +
                                                             "FROM SI_SE_ADQUIERE A \n" +
                                                             "WHERE A.CONSECUTIVO = B.CONSECUTIVO )").addEntity(SiBienInstitucional.class);
            adquisicionList = q.list();

        } catch (HibernateException ex) {
            throw new ExcepcionAdquisicion("Error al obtener el listado de Aquisiciones por Traslado: " + ex.getCause().getLocalizedMessage());

        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return adquisicionList;
    }
    
    public int getNumeroBienesInstitucionalesSinAdquisicion() throws ExcepcionAdquisicion {
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();
        int num = 0;
        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT Count(*)  \n" +
                                                        "FROM SI_BIEN_INSTITUCIONAL B \n" +
                                                        "WHERE \n" +
                                                        "NOT EXISTS \n" +
                                                            "(SELECT A.CONSECUTIVO \n" +
                                                             "FROM SI_SE_ADQUIERE A \n" +
                                                             "WHERE A.CONSECUTIVO = B.CONSECUTIVO )");
            num = (int) q.uniqueResult();

        } catch (HibernateException ex) {
            throw new ExcepcionAdquisicion("Error al obtener el numero de Aquisiciones por Traslado: " + ex.getCause().getLocalizedMessage());

        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return num;
    }
    
     public List<SiBienInstitucional> getListaBienesInstitucionalesActivos() throws ExcepcionTraslado {
        List<SiBienInstitucional> activosList;
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();

        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT *  \n" +
                                                        "FROM SI_BIEN_INSTITUCIONAL \n" +
                                                        "WHERE PROCESO= 'ACTIVO'").addEntity(SiBienInstitucional.class);
            activosList = q.list();

        } catch (HibernateException ex) {
            throw new ExcepcionTraslado("Error al obtener el listado de Bienes activos: " + ex.getCause().getLocalizedMessage());

        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return activosList;
    }
    
    
     public List<SiBienInstitucional> getNumeroBienesInstitucionalesConAdquisicion(int idAdquisicion) throws ExcepcionAdquisicion {
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();
        List<SiBienInstitucional> bienesAdquiridos;
        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT *  \n" +
                                                        "FROM SI_BIEN_INSTITUCIONAL B \n" +
                                                        "WHERE \n" +
                                                        "EXISTS \n" +
                                                            "(SELECT A.CONSECUTIVO \n" +
                                                             "FROM SI_SE_ADQUIERE A \n" +
                                                             "WHERE A.CONSECUTIVO = B.CONSECUTIVO AND A.ID = :idAdquisicion)").addEntity(SiBienInstitucional.class);
            q.setParameter("idAdquisicion", (idAdquisicion));
            bienesAdquiridos = q.list();

        } catch (HibernateException ex) {
            throw new ExcepcionAdquisicion("Error al obtener el numero de Aquisiciones por Traslado: " + ex.getCause().getLocalizedMessage());

        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return bienesAdquiridos;
    }
     
     public List<SiBienInstitucional> getBienesInstitucionalesConTraslado(int num, int anno) throws ExcepcionTraslado {
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();
        List<SiBienInstitucional> bienesTrasladados;
        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT B.CONSECUTIVO, B.MARCA, B.MODELO, B.SERIE, B.CONDICION_ESTADO, B.DESCRIPCION_ESTADO, "+
                                                        "B.UBICACION_FISICA, B.PLACA, B.PROCESO, B.OBSERVACIONES, B.EQUIPO_DE_COMPUTO, B.CODIGO, B.CEDULA, B.DESCRIPCION  \n" +
                                                        "FROM SI_BIEN_INSTITUCIONAL B JOIN SI_SE_TRASLADA S ON B.CONSECUTIVO = S.CONSECUTIVO\n" +
                                                        "JOIN SI_TRASLADO T ON T.NUMERO = S.NUMERO AND T.ANNO = S.ANNO" +
                                                        "WHERE T.NUMERO = :num AND T.ANNO = :anno").addEntity(SiBienInstitucional.class);
            q.setParameter("num", num);
            q.setParameter("anno", anno);
            bienesTrasladados = q.list();

        } catch (HibernateException ex) {
            throw new ExcepcionTraslado("Error al obtener los Bienes en el Traslado: " + ex.getCause().getLocalizedMessage());

        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return bienesTrasladados;
    }
    
     public List<SiBienInstitucional> getListaBienes () throws ExcepcionBienInstitucional {
        List<SiBienInstitucional> listaBienes;
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();
        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT * \n"+
                                                        "FROM  SI_BIEN_INSTITUCIONAL").addEntity(SiBienInstitucional.class);
            listaBienes = q.list();
        }
        catch (HibernateException ex) {
            throw new ExcepcionBienInstitucional("Error al obtener el listado de Bienes Institucionales: " + ex.getCause().getLocalizedMessage());
        }
        finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return listaBienes;
    }
    
    public void insertarBienInstitucional (int consecutivo, String marca, String modelo, String serie, String cond_Estado, String ubicacion, String placa, String observaciones, boolean equipo_Computo, String codigo, String cedula, String descripcion) throws ExcepcionBienInstitucional {
            Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();
            try {
            int nuevoConsecutivo = consecutivo+1;
            Transaction tx = null;
            tx = sesionHibernate.beginTransaction();
            Query q = sesionHibernate.createSQLQuery("INSERT INTO SI_BIEN_INSTITUCIONAL /n"+
                                                       "VALUES (:consecutivo, :marca, :modelo, :serie, :cond_Estado, :ubicacion, :placa, :observaciones, :equipo_Computo, :codigo, :cedula, :descripcion)");
            q.setParameter("consecutivo", nuevoConsecutivo);
            q.setParameter("marca", marca);
            q.setParameter("modelo", modelo);
            q.setParameter("serie", serie);
            q.setParameter("cond_Estado", cond_Estado);
            q.setParameter("ubicacion", ubicacion);
            q.setParameter("placa", placa);
            q.setParameter("observaciones", observaciones);
            q.setParameter("equipo_Computo", equipo_Computo);
            q.setParameter("codigo", codigo);
            q.setParameter("cedula", cedula);
            q.setParameter("descripcion", descripcion);
            q.executeUpdate();
            tx.commit();
            }
            catch (HibernateException ex) {
                throw new ExcepcionBienInstitucional("Error al agregar el bien institucional: " + ex.getCause().getLocalizedMessage());
            } 
            finally {
                if (sesionHibernate.isOpen()) {
                    sesionHibernate.close();
                }
            }        
    }
    public int getUltimoNum() throws ExcepcionBienInstitucional {
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();
        int num = 0;
        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT COUNT(*) FROM SI_BIEN_INSTITUCIONAL");
            if ((int) q.uniqueResult() != 0) {
                q = sesionHibernate.createSQLQuery("SELECT MAX(CONSECUTIVO) FROM SI_BIEN_INSTITUCIONAL");
                num = (int) q.uniqueResult();
            } else {
                num = 0;
            }
        } catch (HibernateException ex) {
            try {
                throw new ExcepcionBienInstitucional("Error al obtener el último num: " + ex.getCause().getLocalizedMessage());
            } catch (ExcepcionBienInstitucional ex1) {
                Logger.getLogger(ModeloBienInstitucional.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return num;
    }
    
}
