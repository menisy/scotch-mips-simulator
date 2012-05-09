import java.util.HashMap;
import java.util.Map;


public class RegistersFile {
    Map<String,Integer> registers = new HashMap<String,Integer>();  
    public int read(String reg){
    	return registers.get(reg);
    }
    public boolean write(String reg, int val){
    	registers.put(reg, val);
    	return true;
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
