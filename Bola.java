/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasapalabrajorge;

import java.awt.Rectangle;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author demum
 */
public class Bola extends JLabel {
    //
/************************************PARAMETROS********************************************/

    private final Rectangle TAM_BOLA = new Rectangle(70,70);
    //
    private static int numLetraAAsignar = 0;

/*************************************ATRIBUTOS********************************************/
    private char letra;
    private char estado;        //A-Acierto, F-Fallada, N-Neutra

    private final char[] abc_spanish  = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

/************************************REFERENCIAS*******************************************/

    private BaseDatos bd;

/************************************CONSTRUCTOR*******************************************/

    /**
     * El constructor de Bola asigna una letra que corresponda segund las anteriores letras que se hayan creado.
     * Se crean con un tamaño predefinido.
     * @param estado 
     */
    public Bola(char estado, BaseDatos bd){
        
        this.bd = bd;
        this.estado = estado;
        asignarLetra();

        this.setBounds(TAM_BOLA);
    }
/***********************************METODOS CLASE******************************************/

    /**
     * Asigna la letra que corresponda segun las letras que se hayan creado antes.
     */
    private void asignarLetra(){
        this.letra = abc_spanish[numLetraAAsignar];
        numLetraAAsignar++;
        if(numLetraAAsignar == 25) numLetraAAsignar = 0;
    }


/*************************************INTERFAZ*********************************************/
    
    /**
     * Cambia el estado visual de una bola.
     * @param estado 
     */
    public void cambiarEstado(char estado){
        switch(estado){
            case 'a':
                this.setIcon(bd.elegirImagenVerde(letra));
                break;
            case 'f':
                this.setIcon(bd.elegirImagenRojo(letra));
                
        }
    }
/***********************************GETTER SETTERS*****************************************/

    public char getLetra() {
        return letra;
    }

    public char getEstado() {
        return estado;
    }
}