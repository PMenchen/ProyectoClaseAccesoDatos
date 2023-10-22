/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.recuerdosgastronomicos;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author pedro
 */
public class OperacionesXML {
    
    /**
     * Método que parsea un XML para poder trabajar sobre él
     * @param ruta
     * @return 
     */
    static  Document ParseDocumentoXML(String ruta) {
        try {
            File xml = new File(ruta);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
        
            return builder.parse(xml);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println(e);
            return null;
        }
    }
    
    /**
     * Método para modificar los datos de un nodo buscado por id
     * @param doc Documento XML
     * @param idBuscar Id por el cual buscar el nodo a modificar
     * @param nuevoNombre   Nuevo nombre a insertar
     * @param nuevoLugar    Nuevo lugar a insertar
     * @param nuevoPrecio   Nuevo precio a insertar
     * @param nuevaPunt     Nueva puntuación a insertar
     * @param nuevaFecha    Nueva fecha a insertar
     */
    static void Modificar(Document doc, String idBuscar, String nuevoNombre, String nuevoLugar, String nuevoPrecio, String nuevaPunt, String nuevaFecha){
        
        NodeList listaPlatos = doc.getElementsByTagName("Plato");
        
        for (int i = 0; i < listaPlatos.getLength(); i++) {
            Element e = (Element) listaPlatos.item(i);
            String idPlato = e.getElementsByTagName("id").item(0).getTextContent();
            
            if (idBuscar.equals(idPlato)) {
                e.getElementsByTagName("fecha").item(0).setTextContent(nuevaFecha);
                e.getElementsByTagName("nombre").item(0).setTextContent(nuevoNombre);
                e.getElementsByTagName("lugar").item(0).setTextContent(nuevoLugar);
                e.getElementsByTagName("precio").item(0).setTextContent(nuevoPrecio);
                e.getElementsByTagName("puntuacion").item(0).setTextContent(nuevaPunt);
                
                break; //Terminamos el bucle una vez se ha encontrado el plato deseado
            }
            
        }
    }
    
    /**
     * Método para modificar los valores de un nodo, de un archivo xml existente a un archivo destino, buscando por el id
     * @param rutaOrigen Ruta del archivo a modificar
     * @param idBuscar Id por el cual buscar el nodo a modificar
     * @param nuevoNombre   Nuevo nombre a insertar
     * @param nuevoLugar    Nuevo lugar a insertar
     * @param nuevoPrecio   Nuevo precio a insertar
     * @param nuevaPunt     Nueva puntuación a insertar
     * @param nuevaFecha    Nueva fecha a insertar
     * @param fichDestino Archivo destino para la modificación
     */
    
    public static void ModificarXML(String rutaOrigen, String idBuscar, String nuevoNombre, String nuevoLugar, String nuevoPrecio, String nuevaPunt, String nuevaFecha, String fichDestino){
        try {
            Document doc = ParseDocumentoXML(rutaOrigen);
        
            Modificar(doc, idBuscar, nuevoNombre, nuevoLugar, nuevoPrecio, nuevaPunt, nuevaFecha);

            LeerBinario_CrearXML.EscribirArchivo(doc, ".\\resources\\" + fichDestino);
            
        } catch (TransformerException e) {
            System.out.println(e);
            e.getMessage();
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        
    }
    
}
