import java.io.File;
import java.io.IOException;
import java.util.Vector;

class VMTranslator {
	public static void translate(Vector<String> files, String asmfile) {
		try {
			CodeWriter w = new CodeWriter(asmfile);

			for (String filename : files) {
				System.out.println("Parsing " + filename + "...");
				VMParser parser = new VMParser(filename);

				String basename = basename((new File(filename)).getName());
				w.setFilename(basename);

				// Insert Code Here
				while(parser.hasMoreCommands()){
					parser.advance();
					if(parser.getCurrentCommand().equals("")){
						continue;
					}
					VMCommandType commandType = parser.commandType();
					if(commandType.equals(VMCommandType.C_PUSH) || commandType.equals(VMCommandType.C_POP)){
						w.writePushPop(commandType, parser.arg1(),parser.arg2());
					}else if(commandType.equals(VMCommandType.C_ARITHMETIC)){
						w.writeArithmetic(parser.arg1());
					}
				}
			}

			System.out.println("Writing " + asmfile + "...");
			w.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage:    java VMTranslator infile.vm");
			System.err.println("       or java VMTranslator folder");
			return;
		}

		File f = new File(args[0]);

		if (f.exists() && f.isDirectory()) {
			walk(args[0]);
		} else {
			String infile = args[0];
			String asmfile = basename(infile) + ".asm";
			Vector<String> files = new Vector<String>();
			files.add(infile);
			translate(files, asmfile);
		}

	}

	private static void walk(String path) {

		File root = new File(path);
		File[] list = root.listFiles();

		if (list == null)
			return;

		Vector<String> vmfiles = new Vector<String>();

		for (File f : list) {
			if (f.isDirectory()) {
				walk(f.getAbsolutePath());
			} else {
				String abspath = f.getAbsolutePath();
				if (abspath.contains(".vm")) {
					vmfiles.add(abspath);
				}
			}
		}

		if (vmfiles.size() == 0)
			return;

		String asmfile = root.getAbsolutePath() + File.separator
				+ root.getName() + ".asm";
		translate(vmfiles, asmfile);

	}

	private static String basename(String path) {
		int dot = path.lastIndexOf('.');
		return ((dot == -1) ? path : path.substring(0, dot));
	}
}

