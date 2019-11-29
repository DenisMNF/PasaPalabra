/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectorosco;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author demum
 */
public class Bola extends JLabel{
    //
/************************************PARAMETROS********************************************/
    private static int numAuxiliar = 0;
    //
    private final ImageIcon neutro = null;
    private final ImageIcon acierto = null;
    private final ImageIcon error = null;
            
/*************************************ATRIBUTOS********************************************/
    private char letra;
    private char[] abecedario;
    
/************************************REFERENCIAS*******************************************/
    private BaseDatos bd;
    
/************************************CONSTRUCTOR*******************************************/
    public Bola(BaseDatos bd){
        this.bd = bd;
        this.letra = abecedario[numAuxiliar];
        asignarLetra();
        
    }
/***********************************METODOS CLASE******************************************/
    private void asignarLetrasRosco(){       
        abecedario = bd.getAbc_spanish();
    }
    private void asignarLetra(){
        this.letra = abecedario[numAuxiliar];
        numAuxiliar++;
    }
/*************************************INTERFAZ*********************************************/
/***********************************GETTER SETTERS*****************************************/
}