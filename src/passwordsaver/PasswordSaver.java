package passwordsaver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import DB.Connect;
import com.mysql.jdbc.Connection;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;


public class PasswordSaver extends Application  {
     static Stage window;
     static Scene initialStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        initialStage = scene;
        window = stage;
        window.setScene(scene);
        Image image = new Image("Ng.jpg");
        window.getIcons().add(image);
        window.show();
    }
    
    public static void ChangeScreenToInit(){
        window.setScene(initialStage);
    }
    
    
    public static  void changeScreen(Scene currentScene){
        window.setScene(currentScene);
    }
    
    
    

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
