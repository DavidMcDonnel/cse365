from parser import *
from code_generator import CodeGenerator
from symbol_table import SymbolTable
import sys, os.path

def assemble(infile, outfile):
    p = Parser(infile)
    st = SymbolTable()
    out = open(outfile, 'w')
    
    # Insert code here
    
    out.close()

    
if __name__=='__main__':
    if len(sys.argv)!=2:
        print 'Usage: %s infile.asm'%sys.argv[0]
        exit(0)
    infile = sys.argv[1]
    outfile = os.path.splitext( infile )[0] + '.hack'
    assemble(infile, outfile)

