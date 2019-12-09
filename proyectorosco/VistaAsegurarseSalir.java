/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectorosco;

import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class VistaAsegurarseSalir extends JDialog{
    JLabel mensaje;
    JButton botonSi,botonNo;
    JPanel panelAsegurarse;
    
    ControladorAsegurarseSalir controlSalir;

    public VistaAsegurarseSalir(Frame frame, String string, boolean bln) {
        super(frame, string, bln);
        this.setLayout(null);
        controlSalir=new ControladorAsegurarseSalir(this);
        
        mensaje=new JLabel("¿Estas segur de que quieres salir?");
        mensaje.setBounds(75, 50, 200, 200);
        botonSi=new JButton("Si");
        botonSi.setBounds(75, 200, 75, 25);
        botonNo=new JButton("No");
        botonNo.setBounds(200, 200, 75, 25);
        
        botonSi.addActionListener(controlSalir);
        botonNo.addActionListener(controlSalir);
        
        panelAsegurarse=new JPanel(null);
        panelAsegurarse.add(mensaje);
        panelAsegurarse.add(botonSi);
        panelAsegurarse.add(botonNo);
        panelAsegurarse.setBounds(0, 0, 400, 400);
        
        setSize(400, 400);
        this.add(panelAsegurarse);
        this.setVisible(true);
    }

}
