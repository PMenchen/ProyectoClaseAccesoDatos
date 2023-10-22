/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.recuerdosgastronomicos;

import com.toedter.calendar.JDateChooser;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author stacy
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Constructor de Menu
     */
    public Menu() {
        initComponents();
        jListOpciones.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String elegido = jListOpciones.getSelectedValue();
                    cambiarContenido(elegido, false, 0);
                }
            }
        });
    }
    
    /**
     * Método que crea lo necesario al darle a la opcion Añadir
     *
     * @param labelAux una etiqueta auxiliar para indicar si el boton funcionó
     * @param editar boolean para indicar si lo llaman desde Editar o no
     * @param id indice para modificar un registro si el boolean es true
     */
    private void botonAnadir(JLabel labelAux, boolean editar, int id) {
        //PRIMERA FILA
        //FECHA
        JLabel labelFecha = crearLabel("Indique la fecha de la visita", 20, 20, 200, 30);
        jContenido.add(labelFecha);
        JDateChooser dateChooserFecha = new JDateChooser();
        dateChooserFecha.setBounds(20, 50, 200, 30);
        jContenido.add(dateChooserFecha);
        //LUGAR
        JLabel labelLugar = crearLabel("Indique el lugar dónde tuvo la experiencia", 300, 20, 300, 30);
        jContenido.add(labelLugar);
        JTextField textFieldLugar = new JTextField();
        textFieldLugar.setBounds(300, 50, 200, 30);
        jContenido.add(textFieldLugar);
        
        //SEGUNDA FILA
        //NOMBRE
        JLabel labelNombre = crearLabel("Indique el nombre el plato", 20, 100, 200, 30);
        jContenido.add(labelNombre);
        JTextField textFieldNombre = new JTextField();
        textFieldNombre.setBounds(20, 130, 200, 30);
        jContenido.add(textFieldNombre);
        //PRECIO
        JLabel labelPrecio = crearLabel("Indique el precio del plato", 300, 100, 300, 30);
        jContenido.add(labelPrecio);
        JTextField textFieldPrecio = new JTextField();
        textFieldPrecio.setBounds(300, 130, 100, 30);
        jContenido.add(textFieldPrecio);
        
        //TERCERA FILA
        //CALIFICACION
        JLabel labelCalificacion = crearLabel("Indique la calificación sobre 5 que le da al plato", 20, 180, 300, 30);
        jContenido.add(labelCalificacion);
        JTextField textFieldCalificacion = new JTextField();
        textFieldCalificacion.setBounds(20, 210, 50, 30);
        jContenido.add(textFieldCalificacion);

        
        //BOTON
        Button btnConfirmar = new Button("Confirmar");
        btnConfirmar.setBounds(300, 210, 100, 30);
        jContenido.add(btnConfirmar);

        if (editar) { //VIENE DE EDITAR
            btnConfirmar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (esNumero(textFieldPrecio.getText()) && esNumero(textFieldCalificacion.getText())
                            && Double.parseDouble(textFieldCalificacion.getText()) >= 0 && Double.parseDouble(textFieldCalificacion.getText()) <= 5
                            && dateChooserFecha.getDate() != null) {
                        labelAux.setText("Modificado con éxito");

                        double precio = Double.valueOf(textFieldPrecio.getText());
                        double calificacion = Double.valueOf(textFieldCalificacion.getText());
                        String nombrePlato = textFieldNombre.getText().replace(" ", "-");
                        String lugar = textFieldLugar.getText().replace(" ", "-");

                        //
                        Calendar calendar = Calendar.getInstance();
                        Date fechaSeleccionada = dateChooserFecha.getDate();
                        calendar.setTime(fechaSeleccionada);
                        
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        int month = calendar.get(Calendar.MONTH) + 1;
                        int year = calendar.get(Calendar.YEAR);
                        String stringFecha = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);

                        GestorArchivos.modificar(id, calendar, nombrePlato, lugar, precio, calificacion);
                        GestorArchivos.leerSecuencialBin(".\\resources\\", "Comidas.bin");
                        OperacionesXML.ModificarXML(".\\resources\\datos.xml", String.valueOf(id), nombrePlato, lugar, String.valueOf(precio), String.valueOf(calificacion), stringFecha, "datosModif.xml");
                    } else {
                        labelAux.setText("Precio, calificación o fecha incorrectos");
                    }
                }
            });

        } else {
            btnConfirmar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (esNumero(textFieldPrecio.getText()) && esNumero(textFieldCalificacion.getText())
                            && Double.parseDouble(textFieldCalificacion.getText()) >= 0 && Double.parseDouble(textFieldCalificacion.getText()) <= 5
                            && dateChooserFecha.getDate() != null) {
                        labelAux.setText("Se añadió con éxito");

                        double precio = Double.valueOf(textFieldPrecio.getText());
                        double calificacion = Double.valueOf(textFieldCalificacion.getText());
                        String nombrePlato = textFieldNombre.getText().replace(" ", "-");
                        String lugar = textFieldLugar.getText().replace(" ", "-");

                        //
                        Calendar calendar = Calendar.getInstance();
                        Date fechaSeleccionada = dateChooserFecha.getDate();
                        calendar.setTime(fechaSeleccionada);

                        GestorArchivos.crear(".\\resources\\", "Comidas.bin");
                        File fich = new File(".\\resources\\Comidas.bin");
                        if (!GestorArchivos.sobreescribirBorrado(calendar, lugar, nombrePlato, precio, calificacion)) {
                            GestorArchivos.escrituraSEC(fich, calendar, calificacion, lugar, precio, nombrePlato);
                        }
                        GestorArchivos.leerSecuencialBin(".\\resources\\", "Comidas.bin");
                    } else {
                        labelAux.setText("Precio, calificacion o fecha incorrectos");
                    }
                }
            });
        }
        
        //PREVISUALIZAR
        JLabel labelPrevisualizar = crearLabel("Presione para comprobar sus recuerdos", 300, 270, 300, 30);
        jContenido.add(labelPrevisualizar);
        Button btnPrevisualizar=new Button("Previsualizar");
        btnPrevisualizar.setBounds(300, 300, 100, 30);
        jContenido.add(btnPrevisualizar);
        
        btnPrevisualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LeerBinario_CrearXML.crearXML(".\\resources\\", "Comidas.bin");
                LeerXMLconSAX.leerConSAX(); //lectura con SAX
                GestorArchivos.abrirArchivo(".\\resources\\", "SAX.txt");
            }
        });
    }

    /**
     * Método que crea lo necesario al darle a la opcion Editar
     *
     * @param labelAux una etiqueta auxiliar para indicar si el boton funcionó
     */
    private void botonEditar(JLabel labelAux) {
        //PRIMERA FILA
        //FECHA
        JLabel labelFecha = crearLabel("Indique el año de la visita a modificar", 20, 20, 300, 30);
        jContenido.add(labelFecha);
        JTextField textFieldFecha = new JTextField();
        textFieldFecha.setBounds(20, 50, 200, 30);
        jContenido.add(textFieldFecha);
        
        //SEGUNDA FILA
        //REGISTROS RECUPERADOS
        JComboBox comboBoxFecha = new JComboBox();

        //BOTON CONECTAR
        Button btnConectar = new Button("Conectar");
        btnConectar.setBounds(300, 50, 200, 30);
        jContenido.add(btnConectar);

        comboBoxFecha.setBounds(20, 100, 400, 30);
        jContenido.add(comboBoxFecha);

        btnConectar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> info = GestorArchivos.recuperar(Integer.parseInt(textFieldFecha.getText()));
                comboBoxFecha.removeAllItems();

                for (String i : info) {
                    comboBoxFecha.addItem(i);
                }
            }
        });

        //BOTON EDITAR
        Button btnEditar = new Button("Editar");
        btnEditar.setBounds(300, 210, 100, 30);
        jContenido.add(btnEditar);

        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String info = (String) comboBoxFecha.getSelectedItem();
                String[] id = info.split(" ");
                cambiarContenido("Añadir", true, Integer.valueOf(id[0]));
                
            }
        });
    }

    /**
     * Método que crea lo necesario al darle a la opcion Eliminar
     *
     * @param labelAux una etiqueta auxiliar para indicar si el boton funcionó
     */
    private void botonEliminar(JLabel labelAux) {
        //PRIMERA FILA
        //FECHA
        JLabel labelFecha = crearLabel("Indique el año de la visita a eliminar", 20, 20, 300, 30);
        jContenido.add(labelFecha);
        JTextField textFieldFecha = new JTextField();
        textFieldFecha.setBounds(20, 50, 200, 30);
        jContenido.add(textFieldFecha);
        
        //SEGUNDA FILA
        //REGISTROS RECUPERADOS
        JComboBox comboBoxFecha = new JComboBox();

        //BOTON CONECTAR
        Button btnConectar = new Button("Conectar");
        btnConectar.setBounds(300, 50, 200, 30);
        jContenido.add(btnConectar);

        comboBoxFecha.setBounds(20, 100, 400, 30);
        jContenido.add(comboBoxFecha);

        btnConectar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> info = GestorArchivos.recuperar(Integer.parseInt(textFieldFecha.getText()));
                comboBoxFecha.removeAllItems();

                GestorArchivos.leerSecuencialBin(".\\resources\\", "Comidas.bin");
                GestorArchivos.escrituraDatosRecuperadosBin(info);
                //LeerBinario_CrearXML.crearXML(".\\recursos\\", "Registro_del_2023.bin");

                for (String i : info) {
                    comboBoxFecha.addItem(i);
                }
            }
        });

        //BOTON ELIMINAR
        Button btnEliminar = new Button("Eliminar");
        btnEliminar.setBounds(300, 210, 100, 30);
        jContenido.add(btnEliminar);

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String info = (String) comboBoxFecha.getSelectedItem();
                String[] id = info.split(" ");
                if (GestorArchivos.eliminarRA(Integer.valueOf(id[0]))) {
                    labelAux.setText("Borrado con éxito");
                    GestorArchivos.leerSecuencialBin(".\\resources\\", "Comidas.bin");//--prueba lee cada vez que se elimina
                    comboBoxFecha.removeAllItems();
                }
            }
        });
    }

    /**
     * Método que crea lo necesario al darle a la opcion Visualizar
     *
     * @param labelAux una etiqueta auxiliar para indicar si el boton funcionó
     */
    private void botonVisualizar(JLabel labelAux) {
        //PRIMERA FILA
        //FECHA
        JLabel labelFecha = crearLabel("Si desea visualizar un año en especifico introduzcalo y pulse el botón", 20, 20, 400, 30);
        jContenido.add(labelFecha);
        JTextField textFieldFecha = new JTextField();
        textFieldFecha.setBounds(20, 50, 200, 30);
        jContenido.add(textFieldFecha);
        
        //BOTON POR AÑO
        Button btnVisualizarFecha = new Button("Visualizar un año");
        btnVisualizarFecha.setBounds(300, 50, 200, 30);
        jContenido.add(btnVisualizarFecha);
        //SEGUNDA FILA
        //NOMBRE
        JLabel labelTodo = crearLabel("Si quiere visualizar todo pulse el siguiente botón", 20, 100, 300, 30);
        jContenido.add(labelTodo);
        
        //BOTON VISUALIZAR TODO
        Button btnVisualizar = new Button("Visualizar todo");
        btnVisualizar.setBounds(20, 130, 200, 30);
        jContenido.add(btnVisualizar);

        btnVisualizarFecha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (esNumero(textFieldFecha.getText())) {
                    labelAux.setText("Datos del año " + textFieldFecha.getText());
                    //abre archivo html (hay que especificar cual)

                    ArrayList<String> info = GestorArchivos.recuperar(Integer.parseInt(textFieldFecha.getText()));
                    String nombreBinFiltrado = "Registro_del_" + textFieldFecha.getText() + ".bin";
                    GestorArchivos.leerSecuencialBin(".\\resources\\", nombreBinFiltrado);
                    GestorArchivos.escrituraDatosRecuperadosBin(info);

                    LeerBinario_CrearXML.crearXML(".\\resources\\", nombreBinFiltrado);
                    XMLtoHTML.convert(".\\resources\\");
                    GestorArchivos.abrirArchivo(".\\resources\\", "index.html");
                }
            }
        });

        btnVisualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                labelAux.setText("Datos de todo lo almacenado");
                LeerBinario_CrearXML.crearXML(".\\resources\\", "Comidas.bin");
                XMLtoHTML.convert(".\\resources\\");
                GestorArchivos.abrirArchivo(".\\resources\\", "index.html");
            }
        });
    }

    /**
     * Método que gestiona la copia de seguridad, mover y eliminar el fichero
     *
     * @param labelAux una etiqueta auxiliar para indicar si el boton funcionó
     */
    private void botonArchivo(JLabel labelAux) {
        //PRIMERA FILA
        //ORIGEN
        JLabel labelOrigen = crearLabel("Pulse para borrar Recuerdos Gastronómicos", 20, 20, 400, 30);
        jContenido.add(labelOrigen);
        Button btnBorrar = new Button("Borrar recuerdos");
        btnBorrar.setBounds(20, 50, 200, 30);
        jContenido.add(btnBorrar);
        btnBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GestorArchivos.borrar(".\\resources\\");
                labelAux.setText("Borrado exitoso");
            }
        });

        //SEGUNDA FILA
        //DESTINO
        JLabel labelDestino = crearLabel("Seleccione la carpeta en la que acabará Recuerdos Gastronómicos", 20, 100, 400, 30);
        jContenido.add(labelDestino);
        Button btnDestino = new Button("Buscar");
        btnDestino.setBounds(20, 130, 200, 30);
        jContenido.add(btnDestino);
        JFileChooser destino = new JFileChooser();
        JTextField textFieldDestino = new JTextField();

        destino.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        btnDestino.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int val = destino.showOpenDialog(null);
                if (val == JFileChooser.APPROVE_OPTION) {
                    textFieldDestino.setText(destino.getSelectedFile().getAbsolutePath());
                }
            }
        });

        //TERCERA FILA
        Button btnCopiar = new Button("Copia de seguridad");
        btnCopiar.setBounds(20, 180, 200, 30);
        jContenido.add(btnCopiar);
        btnCopiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textFieldDestino.getText() == null) {
                    labelAux.setText("Indique primero el destino");
                } else {
                    GestorArchivos.copiar(".\\resources\\", textFieldDestino.getText());
                    labelAux.setText("Copiada de seguridad creada con éxito");
                }
            }
        });

        Button btnMover = new Button("Mover recuerdos");
        btnMover.setBounds(20, 210, 200, 30);
        jContenido.add(btnMover);
        btnMover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textFieldDestino.getText() == null) {
                    labelAux.setText("Indique primero el destino");
                } else {
                    GestorArchivos.mover(".\\resources\\", textFieldDestino.getText());
                    labelAux.setText("Se ha movido sin problemas");
                }
            }
        });
    }

    /**
     * Método que encripta y desencripta el fichero
     *
     * @param labelAux una etiqueta auxiliar para indicar si el boton funcionó
     */
    private void botonEncriptar(JLabel labelAux) {
        JLabel labelEncriptar = crearLabel("Pulse para encriptar el archivo con sus recuerdos", 20, 20, 400, 30);
        jContenido.add(labelEncriptar);
        
        Button btnEncriptar = new Button("Encriptar");
        btnEncriptar.setBounds(20, 50, 200, 30);
        jContenido.add(btnEncriptar);
        
        
        JLabel labelDesencriptar = crearLabel("Pulse para desencriptar el archivo con sus recuerdos", 20, 100, 400, 30);
        jContenido.add(labelDesencriptar);
        Button btnDesencriptar = new Button("Desencriptar");
        btnDesencriptar.setBounds(20, 130, 200, 30);
        jContenido.add(btnDesencriptar);
        
        btnEncriptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GestorArchivos.encriptar();
                labelAux.setText("Encriptado con éxito");
            }
        });
        
        btnDesencriptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GestorArchivos.desencriptar();
                labelAux.setText("Desencriptado con éxito");
            }
        });

        
    }
    
    /**
     * Método que comprueba si el parametro introducido es un Double
     *
     * @param texto numero a comprobar
     * @return true si sí es un double y false si no lo es
     */
    private static boolean esNumero(String texto) {
        try {
            Double.parseDouble(texto);
            return true;
        } catch (NumberFormatException nme) {
            return false;
        }
    }

    /**
     * Método para crear etiquetas
     *
     * @param texto texto que pondrá en la etiqueta
     * @param x posición x
     * @param y posición y
     * @param width ancho de la etiqueta
     * @param height alto de la etiqueta
     * @return la etiqueta creada
     */
    private JLabel crearLabel(String texto, int x, int y, int width, int height) {

        JLabel label = new JLabel(texto);
        label.setBounds(x, y, width, height);

        return label;
    }

    /**
     * Método que a través de un switch nos llevará a los métodos creados para
     * mostrar las diferentes opciones
     *
     * @param elegido que opción se debe crear
     * @param elegir boolean para comprobar si viene de Editar
     * @param id indice para modificar un registro si el boolean es true
     */
    private void cambiarContenido(String elegido, boolean elegir, int id) {
        jContenido.removeAll();
        JLabel labelAux = crearLabel("", 20, 300, 250, 30);
        jContenido.add(labelAux);

        switch (elegido) {
            case "Añadir":
                botonAnadir(labelAux, elegir, id);
                break;

            case "Editar":
                botonEditar(labelAux);
                break;

            case "Eliminar":
                botonEliminar(labelAux);
                break;

            case "Visualizar":
                botonVisualizar(labelAux);
                break;
            case "Archivo":
                botonArchivo(labelAux);
                break;
            default:
                botonEncriptar(labelAux);
                break;
        }

        jContenido.revalidate();
        jContenido.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListOpciones = new javax.swing.JList<>();
        jContenido = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Recuerdos Gastronómicos");

        jListOpciones.setBackground(new java.awt.Color(204, 204, 255));
        jListOpciones.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Añadir", "Editar", "Eliminar", "Visualizar", "Archivo", "Encriptar" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListOpciones);

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addContainerGap())
        );

        jContenido.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jContenidoLayout = new javax.swing.GroupLayout(jContenido);
        jContenido.setLayout(jContenidoLayout);
        jContenidoLayout.setHorizontalGroup(
            jContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );
        jContenidoLayout.setVerticalGroup(
            jContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jContenido;
    private javax.swing.JList<String> jListOpciones;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
