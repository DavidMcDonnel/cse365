class SymbolTable:
    """A symbol table that keeps a correspondence between
        symbolic labels and numeric addresses"""

    def __init__(self):
        """Creates the initial symbol table"""
        None
        
    def add_entry(self, symbol, address=None):
        """Adds the pair (symbol, address) to the table.
            If address is None, sets address to the next
            available memory location"""
        None

    def contains(self, symbol):
        """Does the symbol table contain the given symbol?
            Returns boolean"""
        None

    def get_address(self, symbol):
        """Returns the integer address associated with the symbol"""
        None

