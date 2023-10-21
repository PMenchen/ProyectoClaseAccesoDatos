/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.recuerdosgastronomicos;

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
 * @author stacy
 */
public class XMLtoHTML {
    public static void convert(String ruta){
        
        
        String estilo = ruta + "plantillaXLS.xsl";
        String datosPlatos = ruta + "datos.xml";
        
        File html = new File(ruta + "index.html");
        
        try {
            
            //crear fichero HTML
            FileOutputStream fos = new FileOutputStream(html);
            Source estilos = new StreamSource(estilo); //fuente XSL 
            Source datos = new StreamSource(datosPlatos); //fuente XML
            
            Result result = new StreamResult(fos);
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
            transformer.transform(datos, result); //Se obtiene el HTML
            
            fos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
                
    }
}
