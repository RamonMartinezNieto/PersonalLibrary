package controller;

import handler.HandlerJDO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

// TODO - Añadir Libros
public class AnadirLibroController implements Initializable {

    HandlerJDO jdo = new HandlerJDO();

    @FXML
    ComponenteModificarController componenteModificarController;

    /**
     * @param event
     */
    @FXML
    public void agregarLibro(ActionEvent event) {
        //Cojo todos los daotos

        String nombre = componenteModificarController.getTxtNombre();
        String autor = componenteModificarController.getTxtAutor();
        String comentarios = componenteModificarController.getTxtComentarios();
        boolean leido = (componenteModificarController.getSiRadio()) ? true : false;
        String genero = componenteModificarController.getTxtGenero();
        String puntuacion = componenteModificarController.getTxtPuntuacion();

        jdo.almacenarLibro(nombre, autor, genero, comentarios, leido, puntuacion);
    

}

    @Override
        public void initialize(URL url, ResourceBundle rb) {

    }

}
