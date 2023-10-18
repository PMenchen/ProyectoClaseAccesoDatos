/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.recuerdosgastronomicos;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

/**
 *
 * @author b15-19m
 */
public class GestorArchivos {
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
    public static void EscrituraSEC(Calendar fecha, int puntuacion, String lugar, double precio, String nombre) {
        try (RandomAccessFile fo = new RandomAccessFile(new File(".//Comidas.dat"), "rw")) {
            
            if (fo.length() > 0) {
                // If the file is not empty, move the file pointer to the end
                fo.seek(fo.length());
            }
            
            StringBuffer buffer=null;
            
            //escritura de la fecha (en numeros)
            fo.writeInt(fecha.DATE);  //dia
            fo.writeInt(fecha.MONTH); //mes
            fo.writeInt(fecha.YEAR); //año
            
            fo.writeInt(puntuacion);//puntuacion
            
            buffer = new StringBuffer(lugar); //lugar
            buffer.setLength(10);
            fo.writeChars(buffer.toString());
            buffer=null;

            fo.writeDouble(precio);//salario

            buffer = new StringBuffer(nombre); //nombre
            buffer.setLength(20);
            fo.writeChars(buffer.toString());
            buffer=null;
            
            fo.close();
            
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

}
