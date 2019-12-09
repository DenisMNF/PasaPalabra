/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectorosco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jorge
 */
class ControladorAsegurarseSalir implements ActionListener{
    VistaAsegurarseSalir vistaSalir;

    public ControladorAsegurarseSalir(VistaAsegurarseSalir vistaSalir) {
        this.vistaSalir = vistaSalir;
    }
    
            
            @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("Si")){
            System.exit(0);
        }
        else{
            vistaSalir.dispose();
        }
    }
    
}
