/*
 * TODOS LOS METODOS DEL MENU SERAN LLAMADOS DESDE AQUI 
 */
package proyectorosco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class CntrlVistaJuego implements ActionListener{

    VistaJuego vistaJuego;
    
    VistaAsegurarseSalir asegurarseSalir;

    public CntrlVistaJuego(VistaJuego vistaJuego) {
        this.vistaJuego = vistaJuego;
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
