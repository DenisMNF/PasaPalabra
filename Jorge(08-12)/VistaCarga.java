/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectorosco;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author Jorge
 */
public class VistaCarga extends JFrame{

    Logica logica;
   JProgressBar barraCarga;
   JPanel panelCarga;
   Timer iterador;
   int numeroActual=0;
   boolean terminadoCarga;
    
    public VistaCarga(Logica logica) {
        this.logica=logica;
        crearVista();
        this.pack();
        this.setVisible(true);
    }

    public void crearVista() {
        barraCarga=new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
        barraCarga.setValue(0);
        barraCarga.setStringPainted(true);
        
        panelCarga=new JPanel();
        panelCarga.add(barraCarga);
        panelCarga.setBackground(Color.yellow);
        
        iterador=new Timer(100,new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (numeroActual <= 100) {
                        barraCarga.setValue(numeroActual); 
                     }
                    else{
                        pararIterar();
                        try {
                            logica.empezarPartida();
                        } catch (IOException ex) {
                            Logger.getLogger(VistaCarga.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    numeroActual+=1;
               } 
            }); 
        
        this.add(panelCarga);
    }
    
    public void empezarIterar(){
        iterador.start();
    }
    
    public void pararIterar(){
        iterador.stop();
    }

    
        
    
}
