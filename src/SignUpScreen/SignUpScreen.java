package SignUpScreen;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author CYBA
 */
public class SignUpScreen {
    
    public Scene start() throws IOException{
        System.out.println("morgan");
        Parent root = FXMLLoader.load(getClass().getResource("SignUpScreen.fxml"));
        
        Scene scene = new Scene(root);
        return scene;
        
    }
    
}
