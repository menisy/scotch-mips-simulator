run:
Starting address is 0
File size is 9
putting in slot 0 instruction sll $t0 $t0 1
putting in slot 4 instruction add $t0 $t0 $t0
putting in slot 8 instruction sub $t1 $t0 $t0
putting in slot 12 instruction or $t0 $t0 $t0
putting in slot 16 instruction andi $t0 $t0 1
putting in slot 20 instruction sw $t0 12($t0)
putting in slot 24 instruction lw $t1 12($t0)
putting in slot 28 instruction addi $t1 $t1 1
putting in slot 32 instruction sltui $t2 $t1 -3
ALU First Operand set to Source Register File Destination ALU Register  Data 0
The PC MUX output is 0
Fetching instruction at 0
Data in wire From PC To PC_MUX set to  4
Input set in mux
the instruction is sll $t0 $t0 1
Setting the controls for sll
Data in wire From Control To ALU set to  6
Data in wire From Control To ALU_MUX set to  1
Data in wire From Control To REG_MUX set to  0
Select set to Source Control Destination ALU_MUX Register  Data 1
Select set to Source Control Destination REG_MUX Register  Data 0
Data in wire From Control To PC_MUX set to  0
Select set to Source Control Destination PC_MUX Register  Data 0
Data in wire From Instruction Memory To Register File set to  3
Data in wire From Instrucion Memory To ALU_MUX set to  1
Input set in mux
Setting first operand destination with register $t0
Data in wire From Register File To ALU set to  3
Select is Source Control Destination ALU_MUX Register  Data 1
ALU MUX is forwarding Source Instrucion Memory Destination ALU_MUX Register  Data 1
Select is Source Control Destination ALU_MUX Register  Data 1
ALU Second Operand set to Source Instrucion Memory Destination ALU_MUX Register  Data 1
Doing the operation 
Data in wire From ALU To REG_MUX set to  6
Input set in mux
writing in register file
Select is Source Control Destination REG_MUX Register  Data 0
Destination register is $t0 Destination data is 0
Value of Register  Source Instruction Memory Destination Register File Register $t0 Data 0 is now null
alu writing register
RegWrite became true
The result of the operation is 6
Select is Source Control Destination PC_MUX Register  Data 0
PC MUX is forwarding Source PC Destination PC_MUX Register  Data 4
Select is Source Control Destination PC_MUX Register  Data 0
Data in wire From PC_MUX To PC set to  4
The PC MUX output is 4
Fetching instruction at 4
Data in wire From PC To PC_MUX set to  8
Input set in mux
the instruction is add $t0 $t0 $t0
Setting the controls for add
Data in wire From Control To ALU set to  1
Data in wire From Control To ALU_MUX set to  0
Data in wire From Control To REG_MUX set to  0
Select set to Source Control Destination ALU_MUX Register  Data 0
Select set to Source Control Destination REG_MUX Register  Data 0
Data in wire From Control To PC_MUX set to  0
Select set to Source Control Destination PC_MUX Register  Data 0
Data in wire From Instruction Memory To Register File set to  6
Data in wire From Instruction Memory To Register File set to  6
Data in wire From Instruction Memory To Register File set to  6
Setting first operand destination with register $t0
Data in wire From Register File To ALU set to  6
Setting second operand destination with register $t0
Data in wire From Register File To ALU_MUX set to  6
ALU MUX FIRST OPERAND IS Source Register File Destination ALU_MUX Register $t0 Data 6
Input set in mux
Select is Source Control Destination ALU_MUX Register  Data 0
ALU MUX is forwarding Source Register File Destination ALU_MUX Register $t0 Data 6
Select is Source Control Destination ALU_MUX Register  Data 0
ALU Second Operand set to Source Register File Destination ALU_MUX Register $t0 Data 6
Doing the operation 
The result is 12
Data in wire From ALU To REG_MUX set to  12
Input set in mux
writing in register file
Select is Source Control Destination REG_MUX Register  Data 0
Destination register is $t0 Destination data is 6
Value of Register  Source Instruction Memory Destination Register File Register $t0 Data 6 is now null
alu writing register
RegWrite became true
The result of the operation is 12
Select is Source Control Destination PC_MUX Register  Data 0
PC MUX is forwarding Source PC Destination PC_MUX Register  Data 8
Select is Source Control Destination PC_MUX Register  Data 0
Data in wire From PC_MUX To PC set to  8
The PC MUX output is 8
Fetching instruction at 8
Data in wire From PC To PC_MUX set to  12
Input set in mux
the instruction is sub $t1 $t0 $t0
Setting the controls for sub
Data in wire From Control To ALU set to  2
Data in wire From Control To ALU_MUX set to  0
Data in wire From Control To REG_MUX set to  0
Select set to Source Control Destination ALU_MUX Register  Data 0
Select set to Source Control Destination REG_MUX Register  Data 0
Data in wire From Control To PC_MUX set to  0
Select set to Source Control Destination PC_MUX Register  Data 0
Data in wire From Instruction Memory To Register File set to  0
Data in wire From Instruction Memory To Register File set to  12
Data in wire From Instruction Memory To Register File set to  12
Setting first operand destination with register $t0
Data in wire From Register File To ALU set to  12
Setting second operand destination with register $t0
Data in wire From Register File To ALU_MUX set to  12
ALU MUX FIRST OPERAND IS Source Register File Destination ALU_MUX Register $t0 Data 12
Input set in mux
Select is Source Control Destination ALU_MUX Register  Data 0
ALU MUX is forwarding Source Register File Destination ALU_MUX Register $t0 Data 12
Select is Source Control Destination ALU_MUX Register  Data 0
ALU Second Operand set to Source Register File Destination ALU_MUX Register $t0 Data 12
Doing the operation 
Data in wire From ALU To REG_MUX set to  0
Input set in mux
writing in register file
Select is Source Control Destination REG_MUX Register  Data 0
Destination register is $t1 Destination data is 0
Value of Register  Source Instruction Memory Destination Register File Register $t1 Data 0 is now null
alu writing register
RegWrite became true
The result of the operation is 0
Select is Source Control Destination PC_MUX Register  Data 0
PC MUX is forwarding Source PC Destination PC_MUX Register  Data 12
Select is Source Control Destination PC_MUX Register  Data 0
Data in wire From PC_MUX To PC set to  12
The PC MUX output is 12
Fetching instruction at 12
Data in wire From PC To PC_MUX set to  16
Input set in mux
the instruction is or $t0 $t0 $t0
Setting the controls for or
Data in wire From Control To ALU set to  3
Data in wire From Control To ALU_MUX set to  1
Data in wire From Control To REG_MUX set to  0
Select set to Source Control Destination ALU_MUX Register  Data 1
Select set to Source Control Destination REG_MUX Register  Data 0
Data in wire From Control To PC_MUX set to  0
Select set to Source Control Destination PC_MUX Register  Data 0
Data in wire From Instruction Memory To Register File set to  12
Data in wire From Instruction Memory To Register File set to  12
Data in wire From Instruction Memory To Register File set to  12
Setting first operand destination with register $t0
Data in wire From Register File To ALU set to  12
Setting second operand destination with register $t0
Data in wire From Register File To ALU_MUX set to  12
ALU MUX FIRST OPERAND IS Source Register File Destination ALU_MUX Register $t0 Data 12
Input set in mux
Select is Source Control Destination ALU_MUX Register  Data 1
ALU MUX is forwarding Source Instrucion Memory Destination ALU_MUX Register  Data 1
Select is Source Control Destination ALU_MUX Register  Data 1
ALU Second Operand set to Source Instrucion Memory Destination ALU_MUX Register  Data 1
Doing the operation 
Data in wire From ALU To REG_MUX set to  13
Input set in mux
writing in register file
Select is Source Control Destination REG_MUX Register  Data 0
Destination register is $t0 Destination data is 12
Value of Register  Source Instruction Memory Destination Register File Register $t0 Data 12 is now null
alu writing register
RegWrite became true
The result of the operation is 13
Select is Source Control Destination PC_MUX Register  Data 0
PC MUX is forwarding Source PC Destination PC_MUX Register  Data 16
Select is Source Control Destination PC_MUX Register  Data 0
Data in wire From PC_MUX To PC set to  16
The PC MUX output is 16
Fetching instruction at 16
Data in wire From PC To PC_MUX set to  20
Input set in mux
the instruction is andi $t0 $t0 1
Setting the controls for andi
Data in wire From Control To ALU set to  4
Data in wire From Control To ALU_MUX set to  1
Data in wire From Control To REG_MUX set to  0
Select set to Source Control Destination ALU_MUX Register  Data 1
Select set to Source Control Destination REG_MUX Register  Data 0
Data in wire From Control To PC_MUX set to  0
Select set to Source Control Destination PC_MUX Register  Data 0
Data in wire From Instruction Memory To Register File set to  13
Data in wire From Instrucion Memory To ALU_MUX set to  1
Input set in mux
Setting first operand destination with register $t0
Data in wire From Register File To ALU set to  13
Select is Source Control Destination ALU_MUX Register  Data 1
ALU MUX is forwarding Source Instrucion Memory Destination ALU_MUX Register  Data 1
Select is Source Control Destination ALU_MUX Register  Data 1
ALU Second Operand set to Source Instrucion Memory Destination ALU_MUX Register  Data 1
Doing the operation 
Data in wire From ALU To REG_MUX set to  1
Input set in mux
writing in register file
Select is Source Control Destination REG_MUX Register  Data 0
Destination register is $t0 Destination data is 12
Value of Register  Source Instruction Memory Destination Register File Register $t0 Data 12 is now null
alu writing register
RegWrite became true
The result of the operation is 1
Select is Source Control Destination PC_MUX Register  Data 0
PC MUX is forwarding Source PC Destination PC_MUX Register  Data 20
Select is Source Control Destination PC_MUX Register  Data 0
Data in wire From PC_MUX To PC set to  20
The PC MUX output is 20
Fetching instruction at 20
Data in wire From PC To PC_MUX set to  24
Input set in mux
the instruction is sw $t0 12($t0)
Setting the controls for sw
Data in wire From Control To ALU set to  9
Data in wire From Control To PC_MUX set to  0
Select set to Source Control Destination PC_MUX Register  Data 0
the register is $t0
the register data is 1
Setting address to 13
Data in wire From Register File To ALU set to  13
Source Instruction Memory Destination Register File Register $t0 Data 12
Destination is $t0
Data in wire From Register File To Data Memory set to  1
Data set to Source Register File Destination Data Memory Register  Data 1
Doing the operation 
Address set to 13
Memory writing 
Address 13 in the memory is now 1
The result of the operation is 1
Select is Source Control Destination PC_MUX Register  Data 0
PC MUX is forwarding Source PC Destination PC_MUX Register  Data 24
Select is Source Control Destination PC_MUX Register  Data 0
Data in wire From PC_MUX To PC set to  24
The PC MUX output is 24
Fetching instruction at 24
Data in wire From PC To PC_MUX set to  28
Input set in mux
the instruction is lw $t1 12($t0)
Setting the controls for lw
Data in wire From Control To ALU set to  8
Data in wire From Control To REG_MUX set to  1
Select set to Source Control Destination REG_MUX Register  Data 1
Data in wire From Control To PC_MUX set to  0
Select set to Source Control Destination PC_MUX Register  Data 0
the register is $t0
the register data is 1
Setting address to 13
Data in wire From Register File To ALU set to  13
Doing the operation 
Address set to 13
Memory reading at address  13
Data in wire From Data Memory To REG_MUX set to  1
Input set in mux
writing in register file
Select is Source Control Destination REG_MUX Register  Data 1
Destination register is $t1 Destination data is 12
Value of Register  Source Instruction Memory Destination Register File Register $t1 Data 12 is now null
The result of the operation is 1
Select is Source Control Destination PC_MUX Register  Data 0
PC MUX is forwarding Source PC Destination PC_MUX Register  Data 28
Select is Source Control Destination PC_MUX Register  Data 0
Data in wire From PC_MUX To PC set to  28
The PC MUX output is 28
Fetching instruction at 28
Data in wire From PC To PC_MUX set to  32
Input set in mux
the instruction is addi $t1 $t1 1
Setting the controls for addi
Data in wire From Control To ALU set to  1
Data in wire From Control To ALU_MUX set to  1
Data in wire From Control To REG_MUX set to  0
Select set to Source Control Destination ALU_MUX Register  Data 1
Select set to Source Control Destination REG_MUX Register  Data 0
Data in wire From Control To PC_MUX set to  0
Select set to Source Control Destination PC_MUX Register  Data 0
Data in wire From Instruction Memory To Register File set to  1
Data in wire From Instrucion Memory To ALU_MUX set to  1
Input set in mux
Setting first operand destination with register $t1
Data in wire From Register File To ALU set to  1
Select is Source Control Destination ALU_MUX Register  Data 1
ALU MUX is forwarding Source Instrucion Memory Destination ALU_MUX Register  Data 1
Select is Source Control Destination ALU_MUX Register  Data 1
ALU Second Operand set to Source Instrucion Memory Destination ALU_MUX Register  Data 1
Doing the operation 
The result is 2
Data in wire From ALU To REG_MUX set to  2
Input set in mux
writing in register file
Select is Source Control Destination REG_MUX Register  Data 0
Destination register is $t1 Destination data is 12
Value of Register  Source Instruction Memory Destination Register File Register $t1 Data 12 is now null
alu writing register
RegWrite became true
The result of the operation is 2
Select is Source Control Destination PC_MUX Register  Data 0
PC MUX is forwarding Source PC Destination PC_MUX Register  Data 32
Select is Source Control Destination PC_MUX Register  Data 0
Data in wire From PC_MUX To PC set to  32
The PC MUX output is 32
Fetching instruction at 32
Data in wire From PC To PC_MUX set to  36
Input set in mux
the instruction is sltui $t2 $t1 -3
Setting the controls for sltui
Data in wire From Control To ALU set to  14
Data in wire From Control To ALU_MUX set to  1
Data in wire From Control To REG_MUX set to  0
Select set to Source Control Destination ALU_MUX Register  Data 1
Select set to Source Control Destination REG_MUX Register  Data 0
Data in wire From Instruction Memory To Register File set to  2
Data in wire From Instrucion Memory To ALU_MUX set to  -3
Input set in mux
Setting first operand destination with register $t1
Data in wire From Register File To ALU set to  2
Select is Source Control Destination ALU_MUX Register  Data 1
ALU MUX is forwarding Source Instrucion Memory Destination ALU_MUX Register  Data -3
Select is Source Control Destination ALU_MUX Register  Data 1
ALU Second Operand set to Source Instrucion Memory Destination ALU_MUX Register  Data -3
Doing the operation 
First Operand data is 2
Second Operand data is -3
The result is 1
Data in wire From ALU To REG_MUX set to  1
Input set in mux
writing in register file
Select is Source Control Destination REG_MUX Register  Data 0
Destination register is $t2 Destination data is 12
Value of Register  Source Instruction Memory Destination Register File Register $t2 Data 12 is now null
alu writing register
RegWrite became true
The result of the operation is 1
Select is Source Control Destination PC_MUX Register  Data 0
PC MUX is forwarding Source PC Destination PC_MUX Register  Data 36
Select is Source Control Destination PC_MUX Register  Data 0
Data in wire From PC_MUX To PC set to  36
1
2
1
BUILD SUCCESSFUL (total time: 2 seconds)
