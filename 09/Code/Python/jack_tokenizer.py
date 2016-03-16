from jack_types import *
from utils import replace_entities
SYMBOLS = "{}()[].,;+-*/^|<>=~&"
WHITE = '\r\n\t '

class JackTokenizer:
    def __init__(self, filename):
        """Opens the input file/stream and gets ready to tokenize it"""
        self.data = open(filename).read()
        
    def has_more_tokens(self):
        """Do we have more tokens in the input"""
        return False
        
    def advance(self):
        """Gets the next token from the input and makes it the current token.
            Should only be called if hasMoreTokens() is true. 
            Initially there is no current token."""
        None

    def peek(self):
        """ Returns the next character to be tokenized"""
        if self.i + 1 < len(self.data):
            return self.data[self.i + 1]
        else:
            return None
        
    def token_type(self):
        """Returns the type of the current token."""
        return None
        
    def token_value(self):
        """Returns the string of the current token """
        return None
        
def to_xml(filename):
    """ Tokenizes the specified file and returns 
        the xml representation"""
    jt = JackTokenizer(filename)
    s = '<tokens>\n'
    while jt.has_more_tokens():
        jt.advance()
        value = replace_entities(jt.token_value())
        s += "<%s> %s </%s>\n"%(jt.token_type(), value, jt.token_type())
    s+= '</tokens>\n'
    return s


import sys
if __name__=='__main__':
    s = to_xml(sys.argv[1])
    print s
