package controller;

import handler.HandlerJDO;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class EliminarLibroController implements Initializable {

    HandlerJDO jdo = new HandlerJDO();

    @FXML
    ComponenteDatosLibroController componenteDatosController;

    @FXML
    ComponenteBuscarController componenteBuscarController;

    /**
     * Método para el botón buscar libro que se va a elimnar
     *
     * @param event
     */
    @FXML
    public void buscar(ActionEvent event) {
        componenteDatosController.buscarLibro(componenteBuscarController.getFieldId());
    }

    @FXML
    public void eliminar(ActionEvent event) {

        int idElminar = (isEmpty(componenteDatosController.getLblId())) ? -1 : Integer.parseInt(componenteDatosController.getLblId());

        if (idElminar == -1) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("ERROR!");
            a.setHeaderText("Error al intentar eliminar el libro.");
            a.setContentText("Debes buscar un libro para poder eliminarlo.");
            a.showAndWait();

        } else {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirmación");
            a.setHeaderText("¿Seguro que deseas eliminar este libro?");
            a.setContentText(jdo.consultarLibro(idElminar).getNombre() + "\n" + jdo.consultarLibro(idElminar).getAutor());
            //Almaceno el resultado del alert
            Optional<ButtonType> resultado = a.showAndWait();
            //Si pulso aceptar lo elimino
            if(resultado.get() == ButtonType.OK){
                jdo.eliminarLibro(idElminar);
                componenteDatosController.limpiarDatos();
            }
        }
    }

    /**
     * Comprobar si un campo está vacio
     *
     * @param str
     * @return
     */
    private boolean isEmpty(String str) {
        if (str.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
