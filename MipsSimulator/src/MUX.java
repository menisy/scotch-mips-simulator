
public abstract class MUX {

    Wire[] inputs = new Wire[2];
    Wire outputWire;
    Wire select;

    public void setInputs(Wire[] in) {
        inputs = in;
    }

    public void setSelect(Wire sel) {
        select = sel;
        System.out.println("Select set to " + sel);
    }

    public Wire getOutput() {
        System.out.println("Select is " + this.select);
        return inputs[select.getData()];
    }

    public void setInput(int index, Wire value) {
        inputs[index] = value;
        System.out.println("Input set in mux");
    }

    public abstract void forward();
}
