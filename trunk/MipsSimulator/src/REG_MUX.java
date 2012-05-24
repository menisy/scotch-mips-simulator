/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nada
 */
public class REG_MUX extends MUX {
    RegistersFile registerFile;
    public REG_MUX(RegistersFile registerFile)
    {
        this.registerFile = registerFile;
    }
    public void setOut(int out)
    {
        //todo
    }
    public void forward()
    {
                System.out.println("writing in register file");

        registerFile.setWriteRegisterData(this.getOutput());
                
    }
    
}
