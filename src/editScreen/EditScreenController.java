
package editScreen;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


public class EditScreenController implements Initializable {
    private  ToggleGroup tg;
    static String serviceName ;
    @FXML
    private TextField passwordService ;
    
    @FXML
    private TextField password;
    
    @FXML
    private Button editButton ;
    
    @FXML
    private Button deleteButton ;
    
    @FXML
    private Label errorText;
    
    @FXML
    private RadioButton softDelete;
    
    @FXML
    private RadioButton hardDelete;
    
    
    
    
    @FXML
    private void handleDeleteButtonClicked() throws Exception{
        if(handleValidate()){
            if(softDelete.isSelected()){
                DB.PasswordRecycle pr = new DB.PasswordRecycle();
               Boolean recycling =  pr.addNewPassword(password.getText(), passwordService.getText(), passwordsaver.FXMLDocumentController.LoggedInUserEmail);
               if(recycling){
                   DB.Password pas = new DB.Password();
                   if(pas.deletePassword(passwordService.getText())){
                       EditScreen d = new EditScreen();
                       d.closeModal();
                   }else{
                       System.out.println("WAS NOT DELETED BY PASSWORDS");
                   }
               }else{
                   System.out.println("Was not edittedd");
               }
            }else{
                DB.Password pass = new DB.Password();
                if(pass.deletePassword(passwordService.getText())){
                       EditScreen d = new EditScreen();
                       d.closeModal();
                   }else{
                       System.out.println("WAS NOT DELETED BY PASSWORDS");
                  }
            }
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            serviceName = EditScreen.serviceName;
            passwordService.setText(serviceName);
            setPasswordFieldText();
        } catch (Exception ex) {
            Logger.getLogger(EditScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        radioButtonHandle();
    }    

    public void setPasswordFieldText() throws Exception {
        
        DB.Password newPassword = new DB.Password();
        ResultSet result = newPassword.getPasswordFromServiceName(serviceName);
        String passwordText = "";
        while(result.next()){
            passwordText+=result.getString("passwordValue");
        }
        password.setText(passwordText);
        
        
    }
    
    

    private void radioButtonHandle() {
        
        tg = new ToggleGroup();
        softDelete.setToggleGroup(tg);
        hardDelete.setToggleGroup(tg);
        
        softDelete.setSelected(true);
    }
    
    public Boolean handleValidate(){
        String passwordservicetext = passwordService.getText();
        String passwordText = password.getText();
        Boolean error = false;
        errorText.setText("");
        
        if(passwordservicetext.isEmpty() || passwordText.isEmpty()){
            errorText.setText("Please enter all the fields");
            error=true;
        }
        
        if(error){
            return false;
        }
        return true;
    }
    
}
