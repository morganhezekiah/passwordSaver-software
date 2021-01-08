
package deleteRecycledPasswordModal;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class FXMLController implements Initializable {
    static String service;
    @FXML
    private Label errorMessage;
    @FXML
    private TextField password;
    @FXML
    private TextField passwordService;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setUp();
        } catch (Exception ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void setUp() throws SQLException, Exception{
        DB.PasswordRecycle pr = new DB.PasswordRecycle();
        String[] results= pr.getPasswordUsingServiceName(service);
        password.setText(results[0]);
        passwordService.setText(results[1]);
        
    }
    
    public void handlePermanentDelete() throws Exception{
        DB.PasswordRecycle pr = new DB.PasswordRecycle();
        if (pr.deleteAPasswordPermanently(this.service))
            deleteDeletedRecycledPassword.close();
        
    }
    
}
