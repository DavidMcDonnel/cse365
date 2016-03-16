#!/usr/bin/python

import os, os.path
import sys
from jack_parser import *
from jack_tokenizer import to_xml
from utils import *

def test(filename):
    """Checks to see if the jack file specified by filename
       tokenizes correctly."""
    print filename
    folder = os.path.dirname(filename)
    base = os.path.basename(filename)
    core = os.path.splitext(base)[0]
    
    t_xml = to_xml(filename).strip()

    t_xml_ref = read_whole_file(folder + os.sep + core + 'T.xml')
    dump_to_file(folder + os.sep + 'my' + core + 'T.xml', t_xml)
    print '\tTokenized Correctly?', t_xml == t_xml_ref

if __name__=='__main__':
    """Trys to tokenize all of the Jack files listed in the arguments
      The arguments can either be folder names or specific Jack files""" 
    for fn in get_jack_files(sys.argv[1:]):
        test(fn)

