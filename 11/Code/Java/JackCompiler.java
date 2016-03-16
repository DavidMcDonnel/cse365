//package compiler;
import java.io.File;
import java.util.HashMap;
import java.util.Vector;

public class JackCompiler {
    private JackSymbolTable st;
    private VMWriter vm;
    private String className;

    private HashMap<String, Integer> labels;

    public JackCompiler(String infile, String outfile, boolean debug)
            throws Exception {
        JackParser p = new JackParser(infile);
        SyntaxTreeNode root = p.parseClass();
        vm = new VMWriter(outfile, debug);
        st = new JackSymbolTable();
        labels = new HashMap<String, Integer>();

        className = null;
        labels = new HashMap<String, Integer>();

        generateCode(root);

        vm.close();
    }

    public void generateCode(SyntaxTreeNode node) {
        switch (node.getType()) {
            case CLASS:
                classHelper(node);
                break;
            case CLASS_VAR_DEC:
      //          classHelper(node);
                break;
            case TYPE:
      //          classHelper(node);
                break;
            case SUBROUTINE_DEC:
                subroutineDecHelper(node);
                break;
            case PARAMETER_LIST:
      //          classHelper(node);
                break;
            case VAR_DEC:
      //          classHelper(node);
                break;
            case STATEMENTS:
                stmtHelper(node);
                break;
            case DO_STMT:
                doHelper(node);
                break;
            case LET_STMT:
      //          classHelper(node);
                break;
            case WHILE_STMT:
      //          classHelper(node);
                break;
            case RETURN_STMT:
                returnStmtHelper(node);
                break;
            case IF_STMT:
      //          classHelper(node);
                break;
            case EXPRESSION:
                expressionHelper(node);
                break;
            case TERM:
                termHelper(node);
                break;
            case BRACKETS:
      //          classHelper(node);
                break;
            case EXPRESSION_LIST:
                expressionListHelper(node);
                break;
            case SUBROUTINE_CALL:
                subroutineCallHelper(node);
                break;
            case TOKEN:
      //          classHelper(node);
                break;
            default:
                System.err.println("Unhandled type " + node.getType());
                break;
        }

    }

    private void classHelper(SyntaxTreeNode node){
        className = node.getChildren().elementAt(1).getToken().toString();
        for (int i = 3;i<node.getChildren().size();i++){
            generateCode(node.getChildren().elementAt(i));
        }
    }

    private void subroutineDecHelper(SyntaxTreeNode node){
        vm.write_function(className+"."+node.getChildren().elementAt(2).getToken(), 0);
        generateCode(node.getChildren().elementAt(7));
    }

    private void subroutineCallHelper(SyntaxTreeNode node){
        String temp = "";
        boolean expressionList = false;
        st.startSubroutine();
        for (SyntaxTreeNode n : node.getChildren()){
            if(!expressionList){
                if(n.getToken().toString().equals("(")){
                    expressionList = true;
                    continue;
                }
            }
            if(!expressionList){
                temp += n.getToken().toString();
            }else{
                generateCode(n);
                break;
            }
        }
        vm.write_call(temp, st.varCount(3)); //COULD BE WRONG
    }

    private void expressionListHelper(SyntaxTreeNode node){
        generateCode(node.getChildren().firstElement());
    }

    private void expressionHelper(SyntaxTreeNode node){
        Vector<String> operations = new Vector<String>();
        if(node.getChildren().size()>1){
            for (SyntaxTreeNode n : node.getChildren()){
                if (n.getChildren().size()>1){
                    generateCode(n);
                }else{
                    if(n.getType().equals(NodeType.TOKEN)){
                        operations.add(n.getToken().toString());
                    }else{
                        generateCode(n.getChildren().firstElement());
                    }
                }
            }
            String temp="";
            for(int i = operations.size();i>0;i--){
                temp =operations.get(i);
                if(temp.equals("+")){
                    vm.writeArithmetic("add");
                }else if(temp.equals("-")){
                    vm.writeArithmetic("sub");
                }else if(temp.equals("&")){
                    vm.writeArithmetic("and");
                }else if(temp.equals("~")){
                    vm.writeArithmetic("not");
                }else if(temp.equals("|")){
                    vm.writeArithmetic("or");
                }else if(temp.equals("*")){
                    vm.writeArithmetic("Math.multiply 2");
                }else if(temp.equals("/")){
                    vm.writeArithmetic("Math.divide 2");
                }
            }
        }else if (node.getChildren().size()==0){
            generateCode(node.getChildren().firstElement());
        }
    }

    private void termHelper(SyntaxTreeNode node){
        Token n = node.getChildren().firstElement().getToken();
        st.define(n.toString(), n.type.toString(), 3);
        if(n.type.equals(TokenType.INT)){
            vm.writePush("constant", Integer.parseInt(n.toString()));
        }else if(n.type.equals(TokenType.STRING)){
            vm.writePush("constant", st.varCount(3)); //FIX ME
        }

    }

    private void returnStmtHelper(SyntaxTreeNode node){
        vm.writeReturn();
    }

    private void stmtHelper(SyntaxTreeNode node){
        for(SyntaxTreeNode n : node.getChildren()){
            if (n.getType().equals(NodeType.DO_STMT)){
                generateCode(n);
            }
            if (n.getType().equals(NodeType.RETURN_STMT)){
                vm.writePush("constant",0);
                generateCode(n);
            }
        }
    }
    private void doHelper(SyntaxTreeNode node){
        generateCode(node.getChildren().elementAt(1));
        vm.writePop("temp", 0);
    }

    private String getLabel(String keyword) {
        if (!labels.containsKey(keyword)) {
            labels.put(keyword, 0);
        }

        int index = labels.get(keyword);
        String label = keyword + index;
        labels.put(keyword, index + 1);
        return label;
    }

    public static void compile(String filename, boolean debug) throws Exception {
        System.out.println("Compiling " + filename);
        File f = new File(filename);

        String folder = f.getParent();
        String base = Util.basename(filename);

        String outfile = folder + File.separator + "my" + base + ".vm";

        compile(filename, outfile, debug);
    }

    public static void compile(String infile, String outfile, boolean debug)
            throws Exception {
        new JackCompiler(infile, outfile, debug);
    }

    public static void main(String[] args) {
        boolean debug = false;
        for (String arg : args) {
            if (arg.equals("-d"))
                debug = true;
        }

        for (String file : Util.getJackFiles(args)) {
            try {
                compile(file, debug);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
