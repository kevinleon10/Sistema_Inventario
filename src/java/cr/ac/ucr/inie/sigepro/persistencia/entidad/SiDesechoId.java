package cr.ac.ucr.inie.sigepro.persistencia.entidad;
// Generated 21/06/2017 02:16:07 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SiDesechoId generated by hbm2java
 */
@Embeddable
public class SiDesechoId  implements java.io.Serializable {


     private int numero;
     private int anno;

    public SiDesechoId() {
    }

    public SiDesechoId(int numero, int anno) {
       this.numero = numero;
       this.anno = anno;
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


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SiDesechoId) ) return false;
		 SiDesechoId castOther = ( SiDesechoId ) other; 
         
		 return (this.getNumero()==castOther.getNumero())
 && (this.getAnno()==castOther.getAnno());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getNumero();
         result = 37 * result + this.getAnno();
         return result;
   }   


}


