/*
 *Translates Hack assembly language 
 *      mnemonics into binary codes
 */
class CodeGenerator
{	
	public String literal;
	public String value;
	
    /*
     * Returns the binary code of the dest mnemonic (3 bits)
     */
    public static String dest(String mnemonic)
    {
        if(mnemonic.equals("M")){
        	return "001";
        }else if(mnemonic.equals("D")){
        	return "010";
        }else if(mnemonic.equals("MD")){
        	return "011";
        }else if(mnemonic.equals("A")){
        	return "100";
        }else if(mnemonic.equals("AM")){
        	return "101";
        }else if(mnemonic.equals("AD")){
        	return "110";
        }else if(mnemonic.equals("AMD")){
        	return "111";
        } else {
        	return "000";
        }
    }

    /*
     * Returns the binary code of the comp mnemonic (7 bits)
     */
    public static String comp(String mnemonic)
    {
    	if(mnemonic.equals("0")){
        	return "0101010";
        }else if(mnemonic.equals("1")){
        	return "0111111";
        }else if(mnemonic.equals("-1")){
        	return "0111010";
        }else if(mnemonic.equals("D")){
        	return "0001100";
        }else if(mnemonic.equals("A")){
        	return "0110000";
        }else if(mnemonic.equals("!D")){
        	return "0001101";
        }else if(mnemonic.equals("!A")){
        	return "0110001";
        }else if(mnemonic.equals("-D")){
        	return "0001111";
        }else if(mnemonic.equals("-A")){
        	return "0110011";
        }else if(mnemonic.equals("D+1")){
        	return "0011111";
        }else if(mnemonic.equals("A+1")){
        	return "0110111";
        }else if(mnemonic.equals("D+A")){
        	return "0000010";
        }else if(mnemonic.equals("D-A")){
        	return "0010011";
        }else if(mnemonic.equals("A-D")){
        	return "0000111";
        }else if(mnemonic.equals("D&A")){
        	return "0000000";
        }else if(mnemonic.equals("D|A")){
        	return "0010101";
        }else if(mnemonic.equals("M")){
        	return "1110000";
        }else if(mnemonic.equals("!M")){
        	return "1110001";
        }else if(mnemonic.equals("-M")){
        	return "1110001";
        }else if(mnemonic.equals("M+1")){
        	return "1110111";
        }else if(mnemonic.equals("M-1")){
        	return "1110010";
        }else if(mnemonic.equals("D+M")){
        	return "1000010";
        }else if(mnemonic.equals("D-M")){
        	return "1010011";
        }else if(mnemonic.equals("M-D")){
        	return "1000111";
        }else if(mnemonic.equals("D&M")){
        	return "1000000";
        }else if(mnemonic.equals("D|M")){
        	return "1010101";
        }else if(mnemonic.equals("D-1")){
        	return "0001110";
        }else {
        	return "0110010";
        }
    }

    /*
     * Returns the binary code of the jump mnemonic (3 bits)
     */
    public static String jump(String mnemonic)
    {
        if (mnemonic.equals("JGT")){
        	return "001";
        }else if (mnemonic.equals("JEQ")){
        	return "010";
        }else if (mnemonic.equals("JGE")){
        	return "011";
        }else if (mnemonic.equals("JLT")){
        	return "100";
        }else if (mnemonic.equals("JNE")){
        	return "101";
        }else if (mnemonic.equals("JLE")){
        	return "110";
        }else if (mnemonic.equals("JMP")){
        	return "111";
        }else {
        	return "000";
        }
    }

    /*
     * Returns the binary code of the literal (15 bits)
     */
    public static String literal(int value)
    {
    	StringBuilder sb = new StringBuilder();
        String temp = Integer.toBinaryString(value);
        for (int i =16-temp.length();i>0;i--){
        	sb.append('0');
        }
        return sb.append(temp).toString();
        
        
    }
}        