/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static void copiar(String origen, String destino) {
        File fileOrigen=new File(origen);
        File fileDestino=new File(destino);
        
        copiarCarpeta(fileOrigen, fileDestino);
    }
    
    /**
     * Método recursivo para copiar todo lo que tiene una carpeta dentro de ella
     * @param origen lugar donde se encuentra la carpeta a copiar
     * @param destino lugar donde se copiará el archivo
     */
    private static void copiarCarpeta(File origen, File destino){
        if(origen.isDirectory()){
            if(!destino.exists()){
                destino.mkdirs();
            }
            String[] files=origen.list();
            if(files!=null){
                for(String file:files){
                    File origenP=new File(origen, file);
                    File destinoP=new File(destino, file);
                    
                    if(origenP.isDirectory()){
                        copiarCarpeta(origenP, destinoP);
                    }else{
                        try { 
                            Files.copy(origenP.toPath(), destinoP.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException ioe) {
                            System.out.println(ioe);
                        }
                    }
                }
            }
        }
    }        
}
