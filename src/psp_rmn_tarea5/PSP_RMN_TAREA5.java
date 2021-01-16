/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp_rmn_tarea5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author RMN
 */
public class PSP_RMN_TAREA5 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ViewPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        
        //establezco títutlo y tamaños
        stage.setTitle("Librería Personal");
        stage.setMinHeight(550);
        stage.setMaxHeight(550);
        stage.setMinWidth(700);
        stage.setMaxWidth(700);
       
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
