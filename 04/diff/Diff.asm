// For a given function f(x), calculates some values of f(x)
// using the Differential Engine method discussed in class
// 
// f(x) = a x^n + b x^(n-1)...k x^0
// R0 == n
// R1 == x_max
// R2 == x_0
// R3 == f(x_0)
// R4 == f'(x_0) = f(x_0)-f(x_0-1)
// ...
// R(n+3) == nth difference
//
// Assume n <= 4
//
// You can change the values in the registers as you execute
// 
// By the end, output all the values of x and f(x) you've calculated
// up to and including x_max and f(x_max). Output them by placing
// them in memory at and after RAM[100].
//
// Specifically 
// At the end, be sure that
// RAM[100] = x_0+1
// RAM[101] = f(x_0+1)
// RAM[102] = x_0+2
// RAM[103] = f(x_0+2)
// ...
// RAM[100 + (x_max-x_0-1) * 2] = x_max
// RAM[101 + (x_max-x_0-1) * 2] = f(x_max)
//
// File name: projects/04/Diff.asm

// Put your code here. 

	//initialize differences for f prime - f 4
(INITIALIZE)
	
	//get degree
	@0
	D=M
	@degree
	M=D

	//get max
	@1
	D=M
	@max
	M=D
	
	//get x
	@2
	D=M
	@x
	M=D
	
	//get y
	@3
	D=M
	@y
	M=D

	//get starting RAM address
	@100
	D=A
	@currentAddress
	M=D
	
	@4
	D=M
	@diffFirst
	M=D

	@5
	D=M
	@diffSecond
	M=D

	@6
	D=M
	@diffThird
	M=D

	@7
	D=M
	@diffFourth
	M=D
	
	//initialize iterators
	@0
	D=A
	@i
	M=D
	@j
	M=D
	@k
	M=D
	@l
	M=D
	
	@THREE
	0;JMP

(ONE)
	
	//iterator test and incrementation
	@i
	D=M
	@max
	D=M-D
	@YLOOP
	D;JEQ
	
	@2
	D=A
	@i
	M=M+D
	
	//diffFirst+= diffSecond
	@diffSecond
	D=M
	@diffFirst
	M=M+D
	@YLOOP
	0;JMP

(TWO)
	//iterator test and incrementation
	@j
	D=M
	@max
	D=M-D
	@ONE
	D;JEQ

	@4
	D=A
	@j
	M=M+D

	//diffSecond+=diffThird
	@diffThird
	D=M
	@diffSecond
	M=M+D
	@ONE
	0;JMP

(THREE)
	//iterator test and incrementation
	@k
	D=M
	@max
	D=M-D
	@TWO
	D;JEQ
	
	@8
	D=A
	@k
	M=M+D
	
	//diffThird+=diffFourth
	@diffFourth
	D=M
	@diffThird
	M=M+D
	@TWO
	0;JMP
		
	
(YLOOP)
	//iterator decrementation
	@max
	D=M
	@END
	D;JEQ
	
	@1
	D=D-A
	@max
	M=D
	
	//current ram = current x; x++,address++
	@x
	D=M
	@currentAddress
	M=D
	@1
	D=A
	@x
	M=M+D
	@currentAddress
	M=M+D
	
	//y+=diffFirst, current address=y, address++
	@y
	D=M
	@currentAddress
	M=D
	@1
	D=A
	@currentAddress
	M=M+D
	@y
	D=M
	@diffFirst
	D=D+A
	@y
	M=D	
	
	@THREE
	0;JMP

(END)
	@END
	0;JMP
