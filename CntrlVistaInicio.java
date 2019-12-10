/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasapalabrajorge;
/*
09-12 Jorge Cambios Realizados: 
    - Este es un controlador PROVISIONAL si tienes uno mejor se cambia 
    - simplemente recoje los datos y pasa a la siguiente vista siempre que se introduzca un nomrbe
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Jorge
 */
public class CntrlVistaInicio implements ActionListener{

    String idioma;
    char dificultad;
    VistaInicio vistaInicio;

    /**
     * contructor del controlador de la vista inicio
     * @param vistaInicio es la vista inicio en el que se compruevan los datos
     */
    public CntrlVistaInicio(VistaInicio vistaInicio) {
        this.vistaInicio = vistaInicio;
    }

    /**
     * se comprueba el estado de la dificultad y el del idioma despues comprueba que se tenga un nombre 
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        comprobarDificultad();
        comprobarIdioma();
        if (comprobarNombre()) {
            try {
                vistaInicio.empezarPartida(getIdioma(),getDificultad());
            } catch (IOException ex) {
                Logger.getLogger(CntrlVistaInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * se selecciona el idioma que se va a jugar
     */
    public void comprobarIdioma(){
        if(vistaInicio.getIdiomaEspaniol().getState()){
            setIdioma("español");
        }
        else{
            setIdioma("english");
        }

    }

    /**
     * se selecciona el dificultad a la que se va a jugar
     */
    public void comprobarDificultad(){
        if(vistaInicio.getDificultadFacil().getState()){
            setDificultad('f');
        }
        else{
            setDificultad('d');
        }
    }

    /**
     * se comprueva que el nomrbe no este vacio
     * @return true en caso de que el nombre este relleno 
     *              false si el nombre esta vacio
     */
    private boolean comprobarNombre() {
        if(vistaInicio.getIntroducirNombre().getText().equals("")){
            return false;
        }
        return true;
    }


    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setDificultad(char dificultad) {
        this.dificultad = dificultad;
    }

    public String getIdioma() {
        return idioma;
    }

    public char getDificultad() {
        return dificultad;
    }

}