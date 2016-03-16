from vm_parser import *

class CodeWriter:
    """Translates VM commands into Hack assembly"""
    
    def __init__(self, filename):
        """Opens the output file and gets ready to write into it"""
        None
        
    def set_filename(self, s):
        """Informs the code writer that the translation 
            of a new VM file is started."""
        None
        
    def write_arithmetic(self, command):
        """Writes the assembly code that is the translation of
            the given arithmetic command"""
        None
        
    def write_push_pop(self, command, segment, index):
        """Writes the assembly code that is the translation of 
            the given C_PUSH or C_POP command.
            command is either C_PUSH or C_POP.
            segment is a string.
            index is an integer"""
        None
        
    def close(self):
        None
