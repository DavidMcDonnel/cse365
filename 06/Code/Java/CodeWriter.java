import java.io.*;

/*Translates VM commands into Hack assembly*/
public class CodeWriter {

	public BufferedWriter bw;
	private String currentFile;
	private int countLT = 0;
	private int countGT = 0;
	private int countEQ = 0;
	
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
			bw.write("A=A-1\n");
			bw.write("M=D+M\n");
			bw.write("D=A+1\n");
			bw.write("@SP\n");
			bw.write("M=D\n");
			bw.write("A=M-1\n");
			bw.write("D=M\n");
			
		}else if(command.equals("sub")){
			bw.write("A=A-1\n");
			bw.write("M=M-D\n");
			bw.write("D=A+1\n");
			bw.write("@SP\n");
			bw.write("M=D\n");
			bw.write("A=M-1\n");
			bw.write("D=M\n");
			
		}else if(command.equals("neg")){
			bw.write("D=-D\n");
			bw.write("M=D\n");
			
		}else if(command.equals("eq")){
			
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
			
			bw.write("A=A-1\n");
			bw.write("M=M&D\n");
			bw.write("D=A+1\n"); 
			bw.write("@SP\n");
			bw.write("M=D\n");
			bw.write("A=M-1\n");
			bw.write("D=M\n");
			
		}else if(command.equals("or")){
			
			bw.write("A=A-1\n");
			bw.write("M=M|D\n");
			bw.write("D=A+1\n"); 
			bw.write("@SP\n");
			bw.write("M=D\n");
			bw.write("A=M-1\n");
			bw.write("D=M\n");
			
		}else{
			
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
			if(segment.equals("constant")){
				
				bw.write("@"+index+"\n");
				bw.write("D=A\n");
				bw.write("@SP\n");
				bw.write("A=M\n");
				bw.write("M=D\n");
				bw.write("@SP\n");
				bw.write("AM=M+1\n");
				bw.write("A=A-1\n");
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

	public void close() throws IOException {
		bw.close();
	}

}


