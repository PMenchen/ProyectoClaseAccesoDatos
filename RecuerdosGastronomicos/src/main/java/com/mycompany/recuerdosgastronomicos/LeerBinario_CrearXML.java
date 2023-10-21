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

/**
 *
 * @author stacy
 */
public class LeerBinario_CrearXML {
    static Document InitBuilder(String name) throws ParserConfigurationException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        
        Document d = implementation.createDocument(null, name, null);
        d.setXmlVersion("1.0");
        
        return d;
    }
    
    static Element CrearNodo(String value, Document document){
        Element raiz= document.createElement(value);
        document.getDocumentElement().appendChild(raiz);
        
        return raiz;
    }
    
    static void AddNodo(String datoPlato, String valor, Element raiz, Document document){

        Element elem = document.createElement(datoPlato); //creamos el hijo
        Text text = document.createTextNode(valor.trim()); //damos valor
        elem.appendChild(text); //pegamos el valor al elemento
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz

    }
    
     static void AddNodoFechaToNodo(String datoPlato, String valor1, String valor2, String valor3, Element raiz, Document document){
        Element elem = document.createElement(datoPlato); //creamos el hijo
        
        AddNodo("dia", valor1, elem, document);
        AddNodo("mes", valor2, elem, document);
        AddNodo("año", valor3, elem, document);
        
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        
    }
    
    static void TransformarBinToNodo(String ruta, Document d){
        String nombre; String lugar; double puntuacion; double precio; int id; int day; int month; int year; String stringFecha;
        
        try (RandomAccessFile fichero = new RandomAccessFile(ruta,"r")){
            byte[] b = new byte[40];
            while(fichero.getFilePointer() < fichero.length()){
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
                                
                System.out.println("Fecha [dia: " + String.valueOf(day).trim() + " mes: "  + String.valueOf(month).trim() + " año: " + String.valueOf(year).trim() + "]\tNombre: " + nombre.trim() + "\tLugar: " + lugar.trim() + "\tPrecio: " + String.valueOf(precio).trim() + "\tpuntuacion: " + String.valueOf(puntuacion).trim());
                
                AddNodo("id", String.valueOf(id), e, d);
                AddNodo("fecha", stringFecha, e, d);
                AddNodo("nombre", nombre, e, d);
                AddNodo("lugar", lugar, e, d);
                AddNodo("precio", String.valueOf(precio), e, d);
                AddNodo("puntuacion", String.valueOf(puntuacion), e, d);
            }
            fichero.close();
        }catch(IOException ex){
                System.out.println(ex.getMessage());
                System.out.println(ex.getCause());
                ex.printStackTrace();
                
        }
        
        
    }
    
    static void EscribirConsola(Document document) throws TransformerConfigurationException, TransformerException{
        Source source = new DOMSource(document);
        Result salida = new StreamResult(System.out);
        Transformer transformer=TransformerFactory.newInstance().newTransformer();
        transformer.transform(source,salida);
    }
    
    static void EscribirArchivo(Document document, String ruta) throws TransformerConfigurationException, TransformerException{
        Source source = new DOMSource(document);
        Result salida = new StreamResult(new File(ruta));
        Transformer transformer=TransformerFactory.newInstance().newTransformer();
        transformer.transform(source,salida);
    }
    
    
    
    public static void crearXML (String route, String file){
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
