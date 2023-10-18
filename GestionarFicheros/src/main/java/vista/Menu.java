/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import com.toedter.calendar.JDateChooser;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author stacy
 */
public class Menu extends javax.swing.JFrame {

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
        JLabel label1 = crearLabel("", 20, 250, 200, 30);
        jContenido.add(label1);

        switch (elegido) {
            case "Añadir":

            case "Editar":
                //PRIMERA FILA
                //FECHA
                JLabel labelFecha = crearLabel("Indique la fecha de su visita", 20, 20, 200, 30);
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
                Button btnVisualizar = new Button("Visualizar");
                btnVisualizar.setBounds(300, 210, 100, 30);
                jContenido.add(btnVisualizar);

                btnVisualizar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (esNumero(textFieldPrecio.getText()) && esNumero(textFieldCalificacion.getText())
                                && Double.parseDouble(textFieldCalificacion.getText())>=0 && Double.parseDouble(textFieldCalificacion.getText())<=5) {
                            label1.setText("TODO NICE");
                        } else {
                            label1.setText("Precio o calificación incorrecta");
                        }
                    }
                });
                break;

            case "Eliminar":
                //label1.setText("El");
                break;

            default:
                //label1.setText("V");
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
            String[] strings = { "Añadir", "Editar", "Eliminar", "Visualizar" };
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
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
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
