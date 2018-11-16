package cr.ac.ucr.inie.sigepro.persistencia.entidad;
// Generated 21/06/2017 02:16:07 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * SiBienInstitucional generated by hbm2java
 */
@Entity
@Table(name="SI_BIEN_INSTITUCIONAL"
    ,schema="dbo"
    ,catalog="DB_GRUPO4"
    , uniqueConstraints = @UniqueConstraint(columnNames="PLACA") 
)
public class SiBienInstitucional  implements java.io.Serializable {


     private int consecutivo;
     private SiPersona siPersona;
     private SiUnidadAcademica siUnidadAcademica;
     private String marca;
     private String modelo;
     private String serie;
     private String condicionEstado;
     private String descripcionEstado;
     private String ubicacionFisica;
     private String placa;
     private String proceso;
     private String observaciones;
     private boolean equipoDeComputo;
     private String descripcion;
     private Set<SiAdquisicion> siAdquisicions = new HashSet<SiAdquisicion>(0);
     private Set<SiSePresta> siSePrestas = new HashSet<SiSePresta>(0);
     private Set<SiSolicitudDesecho> siSolicitudDesechos = new HashSet<SiSolicitudDesecho>(0);
     private Set<SiSeTraslada> siSeTrasladas = new HashSet<SiSeTraslada>(0);

    public SiBienInstitucional() {
    }

	
    public SiBienInstitucional(int consecutivo, String proceso, boolean equipoDeComputo, String descripcion) {
        this.consecutivo = consecutivo;
        this.proceso = proceso;
        this.equipoDeComputo = equipoDeComputo;
        this.descripcion = descripcion;
    }
    public SiBienInstitucional(int consecutivo, SiPersona siPersona, SiUnidadAcademica siUnidadAcademica, String marca, String modelo, String serie, String condicionEstado, String descripcionEstado, String ubicacionFisica, String placa, String proceso, String observaciones, boolean equipoDeComputo, String descripcion, Set<SiAdquisicion> siAdquisicions, Set<SiSePresta> siSePrestas, Set<SiSolicitudDesecho> siSolicitudDesechos, Set<SiSeTraslada> siSeTrasladas) {
       this.consecutivo = consecutivo;
       this.siPersona = siPersona;
       this.siUnidadAcademica = siUnidadAcademica;
       this.marca = marca;
       this.modelo = modelo;
       this.serie = serie;
       this.condicionEstado = condicionEstado;
       this.descripcionEstado = descripcionEstado;
       this.ubicacionFisica = ubicacionFisica;
       this.placa = placa;
       this.proceso = proceso;
       this.observaciones = observaciones;
       this.equipoDeComputo = equipoDeComputo;
       this.descripcion = descripcion;
       this.siAdquisicions = siAdquisicions;
       this.siSePrestas = siSePrestas;
       this.siSolicitudDesechos = siSolicitudDesechos;
       this.siSeTrasladas = siSeTrasladas;
    }
   
     @Id 

    
    @Column(name="CONSECUTIVO", unique=true, nullable=false)
    public int getConsecutivo() {
        return this.consecutivo;
    }
    
    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CEDULA")
    public SiPersona getSiPersona() {
        return this.siPersona;
    }
    
    public void setSiPersona(SiPersona siPersona) {
        this.siPersona = siPersona;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CODIGO")
    public SiUnidadAcademica getSiUnidadAcademica() {
        return this.siUnidadAcademica;
    }
    
    public void setSiUnidadAcademica(SiUnidadAcademica siUnidadAcademica) {
        this.siUnidadAcademica = siUnidadAcademica;
    }

    
    @Column(name="MARCA", length=20)
    public String getMarca() {
        return this.marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }

    
    @Column(name="MODELO", length=20)
    public String getModelo() {
        return this.modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    
    @Column(name="SERIE", length=30)
    public String getSerie() {
        return this.serie;
    }
    
    public void setSerie(String serie) {
        this.serie = serie;
    }

    
    @Column(name="CONDICION_ESTADO", length=20)
    public String getCondicionEstado() {
        return this.condicionEstado;
    }
    
    public void setCondicionEstado(String condicionEstado) {
        this.condicionEstado = condicionEstado;
    }

    
    @Column(name="DESCRIPCION_ESTADO", length=300)
    public String getDescripcionEstado() {
        return this.descripcionEstado;
    }
    
    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    
    @Column(name="UBICACION_FISICA", length=300)
    public String getUbicacionFisica() {
        return this.ubicacionFisica;
    }
    
    public void setUbicacionFisica(String ubicacionFisica) {
        this.ubicacionFisica = ubicacionFisica;
    }

    
    @Column(name="PLACA", unique=true, length=7)
    public String getPlaca() {
        return this.placa;
    }
    
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    
    @Column(name="PROCESO", nullable=false, length=15)
    public String getProceso() {
        return this.proceso;
    }
    
    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    
    @Column(name="OBSERVACIONES", length=300)
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    
    @Column(name="EQUIPO_DE_COMPUTO", nullable=false)
    public boolean isEquipoDeComputo() {
        return this.equipoDeComputo;
    }
    
    public void setEquipoDeComputo(boolean equipoDeComputo) {
        this.equipoDeComputo = equipoDeComputo;
    }

    
    @Column(name="DESCRIPCION", nullable=false, length=300)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="SI_SE_ADQUIERE", schema="dbo", catalog="DB_GRUPO4", joinColumns = { 
        @JoinColumn(name="CONSECUTIVO", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="ID", nullable=false, updatable=false) })
    public Set<SiAdquisicion> getSiAdquisicions() {
        return this.siAdquisicions;
    }
    
    public void setSiAdquisicions(Set<SiAdquisicion> siAdquisicions) {
        this.siAdquisicions = siAdquisicions;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="siBienInstitucional")
    public Set<SiSePresta> getSiSePrestas() {
        return this.siSePrestas;
    }
    
    public void setSiSePrestas(Set<SiSePresta> siSePrestas) {
        this.siSePrestas = siSePrestas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="siBienInstitucional")
    public Set<SiSolicitudDesecho> getSiSolicitudDesechos() {
        return this.siSolicitudDesechos;
    }
    
    public void setSiSolicitudDesechos(Set<SiSolicitudDesecho> siSolicitudDesechos) {
        this.siSolicitudDesechos = siSolicitudDesechos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="siBienInstitucional")
    public Set<SiSeTraslada> getSiSeTrasladas() {
        return this.siSeTrasladas;
    }
    
    public void setSiSeTrasladas(Set<SiSeTraslada> siSeTrasladas) {
        this.siSeTrasladas = siSeTrasladas;
    }




}


