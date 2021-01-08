/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homeScreen;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author CYBA
 */
public class HomeScreen {
    
    public Scene start() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        
        Scene scene = new Scene(root);
        return scene;
    }
}
