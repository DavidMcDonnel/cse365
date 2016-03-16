
def replace_entities(s):
    return s.replace('&', '&amp;').replace('<', '&lt;').replace('>', '&gt;')        

def read_whole_file(filename):
    return open(filename).read().replace(chr(13), '')
        
def dump_to_file(filename, contents):
    wf = open(filename, 'w')
    wf.write(contents)
    wf.close()


import os, os.path
def get_jack_files(args):
    jfiles = []
    for arg in args:
        if not os.path.isdir(arg):
            jfiles.append(arg)
        else:
            for root,dirs,files in sorted(os.walk(arg)):
                for fn in files:
                    if fn[-4:]=='jack':
                        jfiles.append(root + os.sep + fn)
    return jfiles
