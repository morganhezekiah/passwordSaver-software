
package DB;

import com.mysql.jdbc.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;


public class Connect {
    public static Connection connectToDb() throws Exception{
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=(Connection) DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/passwordSaver","root","");  
            
            return con;
            
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR");
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
