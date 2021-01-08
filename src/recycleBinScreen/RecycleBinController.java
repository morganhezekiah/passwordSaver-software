
package recycleBinScreen;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import Tables.RecycleBin;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class RecycleBinController implements Initializable {
    
    
    
   
    @FXML
   private  TableView recycleBinTable;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           TableColumn<RecycleBin, String> passwordsColumn = new TableColumn("Password");
           passwordsColumn.setCellValueFactory(new PropertyValueFactory("passwordValue"));
           passwordsColumn.setMinWidth(300);
           
           TableColumn<RecycleBin, String> servicesColumn = new TableColumn("Service");
           servicesColumn.setCellValueFactory(new PropertyValueFactory("serviceName"));
           servicesColumn.setMinWidth(300);
           
           recycleBinTable.setPlaceholder(new Label("No Recycled Passwords"));
           recycleBinTable.getColumns().addAll(passwordsColumn,servicesColumn);
           recycleBinTable.setItems(getRecycleBinPassword());
           
           recycleBinTable.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)->{
               try {
                   RecycleBin selectedPasswords = (RecycleBin)recycleBinTable.getSelectionModel().getSelectedItem();
                   
                   deleteRecycledPasswordModal.deleteDeletedRecycledPassword drp = new deleteRecycledPasswordModal.deleteDeletedRecycledPassword ();
                   if(drp.start(selectedPasswords.getServiceName())){
//                        ObservableList<RecycleBin> rdb =  FXCollections.observableArrayList();
                        
//                        recycleBinTable.setItems(rdb);
//                        Integer size = recycleBinTable.getSelectionModel().getSelectedItems().size();
//                        recycleBinTable.getSelectionModel().clearAndSelect(size+1);
                        resetTable();
                        recycleBinTable.getSelectionModel().clearSelection();
                        
                   }
                       
                   
               } catch (IOException ex) {
                   Logger.getLogger(RecycleBinController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (Exception ex) {
                   Logger.getLogger(RecycleBinController.class.getName()).log(Level.SEVERE, null, ex);
               }
               
           });
       } catch (Exception ex) {
           Logger.getLogger(RecycleBinController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        
    } 
    
    public ObservableList<RecycleBin> getRecycleBinPassword() throws Exception{
        DB.PasswordRecycle recycle = new DB.PasswordRecycle();
        ResultSet results = recycle.getAllUserRecycledPassword(passwordsaver.FXMLDocumentController.LoggedInUserEmail);
        ObservableList<RecycleBin> rb =  FXCollections.observableArrayList();
        while(results.next()){
            
            rb.add(new RecycleBin(results.getString("passwordValue"),results.getString("passwordService")));
        }
        return rb;
    }

    private void resetTable() throws Exception {
        DB.PasswordRecycle recycle = new DB.PasswordRecycle();
        ResultSet results = recycle.getAllUserRecycledPassword(passwordsaver.FXMLDocumentController.LoggedInUserEmail);
        ObservableList<RecycleBin> rb =  FXCollections.observableArrayList();
        while(results.next()){
            
            rb.add(new RecycleBin(results.getString("passwordValue"),results.getString("passwordService")));
        }
        
        recycleBinTable.setItems(rb);
    }
    
    
    public void handleNavigateToPasswordsScreen() throws IOException{
        homeScreen.HomeScreen hm = new homeScreen.HomeScreen();
        
        Scene currentScene = hm.start();
        passwordsaver.PasswordSaver.changeScreen(currentScene);
    }
    
}
