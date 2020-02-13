import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;


public class Compiler {

    //Creating a new arraylist that has words with operators seperated
    static ArrayList<String> wordsWithOp = new ArrayList<>();
    
    public static void main(String[] args) {
        readInFile();    
        getToken();
    }
    
    public static void parseWord(String value){
        //This method is going to go through the words array and split up the words that have operators included within them and then store
        //them in the array wordsWithOp
        
        //This pattern is looking for special character indicating an operator
        String pattern = "[^a-zA-Z0-9]";
        Pattern r = Pattern.compile(pattern);    
        Matcher m = r.matcher(value);            
        
        //variable that indicates to true or false depending on if there is a special character
        boolean containsOperator = false;
        
        //This while loop is checking to see if any special characters are within
        while(m.find()){
            //Since there is a special character, set to containsOperator to true
            containsOperator = true;
            //Get the index of the special character
            int index = value.indexOf(m.group());
            //If the value is at index 0, indicating it is before any tokword at the front of a string
            if(index == 0){
                //Add the special character to the arraylist
                wordsWithOp.add(Character.toString(value.charAt(index)));
                //Shorten the string so it doesn't include the special character just added to the array
                value = value.substring(index+1, value.length());
            }
            //Not at the front of the array, we now have to create a substring of the new string to now 
            //get the proper special characters at the end of the array
            else{
                //Getting the location of the actual tokword
                String tempWord = value.substring(0, index);
                //Add it to the array
                wordsWithOp.add(tempWord);
                //Add the special character to the array
                wordsWithOp.add(Character.toString(value.charAt(index)));
                //Shorten the string again to not include the special character just taken out
                value = value.substring(index + 1, value.length());
            }
        }
        
        //If it doesn't contain an operator... add the value to the arraylist
        if(!containsOperator){
            wordsWithOp.add(value);
        }
            
    }
    
    public static void getToken(){
        //This method will iterate through the wordsWithOp arraylist and determine which words are what tokens
        
        //Initializing tokens array
        String[][] tokens = new String[wordsWithOp.size()][2];
        //Creating the various token patterns needed to recognize the proper tokens
        Pattern tokWord = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]*$");
        Pattern tokOp = Pattern.compile("[^a-zA-Z0-9]");
        Pattern tokNumber = Pattern.compile("[0-9]");
        for(int i = 0; i < wordsWithOp.size(); i++){
            tokens[i][0] = wordsWithOp.get(i);
            if(tokWord.matcher(wordsWithOp.get(i)).find()){
                tokens[i][1] = "tokword";
            }
            else if(tokOp.matcher(wordsWithOp.get(i)).find()){
                tokens[i][1] = "tokop";
            }
            else if(tokNumber.matcher(wordsWithOp.get(i)).find()){
                tokens[i][1] = "toknumber";
            }
        }
        
        for(int i = 0; i < tokens.length; i++){
            System.out.println(tokens[i][0] + " " + tokens[i][1]);
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
                //Go through each 'word' and send it to the wordToChar() method
                //to be further analyzed
                for(int i = 0; i < tempWords.length; i++){
                    //Gets rid of any white space
                    tempWords[i] = tempWords[i].trim();
                    //Checks to see if the line is empty... if it is, skip
                    if(!tempWords[i].isEmpty()){
                        parseWord(tempWords[i]);
                    }
                }
            }
        } 
        catch (Exception ex) {
            System.out.println("File not found!");
        }
        
    }
    
}
