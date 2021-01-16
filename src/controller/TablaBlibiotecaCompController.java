package controller;

import handler.HandlerJDO;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Libro;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.fxutils.viewer.JasperViewerFX;

public class TablaBlibiotecaCompController implements Initializable {

    //Objeto HandlerJDO a utilizar
    HandlerJDO jdo = new HandlerJDO();
    
    private final String rutaInformeBiblioteca = "/reports/listadoLibros.jasper";

    //Clases importantes TableView, TableColumn y TableCell

    //ObervableList para la tabla
    final ObservableList<Libro> observableDatos = FXCollections.observableArrayList();
    
    @FXML
    TableColumn columnaId; 
    
    @FXML
    TableColumn columnaNombre; 
    
    @FXML
    TableColumn columnaAutor; 
    
    @FXML
    TableColumn columnaGenero; 
    
    @FXML
    TableColumn columnaComentarios; 
    
    @FXML
    TableColumn columnaLeido; 
    
    @FXML
    TableColumn columnaPuntuacion;     
    
    @FXML
    TableView tableViewLibros;
    
    @FXML
    public void generarInforme(ActionEvent event) {
        try {
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(jdo.consultarBiblioteca());
            Node source = (Node) event.getSource();
            Stage Stage = (Stage) source.getScene().getWindow();
            JasperViewerFX viewerFx = new JasperViewerFX(Stage, "Informe de los libros de tu biblioteca personal", rutaInformeBiblioteca, new HashMap(), beanColDataSource);
            viewerFx.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Obtengo todos los libros e instancio un Iterator
        Iterator iterator = jdo.consultarBiblioteca().iterator();

        while(iterator.hasNext()){
            Libro l = (Libro) iterator.next();
            observableDatos.add(l);
        }
        
        //Asocio los datos de las columnas con la clase Libro
        columnaId.setCellValueFactory(new PropertyValueFactory<Libro,Integer>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<Libro,String>("nombre"));
        columnaAutor.setCellValueFactory(new PropertyValueFactory<Libro,String>("autor"));
        columnaGenero.setCellValueFactory(new PropertyValueFactory<Libro,String>("genero"));
        columnaComentarios.setCellValueFactory(new PropertyValueFactory<Libro,String>("comentarios"));
        columnaLeido.setCellValueFactory(new PropertyValueFactory<Libro,String>("leido2"));
        columnaPuntuacion.setCellValueFactory(new PropertyValueFactory<Libro,String>("puntuacion"));
      
        
        tableViewLibros.setItems(observableDatos);
        

    }
}
