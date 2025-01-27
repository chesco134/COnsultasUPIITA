/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formidesktop;

import formidesktop.database.acciones.AccionesTablaAlumnoCursaMateria;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author azaraf
 */
public class Trayectoria extends javax.swing.JFrame {

    
    private List<JFrame> listaDeVentanasTrayectoria;
    /**
     * Creates new form Trayectoria
     * @param carrera
     * @param listaDeVentanasTrayectoria
     */
    public Trayectoria(String carrera, List<JFrame> listaDeVentanasTrayectoria) {
        this.listaDeVentanasTrayectoria = listaDeVentanasTrayectoria;
        initComponents();
        jScrollPane1.getHorizontalScrollBar().setUnitIncrement(64);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(64);
        URL url = null;
        switch(carrera){
            case AccionesTablaAlumnoCursaMateria.BIO:
                 url = getClass().getResource("b.png");
                break;
            case AccionesTablaAlumnoCursaMateria.MEC:
                url = getClass().getResource("m.png");
                break;
            case AccionesTablaAlumnoCursaMateria.TEL:
                url = getClass().getResource("t.png");
                break;
        }
        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("Icon width: " + icon.getIconWidth());
        System.out.println("Screen width: " + screenSize.width);
        System.out.println("Difference between Screens: " + (icon.getIconWidth() - screenSize.width));
        icon.setImage(img.getScaledInstance(screenSize.width, -1, Image.SCALE_SMOOTH));
        carreraImg.setIcon(icon);
        setSize(screenSize);
    }
    
    @Override
    public void dispose(){
        listaDeVentanasTrayectoria.remove(this);
        System.out.println("tul (from dispose)");
        super.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        carreraImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(250, 248, 248));
        jPanel1.setLayout(new java.awt.GridLayout());

        carreraImg.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        carreraImg.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(carreraImg);

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public void iniciar() {
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
            java.util.logging.Logger.getLogger(Trayectoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Trayectoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Trayectoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Trayectoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel carreraImg;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
