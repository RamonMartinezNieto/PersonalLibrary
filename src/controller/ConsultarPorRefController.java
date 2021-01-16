package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ConsultarPorRefController implements Initializable {

    @FXML
    ComponenteDatosLibroController componenteDatosController;

    @FXML
    ComponenteBuscarController componenteBuscarController;

    /**
     * Método para el botón buscar por referencia
     * @param event
     */
    @FXML
    public void buscar(ActionEvent event) {
        componenteDatosController.buscarLibro(componenteBuscarController.getFieldId());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}