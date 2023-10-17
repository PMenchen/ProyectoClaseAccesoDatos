/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionarficheros;

import java.io.File;
import java.io.FileOutputStream;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author b15-21m
 */
public class XMLtoHTML {
    public static void main(String[] args){
        
        /*String estilo = ".\\recursos\\alumnosPlantilla.xsl";
        String datosAlumnos = ".\\recursos\\alumnos.xml";*/
        
        String estilo = ".\\recursos\\plantillaXLS.xsl";
        String datosPlatos = ".\\recursos\\datos.xml";
        
        File html = new File(".\\recursos\\index.html");
        
        try {
            
            //crear fichero HTML
            FileOutputStream fos = new FileOutputStream(html);
            Source estilos = new StreamSource(estilo); //fuente XSL 
            Source datos = new StreamSource(datosPlatos); //fuente XML
            //Source datos = new StreamSource(datosAlumnos); //fuente XML
            
            Result result = new StreamResult(fos);
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
            transformer.transform(datos, result); //Se obtiene el HTML
            
            fos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
                
    }
}
