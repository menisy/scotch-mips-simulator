
import java.util.ArrayList;
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
    Wire addressWire;
    Wire readWire;
    Wire writeWire;
    REG_MUX regMUX;
    Integer address;
    Integer data;
    HashMap<Integer, Integer> memory = new HashMap<Integer, Integer>();
    Wire dataWire;
    Wire REG_MUXInput = new Wire("Data Memory", "REG_MUX", "", 0);
    InstructionMemory instructionMemory;

    public DataMemory(REG_MUX regMUX, InstructionMemory instructionMemory) {
        this.regMUX = regMUX;
        this.instructionMemory = instructionMemory;
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
                    readData = this.memory.get(this.addressWire.getData());
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
        this.instructionMemory.wiresLog.get(this.instructionMemory.COMMANDS_COUNTER).add(new ArrayList<String>());
        this.instructionMemory.wiresLog.get(this.instructionMemory.COMMANDS_COUNTER).get(3).add(
                "REG MUX SECOND INPUT: " + this.REG_MUXInput.toString());

    }

    /**
     * The function writes data in the memory
     */
    public void write() {
        System.out.println("WRITE CALLED");
        try {
            if (this.writeWire.getData() == 1) {
                System.out.println("Memory writing ");
                this.memory.put(this.addressWire.getData(), this.dataWire.getData());
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

    public void setAdssdress(Integer address) {
        System.out.println("Address set to " + address);
        this.address = address;
        this.read();
        this.write();
    }
    public void setAddressWire(Wire addressWire)
    {
        
        this.addressWire = addressWire;
        this.instructionMemory.wiresLog.get(this.instructionMemory.COMMANDS_COUNTER).get(2).add("ADDRESS WIRE: " + this.addressWire);
        System.out.println("address wire set to " + this.addressWire.toString());
        this.read();
        this.write();
    }
}
