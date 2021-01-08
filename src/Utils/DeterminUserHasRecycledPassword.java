/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.MenuItem;

/**
 *
 * @author CYBA
 */
public class DeterminUserHasRecycledPassword extends Thread {
    static MenuItem item ;
    public DeterminUserHasRecycledPassword (MenuItem menuItem){
        item = menuItem;
        setName("Menu Disabler");
    }
    
    @Override
    public void run() {
        try {
            DB.PasswordRecycle pr = new DB.PasswordRecycle();
            int count = pr.countUserRecycledPasswords();
            if (count < 1)item.setDisable(true);
        } catch (Exception ex) {
            Logger.getLogger(DeterminUserHasRecycledPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
