package Utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class PasswordFileHelper {
    static String filePath ="init.txt";
    static Boolean exists =false;
    public PasswordFileHelper() throws IOException{
        File init = new File(filePath);
        if(init.exists()) exists =true;
        
        
        if (exists==false)
            init.createNewFile(); 
            
    }
    
    public Boolean writeToFile(String email, String password) throws IOException{
        FileWriter myWriter = new FileWriter(filePath);
        myWriter.write("");
        myWriter.write(email+" "+password);
        myWriter.close();
        return true;
        
    }
    
    public String[] readFile() throws FileNotFoundException{
        File init = new File(filePath);
        Scanner fileReader = new Scanner(init);
        String fileContent ="";
        while(fileReader.hasNext()){
            fileContent+=fileReader.nextLine();
        }
        String[] content = fileContent.split("\\s",2);
        if(content.length > 0){
            return content;
        }else{
            return null;
        }
        
    }
    
    public Integer checkFileSize(){
        File init = new File(filePath);
        if(init.length() > 0){
            return (int)init.length();
        }else{
            return 0;
        }
    }
}
