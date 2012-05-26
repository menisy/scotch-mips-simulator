
import java.util.ArrayList;
import java.util.HashMap;

public class InstructionMemory {

    private int pc;
    boolean done;
    HashMap<Integer, String> instructions = new HashMap<Integer, String>();
    Organizer controller = new Organizer();
    Wire ALURegister = new Wire("Instruction Memory", "Register File", "", 0);
    Wire ALU_MUXRegister = new Wire("Instruction Memory", "Register File", "", 0);
    Wire WriteRegister = new Wire("Instruction Memory", "Register File", "", 0);
    RegistersFile registerFile = new RegistersFile(ALURegister, ALU_MUXRegister, WriteRegister);
    ALU alu = new ALU(ALURegister, ALU_MUXRegister, WriteRegister);
    ALU_MUX aluMUX = new ALU_MUX(alu);
    REG_MUX regMUX = new REG_MUX(registerFile);
    DataMemory memory = new DataMemory(regMUX);
    Wire ALU_MUXSecondInput = new Wire("Instrucion Memory", "ALU_MUX", "", 0);

    public InstructionMemory(ArrayList<String> file) {
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
            instructions.put(j, file.get(i - 1));
            System.out.println("putting in slot " + j + " instruction " + file.get(i - 1));
        }
        controller.setInstances(alu, aluMUX, registerFile, this, regMUX, memory);
        registerFile.setInstances(alu, aluMUX, memory);
        alu.setInstances(controller, registerFile, regMUX, memory);
        this.start();
    }

    public boolean isDone() {
        return done;
    }

    public int getPC() {
        return pc;
    }

    public void start() {
        while (!this.isDone()) {
            if (instructions.get(pc) == null) {
                this.done = true;
                break;
            }
            this.fetchInstruction();
        }
    }

    public void fetchInstruction() {
        System.out.println("Fetching instruction at " + pc);
        String instruction = instructions.get(pc);
        String[] arr = instruction.split(" ");
        if (arr[0].equalsIgnoreCase("end")) {
            done = true;
        }
        pc += 4;
        this.decode(instruction);
    }

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
            alu.setControl(1); // TODO
        } else if (op.equalsIgnoreCase("bne")) {
            alu.setControl(1); // TODO
        } else if (op.equalsIgnoreCase("j")) {
            alu.setControl(1); // TODO
        } else if (op.equalsIgnoreCase("jal")) {
            alu.setControl(1); // TODO
        } else if (op.equalsIgnoreCase("jr")) {
            alu.setControl(1); //TODO
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
        if (!(arr[0].equalsIgnoreCase("sw") || arr[0].equalsIgnoreCase("lw"))) {
            this.aluMUX.forward();
        }

        this.alu.doOperation();
        
    }

    public static void main(String[] abbas) {
        ArrayList<String> file = new ArrayList<String>();
        file.add("sll $t0 $t0 1");
        file.add("add $t0 $t0 $t0");
        file.add("sub $t1 $t0 $t0");

        file.add("or $t0 $t0 $t0");
        file.add("andi $t0 $t0 1");
        file.add("sw $t0 12($t0)");
        file.add("lw $t1 12($t0)");
        file.add("addi $t1 $t1 1");
        file.add("sltui $t2 $t1 -3");
        InstructionMemory is = new InstructionMemory(file);
        System.out.println(is.registerFile.registers.get("$t0"));
        System.out.println(is.registerFile.registers.get("$t1"));
        System.out.println(is.registerFile.registers.get("$t2"));
    }
}
