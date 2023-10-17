/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionarficheros;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
/**
 *
 * @author b15-08m
 */
public class Menu extends JFrame{

    private JPanel jPanel=new JPanel();
    private JList jList;
    private Vector elem=new Vector();
    
    public Menu() {
        this.getContentPane().add(jPanel);
        elem.addElement("A");
        elem.addElement("B");
        elem.addElement("C");
        jList=new JList(elem);
        jPanel.add(jList);
        pack();
        initPantalla();
    }

    private void initPantalla() {

        setLayout(null); //Layout absoluto
        setTitle("Menu"); //Título del JFrame
        setSize(300, 200); //Dimensiones del JFrame
        setResizable(false); //No redimensionable
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar proceso al cerrar ventana
        setVisible(true); //Mostrar JFrame
    }


}
