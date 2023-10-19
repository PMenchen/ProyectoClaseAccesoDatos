/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.recuerdosgastronomicos;

import java.io.File;

/**
 *
 * @author b15-19m
 */
public class RecuerdosGastronomicos {

    public static void main(String[] args) {
        
        String ruta = ".";
        String nombreFich = "Comidas.dat";
        
        GestorArchivos.borrar(ruta, nombreFich);
        GestorArchivos.crear(ruta, nombreFich);
        
        
        
        File fich=new File(nombreFich);
        
        
    }
}
