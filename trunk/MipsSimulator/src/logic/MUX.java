package logic;


public abstract class MUX {

    Wire[] inputs = new Wire[2];
    Wire outputWire;
    Wire select;

    /**
     * the method sets the inputs array to the in wires;
     *
     * @param in
     */
    public void setInputs(Wire[] in) {
        inputs = in;
    }

    /**
     * the Method sets the select wire of the MUX
     *
     * @param sel
     */
    public void setSelect(Wire sel) {
        select = sel;
        System.out.println("Select set to " + sel);
    }

    /**
     * the method returns the output of the MUX
     *
     * @return Wire
     */
    public Wire getOutput() {
        System.out.println("Select is " + this.select);
        return inputs[select.getData()];
    }

    /**
     * The method sets input of the wire at a given index
     *
     * @param index
     * @param value
     */
    public void setInput(int index, Wire value) {
        inputs[index] = value;
        System.out.println("Input set in mux");
    }

    public abstract void forward();
}
