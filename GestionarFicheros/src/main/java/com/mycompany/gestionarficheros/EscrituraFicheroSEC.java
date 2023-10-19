/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionarficheros;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author b15-19m
 */
public class EscrituraFicheroSEC {
    static int id=0;
    
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
    public static void escrituraSEC(File fich, /*Calendar fecha,*/ int puntuacion, String lugar, double precio, String nombre) {
        try (FileOutputStream fos = new FileOutputStream(fich, true);
                DataOutputStream dos = new DataOutputStream(fos)) {
            
            StringBuffer buffer;
            dos.writeInt(id);//indice
            id++;
            
            //escritura de la fecha (en numeros)
            /*fo.writeInt(fecha.DATE);  //dia
            fo.writeInt(fecha.MONTH); //mes
            fo.writeInt(fecha.YEAR); //año
            */
            
            dos.writeInt(puntuacion);//puntuacion
            
            buffer = new StringBuffer(lugar); //lugar
            buffer.setLength(10);
            dos.writeChars(buffer.toString());
            buffer=null;

            dos.writeDouble(precio);//salario

            buffer = new StringBuffer(nombre); //nombre
            buffer.setLength(20);
            dos.writeChars(buffer.toString());
            buffer=null;
            
            fos.close();
            dos.close();
            
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
    
}

