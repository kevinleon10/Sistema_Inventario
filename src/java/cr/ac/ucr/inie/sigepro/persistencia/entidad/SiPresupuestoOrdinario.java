package cr.ac.ucr.inie.sigepro.persistencia.entidad;
// Generated 21/06/2017 02:16:07 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * SiPresupuestoOrdinario generated by hbm2java
 */
@Entity
@Table(name="SI_PRESUPUESTO_ORDINARIO"
    ,schema="dbo"
    ,catalog="DB_GRUPO4"
)
public class SiPresupuestoOrdinario  implements java.io.Serializable {


     private int id;
     private SiAdquisicion siAdquisicion;
     private String empresa;
     private String numeroProyecto;
     private String numeroFactura;

    public SiPresupuestoOrdinario() {
    }

    public SiPresupuestoOrdinario(SiAdquisicion siAdquisicion, String empresa, String numeroProyecto, String numeroFactura) {
       this.siAdquisicion = siAdquisicion;
       this.empresa = empresa;
       this.numeroProyecto = numeroProyecto;
       this.numeroFactura = numeroFactura;
    }
   
     @GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="siAdquisicion"))@Id @GeneratedValue(generator="generator")

    
    @Column(name="ID", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@OneToOne(fetch=FetchType.LAZY)@PrimaryKeyJoinColumn
    public SiAdquisicion getSiAdquisicion() {
        return this.siAdquisicion;
    }
    
    public void setSiAdquisicion(SiAdquisicion siAdquisicion) {
        this.siAdquisicion = siAdquisicion;
    }

    
    @Column(name="EMPRESA", nullable=false, length=100)
    public String getEmpresa() {
        return this.empresa;
    }
    
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    
    @Column(name="NUMERO_PROYECTO", nullable=false, length=50)
    public String getNumeroProyecto() {
        return this.numeroProyecto;
    }
    
    public void setNumeroProyecto(String numeroProyecto) {
        this.numeroProyecto = numeroProyecto;
    }

    
    @Column(name="NUMERO_FACTURA", nullable=false, length=50)
    public String getNumeroFactura() {
        return this.numeroFactura;
    }
    
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }




}


