//package compiler;
import java.util.Hashtable;

public class JackSymbolTable
{

  private class Identifier{
    private String type;
    private Scope kind;
    private int index;

    public Identifier(String t,int i, int k){
      type = t;
      index = i;
      switch (k){
        case 0://static
          kind =Scope.STATIC;
        case 1://field
          kind =Scope.FIELD;
        case 2://var
          kind =Scope.VAR;
        case 3://arg
          kind =Scope.ARGUMENT;
      }
    }
  }

  private Hashtable<String,Identifier> classTable,subroutineTable;
  private int staticIndex,fieldIndex,argIndex,varIndex;

  public JackSymbolTable()
  {
    classTable = new Hashtable<String,Identifier>();
    subroutineTable = new Hashtable<String,Identifier>();
  }

  public void startSubroutine()
  {
    subroutineTable = new Hashtable<String,Identifier>();
    argIndex = 0;
    varIndex = 0;
  }

  public void define(String n, String t, int k)
  {
    Identifier newDef;
    switch (k)
    {
      case 0: //static
        newDef = new Identifier(t,staticIndex++,k);
        staticIndex++;
        subroutineTable.put(n,newDef);
      case 1://field
        newDef = new Identifier(t,fieldIndex++,k);
        fieldIndex++;
        subroutineTable.put(n,newDef);
      case 2://var
        newDef = new Identifier(t,varIndex++,k);
        varIndex++;
        subroutineTable.put(n,newDef);
      case 3://arg
        newDef = new Identifier(t,argIndex++,k);
        argIndex++;
        subroutineTable.put(n,newDef);
    }
  }

  public int varCount(int k)
  {
    switch(k)
    {
      case 0:
        return staticIndex;
      case 1:
        return fieldIndex;
      case 2:
        return varIndex;
      case 3:
        return argIndex;
      default:
        return -1;
    }
  }

  public Scope kindOf(String name)
  {
    if(subroutineTable.containsKey(name))
    {
      return subroutineTable.get(name).kind;
    }else
    {
      return classTable.get(name).kind;
    }
  }

  public String TypeOf(String name)
  {
    if(subroutineTable.containsKey(name))
    {
      return subroutineTable.get(name).type;
    }else
    {
      return classTable.get(name).type;
    }
  }

  public int IndexOf(String name)
  {
    if(subroutineTable.containsKey(name))
    {
      return subroutineTable.get(name).index;
    }else
    {
      return classTable.get(name).index;
    }
  }
}