package controller;

import handler.HandlerJDO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Libro;

public class ComponenteDatosLibroController implements Initializable {

    //Labels a modificar
    @FXML
    Label lblId;
    @FXML
    Label lblNombre;
    @FXML
    Label lblAutor;
    @FXML
    Label lblGenero;
    @FXML
    Label lblComentarios;
    @FXML
    Label lblLeido;
    @FXML
    Label lblPuntuacion;

    /**
     * Método para limpiar los campos
     */
    public void limpiarDatos() {
        lblId.setText("");
        lblNombre.setText("");
        lblAutor.setText("");
        lblGenero.setText("");
        lblComentarios.setText("");
        lblLeido.setText("");
        lblPuntuacion.setText("");
    }

    /**
     * Método para buscar el Libro y establecer los datos
     * @param id 
     */
    public void buscarLibro(int id) {
        HandlerJDO jdo = new HandlerJDO();
        limpiarDatos();
        
        if (id != -1) {
            Libro l = jdo.consultarLibro(id);

            if (l != null) {
                setLblId(String.valueOf(l.getId()));
                setLblNombre(l.getNombre());
                setLblAutor(l.getAutor());
                setLblGenero(l.getGenero());
                setLblComentarios(l.getComentarios());
                setLblLeido(l.isLeido());
                setLblPuntuacion(String.valueOf(l.getPuntuacion()));
            }
        }
    }

    //Setters de los label
    public void setLblId(String id) {
        this.lblId.setText(id);
    }

    public void setLblNombre(String nombre) {
        this.lblNombre.setText(nombre);
    }

    public void setLblAutor(String autor) {
        this.lblAutor.setText(autor);
    }

    public void setLblGenero(String genero) {
        this.lblGenero.setText(genero);
    }

    public void setLblComentarios(String comentarios) {
        this.lblComentarios.setText(comentarios);
    }

    public void setLblLeido(String leido) {
        this.lblLeido.setText(leido);
    }

    public void setLblPuntuacion(String puntuacion) {
        this.lblPuntuacion.setText(puntuacion);
    }

    //Getter de los labels
    public String getLblId() {
        return this.lblId.getText();
    }

    public String getLblNombre() {
        return this.lblNombre.getText();
    }

    public String getLblAutor() {
        return this.lblAutor.getText();
    }

    public String getLblGenero() {
        return this.lblGenero.getText();
    }

    public String getLblComentarios() {
        return this.lblComentarios.getText();
    }

    public String getLblLeido() {
        return this.lblLeido.getText();
    }

    public String getLblPuntuacion() {
        return this.lblPuntuacion.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        limpiarDatos();
    }

}
