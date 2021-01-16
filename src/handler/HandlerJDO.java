package handler;

import java.util.List;
import javafx.scene.control.Alert;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Libro;

public class HandlerJDO {

    //Ruta para guardar los datos JDO en el paquete model
    private final String rutaBiblioteca = "./src/model/biblioteca.odb";

    EntityManagerFactory entityMF;
    EntityManager entityM;

    /**
     * Método para consultar la lista de libros
     *
     * @return List<Libro> cargada
     */
    public List<Libro> consultarBiblioteca() {

        openEntity();

        //Creo un TypeQuery para crear una consulta y consultar todos los libros
        TypedQuery<Libro> consulta = (TypedQuery<Libro>) entityM.createQuery("SELECT libro FROM Libro libro");

        //Almaceno los resultados en un List
        List<Libro> listaLibros = consulta.getResultList();

        //Cierro el entity
        closeEntity();

        return listaLibros;
    }

    /**
     * Método para consultar un solo libro
     *
     * @param id int del id del libro a buscar
     * @return Libro buscado (null si no se encontró)
     */
    public Libro consultarLibro(int id) {

        openEntity();
        //Creo un TypeQuery para crear una consulta y consultar todos los libros
        TypedQuery<Libro> consulta = (TypedQuery<Libro>) entityM.createQuery("SELECT libro FROM Libro libro");
        List<Libro> libros = consulta.getResultList();

        Libro libroEncontrado = null;
        for (Libro l : libros) {
            //Busco el libro que me interesa a través del número del TextField
            if (l.getId() == id) {
                libroEncontrado = l;
            }
        }
        //Cierro los Entity para que no permanezcan abiertos
        closeEntity();

        //Compruebo si se encontró el libro, de lo contrario muestro un alert
        if (libroEncontrado == null) {
            creacionAlert(Alert.AlertType.INFORMATION, "Error al buscar el libro", "El libro que intentas buscar no está almacenado.");

            return null;
        } else {
            return libroEncontrado;
        }
    }

    /**
     * Método para eliminar un libro a través del id
     *
     * @param id
     */
    public void eliminarLibro(int id) {
        openEntity();

        TypedQuery<Libro> consulta = (TypedQuery<Libro>) entityM.createQuery("SELECT libro FROM Libro libro");
        List<Libro> libros = consulta.getResultList();

        //inicio una transacción
        entityM.getTransaction().begin();

        for (Libro l : libros) {
            //Busco el libro que me interesa a través del número del TextField
            if (l.getId() == id) {
                entityM.remove(l);
            }
        }

        try {
            //hago los cambios persistentes
            entityM.getTransaction().commit();
        } catch (Exception e) {
            creacionAlert(Alert.AlertType.ERROR, "Error al elimnar.", "El libro no ha podido ser elimnado.");
            entityM.getTransaction().rollback();
        }
        //cierro el Entity
        closeEntity();
    }

    /**
     * Método para modificar un libro
     *
     * @param id del libro a modificar
     * @param nombre String nombre
     * @param autor String autor
     * @param genero String genero
     * @param comentario String comentario
     * @param leido boolean Leido
     * @param puntuacion double puntuación
     */
    public void modificarLibro(int id, String nombre, String autor, String genero, String comentario, boolean leido, String puntuacion) {
        if (isEmpty(puntuacion)) {
            puntuacion = "0.0";
        }
        openEntity();

        TypedQuery<Libro> consulta = (TypedQuery<Libro>) entityM.createQuery("SELECT libro FROM Libro libro");
        List<Libro> libros = consulta.getResultList();

        for (Libro l : libros) {
            //Busco el libro que me interesa a través del número del TextField
            if (l.getId() == id) {

                if (comprobaciones(puntuacion, nombre)) {
                    //Ahora realizo la modificación
                    entityM.getTransaction().begin();

                    l.setAutor(autor);
                    l.setNombre(nombre);
                    l.setGenero(genero);
                    l.setComentarios(comentario);
                    l.setLeido(leido);
                    l.setPuntuacion(Double.parseDouble(puntuacion));

                    try {

                        //hago los cambios persistentes
                        entityM.getTransaction().commit();

                        String setContentText = "Datos: \n"
                                + "Nombre:\t\t" + l.getNombre() + "\n"
                                + "Autor:\t\t" + l.getAutor() + "\n"
                                + "Genero:\t\t" + l.getGenero() + "\n"
                                + "Comentarios:\t\t" + l.getComentarios() + "\n"
                                + "Leído:\t\t" + l.isLeido() + "\n"
                                + "Puntuación:\t\t" + l.getPuntuacion();

                        creacionAlert(Alert.AlertType.INFORMATION, "Modificado", "Libro " + l.getNombre() + " modificado con éxito.", setContentText);
                    } catch (Exception e) {
                        creacionAlert(Alert.AlertType.WARNING, "Error al modificar el libro", "No se ha podido modificar el libro.");
                        entityM.getTransaction().rollback();
                    }
                }
            }
        }

        //cierro el Entity
        closeEntity();
    }

    /**
     * Método para almacenar un nuevo libro
     *
     * @param nombre String nombre
     * @param autor String autor
     * @param genero String genero
     * @param comentario String comentario
     * @param leido boolean Leido
     * @param puntuacion double puntuación
     */
    public void almacenarLibro(String nombre, String autor, String genero, String comentario, boolean leido, String puntuacion) {
        if (isEmpty(puntuacion)) {
            puntuacion = "0.0";
        }

        //Inicio el Entity
        openEntity();
        if (comprobaciones(puntuacion, nombre)) {
            //Inicio una transacción
            entityM.getTransaction().begin();
            //almaceno datos de muestra
            entityM.persist(new Libro(nombre, autor, genero, comentario, leido, Double.parseDouble(puntuacion)));

            //Hago persistentes los datos
            try {
                entityM.getTransaction().commit();

                String setContentText = "Datos: \n"
                        + "Nombre:\t\t" + nombre + "\n"
                        + "Autor:\t\t" + autor + "\n"
                        + "Genero:\t\t" + genero + "\n"
                        + "Comentarios:\t\t" + comentario + "\n"
                        + "Leído:\t\t" + leido + "\n"
                        + "Puntuación:\t\t" + puntuacion;

                creacionAlert(Alert.AlertType.INFORMATION, "Libro agregado", "Libro " + nombre + " agregado con éxito.", setContentText);
            } catch (Exception e) {
                creacionAlert(Alert.AlertType.ERROR, "Error al elimnar.", "El libro no ha podido ser elimnado.");
                entityM.getTransaction().rollback();
            }
        }
        //Cierro el entity
        closeEntity();

    }

    /**
     * Método para almacenar un nuevo libro de forma simple
     *
     * @param nombre String nombre
     * @param autor String autor
     * @param genero String genero
     * @param comentario String comentario
     * @param leido boolean Leido
     * @param puntuacion double puntuación
     */
    public void almacenarLibroSimple(String nombre, String autor, String genero, String comentario, boolean leido, String puntuacion) {

        //Inicio el Entity
        openEntity();

        //Inicio una transacción
        entityM.getTransaction().begin();
        //almaceno datos de muestra
        entityM.persist(new Libro(nombre, autor, genero, comentario, leido, Double.parseDouble(puntuacion)));
        //Hago persistentes los datos
        entityM.getTransaction().commit();

        closeEntity();
    }

    /**
     * Método para instanciar el EntitityManagerFactory y el EntitityManager
     */
    private void openEntity() {
        entityMF = Persistence.createEntityManagerFactory(rutaBiblioteca);
        entityM = entityMF.createEntityManager();
    }

    /**
     * Método para cerrar el EntityManagerFactory y EntityManager
     */
    private void closeEntity() {
        entityM.close();
        entityMF.close();
    }

    /**
     * Método para comprobar los campos puntuación y nombre
     *
     * @param puntuacion
     * @param nombre
     * @return
     */
    private boolean comprobaciones(String puntuacion, String nombre) {
        boolean correcto = false;
        //Compruebo los datos de la puntuación y el nombre, si todo está bien almaceno el libro

        if (!isNumeric(puntuacion)) {
            creacionAlert(Alert.AlertType.ERROR, "Error con la puntuación", "La puntuación tiene quer de tipo entero o double.");
            correcto = false;

        } else if (Double.parseDouble(puntuacion) < 0 || Double.parseDouble(puntuacion) > 10) {
            creacionAlert(Alert.AlertType.ERROR, "Error con la puntuación", "La puntuación tiene que estar entre 0 y 10");
            correcto = false;
        } else if (isEmpty(nombre)) {
            creacionAlert(Alert.AlertType.ERROR, "Error con el nombre", "El Nombre del libro no puede estar en blanco.");
            correcto = false;
        } else {
            correcto = true;
        }

        return correcto;
    }

    /**
     * Método para mostrar un Alert
     *
     * @param tipo
     * @param titulo
     * @param header
     */
    private void creacionAlert(Alert.AlertType tipo, String titulo, String header) {
        Alert a = new Alert(tipo);
        a.setTitle(titulo);
        a.setHeaderText(header);
        a.show();
    }

    /**
     * Método sobrecargado para mostrar un alert con un Content.
     *
     * @param tipo
     * @param titulo
     * @param header
     * @param content
     */
    private void creacionAlert(Alert.AlertType tipo, String titulo, String header, String content) {
        Alert a = new Alert(tipo);
        a.setTitle(titulo);
        a.setHeaderText(header);
        a.setContentText(content);
        a.show();
    }

    /**
     * Comprobar si un campo está vacio
     *
     * @param str
     * @return
     */
    private boolean isEmpty(String str) {
        return str.equals("");
    }

    /**
     * Comprobar si un String es número de tipo double
     *
     * @param puntuacion
     * @return
     */
    private boolean isNumeric(String puntuacion) {
        try {
            Double.parseDouble(puntuacion);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
