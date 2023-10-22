/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.recuerdosgastronomicos;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

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
            File fichero = new File(ruta + nombre);

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
     */
    public static void borrar(String ruta) {
        File file = new File(ruta);
        borrarCarpeta(file);
    }

    /**
     * Método recursivo para borrar todo lo que tiene una carpeta dentro de ella
     *
     * @param fichero fichero a borrar
     */
    private static void borrarCarpeta(File fichero) {
        if (fichero.isDirectory()) {
            File[] archivos = fichero.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isDirectory()) {
                        borrarCarpeta(archivo);
                    } else {
                        archivo.delete();
                    }
                }
            }
        }
        fichero.delete();
    }

    /**
     * Método que copiará un archivo
     *
     * @param origen lugar donde se encuentra el archivo a copiar
     * @param destino lugar donde se copiará el archivo
     */
    public static void copiar(String origen, String destino) {
        File fileOrigen = new File(origen);
        File fileDestino = new File(destino + "//" + fileOrigen.getName());

        copiarCarpeta(fileOrigen, fileDestino);
    }

    /**
     * Método recursivo para copiar todo lo que tiene una carpeta dentro de ella
     *
     * @param origen lugar donde se encuentra la carpeta a copiar
     * @param destino lugar donde se copiará el archivo
     */
    public static void copiarCarpeta(File origen, File destino) {
        if (origen.isDirectory()) {
            if (!destino.exists()) {
                destino.mkdir();
            }
            String[] elementos = origen.list();
            for (String elemento : elementos) {
                copiarCarpeta(new File(origen, elemento), new File(destino, elemento));
            }
        } else {
            try {
                Files.copy(origen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioex) {
                System.out.println("ERROR E/S");
            }
        }
    }

    /**
     * Método que moverá un archivo
     *
     * @param origen lugar donde se encuentra el archivo a mover
     * @param destino lugar donde se moverá el archivo
     */
    public static void mover(String origen, String destino) {
        File fileOrigen = new File(origen);
        File fileDestino = new File(destino + "//" + fileOrigen.getName());

        moverCarpeta(fileOrigen, fileDestino);
    }

    /**
     * Método recursivo para mover todo lo que tiene una carpeta dentro de ella
     *
     * @param origen lugar donde se encuentra la carpeta a mover
     * @param destino lugar donde se moverá el archivo
     */
    private static void moverCarpeta(File origen, File destino) {
        if (origen.isDirectory()) {
            if (!destino.exists()) {
                destino.mkdir();
            }
            String[] elementos = origen.list();
            for (String elemento : elementos) {
                moverCarpeta(new File(origen, elemento), new File(destino, elemento));
            }
        } else {
            try {
                Files.move(origen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioex) {
                System.out.println("ERROR E/S");
            }
        }
    }

    /**
     * Método que Escribirá en un archivo guardando: Fecha, puntuacion, lugar,
     * precio, nombre En ese orden
     *
     * @param fich Fichero en el que se escribirá
     * @param calendar fecha en la cual se ha comido el plato
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

            buffer = new StringBuffer(nombre); //nombre
            buffer.setLength(20);
            dos.writeChars(buffer.toString());
            buffer = null;

            buffer = new StringBuffer(lugar); //lugar
            buffer.setLength(20);
            dos.writeChars(buffer.toString());
            buffer = null;

            dos.writeDouble(precio);//precio

            dos.writeDouble(puntuacion);//puntuacion

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
        File fichero = new File(ruta + nombre);
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
        File fichero = new File(ruta + nombre);
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
                nombrePlato = "";
                for (int i = 0; i < 20; i++) {
                    nombrePlato += dis.readChar();
                }
                lugar = "";
                for (int i = 0; i < 20; i++) {
                    lugar += dis.readChar();
                }
                precio = dis.readDouble();
                puntuacion = dis.readDouble();

                System.out.print("Indice: " + indice);
                System.out.print(" ");
                System.out.print("dia: " + dia);
                System.out.print(" ");
                System.out.print("mes:" + mes);
                System.out.print(" ");
                System.out.print("año: " + anio);
                System.out.print(" ");
                System.out.print("nombre:" + nombrePlato);
                System.out.print(" ");
                System.out.print("lugar: " + lugar);
                System.out.print(" ");
                System.out.print("precio: " + precio);
                System.out.print(" ");
                System.out.print("punt: " + puntuacion);
                System.out.print(" ");
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

    /**
     * Método para crear una copia de todas las comidas en un determinado año
     *
     * @param comidas ArrayList que contiene todas las comidas
     */
    public static void escrituraDatosRecuperadosBin(ArrayList<String> comidas) {

        if (!comidas.isEmpty()) {

            String[] linea = comidas.get(0).split("\\s+");
            String[] fecha = linea[1].split("/");
            String precio;

            String fich = ".\\resources\\Registro_del_" + fecha[2] + ".bin";// creamos el fichero Registro_del_[año leido]

            try (FileOutputStream fos = new FileOutputStream(fich); DataOutputStream dos = new DataOutputStream(fos)) {

                StringBuffer buffer;
                for (String comida : comidas) {
                    linea = comida.split("\\s+");
                    fecha = linea[1].split("/");

                    dos.writeInt(Integer.parseInt(linea[0]));//id

                    dos.writeInt(Integer.parseInt(fecha[0]));//fecha
                    dos.writeInt(Integer.parseInt(fecha[1]));
                    dos.writeInt(Integer.parseInt(fecha[2]));

                    buffer = new StringBuffer(linea[2]); //comida
                    buffer.setLength(20);
                    dos.writeChars(buffer.toString());
                    buffer = null;

                    buffer = new StringBuffer(linea[3]); //lugar
                    buffer.setLength(20);
                    dos.writeChars(buffer.toString());
                    buffer = null;

                    precio = linea[4].substring(0, linea[4].length() - 1);
                    dos.writeDouble(Double.parseDouble(precio));//precio

                    dos.writeDouble(Double.parseDouble(linea[5]));//puntuacion

                }

                fos.close();
                dos.close();
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }

    }

    /**
     * Método que recuperará una serie de registros según una fecha
     *
     * @param fechaBuscada fecha que debe coincidir para poder recuperarlos
     * @return ArrayList con los registros
     */
    public static ArrayList<String> recuperar(int fechaBuscada) {
        ArrayList<String> platos = new ArrayList<>();
        try {
            RandomAccessFile archivo = new RandomAccessFile(".\\resources\\Comidas.bin", "r");

            while (archivo.getFilePointer() < archivo.length()) {
                int indice = archivo.readInt();
                int dia = archivo.readInt();
                int mes = archivo.readInt();
                int anio = archivo.readInt();
                String nombrePlato = "";
                for (int i = 0; i < 20; i++) {
                    nombrePlato += archivo.readChar();
                }
                String lugar = "";
                for (int i = 0; i < 20; i++) {
                    lugar += archivo.readChar();
                }

                double precio = archivo.readDouble();
                double puntuacion = archivo.readDouble();

                if (anio == fechaBuscada && indice > 0) {
                    String info = indice + " " + dia + "/" + mes + "/" + anio + " " + nombrePlato + " " + lugar + " " + " " + precio + "€" + " " + puntuacion;
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
     * Método que escribirá una cadena usando el RandomAccessFile
     *
     * @param random RandomAccessFile
     * @param texto cadena que debe escribir
     * @param tamaño el tamaño de la cadena a escribir
     */
    public static void escibirCadena(RandomAccessFile random, String texto, int tamaño) {
        try {
            StringBuffer buffer = new StringBuffer(texto);
            buffer = new StringBuffer(texto);
            buffer.setLength(tamaño);
            random.writeChars(buffer.toString());
        } catch (IOException ioe) {
            System.out.println("ERROR E/S");
        }
    }

    /**
     * Método para modificar un registro en concreto
     *
     * @param id id del registro a modificar
     * @param calendar fecha cuando se probó el plato
     * @param lugar lugar donde se probó
     * @param nombre nombre del plato
     * @param precio precio del plato
     * @param calificacion calificacion dada
     */
    public static void modificar(int id, Calendar calendar, String nombre, String lugar, double precio, double calificacion) {
        try {
            RandomAccessFile random = new RandomAccessFile(".\\resources\\Comidas.bin", "rw");
            int posicion = (id - 1) * 112;

            random.seek(posicion);

            random.skipBytes(4);

            random.writeInt(calendar.get(Calendar.DAY_OF_MONTH));  //dia
            random.writeInt(calendar.get(Calendar.MONTH) + 1); //mes-1
            random.writeInt(calendar.get(Calendar.YEAR));//año
            escibirCadena(random, nombre, 20);
            escibirCadena(random, lugar, 20);
            random.writeDouble(precio);
            random.writeDouble(calificacion);

        } catch (FileNotFoundException fnfe) {
            System.out.println("No se encontró el fichero");
        } catch (IOException ioe) {
            System.out.println("ERROR E/S");
        }
    }

    /**
     * Método para eliminar un registro en concreto
     *
     * @param id id del registro a eliminar
     * @return devuelve un boolean para comprobar que se hizo sin problema
     */
    public static boolean eliminarRA(int id) {
        int posicion = ((id - 1) * 112);

        try {
            RandomAccessFile random = new RandomAccessFile(".\\resources\\Comidas.bin", "rw");
            random.seek(posicion);
            random.writeInt(-1);

            random.getFD().sync();
            random.close();
            return true;
        } catch (IOException ioe) {
            return false;
        }
    }

    /**
     * Método para actualiza el índice de las comidas al agregarlas
     *
     * @param indice nuevo
     */
    public static void actualizarIndice() {
        File fichero = new File(".\\resources\\indice.txt");
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
        File fichero = new File(".\\resources\\indice.txt");
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

    /**
     * Método que abre un archivo especificado
     *
     * @param ruta Ruta del archivo
     * @param nombre Nombre del archivo
     */
    public static void abrirArchivo(String ruta, String nombre) {
        try {
            File fileToOpen = new File(ruta + nombre); // Replace with the actual file path
            Desktop.getDesktop().open(fileToOpen);
        } catch (IOException ioex) {
            System.out.println("ERROR E/S");
        }
    }

    /**
     * Método que busca un registro borrado y sobreescribe sus valores
     *
     * @return True si se ha sobreescrito un registro False si no había
     * registros para sobreescribir
     */
    public static boolean sobreescribirBorrado(Calendar calendar, String lugar, String nombre, double precio, double calificacion) {
        int leido;
        int posicion = 0;
        int ident = 1;
        boolean sobreescrito = false;
        try {
            File fich = new File(".\\resources\\Comidas.bin");
            RandomAccessFile random = new RandomAccessFile(fich, "rw");

            while (!sobreescrito && posicion < fich.length()) {
                random.seek(posicion);
                leido = random.readInt();
                if (leido == -1) {
                    random.seek(posicion);//volvemos a la posicion del id para sobreescribir
                    random.writeInt(ident);//sobreescribimos el id
                    random.getFD().sync();
                    modificar(ident, calendar, nombre, lugar, precio, calificacion);
                    OperacionesXML.addNodoExistente(".\\resources\\datosModif.xml", ident, calendar, calificacion, lugar, precio, nombre);
                    sobreescrito = true;
                }
                ident++;
                posicion += 112;
            }

            random.close();
            return sobreescrito;
        } catch (EOFException eofe) {
            System.out.println("");
        } catch (IOException ioe) {
            System.out.println("");
        }
        return false;
    }

    /**
     * Método que encripta el archivo Comidas.bin
     */
    public static void encriptar() {
        File archivoOrigen = new File(".\\resources\\Comidas.bin");
        File archivoDestino = new File(".\\resources\\ComidasEncriptado.bin");

        try {
            FileInputStream lector = new FileInputStream(archivoOrigen);
            DataInputStream dis = new DataInputStream(lector);

            FileOutputStream escritor = new FileOutputStream(archivoDestino);
            DataOutputStream dos = new DataOutputStream(escritor);

            while (dis.available() > 0) {
                dos.writeByte(dis.readByte() + 1);
            }

            dis.close();
            dos.close();
            lector.close();
            escritor.close();
        } catch (IOException ioe) {
            System.out.println("ERROR E/S");
        }

    }

    /**
     * Método que desencripta el archivo
     */
    public static void desencriptar() {
        File archivoOrigen = new File(".\\resources\\ComidasEncriptado.bin");
        File archivoDestino = new File(".\\resources\\Comidas.bin");

        try {
            FileInputStream lector = new FileInputStream(archivoOrigen);
            DataInputStream dis = new DataInputStream(lector);

            FileOutputStream escritor = new FileOutputStream(archivoDestino);
            DataOutputStream dos = new DataOutputStream(escritor);

            while (dis.available() > 0) {
                dos.writeByte(dis.readByte() - 1);
            }

            dis.close();
            dos.close();
            lector.close();
            escritor.close();
        } catch (IOException ioe) {
            System.out.println("ERROR E/S");
        }

        borrar(archivoOrigen.getName());
    }
}
