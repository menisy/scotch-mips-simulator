/*run:
Starting address is 0
File size is 7
putting in slot 0 instruction bne $t1 $t0 8
putting in slot 4 instruction end
putting in slot 8 instruction addi $t1 $t0 2
putting in slot 12 instruction addi $t0 $t0 1
adding labels ZA3 to add $t2 $zero $t0 line (16)
putting in slot 20 instruction jal 4
putting in slot 24 instruction addi $t1 $t1 1
ALU First Operand set to Source Register File Destination ALU Register  Data 0
The PC MUX output is 0
Fetching instruction at 0
Data in wire From PC To PC_MUX set to  4
Input set in mux
the instruction is bne $t1 $t0 8
Setting the controls for bne
Data in wire From Control To PC_MUX set to  0
Data in wire From Control To ALU set to  11
Select set to Source Control Destination PC_MUX Register  Data 0
Data in wire From Control To ALU_MUX set to  0
Select set to Source Control Destination ALU_MUX Register  Data 0
Data in wire From Instruction Memory To Register File set to  3
Data in wire From Instruction Memory To Register File set to  0
Setting first operand destination with register $t1
Data in wire From Register File To ALU set to  0
Setting second operand destination with register $t0
Data in wire From Register File To ALU_MUX set to  3
ALU MUX FIRST OPERAND IS Source Register File Destination ALU_MUX Register $t0 Data 3
Input set in mux
Data in wire From PC To PC_MUX set to  8
Input set in mux
Select is Source Control Destination ALU_MUX Register  Data 0
ALU MUX is forwarding Source Register File Destination ALU_MUX Register $t0 Data 3
Select is Source Control Destination ALU_MUX Register  Data 0
ALU Second Operand set to Source Register File Destination ALU_MUX Register $t0 Data 3
Doing the operation 
ALU Executing BNE with 0 and 3
BNE branching
Data in wire From ALU To PC_MUX set to  1
Select set to Source ALU Destination PC_MUX Register  Data 1
The result of the operation is 0
Select is Source ALU Destination PC_MUX Register  Data 1
PC MUX is forwarding Source PC Destination PC_MUX Register  Data 8
Select is Source ALU Destination PC_MUX Register  Data 1
Data in wire From PC_MUX To PC set to  8
The PC MUX output is 8
Fetching instruction at 4
Data in wire From PC To PC_MUX set to  12
Input set in mux
the instruction is addi $t1 $t0 2
Setting the controls for addi
Data in wire From Control To ALU set to  1
Data in wire From Control To ALU_MUX set to  1
Data in wire From Control To REG_MUX set to  0
Select set to Source Control Destination ALU_MUX Register  Data 1
Select set to Source Control Destination REG_MUX Register  Data 0
Data in wire From Control To PC_MUX set to  0
Select set to Source Control Destination PC_MUX Register  Data 0
Data in wire From Instruction Memory To Register File set to  3
Data in wire From Instrucion Memory To ALU_MUX set to  2
Input set in mux
Setting first operand destination with register $t0
Data in wire From Register File To ALU set to  3
Select is Source Control Destination ALU_MUX Register  Data 1
ALU MUX is forwarding Source Instrucion Memory Destination ALU_MUX Register  Data 2
Select is Source Control Destination ALU_MUX Register  Data 1
ALU Second Operand set to Source Instrucion Memory Destination ALU_MUX Register  Data 2
Doing the operation 
The result is 5
Data in wire From ALU To REG_MUX set to  5
Input set in mux
writing in register file
Select is Source Control Destination REG_MUX Register  Data 0
Destination register is $t1 Destination data is 0
Value of Register  Source Instruction Memory Destination Register File Register $t1 Data 0 is now null
alu writing register
RegWrite became true
The result of the operation is 5
Select is Source Control Destination PC_MUX Register  Data 0
PC MUX is forwarding Source PC Destination PC_MUX Register  Data 12
Select is Source Control Destination PC_MUX Register  Data 0
Data in wire From PC_MUX To PC set to  12
The PC MUX output is 12
Fetching instruction at 8
Data in wire From PC To PC_MUX set to  16
Input set in mux
the instruction is addi $t0 $t0 1
Setting the controls for addi
Data in wire From Control To ALU set to  1
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
The result is 4
Data in wire From ALU To REG_MUX set to  4
Input set in mux
writing in register file
Select is Source Control Destination REG_MUX Register  Data 0
Destination register is $t0 Destination data is 0
Value of Register  Source Instruction Memory Destination Register File Register $t0 Data 0 is now null
alu writing register
RegWrite became true
The result of the operation is 4
Select is Source Control Destination PC_MUX Register  Data 0
PC MUX is forwarding Source PC Destination PC_MUX Register  Data 16
Select is Source Control Destination PC_MUX Register  Data 0
Data in wire From PC_MUX To PC set to  16
The PC MUX output is 16
Fetching instruction at 12
Data in wire From PC To PC_MUX set to  20
Input set in mux
the instruction is add $t2 $zero $t0
Setting the controls for add
Data in wire From Control To ALU set to  1
Data in wire From Control To ALU_MUX set to  0
Data in wire From Control To REG_MUX set to  0
Select set to Source Control Destination ALU_MUX Register  Data 0
Select set to Source Control Destination REG_MUX Register  Data 0
Data in wire From Control To PC_MUX set to  0
Select set to Source Control Destination PC_MUX Register  Data 0
Data in wire From Instruction Memory To Register File set to  0
Data in wire From Instruction Memory To Register File set to  4
Data in wire From Instruction Memory To Register File set to  0
Setting first operand destination with register $zero
Data in wire From Register File To ALU set to  0
Setting second operand destination with register $t0
Data in wire From Register File To ALU_MUX set to  4
ALU MUX FIRST OPERAND IS Source Register File Destination ALU_MUX Register $t0 Data 4
Input set in mux
Select is Source Control Destination ALU_MUX Register  Data 0
ALU MUX is forwarding Source Register File Destination ALU_MUX Register $t0 Data 4
Select is Source Control Destination ALU_MUX Register  Data 0
ALU Second Operand set to Source Register File Destination ALU_MUX Register $t0 Data 4
Doing the operation 
The result is 4
Data in wire From ALU To REG_MUX set to  4
Input set in mux
writing in register file
Select is Source Control Destination REG_MUX Register  Data 0
Destination register is $t2 Destination data is 0
Value of Register  Source Instruction Memory Destination Register File Register $t2 Data 0 is now null
alu writing register
RegWrite became true
The result of the operation is 4
Select is Source Control Destination PC_MUX Register  Data 0
PC MUX is forwarding Source PC Destination PC_MUX Register  Data 20
Select is Source Control Destination PC_MUX Register  Data 0
Data in wire From PC_MUX To PC set to  20
The PC MUX output is 20
Fetching instruction at 16
Data in wire From PC To PC_MUX set to  24
Input set in mux
the instruction is jal 4
Setting the controls for jal
Data in wire From Control To PC_MUX set to  1
Data in wire From Control To ALU set to  16
Data in wire From Control To PC_MUX set to  1
Select set to Source Control Destination PC_MUX Register  Data 1
Data in wire From Control To ALU_MUX set to  0
Select set to Source Control Destination ALU_MUX Register  Data 0
Data in wire From Instruction Memory To Register File set to  20
Data in wire From Instruction Memory To Register File set to  20
Setting first operand destination with register $zero
Data in wire From Register File To ALU set to  0
Data in wire From PC To PC_MUX set to  4
Input set in mux
Select is Source Control Destination ALU_MUX Register  Data 0
ALU MUX is forwarding Source Register File Destination ALU_MUX Register $t0 Data 4
Select is Source Control Destination ALU_MUX Register  Data 0
ALU Second Operand set to Source Register File Destination ALU_MUX Register $t0 Data 4
Doing the operation 
the next instruction is 0
Data in wire From ALU To REG_MUX set to  0
Input set in mux
writing in register file
Select is Source Control Destination REG_MUX Register  Data 0
Destination register is $ra Destination data is 20
Value of Register  Source Instruction Memory Destination Register File Register $ra Data 20 is now null
alu writing register
RegWrite became true
The result of the operation is 0
Select is Source Control Destination PC_MUX Register  Data 1
PC MUX is forwarding Source PC Destination PC_MUX Register  Data 4
Select is Source Control Destination PC_MUX Register  Data 1
Data in wire From PC_MUX To PC set to  4
4
5
4
0
BUILD SUCCESSFUL (total time: 1 second)
*/