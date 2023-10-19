/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import com.toedter.calendar.JDateChooser;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private void botonAnadir(JLabel labelAux){
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
        Button btnAnadir = new Button("Añadir");
        btnAnadir.setBounds(300, 210, 100, 30);
        jContenido.add(btnAnadir);

        btnAnadir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (esNumero(textFieldPrecio.getText()) && esNumero(textFieldCalificacion.getText())
                        && Double.parseDouble(textFieldCalificacion.getText())>=0 && Double.parseDouble(textFieldCalificacion.getText())<=5) {
                    labelAux.setText("TODO NICE");
                    
                    double precio = Double.valueOf(textFieldPrecio.getText());
                    double calificacion = Double.valueOf(textFieldCalificacion.getText());
                    String nombrePlato = textFieldNombre.getText();
                    String lugar = textFieldLugar.getText();
                    
                    
                    
                    GestorArchivos.crear(".", "Comidas.bin");
                    File fich=new File("Comidas.dat");
                    GestorArchivos.escrituraSEC(fich, calificacion, lugar, precio, nombrePlato);
                } else {
                    labelAux.setText("Precio o calificación incorrecta");
                }
            }
        });
    }
    
    private void botonEditar(JLabel labelAux){
        //PRIMERA FILA
        //FECHA
        JLabel labelFecha = crearLabel("Indique la fecha de la visita a modificar", 20, 20, 300, 30);
        jContenido.add(labelFecha);
        JDateChooser dateChooserFecha = new JDateChooser();
        dateChooserFecha.setBounds(20, 50, 200, 30);
        jContenido.add(dateChooserFecha);

        //LUGAR
        JLabel labelLugar = crearLabel("Indique el lugar dónde tuvo la experiencia a modificar", 300, 20, 400, 30);
        jContenido.add(labelLugar);
        JTextField textFieldLugar = new JTextField();
        textFieldLugar.setBounds(300, 50, 200, 30);
        jContenido.add(textFieldLugar);
        //SEGUNDA FILA
        //NOMBRE
        JLabel labelNombre = crearLabel("Indique el nombre el plato a modificar", 20, 100, 300, 30);
        jContenido.add(labelNombre);
        JTextField textFieldNombre = new JTextField();
        textFieldNombre.setBounds(20, 130, 200, 30);
        jContenido.add(textFieldNombre);

        //BOTON
        Button btnEditar = new Button("Editar");
        btnEditar.setBounds(300, 210, 100, 30);
        jContenido.add(btnEditar);

        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarContenido("Añadir");
            }
        });
    }
    
    private void botonEliminar(JLabel labelAux){
        //PRIMERA FILA
        //FECHA
        JLabel labelFecha = crearLabel("Indique la fecha de la visita a eliminar", 20, 20, 300, 30);
        jContenido.add(labelFecha);
        JDateChooser dateChooserFecha = new JDateChooser();
        dateChooserFecha.setBounds(20, 50, 200, 30);
        jContenido.add(dateChooserFecha);

        //LUGAR
        JLabel labelLugar = crearLabel("Indique el lugar dónde tuvo la experiencia a eliminar", 300, 20, 400, 30);
        jContenido.add(labelLugar);
        JTextField textFieldLugar = new JTextField();
        textFieldLugar.setBounds(300, 50, 200, 30);
        jContenido.add(textFieldLugar);
        //SEGUNDA FILA
        //NOMBRE
        JLabel labelNombre = crearLabel("Indique el nombre el plato a eliminar", 20, 100, 300, 30);
        jContenido.add(labelNombre);
        JTextField textFieldNombre = new JTextField();
        textFieldNombre.setBounds(20, 130, 200, 30);
        jContenido.add(textFieldNombre);

        //BOTON
        Button btnEliminar = new Button("Eliminar");
        btnEliminar.setBounds(300, 130, 100, 30);
        jContenido.add(btnEliminar);

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                labelAux.setText("Borrado");
            }
        });
    }
    
    private void botonVisualizar(JLabel labelAux){
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
        jContenido.add(labelTodo);;
        //BOTON
        Button btnVisualizar = new Button("Visualizar todo");
        btnVisualizar.setBounds(20, 130, 200, 30);
        jContenido.add(btnVisualizar);

        btnVisualizarFecha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (esNumero(textFieldFecha.getText())){
                    labelAux.setText("Datos del año "+textFieldFecha.getText());
                }
            }
        });
        
        btnVisualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                labelAux.setText("Datos de todo lo almacenado");
            }
        });
    }
    
    private void botonCopia(JLabel labelAux){
        //PRIMERA FILA
        //ORIGEN
        JLabel labelOrigen = crearLabel("Seleccione el archivo del que desea hacer la copia de seguridad", 20, 20, 400, 30);
        jContenido.add(labelOrigen);
        Button btnOrigen = new Button("Archivo");
        btnOrigen.setBounds(20, 50, 200, 30);
        jContenido.add(btnOrigen);
        JFileChooser origen = new JFileChooser();
        JTextField textFieldOrigen = new JTextField();
        
        origen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        btnOrigen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int val=origen.showOpenDialog(null);
                if(val==JFileChooser.APPROVE_OPTION){
                    textFieldOrigen.setText(origen.getSelectedFile().getAbsolutePath());
                }
            }
        });
        
        //SEGUNDA FILA
        //DESTINO
        JLabel labelDestino = crearLabel("Seleccione la carpeta de la copia de seguridad", 20, 100, 300, 30);
        jContenido.add(labelDestino);
        Button btnDestino = new Button("Carpeta");
        btnDestino.setBounds(20, 130, 200, 30);
        jContenido.add(btnDestino);
        JFileChooser destino = new JFileChooser();
        JTextField textFieldDestino = new JTextField();
        
        destino.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        btnDestino.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int val=destino.showOpenDialog(null);
                if(val==JFileChooser.APPROVE_OPTION){
                    textFieldDestino.setText(destino.getSelectedFile().getAbsolutePath());
                }
            }
        });
        
        
        //TERCERA FILA
        Button btnCopiar = new Button("Copia de seguridad");
        btnCopiar.setBounds(20, 180, 300, 30);
        jContenido.add(btnCopiar);
        btnCopiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(textFieldOrigen.getText()==null && textFieldDestino.getText()==null){
                    labelAux.setText("Indique primero el archivo a copiar y el destino");
                }else{
                    System.out.println("OK");
                    CopiarFichero.copiar(textFieldOrigen.getText(), textFieldDestino.getText());
                }
            }
        });
        
        
    }
    
    private static boolean esNumero(String texto) {
        try {
            Double.parseDouble(texto);
            return true;
        } catch (NumberFormatException nme) {
            return false;
        }
    }

    private JLabel crearLabel(String texto, int x, int y, int width, int height) {

        JLabel label = new JLabel(texto);
        label.setBounds(x, y, width, height);

        return label;
    }

    private void cambiarContenido(String elegido) {
        jContenido.removeAll();
        JLabel labelAux = crearLabel("", 20, 250, 300, 30);
        jContenido.add(labelAux);

        switch (elegido) {
            case "Añadir":
                botonAnadir(labelAux);
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
            default:
                botonCopia(labelAux);
                break;
        }

        jContenido.revalidate();
        jContenido.repaint();
    }

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        jListOpciones.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String elegido = jListOpciones.getSelectedValue();
                    cambiarContenido(elegido);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelLogo = new javax.swing.JPanel();
        jPanelMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListOpciones = new javax.swing.JList<>();
        jContenido = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanelLogoLayout = new javax.swing.GroupLayout(jPanelLogo);
        jPanelLogo.setLayout(jPanelLogoLayout);
        jPanelLogoLayout.setHorizontalGroup(
            jPanelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelLogoLayout.setVerticalGroup(
            jPanelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 106, Short.MAX_VALUE)
        );

        jListOpciones.setBackground(new java.awt.Color(204, 204, 255));
        jListOpciones.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Añadir", "Editar", "Eliminar", "Visualizar", "Copia" };
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
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
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
            .addGap(0, 396, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                        .addComponent(jPanelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jContenido;
    private javax.swing.JList<String> jListOpciones;
    private javax.swing.JPanel jPanelLogo;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
