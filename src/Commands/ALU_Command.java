package Commands;

public abstract class ALU_Command {
	String operation;
	Object[] operands;
	
	public ALU_Command(String operation) {
		this.operation = operation;
	}
	public void setOperands(Object[] operands) {
		this.operands = operands;
	}
	public abstract void run();
}
