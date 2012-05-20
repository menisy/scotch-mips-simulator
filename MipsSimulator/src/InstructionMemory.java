import java.util.ArrayList;
import java.util.HashMap;
import src.*;

public class InstructionMemory {
	private int pc;
	boolean done;
	HashMap<Integer, String> instructions = new HashMap<Integer, String>();
        ALU alu = new ALU();
        ALU_MUX aluMUX = new ALU_MUX(alu);
        Organizer controller = new Organizer();
        RegistersFile registerFile = new RegistersFile();
	public InstructionMemory(ArrayList<String> file) {
		String firstLine = file.get(0);
		String[] arr = firstLine.split(" ");
		int startingAddress;
		if (arr[0].equalsIgnoreCase("ORG"))
			startingAddress = Integer.parseInt(arr[1]);
		else
			startingAddress = 0;
                System.out.println("Starting address is " + startingAddress);
                System.out.println("File size is " +file.size());
		for (int i = 1, j = startingAddress; i <= file.size(); i++, j += 4) {
			instructions.put(j, file.get(i-1));
                        System.out.println("putting in slot " + j + " instruction " +file.get(i-1));
		}
                controller.setInstances(alu,aluMUX,registerFile,this);
                registerFile.setInstances(alu, aluMUX);
                alu.setInstances(controller,registerFile);
                this.start();
	}
	public boolean isDone(){
		return done;
	}
	public int getPC() {
		return pc;
	}
        public void start(){
		while(!this.isDone()){
                       if(instructions.get(pc) == null)
                       {
                           this.done = true;
                           break;
                       }
			this.fetchInstruction();
		}
	}

	public void fetchInstruction() {
            System.out.println("Fetching instruction at " + pc);
		String instruction = instructions.get(pc);
		String [] arr = instruction.split(" ");
		if(arr[0].equalsIgnoreCase("end")) done = true;
		pc += 4;
		this.decode(instruction);
	}
        public void decode(String instruction)
        {
            System.out.println("the instruction is " + instruction);
            String [] arr = instruction.split(" ");
            String op = arr[0];
            this.controller.setControls(arr[0]);
		if(op.equalsIgnoreCase("add")){
			registerFile.setWriteRegister(arr[1]);
                        registerFile.setALURegister(arr[2]);
                        registerFile.setALU_MUXRegister(arr[3]);
		}else if(op.equalsIgnoreCase("addi")){
			registerFile.setWriteRegister(arr[1]);
                        registerFile.setALURegister(arr[2]);
                        aluMUX.setInput(1,Integer.parseInt(arr[3]));
		}else if(op.equalsIgnoreCase("sub")){
			registerFile.setWriteRegister(arr[1]);
                        registerFile.setALURegister(arr[2]);
                        registerFile.setALU_MUXRegister(arr[3]);
		}else if(op.equalsIgnoreCase("lw")){
			alu.setControl(1); // TODO 
		}else if(op.equalsIgnoreCase("sw")){
			alu.setControl(1); // TODO
		}else if(op.equalsIgnoreCase("sll")){
			registerFile.setWriteRegister(arr[1]);
                        registerFile.setALURegister(arr[2]);
		}else if(op.equalsIgnoreCase("srl")){
			registerFile.setWriteRegister(arr[1]);
                        registerFile.setALURegister(arr[2]);
		}else if(op.equalsIgnoreCase("and")){
			registerFile.setWriteRegister(arr[1]);
                        registerFile.setALURegister(arr[2]);
                        registerFile.setALU_MUXRegister(arr[3]);
		}else if(op.equalsIgnoreCase("andi")){
			registerFile.setWriteRegister(arr[1]);
                        registerFile.setALURegister(arr[2]);
		}else if(op.equalsIgnoreCase("or")){
			registerFile.setWriteRegister(arr[1]);
                        registerFile.setALURegister(arr[2]);
                        registerFile.setALU_MUXRegister(arr[3]);
		}else if(op.equalsIgnoreCase("ori")){
			registerFile.setWriteRegister(arr[1]);
                        registerFile.setALURegister(arr[2]);
		}else if(op.equalsIgnoreCase("nor")){
			registerFile.setWriteRegister(arr[1]);
                        registerFile.setALURegister(arr[2]);
                        registerFile.setALU_MUXRegister(arr[3]);
		}else if(op.equalsIgnoreCase("beq")){
			alu.setControl(1); // TODO
		}else if(op.equalsIgnoreCase("bne")){
			alu.setControl(1); // TODO
		}else if(op.equalsIgnoreCase("j")){
			alu.setControl(1); // TODO
		}else if(op.equalsIgnoreCase("jal")){
			alu.setControl(1); // TODO
		}else if(op.equalsIgnoreCase("jr")){
			alu.setControl(1); //TODO
		}else if(op.equalsIgnoreCase("slt")){
			alu.setControl(1); //TODO
		}else if(op.equalsIgnoreCase("slti")){
			alu.setControl(1); // TODO
		}else if(op.equalsIgnoreCase("sltu")){
			alu.setControl(1); // TODO
		}else if(op.equalsIgnoreCase("sltui")){
			alu.setControl(1); // TODO
		}
                this.aluMUX.forward();
                this.alu.doOperation();
        }
        public static void main(String[]abbas)
        {
            ArrayList<String> file = new ArrayList<String>();
                file.add("addi $t0 $zero 4");
                file.add("add $t1 $t1 $t2");
                file.add("sub $t2 $t1 $t0");
                new InstructionMemory(file);
        }
}
