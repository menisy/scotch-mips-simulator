package logic;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Omar Nada
 */
public class Wire {

    String source;
    String destination;
    String destinationRegister;
    int data;

    public Wire(String source, String destination, String destinationRegister, int data) {
        this.source = source;
        this.destination = destination;
        this.destinationRegister = destinationRegister;
        this.data = data;
    }

    public void setData(int data) {
        this.data = data;
        System.out.println("Data in wire From " + this.source + " To " + this.destination + " set to  " + data);
    }

    public String getDestinationRegister() {
        return this.destinationRegister;

    }

    public void setDestinationRegister(String destinationRegister) {
        this.destinationRegister = destinationRegister;
    }

    public int getData() {
        return this.data;
    }

    public String getSource() {
        return this.source;
    }

    public String getDestination() {
        return this.destination;
    }

    public String toString() {
        return "Source " + this.source + " Destination " + this.destination + " Register " + this.destinationRegister + " Data " + this.data;
    }
}
