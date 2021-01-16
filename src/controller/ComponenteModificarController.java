package controller;

import handler.HandlerJDO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Libro;

public class ComponenteModificarController implements Initializable {

    //TextField para modificar
    @FXML
    TextField txtId;
    @FXML
    TextField txtNombre;
    @FXML
    TextField txtAutor;
    @FXML
    TextField txtGenero;
    @FXML
    TextField txtComentarios;
    @FXML
    RadioButton siRadio;
    @FXML
    RadioButton noRadio;
    
    
    @FXML
    TextField txtPuntuacion;

    /**
     * Método para limpaiar los TextField
     */
    public void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtAutor.setText("");
        txtGenero.setText("");
        txtComentarios.setText("");
        siRadio.setSelected(false);
        noRadio.setSelected(true);
        txtPuntuacion.setText("");
    }

    public void deshabilitarCampos(){
        txtId.setDisable(true);
        txtNombre.setDisable(true);
        txtAutor.setDisable(true);
        txtGenero.setDisable(true);
        txtComentarios.setDisable(true);
        siRadio.setDisable(true);
        noRadio.setDisable(true);
        txtPuntuacion.setDisable(true);        
    }
    
    public void habilitarCampos(){
        txtId.setDisable(false);
        txtNombre.setDisable(false);
        txtAutor.setDisable(false);
        txtGenero.setDisable(false);
        txtComentarios.setDisable(false);
        siRadio.setDisable(false);
        noRadio.setDisable(false);
        txtPuntuacion.setDisable(false);        
    }
    
    /**
     * Método para buscar el Libro y poner los datos en los TextField
     * @param id 
     */
    public void buscarLibro(int id) {
        limpiarCampos();
        HandlerJDO jdo = new HandlerJDO();

        if (id != -1) {

            Libro l = jdo.consultarLibro(id);
            if (l != null) {
                setTxtlId(String.valueOf(l.getId()));
                setTxtNombre(l.getNombre());
                setTxtAutor(l.getAutor());
                setTxtGenero(l.getGenero());
                setTxtComentarios(l.getComentarios());
                
                if(l.isLeido().equals("Sí")){ 
                    noRadio.setSelected(false);
                    siRadio.setSelected(true);
                } else {
                    noRadio.setSelected(true);
                    siRadio.setSelected(false);
                }
                
                setTxtPuntuacion(String.valueOf(l.getPuntuacion()));
            }
        }
    }

    public void setTxtlId(String id) {
        this.txtId.setText(id);
    }

    public void setTxtNombre(String nombre) {
        this.txtNombre.setText(nombre);
    }

    public void setTxtAutor(String autor) {
        this.txtAutor.setText(autor);
    }

    public void setTxtGenero(String genero) {
        this.txtGenero.setText(genero);
    }

    public void setTxtComentarios(String comentarios) {
        this.txtComentarios.setText(comentarios);
    }

    
    
    
    //Los get y set de 
    public boolean getSiRadio() {
        return siRadio.isSelected();
    }
    
    public void setSiRadio(boolean opcionSi) {
        this.siRadio.setSelected(opcionSi);
    }

    public boolean getNoRadio() {
        return noRadio.isSelected();
    }

    public void setNoRadio(boolean opcionNo) {
        this.noRadio.setSelected(opcionNo);
    }



    public void setTxtPuntuacion(String puntuacion) {
        this.txtPuntuacion.setText(puntuacion);
    }

    //Getter para extraer el texto
    public String getTxtId() {
        return this.txtId.getText();
    }

    public String getTxtNombre() {
        return this.txtNombre.getText();
    }

    public String getTxtAutor() {
        return this.txtAutor.getText();
    }

    public String getTxtGenero() {
        return this.txtGenero.getText();
    }

    public String getTxtComentarios() {
        return this.txtComentarios.getText();
    }



    public String getTxtPuntuacion() {
        return this.txtPuntuacion.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Evito que se pueda modifivar el txt del id
        txtId.setEditable(false);
    }
}
