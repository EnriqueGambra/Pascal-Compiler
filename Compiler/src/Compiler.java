import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Compiler {
    
    ArrayList<String> linesIn = new ArrayList<>();

    public static void main(String[] args) {
        readInFile();
    }
    
    public static void readInFile(){
        //Get the file path
        String filePath = JOptionPane.showInputDialog("Give the file path for the text file you'd like to write in.");
        
        //Using a sample file path for now... need double \\ to avoid the escape character in a string
        File pascalFile = new File("C:\\Users\\Owner\\github-Repos\\Pascal-Compiler\\sample_pascal_code.txt");
        
        try {
            //Trying to now open the file
            BufferedReader br = new BufferedReader(new FileReader(pascalFile));
            String linesRead;
            //Reading every line
            while((linesRead = br.readLine()) != null){
                System.out.println(linesRead);
            }
        } 
        catch (Exception ex) {
            System.out.println("File not found!");
        }
        
    }
    
}
