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
    
    private Logica log;
    
    private ArrayList<JLabel> bolas;
    
    private JPanel panelPreguntasYRespuestas,panelCentral,panelCronometro;
    
    private TextField fieldRespuestas;
    
    private JLabel textoPreguntas,textoSegundos,textoMinutos;

    private static double i=4.705;
     
    private static int contador=0;
     
    private int posX,posY;
     
    private double x,y,r=300;
     
     
    public Vista(Logica log) {
        this.log=log;
        crearVista();
    }

    /**
     * creo la vista principal
     * 
     */
    private void crearVista() {
        this.setLayout(null);
        bolas=new ArrayList<JLabel>();
        panelCentral=new JPanel(null);
        for (int i = 0; i < NBOLAS; i++) {
            bolas.add(log.getBola());      
            posicionarBola(bolas.get(i));
        }
        //crearMenuPreguntas();
        
        this.setSize(900, 900);
        this.setLocation(100, 100);
        this.setVisible(true);ç
          
    public void crearVista() {
        this.setLayout(null);
        bolas=new ArrayList<>();
        panelCentral=new JPanel(null);
        for (int i = 0; i < NBOLAS; i++) {
            bolas.add(log.getBola());
            posicionarBola(bolas.get(i));
        }
        panelCentral.setBounds(0, 0, ALTOVENTANA, ANCHOVENTANA);
        panelCentral.setBackground(Color.yellow);
        crearPanelPreguntas();
        crearPanelCronometro();
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
    private void crearPanelPreguntas() {
       fieldRespuestas=new TextField(10);
       textoPreguntas=new JLabel("");
       
       fieldRespuestas.setBounds(50, 100, 50, 50);
       textoPreguntas.setBounds(0 , 0, 100, 100);
             
       
       panelPreguntasYRespuestas=new JPanel(null);
       
       panelPreguntasYRespuestas.add(fieldRespuestas);
       panelPreguntasYRespuestas.add(textoPreguntas);
       panelPreguntasYRespuestas.setBackground(Color.yellow);
       panelPreguntasYRespuestas.setBounds(450, 450, 200, 200);
       
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
    /**
     * crea el panel de cronometro
     */
    private void crearPanelCronometro() {
        textoMinutos=new JLabel();
        textoSegundos=new JLabel("0");
        
        textoMinutos.setBounds(0, 50, 50, 50);
        textoSegundos.setBounds(51, 50, 50, 50);
        
        panelCronometro=new JPanel(null);
        panelCronometro.add(textoSegundos);
        panelCronometro.add(textoMinutos);
        
        panelCronometro.setBounds(0, 0, 100, 100);
        
        this.add(panelCronometro);
        
        
    }
    
    /**
     * Actualiza los segundos del Cronometro
     * @param segundos son los segundos que escriben en el JLabel
     */
    public void actualizarCronometroSegundos(String segundos){
        textoSegundos.setText(segundos);
        this.repaint();
    }
    
    /**
     * Actualiza los minutos y coloca el JLaebl de textoSegundos a 0
     * @param minutos son los minutos que se quiere actualizar
     */
    public void actualizarCronometroMinutos(String minutos){
        textoMinutos.setText(minutos);
        textoSegundos.setText("0");
        this.repaint();
    }
    
    
    
    
    
    
    
    
    
}
