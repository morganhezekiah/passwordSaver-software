
package DB;

import static DB.Password.con;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PasswordRecycle {
    static Connection   con;
    public PasswordRecycle() throws Exception{
        con = Connect.connectToDb();
    }
    public Boolean addNewPassword(String passwordValue, String passwordService, String user_email) throws SQLException{
        String stml = "INSERT INTO password_recycle(passwordValue, passwordService, user_email) VALUES(?,?,?) ";
        PreparedStatement statement = con.prepareStatement(stml);
        statement.setString(1, passwordValue);
        statement.setString(2, passwordService);
        statement.setString(3, user_email);
        statement.executeUpdate();
        return true;
        
    }
    
    public ResultSet getAllUserRecycledPassword(String user_email) throws SQLException{
        String stml ="SELECT * FROM password_recycle WHERE user_email = ? ORDER BY id DESC";
        PreparedStatement statement = con.prepareStatement(stml);
        statement.setString(1, user_email);
        ResultSet results = statement.executeQuery();
        return results;
    }
    
    public Boolean deleteAPasswordPermanently(String servicename) throws SQLException{
        String stml ="DELETE FROM password_recycle WHERE passwordService = ?";
        PreparedStatement statement = con.prepareStatement(stml);
        statement.setString(1, servicename);
        statement.executeUpdate();
        return true;
    }
    
    public String[] getPasswordUsingServiceName(String service) throws SQLException{
        String stml ="SELECT * FROM password_recycle WHERE  passwordService = ?";
        PreparedStatement statement = con.prepareStatement(stml);
        statement.setString(1, service);
        ResultSet results = statement.executeQuery();
        String password="",passwordService = "";
        while(results.next()){
            password+=results.getString("passwordValue");
            passwordService+=results.getString("passwordService");
        }
        String[] resultArray ={password, passwordService};
        return resultArray;
    }
    
    public int countUserRecycledPasswords() throws SQLException{
        ResultSet rs = getAllUserRecycledPassword(passwordsaver.FXMLDocumentController.LoggedInUserEmail);
        int count = 0;
        while(rs.next()){
            count+=1;
        }
        return count;
    }
    
     public ResultSet search(String param) throws SQLException{
        String like = "%"+param+"%";
        String stml = "SELECT * FROM passwords WHERE passwordService LIKE "+like+"OR passwordValue LIKE" + like;
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(stml);
        
        return result;
    }
}
