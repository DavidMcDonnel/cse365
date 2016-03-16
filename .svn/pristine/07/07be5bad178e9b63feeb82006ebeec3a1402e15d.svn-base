from vm_parser import *
from code_writer import CodeWriter
import sys, os.path

def translate(vmfiles, asmfile):
    w = CodeWriter(asmfile)
    
    for fn in vmfiles:
        print 'Parsing %s...'%fn
        p = Parser(fn)
        w.set_filename( os.path.splitext( os.path.split(fn)[-1] )[0] )
    
        # Insert Code Here
            
    print 'Writing %s...'%asmfile
    w.close()

def is_vm_file(fn):
    return fn[-3:]=='.vm'

if __name__=='__main__':
    if len(sys.argv)!=2:
        print 'Usage:    %s infile.vm'%sys.argv[0]
        print '       or %s folder'
        exit(0)


    if not os.path.isdir(sys.argv[1]):
        fn = os.path.abspath(sys.argv[1])
        asmfile = os.path.splitext(fn)[0] + '.asm'
        translate([fn], asmfile)
    else:
        for root,dirs,files in os.walk(sys.argv[1]):
            folder = os.path.abspath(root)
            valid_files = []
            for fn in filter(is_vm_file, files):
                new_fn = os.path.join( folder, fn )
                valid_files.append(new_fn)

            if len(valid_files)==0:
                continue
            
            asmfile = os.path.join(folder, '%s.asm'%os.path.basename(folder))
            translate(valid_files, asmfile)

