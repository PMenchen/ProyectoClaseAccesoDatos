/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.recuerdosgastronomicos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author stacy
 */
public class LeerXMLconSAX {
    
    /*
    public static void main(String[] args) {
        leerConSAX(); //solo llamar a esta línea
    }
    */
    
    public static void leerConSAX() {
        File fich = new File(".\\resources\\SAX.txt");
        try (FileWriter ficheroOut = new FileWriter(fich);
            BufferedWriter escribir = new BufferedWriter(ficheroOut);){

        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser parser = parserFactory.newSAXParser();
        XMLReader procesadorXML = parser.getXMLReader();

        GestionContenido gestor = new GestionContenido(escribir);
        procesadorXML.setContentHandler(gestor);
        InputSource fileXML = new InputSource(".\\resources\\datos.xml");
        procesadorXML.parse(fileXML);

        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(LeerXMLconSAX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void escribirCadena(BufferedWriter escribir, String texto) {
        try {
            escribir.write(texto);
        } catch (IOException ioe) {
            System.out.println("ERROR E/S");
        }
    }
    //--------
    
    static class GestionContenido extends DefaultHandler {
        
        private BufferedWriter escribir;
        
        public GestionContenido(BufferedWriter escribir) {
            super();
            this.escribir = escribir;
        }
        
        @Override
        public void startDocument() {
            System.out.println("Comienzo documento");
            escribirCadena(escribir, "Comienzo documento\n");
        }
        
        @Override
        public void endDocument() {
            System.out.println("Final documento");
            escribirCadena(escribir, "Final documento\n");
        }
        
        @Override
        public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
            System.out.printf("Comienzo elemento: %s%n", nombreC);
            if (nombreC.equalsIgnoreCase("platos")) {
            }
            else if (nombreC.equalsIgnoreCase("plato")) {
                escribirCadena(escribir, "    ");
            } else {
                escribirCadena(escribir, "        ");
            }
            escribirCadena(escribir, "-Ini- "+nombreC + ": \n");
        }
        
        @Override
        public void endElement(String uri, String nombre, String nombreC) {
            if (nombreC.equalsIgnoreCase("platos")) {
            }
            else if (nombreC.equalsIgnoreCase("plato")) {
                escribirCadena(escribir, "    ");
            } else {
                escribirCadena(escribir, "        ");
            }
            escribirCadena(escribir, "-Fin- "+nombreC+"\n");
            System.out.printf("Fin elemento: %s%n", nombreC);
        }
        
        @Override
        public void characters(char[] ch, int inicio, int longitud) throws SAXException {
            String car = new String(ch, inicio, longitud);
            car = car.replaceAll("[\t\n]","");
            System.out.printf("\t%s%n", car);
            escribirCadena(escribir, "            "+car + "\n");
        }
    }
    
}
