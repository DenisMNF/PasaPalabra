/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectorosco;

import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author Jorge
 */
public class VistaContactoModal extends JDialog{
    JLabel mensaje=new JLabel("Denis y Jorge");

    public VistaContactoModal(Frame frame, String string, boolean bln) {
        super(frame, string, bln);
        this.add(mensaje);
        this.setVisible(true);
    }

    
}
