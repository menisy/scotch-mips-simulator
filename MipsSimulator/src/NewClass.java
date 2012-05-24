run:
Starting address is 0
File size is 3
putting in slot 0 instruction addi $t0 $zero 4
putting in slot 4 instruction sw $t0 12($t0)
putting in slot 8 instruction lw $t1 12($t0)
Fetching instruction at 0
the instruction is addi $t0 $zero 4
Setting the controls for addi
 alu control set to 1
Select set to 1
Select set to 0
Write Register is now $t0
ALU Register is now $zero
Setting operand 1 to 0
Input set in mux
ALU MUX is forwarding 4
Doing the operation 
Input set in mux
The result of the operation is 4
writing in register file
Value of Register  $t0 is now 4
alu writing register
Input set in mux
Fetching instruction at 4
the instruction is sw $t0 12($t0)
Setting the controls for sw
 alu control set to 9
Select set to 1
ALU Register is now 12($t0)
Computing the address 
the register is $t0
Setting address to 16
Setting operand 1 to 16
ALU_MUX Register is now $t0
Input set in mux
Data set to 4
ALU MUX is forwarding 4
Doing the operation 
Address set to 16
writing in register file
Value of Register  $t0 is now 0
Memory writing 
Address 16 in the memory is now 4
Exception in thread "main" java.lang.NullPointerException
The result of the operation is 4
	at DataMemory.read(DataMemory.java:40writing in register file
Value of Register  $t0 is now 0
alu writing register
)
	at DataMemory.setAddress(DataMemory.java:64)
Input set in mux
	at ALU.doOperation(ALU.java:64)
Fetching instruction at 8
	at InstructionMemory.decode(InstructionMemory.java:130)
the instruction is lw $t1 12($t0)
	at InstructionMemory.fetchInstruction(InstructionMemory.java:56)
Setting the controls for lw
	at InstructionMemory.start(InstructionMemory.java:46)
 alu control set to 8
	at InstructionMemory.<init>(InstructionMemory.java:31)
Select set to 1
	at InstructionMemory.main(InstructionMemory.java:138)
Write Register is now $t1
ALU Register is now 12($t0)
Computing the address 
the register is $t0
Setting address to 12
Setting operand 1 to 12
ALU MUX is forwarding 4
Doing the operation 
Address set to 12
Memory reading at address  12
Java Result: 1
BUILD SUCCESSFUL (total time: 1 second)
