
package homeScreen;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;


import DB.Password;
import Tables.Passwords;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeScreenController implements Initializable {
    static Passwords selectedItem;
    @FXML
    private  TableView passwordsTable;
    
    @FXML
    private Label addPasswordError ;
    
    @FXML 
    private MenuItem recycleNav ;
    
    @FXML
    private static Button removeTableContentButton;
    
    @FXML
    private TextField addPasswordName;
    
    @FXML
    private TextField search;
    
    @FXML
    private TextField addNewPasswordPassword;
    
         //ps.addNewPassword(passwordValue, serviceName)
    @FXML
    public void handleAddNewPassword(ActionEvent event) throws Exception{
        DB.Password ps = new DB.Password();
        Boolean error = false;
        String passwordName = addPasswordName.getText();
        String passwordService = addNewPasswordPassword.getText();
        addPasswordError.setText("");
        
        if(passwordName.isEmpty() || passwordService.isEmpty()){
            error =true;
            addPasswordError.setText("Please enter all fields");
        }else{
            Boolean addinfExec = ps.addNewPassword(passwordName, passwordService,passwordsaver.FXMLDocumentController.LoggedInUserEmail);
            
            if(addinfExec){
                passwordsTable.getItems().add(new Tables.Passwords(passwordName, passwordService));
                addPasswordName.clear();
                addNewPasswordPassword.clear();
                
                ResetPasswordTable();
            }else{
                addPasswordError.setText("Couldn't save your password,please confirm you are connected");
            }
        }
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
        try {
            DB.Password ps = new DB.Password();
            ObservableList<Passwords> passwordss =  ps.getPasswords(
                    passwordsaver.FXMLDocumentController.LoggedInUserEmail
            );
            
            TableColumn<Passwords, String> serviceValueColumn = new TableColumn("Service");
            serviceValueColumn.setMinWidth(300);
            serviceValueColumn.setCellValueFactory(new PropertyValueFactory("serviceName"));
            
            
            TableColumn<Passwords, String> passwordValueColumn = new TableColumn("Password");
            passwordValueColumn.setMinWidth(300);
            passwordValueColumn.setCellValueFactory(new PropertyValueFactory("passwordValue"));
            
            passwordsTable.setPlaceholder(new Label("No Password Found"));
            passwordsTable.setItems(passwordss);
            passwordsTable.getColumns().addAll(serviceValueColumn, passwordValueColumn);
            
    
            
            passwordsTable.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)->{
                try {
                    Passwords no = (Passwords)passwordsTable.getSelectionModel().getSelectedItem();
                    String serviceName = no.getServiceName();
                    editScreen.EditScreen editscreen = new editScreen.EditScreen();
                    editscreen.start(serviceName);
                    ResetPasswordTable();
                } catch (IOException ex) {
                    Logger.getLogger(HomeScreenController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(HomeScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            
        } catch (Exception ex) {
            Logger.getLogger(HomeScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    public void ResetPasswordTable() throws Exception {
        DB.Password ps = new DB.Password();
        ObservableList<Passwords> passwordss =  ps.getPasswords(
                passwordsaver.FXMLDocumentController.LoggedInUserEmail
        );
        
        passwordsTable.setItems(passwordss);
        
    }
    
    
    public void handleRedirctToRecycleScreen() throws IOException{
        recycleBinScreen.RecycleBin rb = new recycleBinScreen.RecycleBin();
        passwordsaver.PasswordSaver.changeScreen(rb.start());
    }

    private void init() {
        Utils.DeterminUserHasRecycledPassword duhrp = new Utils.DeterminUserHasRecycledPassword(recycleNav);
//        duhrp.start();
        
    }
    
    
    @FXML
    public void handleSearch() throws Exception{
        String param = search.getText();
        if(!param.isEmpty()){
            ResultSet result = Utils.Search.search(true, param);
            ObservableList<Passwords> passwordsToStore = FXCollections.observableArrayList();
            while(result.next()){
                Passwords ps = new Passwords(result.getString("passwordValue"),result.getString("serviceName"));
                passwordsToStore.add(ps);
            }
            passwordsTable.setItems(passwordsToStore);
        }
        
    }
    
    
 
}
