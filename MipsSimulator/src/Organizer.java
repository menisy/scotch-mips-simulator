import java.io.File;
import java.util.ArrayList;


public class Organizer {
	InstructionMemory instructionMemory;
	RegistersFile registers;
	ALU alu;
	MUX aluMUX,regMUX,pcMUX;
        public Organizer()
        {
            
        }
        public void setInstances(ALU alu, ALU_MUX aluMUX, RegistersFile registers, InstructionMemory instructionMemory)
        {
            this.alu = alu;
            this.aluMUX = aluMUX;
            this.registers = registers;
            this.instructionMemory = instructionMemory;
        }
	public Organizer(ArrayList<String> file){
		instructionMemory = new InstructionMemory(file);
		registers = new RegistersFile();
		alu = new ALU();
		aluMUX = new ALU_MUX(alu);
	// TODO	regMUX = new MUX();
	// TODO	pcMUX = new MUX();
	}

	public void setControls(String operation){
            System.out.println("Setting the controls for " + operation);
		if(operation.equalsIgnoreCase("add")){
			alu.setControl(1);
			aluMUX.setSelect(0);
		}else if(operation.equalsIgnoreCase("addi")){
			alu.setControl(1);
			aluMUX.setSelect(1);
		}else if(operation.equalsIgnoreCase("sub")){
			alu.setControl(2);
			aluMUX.setSelect(0);
		}else if(operation.equalsIgnoreCase("lw")){
			alu.setControl(1); // TODO 
		}else if(operation.equalsIgnoreCase("sw")){
			alu.setControl(1); // TODO
		}else if(operation.equalsIgnoreCase("sll")){
			alu.setControl(6);
                        aluMUX.setSelect(1);
		}else if(operation.equalsIgnoreCase("srl")){
			alu.setControl(5);
                        aluMUX.setSelect(1);
		}else if(operation.equalsIgnoreCase("and")){
			alu.setControl(4);
			aluMUX.setSelect(0);
		}else if(operation.equalsIgnoreCase("andi")){
			alu.setControl(4);
			aluMUX.setSelect(1);
		}else if(operation.equalsIgnoreCase("or")){
			alu.setControl(3);
			aluMUX.setSelect(0);
		}else if(operation.equalsIgnoreCase("ori")){
			alu.setControl(3);
			aluMUX.setSelect(1);
		}else if(operation.equalsIgnoreCase("nor")){
			alu.setControl(7);
			aluMUX.setSelect(0);
		}else if(operation.equalsIgnoreCase("beq")){
			alu.setControl(1); // TODO
		}else if(operation.equalsIgnoreCase("bne")){
			alu.setControl(1); // TODO
		}else if(operation.equalsIgnoreCase("j")){
			alu.setControl(1); // TODO
		}else if(operation.equalsIgnoreCase("jal")){
			alu.setControl(1); // TODO
		}else if(operation.equalsIgnoreCase("jr")){
			alu.setControl(1); //TODO
		}else if(operation.equalsIgnoreCase("slt")){
			alu.setControl(1); //TODO
		}else if(operation.equalsIgnoreCase("slti")){
			alu.setControl(1); // TODO
		}else if(operation.equalsIgnoreCase("sltu")){
			alu.setControl(1); // TODO
		}else if(operation.equalsIgnoreCase("sltui")){
			alu.setControl(1); // TODO
		}
		
	}
        public void setRegisterWriteControl()
        {
            registers.setRegWrite();
        }
	public static void main(String [] args){
		ArrayList<String> file = new ArrayList<String>();
                file.add("add $t0 $t1 $t2");
                
	}
	
}
