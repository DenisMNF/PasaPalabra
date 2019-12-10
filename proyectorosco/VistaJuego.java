
package proyectorosco;
/*
09-12 Jorge Cambios Realizados: 
    -añadido el panel contador de preguntas acertadas y falladas 
    -creado un controlador para llamar a las funciones del menu
    -metodos para parar la partida y seguir despues de parar la partida
 */


import com.sun.glass.events.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;
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
    
    private final int NUM_MODOS_PREGUNTAS = 3;
    
    static final String MENU_CONTACTO="Contacto";
    static final String MENU_SALIR="Salir";
    static final String MENU_EMPEZAR_PARTIDA="Empezar nueva partida";
    static final String MENU_GUARDAR_PARTIDA="Guardar partida";
    static final String MENU_CARGAR_PARTIDA="Cargar partida";
    static final String MENU_PAUSAR_PARTIDA="Pausar partida";
    static final String MENU_RETOMAR_PARTIDA="Retomar partida";
    static final String BOTON_COMPROBAR="Comprobar";
    static final String BOTON_PASAPALABRA ="Pasa Palabra";
    
    
    //
    private final Rectangle TAM_PANEL_PREG_Y_RES = new Rectangle(570,300);
    private final Rectangle TAM_PANEL_CENTRAL = new Rectangle(1200, 1000);
    private final Rectangle TAM_TXTFIELD = new Rectangle(200,30);
    private final Rectangle TAM_PANEL_PREGUNTAS = new Rectangle(500, 90);
    private final Rectangle TAM_FRAME = new Rectangle(1500,1000);
    private final Rectangle TAM_PANEL_CRONO = new Rectangle(100, 60);
    private final Rectangle TAM_PANEL_CONTADOR = new Rectangle(150, 60);
    //
    private final Point POS_TEXTO_PREGUNTAS = new Point(270,310);
    private final Point POS_PANEL_PREGUNTAS = new Point(50,100);
    private final Point POS_TXTFIELD = new Point(20,170);
    private final Point POS_PANEL_CRONO = new Point(1100, 70);
    private final Point POS_PANEL_CENTRAL = new Point(20,20);
    private final Point POS_PANEL_CONTADOR = new Point(1000, 270);
    
/*************************************ATRIBUTOS********************************************/
   
    private ArrayList<JLabel> bolas;   
    private JPanel panelPreguntasYRespuestas,panelCentral,panelCronometro,panelContadorPreguntas,panelPato;  
    private TextField fieldRespuestas;   
    private JLabel textoPreguntas,textoSegundos,textoMinutos,textoPreguntasAcertadas,textoPreguntasFalladas,textoAcertadas,textoFalladas,labelImagenPato;
    private MenuItem menuNuevaPartida,menuGuardarPartida,menuPausarPartida,menuCargarPartida,menuSalir,menuCotacto,menuRetomarPartida;
    private Menu menuPartida,menuOtros;
    private MenuBar barraMenu;
    private MenuShortcut atajoSalir,atajoCargar,atajoNuevaPartida,atajoPausar,atajoGuardar;
    
    private Border borde = new TitledBorder(new EtchedBorder(Color.black, Color.darkGray));
    
    private Button btn_pasaPalabra, btn_comprobar;
    
    public ImageIcon imagenPato=new ImageIcon(this.getClass().getResource("/Otras Imagenes/patoNegroDerecha.png"));
    public ImageIcon imagenPatoDisparado=new ImageIcon(this.getClass().getResource("/Otras Imagenes/pajaroDisparado.png"));
    
    private static int posicionPato;
    private int posX,posY;  
    private double x,y,r=370;   

/************************************REFERENCIAS*******************************************/
    private CntrlVistaJuego control;  
/************************************CONSTRUCTOR*******************************************/
     public VistaJuego(CntrlVistaJuego control) {
        this.control=control;
        crearVista();
        this.control.cronometro();
        crearPanelCronometro();
        crearMenu();
        crearPanelContador();
        this.control.easterEggPato();
        opcionCerrar();
        posicionPato=0;
        aniadirControl();
        this.setIconImage(control.seleccionarIcono());
        this.setVisible(true);
    }
/***********************************METODOS CLASE******************************************/



    /**
      * Crea y conficura el frame y el panel central . Pinta por primera vez el rosco y crea el primer panel de preguntas.
      */
    private void crearVista() {
        this.setLayout(null);
        this.setBounds(TAM_FRAME);
        //this.setLocation(100, 100);
        bolas=control.getBolas();
        panelCentral=new JPanel(null);
        
        panelCentral.setBounds(TAM_PANEL_CENTRAL);
        panelCentral.setLocation(POS_PANEL_CENTRAL);
        panelCentral.setBackground(Color.WHITE);
        
        pintarRosco();
 
        crearPanelPreguntas(0);
        
        
        this.add(panelCentral);
        
    
    }
    
    
    /**
     * crea el formulario de la parte de abajo
     */
    private void crearPanelPreguntas(int modeloPregunta) {
       JPanel panelPregunta;
       
       int contadorPregunta = 0;
       
       String textoPregunta = control.pasarPreguntas();

       switch(modeloPregunta){
           case 0: //Modelo pregunta respuesta.
               panelPregunta = crearPanelModeloPreguntaRespuesta();
               panelPregunta.setBorder(borde);
               panelPregunta.setVisible(true);

               panelCentral.add(panelPregunta);
               panelCentral.repaint();
               cambiarTextoPregunta(textoPregunta);
               break;
           case 1: //Modelo test.
               panelPregunta = crearPanelModeloTest();
               panelPregunta.setBorder(borde);
               panelCentral.add(panelPregunta);
               panelCentral.repaint();
               cambiarTextoPregunta(textoPregunta);
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
       textoPreguntas.setBounds(new Rectangle(300,50));
       textoPreguntas.setLocation(new Point(30,30));

       panelPreguntasYRespuestas=new JPanel(null);
       
       JPanel panelRespuestasTest = new JPanel();
       panelRespuestasTest.setLayout(new GridLayout(1,1));
       
       String respuestasTest = control.pasarRespuestaTriple(control.getIndicadorBolaEnJuego());
       String[] divisionAux = respuestasTest.split("/");
       
       JRadioButton res1 = new JRadioButton(divisionAux[0]);
       JRadioButton res2 = new JRadioButton(divisionAux[1]);
       JRadioButton res3 = new JRadioButton(divisionAux[2]);

       ButtonGroup btnGroup = new ButtonGroup();

       btnGroup.add(res1);
       btnGroup.add(res2);
       btnGroup.add(res3);      

       panelRespuestasTest.add(res1);
       panelRespuestasTest.add(res2);
       panelRespuestasTest.add(res3);
       panelRespuestasTest.setBounds(TAM_PANEL_PREGUNTAS);
       panelRespuestasTest.setLocation(POS_PANEL_PREGUNTAS);
       
       panelRespuestasTest.add(btn_comprobar);
       panelRespuestasTest.add(btn_pasaPalabra);
       
       panelPreguntasYRespuestas.add(panelRespuestasTest);

       panelPreguntasYRespuestas.add(textoPreguntas);
       panelPreguntasYRespuestas.setBounds(TAM_PANEL_PREG_Y_RES);
       panelPreguntasYRespuestas.setLocation(POS_TEXTO_PREGUNTAS);

       aniadirCntrlBotones();

       return panelPreguntasYRespuestas;
    }
    
    /**
     * Devuelve un panel con la estructura de Enunciado pregunta - Txtfield con la respuesta
     * @return El panel con el formato Pregunta - Respuesta
     */
    private JPanel crearPanelModeloPreguntaRespuesta(){
       fieldRespuestas=new TextField(10);
       textoPreguntas=new JLabel();

       fieldRespuestas.setBounds(TAM_TXTFIELD);
       fieldRespuestas.setLocation(POS_TXTFIELD);
       textoPreguntas.setBounds(new Rectangle(500,50));
       textoPreguntas.setLocation(new Point(20,8));


       panelPreguntasYRespuestas=new JPanel(null);
       
       
       btn_comprobar = new Button("Comprobar");
       btn_pasaPalabra = new Button("Pasa Palabra");
       
       btn_comprobar.setBounds(new Rectangle(70,30));
       btn_pasaPalabra.setBounds(new Rectangle(100,30));
       //
       btn_comprobar.setLocation(new Point(50,260));
       btn_pasaPalabra.setLocation(new Point(450,260));

       panelPreguntasYRespuestas.add(btn_comprobar);
       panelPreguntasYRespuestas.add(btn_pasaPalabra);
       panelPreguntasYRespuestas.add(fieldRespuestas);
       panelPreguntasYRespuestas.add(textoPreguntas);
       panelPreguntasYRespuestas.setBounds(TAM_PANEL_PREG_Y_RES);
       panelPreguntasYRespuestas.setLocation(POS_TEXTO_PREGUNTAS);
       
       aniadirCntrlBotones();

       return panelPreguntasYRespuestas;
    }

    /**
     * metodo para cambiar las preguntas y coloca el textFiels de respuestas vacio 
     * @param textoPregunta esta es la nueva pregunta que se quiere mostrr
     */
    private void cambiarTextoPregunta(String textoPregunta){
        textoPreguntas.setText(textoPregunta);
        panelPreguntasYRespuestas.repaint();
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

        panelCentral.add(panelCronometro);                
    }
    
    /**
     * Crea el menu del frame.
     */
    private void crearMenu() {
       // menuNuevaPartida,menuGuardarPartida,menuPausarPartida,menuSalir
       atajoNuevaPartida=new MenuShortcut(KeyEvent.VK_N);
       menuNuevaPartida=new MenuItem(MENU_EMPEZAR_PARTIDA,atajoNuevaPartida);

       atajoGuardar=new MenuShortcut(KeyEvent.VK_F5);
       menuGuardarPartida=new MenuItem(MENU_GUARDAR_PARTIDA,atajoGuardar);       

       atajoCargar=new MenuShortcut(KeyEvent.VK_C);
       menuCargarPartida=new MenuItem(MENU_CARGAR_PARTIDA,atajoCargar);

       atajoPausar=new MenuShortcut(KeyEvent.VK_P);
       menuPausarPartida=new MenuItem(MENU_PAUSAR_PARTIDA,atajoPausar);
       
       menuRetomarPartida=new MenuItem(MENU_RETOMAR_PARTIDA);
       menuRetomarPartida.setEnabled(false);
       
       atajoSalir=new MenuShortcut(KeyEvent.VK_O);
       menuSalir=new MenuItem(MENU_SALIR,atajoSalir);



       menuPartida=new Menu("Partida");

       menuPartida.add(menuNuevaPartida);
       menuPartida.add(menuGuardarPartida);
       menuPartida.add(menuCargarPartida);
       menuPartida.add(menuPausarPartida);
       menuPartida.add(menuRetomarPartida);
       menuPartida.addSeparator();
       menuPartida.add(menuSalir);

       menuCotacto=new MenuItem(MENU_CONTACTO);

       menuOtros=new Menu("Otros");
       
       menuOtros.add(menuCotacto);
       
       barraMenu=new MenuBar();
       barraMenu.add(menuPartida);
       barraMenu.add(menuOtros);

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
        
        panelCentral.add(panelContadorPreguntas);
        
    }
    
    
    /**
     * Pinta las bolas en el panel central.
     */
    private void pintarRosco(){
        for (int i = 0; i < NBOLAS; i++) {
            posicionarBola(bolas.get(i));
        }
    }
    
    /**
     * Añade los controladores a los botones de los paneles con preguntas. Es necesario que esten a parte de aniadirControl porque 
     * hay que llamar a esta funcion mas de una vez. En concreto cada vez que cambiamos de pregunta y se crea un panel de preguntas nuevo.
     */    
    private void aniadirCntrlBotones(){
        btn_comprobar.addActionListener(control);
        btn_pasaPalabra.addActionListener(control);
        
    }

/*************************************INTERFAZ*********************************************/

    
    public void actualizarContadorPreguntas(String acertadas,String falladas){
        textoPreguntasAcertadas.setText(acertadas);
        textoPreguntasFalladas.setText(falladas);        
    }

    public void pedirALogicaComprobacion(){
        
    }
    
    /**
     * Controla las iteraciones del juego al pasar de letra a letra. Elige aleatoriamente un formato de 
     */
    public void iteracionJuego(int modeloPregunta){
        crearPanelPreguntas(modeloPregunta); 
    }
    
    public void eliminarPanelPreguntaAntiguo(){
        if(panelPreguntasYRespuestas!=null){
            panelCentral.remove(panelPreguntasYRespuestas);
        }
        pintarRosco();

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
    
    
    void pausarCronometro() {
        menuRetomarPartida.setEnabled(true);
        menuPausarPartida.setEnabled(false);
    }

    void restaurarPartida() {
        menuRetomarPartida.setEnabled(false);
        menuPausarPartida.setEnabled(true);
    }


    private void aniadirControl() {
        menuCotacto.addActionListener(control);
        menuSalir.addActionListener(control);
        menuGuardarPartida.addActionListener(control);
        menuCargarPartida.addActionListener(control);
        menuNuevaPartida.addActionListener(control);
       menuPausarPartida.addActionListener(control);
      menuRetomarPartida.addActionListener(control);
    }

    void moveindoPato() {
        posicionPato+=10;
        labelImagenPato.setLocation(posicionPato, labelImagenPato.getY());
        this.repaint();
    }

    void crearPato(int posicion) {
        labelImagenPato=new JLabel(imagenPato);
        labelImagenPato.setBounds(0, posicion, 61, 47);
        labelImagenPato.addMouseListener(control);
        panelCentral.add(labelImagenPato);
        
        this.repaint();
    }

    void callendoPato() {
        labelImagenPato.setIcon(imagenPatoDisparado);
        labelImagenPato.setLocation(labelImagenPato.getX(), labelImagenPato.getY()+15);
        this.repaint();
    }

    public void opcionCerrar(){
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                control.antesDeSalir();
            }
        });
    }

    
/***********************************GETTER SETTERS*****************************************/



}
