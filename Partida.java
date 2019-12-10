/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasapalabrajorge;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author demum
 */
public class Partida implements Serializable {
    
   
    
/************************************PARAMETROS********************************************/
/*************************************ATRIBUTOS********************************************/
    private String nombrePartida;
    private String[][] progresoRosco;
    private String puntuacion;
/************************************REFERENCIAS*******************************************/
/************************************CONSTRUCTOR*******************************************/
    public Partida(String nombrePartida, String[][] progresoRosco, String puntuacion){
        this.nombrePartida = nombrePartida;
        this.progresoRosco = progresoRosco;
        this.puntuacion = puntuacion;
    }

  
/***********************************METODOS CLASE******************************************/
/*************************************INTERFAZ*********************************************/
/***********************************GETTER SETTERS*****************************************/
    public String getNombrePartida() {
      return nombrePartida;
    }

    public String getPuntuacion() {
        return puntuacion;
    }
    
}
