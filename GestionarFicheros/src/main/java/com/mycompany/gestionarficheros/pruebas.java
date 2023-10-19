/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.gestionarficheros;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author b15-21m
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    
    
    final static String ruta = ".\\recursos\\";
    
    public static int randomElemArray(int min, int max){
        
        return ThreadLocalRandom.current().nextInt(min, max + 1);
        
    }
    
    public static int randomInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }
    
    public static double randomDouble(int min, int max){
        return ThreadLocalRandom.current().nextDouble(min,max);
    }
    
    public static void main(String[] args) {
        
        String[] nombresPlatos = {"Carbonara", "Pisto", "Paella", "Gachas", "Risotto", "Puchero"};
        String[] lugares = {"Taberna", "Venecia", "Gondola", "Speaker", "Bananas"};
        Double[] precios = {10.7,12.3,15.8,20.4,17.0};
        
                
        EscrituraFicheroSEC.EscrituraSEC(2, "Speaker", 17.0, "Paella", ruta);
        EscrituraFicheroSEC.EscrituraSEC(4, "Taberna", 15.8, "Gachas", ruta);
        EscrituraFicheroSEC.EscrituraSEC(3, "Venecia", 12.3, "Risotto", ruta);
        EscrituraFicheroSEC.EscrituraSEC(1, "Bananas", 20.4, "Pisto", ruta);
        EscrituraFicheroSEC.EscrituraSEC(5, "Gondola", 10.7, "Carbonara", ruta);
        
        LeerBinario_CrearXML.crearXML(ruta);
    }
    
}
