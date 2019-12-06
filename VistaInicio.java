/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistrosco;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *NOMBRE,DIFICULTAD,IDIOMA
 * @author Jorge
 */
public class VistaInicio extends JFrame{
    
    CheckboxGroup grupoIdioma,grupoDificultad;
    
    Checkbox idiomaEspaniol,idiomaIngles,dificultadDificil,dificultadFacil;
    
    JLabel textoDificultad,textoIdioma,textoNombre;
    
    JPanel panelDificultad,panelIdioma,panelNombre;
    
    TextField introducirNombre;
    
    JButton botonSiguiente;

    public VistaInicio() {
        empezarVistaJuego();
    }

    private void empezarVistaJuego() {
        this.setLayout(new GridLayout(4, 1));
        
        crearParteNombre();
        crearParteDificultad();
        crearParteIdioma();
        crearBoton();
        
        this.setSize(350, 600);
        this.setLocation(100, 100);
        this.setVisible(true);
    }

    private void crearParteNombre() {
        textoNombre=new JLabel("Introduce el nombre");
        introducirNombre=new TextField();
        
        panelNombre=new JPanel(null);
        
        textoNombre.setBounds(20, 10, 200,20 );
        introducirNombre.setBounds(40, 35, 200, 25);
        
        panelNombre.add(textoNombre);
        panelNombre.add(introducirNombre);
        
        panelNombre.setBounds(0,0,150, 200);
        this.add(panelNombre);
        
    }
    
    private void crearParteIdioma() {
        textoIdioma=new JLabel("Idioma");
        textoIdioma.setBounds(20, 10, 200,20);
        
        grupoIdioma=new CheckboxGroup();
        
        idiomaEspaniol=new Checkbox();
        idiomaIngles=new Checkbox();
        
        idiomaEspaniol.setLabel("Español ");
        idiomaIngles.setLabel("Ingles ");
        
        idiomaEspaniol.setState(true);
        idiomaIngles.setState(false);
        
        idiomaEspaniol.setCheckboxGroup(grupoIdioma);
        idiomaIngles.setCheckboxGroup(grupoIdioma);
        
        idiomaEspaniol.setBounds(40, 50,100,20);
        idiomaIngles.setBounds(40, 75,100,20);
        
        panelIdioma=new JPanel(null);
        
        panelIdioma.add(textoIdioma);
        panelIdioma.add(idiomaEspaniol);
        panelIdioma.add(idiomaIngles);
        
        panelIdioma.setBounds(0,200,100, 100);
        
        this.add(panelIdioma);
    }

    
    private void crearParteDificultad() {
        textoDificultad=new JLabel("Dificultad");
        textoDificultad.setBounds(20, 0,75,20);
        
        grupoDificultad=new CheckboxGroup();
        
        dificultadFacil=new Checkbox();
        dificultadDificil=new Checkbox();
        
        dificultadFacil.setLabel("Facil ");
        dificultadDificil.setLabel("Dificil ");
        
        dificultadFacil.setState(true);
        dificultadDificil.setState(false);
        
        dificultadFacil.setCheckboxGroup(grupoDificultad);
        dificultadDificil.setCheckboxGroup(grupoDificultad);
        
        dificultadFacil.setBounds(40, 50,100,20);
        dificultadDificil.setBounds(40, 75,100,20);
        
        panelDificultad=new JPanel(null);
        
        panelDificultad.add(textoDificultad);
        panelDificultad.add(dificultadFacil);
        panelDificultad.add(dificultadDificil);
        
        panelDificultad.setBounds(0,100,100, 100);
        
        this.add(panelDificultad);
    }

    private void crearBoton() {
        botonSiguiente=new JButton("Emepezar");
        
        botonSiguiente.setBounds(3, 3, 5, 5);
        
        this.add(botonSiguiente);
                
    }
    
}
