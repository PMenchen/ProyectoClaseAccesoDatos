/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionarficheros;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author b15-08m
 */
public class CrearFichero {
    /**
     * Método que creara un archivo
     * 
     * @param ruta lugar donde se creará el archivo
     * @param nombre nombre del archivo
     */
    public static void crear(String ruta, String nombre){
       
        try{
            File fichero=new File(ruta+"//"+nombre);
            
            if(fichero.createNewFile()){
                System.out.println("[TEXTO DEFECTO]");
            }
        }catch(IOException ioe){
            System.out.println("ERROR E/S");
        }
       
       
    }
}
