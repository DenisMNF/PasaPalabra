/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasapalabrajorge;

import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author Jorge
 */
public class VistaContactoModal extends JDialog{
    JLabel mensaje=new JLabel("Denis y Jorge");
    
    private Rectangle TAM_VENTANA_MODAL = new Rectangle(100,50);

    public VistaContactoModal(Frame frame, String string, boolean bln) {
        super(frame, string, bln);
        this.add(mensaje);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setBounds(TAM_VENTANA_MODAL);
    }


}