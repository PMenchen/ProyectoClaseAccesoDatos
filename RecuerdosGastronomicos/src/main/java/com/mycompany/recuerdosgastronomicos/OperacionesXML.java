/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.recuerdosgastronomicos;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
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
     * Método que añade un nuevo nodo a un XML existente
     * @param XML El archivo XML
     * @param calendar La fecha
     * @param calificacion La calificación
     * @param lugar El lugar
     * @param precio El precio
     * @param nombrePlato El nombre del plato
     */
    public static void addNodoExistente(String XML, Calendar calendar, Double calificacion, String lugar, Double precio, String nombrePlato) {
        try {
            File archivoXML = new File(XML);

            DocumentBuilderFactory documentBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBF.newDocumentBuilder();
            Document doc = documentBuilder.parse(archivoXML.getName());
            Element raiz = doc.getDocumentElement();
            
            NodeList listaNodos = doc.getElementsByTagName("id");
            int ultimoID = -1;
            for (int i = 0; i < listaNodos.getLength(); i++) {
                Element elem = (Element) listaNodos.item(i);
                int id = Integer.parseInt(elem.getTextContent());
                if (id > ultimoID) {
                    ultimoID = id;
                }
            }
            
            int day=calendar.get(Calendar.DAY_OF_MONTH);  //dia
            int month=calendar.get(Calendar.MONTH) + 1; //mes-1
            int year=calendar.get(Calendar.YEAR);
                    
            String stringFecha = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);

            LeerBinario_CrearXML.AddNodo("id", String.valueOf(ultimoID+1), raiz, doc);
            LeerBinario_CrearXML.AddNodo("fecha", stringFecha, raiz, doc);
            LeerBinario_CrearXML.AddNodo("nombre", nombrePlato, raiz, doc);
            LeerBinario_CrearXML.AddNodo("lugar", lugar, raiz, doc);
            LeerBinario_CrearXML.AddNodo("precio", String.valueOf(precio), raiz, doc);
            LeerBinario_CrearXML.AddNodo("puntuacion", String.valueOf(calificacion), raiz, doc);

            LeerBinario_CrearXML.EscribirArchivo(doc, XML);
        } catch (TransformerException ex) {
            System.out.println(ex);
        } catch (SAXException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        }
    }
    
    /**
     * Método para modificar los datos de un nodo por id
     * @param doc Documento XML
     * @param idBuscar Id por el cual buscar el nodo a modificar
     * @param nuevoNombre Nuevo nombre a insertar
     * @param nuevoLugar Nuevo lugar a insertar
     * @param nuevoPrecio Nuevo precio a insertar
     * @param nuevaPunt Nueva puntuación a insertar
     * @param nuevaFecha Nueva fecha a insertar
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
     * Método para modificar los valores de un nodo, de un archivo xml existente a un archivo destino, buscado por el id
     * @param rutaOrigen Ruta del archivo a modificar
     * @param idBuscar Id por el cual buscar el nodo a modificar
     * @param nuevoNombre Nuevo nombre a insertar
     * @param nuevoLugar Nuevo lugar a insertar
     * @param nuevoPrecio Nuevo precio a insertar
     * @param nuevaPunt Nueva puntuación a insertar
     * @param nuevaFecha Nueva fecha a insertar
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
    
    /**
     * Elimina un nodo indicado por su Id
     * @param id Id del Nodo a borrar
     * @param ruta Ruta del XML
     * @param fichXML Nombre del XML
     */
    public static void eliminarId(String id, String ruta, String fichXML) {
        Document doc=ParseDocumentoXML(ruta+fichXML);
        NodeList lista = doc.getElementsByTagName("Plato");//obtenemos todos los platos
        Node nodo;
        NodeList hijos;
        Node hijo;
        boolean borrado=false;
        
        for (int i=0; i<lista.getLength() && !borrado; i++) {
            nodo = lista.item(i);//obtenemos el nodo actual
            hijos = nodo.getChildNodes();//obtenemos sus hijos
            
            //ahora, al igual que con el padre, buscamos en los hijos
            for (int j=0; j<hijos.getLength() && !borrado; j++) {
                hijo = hijos.item(j);
                if (hijo.getNodeType() == Node.ELEMENT_NODE &&  
                    hijo.getNodeName().equalsIgnoreCase("id") && 
                    hijo.getTextContent().equals(id)) {
                    
                    System.out.println(hijo.getNodeName());
                    System.out.println(hijo.getTextContent());
                    deleteNodo(nodo);
                    borrado=true;
                }
            }
        }
        
        try { 
            LeerBinario_CrearXML.EscribirArchivo(doc, ruta+fichXML);
        } catch (TransformerException ex) {
            System.out.println(ex);
        }
        
    }
    
     /**
     * Elimina el nodo indicado
     * @param nodo El nodo a borrar
     */
    public static void deleteNodo(Node nodo) {
        nodo.getParentNode().removeChild( nodo );
    }
    
}
