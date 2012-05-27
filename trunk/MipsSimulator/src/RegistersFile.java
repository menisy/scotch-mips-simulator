
import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegistersFile {

    Map<String, Integer> registers = new HashMap<String, Integer>();
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
    Wire regWriteControl;

    public RegistersFile(Wire ALURegister, Wire ALU_MUXRegister, Wire WriteRegister) {
        this.ALURegister = ALURegister;
        this.ALU_MUXRegister = ALU_MUXRegister;
        this.ALUFirstOperand = new Wire("Register File", "ALU", "", 0);
        this.ALU_MUXFirstOperand = new Wire("Register File", "ALU_MUX", "", 0);
        this.writeRegister = WriteRegister;
        this.populate();
    }

    /**
     * The Method sets the ALU first operand using the data in the wire coming
     * from the register file
     */
    public void setFirstOperandDestination() {
        String register = this.ALURegister.getDestinationRegister();
        if (register.contains("(")) {
            String[] splittedAddress = register.split("\\(");
            Integer offset = Integer.parseInt(splittedAddress[0]);
            /*
            String effectiveRegister = splittedAddress[1].substring(0, 3);
            */
            String effectiveRegister = splittedAddress[1].substring(0,splittedAddress[1].indexOf(')'));
            Integer registerData; 
            if (!effectiveRegister.startsWith("$")) {
	            System.out.println("the memory vairable is " + effectiveRegister);
            	registerData = InstructionMemory.DATA.get(effectiveRegister);
            	
	            System.out.println("the variable data is " + registerData);
	            System.out.println("Setting address to " + (offset + registerData));
            }else {
	            System.out.println("the register is " + effectiveRegister);
	            registerData = this.registers.get(effectiveRegister);
            
	            System.out.println("the register data is " + registerData);
	            System.out.println("Setting address to " + (offset + registerData));
            }
            this.ALUFirstOperand.setData(offset + registerData);

        } else {
            System.out.println("Setting first operand destination with register " + register);
            this.ALUFirstOperand.setDestinationRegister(register);
            this.ALUFirstOperand.setData(this.registers.get(register));
        }


    }

    public void setFirstOperandDirectDestination() {
        this.ALUFirstOperand.setData(this.ALURegister.getData());
    }

    /**
     * The Method sets the ALU first operand using the data in the wire coming
     * from the register file
     */
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
        System.out.println("Setting write control");
        this.writeRegisterData = writeRegisterData;
        this.memory.instructionMemory.wiresLog.get(this.memory.instructionMemory.COMMANDS_COUNTER).add(new ArrayList<String>());
        if(this.memory.instructionMemory.wiresLog.get(this.memory.instructionMemory.COMMANDS_COUNTER)
                .size() == 5)
        {
            this.memory.instructionMemory.wiresLog.get(this.memory.instructionMemory.COMMANDS_COUNTER)
                    .get(4).add("REGISTER WRITE: " + this.writeRegisterData.toString());
        }
        else
        {
              this.memory.instructionMemory.wiresLog.get(this.memory.instructionMemory.COMMANDS_COUNTER)
                    .get(3).add("REGISTER WRITE: " + this.writeRegisterData.toString());
        }
       
        try
        {
            this.write(writeRegisterData.getData());
        }
        catch(Exception e)
        {
            System.out.println("Write operation failed");
            e.printStackTrace();
        }
    }

    public void setInstances(ALU alu, ALU_MUX aluMUX, DataMemory memory) {
        this.alu = alu;
        this.aluMUX = aluMUX;
        this.memory = memory;
        this.alu.setALUFirstOperand(this.ALUFirstOperand);
    }

    /**
     * The Method write a value in the write register
     *
     * @param val
     * @return boolean
     */
    public boolean write(int val) throws Exception {
        System.out.println("writing");
        if (this.regWriteControl.getData() == 1) {
            if(this.writeRegister.getDestinationRegister().equalsIgnoreCase("$zero"))
                throw new Exception("$zero register is write protected");
            System.out.println("Destination register is " + this.writeRegister.getDestinationRegister() + " Destination data is " + this.writeRegister.getData());
            registers.put(this.writeRegister.getDestinationRegister(), val);
            this.regWrite = false;
            System.out.println("Value of Register  " + this.writeRegister + " is now " + this.registers.get(this.writeRegister));
            this.regWriteControl.setData(0);
            return true;
        }

        return false;

    }

    public void setRegWriteControl(Wire regWriteControl) {
        System.out.println("SETTING REGISTER WRITE CONTROL TO " + this.regWriteControl);
        this.regWriteControl = regWriteControl;
    }

    public void setRegWrite() {
        this.regWrite = true;
        System.out.println("RegWrite became " + this.regWrite);
    }


    /**
     * The method forwards the data wire to the memory
     */
    public void forward() {
        System.out.println(this.ALU_MUXRegister.toString());
        String x = this.ALU_MUXRegister.getDestinationRegister();
        System.out.println("Destination is " + x);
        this.data.setData(this.registers.get(this.ALU_MUXRegister.getDestinationRegister()));
        this.memory.setDataWire(this.data);
    }

    /**
     * The Method initialises the registers.
     */
    public void populate() { // yahia kan hy3mel trick
        registers.put("$zero", 0);
        registers.put("$at", 0);
        registers.put("$v0", 0);
        registers.put("$v1", 0);
        registers.put("$a0", 4);
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
