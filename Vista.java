/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistrosco;

import java.awt.Color;
import java.awt.TextField;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class Vista extends JFrame{
    public final int NBOLAS=26,ALTOVENTANA=900,ANCHOVENTANA=900;
    
    Logica log;
    
    ArrayList<JLabel> bolas;
    
    JPanel panelPreguntasYRespuestas,panelCentral;
    
    TextField fieldRespuestas;
    
     JLabel textoPreguntas;

     static double i=4.705;
     
     static int contador=0;
     
     int posX,posY;
     
     double x,y,r=300;
     
     
     
     
    public Vista() {
        log=new Logica();
        crearVista();
    }

    /**
     * creo la vista principal
     * 
     */
    private void crearVista() {
        this.setLayout(null);
        bolas=new ArrayList<>();
        panelCentral=new JPanel(null);
        for (int i = 0; i < NBOLAS; i++) {
            bolas.add(log.getBola());
            posicionarBola(bolas.get(i));
        }
        panelCentral.setBounds(0, 0, ALTOVENTANA, ANCHOVENTANA);
        panelCentral.setBackground(Color.yellow);
        this.add(panelCentral);
        this.setSize(900, 900);
        this.setLocation(100, 100);
        this.setVisible(true);
        //crearMenuPreguntas();
    }

    /**
     * se encarga de colocar el JLabel de la bola en el panel centrar 
     * @param bola esta es la imagen que bola que se coloca
     */
    private void posicionarBola(JLabel bola) {
        bola.setBounds(ALTOVENTANA/2, ANCHOVENTANA/2, 71, 60);
        this.crearEspacios(bola);
        bola.setLocation((int)x, (int)y);
        panelCentral.add(bola);
        this.add(panelCentral);
    }

    /**
     * crea los espacio para para ponerlos en forma de circulo
     * @param bola este JLabel es la imagen de la bola que utilizo para cojer el posicionamiento de x e y;
     */
    private void crearEspacios(JLabel bola){
        x=bola.getX()+Math.cos(i)*r;
        y=bola.getY()+Math.sin(i)*r;
         i+=0.24155;
    }
    
    /**
     * crea el formilario de la parte de abajo
     */
    private void crearMenuPreguntas() {
       fieldRespuestas=new TextField(10);
       textoPreguntas=new JLabel("");
       
       fieldRespuestas.setBounds(100, 100, 50, 50);
       textoPreguntas.setBounds(150 , 150, 100, 100);
             
       
       panelPreguntasYRespuestas=new JPanel(null);
       
       panelPreguntasYRespuestas.add(fieldRespuestas);
       panelPreguntasYRespuestas.add(textoPreguntas);
       panelPreguntasYRespuestas.setBackground(Color.yellow);
       panelPreguntasYRespuestas.setBounds(0, 0, 200, 200);
       
       this.add(panelPreguntasYRespuestas);
       
    }
    
    /**
     * metodo para cambiar las preguntas y coloca el textFiels de respuestas vacio 
     * @param textoPregunta esta es la nueva pregunta que se quiere mostrr
     */
    private void cambiarPregunta(String textoPregunta){
        textoPreguntas.setText(textoPregunta);
        fieldRespuestas.setText("");
    }
    
    
    
    
    
    
    
    
    
}
