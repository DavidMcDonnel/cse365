// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[3], respectively.)

// Put your code here.

//initialize countdown (i)
	@1
	D=M
	@countdown
	M=D

//initializes memory for answer (R2)
	@2
	M=0
(LOOP)
	//test if countdown equals 0
	@countdown
	D=M
	@END
	D;JEQ //D still equals countdown

	//if test fails countdown-=1
	@1
	D=D-A //D = countdown - 1
	@countdown
	M=D

	//test failed, increment sum by R0
	@2
	D=M
	@0
	D=D+M //D=sum+R0
	@2
	M=D
	
	@LOOP
	0;JMP
(END)
	@END
	0;JMP
