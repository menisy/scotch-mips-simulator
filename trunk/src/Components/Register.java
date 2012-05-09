package Components;

public class Register {
	final String name;
	int content;
	boolean load, write, reset;
	
	public Register(String name) {
		this.name = name;
	}
	public void setValue(int value) {
		content = value;
	}
	public int getContent() {
		return content;
	}
}
