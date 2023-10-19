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
        GestorArchivos.borrar(".", "Comidas.bin");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
}
