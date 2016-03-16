// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, the
// program clears the screen, i.e. writes "white" in every pixel.

// Put your code here.

//checkinput is the start of our programwide loop
(CHECKINPUT)
	//store screen address in pixel variable
	@SCREEN
	D=A
	@pixel
	M=D

	//if key pressed (kbd=1) goto black
	@KBD
	D=M
	@BLACK
	D;JNE
	
	//else (D=0)
(WHITE)
	@0
	D=A
	@COLORSET
	0;JMP

	//D = -1
(BLACK)
	@0
	D=A-1
	
	//color = D

(COLORSET)
	@color
	M=D

(LOOP)
	//make pixel this color (white or black)
	@color
	D=M
	@pixel
	A=M
	M=D

	//increment pixel
	@pixel
	M=M+1
	D=M

	//keyboard starts 8 kibibytes after screen
	//screen is represented by an 8 kibibyte memory map
	//thus KBD is first address after SCREEN
	
	//if end of screen, CHECKINPUT, else LOOP
	@KBD
	D=D-A //D=pixel address - KBD address
	@CHECKINPUT
	D;JEQ

	@LOOP
	0;JMP

(END)
	@END
	0;JMP
