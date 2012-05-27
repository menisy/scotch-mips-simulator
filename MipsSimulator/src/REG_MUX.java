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

    public REG_MUX(RegistersFile registerFile) {
        this.registerFile = registerFile;
    }

    /**
     * The method sets the output of the PCMUX to the selected input
     */
    public void forward() {
        System.out.println("writing in register file");
        registerFile.setWriteRegisterData(this.getOutput());

    }
}
