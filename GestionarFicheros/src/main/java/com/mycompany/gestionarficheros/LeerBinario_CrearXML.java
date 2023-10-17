/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionarficheros;

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
 * @author b15-21m
 */
public class LeerBinario_CrearXML {
    static Document InitBuilder(String name) throws ParserConfigurationException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();;
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
    
    static void AddNodo(String datoEmple, String valor, Element raiz, Document document){

        Element elem = document.createElement(datoEmple); //creamos el hijo
        Text text = document.createTextNode(valor.trim()); //damos valor
        elem.appendChild(text); //pegamos el valor al elemento
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz

    }
    
    static void TransformarBinToNodo(String ruta, Document d){
        int id; String nombre; String lugar; double puntuacion; double precio; //fecha
        try (RandomAccessFile fichero = new RandomAccessFile(ruta,"r")){
            byte[] b = new byte[20];
            while(fichero.getFilePointer() < fichero.length()){
                Element e = CrearNodo("Plato", d);
                
                id = fichero.readInt();
                
                fichero.read(b);
                nombre = new String(b, "UTF-16");
                lugar = new String(b, "UTF-16");
                puntuacion = fichero.readDouble();
                precio = fichero.readDouble();
                
                AddNodo("id", String.valueOf(id), e, d);
                AddNodo("nombre", nombre, e, d);
                AddNodo("lugar", lugar, e, d);
                AddNodo("puntuacion", String.valueOf(puntuacion), e, d);
                AddNodo("precio", String.valueOf(precio), e, d);
                
                
            }
        }catch(IOException ex){
                System.out.println("Error e/s");
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
    
    
    
    public static void main (String[] args){
        try {
            String origen = ".\\recursos\\comidas.dat";
            String ruta = ".\\recursos\\datos.xml";
            
            String value = "Platos";
            Document document = InitBuilder(value);
            
            
            TransformarBinToNodo(origen, document/*, nodo*/);
            
            EscribirArchivo(document, ruta);
            EscribirConsola(document);
            
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
