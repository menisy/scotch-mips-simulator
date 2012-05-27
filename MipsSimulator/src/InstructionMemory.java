
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.security.sasl.AuthorizeCallback;

public class InstructionMemory {

    static HashMap<String, Integer> DATA = new HashMap<String, Integer>();
    static ArrayList<ArrayList<ArrayList<String>>> wiresLog = new ArrayList<ArrayList<ArrayList<String>>>();
    static int COMMANDS_COUNTER = 0;
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
    DataMemory memory;
    Wire ALU_MUXSecondInput = new Wire("Instrucion Memory", "ALU_MUX", "", 0);
    Wire controllerWire = new Wire("Instruction Memory", "Controller", "", 0);

    public InstructionMemory(ArrayList<String> file) {
        this.memory = new DataMemory(this.regMUX, this);
        this.wiresLog.add(new ArrayList<ArrayList<String>>());
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
        boolean dataSeg = false;
        for (int i = 1, j = startingAddress; i <= file.size(); i++, j += 4) {
            String line = file.get(i - 1);
            // AUTHOR YAHIA
            if (line.contains(".data")) {
                System.out.println("IN THE DATA SEGMENT");
                dataSeg = true;
                j-=4;
                continue;
            }
            else if (line.contains(".text")) {
                System.out.println("IN THE TEXT SEGMENT");
                dataSeg = false;
                j-=4;
                continue;
            }
            
            if (dataSeg) {              
                System.out.println("$$$$$$$$$$$$$ IN DATA SEG");
                String[] split = line.split(": ",2);
                System.out.println(Arrays.toString(split));     
                if (split.length < 3 && split[1].startsWith(".word")) {
                	int value = 0;
                	String[] split2 = split[1].split(" ");
                	System.out.println(Arrays.toString(split2));
                	if (split2[1].startsWith("0x"))
                	{
                		// I am really sorry for the following line :'( 
                		// it's the simplest. It's evil I know :'(, :'(
                		BigInteger bi = new BigInteger(split2[1].substring(2), 16);
                		value = bi.intValue();
                	}else 
                	{
                		value = Integer.parseInt(split2[1]);
                	}
                	System.out.println("adding " + split[0] + " => " + value);
                	DATA.put(split[0], value);
                }
                j-=4;
                
            } else if (line.contains(":")) {
                String[] split = line.split(": ");
                System.out.println("adding labels " + split[0] + " to " + split[1] + " line (" + j + ")");
                labels.put(split[0], j);
                instructions.put(j, split[1]);
                
            // AUTHOR YAHIA END 
            } else {
                if (file.get(i - 1).contains("ORG")) {
                    String[] splittedInstruction = file.get(i - 1).split(" ");
                    startingAddress = Integer.parseInt(splittedInstruction[1]);
                    j -= 4;
                } else {
                    instructions.put(j, file.get(i - 1));
                    System.out.println("putting in slot " + j + " instruction " + file.get(i - 1));
                }




            }
        }
        controller.setInstances(alu, aluMUX, registerFile, this, regMUX, memory, this.pcMUX);
        registerFile.setInstances(alu, aluMUX, memory);
        alu.setInstances(controller, registerFile, regMUX, memory);
        this.PC_MUXOut.setData(startingAddress);
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
        this.wiresLog.get(COMMANDS_COUNTER).add(new ArrayList<String>());
        this.wiresLog.get(COMMANDS_COUNTER).get(0).add("PC wire: " + this.PC_MUXOut.toString());
        if (arr[0].equalsIgnoreCase("end")) {
            //done = true;
        } else {
            pc += 4;
            this.sequential.setData(this.PC_MUXOut.getData() + 4);
            this.pcMUX.setInput(0, this.sequential);
            this.decode(instruction);
        }
        COMMANDS_COUNTER++;
        this.wiresLog.add(new ArrayList<ArrayList<String>>());

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
        this.wiresLog.get(COMMANDS_COUNTER).add(new ArrayList<String>());
        this.wiresLog.get(COMMANDS_COUNTER).add(new ArrayList<String>());
        this.controller.setControllerWire(controllerWire);

        if (op.equalsIgnoreCase("add")) {
            this.controllerWire.setData(1);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
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
            this.controllerWire.setData(2);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            WriteRegister.setDestinationRegister(arr[1]);
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.ALU_MUXSecondInput.setData(Integer.parseInt(arr[3]));
            this.aluMUX.setInput(1, this.ALU_MUXSecondInput);
            this.registerFile.setFirstOperandDestination();
        } else if (op.equalsIgnoreCase("sub")) {
            this.controllerWire.setData(3);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            WriteRegister.setDestinationRegister(arr[1]);
            WriteRegister.setData(this.registerFile.registers.get(arr[1]));
            ALU_MUXRegister.setDestinationRegister(arr[3]);
            ALU_MUXRegister.setData(this.registerFile.registers.get(arr[3]));
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.registerFile.setFirstOperandDestination();
            this.registerFile.setSecondOperandDestination();
        } else if (op.equalsIgnoreCase("lw")) {
            this.controllerWire.setData(4);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            WriteRegister.setDestinationRegister(arr[1]);
            ALURegister.setDestinationRegister(arr[2]);
            this.registerFile.setFirstOperandDestination();
        } else if (op.equalsIgnoreCase("sw")) {
            this.controllerWire.setData(5);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            ALURegister.setDestinationRegister(arr[2]);
            ALU_MUXRegister.setDestinationRegister(arr[1]);
            this.registerFile.setFirstOperandDestination();

            registerFile.forward();
        } else if (op.equalsIgnoreCase("sll")) {
            this.controllerWire.setData(6);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            WriteRegister.setDestinationRegister(arr[1]);
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.ALU_MUXSecondInput.setData(Integer.parseInt(arr[3]));
            this.aluMUX.setInput(1, this.ALU_MUXSecondInput);
            this.registerFile.setFirstOperandDestination();
        } else if (op.equalsIgnoreCase("srl")) {
            this.controllerWire.setData(7);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            WriteRegister.setDestinationRegister(arr[1]);
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.ALU_MUXSecondInput.setData(Integer.parseInt(arr[3]));
            this.aluMUX.setInput(1, this.ALU_MUXSecondInput);
            this.registerFile.setFirstOperandDestination();
        } else if (op.equalsIgnoreCase("and")) {
            this.controllerWire.setData(8);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            WriteRegister.setDestinationRegister(arr[1]);
            WriteRegister.setData(this.registerFile.registers.get(arr[1]));
            ALU_MUXRegister.setDestinationRegister(arr[3]);
            ALU_MUXRegister.setData(this.registerFile.registers.get(arr[3]));
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.registerFile.setFirstOperandDestination();
            this.registerFile.setSecondOperandDestination();
        } else if (op.equalsIgnoreCase("andi")) {
            this.controllerWire.setData(9);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            WriteRegister.setDestinationRegister(arr[1]);
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.ALU_MUXSecondInput.setData(Integer.parseInt(arr[3]));
            this.aluMUX.setInput(1, this.ALU_MUXSecondInput);
            this.registerFile.setFirstOperandDestination();
        } else if (op.equalsIgnoreCase("or")) {
            this.controllerWire.setData(10);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            WriteRegister.setDestinationRegister(arr[1]);
            WriteRegister.setData(this.registerFile.registers.get(arr[1]));
            ALU_MUXRegister.setDestinationRegister(arr[3]);
            ALU_MUXRegister.setData(this.registerFile.registers.get(arr[3]));
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.registerFile.setFirstOperandDestination();
            this.registerFile.setSecondOperandDestination();
        } else if (op.equalsIgnoreCase("ori")) {
            this.controllerWire.setData(11);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            WriteRegister.setDestinationRegister(arr[1]);
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.ALU_MUXSecondInput.setData(Integer.parseInt(arr[3]));
            this.aluMUX.setInput(1, this.ALU_MUXSecondInput);
            this.registerFile.setFirstOperandDestination();
        } else if (op.equalsIgnoreCase("nor")) {
            this.controllerWire.setData(12);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            WriteRegister.setDestinationRegister(arr[1]);
            WriteRegister.setData(this.registerFile.registers.get(arr[1]));
            ALU_MUXRegister.setDestinationRegister(arr[3]);
            ALU_MUXRegister.setData(this.registerFile.registers.get(arr[3]));
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.registerFile.setFirstOperandDestination();
            this.registerFile.setSecondOperandDestination();
        } else if (op.equalsIgnoreCase("beq")) {
            this.controllerWire.setData(13);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
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
            this.controllerWire.setData(14);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            ALU_MUXRegister.setDestinationRegister(arr[2]);
            ALU_MUXRegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setData(this.registerFile.registers.get(arr[1]));
            ALURegister.setDestinationRegister(arr[1]);
            this.registerFile.setFirstOperandDestination();
            this.registerFile.setSecondOperandDestination();
            this.jump.setData(getJumpValue(arr[3]));
            this.pcMUX.setInput(1, jump);
        } else if (op.equalsIgnoreCase("j")) {
            this.controllerWire.setData(15);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            this.jump.setData(getJumpValue(arr[1]));
            this.pcMUX.setInput(1, jump);
        } else if (op.equalsIgnoreCase("jal")) {
            this.controllerWire.setData(16);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            WriteRegister.setDestinationRegister("$ra");
            WriteRegister.setData(this.PC_MUXOut.getData());
            System.out.println("pc mux out is " + this.PC_MUXOut.getData());
            ALURegister.setData(this.PC_MUXOut.getData() + 4);
            this.ALU_MUXRegister.setData(0);
            this.registerFile.setFirstOperandDirectDestination();
            this.jump.setData(getJumpValue(arr[1]));
            this.pcMUX.setInput(1, jump);

        } else if (op.equalsIgnoreCase("jr")) {
            this.controllerWire.setData(17);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            this.jump.setData(this.registerFile.registers.get(arr[1]));
            this.pcMUX.setInput(1, jump);
        } else if (op.equalsIgnoreCase("slt")) {
            this.controllerWire.setData(18);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            WriteRegister.setDestinationRegister(arr[1]);
            WriteRegister.setData(this.registerFile.registers.get(arr[1]));
            ALU_MUXRegister.setDestinationRegister(arr[3]);
            ALU_MUXRegister.setData(this.registerFile.registers.get(arr[3]));
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.registerFile.setFirstOperandDestination();
            this.registerFile.setSecondOperandDestination();
        } else if (op.equalsIgnoreCase("slti")) {
            this.controllerWire.setData(19);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            WriteRegister.setDestinationRegister(arr[1]);
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.ALU_MUXSecondInput.setData(Integer.parseInt(arr[3]));
            this.aluMUX.setInput(1, this.ALU_MUXSecondInput);
            this.registerFile.setFirstOperandDestination();
        } else if (op.equalsIgnoreCase("sltu")) {
            this.controllerWire.setData(20);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
            WriteRegister.setDestinationRegister(arr[1]);
            WriteRegister.setData(this.registerFile.registers.get(arr[1]));
            ALU_MUXRegister.setDestinationRegister(arr[3]);
            ALU_MUXRegister.setData(this.registerFile.registers.get(arr[3]));
            ALURegister.setData(this.registerFile.registers.get(arr[2]));
            ALURegister.setDestinationRegister(arr[2]);
            this.registerFile.setFirstOperandDestination();
            this.registerFile.setSecondOperandDestination();
        } else if (op.equalsIgnoreCase("sltui")) {
            this.controllerWire.setData(21);
            this.wiresLog.get(COMMANDS_COUNTER).get(1).add("CONTROLLER WIRE: " + this.controllerWire.toString());
            this.controller.setControls();
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

        this.wiresLog.get(COMMANDS_COUNTER).get(1).add("ALU FIRST INPUT: " + this.ALURegister.toString());
        this.wiresLog.get(COMMANDS_COUNTER).get(1).add("ALU MUX INPUT: " + this.ALU_MUXRegister.toString());
        this.wiresLog.get(COMMANDS_COUNTER).get(1).add("WRITE REGISTER: " + this.WriteRegister.toString());
        this.wiresLog.get(COMMANDS_COUNTER).get(1).add("JUMP: " + this.jump.toString());
        this.wiresLog.get(COMMANDS_COUNTER).get(1).add("ALU MUX SECOND INPUT: " + this.ALU_MUXSecondInput.toString());


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

    public void printWiresLog() {
        int cycles = 0;
        for (int i = 0; i < this.wiresLog.size(); i++) {
            System.out.println("For command " + i);
            for (int j = 0; j < this.wiresLog.get(i).size(); j++) {
                cycles++;
                System.out.println("For Cycle " + j);
                for (int k = 0; k < this.wiresLog.get(i).get(j).size(); k++) {
                    System.out.println(this.wiresLog.get(i).get(j).get(k));
                }
            }
        }
        System.out.println("The program used " + cycles + " cycles");
    }

    /**
     * The method uses the factorial logic to add numbers to each other instead
     * of multiplying FACT(4) = 4 + FACT(3) FACT(0) = 1 FACT(1) = 1
     */
    public static void testRecursiveCode() {
        ArrayList<String> file = new ArrayList<String>();
        file.add("ORG 100");
        file.add(".data");
        file.add("a: .word 0xFF");
        file.add("b: .word 5");
        file.add(".text");        
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
        is.printWiresLog();

    }

    /**
     * The method tests the functionality of arithmetic instructions.
     */
    public static void testArithmeticInstructions() {
        ArrayList<String> file = new ArrayList<String>();
        file.add("sll $t0 $t0 2");
        file.add("srl $t1 $t0 2");
        file.add("and $t2 $t1 $t0");
        file.add("andi $t3 $t0 2");
        file.add("or $t4 $t3 $t2");
        file.add("ori $t5 $t4 5");
        file.add("nor $t6 $t5 $t4");
        file.add("slt $s0 $t0 $t4");
        file.add("sltui $s1 $t0 2");
        file.add("sltu $s2 $s1 $s0");
        InstructionMemory is = new InstructionMemory(file);
        is.printWiresLog();
       
    }

    /**
     * The Method keeps subtracting one from t1, until $t1 becomes 0
     */
    public static void testSubtractor() {
        ArrayList<String> file = new ArrayList<String>();
        file.add("addi $t0 $zero 1");
        file.add("addi $t1 $zero 5");
        file.add("CHECK: bne $t1 $zero LOOP");
        file.add("LOOP: sub $t1 $t1 $t0");
        file.add("beq $t1 $zero END");
        file.add("j CHECK");
        file.add("END: end");
        InstructionMemory is = new InstructionMemory(file);
        is.printWiresLog();


    }

    public static void main(String[] abbas) {

        testRecursiveCode();
        testArithmeticInstructions();
        testSubtractor();

    }
}