import java.io.*;

/*
 * Handles the parsing of a single .vm file and 
 * encapsulates access to the input code. Parses
 * VM commands and removes white space and comments
 */
public class VMParser {
	private BufferedReader br;
	public String currentCommand;
	
	/*
	 * Opens the input file/stream and gets ready to parse it
	 */
	public VMParser(String filename) {
		try{
		br = new BufferedReader(new FileReader(filename));
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		currentCommand = "";
	}

	/*
	 * Are there more commands in the input?
	 */
	public boolean hasMoreCommands() throws IOException {
	   return br.ready();
	}

	/*
	 * Moves to the next command. Should only be called if hasMoreCommands() is
	 * true Initially there is no current command
	 */
	public void advance() throws IOException{
		if(hasMoreCommands()){
			currentCommand = br.readLine();
			currentCommand = currentCommand.replaceAll("\\s+"," ");
			int temp = currentCommand.indexOf("//");
			if(temp != -1){
				currentCommand = currentCommand.substring(0,temp);
			}
		}
	}

	/*
	 * Returns the current command
	 */
	public String getCurrentCommand() {
		return currentCommand;
	}

	/*
	 * Returns the type of the current command. In Assignment 7, you need only
	 * recognize C_ARITHMETIC, C_PUSH and C_POP. In Assignment 8, you will need
	 * to also recognize C_LABEL, C_GOTO, C_IF, C_FUNCTION, C_RETURN, C_CALL
	 */
	public VMCommandType commandType() {
		if(currentCommand.contains("push")){
			return VMCommandType.C_PUSH;
		}else if(currentCommand.contains("pop")){
			return VMCommandType.C_POP;
		}else if(currentCommand.contains("label")){
			return VMCommandType.C_LABEL;
		}else if(currentCommand.contains("if-goto")){
			return VMCommandType.C_IF;
		}else if(currentCommand.contains("goto")){
			return VMCommandType.C_GOTO;
		}else if(currentCommand.contains("function")){
			return VMCommandType.C_FUNCTION;
		}else if(currentCommand.contains("return")){
			return VMCommandType.C_RETURN;
		}else if(currentCommand.contains("call")){
			return VMCommandType.C_CALL;
		}else{
			return VMCommandType.C_ARITHMETIC;
		}
	}

	/*
	 * Returns the first argument of the current command as a string. For
	 * C_ARITHMETIC, the command itself is returned. Should not be called if
	 * type is C_RETURN.
	 */
	public String arg1() {
		if(commandType().equals(VMCommandType.C_ARITHMETIC)){
			return currentCommand;
		}else{
			String[] command = currentCommand.split(" ");
			if(commandType().equals(VMCommandType.C_FUNCTION)){
				currentCommand = command[1];
			}
			return command[1];
			
		}
		
	}

	/*
	 * Returns the second argument of the current command as an integer. Should
	 * only be called if the current type is C_PUSH, C_POP, C_FUNCTION or C_CALL
	 */
	public int arg2(){
		VMCommandType cmdTemp = commandType();
		if(cmdTemp.equals(VMCommandType.C_PUSH) || cmdTemp.equals(VMCommandType.C_POP) ||
			cmdTemp.equals(VMCommandType.C_FUNCTION) || cmdTemp.equals(VMCommandType.C_CALL)){
			String[] tokens = currentCommand.split(" ");
			return Integer.parseInt(tokens[2]);
		}else{
			return Integer.MIN_VALUE;
		}
	}
}

