import java.io.File;
import java.io.IOException;

public class BigAssignment11Test {
    public static boolean test(String filename) throws IOException {
        System.out.println(filename);
        File f = new File(filename);

        String folder = f.getParent();
        String base = Util.basename(filename);

        String outputFile = folder + File.separator + "my" + base + ".vm";

        String code = "";
        try {
            JackCompiler.compile(filename, outputFile, false);
            code = Util.readWholeFile(outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String code_ref = Util.readWholeFile(folder + File.separator + base
                + ".vm");

        boolean result = equalVMCode(code, code_ref);

        if (!result) {
            String altFn = folder + File.separator + base + ".vm.alt";
            File falt = new File(altFn);
            if(falt.exists()){
                code_ref = Util.readWholeFile(altFn);
                result = equalVMCode(code, code_ref);
            }
        }

        System.out.println("\tCompiled Correctly? " + result);
        return result;

    }

    private static boolean equalVMCode(String s1, String s2) {
        s1 = stripComments(s1);
        s2 = stripComments(s2);
        String[] a1 = s1.split("\\s+");
        String[] a2 = s2.split("\\s+");
        if (a1.length != a2.length) {
            return false;
        }

        for (int i = 0; i < a1.length; i++) {
            if (!a1[i].equals(a2[i]))
                return false;
        }

        return true;
    }

    private static String stripComments(String s) {
        s = stripComments(s, "/*", "*/");
        return stripComments(s, "//", "\n");
    }

    private static String stripComments(String s, String start, String finish) {
        int i1, i2;
        int extra = finish.equals("\n") ? 0 : finish.length();
        while ((i1 = s.indexOf(start)) >= 0) {
            i2 = s.indexOf(finish, i1);
            s = s.substring(0, i1) + s.substring(i2 + extra);
        }
        return s;
    }

    public static void main(String[] args) {
        boolean all = true;
        for (String file : Util.getJackFiles(args)) {
            try {
                all &= test(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("All passed? " + all);
    }
}
