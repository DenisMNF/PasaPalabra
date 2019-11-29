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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    //
    private final int NUM_LETRAS = 26;
    //
    private final char[] abc_spanish  = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    
/*************************************ATRIBUTOS********************************************/
    private ArrayList<String> preguntas;
    private File idioma_file;
    private File puntuaciones_file = new File(path_puntuaciones);
    
/************************************REFERENCIAS*******************************************/
/************************************CONSTRUCTOR*******************************************/
    public BaseDatos() throws IOException{
        idioma_file = new File(ESPAÑOL);
        cargarPreguntas(idioma_file);
        mostrarDatos();
    }
/***********************************METODOS CLASE******************************************/
    
    private void cargarPreguntas(File f) throws FileNotFoundException, IOException{
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
    }
    
    public void registrarPuntuacionEnHistorico(String puntuacion) throws IOException{
        
        if(!puntuaciones_file.exists()){
            //
            puntuaciones_file.createNewFile();
        }
        PrintWriter escritor = new PrintWriter ( new BufferedWriter ( new FileWriter(puntuaciones_file,true)));
        
        escritor.println(puntuacion);
        escritor.close();
    }
    
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
    
    private void mostrarDatos(){
        for(String indice: preguntas){
            System.out.println(indice);
        }
    }
    

/*************************************INTERFAZ*********************************************/
    public void limpiarHistoricoPuntuaciones(){
        if(puntuaciones_file.exists()){
            puntuaciones_file.delete();
        }
    }
   
/***********************************GETTER SETTERS*****************************************/
     public int getNUM_LETRAS() {
        return NUM_LETRAS;
    }

    public char[] getAbc_spanish() {
        return abc_spanish;
    }
    
}
