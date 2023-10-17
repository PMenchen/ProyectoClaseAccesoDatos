/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.gestionarficheros;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author b15-21m
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    
    
    public static int randomElemArray(int min, int max){
        
        return ThreadLocalRandom.current().nextInt(min, max + 1);
        
    }
    
    public static void main(String[] args) {
        
        String[] nombresPlatos = {""};
        String[] lugares = {""};
        
        int indicePlatos = randomElemArray(0, nombresPlatos.length -1);
        int indiceLugares = randomElemArray(0, lugares.length -1);
        String plato;
        String lugar;
        
        while (true) {            
            plato = nombresPlatos[indicePlatos];
            lugar = lugares[indiceLugares];
        }
    }
    
}
