package cr.ac.ucr.inie.sigepro.persistencia.entidad;
// Generated 21/06/2017 02:16:07 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SiSePrestaId generated by hbm2java
 */
@Embeddable
public class SiSePrestaId  implements java.io.Serializable {


     private int numero;
     private int anno;
     private int consecutivo;

    public SiSePrestaId() {
    }

    public SiSePrestaId(int numero, int anno, int consecutivo) {
       this.numero = numero;
       this.anno = anno;
       this.consecutivo = consecutivo;
    }
   


    @Column(name="NUMERO", nullable=false)
    public int getNumero() {
        return this.numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }


    @Column(name="ANNO", nullable=false)
    public int getAnno() {
        return this.anno;
    }
    
    public void setAnno(int anno) {
        this.anno = anno;
    }


    @Column(name="CONSECUTIVO", nullable=false)
    public int getConsecutivo() {
        return this.consecutivo;
    }
    
    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SiSePrestaId) ) return false;
		 SiSePrestaId castOther = ( SiSePrestaId ) other; 
         
		 return (this.getNumero()==castOther.getNumero())
 && (this.getAnno()==castOther.getAnno())
 && (this.getConsecutivo()==castOther.getConsecutivo());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getNumero();
         result = 37 * result + this.getAnno();
         result = 37 * result + this.getConsecutivo();
         return result;
   }   


}


