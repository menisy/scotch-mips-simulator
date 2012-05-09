package Commands;

import Components.Register;

public class ADD extends ALU_Command {
	
	public ADD(Register result, Register operand1, Register operand2) {
		super("ADD");
		Object[] operands = {result, operand1, operand2};
		setOperands(operands);
	}
	public void run() {
		Register result = (Register) operands[0];
		int val1 = ((Register) operands[1]).getContent();
		int val2 = ((Register) operands[2]).getContent();
		result.setValue(val1 + val2);
	}
}
