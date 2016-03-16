import java.io.IOException;

public class JackTokenizer {
    private String input;
    private Token token;

    /*
     * Opens the input file/stream and gets ready to tokenize it 
     */
    public JackTokenizer(String filename) throws IOException {
        input = Util.readWholeFile(filename);
        token = null;
    }

    /* Do we have more tokens in the input */
    public boolean hasMoreTokens() {
        return false;
    }

    /*
     * Gets the next token from the input and makes it the current token.
     *      Should only be called if hasMoreTokens() is true. 
     *      Initially there is no current token.
     */
    public void advance() {
        
	
    }

    /* 
     * Returns the next char to be tokenized
     */
    public char peek() {
        // Insert your code here
        return 0;
    }

    /*
     * Returns the current token
     */
    public Token getToken() {
        return token;
    }

    /*
     * Tokenizes the specified file and returns 
     *  the xml representation
     */
    public static String toXml(String filename) {
        JackTokenizer jt = null;
        try {
            jt = new JackTokenizer(filename);
        } catch (IOException ie) {

        }
        StringBuffer sb = new StringBuffer("<tokens>\n");

        while (jt.hasMoreTokens()) {
            jt.advance();
            Token t = jt.getToken();
            String s = Util.replaceEntities(t.token);
            sb.append("<");
            sb.append(t.type);
            sb.append("> ");
            sb.append(s);
            sb.append(" </");
            sb.append(t.type);
            sb.append(">\n");
        }
        sb.append("</tokens>\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(toXml(args[0]));
    }
}
