
public class ALU {

    int op1, op2;
    //int aluOp; 
    int res;
    int isZero;
    Organizer controller;
    RegistersFile registerFile;
    REG_MUX regMUX;
    DataMemory memory;
    Wire ALURegister;
    Wire ALU_MUXRegister;
    Wire writeRegister;
    Wire ALUFirstOperand;
    Wire ALUSecondOperand;
    Wire REG_MUXFirstInput = new Wire("ALU", "REG_MUX", "", 0);
    Wire ALUControl;

    public ALU(Wire ALURegister, Wire ALU_MUXRegister, Wire writeRegister) {
        this.ALURegister = ALURegister;
        this.ALU_MUXRegister = ALU_MUXRegister;
        this.writeRegister = writeRegister;
    }

    public void setALUControl(Wire ALUControl) {
        this.ALUControl = ALUControl;
    }

    public void setALUFirstOperand(Wire ALUFirstOperand) {
        this.ALUFirstOperand = ALUFirstOperand;
        System.out.println("ALU First Operand set to " + ALUFirstOperand.toString());
    }

    public void setALUSecondOperand(Wire ALUSecondOperand) {
        this.ALUSecondOperand = ALUSecondOperand;
        System.out.println("ALU Second Operand set to " + ALUSecondOperand.toString());
    }

    public void setInstances(Organizer controller, RegistersFile registerFile, REG_MUX regMUX, DataMemory memory) {
        this.controller = controller;
        this.registerFile = registerFile;
        this.regMUX = regMUX;
        this.memory = memory;
    }
    //public void setOp1(int n){
    //  System.out.println("Setting operand 1 to " + n);	
    //op1 = n;
    //}

    public void setOp2(int n) {
        op2 = n;
    }
    /*
     * 1 - add 2 - sub 3 - or 4 - and 5 - shift right 6 - shift left 7 - nor
     *
     *
     *
     */

    public void doOperation() {
        System.out.println("Doing the operation ");
        int aluOp = this.ALUControl.getData();
        if (aluOp == 1) {
            res = this.ALUFirstOperand.getData() + this.ALUSecondOperand.getData();
            System.out.println("The result is " + res);
            this.REG_MUXFirstInput.setData(res);
            this.regMUX.setInput(0, this.REG_MUXFirstInput);
            this.regMUX.forward();
            this.writeRegister();
        } else if (aluOp == 2) {
            res = this.ALUFirstOperand.getData() - this.ALUSecondOperand.getData();;
            this.REG_MUXFirstInput.setData(res);
            this.regMUX.setInput(0, this.REG_MUXFirstInput);
            this.regMUX.forward();
            this.writeRegister();
        } else if (aluOp == 3) {
            res = this.ALUFirstOperand.getData() | this.ALUSecondOperand.getData();;
            this.REG_MUXFirstInput.setData(res);
            this.regMUX.setInput(0, this.REG_MUXFirstInput);
            this.regMUX.forward();
            this.writeRegister();
        } else if (aluOp == 4) {
            res = this.ALUFirstOperand.getData() & this.ALUSecondOperand.getData();;
            this.REG_MUXFirstInput.setData(res);
            this.regMUX.setInput(0, this.REG_MUXFirstInput);
            this.regMUX.forward();
            this.writeRegister();
        } else if (aluOp == 5) {
            res = this.ALUFirstOperand.getData() >> this.ALUSecondOperand.getData();;
            this.REG_MUXFirstInput.setData(res);
            this.regMUX.setInput(0, this.REG_MUXFirstInput);
            this.regMUX.forward();
            this.writeRegister();
        } else if (aluOp == 6) {
            res = this.ALUFirstOperand.getData() << this.ALUSecondOperand.getData();;
            this.REG_MUXFirstInput.setData(res);
            this.regMUX.setInput(0, this.REG_MUXFirstInput);
            this.regMUX.forward();
            this.writeRegister();
        } else if (aluOp == 7) {
            res = ~(this.ALUFirstOperand.getData() | this.ALUSecondOperand.getData());
            this.REG_MUXFirstInput.setData(res);
            this.regMUX.setInput(0, this.REG_MUXFirstInput);
            this.regMUX.forward();
            this.writeRegister();
        } else if (aluOp == 8) {
            memory.setAddress((Integer) this.ALUFirstOperand.getData());
        } else if (aluOp == 9) {

            memory.setAddress((Integer) this.ALUFirstOperand.getData());
        } else if (aluOp == 13)
        {
            System.out.println("First Operand data is " + this.ALUFirstOperand.getData());
            System.out.println("Second Operand data is " + this.ALUSecondOperand.getData());
            res = 0;
            if (this.ALUFirstOperand.getData() < this.ALUSecondOperand.getData())
                res = 1;
            System.out.println("The result is " + res);
            this.REG_MUXFirstInput.setData(res);
            this.regMUX.setInput(0, this.REG_MUXFirstInput);
            this.regMUX.forward();
            this.writeRegister();
        }
  
        else if (aluOp == 14)
        {
                    System.out.println("First Operand data is " + this.ALUFirstOperand.getData());
            System.out.println("Second Operand data is " + this.ALUSecondOperand.getData());
            res = 0;
            if (Math.abs(this.ALUFirstOperand.getData()) < Math.abs(this.ALUSecondOperand.getData()))
                res = 1;
            System.out.println("The result is " + res);
            this.REG_MUXFirstInput.setData(res);
            this.regMUX.setInput(0, this.REG_MUXFirstInput);
            this.regMUX.forward();
            this.writeRegister();
        }
        if (res == 0) {
            isZero = 1;
        } else {
            isZero = 0;
        }
        System.out.println("The result of the operation is " + res);

    }

    public int getZero() {
        return isZero;
    }

    public int getResult() {
        return res;
    }

    public void writeRegister() {
        System.out.println("alu writing register");
        controller.setRegisterWriteControl();
        // registerFile.write(res);
        //   regMUX.setInput(0, this.res);
        // regMUX.forward();
    }
}
