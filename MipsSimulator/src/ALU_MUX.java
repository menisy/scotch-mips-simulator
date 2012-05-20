
import src.*;
public class ALU_MUX extends MUX{
	ALU alu;
        
	public ALU_MUX(ALU alu){
		this.alu = alu;
	}
	public void forward(){
            System.out.println("ALU MUX is forwarding " + super.getOutput());
		alu.setOp2(super.getOutput());
	}
}
