
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
    Wire ALUControl = new Wire("Control", "ALU", "", 0);

    public Organizer() {
    }

    public void setInstances(ALU alu, ALU_MUX aluMUX, RegistersFile registers, InstructionMemory instructionMemory, REG_MUX regMUX, DataMemory memory) {
        this.alu = alu;
        this.alu.setALUControl(this.ALUControl);
        this.aluMUX = aluMUX;
        this.registers = registers;
        this.instructionMemory = instructionMemory;
        this.regMUX = regMUX;
        this.memory = memory;
    }

    public Organizer(ArrayList<String> file) {
        instructionMemory = new InstructionMemory(file);
//		alu = new ALU();
        aluMUX = new ALU_MUX(alu);
        // TODO	regMUX = new MUX();
        // TODO	pcMUX = new MUX();
    }

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
        } else if (operation.equalsIgnoreCase("addi")) {
            //alu.setControl(1);
            this.ALUControl.setData(1);
            this.ALU_MUXControl.setData(1);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
        } else if (operation.equalsIgnoreCase("sub")) {
            //alu.setControl(2);
            this.ALUControl.setData(2);
            this.ALU_MUXControl.setData(0);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
        } else if (operation.equalsIgnoreCase("lw")) {
//          alu.setControl(8); // TODO 
            this.ALUControl.setData(8);
            this.REG_MUXControl.setData(1);
            regMUX.setSelect(this.REG_MUXControl);
            memory.setRead();
        } else if (operation.equalsIgnoreCase("sw")) {
            //alu.setControl(9); // TODO
            this.ALUControl.setData(9);
            //regMUX.setSelect(1);
            memory.setWrite();
        } else if (operation.equalsIgnoreCase("sll")) {
            //alu.setControl(6);
            this.ALUControl.setData(6);
            this.ALU_MUXControl.setData(1);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
        } else if (operation.equalsIgnoreCase("srl")) {
            //alu.setControl(5);
            this.ALUControl.setData(5);
            this.ALU_MUXControl.setData(1);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
        } else if (operation.equalsIgnoreCase("and")) {
            //alu.setControl(4);
            this.ALUControl.setData(4);
            this.ALU_MUXControl.setData(1);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
        } else if (operation.equalsIgnoreCase("andi")) {
            //alu.setControl(4);
            this.ALUControl.setData(4);
            this.ALU_MUXControl.setData(1);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
        } else if (operation.equalsIgnoreCase("or")) {
            //alu.setControl(3);
            this.ALUControl.setData(3);
            this.ALU_MUXControl.setData(1);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
        } else if (operation.equalsIgnoreCase("ori")) {
            //alu.setControl(3);
            this.ALUControl.setData(3);
            this.ALU_MUXControl.setData(1);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
        } else if (operation.equalsIgnoreCase("nor")) {
            //alu.setControl(7);
            this.ALUControl.setData(7);
            this.ALU_MUXControl.setData(1);
            this.REG_MUXControl.setData(0);
            aluMUX.setSelect(this.ALU_MUXControl);
            regMUX.setSelect(this.REG_MUXControl);
        } else if (operation.equalsIgnoreCase("beq")) {
            alu.setControl(1); // TODO
        } else if (operation.equalsIgnoreCase("bne")) {
            alu.setControl(1); // TODO
        } else if (operation.equalsIgnoreCase("j")) {
            alu.setControl(1); // TODO
        } else if (operation.equalsIgnoreCase("jal")) {
            alu.setControl(1); // TODO
        } else if (operation.equalsIgnoreCase("jr")) {
            alu.setControl(1); //TODO
        } else if (operation.equalsIgnoreCase("slt")) {
            alu.setControl(1); //TODO
        } else if (operation.equalsIgnoreCase("slti")) {
            alu.setControl(1); // TODO
        } else if (operation.equalsIgnoreCase("sltu")) {
            alu.setControl(1); // TODO
        } else if (operation.equalsIgnoreCase("sltui")) {
            alu.setControl(1); // TODO
        }

    }

    public void setRegisterWriteControl() {
        registers.setRegWrite();
    }

    public static void main(String[] args) {
        ArrayList<String> file = new ArrayList<String>();
        file.add("add $t0 $t1 $t2");

    }
}
