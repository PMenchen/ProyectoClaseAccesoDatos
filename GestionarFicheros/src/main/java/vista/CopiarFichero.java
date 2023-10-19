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
public class CopiarFichero {
    /**
     * Método que copiará un archivo
     * 
     * @param origen lugar donde se encuentra el archivo a copiar
     * @param nombre nombre del archivo a copiar
     * @param destino lugar donde se copiará el archivo
     * @throws IOException 
     */
    public static void copiar(String origen, String nombre, String destino) throws IOException{
        Path origenP=Paths.get(origen+"//"+nombre);
        Path destinoP=Paths.get(destino+"//"+nombre);
        try{
            Files.copy(origenP, destinoP);
        }catch(IOException ioe){
            System.out.println("ERROR E/S");
        }
    }
}
