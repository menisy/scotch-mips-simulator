
public class ALU {
	int op1,op2;
	int aluOp; 
	int res;
	int isZero;
        Organizer controller;
        RegistersFile registerFile;
        public void setInstances(Organizer controller, RegistersFile registerFile)
        {
            this.controller = controller;
            this.registerFile = registerFile;
        }
	public void setOp1(int n){
	    System.out.println("Setting operand 1 to " + n);	
            op1 = n;
	}
	public void setOp2(int n){
		op2 = n;
	}
	/* 1 - add
	 * 2 - sub
	 * 3 - or
	 * 4 - and
	 * 5 - shift right
	 * 6 - shift left
	 * 7 - nor
	 * 
	 * 
	 * */
	public void setControl(int n){
		aluOp = n;
                System.out.println(" alu control set to " + n);
	}
	
	public void doOperation(){
            System.out.println("Doing the operation ");
		if(aluOp == 1){
			res = op1 + op2;
		}else if(aluOp == 2){
			res = op1 - op2;
		}else if(aluOp == 3){
			res = op1 | op2;
		}else if(aluOp == 4){
			res = op1 & op2;
		}else if(aluOp == 5){
			res = op1 >> op2;
		}else if(aluOp == 6){
			res = op1 << op2;
		}else if(aluOp == 7){
			res = ~(op1 | op2);
		}
		if(res == 0) 
			isZero = 1;
		else
			isZero = 0;
                System.out.println("The result of the operation is " + res);
                this.writeRegister();
	}
	public int getZero(){
		return isZero;
	}
	public int getResult(){
		return res;
	}
        public void writeRegister()
        {
            System.out.println("alu writing register");
            controller.setRegisterWriteControl();
            registerFile.write(res);
        }
}
