/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.recuerdosgastronomicos;

/**
 *
 * @author b15-19m
 */
public class RecuerdosGastronomicos {

    public static void main(String[] args) {
        
        String ruta = ".";
        String nombreFich = "Comidas.dat";
        
        GestorArchivos.crear(ruta, nombreFich);
        System.out.println("creado");
    }
}
