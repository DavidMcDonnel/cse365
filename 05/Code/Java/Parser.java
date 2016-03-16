import java.io.BufferedReader;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

 // Encapsulates access to the input code.
 //     Reads an assembly language command, prases it and 
 //     provides convenient access to to the command's components. 
 //     Also removes white space and comments.
 //
class Parser{
    public static enum CommandType { A_COMMAND, C_COMMAND, L_COMMAND };
    public int lineCount;
    public String file;
    public String[] linesArray;
    BufferedReader br;
    
    /*
     *Opens the input file/stream and gets ready to parse it
     */
    public Parser(String filename)
    {
    	FileInputStream fs = null;
    	lineCount = 0;
    	try{
    		fs = new FileInputStream(filename);
    	} catch (FileNotFoundException e1){
    		e1.printStackTrace();
    	}
    	int fileContent;
    	try{
    		while ((fileContent = fs.read())!=-1){
    			file += (char) fileContent;
    		}
    	}catch (IOException e){
    		e.printStackTrace();
    	}
    	
    	DataInputStream in = new DataInputStream(fs);
    	br = new BufferedReader(new InputStreamReader(in));
    	file.replaceAll( "//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/|(?m)^[ \t]*\r?\n|null|\t", "" );
		file.replaceAll("(?m)^[ \t]*\r?\n", "");
		linesArray = file.split("\n");
		
		for (String i:linesArray){
			i.trim();
		}
    }

    /*
     * Are there more commands in the input?
     */        
    public boolean hasMoreCommands()
    {
        return lineCount<linesArray.length;
    }

    /*
     *Moves to the next command. 
     *     Should only be called if hasMoreCommands() is true
     *     Initially there is no current command
     */
    public void advance()
    {
    	lineCount++;
    }

    /*
     * Returns the current command
     */        
    public String getCurrentCommand()
    {
    	return linesArray[lineCount];
    }
    
    /*
     * Resets back to original state
     */        
    public void reset()
    {
    }

    /*
     * Returns the type of the current command.
     *      A_COMMAND for @Xxx where Xxx is a symbol or number
     *      C_COMMAND for dest=comp;jump
     *      L_COMMAND for (xxx) where Xxx is symbol
     */
    public CommandType commandType()
    {
    	if (linesArray[lineCount].startsWith("@")){
    		return Parser.CommandType.A_COMMAND;
    	}else if(linesArray[lineCount].startsWith("(")){
    		return Parser.CommandType.L_COMMAND;
    	}else {
    		return Parser.CommandType.C_COMMAND;
    	}
    }

    /*
     *Returns the symbol Xxx of the current command
     *      of type @Xxx or (Xxx). Should only be called when commandType
     *      is A_COMMAND or L_COMMAND
     */
    public String symbol()
    {
    	String ans = linesArray[lineCount];
    	if (linesArray[lineCount].startsWith("@")){
    		ans.replaceAll("@","");
    	}else {
    		if(linesArray[lineCount].startsWith("(")){
    		ans.replaceAll("\\((.*?)\\)","$1");
    		}
    	}
    	return ans;
    }    

    /*
     *Returns the destination mnemonic in the current C-command. Should be
     *      called only when commandType() is C_COMMAND
     */
    public String dest()
    {
        if(linesArray[lineCount].contains("=")){
        	String dest = linesArray[lineCount];
        	dest=dest.substring(0,dest.lastIndexOf("="));
        	return dest;
        }else return null;
    }    

    /*
     *Returns the comp mnemonic in the current C-command. Should be
           called only when commandType() is C_COMMAND
     */
    public String comp()
    {
    	String comp = linesArray[lineCount];
    	if(linesArray[lineCount].contains(";")){
        	comp=comp.substring(comp.lastIndexOf("=")+1,comp.lastIndexOf(";"));
        	return comp;
        }else{
        	comp=comp.substring(comp.lastIndexOf("=")+1,comp.length());
        	return comp;
        }
    }    

    /*
     *Returns the jump mnemonic in the current C-command. Should be
           called only when commandType() is C_COMMAND
     */            
    public String jump()
    {
        if(linesArray[lineCount].contains(";")){
        	String jump = linesArray[lineCount];
        	return jump.substring(jump.lastIndexOf(";")+1, jump.length());
        }
        else{
        	return null;
        }
    }    
}
