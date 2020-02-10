import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Compiler {
    
    static ArrayList<String> words = new ArrayList<>();

    public static void main(String[] args) {
        readInFile();
        for(int i = 0; i < words.size(); i++){
            System.out.println(words.get(i));
        }
    }
    
    //Method readInFile is going to read in the file and create an arraylist formatting
    //all the words that are within the file into an arraylist called words
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
                //Split the string in with every space, creating and seperating every 'word' in the line
                String[] tempWords = linesRead.split(" "); 
                //Go through each 'word' and store it into an arraylist and keep them stored in the arraylist
                for(int i = 0; i < tempWords.length; i++){
                    words.add(tempWords[i]);
                }
            }
        } 
        catch (Exception ex) {
            System.out.println("File not found!");
        }
        
    }
    
}
