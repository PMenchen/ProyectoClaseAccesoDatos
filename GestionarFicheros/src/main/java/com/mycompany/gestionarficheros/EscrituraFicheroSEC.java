/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionarficheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

/**
 *
 * @author b15-19m
 */
public class EscrituraFicheroSEC {
    
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
     */
    public static void EscrituraSEC(Date fecha, int puntuacion, String lugar, double precio, String nombre) {
        try (RandomAccessFile fo = new RandomAccessFile(new File(".//Comidas.dat"), "rw")) {
            
            StringBuffer buffer=null;
            
            //fo.writeDate(fecha);
            
            fo.writeInt(puntuacion);//puntuacion
            
            buffer = new StringBuffer(lugar); //lugar
            buffer.setLength(10);
            fo.writeChars(buffer.toString());
            buffer=null;

            fo.writeDouble(precio);//salario

            buffer = new StringBuffer(nombre); //nombre
            buffer.setLength(10);
            fo.writeChars(buffer.toString());
            buffer=null;
            
            fo.close();
            
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
    
}

