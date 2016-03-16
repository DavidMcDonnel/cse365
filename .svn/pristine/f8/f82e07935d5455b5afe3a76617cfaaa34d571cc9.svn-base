import java.io.*;

/*Translates VM commands into Hack assembly*/
public class CodeWriter {

	public BufferedWriter bw;
	private String currentFile;
	private int countLT = 0;
	private int countGT = 0;
	private int countEQ = 0;
	private int countReturnAddress = 0;
	
	/*Opens the output file and gets ready to write into it*/
	public CodeWriter(String filename) throws IOException {
		File f = new File(filename);
		if(!f.exists()){
			f.createNewFile();
		}
		FileWriter fw = new FileWriter(f.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		currentFile = filename.substring(filename.lastIndexOf('\\')+1,filename.indexOf('.'));
	}

	/*Informs the code writer that the translation 
	of a new VM file is started.*/
	public void setFilename(String filename) {
		currentFile = filename;//.substring(filename.lastIndexOf('/'), filename.indexOf('.'));
	}

	/*Writes the assembly code that is the translation of
	the given arithmetic command*/
	public void writeArithmetic(String command) throws IOException {
		
		if(command.equals("add"))
		{
			bw.write("//!-add\n");
			bw.write("@SP\n");
			bw.write("A=M\n");
			bw.write("A=A-1\n");
			bw.write("D=M\n");
			
			bw.write("A=A-1\n");
			bw.write("M=D+M\n");
			bw.write("D=A+1\n");
			bw.write("@SP\n");
			bw.write("M=D\n");
			bw.write("A=M-1\n");
			bw.write("D=M\n");
			
		}else if(command.equals("sub")){
			bw.write("//!-sub\n");
			
			bw.write("@SP\n");
			bw.write("A=M\n");
			bw.write("A=A-1\n");
			bw.write("D=M\n");
			
			bw.write("A=A-1\n");
			bw.write("M=M-D\n");
			bw.write("D=A+1\n");
			bw.write("@SP\n");
			bw.write("M=D\n");
			bw.write("A=M-1\n");
			bw.write("D=M\n");
			
		}else if(command.equals("neg")){
			bw.write("//!-neg\n");
			
			bw.write("@SP\n");
			bw.write("AM=M-1\n");
			bw.write("D=M\n");
			bw.write("A=A-1\n");
			
			bw.write("D=-D\n");
			bw.write("M=D\n");
			
		}else if(command.equals("eq")){
			bw.write("//!-eq\n");	
			
			bw.write("@SP\n");
			bw.write("AM=M-1\n");
			bw.write("D=M\n");
			//bw.write("A=A-1\n");
			
			bw.write("A=A-1\n");
			
			bw.write("D=M-D\n");
			bw.write("@EQ_"+countEQ+"\n");
			bw.write("D;JEQ\n");
			bw.write("@SP\n");
			bw.write("A=M\n");
			bw.write("A=A-1\n");
			bw.write("A=A-1\n");
			bw.write("M=0\n");
			bw.write("@NeQ_"+countEQ+"\n");
			bw.write("0;JMP\n");
			
			bw.write("(EQ_"+countEQ+")\n");
			bw.write("@SP\n");
			bw.write("A=M\n");
			bw.write("A=A-1\n");
			bw.write("A=A-1\n");
			bw.write("M=-1\n");
			
			bw.write("(NeQ_"+countEQ+")\n"); 
			bw.write("@SP\n");
			bw.write("M=M-1\n");
			bw.write("A=M\n");
			bw.write("A=A-1\n");
			bw.write("D=M\n");
			
			countEQ++;
			
		}else if(command.equals("gt")){
			bw.write("//!-gt\n");
			
			bw.write("@SP\n");
			bw.write("AM=M-1\n");
			bw.write("D=M\n");
			//bw.write("A=A-1\n");
			
			bw.write("A=A-1\n");
			
			bw.write("D=M-D\n");
			bw.write("@GT_"+countGT+"\n");
			bw.write("D;JGT\n");
			bw.write("@SP\n");
			bw.write("A=M\n");
			bw.write("A=A-1\n");
			bw.write("A=A-1\n");
			bw.write("M=0\n");
			bw.write("@NgT_"+countGT+"\n");
			bw.write("0;JMP\n");
			
			bw.write("(GT_"+countGT+")\n");
			bw.write("@SP\n");
			bw.write("A=M\n");
			bw.write("A=A-1\n");
			bw.write("A=A-1\n");
			bw.write("M=-1\n");
			
			bw.write("(NgT_"+countGT+")\n");
			bw.write("@SP\n");
			bw.write("M=M-1\n");
			bw.write("A=M\n");
			bw.write("A=A-1\n");
			bw.write("D=M\n");
			
			countGT++;
			
		}else if(command.equals("lt")){
			bw.write("//!-lt\n");
			bw.write("@SP\n");
			bw.write("A=M\n");
			bw.write("A=A-1\n");
			bw.write("D=M\n");
			
			bw.write("A=A-1\n");
			
			bw.write("D=M-D\n");
			bw.write("@LT_"+countLT+"\n");
			bw.write("D;JLT\n");
			bw.write("@SP\n");
			bw.write("A=M\n");
			bw.write("A=A-1\n");
			bw.write("A=A-1\n");
			bw.write("M=0\n");
			bw.write("@NlT_"+countLT+"\n");
			bw.write("0;JMP\n");
			
			bw.write("(LT_"+countLT+")\n");
			bw.write("@SP\n");
			bw.write("A=M\n");
			bw.write("A=A-1\n");
			bw.write("A=A-1\n");
			bw.write("M=-1\n");
			
			bw.write("(NlT_"+countLT+")\n");
			bw.write("@SP\n");
			bw.write("M=M-1\n");
			bw.write("A=M\n");
			bw.write("A=A-1\n");
			bw.write("D=M\n");
			
			countLT++;
			
		}else if(command.equals("and")){
			bw.write("//!-and\n");
			bw.write("@SP\n");
			bw.write("A=M\n");
			bw.write("A=A-1\n");
			bw.write("D=M\n");
			
			bw.write("A=A-1\n");
			bw.write("M=M&D\n");
			bw.write("D=A+1\n"); 
			bw.write("@SP\n");
			bw.write("M=D\n");
			bw.write("A=M-1\n");
			bw.write("D=M\n");
			
		}else if(command.equals("or")){
			bw.write("//!-or\n");
			bw.write("@SP\n");
			bw.write("A=M\n");
			bw.write("A=A-1\n");
			bw.write("D=M\n");
			
			bw.write("A=A-1\n");
			bw.write("M=M|D\n");
			bw.write("D=A+1\n"); 
			bw.write("@SP\n");
			bw.write("M=D\n");
			bw.write("A=M-1\n");
			bw.write("D=M\n");
			
		}if(command.equals("not")){
			bw.write("//!-not\n");
			bw.write("@SP\n");
			bw.write("A=M\n");
			bw.write("A=A-1\n");
			bw.write("D=M\n");
			
			bw.write("D=!D\n");
			bw.write("M=D\n");
		}
			
	}

	/*Writes the assembly code that is the translation of 
	the given C_PUSH or C_POP command.
	command is either C_PUSH or C_POP.
	*/
	public void writePushPop(VMCommandType command, String segment, int index)
			throws IOException {
		String temp = "";
		String temp2 = "A=M\n";
		if(command.equals(VMCommandType.C_PUSH)){
			bw.write("//!-push " + segment + " " + index + "\n");
			if(segment.equals("constant")){
				
				bw.write("@"+index+"\n");
				bw.write("D=A\n");
				bw.write("@SP\n");
				bw.write("A=M\n");
				bw.write("M=D\n");
				bw.write("@SP\n");
				bw.write("AM=M+1\n");
				bw.write("A=A+1\n");
				bw.write("D=M\n");
				
			}else if(segment.equals("local")){
				
				bw.write("@"+index+"\n");
				bw.write("D=A\n");
				bw.write("@LCL\n");
				bw.write("A=M\n");
				bw.write("A=A+D\n");
				bw.write("D=M\n");
				bw.write("@SP\n");
				bw.write("A=M\n");
				bw.write("M=D\n");
				bw.write("@SP\n");
				bw.write("AM=M+1\n");
				bw.write("A=A-1\n");
				
			}else if(segment.equals("argument")){
				
				bw.write("@"+index+"\n");
				bw.write("D=A\n");
				bw.write("@ARG\n");
				bw.write("A=M\n");
				bw.write("A=A+D\n");
				bw.write("D=M\n");
				bw.write("@SP\n");
				bw.write("A=M\n");
				bw.write("M=D\n");
				bw.write("@SP\n");
				bw.write("AM=M+1\n");
				bw.write("A=A-1\n");
				
			}else if(segment.equals("this")){
				
				bw.write("@"+index+"\n");
				bw.write("D=A\n");
				bw.write("@THIS\n");
				bw.write("A=M\n");
				bw.write("A=A+D\n");
				bw.write("D=M\n");
				bw.write("@SP\n");
				bw.write("A=M\n");
				bw.write("M=D\n");
				bw.write("@SP\n");
				bw.write("AM=M+1\n");
				bw.write("A=A-1\n");

			}else if(segment.equals("that")){
				
				bw.write("@"+index+"\n");
				bw.write("D=A\n");
				bw.write("@THAT\n");
				bw.write("A=M\n");
				bw.write("A=A+D\n");
				bw.write("D=M\n");
				bw.write("@SP\n");
				bw.write("A=M\n");
				bw.write("M=D\n");
				bw.write("@SP\n");
				bw.write("AM=M+1\n");
				bw.write("A=A-1\n");

				
			}else if(segment.equals("pointer")){
				
				if(index == 0){temp = "THIS";}else{temp = "THAT";}
				
				bw.write("@" + temp + "\n");
				bw.write("D=M\n");
				bw.write("@SP\n");
				bw.write("A=M\n");
				bw.write("M=D\n");
				bw.write("@SP\n");
				bw.write("AM=M+1\n");
				bw.write("A=A-1\n");
				
			}else if(segment.equals("temp")){
				
				bw.write("@"+index+"\n");
				bw.write("D=A\n");
				bw.write("@R5\n");
				bw.write("A=A+D\n");
				bw.write("D=M\n");
				bw.write("@SP\n");
				bw.write("A=M\n");
				bw.write("M=D\n");
				bw.write("@SP\n");
				bw.write("AM=M+1\n");
				bw.write("A=A-1\n");	
				
			}else if(segment.equals("static")){
				
				bw.write("@"+currentFile+"."+index+"\n");
				bw.write("D=M\n");
				bw.write("@SP\n");
				bw.write("A=M\n");
				bw.write("M=D\n");
				bw.write("@SP\n");
				bw.write("AM=M+1\n");
				bw.write("A=A-1\n");	
				
			}
		}else if(command.equals(VMCommandType.C_POP)){
			bw.write("//!-pop " + segment + " " + index + "\n");
			if(segment.equals("pointer")){
				if(index == 0){temp = "THIS";}else{temp = "THAT";}
				
				bw.write("D=M\n");
				bw.write("@"+temp+"\n");
				bw.write("M=D\n");
				bw.write("@SP\n");
				bw.write("M=M-1\n");
				bw.write("A=M-1\n");
				
			}else if(segment.equals("static")){
				
				bw.write("@"+currentFile+"."+index+"\n");
				bw.write("M=D\n");
				bw.write("@SP\n");
				bw.write("AM=M-1\n");
				bw.write("A=A-1\n");
				bw.write("D=M\n");
				
			}else{
				
				if(segment.equals("local")){
					temp = "LCL";
				}else if(segment.equals("argument")){
					temp = "ARG";
				}else if(segment.equals("this")){
					temp = "THIS";
				}else if(segment.equals("that")){
					temp = "THAT";
				}else if(segment.equals("temp")){
					temp = "R5";
					temp2 = "";
				}
				
				bw.write("D=M\n");
				bw.write("@R13\n");
				bw.write("M=D\n");
				bw.write("@"+index+"\n");
				bw.write("D=A\n");
				bw.write("@" + temp + "\n");
				bw.write(temp2);
				bw.write("D=A+D\n");
				bw.write("@R14\n");
				bw.write("M=D\n");
				bw.write("@R13\n");
				bw.write("D=M\n");
				bw.write("@R14\n");
				bw.write("A=M\n");
				bw.write("M=D\n");
				bw.write("@SP\n");
				bw.write("M=M-1\n");
				bw.write("A=M-1\n");
			}
		}
	}
	
	public void writeInit() throws IOException{
		bw.write("//!-Init\n");
		bw.write("@256\n");
		bw.write("D=A\n");
		bw.write("@SP\n");
		bw.write("M=D\n");
		writeCall("Sys.init",0);
	}
	
	public void writeLabel(String label) throws IOException{
		bw.write("//!-label "+label+"\n");
		bw.write("("+label+")\n");
	}
	
	public void writeGoto(String label) throws IOException{
		bw.write("//!-goto "+label+"\n");
		bw.write("@"+label+"\n");
		bw.write("0;JMP\n");
	}
	public void writeIf(String label) throws IOException{
		bw.write("//!-if-goto "+label+"\n");
		bw.write("D=M\n");
		bw.write("@SP\n");
		bw.write("M=M-1\n");
		bw.write("@"+label+"\n");
		bw.write("D;JNE\n");
	}
	
	public void writeCall(String functionName,int numArgs) throws IOException{
		
		//push return address
		bw.write("//!-call "+functionName+" "+numArgs+"\n");
		bw.write("@returnAddress"+countReturnAddress+"\n");
		bw.write("D=A\n");
		bw.write("@SP\n");
		bw.write("AM=M+1\n");
		bw.write("A=A-1\n");
		bw.write("M=D\n");
		
		//push and save LCL,ARG,THIS,THAT
		bw.write("@LCL\n");
		writePushSave();
		bw.write("@ARG\n");
		writePushSave();
		bw.write("@THIS\n");
		writePushSave();
		bw.write("@THAT\n");
		writePushSave();
		
		//move ARG
		bw.write("@"+numArgs+"\n");
		bw.write("D=A\n");
		bw.write("@5\n");
		bw.write("D=D+A\n");
		bw.write("@SP\n");
		bw.write("D=M-D\n");
		bw.write("@ARG\n");
		bw.write("M=D\n");
		
		//move LCL
		bw.write("@SP\n");
		bw.write("D=M\n");
		bw.write("@LCL\n");
		bw.write("M=D\n");
		
		//goto func
		bw.write("@"+functionName+"\n");
		bw.write("0;JMP\n");
		
		//label return address
		bw.write("(returnAddress"+countReturnAddress+")\n");
		countReturnAddress++;
	}
	
	public void writeReturn() throws IOException{
		bw.write("//!-return\n");
		
		//store LCL address in R13
		bw.write("@LCL\n");
		bw.write("D=M\n");
		bw.write("@R13\n");
		bw.write("M=D\n");
		
		//store return address in R14
		bw.write("@5\n");
		bw.write("D=A\n");
		bw.write("@LCL\n");
		bw.write("A=M-D\n");
		bw.write("D=M\n");
		bw.write("@R14\n");
		bw.write("M=D\n");
		
		//move return for call, reset SP of call
		bw.write("@SP\n");
		bw.write("A=M-1\n");
		bw.write("D=M\n");
		bw.write("@ARG\n");
		bw.write("A=M\n");
		bw.write("M=D\n");
		bw.write("D=A\n");
		bw.write("@SP\n");
		bw.write("M=D+1\n");
		
		//reset THAT,THIS,ARG,LCL of call
		writeReset("THAT");
		writeReset("THIS");
		writeReset("ARG");
		writeReset("LCL");
		
		//goto return address
		bw.write("@R14\n");
		bw.write("A=M\n");
		bw.write("0;JMP\n");
	}
	
	public void writeFunction(String functionName, int numLocals) throws IOException{
		bw.write("//!-function "+functionName+" "+numLocals+"\n");
		bw.write("("+functionName+")\n");
		bw.write("@LCL\n");
		bw.write("A=M\n");
		
		for(int i = 0;i<numLocals;i++){
			bw.write("M=0\n");
			bw.write("A=A+1\n");
		}
		bw.write("D=A\n");
		bw.write("@SP\n");
		bw.write("AM=D\n");
	}
	
	public void writePushSave() throws IOException{
		bw.write("D=M\n");
		bw.write("@SP\n");
		bw.write("AM=M+1\n");
		bw.write("A=A-1\n");
		bw.write("M=D\n");
	}
	
	public void writeReset(String var) throws IOException{
		bw.write("@R13\n");
		bw.write("AM=M-1\n");
		bw.write("D=M\n");
		bw.write("@"+var+"\n");
		bw.write("M=D\n");
		
	}

	public void close() throws IOException {
		bw.close();
	}

}



