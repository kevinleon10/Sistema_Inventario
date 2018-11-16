/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.inie.sigepro.modelo;

import cr.ac.ucr.inie.sigepro.bd.HibernateUtil;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiAdquisicion;
import cr.ac.ucr.inie.sigepro.excepcion.ExcepcionAdquisicion;
import cr.ac.ucr.inie.sigepro.persistencia.entidad.SiBienInstitucional;
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
 * @author Jorge Remón
 */
public class ModeloAdquisicion {

    public List<SiAdquisicion> getListaAdquisicionesTraslado() throws ExcepcionAdquisicion {
        List<SiAdquisicion> adquisicionList;
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();

        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT * FROM SI_ADQUISICION A JOIN SI_ADQUISICION_POR_TRASLADO T ON A.ID = T.ID").addEntity(SiAdquisicion.class);
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

    public List<SiAdquisicion> getListaAdquisicionesPresupuesto() throws ExcepcionAdquisicion {
        List<SiAdquisicion> adquisicionList;
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();

        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT * FROM SI_ADQUISICION A JOIN SI_PRESUPUESTO_ORDINARIO O ON A.ID = O.ID").addEntity(SiAdquisicion.class);
            adquisicionList = q.list();

        } catch (HibernateException ex) {
            throw new ExcepcionAdquisicion("Error al obtener el listado de Aquisiciones por Presupuesto Ordinario: " + ex.getCause().getLocalizedMessage());

        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return adquisicionList;
    }

    public List<SiAdquisicion> getListaAdquisicionesFundacion() throws ExcepcionAdquisicion {
        List<SiAdquisicion> adquisicionList;
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();

        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT * FROM SI_ADQUISICION A JOIN SI_FUNDACION_UCR F ON A.ID = F.ID").addEntity(SiAdquisicion.class);
            adquisicionList = q.list();

        } catch (HibernateException ex) {
            throw new ExcepcionAdquisicion("Error al obtener el listado de Aquisiciones por Fundación UCR: " + ex.getCause().getLocalizedMessage());

        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return adquisicionList;
    }

    public List<SiAdquisicion> getListaAdquisicionesSuministro() throws ExcepcionAdquisicion {
        List<SiAdquisicion> adquisicionList;
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();

        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT * FROM SI_ADQUISICION A JOIN SI_OFICINA_SUMINISTROS O ON A.ID = O.ID").addEntity(SiAdquisicion.class);
            adquisicionList = q.list();

        } catch (HibernateException ex) {
            throw new ExcepcionAdquisicion("Error al obtener el listado de Aquisiciones por Fundación UCR: " + ex.getCause().getLocalizedMessage());

        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return adquisicionList;
    }

    public void insertarAdquisicionPresupuestoOrdinario(int id, Date fecha, String proyecto, String factura, String empresa, List<SiBienInstitucional> bienes) throws ExcepcionAdquisicion {
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = null;
            tx = sesionHibernate.beginTransaction();
            Query q = sesionHibernate.createSQLQuery("INSERT INTO SI_ADQUISICION (ID, FECHA_ADQUISICION) VALUES (:idAdquisicion, :fechaAdquisicion)");
            q.setParameter("idAdquisicion", (id + 1));
            q.setParameter("fechaAdquisicion", fecha);
            q.executeUpdate();
            tx.commit();

            tx = null;
            tx = sesionHibernate.beginTransaction();
            q = sesionHibernate.createSQLQuery("INSERT INTO SI_PRESUPUESTO_ORDINARIO (ID, NUMERO_PROYECTO, NUMERO_FACTURA, EMPRESA) VALUES (:idAdquisicion, :proyectoAdquisicion, :facturaAdquisicion, :empresaAdquisicion)");
            q.setParameter("idAdquisicion", (id + 1));
            q.setParameter("proyectoAdquisicion", proyecto);
            q.setParameter("facturaAdquisicion", factura);
            q.setParameter("empresaAdquisicion", empresa);
            q.executeUpdate();
            tx.commit();

            for (int i = bienes.size() - 1; i >= 0; i--) {
                tx = null;
                tx = sesionHibernate.beginTransaction();
                q = sesionHibernate.createSQLQuery("INSERT INTO SI_SE_ADQUIERE VALUES (:consecutivoBien, :idAdquisicion)");
                q.setParameter("consecutivoBien", bienes.get(i).getConsecutivo());
                q.setParameter("idAdquisicion", (id + 1));
                q.executeUpdate();
                tx.commit();
                bienes.remove(i);
            }

        } catch (HibernateException ex) {
            throw new ExcepcionAdquisicion("Error al agregar la adquisición: " + ex.getCause().getLocalizedMessage());
        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
    }

    public void insertarAdquisicionTraslado(int id, Date fecha, String oficio, String codUnidad, String cedPersona,  List<SiBienInstitucional> bienes) throws ExcepcionAdquisicion {
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = null;
            tx = sesionHibernate.beginTransaction();
            Query q = sesionHibernate.createSQLQuery("INSERT INTO SI_ADQUISICION (ID, FECHA_ADQUISICION) VALUES (:idAdquisicion, :fechaAdquisicion)");
            q.setParameter("idAdquisicion", (id + 1));
            q.setParameter("fechaAdquisicion", fecha);
            q.executeUpdate();
            tx.commit();

            tx = null;
            tx = sesionHibernate.beginTransaction();
            q = sesionHibernate.createSQLQuery("INSERT INTO SI_ADQUISICION_POR_TRASLADO (ID, NUMERO_OFICIO, CODIGO, CEDULA) VALUES (:idAdquisicion, :numeroOficio, :codigoUnidad, :CedulaResponsable)");
            q.setParameter("idAdquisicion", (id + 1));
            q.setParameter("numeroOficio", oficio);
            q.setParameter("codigoUnidad", codUnidad);
            q.setParameter("CedulaResponsable", cedPersona);
            q.executeUpdate();
            tx.commit();

            for (int i = bienes.size() - 1; i >= 0; i--) {
                tx = null;
                tx = sesionHibernate.beginTransaction();
                q = sesionHibernate.createSQLQuery("INSERT INTO SI_SE_ADQUIERE VALUES (:consecutivoBien, :idAdquisicion)");
                q.setParameter("consecutivoBien", bienes.get(i).getConsecutivo());
                q.setParameter("idAdquisicion", (id + 1));
                q.executeUpdate();
                tx.commit();
                bienes.remove(i);
            }

        } catch (HibernateException ex) {
            throw new ExcepcionAdquisicion("Error al agregar la adquisición: " + ex.getCause().getLocalizedMessage());
        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
    }

    public void insertarAdquisicionFundacionUcr(int id, Date fecha, String empresa, String factura, List<SiBienInstitucional> bienes) throws ExcepcionAdquisicion {
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = null;
            tx = sesionHibernate.beginTransaction();
            Query q = sesionHibernate.createSQLQuery("INSERT INTO SI_ADQUISICION (ID, FECHA_ADQUISICION) VALUES (:idAdquisicion, :fechaAdquisicion)");
            q.setParameter("idAdquisicion", (id + 1));
            q.setParameter("fechaAdquisicion", fecha);
            q.executeUpdate();
            tx.commit();

            tx = null;
            tx = sesionHibernate.beginTransaction();
            q = sesionHibernate.createSQLQuery("INSERT INTO SI_FUNDACION_UCR (ID, EMPRESA, NUMERO_FACTURA) VALUES (:idAdquisicion, :empresaAdquisicion, :facturaAdquisicion)");
            q.setParameter("idAdquisicion", (id + 1));
            q.setParameter("empresaAdquisicion", empresa);
            q.setParameter("facturaAdquisicion", factura);
            q.executeUpdate();
            tx.commit();

            for (int i = bienes.size() - 1; i >= 0; i--) {
                tx = null;
                tx = sesionHibernate.beginTransaction();
                q = sesionHibernate.createSQLQuery("INSERT INTO SI_SE_ADQUIERE VALUES (:consecutivoBien, :idAdquisicion)");
                q.setParameter("consecutivoBien", bienes.get(i).getConsecutivo());
                q.setParameter("idAdquisicion", (id + 1));
                q.executeUpdate();
                tx.commit();
                bienes.remove(i);
            }

        } catch (HibernateException ex) {
            throw new ExcepcionAdquisicion("Error al agregar la adquisición: " + ex.getCause().getLocalizedMessage());
        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
    }

    public void insertarAdquisicionOficinaSuministros(int id, Date fecha, String empresa, String ordenCompra, String numeroContratacion, List<SiBienInstitucional> bienes) throws ExcepcionAdquisicion {
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = null;
            tx = sesionHibernate.beginTransaction();
            Query q = sesionHibernate.createSQLQuery("INSERT INTO SI_ADQUISICION (ID, FECHA_ADQUISICION) VALUES (:idAdquisicion, :fechaAdquisicion)");
            q.setParameter("idAdquisicion", (id + 1));
            q.setParameter("fechaAdquisicion", fecha);
            q.executeUpdate();
            tx.commit();

            tx = null;
            tx = sesionHibernate.beginTransaction();
            q = sesionHibernate.createSQLQuery("INSERT INTO SI_OFICINA_SUMINISTROS (ID, EMPRESA, ORDEN_COMPRA, NUMERO_CONTRATACION) VALUES (:idAdquisicion, :empresa, :ordenCompra, :numeroContratacion)");
            q.setParameter("idAdquisicion", (id + 1));
            q.setParameter("empresa", empresa);
            q.setParameter("ordenCompra", ordenCompra);
            q.setParameter("numeroContratacion", numeroContratacion);
            q.executeUpdate();
            tx.commit();

            for (int i = bienes.size() - 1; i >= 0; i--) {
                tx = null;
                tx = sesionHibernate.beginTransaction();
                q = sesionHibernate.createSQLQuery("INSERT INTO SI_SE_ADQUIERE VALUES (:consecutivoBien, :idAdquisicion)");
                q.setParameter("consecutivoBien", bienes.get(i).getConsecutivo());
                q.setParameter("idAdquisicion", (id + 1));
                q.executeUpdate();
                tx.commit();
                bienes.remove(i);
            }

        } catch (HibernateException ex) {
            throw new ExcepcionAdquisicion("Error al agregar la adquisición: " + ex.getCause().getLocalizedMessage());
        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
    }

    public int getUltimoId() throws ExcepcionAdquisicion {
        Session sesionHibernate = HibernateUtil.getSessionFactory().openSession();
        int id = 0;
        try {
            SQLQuery q = sesionHibernate.createSQLQuery("SELECT COUNT(*) FROM SI_ADQUISICION");
            if ((int) q.uniqueResult() != 0) {
                q = sesionHibernate.createSQLQuery("SELECT MAX(ID) FROM SI_ADQUISICION");
                id = (int) q.uniqueResult();
            } else {
                id = 0;
            }
        } catch (HibernateException ex) {
            try {
                throw new ExcepcionAdquisicion("Error al obtener el último id: " + ex.getCause().getLocalizedMessage());
            } catch (ExcepcionAdquisicion ex1) {
                Logger.getLogger(ModeloAdquisicion.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (sesionHibernate.isOpen()) {
                sesionHibernate.close();
            }
        }
        return id;
    }

}
