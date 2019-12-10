/*
    Jorge cambios realizados:
        - he hecho que la logica lleve el contador de aciertos y fallos y los muestra a la vista
 */
package pasapalabrajorge;

/**
 *
 * @author demum
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Jorge
 */
public class Logica {

/************************************PARAMETROS********************************************/
    private final int NUM_PREGUNTAS = 26;
/*************************************ATRIBUTOS********************************************/
    private int minutos,segundos;   
    private BaseDatos bd;   
    private Timer coronometro;   
    private VistaJuego vista;
    private VistaInicio vistaIni;
    private ArrayList<String> preguntas;
    private ArrayList<String> respuestas;
    private ArrayList<Bola> bolas = new ArrayList();
    private String idioma;
    //
    private int indicadorBolaEnJuego = 0;

    char dificultad;
    //contador
    int respuestasAcertadas,respuestasFalladas;

/************************************REFERENCIAS*******************************************/
/************************************CONSTRUCTOR*******************************************/
     public Logica() throws IOException {
        bd = new BaseDatos();
        vistaIni=new VistaInicio(this);

    }
/***********************************METODOS CLASE******************************************/
      /**
     * comprueba que los segundos sean 60 
     * en caso de sea 60 se suma un minuto, se ponen los segundos a 0 y actualiza su vista por el metodo actualizarCronometroMinutos
     * en caso de que no sea 60 se actualiza la vista por el metodo actualizarCronometroSegundos
     */
    private void actualizarCronometro() {
       if(segundos==60){
            minutos++;
            segundos=0;
            vista.actualizarCronometroMinutos(String.valueOf(minutos));
        }
        else{
            vista.actualizarCronometroSegundos(String.valueOf(segundos));
        }
    }

    /**
     * Arranca el cronometro
     */
    public void empiezaCronometro() {
        coronometro.start();
    }
    public void mostrarCarga(){
        VistaCarga vistaCarrga=new VistaCarga(this);
       vistaCarrga.empezarIterar();
    }

    public void empezarPartida() throws IOException{
            bd.cargarPreguntasYRespuestas(idioma, dificultad);
            preguntas = bd.getPreguntas();
            vista=new VistaJuego(this);
            respuestasAcertadas=0;
            respuestasFalladas=0;
            actualizarContadorPreguntas();

    }

    private void actualizarContadorPreguntas() {
        vista.actualizarContadorPreguntas(String.valueOf(respuestasAcertadas), String.valueOf(respuestasFalladas));
    }
    
    




/*************************************INTERFAZ*********************************************/
     /**
     * crea la clase cronometro y entra en un bucle infinito para que actualice el cronometro
     */
    public void cronometro(){
        minutos=0;
        segundos=0;
        coronometro=new Timer(1000,new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    segundos++;
                    actualizarCronometro();
                }
            }); 
        empiezaCronometro();        
    }
    
    /**
     * AUN POR IMPLEMENTAR: comprueba si las respuestas son correctas
     * @param respuesta 
     */
    
    public void comprobarRespuesta(String respuesta){
        respuestas = new ArrayList<String>();
        
    }


    /**
     * se pausa el juego en el caso de que sea necesario
     */
    public void pausarJuego(){
        coronometro.stop();
    }


    /**
     * Crea un objeto bola y ordena a base de datos que le asigne la imagen correspondiente.
     * @return 
     */

    public JLabel getBola(){
        //cambiar oara que reciba la imagen de la bola
        
        Bola bola = new Bola('N', bd);
       switch(bola.getEstado()){
           case 'N':
               bola.setIcon(bd.elegirImagenAzul(bola.getLetra()));
               bolas.add(bola);
               return bola;
           case 'A':
               bola.setIcon(bd.elegirImagenVerde(bola.getLetra()));
               bolas.add(bola);
               return bola;
           case 'F':
               bola.setIcon(bd.elegirImagenRojo(bola.getLetra()));
               bolas.add(bola);
               return bola;

           default:
               System.out.println("--Estado de bola erroneo--");
               return null;
       }

    }
    
    /**
     * Devuelve una pregunta segun el indice que marque el indicador del juego.
     * @return Devuelve la pregunta 
     */
    public String pasarPreguntas(){
        String pregunta = preguntas.get(indicadorBolaEnJuego);
        indicadorBolaEnJuego++;
        return pregunta;
    }
    
    /**
     * Pasa un String con las respuestas del modo test en el formato res1/res2/res3
     * @param indiceRespuesta El indice que marca que respuesta deseamos coger.
     * @return 
     */
    
    public String pasarRespuestaTriple(int indiceRespuesta){
        String respuesta = bd.getRespuestasTriple().get(indiceRespuesta);
        return respuesta;
    }



/***********************************GETTER SETTERS*****************************************/
    
     
     

    
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setDificultad(char dificultad) {
        this.dificultad = dificultad;
    }
    
    


    public int getIndicadorBolaEnJuego() {
        return indicadorBolaEnJuego;
    }










    /**
     * llama al metodo start de cronometro
     */



}