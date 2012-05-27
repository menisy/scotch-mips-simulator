
public class ALU_MUX extends MUX {

    ALU alu;

    public ALU_MUX(ALU alu) {
        super();
        this.alu = alu;
    }

    /**
     * The function sets the ALUSecondOperand to the output of the mux.
     */
    public void forward() {
        System.out.println("ALU MUX is forwarding " + super.getOutput());
        alu.setALUSecondOperand(super.getOutput());
    }
}
