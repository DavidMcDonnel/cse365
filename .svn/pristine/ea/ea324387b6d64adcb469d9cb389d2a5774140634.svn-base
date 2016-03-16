from jack_tokenizer import *
from syntax_tree_node import *

class JackParser:
    def __init__(self, filename):
        """ Initializes the parser by opening a tokenizer
            with the given filename."""
        self.jt = JackTokenizer(filename)
        self.jt.advance()
        self.error = None
        
    def is_done(self):
        """ Return true if we've parsed all of the tokens"""
        return not self.jt.has_more_tokens()        

    def consume_keyword(self, values=None):
        """ Consumes a Keyword token. If values is not None,
            this will only return if the token matches one of the listed values"""
        n = self.consume(T_KEYWORD)
        if n and values is not None:
            if n.token not in values:
                s = ','.join([KEYWORDS[a] for a in values])
                self.error = 'Keyword %s is not the expected value %s'%(n.token, s)
        return n
        
    def consume_symbol(self, values=None):
        """ Consumes a Symbol token. If values is not None,
            this will only return if the token matches one of the listed values"""
        n = self.consume(T_SYMBOL)
        if n and values is not None:
            if n.token not in values:
                self.error = 'Symbol is not the expected value %s'%str(values)
        return n

    def consume(self, t_type):
        """ Returns a SyntaxTreeNode representing the current token 
            if the token type matches t_type.
            If the type does not match, or we've previously encountered an error, 
            the function returns nothing. """
        if self.error is not None:
            return None
        if self.jt.token_type()!=t_type:
            self.error = "Token %s is not expected type %s (%s)"%(self.jt.token_value(), t_type, self.jt.token_type())
            return None

        n = SyntaxTreeNode(t_type, self.jt.token_value())
        # print self.jt.token_value()
        self.jt.advance()
        return n
        
    def parse_class(self):
        """Returns the syntax tree for complete class"""
        n = SyntaxTreeNode(N_CLASS)

        return n
    
    def parse_class_variable_declaration(self):
        n = SyntaxTreeNode(N_CLASS_VAR_DEC)

        return n
        
    def parse_type(self):
        n = SyntaxTreeNode(N_TYPE)

        return n
    
    def parse_subroutine(self):
        n = SyntaxTreeNode(N_SUBROUTINE_DEC)

        return n
    
    def parse_parameter_list(self):
        n = SyntaxTreeNode(N_PARAMETER_LIST)

        return n

    def parse_variable_declaration(self):
        n = SyntaxTreeNode(N_VAR_DEC)

        return n
        
    def parse_statements(self):
        n = SyntaxTreeNode(N_STATEMENTS)

        return n

    def parse_do(self):
        n = SyntaxTreeNode(N_DO_STMT)

        return n
    
    def parse_let(self):
        n = SyntaxTreeNode(N_LET_STMT)

        return n
    
    def parse_while(self):
        n = SyntaxTreeNode(N_WHILE_STMT)

        return n
    
    def parse_return(self):
        n = SyntaxTreeNode(N_RETURN_STMT)

        return n
    
    def parse_if(self):
        n = SyntaxTreeNode(N_IF_STMT)

        return n
    
    def parse_expression(self):
        n = SyntaxTreeNode(N_EXPRESSION)

        return n
    
    def parse_term(self):
        n = SyntaxTreeNode(N_TERM)
        
        return n
        
    def parse_brackets(self):
        n = SyntaxTreeNode(N_BRACKETS)
        
        return n
    
    def parse_expression_list(self):
        n = SyntaxTreeNode(N_EXPRESSION_LIST)

        return n
        
    def parse_subroutine_call(self):
        n = SyntaxTreeNode(N_SUBROUTINE_CALL)

        return n
        
        
import sys
if __name__=='__main__':
    p = JackParser(sys.argv[1])
    node = p.parse_class()
    print node
    print "All tokens parsed:",p.is_done()
