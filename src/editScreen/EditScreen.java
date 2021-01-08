/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editScreen;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author CYBA
 */
public class EditScreen {
    static String serviceName;
    static Stage stage;
    public  void start(String serviceName) throws IOException, Exception{
        this.serviceName = serviceName;
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("EditScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit Password for "+serviceName);
        stage.setScene(scene);
        stage.showAndWait();
        
    }
    public void closeModal() throws Exception {
        
        stage.close();
        
    }
    
}
