package DB;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



import Tables.Passwords;
import java.sql.Statement;


public class Password {
    static Connection con;
    
    public Password() throws Exception{
         con = Connect.connectToDb();
    }
    
    public ObservableList<Passwords> getPasswords(String email) throws SQLException{
        ObservableList<Passwords> passwordsToStore = FXCollections.observableArrayList();
        String query ="SELECT * FROM passwords WHERE user_email = ? ORDER BY id DESC";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, email);
        ResultSet results = stmt.executeQuery();
        
        
        while(results.next()){
            
            Passwords ps = new Passwords(results.getString("passwordValue"),results.getString("serviceName"));
            passwordsToStore.add(ps);
            
        }
        
            

        
        return passwordsToStore;
        
    }
    
    public Boolean addNewPassword(String passwordValue, String serviceName,String email) throws SQLException{
        
        serviceName =  serviceName.toUpperCase();
        //VALIDATE SERVICE NAME
        String selectStatement = "SELECT * FROM passwords WHERE serviceName = ?";
        PreparedStatement stmt = con.prepareStatement(selectStatement);
        stmt.setString(1, serviceName);
        ResultSet result = stmt.executeQuery();
        int counter =0;
        while(result.next()){
            counter++;
        }
        
        if(counter > 0){
            return false;
        }else{
            //SAVING TO THE DB
            selectStatement = "INSERT INTO passwords(passwordValue, serviceName,user_email) VALUES(?, ?, ?)";
            stmt = con.prepareStatement(selectStatement);
            stmt.setString(1, passwordValue);
            stmt.setString(2, serviceName);
            stmt.setString(3, email);
            stmt.executeUpdate();
            return true;
            
        }
        
    }
    
    
    public ResultSet getPasswordFromServiceName(String serviceName) throws SQLException{
        String stml = "SELECT * FROM passwords WHERE serviceName = ?";
        PreparedStatement statement = con.prepareStatement(stml);
        statement.setString(1, serviceName);
        ResultSet result = statement.executeQuery();
        
        return result;
    }
    
    public Boolean deletePassword(String serviceName) throws SQLException{
        String stml = "DELETE  FROM passwords WHERE serviceName = ?";
        PreparedStatement statement = con.prepareStatement(stml);
        statement.setString(1, serviceName);
        statement.executeUpdate();
        return true;
    }
    
    public ResultSet search(String param) throws SQLException{
        
        String like = "'"+"%"+param+"%"+"'";
        String stml = "SELECT * FROM passwords WHERE serviceName LIKE "+like+"OR passwordValue LIKE" + like;
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(stml);
        
        return result;
    }
}
