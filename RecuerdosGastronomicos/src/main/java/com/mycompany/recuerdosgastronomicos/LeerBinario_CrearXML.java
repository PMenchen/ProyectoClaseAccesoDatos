/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.recuerdosgastronomicos;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class LeerBinario_CrearXML {

    /**
     * Inicializa un nuevo documento XML
     *
     * @param name El nombre del documento XML.
     * @return Documento XML inicializado.
     * @throws ParserConfigurationException Si ocurre un error durante la
     * creación del documento.
     */
    static Document InitBuilder(String name) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();

        Document d = implementation.createDocument(null, name, null);
        d.setXmlVersion("1.0");

        return d;
    }

    /**
     * Método para crear un nodo principal
     *
     * @param value Valor del nombre
     * @param document Documento XML en donde se insertará el nodo principal
     * @return Devuelve el elemento raiz al cual se añadirán otros nodos
     */
    static Element CrearNodo(String value, Document document) {
        Element raiz = document.createElement(value);
        document.getDocumentElement().appendChild(raiz);

        return raiz;
    }

    /**
     * Método para añadir un nodo
     *
     * @param datoPlato Nombre del nodo a añadir
     * @param valor Valor a asignar en ese nodo
     * @param raiz La raíz de la que crece el nodo
     * @param document Documento XML
     */
    static void AddNodo(String datoPlato, String valor, Element raiz, Document document) {

        Element elem = document.createElement(datoPlato); //creamos el hijo
        Text text = document.createTextNode(valor.trim()); //damos valor
        elem.appendChild(text); //pegamos el valor al elemento
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz

    }

    /**
     * Convierte registros en binario a un Nodo que se agregará a un XML
     *
     * @param ruta Del archivo binario
     * @param d Documento
     */
    static void TransformarBinToNodo(String ruta, Document d) {
        String nombre;
        String lugar;
        double puntuacion;
        double precio;
        int id;
        int day;
        int month;
        int year;
        String stringFecha;

        try (RandomAccessFile fichero = new RandomAccessFile(ruta, "r")) {
            byte[] b = new byte[40];
            while (fichero.getFilePointer() < fichero.length()) {
                Element e = CrearNodo("Plato", d);

                id = fichero.readInt();

                day = fichero.readInt();
                month = fichero.readInt();
                year = fichero.readInt();

                stringFecha = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);

                fichero.read(b);
                nombre = new String(b, "UTF-16");

                fichero.read(b);
                lugar = new String(b, "UTF-16");

                precio = fichero.readDouble();

                puntuacion = fichero.readDouble();

                if (id != -1) {//si el id no está eliminado
                    AddNodo("id", String.valueOf(id), e, d);
                    AddNodo("fecha", stringFecha, e, d);
                    AddNodo("nombre", nombre, e, d);
                    AddNodo("lugar", lugar, e, d);
                    AddNodo("precio", String.valueOf(precio), e, d);
                    AddNodo("puntuacion", String.valueOf(puntuacion), e, d);

                }

            }
            fichero.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause());
            ex.printStackTrace();

        }
    }

    /**
     * Escribe el contenido de un documento XML en un archivo en la ruta
     * especificada.
     *
     * @param document El documento XML
     * @param ruta La ruta del archivo donde se guardará el contenido XML
     * @throws TransformerConfigurationException Si ocurre un error de
     * configuración en el transformador.
     * @throws TransformerException Si ocurre un error durante la transformación
     * y escritura del documento.
     */
    static void EscribirArchivo(Document document, String ruta) throws TransformerConfigurationException, TransformerException {
        Source source = new DOMSource(document);
        Result salida = new StreamResult(new File(ruta));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, salida);
    }

    /**
     *
     * @param route Ruta del archivo
     * @param file Nombre del archivo
     */
    public static void crearXML(String route, String file) {
        try {
            String origen = route + file;
            String ruta = route + "datos.xml";

            String value = "Platos";
            Document document = InitBuilder(value);

            TransformarBinToNodo(origen, document);

            EscribirArchivo(document, ruta);

        } catch (Exception e) {
            System.out.println(e);
            e.getMessage();
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
}
