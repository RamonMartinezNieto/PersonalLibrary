package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ComponenteBuscarController implements Initializable {
    
    @FXML 
    TextField textFieldID;   
    
    /**
     * Obtener texto del TextField dónde inlcuimos el id
     * @return 
     */
    public int getFieldId(){
        //TODO ¿Manejar Integer desde aquí?
        if(comprobarInt(textFieldID.getText())){
            return Integer.parseInt(textFieldID.getText());
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Error con el número introducido");
            a.setHeaderText("Tienes que introducir un núemero entero");
            a.show();
            
            return -1;
        }
    }
    
    private boolean comprobarInt(String id){
        try{
            int numero = Integer.parseInt(id);
            return true; 
        } catch(NumberFormatException e){
            return false;
        }
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO - Cargar ComboBox
    }    
    
}
