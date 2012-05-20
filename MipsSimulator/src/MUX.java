
public abstract class MUX {
	int [] inputs = new int[2];
	int out;
	int select;
	public void setInputs(int [] in){
		inputs = in;
	}
	public void setSelect(int sel){
		select = sel;
                System.out.println("Select set to " + sel);
	}
	public int getOutput(){
		return inputs[select];
	}
        public void setInput(int index, int value)
        {
            inputs[index] = value;
        }
	public abstract void forward();
}
