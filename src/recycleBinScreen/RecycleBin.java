
package recycleBinScreen;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class RecycleBin {
    public Scene start() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("RecycleBin.fxml"));
        Scene scene = new Scene(root);
        return scene;
    }
}
