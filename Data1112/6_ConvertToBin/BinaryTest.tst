load,  // Load all the VM files from the current directory.
output-file BinaryTest.out,
compare-to BinaryTest.cmp,
output-list RAM[8000]%D1.6.1 RAM[8001]%D1.6.1 RAM[8002]%D1.6.1 RAM[8003]%D1.6.1 RAM[8004]%D1.6.1 RAM[8005]%D1.6.1 RAM[8006]%D1.6.1 RAM[8007]%D1.6.1 RAM[8008]%D1.6.1;

set RAM[8000] 42,

repeat 90000 {
  vmstep;
}

output;

