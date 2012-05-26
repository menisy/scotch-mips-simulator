
public class PC_MUX extends MUX {

    InstructionMemory instructionMemory;
    Wire PC_MUXOut;
    
    public PC_MUX(InstructionMemory instructionMemory, Wire PC_MUXOut) {
        super();
        this.instructionMemory = instructionMemory;
        this.PC_MUXOut = PC_MUXOut;
    }

    public void forward() {
        System.out.println("PC MUX is forwarding " + super.getOutput());
        this.PC_MUXOut.setData(super.getOutput().getData());
    }
}
