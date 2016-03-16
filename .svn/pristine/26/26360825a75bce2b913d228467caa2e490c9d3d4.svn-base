class VMWriter:
    def __init__(self, output, debug=False):
        self.out = open(output, 'w')
        self.code = []
        self.debug = debug
        
    def add_line(self, s):
        self.code.append(s)
        if self.debug:
            print s
            
    def write_comment(self, s):
        self.add_line('  // ' + s)

    def write_push(self, segment, index):
        self.add_line('  push %s %d'%(segment, index))

    def write_pop(self, segment, index):
        self.add_line('  pop %s %d'%(segment, index))

    def write_arithmetic(self, command):
        self.add_line('  %s'%command)
 
    def write_label(self, label):
        self.add_line('label %s'%label)

    def write_goto(self, label):
        self.add_line('  goto %s'%label)

    def write_if(self, label):
        self.add_line('  if-goto %s'%label)

    def write_call(self, name, nargs):
        self.add_line('  call %s %s'%(name, nargs))

    def write_function(self, name, nlocals):
        self.add_line('function %s %d'%(name, nlocals))

    def write_return(self):
        self.add_line('  return')

    def close(self):
        for s in self.code:
            self.out.write(s + '\n')
        self.out.close()



