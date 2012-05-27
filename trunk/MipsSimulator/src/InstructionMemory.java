
import java.util.ArrayList;
import java.util.HashMap;

import javax.security.sasl.AuthorizeCallback;

public class InstructionMemory {

    private int pc;
    boolean done;
    HashMap<Integer, String> instructions = new HashMap<Integer, String>();
    HashMap<String, Integer> labels = new HashMap<String, Integer>();
    Organizer controller = new Organizer();
    Wire ALURegister = new Wire("Instruction Memory", "Register File", "", 0);
    Wire ALU_MUXRegister = new Wire("Instruction Memory", "Register File", "", 0);
    Wire WriteRegister = new Wire("Instruction Memory", "Register File", "", 0);
    RegistersFile registerFile = new RegistersFile(ALURegister, ALU_MUXRegister, WriteRegister);
    ALU alu = new ALU(ALURegister, ALU_MUXRegister, WriteRegister);
    ALU_MUX aluMUX = new ALU_MUX(alu);
    REG_MUX regMUX = new REG_MUX(registerFile);
    Wire sequential = new Wire("PC", "PC_MUX", "", pc);
    Wire jump = new Wire("PC", "PC_MUX", "", 0);
    Wire PC_MUXOut = new Wire("PC_MUX", "PC", "", 0);
    PC_MUX pcMUX;
    DataMemory memory = new DataMemory(regMUX);
    Wire ALU_MUXSecondInput = new Wire("Instrucion Memory", "ALU_MUX", "", 0);

    public InstructionMemory(ArrayList<String> file) {
        this.pcMUX = new PC_MUX(this, this.PC_MUXOut);
        this.alu.setPCMUX(this.pcMUX);
        String firstLine = file.get(0);
        String[] arr = firstLine.split(" ");
        int startingAddress;
        if (arr[0].equalsIgnoreCase("ORG")) {
            startingAddress = Integer.parseInt(arr[1]);
        } else {
            startingAddress = 0;
        }
        System.out.println("Starting address is " + startingAddress);
        System.out.println("File size is " + file.size());
        for (int i = 1, j = startingAddress; i <= file.size(); i++, j += 4) {
            String line = file.get(i - 1);
            if (line.contains(":")) {
                String[] split = line.split(": ");
                System.out.println("adding labels " + split[0] + " to " + split[1] + " line (" + j + ")");
                labels.put(split[0], j);
                instructions.put(j, split[1]);
            } else {
                instructions.put(j, file.get(i - 1));
                System.out.println("putting in slot " + j + " instruction " + file.get(i - 1));
            }
        }
        controller.setInstances(alu, aluMUX, registerFile, this, regMUX, memory, this.pcMUX);
        registerFile.setInstances(alu, aluMUX, memory);
        alu.setInstances(controller, registerFile, regMUX, memory);
        this.start();
    }

    /**
     * The Method returns whether the instruction memory is done fetching all
     * instructions or not
     *
     * @return boolean
     */
    public boolean isDone() {
        return done;
    }

    /**
     * The Method returns the current PC value
     *
     * @return int
     */
    public int getPC() {
        return pc;
    }

    /**
     * The Method is called whenever the instruction memory is done inserting
     * instructions. the method loops and calls the fetchInstruction method,
     * whenever it hits the end of the file, the method stops
     *
     * @see fetchInstruction()
     */
    public void start() {
        while (!this.isDone()) {
            if ((instructions.get(this.PC_MUXOut.getData()) == null) || (instructions.get(this.PC_MUXOut.getData()).equalsIgnoreCase("end"))) {
                this.done = true;
                break;
            }
            this.fetchInstruction();
        }
    }

    /**
     * The Method fetches the instruction from the instruction memory using the
     * current PC value and passes this instruction to the decode method
     *
     * @see decode()
     */
    public void fetchInstruction() {
        System.out.println(registerFile.registers.get("$t0"));
        System.out.println(registerFile.registers.get("$s0"));
        System.out.println(registerFile.registers.get("$t2"));
        System.out.println("The PC MUX output is " + this.PC_MUXOut.getData());
        System.out.println("Fetching instruction at " + pc);
        String instruction = instructions.get(this.PC_MUXOut.getData());
        String[] arr = instruction.split(" ");
        if (arr[0].equalsIgnoreCase("end")) {
            //done = true;
        } else {
            pc += 4;
            this.sequential.setData(this.PC_MUXOut.getData() + 4);
            this.pcMUX.setInput(0, this.sequential);
            this.decode(instruction);
        }

    }

    /**
     * The method sets the PC to a given value
     *
     * @param pc
     */
    public void setPC(int pc) {
        this.pc = pc;
    }

    /**
     * The Method is given an instruction, it decodes this instruction, first it
     * asks the controller to set the controls for this instruction then it sets
     * the register file inputs and PC_MUX, ALU_MUX inputs
     *
     * @param instruction
     */
    public void decode(String instruction) {
        System.out.println("the instruction is " + instruction);
        String[] arr = instruction.split(" ");
        String op = arr[0];
        this.controller.setControls(arr[0]);
        if (op.equalsIgnoreCase("add")) {
            WriteRegister.setDestinationRegister(arr[1]);
            WriteRegister.setData(this.registerFile.registers.get(arr[1]));
            ALU_MUXRegister.setDestinationRegister(arr[3]);
            ALU_MUXRegister.setData(this.registerFile.registers.get(arr[3]));
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.registerFile.setFirstOperandDestination();
            this.registerFile.setSecondOperandDestination();
            //this.registerFile.setFirstOperandDestination(arr[2]);
            //this.registerFile.setSecondOperandDestination(arr[3]);
        } else if (op.equalsIgnoreCase("addi")) {
            WriteRegister.setDestinationRegister(arr[1]);
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.ALU_MUXSecondInput.setData(Integer.parseInt(arr[3]));
            this.aluMUX.setInput(1, this.ALU_MUXSecondInput);
            this.registerFile.setFirstOperandDestination();
        } else if (op.equalsIgnoreCase("sub")) {
            WriteRegister.setDestinationRegister(arr[1]);
            WriteRegister.setData(this.registerFile.registers.get(arr[1]));
            ALU_MUXRegister.setDestinationRegister(arr[3]);
            ALU_MUXRegister.setData(this.registerFile.registers.get(arr[3]));
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.registerFile.setFirstOperandDestination();
            this.registerFile.setSecondOperandDestination();
        } else if (op.equalsIgnoreCase("lw")) {
            WriteRegister.setDestinationRegister(arr[1]);
            ALURegister.setDestinationRegister(arr[2]);
            this.registerFile.setFirstOperandDestination();
        } else if (op.equalsIgnoreCase("sw")) {
            ALURegister.setDestinationRegister(arr[2]);
            ALU_MUXRegister.setDestinationRegister(arr[1]);
            this.registerFile.setFirstOperandDestination();

            registerFile.forward();
        } else if (op.equalsIgnoreCase("sll")) {
            WriteRegister.setDestinationRegister(arr[1]);
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.ALU_MUXSecondInput.setData(Integer.parseInt(arr[3]));
            this.aluMUX.setInput(1, this.ALU_MUXSecondInput);
            this.registerFile.setFirstOperandDestination();
        } else if (op.equalsIgnoreCase("srl")) {
            WriteRegister.setDestinationRegister(arr[1]);
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.ALU_MUXSecondInput.setData(Integer.parseInt(arr[3]));
            this.aluMUX.setInput(1, this.ALU_MUXSecondInput);
            this.registerFile.setFirstOperandDestination();
        } else if (op.equalsIgnoreCase("and")) {
            WriteRegister.setDestinationRegister(arr[1]);
            WriteRegister.setData(this.registerFile.registers.get(arr[1]));
            ALU_MUXRegister.setDestinationRegister(arr[3]);
            ALU_MUXRegister.setData(this.registerFile.registers.get(arr[3]));
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.registerFile.setFirstOperandDestination();
            this.registerFile.setSecondOperandDestination();
        } else if (op.equalsIgnoreCase("andi")) {
            WriteRegister.setDestinationRegister(arr[1]);
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.ALU_MUXSecondInput.setData(Integer.parseInt(arr[3]));
            this.aluMUX.setInput(1, this.ALU_MUXSecondInput);
            this.registerFile.setFirstOperandDestination();
        } else if (op.equalsIgnoreCase("or")) {
            WriteRegister.setDestinationRegister(arr[1]);
            WriteRegister.setData(this.registerFile.registers.get(arr[1]));
            ALU_MUXRegister.setDestinationRegister(arr[3]);
            ALU_MUXRegister.setData(this.registerFile.registers.get(arr[3]));
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.registerFile.setFirstOperandDestination();
            this.registerFile.setSecondOperandDestination();
        } else if (op.equalsIgnoreCase("ori")) {
            WriteRegister.setDestinationRegister(arr[1]);
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.ALU_MUXSecondInput.setData(Integer.parseInt(arr[3]));
            this.aluMUX.setInput(1, this.ALU_MUXSecondInput);
            this.registerFile.setFirstOperandDestination();
        } else if (op.equalsIgnoreCase("nor")) {
            WriteRegister.setDestinationRegister(arr[1]);
            WriteRegister.setData(this.registerFile.registers.get(arr[1]));
            ALU_MUXRegister.setDestinationRegister(arr[3]);
            ALU_MUXRegister.setData(this.registerFile.registers.get(arr[3]));
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.registerFile.setFirstOperandDestination();
            this.registerFile.setSecondOperandDestination();
        } else if (op.equalsIgnoreCase("beq")) {
            ALU_MUXRegister.setDestinationRegister(arr[2]);
            ALU_MUXRegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setData(this.registerFile.registers.get(arr[1]));
            ALURegister.setDestinationRegister(arr[1]);
            this.registerFile.setFirstOperandDestination();
            this.registerFile.setSecondOperandDestination();
//            this.jump.setData(Integer.parseInt(arr[3]));
            this.jump.setData(getJumpValue(arr[3]));
            this.pcMUX.setInput(1, jump);
        } else if (op.equalsIgnoreCase("bne")) {
            ALU_MUXRegister.setDestinationRegister(arr[2]);
            ALU_MUXRegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setData(this.registerFile.registers.get(arr[1]));
            ALURegister.setDestinationRegister(arr[1]);
            this.registerFile.setFirstOperandDestination();
            this.registerFile.setSecondOperandDestination();
            this.jump.setData(getJumpValue(arr[3]));
            this.pcMUX.setInput(1, jump);
        } else if (op.equalsIgnoreCase("j")) {
            this.jump.setData(getJumpValue(arr[1]));
            this.pcMUX.setInput(1, jump);
        } else if (op.equalsIgnoreCase("jal")) {
            WriteRegister.setDestinationRegister("$ra");
            WriteRegister.setData(this.PC_MUXOut.getData());
            System.out.println("pc mux out is " + this.PC_MUXOut.getData());
            ALURegister.setData(this.PC_MUXOut.getData() + 4);
            this.ALU_MUXRegister.setData(0);;
            this.registerFile.setFirstOperandDirectDestination();
            this.jump.setData(getJumpValue(arr[1]));
            this.pcMUX.setInput(1, jump);

        } else if (op.equalsIgnoreCase("jr")) {
            this.jump.setData(this.registerFile.registers.get(arr[1]));
            this.pcMUX.setInput(1, jump);
        } else if (op.equalsIgnoreCase("slt")) {
            WriteRegister.setDestinationRegister(arr[1]);
            WriteRegister.setData(this.registerFile.registers.get(arr[1]));
            ALU_MUXRegister.setDestinationRegister(arr[3]);
            ALU_MUXRegister.setData(this.registerFile.registers.get(arr[3]));
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.registerFile.setFirstOperandDestination();
            this.registerFile.setSecondOperandDestination();
        } else if (op.equalsIgnoreCase("slti")) {
            WriteRegister.setDestinationRegister(arr[1]);
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.ALU_MUXSecondInput.setData(Integer.parseInt(arr[3]));
            this.aluMUX.setInput(1, this.ALU_MUXSecondInput);
            this.registerFile.setFirstOperandDestination();
        } else if (op.equalsIgnoreCase("sltu")) {
            WriteRegister.setDestinationRegister(arr[1]);
            WriteRegister.setData(this.registerFile.registers.get(arr[1]));
            ALU_MUXRegister.setDestinationRegister(arr[3]);
            ALU_MUXRegister.setData(this.registerFile.registers.get(arr[3]));
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.registerFile.setFirstOperandDestination();
            this.registerFile.setSecondOperandDestination();
        } else if (op.equalsIgnoreCase("sltui")) {
            WriteRegister.setDestinationRegister(arr[1]);
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.ALU_MUXSecondInput.setData(Integer.parseInt(arr[3]));
            this.aluMUX.setInput(1, this.ALU_MUXSecondInput);
            this.registerFile.setFirstOperandDestination();
        }
        if (!(arr[0].equalsIgnoreCase("sw") || arr[0].equalsIgnoreCase("lw") || arr[0].equalsIgnoreCase("jal"))) {
            this.aluMUX.forward();
        }

        this.alu.doOperation();
        this.pcMUX.forward();

    }

    /**
     * This method gets the value which a label points at. If there are no
     * values, -1 is returned. The method first checks if the argument passed
     * can be be parsed to an integer, if yes, it jumps directly to that value
     * Author: Yahia
     */
    public Integer getJumpValue(String arg) {
        try {
            Integer jumpValue = Integer.parseInt(arg);
            return jumpValue;
        } catch (Exception e) {
            System.out.println("Jumping to " + arg);
            if (labels.get(arg) != null) {
                return labels.get(arg);
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] abbas) {
        ArrayList<String> file = new ArrayList<String>();
        /*
         * file.add("sll $t0 $t0 1"); file.add("add $t0 $t0 $t0"); file.add("sub
         * $t1 $t0 $t0");
         *
         * file.add("or $t0 $t0 $t0"); file.add("and $t0 $t0 $t0"); file.add("sw
         * $t0 12($t0)"); file.add("lw $t1 12($t0)"); file.add("addi $t1 $t1
         * 1"); file.add("sltui $t2 $t1 -3"); // file.add("and $t1 $t0 $t0");
         */
        //file.add("add $t0 $t0 $t0");
        //  file.add("bne $t1 $t0 8");
        // file.add("end");
        //file.add("addi $t1 $t0 2");
        //file.add("addi $t0 $t0 1");
        // the following instruction won't be executed, we used branch dumbass!
        // file.add("ZA3: add $t2 $zero $t0");
        // file.add("jal 4");
        // file.add("addi $t1 $t1 1");
        //  file.add("end");
        // file.add("addi $t0 $zero 0");
        //file.add("slti $t2 $t0 10");
        //file.add("beq $t2 $zero 22");
        //file.add("add $s0 $s0 $s1");
        //file.add("addi $t0 $t0 1");
        //file.add("jal 4");
        //file.add(" end");
        file.add("jal FACT");
        file.add("end");
        file.add("FACT: addi $sp $sp -8");
        file.add("sw $ra 4($sp)");
        file.add("sw $a0 0($sp)");
        file.add("slti $t0 $a0 1");
        file.add("beq $t0 $zero L1");
        file.add("addi $v0 $zero 1");
        file.add("addi $sp $sp 8");
        file.add("jr $ra");
        file.add("L1: addi $a0 $a0 -1");
        file.add("jal FACT");
        file.add("lw $a0 0($sp)");
        file.add("lw $ra 4($sp)");
        file.add("addi $sp $sp 8");
        file.add("add $v0 $a0 $v0");
        file.add("jr $ra");


        InstructionMemory is = new InstructionMemory(file);
        System.out.println(is.registerFile.registers.get("$t0"));
        System.out.println(is.registerFile.registers.get("$t1"));
        System.out.println(is.registerFile.registers.get("$t2"));
        System.out.println(is.registerFile.registers.get("$ra"));
        System.out.println(is.registerFile.registers.get("$v0"));
    }
}
