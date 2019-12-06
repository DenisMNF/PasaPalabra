/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistrosco;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Jorge
 */
public class Logica {
    private int minutos,segundos;
    
    private Timer coronometro;
    
    private Vista vista;
    
    /*empieza quitar para 
    ImageIcon imagen;
    
    JLabel label;
    
    JPanel bola;
    
    String[] letras= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    
    static int contador=0;
    
    public JLabel Imagen() {
        imagen=new ImageIcon("Imagenes/Img Azul/"+letras[contador]+"_a.png");
        contador++;
        label=new JLabel();
        label.setIcon(imagen);
        return label;
    }
    //termina quitar para prueba*/

    public Logica() {
        vista=new Vista(this);
        cronometro();
    }
    
    public String setPregunta(String pregunta){
        return pregunta;
    }
    
    public void getRespuesta(String respuesta){
        
    }
    
    public JLabel getBola(){
        //cambiar oara que reciba la imagen de la bola
        return Imagen();
    }
    
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
     * se pausa el juego en el caso de que sea necesario
     */
    public void pausarJuego(){
        coronometro.stop();
    }

    /**
     * llama al metodo start de cronometro
     */
    private void empiezaCronometro() {
        coronometro.start();
    }
    
    
    
    
}
