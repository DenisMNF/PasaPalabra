/*
Funciones:
    -Carga de preguntas desde fichero.
    -Implementación de dos idiomas.
    -Partidas guardadas
 */
package proyectorosco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author demum
 */
public class BaseDatos {
    
/************************************PARAMETROS********************************************/
    private final String ESPAÑOL = "spanish.txt";
    private final String INGLES = "english.txt";
    private final String path_puntuaciones = "historico_puntuaciones.txt";
    private final String path_partidasGuardadas = "saves/";
    //
    private final int NUM_LETRAS = 26;
    //
    private final char[] abc_spanish  = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    
/*************************************ATRIBUTOS********************************************/
    private ArrayList<String> preguntas;
    //
    private File idioma_file;
    private File puntuaciones_file = new File(path_puntuaciones);
    private File partidasGuardadas_file = new File(path_partidasGuardadas);
    
    
/************************************REFERENCIAS*******************************************/
/************************************CONSTRUCTOR*******************************************/
    public BaseDatos() throws IOException{
        idioma_file = new File(ESPAÑOL);
        cargarPreguntas(idioma_file);

    }
    
/***********************************METODOS CLASE******************************************/
    /**
     * Metodo que carga las preguntas que aparecerán en la vista del juego desde un archivo. 
     * @param f archivo desde el que se cargarán las preguntas
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private ArrayList<String> cargarPreguntas(File f) throws FileNotFoundException, IOException{
        //
        preguntas = new ArrayList<String>();
        BufferedReader lector = new BufferedReader( new FileReader(idioma_file));
        //
        String linea = lector.readLine();
        while(linea != null){
            //
            preguntas.add(linea);
            linea = lector.readLine();
        }
        
        lector.close(); 
        return preguntas;
    }
       
/*************************************INTERFAZ*********************************************/
    /**
     * Registra una puntuación en el registro histórico de puntuaciones del juego.
     * @param puntuacion puntuación que va a ser guardada.
     * @throws IOException 
     */
    public void registrarPuntuacionEnHistorico(String puntuacion) throws IOException{
        
        if(!puntuaciones_file.exists()){
            //
            puntuaciones_file.createNewFile();
        }
        PrintWriter escritor = new PrintWriter ( new BufferedWriter ( new FileWriter(puntuaciones_file,true)));
        
        escritor.println(puntuacion);
        escritor.close();
    }
    
    /**
     * Carga las puntuaciones historicas guardadas en el juego para mostrarlas en vista.
     * @return  devuelve los datos de puntuaciones históricas.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public ArrayList<String> cargarPuntuacionesDeHistorico() throws FileNotFoundException, IOException{
        BufferedReader lector = new BufferedReader( new FileReader(puntuaciones_file));
        
        ArrayList<String> datosRecogidos = new ArrayList<String>();
        
        String linea = lector.readLine();
        while( linea != null){
            datosRecogidos.add(linea);
            linea = lector.readLine();
        }
        lector.close();
        return datosRecogidos;
    }
    
    /**
     * Borra todos los datos de las puntuaciones históricas del juego. 
     */
    public void limpiarHistoricoPuntuaciones(){
        if(puntuaciones_file.exists()){
            puntuaciones_file.delete();
        }
    }
    
    /**
     * Recibe una partida que quiere ser guardada en un fichero. Para ello este método serializa el objeto partida, convirtiendolo en una cadena de bits. 
     * Escribe esta cadena en un archivo saves/nombre_partida
     * @param partidaAGuardar Partida que quiere ser guardada
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void serializarPartida(Partida partidaAGuardar) throws FileNotFoundException, IOException{
        File nuevo = new File(path_partidasGuardadas+partidaAGuardar.getNombrePartida());
        
        FileOutputStream fos = new FileOutputStream(nuevo);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        oos.writeObject(partidaAGuardar);
        oos.close();
        fos.close();
    }
    
    /**
     * Carga un archivo que contiene la serialización de un objeto partida. Lo convierte al objeto de nuevo y lo devuelve.
     * @param nombrePartidaACargar nombre de la partida que queremos cargar.
     * @return objeto partida reconstruido.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public Partida desSerializarPartida(String nombrePartidaACargar) throws FileNotFoundException, IOException, ClassNotFoundException{
      
        FileInputStream fis = new FileInputStream(path_partidasGuardadas+nombrePartidaACargar);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        Partida partidaCargada = (Partida) ois.readObject();
        
        ois.close();
        fis.close();
        
        return partidaCargada;
        
        
    }
    

   
/***********************************GETTER SETTERS*****************************************/
     public int getNUM_LETRAS() {
        return NUM_LETRAS;
    }

    public char[] getAbc_spanish() {
        return abc_spanish;
    }
    
}
