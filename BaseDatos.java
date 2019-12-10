/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasapalabrajorge;

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
import javax.swing.ImageIcon;


/**
 *
 * @author demum
 */
public class BaseDatos {
    
/************************************PARAMETROS********************************************/
    private final String PATH_ESPAÑOL = "questions/Es/";
    private final String PATH_INGLES = "questions/En/";
    private final String FILE_ESPAÑOL_FACIL = "spanish.txt";
    private final String FILE_ESPAÑOL_DIFICIL = "spanish_dificil.txt";
    private final String FILE_ENGLISH_EASY = "english.txt";
    private final String FILE_ENGLISH_HARD = "english_hard.txt";

    private final String path_puntuaciones = "historico_puntuaciones.txt";
    private final String path_partidasGuardadas = "saves/";
    //
    private final int NUM_LETRAS = 26;
    
    //

/*************************************ATRIBUTOS********************************************/
    private ArrayList<String> preguntas;
    private ArrayList<String> respuestasSimple;
    private ArrayList<String> respuestasTriple;
    //
    private File idioma_file;
    private File puntuaciones_file = new File(path_puntuaciones);
    private File partidasGuardadas_file = new File(path_partidasGuardadas);

    private static int numControlBola = 0;


/************************************REFERENCIAS*******************************************/
/************************************CONSTRUCTOR*******************************************/
    public BaseDatos() throws IOException{


    }

/***********************************METODOS CLASE******************************************/
    

/*************************************INTERFAZ*********************************************/
    
    /**
     * Metodo que carga las preguntas que aparecerán en la vista del juego desde un archivo. 
     * @param f archivo desde el que se cargarán las preguntas
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void cargarPreguntasYRespuestas(String idioma,char dificultad) throws FileNotFoundException, IOException{
        //

        File file = null;

        switch(idioma){
            case "español":
                if(dificultad == 'f'){
                    file = new File(PATH_ESPAÑOL+FILE_ESPAÑOL_FACIL);
                }else{
                    file = new File(PATH_ESPAÑOL+FILE_ESPAÑOL_DIFICIL);
                }
                break;
            case "english":
                if(dificultad == 'f'){
                    file = new File(PATH_INGLES+FILE_ENGLISH_EASY);
                }else{
                    file = new File(PATH_INGLES+FILE_ENGLISH_HARD);
                }
                break;
        }


        preguntas = new ArrayList<String>();
        respuestasSimple = new ArrayList<String>();
        respuestasTriple = new ArrayList<String>();
       
        BufferedReader lector = new BufferedReader( new FileReader(file));
        //
        String[] divisionAux;
        String linea = lector.readLine();
        int contadorLineas = 0;
        
        while(contadorLineas < NUM_LETRAS){
            //
            divisionAux = linea.split("/");
            preguntas.add(divisionAux[0]);
            respuestasSimple.add(divisionAux[1]);
            respuestasTriple.add(divisionAux[1]+"/"+divisionAux[2]+"/"+divisionAux[3]);
            linea = lector.readLine();
            
            contadorLineas++;
        }

        lector.close(); 

    }
    
    
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
    
    
    /**
     * Devuelve la imagen en su version azul de la letra que recibe por parámetro.
     * @param letra letra de la que queremos la imagen azul
     * @return 
     */
    
     public ImageIcon elegirImagenAzul(char letra){
       ImageIcon icon = new ImageIcon(this.getClass().getResource("/Azules/"+letra+"_a.png"));
       return icon;       
    }
     
     
     /**
     * Devuelve la imagen en su version verde de la letra que recibe por parámetro.
     * @param letra letra de la que queremos la imagen verde
     * @return 
     */
     
    public ImageIcon elegirImagenVerde(char letra){
       ImageIcon icon = new ImageIcon(this.getClass().getResource("/Verdes/"+letra+"_v.png"));
       return icon;    

    }
    
    
    /**
     * Devuelve la imagen en su version roja de la letra que recibe por parámetro.
     * @param letra letra de la que queremos la imagen roja
     * @return 
     */
    
    public ImageIcon elegirImagenRojo(char letra){
       ImageIcon icon = new ImageIcon(this.getClass().getResource("/Rojas/"+letra+"_r.png"));
       return icon;    

    }




/***********************************GETTER SETTERS*****************************************/
     public int getNUM_LETRAS() {
        return NUM_LETRAS;
    }

    public ArrayList<String> getPreguntas() {
        return preguntas;
    }

    public ArrayList<String> getRespuestasSimple() {
        return respuestasSimple;
    }

    public ArrayList<String> getRespuestasTriple() {
        return respuestasTriple;
    }
     



    
}
