import java.io.File;
import java.io.IOException;

public class BigAssignment9Test {
    /* Checks to see if the jack file specified by filename
     * tokenizes correctly.
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
    }

    /* 
     * Trys to tokenize all of the Jack files listed in the arguments
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
