/*
 * 10/12 Jorge Cambio 
 * -esta clase sirve de union entre la logica y la vista, creando esta vista la clase
 * -
 */
package proyectorosco;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import static proyectorosco.VistaJuego.BOTON_COMPROBAR;
import static proyectorosco.VistaJuego.BOTON_PASAPALABRA;
import static proyectorosco.VistaJuego.MENU_CARGAR_PARTIDA;
import static proyectorosco.VistaJuego.MENU_CONTACTO;
import static proyectorosco.VistaJuego.MENU_EMPEZAR_PARTIDA;
import static proyectorosco.VistaJuego.MENU_GUARDAR_PARTIDA;
import static proyectorosco.VistaJuego.MENU_PAUSAR_PARTIDA;
import static proyectorosco.VistaJuego.MENU_RETOMAR_PARTIDA;
import static proyectorosco.VistaJuego.MENU_SALIR;

/**
 *
 * @author Jorge
 */
public class CntrlVistaJuego implements ActionListener,MouseListener{
    
    private final int NBOLAS=26;
    
    private ArrayList<JLabel> bolas;   

    VistaJuego vistaJuego;
    
    Logica log;
    
    VistaAsegurarseSalir asegurarseSalir;
    
    private final int NUM_MODOS_PREGUNTAS = 2;
    private int modeloPregunta;
    
    public CntrlVistaJuego(Logica log) {
        this.log=log;
        llenarBolas();
        this.vistaJuego = new VistaJuego(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        switch(ae.getActionCommand()){
            case MENU_CONTACTO:
                pausarPartida();
                new VistaContactoModal(vistaJuego, "Contacto", true);
                restaurarPartida();
            break;
            case MENU_SALIR:
                antesDeSalir();
            break;    
            case MENU_EMPEZAR_PARTIDA:
                empezarNuevaPartida();
            break;
            case MENU_GUARDAR_PARTIDA:
                
                
            break;    
            case MENU_CARGAR_PARTIDA:
                
            break;    
            case MENU_PAUSAR_PARTIDA:
                pausarPartida();
            break;
            case MENU_RETOMAR_PARTIDA:
                restaurarPartida();
            break;
            case BOTON_COMPROBAR:
                
                vistaJuego.eliminarPanelPreguntaAntiguo();
                modeloPregunta = (int)(Math.random() * NUM_MODOS_PREGUNTAS);  
                vistaJuego.iteracionJuego(modeloPregunta);
                
            break;
               
            case BOTON_PASAPALABRA:
                vistaJuego.eliminarPanelPreguntaAntiguo();
                modeloPregunta = (int)(Math.random() * NUM_MODOS_PREGUNTAS);
                vistaJuego.iteracionJuego(modeloPregunta);
                break;
            default:
                System.err.println("OPCION NO IMPLEMENTADA");
        }
    }
    
    public void restaurarPartida(){
        vistaJuego.restaurarPartida();
        log.empiezaCronometro();
    }
    
    public void pausarPartida(){
        vistaJuego.pausarCronometro();
        log.pararPato();
        log.pausarJuego();
    }

    public void empezarNuevaPartida(){
        pausarPartida();
        vistaJuego.dispose();
        log.crearVistaInicio();
    }
    
    public void antesDeSalir(){
        pausarPartida();
        asegurarseSalir = new VistaAsegurarseSalir(vistaJuego, "Salir", true);
        restaurarPartida();
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        log.patoCayendo();
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    void cronometro() {
        log.cronometro();
    }

    void easterEggPato() {
        log.easterEggPato();
    }

    private void llenarBolas() {
        bolas=new ArrayList<>();
        for (int i = 0; i < NBOLAS; i++) {
            bolas.add(log.getBola());
        }
    }

    public ArrayList<JLabel> getBolas() {
        return bolas;
    }

    void actualizarContadorPreguntas(String valueOf, String valueOf0) {
        vistaJuego.actualizarContadorPreguntas(valueOf, valueOf0);
    }

    void actualizarCronometroSegundos(String valueOf) {
        vistaJuego.actualizarCronometroSegundos(valueOf);
    }

    void actualizarCronometroMinutos(String valueOf) {
        vistaJuego.actualizarCronometroMinutos(valueOf);
    }

    void moveindoPato() {
        vistaJuego.moveindoPato();
    }

    void callendoPato() {
        vistaJuego.callendoPato();
    }

    void crearPato(int rng) {
        vistaJuego.crearPato(rng);
    }

    public Image seleccionarIcono() {
        return log.seleccionarIcono();
    }

    public String pasarPreguntas() {
        return log.pasarPreguntas();
    }
    
    public String pasarRespuestaTriple(int indiceRespuesta){
        return log.pasarRespuestaTriple(indiceRespuesta);
    }
    
    public int getIndicadorBolaEnJuego(){
        return log.getIndicadorBolaEnJuego();
    }
    
}
