A_COMMAND = 0
C_COMMAND = 1
L_COMMAND = 2

class Parser:
    """Encapsulates access to the input code.
        Reads an assembly language command, prases it and 
        provides convenient access to to the command's components. 
        Also removes white space and comments."""

    def __init__(self, infile):
        """Opens the input file/stream and gets ready to parse it"""
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
        
    def reset(self):
        """Resets back to original state"""
        None

    def command_type(self):
        """Returns the type of the current command.
            A_COMMAND for @Xxx where Xxx is a symbol or number
            C_COMMAND for dest=comp;jump
            L_COMMAND for (xxx) where Xxx is symbol"""
        None

    def symbol(self):
        """Returns the symbol or decimal Xxx of the current command
            of type @Xxx or (Xxx). Should only be called when commandType
            is A_COMMAND or L_COMMAND"""
        None

    def dest(self):
        """Returns the destination mnemonic in the current C-command. Should be
           called only when commandType() is C_COMMAND"""
        None

    def comp(self):
        """Returns the comp mnemonic in the current C-command. Should be
           called only when commandType() is C_COMMAND"""
        None
            
    def jump(self):
        """Returns the jump mnemonic in the current C-command. Should be
           called only when commandType() is C_COMMAND"""
        None


