import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Assembler
{
    public static void assemble(String infile, String outfile)
    {
        Parser p = new Parser(infile);
        SymbolTable st = new SymbolTable();
        int count = 0;
        
        File output = new File(outfile);
        FileWriter fw = null;
        try{
        	fw = new FileWriter(output.getAbsoluteFile());
        } catch(IOException e){
        	e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        
        while (p.hasMoreCommands()){
        	if(p.commandType()==Parser.CommandType.L_COMMAND){
        		st.addEntry(p.symbol(),count);
        	}
        	else count++;
        	p.advance();
        }
        p.lineCount=0;
        
        while(p.hasMoreCommands()){
        	String temp = p.symbol();
        	if(p.commandType()==Parser.CommandType.A_COMMAND){
        		if(temp.matches("-?\\d+(\\.\\d+)?")){
        			int xxx = Integer.parseInt(temp);
	        		try{
	        			bw.write(CodeGenerator.literal(xxx)+'\n');
	        		} catch (IOException e){
	        			e.printStackTrace();
	        		}
	        	}else {
	    			if(!st.contains(temp)){
	    				st.addEntry(temp);
	    			}else{
	    				try{
	    					bw.write(CodeGenerator.literal(st.getAddress(temp))+'\n');
	    				}catch (IOException e){
	    					e.printStackTrace();
	    				}
	    			}
	    		}
        	}else{
        		String tempD = CodeGenerator.dest(p.dest());
        		String tempC = CodeGenerator.comp(p.comp());
        		String tempJ = CodeGenerator.jump(p.jump());
        		if(p.linesArray[p.lineCount].contains("=")){
        			String tempN = CodeGenerator.jump("NULL");
        			try{
        				bw.write("111"+tempC+tempD+tempN+'\n');
        			}catch (IOException e){
    					e.printStackTrace();
    				}
        		}else{
        			String tempN = CodeGenerator.dest("NULL");
        			try{
        				bw.write("111"+tempC+tempN+tempJ+'\n');
        			}catch (IOException e){
    					e.printStackTrace();
    				}
        		}
        	}
        	p.advance();
        }
        try{
        	bw.close();
        }catch (IOException e){
			e.printStackTrace();
		}
    }

    public static void main(String[] args)
    {
        if(args.length!=1)
        {
            System.err.println("Usage: java Assembler infile.asm");
            System.exit(0);
        }
        
        String infile = args[0];
        int dot = infile.lastIndexOf('.');
        String outfile = ((dot == -1) ? infile : infile.substring(0, dot) ) + ".hack";
        assemble(infile, outfile);
    }
}