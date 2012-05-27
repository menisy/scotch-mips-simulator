package logic;


import java.io.File;
import java.util.ArrayList;

public class Organizer {

    InstructionMemory instructionMemory;
    RegistersFile registers;
    ALU alu;
    MUX aluMUX, regMUX, pcMUX;
    DataMemory memory;
    Wire ALU_MUXControl = new Wire("Control", "ALU_MUX", "", 0);
    Wire REG_MUXControl = new Wire("Control", "REG_MUX", "", 0);
    Wire PC_MUXControl = new Wire("Control", "PC_MUX", "", 0);
    Wire ALUControl = new Wire("Control", "ALU", "", 0);
    Wire readWire = new Wire("Control", "Memory", "", 0);
    Wire writeWire = new Wire("Control", "Memory", "", 0);
    Wire registerWrite = new Wire("Control", "Register File", "", 0);

    public Organizer() {
    }

    /**
     * The Method sets the instance variables of the Organizer
     *
     * @param alu
     * @param aluMUX
     * @param registers
     * @param instructionMemory
     * @param regMUX
     * @param memory
     * @param pcMUX
     */
    public void setInstances(ALU alu, ALU_MUX aluMUX, RegistersFile registers, InstructionMemory instructionMemory, REG_MUX regMUX, DataMemory memory, PC_MUX pcMUX) {
        this.alu = alu;
        this.alu.setALUControl(this.ALUControl);
        this.aluMUX = aluMUX;
        this.registers = registers;
        this.instructionMemory = instructionMemory;
        this.regMUX = regMUX;
        this.memory = memory;
        this.pcMUX = pcMUX;
    }

    /**
     * The function takes in the operation to be executed and sets the control
     * signals accordingly
     *
     * @param operation
     */
    public void setControls(String operation) {
        System.out.println("Setting the controls for " + operation);
        if (operation.equalsIgnoreCase("add")) {
            //alu.setControl(1);
            this.ALUControl.setData(1);
            this.ALU_MUXControl.setData(0);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
            //aluMUX.setSelect(0);
            //regMUX.setSelect(0);
            this.PC_MUXControl.setData(0);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.registerWrite.setData(1);
            registers.setRegWriteControl(this.registerWrite);
        } else if (operation.equalsIgnoreCase("addi")) {
            //alu.setControl(1);
            this.ALUControl.setData(1);
            this.ALU_MUXControl.setData(1);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
            this.PC_MUXControl.setData(0);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.registerWrite.setData(1);
            registers.setRegWriteControl(this.registerWrite);
        } else if (operation.equalsIgnoreCase("sub")) {
            //alu.setControl(2);
            this.ALUControl.setData(2);
            this.ALU_MUXControl.setData(0);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
            this.PC_MUXControl.setData(0);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.registerWrite.setData(1);
            registers.setRegWriteControl(this.registerWrite);
        } else if (operation.equalsIgnoreCase("lw")) {
//          alu.setControl(8); // TODO 
            this.ALUControl.setData(8);
            this.REG_MUXControl.setData(1);
            regMUX.setSelect(this.REG_MUXControl);
            this.readWire.setData(1);
            memory.setReadWire(this.readWire);
            this.PC_MUXControl.setData(0);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.registerWrite.setData(1);
            registers.setRegWriteControl(this.registerWrite);
        } else if (operation.equalsIgnoreCase("sw")) {
            //alu.setControl(9); // TODO
            this.ALUControl.setData(9);
            //regMUX.setSelect(1);
            this.writeWire.setData(1);
            memory.setWriteWire(writeWire);
            this.PC_MUXControl.setData(0);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.registerWrite.setData(0);
            registers.setRegWriteControl(this.registerWrite);
        } else if (operation.equalsIgnoreCase("sll")) {
            //alu.setControl(6);
            this.ALUControl.setData(6);
            this.ALU_MUXControl.setData(1);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
            this.PC_MUXControl.setData(0);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.registerWrite.setData(1);
            registers.setRegWriteControl(this.registerWrite);
        } else if (operation.equalsIgnoreCase("srl")) {
            //alu.setControl(5);
            this.ALUControl.setData(5);
            this.ALU_MUXControl.setData(1);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
            this.PC_MUXControl.setData(0);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.PC_MUXControl.setData(0);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.registerWrite.setData(1);
            registers.setRegWriteControl(this.registerWrite);
        } else if (operation.equalsIgnoreCase("and")) {
            //alu.setControl(4);
            this.ALUControl.setData(4);
            this.ALU_MUXControl.setData(0);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
            this.PC_MUXControl.setData(0);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.registerWrite.setData(1);
            registers.setRegWriteControl(this.registerWrite);
        } else if (operation.equalsIgnoreCase("andi")) {
            //alu.setControl(4);
            this.ALUControl.setData(4);
            this.ALU_MUXControl.setData(1);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
            this.PC_MUXControl.setData(0);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.registerWrite.setData(1);
            registers.setRegWriteControl(this.registerWrite);
        } else if (operation.equalsIgnoreCase("or")) {
            //alu.setControl(3);
            this.ALUControl.setData(3);
            this.ALU_MUXControl.setData(0);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
            this.PC_MUXControl.setData(0);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.registerWrite.setData(1);
            registers.setRegWriteControl(this.registerWrite);
        } else if (operation.equalsIgnoreCase("ori")) {
            //alu.setControl(3);
            this.ALUControl.setData(3);
            this.ALU_MUXControl.setData(1);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
            this.PC_MUXControl.setData(0);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.registerWrite.setData(1);
            registers.setRegWriteControl(this.registerWrite);
        } else if (operation.equalsIgnoreCase("nor")) {
            //alu.setControl(7);
            this.ALUControl.setData(7);
            this.ALU_MUXControl.setData(0);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
            this.PC_MUXControl.setData(0);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.registerWrite.setData(1);
            registers.setRegWriteControl(this.registerWrite);
        } else if (operation.equalsIgnoreCase("beq")) {
            this.PC_MUXControl.setData(0);
            this.ALUControl.setData(10);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.ALU_MUXControl.setData(0);
            this.registerWrite.setData(0);
            registers.setRegWriteControl(this.registerWrite);
            aluMUX.setSelect(this.ALU_MUXControl);
        } else if (operation.equalsIgnoreCase("bne")) {
            this.PC_MUXControl.setData(0);
            this.ALUControl.setData(11);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.ALU_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            this.registerWrite.setData(0);
            registers.setRegWriteControl(this.registerWrite);

        } else if (operation.equalsIgnoreCase("jr")) {
            this.PC_MUXControl.setData(1);
            this.ALUControl.setData(12);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.ALU_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            this.registerWrite.setData(0);
            registers.setRegWriteControl(this.registerWrite);
        } else if (operation.equalsIgnoreCase("j")) {
            this.PC_MUXControl.setData(1);
            this.ALUControl.setData(12);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.ALU_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            this.registerWrite.setData(0);
            registers.setRegWriteControl(this.registerWrite);

        } else if (operation.equalsIgnoreCase("jal")) {
            this.PC_MUXControl.setData(1);
            this.ALUControl.setData(16);
            this.PC_MUXControl.setData(1);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.ALU_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            this.REG_MUXControl.setData(0);
            regMUX.setSelect(this.REG_MUXControl);
            this.registerWrite.setData(1);
            registers.setRegWriteControl(this.registerWrite);

        } else if (operation.equalsIgnoreCase("slt")) {
            this.ALUControl.setData(13);
            this.ALU_MUXControl.setData(0);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
            this.PC_MUXControl.setData(0);
            this.pcMUX.setSelect(this.PC_MUXControl);
            this.registerWrite.setData(1);
            registers.setRegWriteControl(this.registerWrite);
        } else if (operation.equalsIgnoreCase("slti")) {
            this.ALUControl.setData(13);
            this.ALU_MUXControl.setData(1);
            this.REG_MUXControl.setData(0);
            this.PC_MUXControl.setData(0);
            this.registerWrite.setData(1);
            registers.setRegWriteControl(this.registerWrite);
            this.pcMUX.setSelect(this.PC_MUXControl);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
        } else if (operation.equalsIgnoreCase("sltu")) {
            this.ALUControl.setData(14);
            this.ALU_MUXControl.setData(0);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
            this.PC_MUXControl.setData(0);
            this.registerWrite.setData(1);
            registers.setRegWriteControl(this.registerWrite);
            this.pcMUX.setSelect(this.PC_MUXControl);
        } else if (operation.equalsIgnoreCase("sltui")) {
            this.ALUControl.setData(14);
            this.ALU_MUXControl.setData(1);
            this.registerWrite.setData(1);
            registers.setRegWriteControl(this.registerWrite);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
            this.PC_MUXControl.setData(0);
            this.pcMUX.setSelect(this.PC_MUXControl);
        } else if (operation.equalsIgnoreCase("beq")) {
            this.ALUControl.setData(14);
            this.ALU_MUXControl.setData(1);
            aluMUX.setSelect(this.ALU_MUXControl);
            this.registerWrite.setData(0);
            registers.setRegWriteControl(this.registerWrite);
        }
        instructionMemory.wiresLog.get(instructionMemory.COMMANDS_COUNTER).get(1).add("ALU CONTROL: " + this.ALUControl.toString());
        instructionMemory.wiresLog.get(instructionMemory.COMMANDS_COUNTER).get(1).add("ALU MUX CONTROL: " + this.ALU_MUXControl.toString());
        instructionMemory.wiresLog.get(instructionMemory.COMMANDS_COUNTER).get(1).add("PC MUX CONTROL: " + this.PC_MUXControl.toString());
        instructionMemory.wiresLog.get(instructionMemory.COMMANDS_COUNTER).get(1).add("REG MUX CONTROL: " + this.REG_MUXControl.toString());
        instructionMemory.wiresLog.get(instructionMemory.COMMANDS_COUNTER).get(1).add("MEMORY READ: " + this.readWire.toString());
        instructionMemory.wiresLog.get(instructionMemory.COMMANDS_COUNTER).get(1).add("MEMORY WRITE: " + this.writeWire.toString());
        instructionMemory.wiresLog.get(instructionMemory.COMMANDS_COUNTER).get(1).add("REGISTER WRITE: " + this.registerWrite.toString());
        this.setRegisterWriteControl();
    }
    //TODO, Change this to wires.

    public void setRegisterWriteControl() {
        // registers.setRegWrite();
        //this.registerWrite.setData(1);
        //registers.setRegWriteControl(this.registerWrite);
    }

    public static void main(String[] args) {
        ArrayList<String> file = new ArrayList<String>();
        file.add("add $t0 $t1 $t2");

    }
}
