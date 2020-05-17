/*
This class will be used to implement part 4 of the term project where we
implement a LL(1) parser.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class TableDrivenParser {
    
    private final String[][] sTable;
    private HashMap<String, ArrayList<String>> methodTracker;
    private int counter = 0;
    
    public TableDrivenParser(String[][] sTable){
        this.sTable = sTable;
        methodTracker = new HashMap<>();
        programStartTree();
    }
    
    private void programStartTree(){
        //Start of the parse tree
        String[] firstToken = getWord();
        if(firstToken[1].equals("tokprogram")){
            String[] nextToken = getWord();
            if(nextToken[1].equals("tokidentifier")){
                parseStatementList(nextToken);
            }
        } 
        else{
            System.out.println("Program does not start with 'PROGRAM'. Execution"
            + " terminated.");
        }
    }
    
    private void parseStatementList(String[] token){
        //This method will begin to parse the statement side of things for the
        //parser
        ArrayList<String> currentScope = new ArrayList<>();

        while(true){
            currentScope.add(token[0]);
            currentScope.add(token[1]);
            
            token = getWord();
            if(token[1].equals("tokword") || 
                    token[1].equals("tokvardeclaration")){
                parseExpressions(token);
            }
            else if(token[1].equals("toksemicolon")){
                populateMethodTracker(currentScope);
                currentScope.clear();
            }
            
            if(isEndOfFile() == true){
                break;
            }
        }
        
    }
    
    private void parseExpressions(String[] token){
        //Push into a stack.. see how it works... then we are done
    }
    
    private void populateMethodTracker(ArrayList<String> currentScope){
        String methodName = currentScope.get(0);
        methodTracker.put(methodName, currentScope);
    }
    
    private boolean isEndOfFile(){
        if(sTable.length == counter-1){
            return true;
        }
        return false;
    }
    
    private String[] getWord(){
        //This method will return the appropriate word required
        String[] word = new String[2];
        word[0] = sTable[counter][0];
        word[1] = sTable[counter][1];
        counter++;
        return word;
    }
}
