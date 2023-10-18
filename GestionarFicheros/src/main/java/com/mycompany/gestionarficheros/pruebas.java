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
        
        int indicePlatos; int indiceLugares; int n = 1;
        String plato;
        String lugar;
        double precio;
        int puntuacion;
        Calendar fecha;
        //SimpleDateFormat sdf;
        
        while (!(n==5)) {            
            indicePlatos = randomElemArray(0, nombresPlatos.length -1);
            indiceLugares = randomElemArray(0, lugares.length -1);
            plato = nombresPlatos[indicePlatos];
            lugar = lugares[indiceLugares];
            precio = randomDouble(10, 20);
            puntuacion = randomInt(0, 5);
            fecha = new GregorianCalendar(randomInt(2000, 2023), randomInt(0, 11), randomInt(1, 30));
            
            EscrituraFicheroSEC.EscrituraSEC(/*fecha,*/ puntuacion, lugar, precio, plato, ruta);
            
            n++;
        }
        
        LeerBinario_CrearXML.crearXML(ruta);
    }
    
}
