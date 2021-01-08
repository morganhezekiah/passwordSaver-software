/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deleteRecycledPasswordModal;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public  class deleteDeletedRecycledPassword {
    static Stage  stage ;
    public Boolean start(String service) throws IOException{
        FXMLController.service = service;
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene scene =new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Delete Password for "+ service +" permanently");
        stage.showAndWait();
        return true;
    }
    
    public static void close(){
        stage.close();
    }
}
