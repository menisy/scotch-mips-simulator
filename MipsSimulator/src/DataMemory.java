
import java.util.HashMap;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Nada
 */
public class DataMemory {

    boolean read = false;
    boolean write = false;
    REG_MUX regMUX;
    Integer address;
    Integer data;
    HashMap<Integer, Integer> memory = new HashMap<Integer, Integer>();
    Wire dataWire;
    Wire REG_MUXInput = new Wire("Data Memory", "REG_MUX", "", 0);

    public DataMemory(REG_MUX regMUX) {
        this.regMUX = regMUX;
    }

    public void setWrite() {
        this.write = true;

    }

    public void setRead() {
        this.read = true;
    }

    public void read() {
        if (this.read) {
            System.out.println("Memory reading at address  " + this.address);
            this.REG_MUXInput.setData(this.memory.get(this.address));
            this.regMUX.setInput(1, this.REG_MUXInput);
            this.regMUX.forward();
        }
        this.read = false;
    }

    public void write() {
        if (this.write) {
            System.out.println("Memory writing ");
            this.memory.put(this.address, this.dataWire.getData());
            System.out.println("Address " + this.address + " in the memory is now " + this.memory.get(this.address));
        }
        this.write = false;
    }

    public void setDataWire(Wire data) {
        this.dataWire = data;
        System.out.println("Data set to " + data);
    }

    public void setAddress(Integer address) {
        System.out.println("Address set to " + address);
        this.address = address;
        this.read();
        this.write();
    }
}
