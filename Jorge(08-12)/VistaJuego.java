
package proyectorosco;
/*
 06/12 Denis. Cambios Realizados: 
    He modificado el metodo posicionarBolas() para que pida crear una bola a logica, la cual llega ya con su imagen y tamaño asignado
    He recolocado el panel preguntas en la parte superior del frame.
Problema: no se ven las imagenes 
 */


import com.sun.glass.events.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TextField;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Jorge
 */
public class VistaJuego extends JFrame{

/************************************PARAMETROS********************************************/
    
    private final int NBOLAS=26,ALTOVENTANA=1000,ANCHOVENTANA=1000;
    private static double i=4.705;
    private final int X_CENTRO_ROSCO = 500;
    private final int Y_CENTRO_ROSCO = 400;
    //
    private final Rectangle TAM_PANEL_PREG_Y_RES = new Rectangle(570,300);
    private final Rectangle TAM_PANEL_CENTRAL = new Rectangle(1200, 1000);
    private final Rectangle TAM_TXTFIELD = new Rectangle(100,30);
    private final Rectangle TAM_PANEL_PREGUNTAS = new Rectangle(500, 90);
    private final Rectangle TAM_FRAME = new Rectangle(1500,1000);
    private final Rectangle TAM_PANEL_CRONO = new Rectangle(100, 60);
    private final Rectangle TAM_PANEL_CONTADOR = new Rectangle(150, 60);
    //
    private final Point POS_TEXTO_PREGUNTAS = new Point(270,310);
    private final Point POS_PANEL_PREGUNTAS = new Point(50,100);
    private final Point POS_TXTFIELD = new Point(100,100);
    private final Point POS_PANEL_CRONO = new Point(1100, 70);
    private final Point POS_PANEL_CENTRAL = new Point(20,20);
    private final Point POS_PANEL_CONTADOR = new Point(1000, 270);
    
/*************************************ATRIBUTOS********************************************/
   
    private ArrayList<JLabel> bolas;   
    private JPanel panelPreguntasYRespuestas,panelCentral,panelCronometro,panelContadorPreguntas;  
    private TextField fieldRespuestas;   
    private JLabel textoPreguntas,textoSegundos,textoMinutos,textoPreguntasAcertadas,textoPreguntasFalladas,textoAcertadas,textoFalladas;
    private MenuItem menuNuevaPartida,menuGuardarPartida,menuPausarPartida,menuCargarPartida,menuSalir;
    private Menu menuPartida,menuCotacto;
    private MenuBar barraMenu;
    private MenuShortcut atajoSalir,atajoCargar,atajoNuevaPartida,atajoPausar,atajoGuardar;
    private Border borde = new TitledBorder(new EtchedBorder(Color.black, Color.darkGray));

    private int posX,posY;  
    private double x,y,r=370;   

/************************************REFERENCIAS*******************************************/
    private Logica log;  
/************************************CONSTRUCTOR*******************************************/
     public VistaJuego(Logica log) {
        this.log=log;
        crearVista();
        log.cronometro();
    }
/***********************************METODOS CLASE******************************************/




    private void crearVista() {
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        bolas=new ArrayList<>();
        panelCentral=new JPanel(null);

        panelCentral.setBounds(TAM_PANEL_CENTRAL);
        panelCentral.setLocation(POS_PANEL_CENTRAL);
        panelCentral.setBackground(Color.yellow);
        for (int i = 0; i < NBOLAS; i++) {
            bolas.add(log.getBola());
            posicionarBola(bolas.get(i));
        }

        crearPanelPreguntas(1);
        crearPanelCronometro();
        crearMenu();
        crearPanelContador();
        this.add(panelCentral);
        this.setBounds(TAM_FRAME);
        //this.setLocation(100, 100);
        this.setVisible(true);
        //crearMenuPreguntas();
    }
    
    
    /**
     * crea el formilario de la parte de abajo
     */
    private void crearPanelPreguntas(int modeloPregunta) {
       JPanel panelPregunta;
              
       switch(modeloPregunta){
           case 0: //Modelo pregunta respuesta.
               panelPregunta = crearPanelModeloPreguntaRespuesta();
               panelPregunta.setBorder(borde);
               this.add(panelPregunta);
               cambiarTextoPregunta("Hola guapa");
               break;
           case 1: //Modelo test.
               panelPregunta = crearPanelModeloTest();
               panelPregunta.setBorder(borde);
               this.add(panelPregunta);
               cambiarTextoPregunta("Hola guapa");
               break;
               
           case 2: //Modelo imagenes
               break;
       }


    }
    
    /**
     Devuelve un panel con la estructura de Enunciado pregunta - RadioButtons con la respuesta
     * @return El panel con el formato Pregunta - Test
     */
    private JPanel crearPanelModeloTest(){
       textoPreguntas=new JLabel("TEXTO DE PREGUNTA AQUI");
       textoPreguntas.setBounds(30 , 15, 200, 30);
        
       panelPreguntasYRespuestas=new JPanel(null);
        

       JPanel panelRespuestasTest = new JPanel();
       panelRespuestasTest.setLayout(new GridLayout(1,1));
       JRadioButton res1 = new JRadioButton("Dinamarca");
       JRadioButton res2 = new JRadioButton("Ojo");
       JRadioButton res3 = new JRadioButton("Baguette");
       
       ButtonGroup btnGroup = new ButtonGroup();
       
       btnGroup.add(res1);
       btnGroup.add(res2);
       btnGroup.add(res3);      
       
       panelRespuestasTest.add(res1);
       panelRespuestasTest.add(res2);
       panelRespuestasTest.add(res3);
       panelRespuestasTest.setBounds(TAM_PANEL_PREGUNTAS);
       panelRespuestasTest.setLocation(POS_PANEL_PREGUNTAS);
       
       panelPreguntasYRespuestas.add(panelRespuestasTest);

       panelPreguntasYRespuestas.add(textoPreguntas);
       panelPreguntasYRespuestas.setBounds(TAM_PANEL_PREG_Y_RES);
       panelPreguntasYRespuestas.setLocation(POS_TEXTO_PREGUNTAS);
       
       return panelPreguntasYRespuestas;
    }
    
    /**
     * Devuelve un panel con la estructura de Enunciado pregunta - Txtfield con la respuesta
     * @return El panel con el formato Pregunta - Respuesta
     */
    private JPanel crearPanelModeloPreguntaRespuesta(){
       fieldRespuestas=new TextField(10);
       textoPreguntas=new JLabel("TEXTO DE PREGUNTA AQUI");

       fieldRespuestas.setBounds(TAM_TXTFIELD);
       fieldRespuestas.setLocation(POS_TXTFIELD);
       textoPreguntas.setBounds(0 , 0, 100, 100);


       panelPreguntasYRespuestas=new JPanel(null);

       panelPreguntasYRespuestas.add(fieldRespuestas);
       panelPreguntasYRespuestas.add(textoPreguntas);
       panelPreguntasYRespuestas.setBackground(Color.red);
       panelPreguntasYRespuestas.setBounds(TAM_PANEL_PREG_Y_RES);
       panelPreguntasYRespuestas.setLocation(POS_TEXTO_PREGUNTAS);
       
       return panelPreguntasYRespuestas;
    }

    /**
     * metodo para cambiar las preguntas y coloca el textFiels de respuestas vacio 
     * @param textoPregunta esta es la nueva pregunta que se quiere mostrr
     */
    private void cambiarTextoPregunta(String textoPregunta){
        textoPreguntas.setText(textoPregunta);
    }

    /**
     * crea el panel de cronometro
     */
    private void crearPanelCronometro() {
        textoMinutos=new JLabel();
        textoSegundos=new JLabel("0");

        textoMinutos.setBounds(new Rectangle(20,20));
        textoSegundos.setBounds(new Rectangle(20,20));
        textoMinutos.setLocation(new Point(25,22));
        textoSegundos.setLocation(new Point(70,22));
        
        JLabel separacionMinSeg = new JLabel(":");
        separacionMinSeg.setSize(10,10);
        separacionMinSeg.setLocation(new Point(52,26));

        panelCronometro=new JPanel(null);
        panelCronometro.add(textoSegundos);
        panelCronometro.add(textoMinutos);
        panelCronometro.add(separacionMinSeg);
        panelCronometro.setBorder(borde);
        
        
        panelCronometro.setLayout(new BorderLayout());
        panelCronometro.setBounds(TAM_PANEL_CRONO);
        panelCronometro.setLocation(POS_PANEL_CRONO);

        this.add(panelCronometro);                
    }
    
    /**
     * Crea el menu del frame.
     */
    private void crearMenu() {
       // menuNuevaPartida,menuGuardarPartida,menuPausarPartida,menuSalir
       atajoNuevaPartida=new MenuShortcut(KeyEvent.VK_N);
       menuNuevaPartida=new MenuItem("Empezar nueva partida",atajoNuevaPartida);

       atajoGuardar=new MenuShortcut(KeyEvent.VK_S);
       menuGuardarPartida=new MenuItem("Guardar partida",atajoGuardar);       

       atajoCargar=new MenuShortcut(KeyEvent.VK_C);
       menuCargarPartida=new MenuItem("Cargar partida",atajoCargar);

       atajoPausar=new MenuShortcut(KeyEvent.VK_P);
       menuPausarPartida=new MenuItem("Pausar partida",atajoPausar);

       atajoSalir=new MenuShortcut(KeyEvent.VK_O);
       menuSalir=new MenuItem("Pausar partida",atajoSalir);



       menuPartida=new Menu("Partida");

       menuPartida.add(menuNuevaPartida);
       menuPartida.add(menuGuardarPartida);
       menuPartida.add(menuCargarPartida);
       menuPartida.add(menuPausarPartida);
       menuPartida.add(menuSalir);

       menuCotacto=new Menu("Contacto");

       barraMenu=new MenuBar();
       barraMenu.add(menuPartida);
       barraMenu.add(menuCotacto);

       this.setMenuBar(barraMenu);
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
        x=X_CENTRO_ROSCO+Math.cos(i)*r;
        y=Y_CENTRO_ROSCO+Math.sin(i)*r;
        i+=0.24155;
    }

    
    private void crearPanelContador() {
        textoPreguntasAcertadas=new JLabel();
        textoPreguntasFalladas=new JLabel();
        textoAcertadas=new JLabel("Acertadas");
        textoFalladas=new JLabel("Falladas");
        panelContadorPreguntas=new JPanel(new GridLayout(2, 2));
        
        panelContadorPreguntas.add(textoAcertadas);
        panelContadorPreguntas.add(textoFalladas);
        panelContadorPreguntas.add(textoPreguntasAcertadas);
        panelContadorPreguntas.add(textoPreguntasFalladas);
        
        panelContadorPreguntas.setBounds(TAM_PANEL_CONTADOR);
        panelContadorPreguntas.setLocation(POS_PANEL_CONTADOR);
        panelContadorPreguntas.setBorder(borde);
        
        this.add(panelContadorPreguntas);
        
    }
    
    public void actualizarContadorPreguntas(String acertadas,String falladas){
        textoPreguntasAcertadas.setText(acertadas);
        textoPreguntasFalladas.setText(falladas);        
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

    

/***********************************GETTER SETTERS*****************************************/



}
