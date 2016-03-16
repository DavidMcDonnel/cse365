import os, os.path
import sys
import collections
from jack_parser import *
from symbol_table import *
from vm_writer import *
from utils import *

class Compiler:
    def __init__(self, infile, outfile, debug=False):
        c = JackParser(infile)
        self.root = c.parse_class()
        self.st = SymbolTable()
        self.vm = VMWriter(outfile, debug)
        self.class_name = None
        self.label_i = collections.defaultdict(int)
        
    def generate_code(self, node=None):
        if node is None:
            node = self.root
            
        if node.type == 'class':
            None
        else:
            print "Unhandled type:", node.type
            
    def get_label(self, keyword='label'):
        i = self.label_i[keyword]
        l = "%s%d"%(keyword, i)
        self.label_i[keyword]+=1
        return l
            
    def close(self):
        self.vm.close()
        
def compile_it(filename, outfile=None, debug=False):
    if outfile is None:
        
        folder = os.path.dirname(filename)
        base = os.path.basename(filename)
        core = os.path.splitext(base)[0]
        
        outfile = folder + os.sep + 'my' + core + '.vm'
        
    c = Compiler(filename, outfile, debug)
    c.generate_code()
    c.close()
    
    



if __name__=='__main__':
    debug = '-d' in sys.argv
    
    for fn in get_jack_files(sys.argv[1:]):
        if fn == '-d':
            continue
        compile_it(fn, debug=debug)

