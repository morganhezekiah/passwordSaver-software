
package DB;

import com.mysql.jdbc.Connection;
import DB.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class User {
    static Connection con;
    public User(){
        try {
            con = Connect.connectToDb();
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Boolean addUser(String email , String password, String fullName) throws SQLException{
        String stm = "INSERT INTO users(email, passwords, fullName) VALUES(?,?,?)";
        PreparedStatement stml = con.prepareStatement(stm);
        stml.setString(1, email);
        stml.setString(2, password);
        stml.setString(3, fullName);
        stml.executeUpdate();
        return true;

    }
    
    public ResultSet getUserThroughEmail(String email) throws SQLException{
        String stml = "SELECT * FROM users WHERE email = ?";
        PreparedStatement stm = con.prepareStatement(stml);
        stm.setString(1, email);
        ResultSet rs = stm.executeQuery();
        return rs;
        
    }
    
    
}
