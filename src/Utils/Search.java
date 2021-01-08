
package Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Search {
    
    static String param;
    public static ResultSet search(Boolean searcher, String para) throws SQLException, Exception{
        param = para;
        ResultSet result ;
        if (searcher)
            result = searchPasswords();
        else result = searchRecyclePassword();
        
        return result;
        
    }

    private static ResultSet searchRecyclePassword() throws Exception {
        DB.PasswordRecycle passwordRescycle = new DB.PasswordRecycle();
        ResultSet result = passwordRescycle.search(param);
        return result;
    }

    private static ResultSet searchPasswords() throws Exception {
        
            DB.Password password =  new DB.Password();
            ResultSet result = password.search(param);
            return result;
    }
}
