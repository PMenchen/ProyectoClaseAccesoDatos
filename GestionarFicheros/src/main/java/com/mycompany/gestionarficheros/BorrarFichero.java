/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionarficheros;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author b15-08m
 */
public class BorrarFichero {
    /**
     * Método para borrar un fichero
     * 
     * @param ruta lugar donde se encuentra el archivo a borrar
     * @param nombre nombre del archivo
     */
    public static void borrar(String ruta, String nombre){
        Path rutaP=Paths.get(ruta+"//"+nombre);
        try{
            Files.delete(rutaP);
        }catch(IOException ioe){
            System.out.println("ERROR E/S");
        }
    }
}
