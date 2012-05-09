import java.util.ArrayList;
import java.util.HashMap;


public class InstructionMemory {
	private int pc;
	public InstructionMemory(ArrayList<String> file){
		
	}
	HashMap<Integer,String> instructions = new HashMap<Integer,String>();
	public int getPC(){
		return pc;
	}
	public Byte[] getInstruction(){
		Byte[] res = new Byte[4];
		for(int i = 0 ;i<4;i++){
			res[i] = res[pc++];
		}
		return res;
	}
}
