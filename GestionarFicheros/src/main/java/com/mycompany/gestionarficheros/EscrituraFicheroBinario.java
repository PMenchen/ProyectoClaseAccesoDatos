/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionarficheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author b15-19m
 */
public class EscrituraFicheroBinario {
    
    public static void main (String[] args) {
        
    }
    
    /**
     * Crea un fichero.dat "Comidas" en donde se guarda:
     * Fecha, puntuacion, lugar, precio, nombre
     * En ese orden
     * 
     * @param fecha fecha en la que se ha comido el plato
     * @param puntuacion puntuacion/estrellas dadas al plato
     * @param lugar lugar (restaurante) en donde se ha comido
     * @param precio precio
     * @param nombre nombre del plato
     * @param ruta fragmento de la ruta que no cambia para pruebas
     */
    public static void EscrituraSEC(/*Calendar fecha, */int puntuacion, String lugar, double precio, String nombre, String ruta) {
        try (RandomAccessFile fo = new RandomAccessFile(new File(ruta + "comidas.bin"), "rw")) {
            
            if (fo.length() > 0) {
                // If the file is not empty, move the file pointer to the end
                fo.seek(fo.length());
            }
            
            StringBuffer buffer=null;
            
            /*//escritura de la fecha (en numeros)
            fo.writeInt(fecha.DATE);  //dia
            fo.writeInt(fecha.MONTH); //mes
            fo.writeInt(fecha.YEAR); //año*/
            
            buffer = new StringBuffer(nombre); //nombre
            buffer.setLength(20);
            fo.writeChars(buffer.toString());
            buffer=null;
            
            buffer = new StringBuffer(lugar); //lugar
            buffer.setLength(20);
            fo.writeChars(buffer.toString());
            buffer=null;

            fo.writeDouble(precio);//precio
            
            fo.writeInt(puntuacion);//puntuacion
            
            fo.close();
            
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
    
    
}

