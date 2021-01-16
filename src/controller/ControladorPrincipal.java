package controller;

import handler.HandlerJDO;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author RMN
 */
public class ControladorPrincipal implements Initializable {

    //rutas a las diferentes vistas componentes
    private final String TABLA_BIBLIOTECA = "/view/TablaBlibioteca.fxml";
    private final String CONSULTAR_REF = "/view/ConsultarPorRef.fxml";
    private final String EILIMNAR_LIBRO = "/view/EliminarLibro.fxml";
    private final String MODIFICAR_LIBRO = "/view/ModificarLibro.fxml";
    private final String PANEL_PRINCIPAL = "/view/PanelPrincipal.fxml";
    private final String ANADIR_LIBRO = "/view/AnadirLibro.fxml";

    //Panel Principal
    @FXML
    AnchorPane panelPrincipal;

    @FXML
    private void verTablaBiblioteca(ActionEvent event) {
        cambiarPanel(TABLA_BIBLIOTECA);
    }

    @FXML
    private void panelInicio(ActionEvent event) {
        cambiarPanel(PANEL_PRINCIPAL);
    }

    @FXML
    private void consultarLibro(ActionEvent event) {
        cambiarPanel(CONSULTAR_REF);
    }

    @FXML
    private void modificarLibro(ActionEvent event) {
        cambiarPanel(MODIFICAR_LIBRO);
    }

    @FXML
    private void elminarLibro(ActionEvent event) {
        cambiarPanel(EILIMNAR_LIBRO);
    }

    @FXML
    private void anadirLibro(ActionEvent event) {
        cambiarPanel(ANADIR_LIBRO);
    }

    @FXML
    private void acercaDe(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Información del alumno.");
        a.setHeaderText("********************************************************\n"
                + "* PSP - Tarea Individual 5 - Biblioteca casera *\n"
                + "********************************************************\n"
                + "* Ramón Martínez Nieto                   *\n"
                + "********************************************************\n");
        a.show();
    }

    @Deprecated
    @FXML
    private void manual(ActionEvent event) {
        try {
            File path = new File("./src/resources/PSP_Ramon_Martinez_Nieto_T5.pdf");
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método para cambiar de panel
     *
     * @param panelSwitch String con la ruta del panel a mostrar
     */
    private void cambiarPanel(String panelSwitch) {
        panelPrincipal.getChildren().clear();
        URL url = getClass().getResource(panelSwitch);

        try {
            Node nodo = FXMLLoader.load(url);

            panelPrincipal.getChildren().add(nodo);
            AnchorPane.setTopAnchor(nodo, 0.0);
            AnchorPane.setLeftAnchor(nodo, 0.0);
            AnchorPane.setRightAnchor(nodo, 0.0);
            AnchorPane.setBottomAnchor(nodo, 0.0);

        } catch (IOException ex) {
            System.err.println("Error al cambiar de panel");
        }
    }

    /**
     * Método para salir de la aplicación
     *
     * @param event
     */
    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Método para cargar libros de muestra
     *
     * @param event
     */
    private void cargarLibrosMuestra() {
        //Clase HandlerJDO para manejar el archivo
        HandlerJDO jdo = new HandlerJDO();

        jdo.almacenarLibroSimple("Patrones de Diseño", "Erich Gamma", "Programación", "Libro para aprender a usar los patrones de diseño", false, "10.0");
        jdo.almacenarLibroSimple("Mastering JavaFX10", "Sergey Ginev", "Programación", "Libro para aprender a programar con JvaFX", false, "6.0");
        jdo.almacenarLibroSimple("El Arte de la Invisibilidad", "Robert Mitnick", "Seguridad informática", "Libro ilustrativo para aplicar seguridad informática a nuestro día a día.", true, "8.0");
        jdo.almacenarLibroSimple("¡Guardias! ¿Guardias?", "Terry Pratchett", "Fantasía", "Uno de los libros del mundo creado por Terry Pratchett", true, "7.0");
        jdo.almacenarLibroSimple("Mort", "Terry Pratchett", "Fantasía", "Libro de la obra de Terry Pratchett, mirando el mundo como la muerte", true, "8.0");
        jdo.almacenarLibroSimple("El Inversor Inteligente", "Benjamin Graham", "Economía", "Uno de los principales libros sobre la economía y como invertir", false, "9.0");
        jdo.almacenarLibroSimple("Introductiong to Programming", "Nick Samoylov", "Programación", "Introducción a la programación con Java", true, "10.0");
        jdo.almacenarLibroSimple("Cybersecurity for Architects", "Neil Rerup", "Seguridad informática", "Libro que trata sobre la ciberseguridad", false, "0.0");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Cargo el panel principal al entrar en la aplicación
        cambiarPanel(PANEL_PRINCIPAL);

        File f = new File("./src/model/biblioteca.odb");
        //Si el archivo no existe entonces lo creo y cargo los libros de muestra
        if (!f.exists()) {
            cargarLibrosMuestra();
        }
    }
}
