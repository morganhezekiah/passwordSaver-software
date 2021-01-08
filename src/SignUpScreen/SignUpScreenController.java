/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SignUpScreen;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import DB.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author CYBA
 */
public class SignUpScreenController implements Initializable {
    
    Boolean error = false;
    @FXML
    private TextField fullName ;
    
    @FXML
    private Label fullNameError ;
    
    @FXML
    private TextField email ;
    
    @FXML
    private Label emailError ;
    
    @FXML
    private PasswordField password ;
    
    @FXML
    private Label passwordError ;
    @FXML
    public void handleChangeScreenToSignUp(){
        passwordsaver.PasswordSaver.ChangeScreenToInit();
    }
    
    @FXML 
    public void handleSignUp(ActionEvent Event) throws SQLException{
        String emailValue = email.getText();
        String passwordValue = password.getText();
        String fullNameValue = fullName.getText();
        emailError.setText("");
        passwordError.setText("");
        fullNameError.setText("");
        error=false;
        if(emailValue.isEmpty()){
            emailError.setText("Please enter an email");
            error=true;
        }
        
        if(passwordValue.isEmpty()){
            passwordError.setText("Please enter a password");
            error=true;
        }
        if(fullNameValue.isEmpty()){
            fullNameError.setText("Please enter your Fullname");
            error=true;
        }
        
        if(error == false){
            User user = new User();
            ResultSet rs = user.getUserThroughEmail(emailValue);
            if(rs.next()){
                error=true;
                emailError.setText("This email seems to already exist");
            }
            
            if(error == false){
                Boolean execSaving =  user.addUser(emailValue, passwordValue, fullNameValue);
                if (execSaving){
                    try {
                        homeScreen.HomeScreen hs = new homeScreen.HomeScreen();
                        Scene scene = hs.start();
                        handleScreen(scene);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(SignUpScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        }
    }
    
    public void handleScreen(Scene scene){
        passwordsaver.PasswordSaver.changeScreen(scene);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
