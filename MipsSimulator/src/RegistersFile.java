
import java.util.HashMap;
import java.util.Map;


public class RegistersFile {
    Map<String,Integer> registers = new HashMap<String,Integer>();
    String ALURegister;
    String ALU_MUXRegister;
    String writeRegister;
    ALU alu;
    ALU_MUX aluMUX;
    boolean regWrite = false;
    public RegistersFile()
    {
        this.populate();
    }
    public void setInstances(ALU alu, ALU_MUX aluMUX)
    {
        this.alu = alu;
        this.aluMUX = aluMUX;
    }
    public int read(String reg){
    	return registers.get(reg);
    }
    public boolean write (int val){
    	registers.put(this.writeRegister, val);
        this.regWrite = false;
        System.out.println("Value of Register  " + this.writeRegister + " is now " + this.registers.get(this.writeRegister));

    	return true;
    }
    public void setRegWrite()
    {
        this.regWrite = true;
        System.out.println("RegWrite became " + this.regWrite);
    }
    public void setALURegister(String register)
    {
        this.ALURegister = register;
        System.out.println("ALU Register is now " +this.ALURegister);
        alu.setOp1(registers.get(register));
    }
    public void setALU_MUXRegister(String register)
    {
        this.ALU_MUXRegister = register;
        System.out.println("ALU_MUX Register is now " +this.ALU_MUXRegister);
        aluMUX.setInput(0, this.registers.get(register));

    }
    public void setWriteRegister(String register)
    {
        this.writeRegister = register;
        System.out.println("Write Register is now " +this.writeRegister);

    }
    public void populate(){ // yahia kan hy3mel trick
       registers.put("$zero", 0);
       registers.put("$at", 0);
       registers.put("$v0", 0);
       registers.put("$v1", 0);
       registers.put("$a0", 0);
       registers.put("$a1", 0);
       registers.put("$a2", 0);
       registers.put("$a3", 0);
       registers.put("$t0", 0);
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
