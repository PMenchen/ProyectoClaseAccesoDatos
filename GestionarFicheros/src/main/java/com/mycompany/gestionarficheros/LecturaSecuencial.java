/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionarficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b15-08m
 */
public class LecturaSecuencial {
    /**
     * Método para leer de manera secuencial un archivo
     * 
     * @param ruta lugar donde se encuentra el archivo a leer
     * @param nombre nombre del archivo
     */
    public static void leerSecuencial(String ruta, String nombre){
        File fichero=new File(ruta+"//"+nombre);
        try{
            FileReader lector = new FileReader(fichero);
            BufferedReader br = new BufferedReader(lector);
            
            String linea;
            while((linea=br.readLine())!=null){
                //System.out.println(linea);
            }
            
            br.close();
            lector.close();
        }catch(FileNotFoundException fnfe){
            System.out.println("NO se encontró el fichero");
        } catch (IOException iex) {
            System.out.println("ERROR E/S");
        }
    }
}
