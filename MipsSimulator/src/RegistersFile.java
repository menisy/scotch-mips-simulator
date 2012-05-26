
import java.util.HashMap;
import java.util.Map;

public class RegistersFile {

    Map<String, Integer> registers = new HashMap<String, Integer>();
    //String ALURegister;
    //String ALU_MUXRegister;
    //String writeRegister;
    ALU alu;
    ALU_MUX aluMUX;
    DataMemory memory;
    boolean regWrite = false;
    Wire ALURegister;
    Wire ALU_MUXRegister;
    Wire writeRegister;
    Wire ALUFirstOperand;
    Wire ALU_MUXFirstOperand;
    Wire writeRegisterData;
    Wire data = new Wire("Register File", "Data Memory", "", 0);

    public RegistersFile(Wire ALURegister, Wire ALU_MUXRegister, Wire WriteRegister) {
        this.ALURegister = ALURegister;
        this.ALU_MUXRegister = ALU_MUXRegister;
        this.ALUFirstOperand = new Wire("Register File", "ALU", "", 0);
        this.ALU_MUXFirstOperand = new Wire("Register File", "ALU_MUX", "", 0);
        this.writeRegister = WriteRegister;
        this.populate();
    }

    public void setFirstOperandDestination() {
        String register = this.ALURegister.getDestinationRegister();
        if (register.contains("(")) {
            String[] splittedAddress = register.split("\\(");
            Integer offset = Integer.parseInt(splittedAddress[0]);
            String effectiveRegister = splittedAddress[1].substring(0, 3);
            System.out.println("the register is " + effectiveRegister);
            Integer registerData = this.registers.get(effectiveRegister);
            System.out.println("the register data is " + registerData);
            System.out.println("Setting address to " + (offset + registerData));
            this.ALUFirstOperand.setData(offset + registerData);

        } else {
            System.out.println("Setting first operand destination with register " + register);
            this.ALUFirstOperand.setDestinationRegister(register);
            this.ALUFirstOperand.setData(this.registers.get(register));
        }


    }

    public void setSecondOperandDestination() {
        String register = this.ALU_MUXRegister.getDestinationRegister();
        System.out.println("Setting second operand destination with register " + register);
        this.ALU_MUXFirstOperand.setDestinationRegister(register);
        this.ALU_MUXFirstOperand.setData(this.registers.get(register));
        System.out.println("ALU MUX FIRST OPERAND IS " + this.ALU_MUXFirstOperand);
        this.aluMUX.setInput(0, this.ALU_MUXFirstOperand);
    }

    public void setWriteRegister(Wire writeRegister) {
        this.writeRegister = writeRegister;
    }

    public void setWriteRegisterData(Wire writeRegisterData) {
        this.writeRegisterData = writeRegisterData;
        this.write(writeRegisterData.getData());
    }

    public void setInstances(ALU alu, ALU_MUX aluMUX, DataMemory memory) {
        this.alu = alu;
        this.aluMUX = aluMUX;
        this.memory = memory;
        this.alu.setALUFirstOperand(this.ALUFirstOperand);
    }

    public int read(String reg) {
        return registers.get(reg);
    }

    public boolean write(int val) {
        System.out.println("Destination register is " + this.writeRegister.getDestinationRegister() + " Destination data is " + this.writeRegister.getData());
        registers.put(this.writeRegister.getDestinationRegister(), val);
        this.regWrite = false;
        System.out.println("Value of Register  " + this.writeRegister + " is now " + this.registers.get(this.writeRegister));
        return true;
    }

    public void setRegWrite() {
        this.regWrite = true;
        System.out.println("RegWrite became " + this.regWrite);
    }
    /*
     * public void setALURegister(String register) { this.ALURegister =
     * register; System.out.println("ALU Register is now " +this.ALURegister);
     * if(register.length() > 3 && !register.equalsIgnoreCase("$zero")) {
     * System.out.println("Computing the address "); String[] splittedAddress =
     * register.split("\\("); Integer offset =
     * Integer.parseInt(splittedAddress[0]); String effectiveRegister =
     * splittedAddress[1].substring(0,3); System.out.println("the register is "
     * + effectiveRegister); Integer registerData =
     * this.registers.get(effectiveRegister); System.out.println("the register
     * data is " +registerData); System.out.println("Setting address to "+
     * (offset + registerData)); this.alu.setOp1(offset + registerData); } else
     * { alu.setOp1(registers.get(register));
     *
     *
     * }
     * }
     * public void setALU_MUXRegister(String register) { this.ALU_MUXRegister =
     * register; System.out.println("ALU_MUX Register is now "
     * +this.ALU_MUXRegister); this.registers.get(register); aluMUX.setInput(0,
     * this.registers.get(register));
     *
     * }
     * public void setWriteRegister(String register) { this.writeRegister =
     * register; System.out.println("Write Register is now "
     * +this.writeRegister);
     *
     * }
     */

    public void forward() {
        System.out.println(this.ALU_MUXRegister.toString());
        String x = this.ALU_MUXRegister.getDestinationRegister();
        System.out.println("Destination is " + x);
        this.data.setData(this.registers.get(this.ALU_MUXRegister.getDestinationRegister()));
        this.memory.setDataWire(this.data);
    }

    public void setInputs() {
        this.setFirstOperandDestination();
        this.setSecondOperandDestination();
    }

    public void populate() { // yahia kan hy3mel trick
        registers.put("$zero", 0);
        registers.put("$at", 0);
        registers.put("$v0", 0);
        registers.put("$v1", 0);
        registers.put("$a0", 0);
        registers.put("$a1", 0);
        registers.put("$a2", 0);
        registers.put("$a3", 0);
        registers.put("$t0", 3);
        registers.put("$t1", 0);
        registers.put("$t2", 0);
        registers.put("$t3", 0);
        registers.put("$t4", 0);
        registers.put("$t5", 0);
        registers.put("$t6", 0);
        registers.put("$t7", 0);
        registers.put("$s0", 0);
        registers.put("$s1", 0);
        registers.put("$s2", 0);
        registers.put("$s3", 0);
        registers.put("$s4", 0);
        registers.put("$s5", 0);
        registers.put("$s6", 0);
        registers.put("$s7", 0);
        registers.put("$t8", 0);
        registers.put("$t9", 0);
        registers.put("$k0", 0);
        registers.put("$k1", 0);
        registers.put("$gp", 0);
        registers.put("$sp", 0);
        registers.put("$fp", 0);
        registers.put("$ra", 0);
    }
}