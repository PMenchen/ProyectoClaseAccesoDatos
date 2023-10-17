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
public class MoverFichero {
    /**
     * Método para mover un fichero
     * 
     * @param origen lugar donde se encuentra el fichero a mover
     * @param nombre nombre del fichero a mover
     * @param destino lugar donde se moverá el fichero
     * @throws IOException 
     */
    public static void copiar(String origen, String nombre, String destino) throws IOException{
        Path origenP=Paths.get(origen+"//"+nombre);
        Path destinoP=Paths.get(destino+"//"+nombre);
        try{
            Files.move(origenP, destinoP);
        }catch(IOException ioe){
            System.out.println("ERROR E/S");
        }
    }
}
