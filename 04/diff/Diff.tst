load Diff.hack,
output-file Diff.out;
compare-to Diff.cmp,
output-list RAM[100]%D2.6.2 
            RAM[101]%D2.6.2 
            RAM[102]%D2.6.2 
            RAM[103]%D2.6.2 
            RAM[104]%D2.6.2 
            RAM[105]%D2.6.2 ,
set RAM[0] 2,   // Highest Power
set RAM[1] 6,   // Max X
set RAM[2] 3,   // Initial X
set RAM[3] 13,  // f(x)
set RAM[4] 5,   // f'(x) = f(x)-f(x-1)
set RAM[5] 2 ;  // f''(x) = f'(x)-f'(x-1)

repeat 10000 {
  ticktock;
}

output;

