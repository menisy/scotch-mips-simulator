
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
    PC_MUX pcMUX;
    Wire PCMUXControl = new Wire("ALU", "PC_MUX", "", 0);

    public ALU(Wire ALURegister, Wire ALU_MUXRegister, Wire writeRegister) {
        this.ALURegister = ALURegister;
        this.ALU_MUXRegister = ALU_MUXRegister;
        this.writeRegister = writeRegister;
    }

    /**
     * The Method sets the PC_MUX in the alu to the input PC_MUX
     *
     * @param pcMUX
     */
    public void setPCMUX(PC_MUX pcMUX) {
        this.pcMUX = pcMUX;
    }

    /**
     * The Method sets the ALUControl Wire to the input ALUControl
     *
     * @param ALUControl
     */
    public void setALUControl(Wire ALUControl) {
        this.ALUControl = ALUControl;
    }

    /**
     * The Method sets the first operand wire to the input ALUFirstOperand
     *
     * @param ALUFirstOperand
     */
    public void setALUFirstOperand(Wire ALUFirstOperand) {
        this.ALUFirstOperand = ALUFirstOperand;
        System.out.println("ALU First Operand set to " + ALUFirstOperand.toString());
    }

    /**
     * The Method sets the second operand wire to the input ALUSecondOperand
     *
     * @param ALUSecondOperand
     */
    public void setALUSecondOperand(Wire ALUSecondOperand) {
        this.ALUSecondOperand = ALUSecondOperand;
        System.out.println("ALU Second Operand set to " + ALUSecondOperand.toString());
    }

    /**
     * The method sets the controller, registerFile, regMux and memory instance
     * variables
     *
     * @param controller
     * @param registerFile
     * @param regMUX
     * @param memory
     */
    public void setInstances(Organizer controller, RegistersFile registerFile, REG_MUX regMUX, DataMemory memory) {
        this.controller = controller;
        this.registerFile = registerFile;
        this.regMUX = regMUX;
        this.memory = memory;
    }

    /*
     *
     * The method runs the ALU Operation, it is called after the control sets
     * its control signals, and the register file passes the register contents.
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
        } else if (aluOp == 13) {
            System.out.println("First Operand data is " + this.ALUFirstOperand.getData());
            System.out.println("Second Operand data is " + this.ALUSecondOperand.getData());
            res = 0;
            if (this.ALUFirstOperand.getData() < this.ALUSecondOperand.getData()) {
                res = 1;
            }
            System.out.println("The result is " + res);
            this.REG_MUXFirstInput.setData(res);
            this.regMUX.setInput(0, this.REG_MUXFirstInput);
            this.regMUX.forward();
            this.writeRegister();
        } else if (aluOp == 10) {
            System.out.println("ALU Executing BEQ with " + this.ALUFirstOperand.getData() + " and " + this.ALUSecondOperand.getData());
            if (this.ALUFirstOperand.getData() == this.ALUSecondOperand.getData()) {
                System.out.println("BRANCHING BEQ");
                this.PCMUXControl.setData(1);
                this.pcMUX.setSelect(this.PCMUXControl);
            }
        } else if (aluOp == 11) {
            System.out.println("ALU Executing BNE with " + this.ALUFirstOperand.getData() + " and " + this.ALUSecondOperand.getData());
            if (this.ALUFirstOperand.getData() != this.ALUSecondOperand.getData()) {
                System.out.println("BNE branching");
                this.PCMUXControl.setData(1);
                this.pcMUX.setSelect(this.PCMUXControl);
            }
        } else if (aluOp == 16) {
            res = this.ALUFirstOperand.getData();
            System.out.println("the next instruction is " + this.ALUFirstOperand.getData());
            this.REG_MUXFirstInput.setData(res);
            this.regMUX.setInput(0, this.REG_MUXFirstInput);
            this.regMUX.forward();
            this.writeRegister();
        } else if (aluOp == 14) {
            System.out.println("First Operand data is " + this.ALUFirstOperand.getData());
            System.out.println("Second Operand data is " + this.ALUSecondOperand.getData());
            res = 0;
            if (Math.abs(this.ALUFirstOperand.getData()) < Math.abs(this.ALUSecondOperand.getData())) {
                res = 1;
            }
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

    public void writeRegister() {
        //  System.out.println("alu writing register");
        //controller.setRegisterWriteControl();
        // registerFile.write(res);
        //   regMUX.setInput(0, this.res);
        // regMUX.forward();
    }
}
