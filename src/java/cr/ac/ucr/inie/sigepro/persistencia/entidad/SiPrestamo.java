package cr.ac.ucr.inie.sigepro.persistencia.entidad;
// Generated 21/06/2017 02:16:07 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SiPrestamo generated by hbm2java
 */
@Entity
@Table(name="SI_PRESTAMO"
    ,schema="dbo"
    ,catalog="DB_GRUPO4"
)
public class SiPrestamo  implements java.io.Serializable {


     private SiPrestamoId id;
     private SiPersona siPersona;
     private Date fechaInicio;
     private Date fecharFinal;
     private byte[] reporte;
     private String estado;
     private String enCondicionDe;
     private Set<SiSePresta> siSePrestas = new HashSet<SiSePresta>(0);

    public SiPrestamo() {
    }

	
    public SiPrestamo(SiPrestamoId id, SiPersona siPersona, Date fechaInicio, Date fecharFinal, String estado, String enCondicionDe) {
        this.id = id;
        this.siPersona = siPersona;
        this.fechaInicio = fechaInicio;
        this.fecharFinal = fecharFinal;
        this.estado = estado;
        this.enCondicionDe = enCondicionDe;
    }
    public SiPrestamo(SiPrestamoId id, SiPersona siPersona, Date fechaInicio, Date fecharFinal, byte[] reporte, String estado, String enCondicionDe, Set<SiSePresta> siSePrestas) {
       this.id = id;
       this.siPersona = siPersona;
       this.fechaInicio = fechaInicio;
       this.fecharFinal = fecharFinal;
       this.reporte = reporte;
       this.estado = estado;
       this.enCondicionDe = enCondicionDe;
       this.siSePrestas = siSePrestas;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="numero", column=@Column(name="NUMERO", nullable=false) ), 
        @AttributeOverride(name="anno", column=@Column(name="ANNO", nullable=false) ) } )
    public SiPrestamoId getId() {
        return this.id;
    }
    
    public void setId(SiPrestamoId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CEDULA", nullable=false)
    public SiPersona getSiPersona() {
        return this.siPersona;
    }
    
    public void setSiPersona(SiPersona siPersona) {
        this.siPersona = siPersona;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="FECHA_INICIO", nullable=false, length=10)
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="FECHAR_FINAL", nullable=false, length=10)
    public Date getFecharFinal() {
        return this.fecharFinal;
    }
    
    public void setFecharFinal(Date fecharFinal) {
        this.fecharFinal = fecharFinal;
    }

    
    @Column(name="REPORTE")
    public byte[] getReporte() {
        return this.reporte;
    }
    
    public void setReporte(byte[] reporte) {
        this.reporte = reporte;
    }

    
    @Column(name="ESTADO", nullable=false, length=15)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    @Column(name="EN_CONDICION_DE", nullable=false, length=15)
    public String getEnCondicionDe() {
        return this.enCondicionDe;
    }
    
    public void setEnCondicionDe(String enCondicionDe) {
        this.enCondicionDe = enCondicionDe;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="siPrestamo")
    public Set<SiSePresta> getSiSePrestas() {
        return this.siSePrestas;
    }
    
    public void setSiSePrestas(Set<SiSePresta> siSePrestas) {
        this.siSePrestas = siSePrestas;
    }




}


