/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

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
     * @param destino lugar donde se copiará el archivo
     * @throws IOException 
     */
    public static void copiar(String origen, String destino) throws IOException{
        Path origenP=Paths.get(origen);
        Path destinoP=Paths.get(destino);
        try{
            Files.copy(origenP, destinoP);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }
}
