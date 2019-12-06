
package proyectorosco;
/*
 06/12 Denis. Cambios Realizados: 
    He modificado el metodo posicionarBolas() para que pida crear una bola a logica, la cual llega ya con su imagen y tamaño asignado
    He recolocado el panel preguntas en la parte superior del frame.

Problema: no se ven las imagenes 
 */


import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Rectangle;
import java.awt.TextField;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class VistaJuego extends JFrame{
    
/************************************PARAMETROS********************************************/
    public final int NBOLAS=26,ALTOVENTANA=950,ANCHOVENTANA=900;
    private static double i=4.705;
    private final Rectangle TAM_PANEL_PREG_Y_RES = new Rectangle(600,200);
/*************************************ATRIBUTOS********************************************/
    
    private Logica log;  
    private ArrayList<JLabel> bolas;   
    private JPanel panelPreguntasYRespuestas,panelCentral,panelCronometro;  
    private TextField fieldRespuestas;   
    private JLabel textoPreguntas,textoSegundos,textoMinutos;
    /*private MenuItem menuNuevaPartida,menuGuardarPartida,menuPausarPartida,menuContacto,menuSalir;
    private Menu 
    private MenuBar barraMenu;*/
    
    private int posX,posY;  
    private double x,y,r=300;   
    
/************************************REFERENCIAS*******************************************/
/************************************CONSTRUCTOR*******************************************/
     public VistaJuego(Logica log) {
        this.log=log;
        crearVista();
    }
/***********************************METODOS CLASE******************************************/
     
    
    
    
    private void crearVista() {
        this.setLayout(null);
        bolas=new ArrayList<>();
        panelCentral=new JPanel(null);
        
        panelCentral.setBounds(0, 0, ALTOVENTANA, ANCHOVENTANA);
        panelCentral.setBackground(Color.yellow);
        for (int i = 0; i < NBOLAS; i++) {
            bolas.add(log.getBola());
            posicionarBola(bolas.get(i));
        }
       
        crearPanelPreguntas();
        crearPanelCronometro();
        //crearMenu();
        this.add(panelCentral);
        this.setSize(900, 950);
        this.setLocation(100, 100);
        this.setVisible(true);
        //crearMenuPreguntas();
    }
    /**
     * se encarga de colocar el JLabel de la bola en el panel centrar 
     * @param bola esta es la imagen que bola que se coloca
     */
    private void posicionarBola(JLabel bola) {
        bola.setLocation(400,500);
        this.crearEspacios(bola);
        bola.setLocation((int)x, (int)y);
        panelCentral.add(bola);
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
       panelPreguntasYRespuestas.setBackground(Color.red);
       panelPreguntasYRespuestas.setBounds(TAM_PANEL_PREG_Y_RES);
       panelPreguntasYRespuestas.setLocation(200,0);
       
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
    
    

/*************************************INTERFAZ*********************************************/
    
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

/*    private void crearMenu() {
        
    }*/
    
/***********************************GETTER SETTERS*****************************************/
          
   
    
}
