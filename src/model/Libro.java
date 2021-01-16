package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Clase Libro tratado por el EntityManager
 *
 * @author RMN
 */
@Entity
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;

    //id generado automáticamente y único 
    @Id
    @GeneratedValue
    private int id;

    String nombre, autor, genero, comentarios;
    boolean leido; // Si o no 
    
    /** Este dato lo uso para el ObservableList, con ello puedo establecer luego las
    * columnas con el dato "si" o "no" ya que no puedo setear un método e specificio 
    a la columna*/
    boolean leido2;
    
    double puntuacion;  // 0 - 10

    /**
     * Constructor completo
     *
     * @param nombre
     * @param autor
     * @param genero
     * @param comentarios
     * @param leido
     * @param puntuacion
     */
    public Libro(String nombre, String autor, String genero, String comentarios, boolean leido, double puntuacion) {
        this.nombre = nombre;
        this.autor = autor;
        this.genero = genero;
        this.comentarios = comentarios;
        this.leido = leido;
        this.puntuacion = puntuacion;
        
        this.leido2 = leido; 
    }
    
    /**
     * Constructor vacío
     */
    public Libro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String isLeido() {
        String leido = "No";
        
        if(this.leido){
            leido = "Sí";
        }
        
        return leido;
    }
    
    public String isLeido2() {
        String leido = "No";
        
        if(this.leido){
            leido = "Sí";
        }
        
        return leido;
    }
    
    public boolean getLeido(){
        return this.leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
        this.leido2 = leido;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }
}