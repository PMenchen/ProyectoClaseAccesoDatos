/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.recuerdosgastronomicos;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

/**
 *
 * @author b15-19m
 * @author b15-08m
 * @author b15-21m
 */
public class GestorArchivos {
    static int id=0;
    
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
     * Método que Escribirá en un archivo guardando:
     * Fecha, puntuacion, lugar, precio, nombre
     * En ese orden
     * 
     * @param fich Fichero en el que se escribirá
     * //@param fecha fecha en la que se ha comido el plato
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
    
    /**
     * Método para leer de manera secuencial un archivo (txt)
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
                System.out.println(linea);
            }
            
            br.close();
            lector.close();
        }catch(FileNotFoundException fnfe){
            System.out.println("NO se encontró el fichero");
        } catch (IOException iex) {
            System.out.println("ERROR E/S");
        }
    }
    
    /**
     * Método para leer de manera secuencial un archivo (binario)
     * 
     * @param ruta lugar donde se encuentra el archivo a leer
     * @param nombre nombre del archivo
     */
    public static void leerSecuencialBin(String ruta, String nombre){
        File fichero=new File(ruta+"//"+nombre);
        try (FileInputStream fis = new FileInputStream(fichero);
                DataInputStream dis = new DataInputStream(fis)) {
            
            
            int indice;
            int puntuacion;
            String lugar;
            double precio;
            String nombrePlato;
            
                while (fis.available() > 0) {
                    indice = dis.readInt();
                    puntuacion = dis.readInt();
                    lugar = "";
                    for (int i=0; i<10; i++) {
                        lugar += dis.readChar();
                    }
                    precio = dis.readDouble();
                    nombrePlato = "";
                    for (int i=0; i<20; i++) {
                        nombrePlato += dis.readChar();
                    }

                    System.out.print(indice);
                    System.out.print(" ");
                    System.out.print(puntuacion);
                    System.out.print(" ");
                    System.out.print(lugar);
                    System.out.print(" ");
                    System.out.print(precio);
                    System.out.print(" ");
                    System.out.print(nombrePlato);
                    System.out.println();
                }
            fis.close();
            dis.close();
        }catch(FileNotFoundException fnfe){
            System.out.println("NO se encontró el fichero");
        } catch (IOException iex) {
            System.out.println("Lectura terminada...");
        }
    }

}
