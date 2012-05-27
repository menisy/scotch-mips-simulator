
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
    Wire readWire;
    Wire writeWire;
    REG_MUX regMUX;
    Integer address;
    Integer data;
    HashMap<Integer, Integer> memory = new HashMap<Integer, Integer>();
    Wire dataWire;
    Wire REG_MUXInput = new Wire("Data Memory", "REG_MUX", "", 0);

    public DataMemory(REG_MUX regMUX) {
        this.regMUX = regMUX;
    }

    public void setReadWire(Wire readWire) {
        System.out.println("read wire set");
        this.readWire = readWire;
    }

    public void setWriteWire(Wire writeWire) {
        System.out.println("write wire set");
        this.writeWire = writeWire;
    }

    /**
     * The method reads data from the memory and passes it to the regMUX
     */
    public void read() {
        try {
            if (this.readWire.getData() == 1) {
                System.out.println("Memory reading at address  " + this.address);
                int readData = 0;
                try {
                    readData = this.memory.get(this.address);
                } catch (Exception e) {
                    System.out.println("The address is empty");
                }
                this.REG_MUXInput.setData(readData);
                this.regMUX.setInput(1, this.REG_MUXInput);
                this.regMUX.forward();
            }
            this.readWire.setData(0);
        } catch (Exception e) {
            System.out.println("Can't read");
        }

    }

    /**
     * The function writes data in the memory
     */
    public void write() {
        try {
            if (this.writeWire.getData() == 1) {
                System.out.println("Memory writing ");
                this.memory.put(this.address, this.dataWire.getData());
                System.out.println("Address " + this.address + " in the memory is now " + this.memory.get(this.address));
            }
            this.writeWire.setData(0);
        } catch (Exception e) {
            System.out.println("can't write");
        }

    }

    /**
     * The method sets the memory data wire to the input data
     *
     * @param data
     */
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
