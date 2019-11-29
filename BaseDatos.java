/*
Funciones:
    -Carga de preguntas desde fichero.
    -Implementación de dos idiomas.
    -Partidas guardadas
 */
package proyectorosco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author demum
 */
public class BaseDatos {
    
/************************************PARAMETROS********************************************/
    private final String ESPAÑOL = "spanish.txt";
    private final String INGLES = "english.txt";
    //
    private final int NUM_LETRAS = 26;
    //
    private final char[] abc_spanish  = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    
/*************************************ATRIBUTOS********************************************/
    private ArrayList<String> preguntas;
    private File f;
/************************************REFERENCIAS*******************************************/
/************************************CONSTRUCTOR*******************************************/
    public BaseDatos() throws IOException{
        f = new File(INGLES);
        cargarPreguntas(f);
        mostrarDatos();
    }
/***********************************METODOS CLASE******************************************/
    private void cargarPreguntas(File f) throws FileNotFoundException, IOException{
        //
        preguntas = new ArrayList<String>();
        BufferedReader lector = new BufferedReader( new FileReader(f));
        //
        String linea = lector.readLine();
        while(linea != null){
            //
            preguntas.add(linea);
            linea = lector.readLine();
        }
        
        lector.close();
        
        
    }
    
    private void mostrarDatos(){
        for(String indice: preguntas){
            System.out.println(indice);
        }
    }
    
 

/*************************************INTERFAZ*********************************************/
    public int getNUM_LETRAS() {
        return NUM_LETRAS;
    }

    public char[] getAbc_spanish() {
        return abc_spanish;
    }
/***********************************GETTER SETTERS*****************************************/
    
    
}
