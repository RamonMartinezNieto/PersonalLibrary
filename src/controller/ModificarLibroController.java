package controller;

import handler.HandlerJDO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ModificarLibroController implements Initializable {

    HandlerJDO jdo = new HandlerJDO();

    @FXML
    ComponenteModificarController componenteModificarController;

    @FXML
    ComponenteBuscarController componenteBuscarController;

    @FXML
    Button modificarLibro;

    /**
     * Método para el botón pulsar que buscará el libro a través del ID y
     * establecerá los datos en lso TextField
     *
     * @param event
     */
    @FXML
    public void buscar(ActionEvent event) {
        componenteModificarController.buscarLibro(componenteBuscarController.getFieldId());

        if (componenteModificarController.getTxtId().equals("")) {
            componenteModificarController.deshabilitarCampos();
            modificarLibro.setDisable(true);
        } else {
            componenteModificarController.habilitarCampos();
            modificarLibro.setDisable(false);
        }
    }

    /**
     * Método para modificar un libro, cogerá los vfalores introducidos y se los
     * pasará al HandlerJDO para que modifique el libro a través del método
     * modificarLibro()
     *
     * @param event
     */
    @FXML
    public void modificarLibro(ActionEvent event) {

        //Cojo todos los daotos
        int id = Integer.parseInt(componenteModificarController.getTxtId());
        String nombre = componenteModificarController.getTxtNombre();
        String autor = componenteModificarController.getTxtAutor();
        String comentarios = componenteModificarController.getTxtComentarios();
        boolean leido = (componenteModificarController.getSiRadio()) ? true : false;
        String genero = componenteModificarController.getTxtGenero();
        String puntuacion = componenteModificarController.getTxtPuntuacion();

        //Ahora realizo la modificación
        jdo.modificarLibro(id, nombre, autor, genero, comentarios, leido, puntuacion);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        componenteModificarController.deshabilitarCampos();
        modificarLibro.setDisable(true);
    }
}
