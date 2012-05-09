package Components;
import java.util.HashMap;


public class Memory {
	private final int size;
	private final int wordSize;// (the same as registers)
	private HashMap <String, Integer> map; 
	private boolean read_write; //true => read, false => write
	private int currentAddress;

	public Memory(int size, int wordSize) {
		this.size = size;
		this.wordSize = wordSize;
	}
	void setCurrentAddress(int newAddress) {
		
	}
	int readWord(int address) {
		return address;
		
	}
	void writeWord(int address, int newWord) {
		
	}
	void setRead() {
		
	}
	void setWrite() {
		
	}
	void reset() {
		
	}

}
