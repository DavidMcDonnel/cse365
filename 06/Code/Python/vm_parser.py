# Command Types
C_ARITHMETIC = 1
C_PUSH = 2
C_POP = 3
# You do not need to worry about the following command types in assignment 7
C_LABEL = 4
C_GOTO = 5
C_IF = 6
C_FUNCTION = 7
C_RETURN = 8
C_CALL = 9

class Parser:
    """Handles the parsing of a single .vm file and 
        encapsulates access to the input code. Parses
        VM commands and removes white space and comments"""

    def __init__(self, stream):
        """Opens the input file"""
        None
                
    def has_more_commands(self):
        """Are there more commands in the input?"""
        return False

    def advance(self):
        """Moves to the next command. 
           Should only be called if has_more_commands() is True
           Initially there is no current command"""
        None
        
    def get_current_command(self):
        """Returns the current command (string)"""
        None
        
    def command_type(self):
        """Returns the type of the current command.
            In Assignment 7, you need only recognize
                C_ARITHMETIC, C_PUSH and C_POP.
            In Assignment 8, you will need to also recognize
                C_LABEL, C_GOTO, C_IF, C_FUNCTION, C_RETURN, C_CALL"""
        None

    def arg1(self):
        """Returns the first argument of the current command as a string.
            For C_ARITHMETIC, the command itself is returned. 
            Should not be called if type is C_RETURN."""
        None

    def arg2(self):
        """Returns the second argument of the current command as an integer.
            Should only be called if the current type is 
               C_PUSH, C_POP, C_FUNCTION or C_CALL"""
        None

