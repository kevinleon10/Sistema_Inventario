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
 * SiFundacionUcr generated by hbm2java
 */
@Entity
@Table(name="SI_FUNDACION_UCR"
    ,schema="dbo"
    ,catalog="DB_GRUPO4"
)
public class SiFundacionUcr  implements java.io.Serializable {


     private int id;
     private SiAdquisicion siAdquisicion;
     private String empresa;
     private String numeroFactura;

    public SiFundacionUcr() {
    }

    public SiFundacionUcr(SiAdquisicion siAdquisicion, String empresa, String numeroFactura) {
       this.siAdquisicion = siAdquisicion;
       this.empresa = empresa;
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

    
    @Column(name="NUMERO_FACTURA", nullable=false, length=50)
    public String getNumeroFactura() {
        return this.numeroFactura;
    }
    
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }




}


