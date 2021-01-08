package passwordsaver;

import com.mysql.jdbc.Connection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import DB.Connect;
import DB.User;
import Utils.PasswordFileHelper;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class FXMLDocumentController implements Initializable {
    
    public static String LoggedInUserEmail ="";
    private   static Connection con;
    
    @FXML
    private   Label username_error;
    
    @FXML
    private   Label password_error;
    
    @FXML
    private   PasswordField password;
    
    @FXML
    private TextField email;
    
    
    @FXML
    private  void handleLogin(ActionEvent event) throws IOException {
        Boolean error = false;
        password_error.setText("");
        username_error.setText("");
        String passwordValue = password.getText();
        String usernameValue = email.getText();
        if (passwordValue.isEmpty()){
            password_error.setText("Please enter a value for the password");
            error=true;
        }
        if(usernameValue.isEmpty()){
            username_error.setText("Please enter a value for the password");
            error=true;
        }
        
        if(error == false){
            try {
                User user = new User();
                ResultSet executing = user.getUserThroughEmail(usernameValue);
                int count =0;
                String password ="";
                while(executing.next()){
                    count++;
                    password+=executing.getString("passwords");
                }
                
                if(count == 1){
                    if(password.equals(passwordValue)){
                        homeScreen.HomeScreen hs = new homeScreen.HomeScreen();
                        Scene hsScene = hs.start();
                        PasswordFileHelper ph = new PasswordFileHelper();
                        ph.writeToFile(usernameValue, passwordValue);
                        LoggedInUserEmail = usernameValue;
                        handleChangeScreen(hsScene);
                    }
                }
                
               
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    //CHANGE SCREEN TO THE SIGN UP SCREEN
    @FXML 
    public void handleChangeScreenToSignUp(ActionEvent event) throws IOException{
        SignUpScreen.SignUpScreen signup= new SignUpScreen.SignUpScreen();
        handleChangeScreen(signup.start());
        
        
    }
    
    public void handleChangeScreen(Scene scene){
        PasswordSaver.changeScreen(scene);
    }
    
    @FXML
    public void handleEmailChange(ActionEvent event){
        System.out.println("change");
    }
    
    public void handleInitFuctionality() throws IOException{
        PasswordFileHelper ph = new PasswordFileHelper();
        Integer fileSize = ph.checkFileSize();
        if(fileSize > 0){
            String[] fileContent = ph.readFile();
            String emailFromFile = fileContent[0];
            String passwordFromFile = fileContent[1];
            email.setText(emailFromFile);
            password.setText(passwordFromFile);
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        LoggedInUserEmail = email.getText();
        try {
            handleInitFuctionality();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = Connect.connectToDb();
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
