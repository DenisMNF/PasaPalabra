/*
 * TODOS LOS METODOS DEL MENU SERAN LLAMADOS DESDE AQUI 
 */
package pasapalabrajorge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static pasapalabrajorge.VistaJuego.MENU_CARGAR_PARTIDA;
import static pasapalabrajorge.VistaJuego.MENU_CONTACTO;
import static pasapalabrajorge.VistaJuego.MENU_EMPEZAR_PARTIDA;
import static pasapalabrajorge.VistaJuego.MENU_GUARDAR_PARTIDA;
import static pasapalabrajorge.VistaJuego.MENU_PAUSAR_PARTIDA;
import static pasapalabrajorge.VistaJuego.MENU_RETOMAR_PARTIDA;
import static pasapalabrajorge.VistaJuego.MENU_SALIR;
import static pasapalabrajorge.VistaJuego.BOTON_COMPROBAR;
import static pasapalabrajorge.VistaJuego.BOTON_PASAPALABRA;


/**
 *
 * @author Jorge
 */
public class CntrlVistaJuego implements ActionListener{

    private VistaJuego vistaJuego;
    private VistaAsegurarseSalir asegurarseSalir;
    private Logica log;
    //
    private final int NUM_MODOS_PREGUNTAS = 2;
    private int modeloPregunta;

    public CntrlVistaJuego(VistaJuego vistaJuego, Logica log) {
        this.vistaJuego = vistaJuego;
        this.log = log;
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
                pausarPartida();
                asegurarseSalir=new VistaAsegurarseSalir(vistaJuego, "Salir", true);
                restaurarPartida();
            break;    
            case MENU_EMPEZAR_PARTIDA:

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
    }

    public void pausarPartida(){
        vistaJuego.pausarCronometro();
    }

}