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
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b15-19m
 * @author b15-08m
 * @author b15-21m
 */
public class GestorArchivos {

    static int id = setIndice();

    /**
     * Método que creara un archivo
     *
     * @param ruta lugar donde se creará el archivo
     * @param nombre nombre del archivo
     */
    public static void crear(String ruta, String nombre) {

        try {
            File fichero = new File(ruta + "//" + nombre);

            if (fichero.createNewFile()) {
                System.out.println("Fichero ya existente");
            }
        } catch (IOException ioe) {
            System.out.println("ERROR E/S");
        }

    }

    /**
     * Método para borrar un fichero
     *
     * @param ruta lugar donde se encuentra el archivo a borrar
     * @param nombre nombre del archivo
     */
    public static void borrar(String ruta, String nombre) {
        Path rutaP = Paths.get(ruta + "//" + nombre);
        try {
            Files.delete(rutaP);
        } catch (IOException ioe) {
            System.out.println("ERROR E/S");
        }
    }

    /**
     * Método que copiará un archivo
     *
     * @param origen lugar donde se encuentra el archivo a copiar
     * @param destino lugar donde se copiará el archivo
     * @throws IOException
     */
    public static void copiar(String origen, String destino) {
        File fileOrigen = new File(origen);
        File fileDestino = new File(destino);

        copiarCarpeta(fileOrigen, fileDestino);
    }

    /**
     * Método recursivo para copiar todo lo que tiene una carpeta dentro de ella
     *
     * @param origen lugar donde se encuentra la carpeta a copiar
     * @param destino lugar donde se copiará el archivo
     */
    private static void copiarCarpeta(File origen, File destino) {
        if (origen.isDirectory()) {
            if (!destino.exists()) {
                destino.mkdirs();
            }
            String[] files = origen.list();
            if (files != null) {
                for (String file : files) {
                    File origenP = new File(origen, file);
                    File destinoP = new File(destino, file);

                    if (origenP.isDirectory()) {
                        copiarCarpeta(origenP, destinoP);
                    } else {
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

    /**
     * Método que Escribirá en un archivo guardando: Fecha, puntuacion, lugar,
     * precio, nombre En ese orden
     *
     * @param fich Fichero en el que se escribirá //@param fecha fecha en la que
     * se ha comido el plato
     * @param puntuacion puntuacion/estrellas dadas al plato
     * @param lugar lugar (restaurante) en donde se ha comido
     * @param precio precio
     * @param nombre nombre del plato
     */
    public static void escrituraSEC(File fich, Calendar calendar, double puntuacion, String lugar, double precio, String nombre) {
        try (FileOutputStream fos = new FileOutputStream(fich, true); DataOutputStream dos = new DataOutputStream(fos)) {

            StringBuffer buffer;
            dos.writeInt(id);//indice
            actualizarIndice();

            //escritura de la fecha (en numeros)
            dos.writeInt(calendar.get(Calendar.DAY_OF_MONTH));  //dia
            dos.writeInt(calendar.get(Calendar.MONTH) + 1); //mes-1
            dos.writeInt(calendar.get(Calendar.YEAR));//año

            dos.writeDouble(puntuacion);//puntuacion

            buffer = new StringBuffer(lugar); //lugar
            buffer.setLength(10);
            dos.writeChars(buffer.toString());
            buffer = null;

            dos.writeDouble(precio);//salario

            buffer = new StringBuffer(nombre); //nombre
            buffer.setLength(20);
            dos.writeChars(buffer.toString());
            buffer = null;

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
    public static void leerSecuencial(String ruta, String nombre) {
        File fichero = new File(ruta + "//" + nombre);
        try {
            FileReader lector = new FileReader(fichero);
            BufferedReader br = new BufferedReader(lector);

            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            br.close();
            lector.close();
        } catch (FileNotFoundException fnfe) {
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
    public static void leerSecuencialBin(String ruta, String nombre) {
        File fichero = new File(ruta + "//" + nombre);
        try (FileInputStream fis = new FileInputStream(fichero); DataInputStream dis = new DataInputStream(fis)) {

            int indice;
            int dia;
            int mes;
            int anio;
            double puntuacion;
            String lugar;
            double precio;
            String nombrePlato;

            while (fis.available() > 0) {
                indice = dis.readInt();
                dia = dis.readInt();
                mes = dis.readInt();
                anio = dis.readInt();
                puntuacion = dis.readDouble();
                lugar = "";
                for (int i = 0; i < 10; i++) {
                    lugar += dis.readChar();
                }
                precio = dis.readDouble();
                nombrePlato = "";
                for (int i = 0; i < 20; i++) {
                    nombrePlato += dis.readChar();
                }

                System.out.print("Indice: " + indice);
                System.out.print(" ");
                System.out.print("dia: " + dia);
                System.out.print(" ");
                System.out.print("mes:" + mes);
                System.out.print(" ");
                System.out.print("año: " + anio);
                System.out.print(" ");
                System.out.print("punt: " + puntuacion);
                System.out.print(" ");
                System.out.print("lugar: " + lugar);
                System.out.print(" ");
                System.out.print("precio: " + precio);
                System.out.print(" ");
                System.out.print("nombre:" + nombrePlato);
                System.out.println();
            }
            fis.close();
            dis.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("NO se encontró el fichero");
        } catch (IOException iex) {
            System.out.println("Lectura terminada...");
        }
    }

    public static ArrayList<String> recuperar(int fechaBuscada) {
        ArrayList<String> platos = new ArrayList<>();
        try {
            RandomAccessFile archivo = new RandomAccessFile("Comidas.bin", "r");

            while (archivo.getFilePointer() < archivo.length()) {
                int indice = archivo.readInt();
                int dia = archivo.readInt();
                int mes = archivo.readInt();
                int anio = archivo.readInt();
                double puntuacion = archivo.readDouble();
                String lugar = "";
                for (int i = 0; i < 10; i++) {
                    lugar += archivo.readChar();
                }
                double precio = archivo.readDouble();
                String nombrePlato = "";
                for (int i = 0; i < 20; i++) {
                    nombrePlato += archivo.readChar();
                }

                if (anio == fechaBuscada) {
                    String info = indice + " " + dia + "/" + mes + "/" + anio + " " + puntuacion + " " + lugar + " " + " " + nombrePlato + " " + precio + "€";
                    platos.add(info);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return platos;
    }

    /**
     * Método para actualiza el índice de las comidas al agregarlas
     *
     * @param indice nuevo
     */
    public static void actualizarIndice() {
        File fichero = new File(".//indice.txt");
        try (FileWriter escribir = new FileWriter(fichero)) {
            int newId = id++;
            escribir.write(String.valueOf(newId));

        } catch (IOException iex) {
            System.out.println("ERROR E/S");
        }
    }

    /**
     * Método para leer el índice de las comidas (guardado en un fichero)
     *
     * @return el valor del índice
     */
    public static int setIndice() {
        int indice = -1;
        File fichero = new File(".//indice.txt");
        if (fichero.exists() && fichero.length() != 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {

                indice = Integer.parseInt(reader.readLine()) + 1;

            } catch (IOException | NumberFormatException e) {
                System.out.println(e);
            }
        } else {
            indice = 1;
        }
        return indice;
    }
    
    public static void escibirCadena(RandomAccessFile random, String texto){
        try{
            StringBuffer buffer = new StringBuffer(texto);
            buffer = new StringBuffer(texto);
            buffer.setLength(10);
            random.writeChars(buffer.toString());
        }catch (IOException ioe) {
            System.out.println("ERROR E/S");
        }
    }
    
    public static void modificar(int id, Calendar calendar, String lugar, String nombre, double precio, double calificacion){
        try {
            RandomAccessFile random = new RandomAccessFile("Comidas.bin", "rw");
            int posicion=(id-1)*32;
            
            random.seek(posicion);
            
            random.skipBytes(4);
            
            random.writeInt(calendar.get(Calendar.DAY_OF_MONTH));  //dia
            random.writeInt(calendar.get(Calendar.MONTH) + 1); //mes-1
            random.writeInt(calendar.get(Calendar.YEAR));//año
            random.writeDouble(calificacion);
            escibirCadena(random, lugar);
            random.writeDouble(precio);
            escibirCadena(random, nombre);
                
        } catch (FileNotFoundException fnfe) {
            System.out.println("No se encontró el fichero");
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
    
    public static boolean eliminarRA(int id){
        int posicion=((id-1)*32);
        
        try {
            RandomAccessFile random = new RandomAccessFile("Comidas.bin", "rw");
            random.seek(posicion);
            random.writeInt(-1);
            
            return true;
        } catch (IOException ioe) {
            return false;
        }
    }
}
