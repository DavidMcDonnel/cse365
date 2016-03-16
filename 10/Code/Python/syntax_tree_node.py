from jack_types import *
from utils import replace_entities

class SyntaxTreeNode:
    """ This class represents the parse tree for 
        the code. Each node can either represent a 
        terminal type (i.e. a token) or it can represent
        a non-terminal with children. """
        
    def __init__(self, node_type=None, token=None):
        """ Constructor. node_type can either be a 
            non-terminal node type (N_*) or a 
            token type (T_*), which will require a token. """
        self.type = node_type
        self.token = token
        self.children = []
        
    def add_child(self, child):
        """ Adds a child node to this node """
        if self.token is not None:
            raise Exception( 'Can not add child to %s node which already has token %s'%(self.type, self.token) )
        self.children.append( child )    
        
    def add_children(self, children):
        """ Adds multiple children to this node """
        self.children += children
        
    def has_children(self):
        """ Return true if this is a non-terminal type"""
        return self.token==None
        
    def get_children(self):
        """ Return a list of the children """
        return children
                
    def __repr__(self):
        return self.to_xml()
        
    def to_xml(self, indent=0):
        if self is None:
            return ''
        s = (' ' * indent) + '<%s>'% self.type
        if self.has_children():
            s += '\n'
            for child in self.children:
                if child:
                    s += child.to_xml(indent+1)
            s += (' '*indent)
        else:
            s += replace_entities(' %s '% self.token ) 
        
        
        s += '</%s>\n'% self.type
        
        return s
