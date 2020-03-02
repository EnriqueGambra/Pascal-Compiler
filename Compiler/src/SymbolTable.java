import java.util.*;

public class SymbolTable {
    //This class will create the symbol table from the already parsed tokens
    
    private static HashMap<String, String> sTable = new HashMap<>();
    private static String[][] tokenArr;
    private static String[][] sTableArr;
    
    
    public SymbolTable(String[][] tokenArr){
        this.tokenArr = tokenArr;
        populateSTable();
    }
    
    public static void populateSTable(){
        //This method will populate the symbol table to include all tokens and key words
        sTable.put("PROGRAM", "tokprogram");
        sTable.put(";", "toksemicolon");
        sTable.put("=", "tokequals");
        sTable.put("BEGIN", "tokbegin");
        sTable.put("END", "tokend");
        sTable.put(".", "tokperiod");
    }
    
    public void getSTableToken(){
        //This method will convert the tokens from the compiler class and put them as a part of the symbol table class
        sTableArr = new String[tokenArr.length][2];
        
        boolean skipCurrent = false;
        
        for(int i = 0; i < tokenArr.length; i++){
            
            if(skipCurrent){
                skipCurrent = false;
                continue;
            }
            
            String word = tokenArr[i][0];
            String token = tokenArr[i][1];
            
            if(sTable.containsKey(word.toUpperCase())){
                //Checking to correctly identify identifiers after an equals sign was detected
                if(word.equals("=")){
                    if(tokenArr[i-2][1].equals("tokword")){
                        sTableArr[i-1][1] = "tokidentifier";
                    }
                }
                //If the word equals program, the next word must be identifier and skip the loop
                else if(word.equals("PROGRAM")){
                    sTableArr[i+1][0] = tokenArr[i+1][0];
                    sTableArr[i+1][1] = "tokidentifier";
                    skipCurrent = true;
                }
                
                sTableArr[i][0] = word;
                sTableArr[i][1] = sTable.get(word.toUpperCase());
            }
            //If none of these tokens are in the array, put these words straight into the array
            else{
                sTableArr[i][0] = word;
                sTableArr[i][1] = token;
            }
        }
        
    }
    
    public void sTableToString(){
        //Outputs the sTableArr to the terminal showing the appropriate tokens
        for(int i = 0; i < sTableArr.length; i++){
            String word = sTableArr[i][0];
            String token = sTableArr[i][1];
            
            System.out.println(word + " " + token);
        }
    }
}
