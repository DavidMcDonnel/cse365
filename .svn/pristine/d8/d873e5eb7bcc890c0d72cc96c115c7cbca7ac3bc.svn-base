import java.io.File;
import java.io.IOException;

public class BigAssignment10Test {
    /* Checks to see if the jack file specified by filename
     * tokenizes and parses correctly.
     */
    public static void test(String filename) throws IOException {
        System.out.println(filename);
        File f = new File(filename);

        String folder = f.getParent();
        String base = Util.basename(filename);

        String t_xml = JackTokenizer.toXml(filename).trim();

        String t_ref = Util.readWholeFile(folder + File.separator + base
                + "T.xml");

        Util.dumpToFile(folder + File.separator + "my" + base + "T.xml", t_xml);

        System.out.println("\tTokenized Correctly? " + t_xml.equals(t_ref));

        JackParser parser = new JackParser(filename);
        SyntaxTreeNode n = parser.parseClass();
        String p_xml = n.toString().trim();

        Util.dumpToFile(folder + File.separator + "my" + base + ".xml", p_xml);

        String p_ref = Util.readWholeFile(folder + File.separator + base
                + ".xml");
        p_ref = p_ref.replaceAll("" + (char) 13, "");

        System.out.println("\tParsed Correctly?    " + p_xml.equals(p_ref));
    }

    /* 
     * Trys to parse all of the Jack files listed in the arguments
     * The arguments can either be folder names or specific Jack files 
     */
    public static void main(String[] args) {
        for (String file : Util.getJackFiles(args)) {
            try {
                test(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
