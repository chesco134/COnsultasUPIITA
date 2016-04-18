/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formidesktop;

import formidesktop.database.DatabaseConnection;
import formidesktop.panels.ListContent;
import formidesktop.panels.RowList;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author jcapiz
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    public void listMaterias(String boleta) {
        remove(jPanel1);
        ListContent lContent = new ListContent();
        DatabaseConnection con = new DatabaseConnection();
        try {
            PreparedStatement pstmnt = 
                    con.getConnection()
                            .prepareStatement("select * from "
                    + "Alumno join Programa_Academico_Unidad_Aprendizaje "
                    + "using(idPrograma_Academico) where boleta like ?");
            pstmnt.setString(1, boleta);
            ResultSet rs = pstmnt.executeQuery();
            List<String> materias = new ArrayList<>();
            while (rs.next()) {
                materias.add(rs.getString("idUnidad_Aprendizaje"));
            }
            lContent.setLayout(new GridLayout(materias.size()+1, 1));
            Font myFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Roboto-Regular.ttf"));
            JLabel headingRecurseDeMateria = new JLabel("Pienso recursarla");
            headingRecurseDeMateria.setFont(myFont.deriveFont(14f).deriveFont(Font.BOLD));
            JLabel headingUnidadDeAprendizaje = new JLabel("Unidad de Aprendizaje");
            headingUnidadDeAprendizaje.setFont(myFont.deriveFont(14f).deriveFont(Font.BOLD));
            JPanel headingPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 12, 0));
            headingPanel.add(headingRecurseDeMateria);
            headingPanel.add(headingUnidadDeAprendizaje);
            headingPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 12, 0));
            System.out.println("Totolte " + headingRecurseDeMateria.getWidth());
            lContent.add(headingPanel);
            materias.stream().forEach((cardName) -> {
                RowList row = new RowList(boleta, cardName, myFont.deriveFont(14f), headingRecurseDeMateria.getWidth());
                lContent.add(row);
            });
            JPanel listPanel = new JPanel(new BorderLayout());
            listPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            JLabel encabezado = new JLabel("Unidad Profesional Interdisciplinaria en "
                    + "Ingenierías y Tecnologías Avanzadas");
            System.out.println("MyFont family: " + myFont.getFamily() +
                    ", with a size of: " + myFont.deriveFont(18f).getSize());
            encabezado.setFont(myFont.deriveFont(20f));
            encabezado.setBorder(BorderFactory.createEmptyBorder(5, 10, 2, 10));
            encabezado.setHorizontalAlignment(JLabel.CENTER);
            JButton aceptar = new JButton("Terminar");
            aceptar.setFont(myFont.deriveFont(14f));
            JPanel panelDeBotonAceptar = new JPanel();
            panelDeBotonAceptar.setLayout(new FlowLayout());
            panelDeBotonAceptar.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
            panelDeBotonAceptar.add(aceptar);
            listPanel.add(encabezado, BorderLayout.NORTH);
            listPanel.add(lContent, BorderLayout.WEST);
            listPanel.add(panelDeBotonAceptar, BorderLayout.EAST);
            JScrollPane sPane = new JScrollPane(listPanel);
            sPane.getVerticalScrollBar().setUnitIncrement(64);
            setLayout(new BorderLayout(10, 7));
            add(sPane, BorderLayout.CENTER);
            repaint();
            revalidate();
            aceptar.addActionListener((ActionEvent evt) -> {
                MainFrame.this.remove(sPane);
                MainFrame.this.initComponents();
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FontFormatException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.closeConnection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consulta de demanda de unidades de aprendizaje");
        setPreferredSize(new java.awt.Dimension(780, 600));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanel1.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel1.setPreferredSize(new java.awt.Dimension(240, 126));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(240, 186));
        jPanel2.setLayout(new java.awt.GridLayout(3, 0, 0, 10));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Enter control number");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 10, 1));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel1);

        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField1.setMargin(new java.awt.Insets(10, 0, 0, 0));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        jPanel2.add(jTextField1);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setToolTipText("");
        jPanel2.add(jLabel2);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == '\n') {
            if (!"".equals(jTextField1.getText().trim())) {
                listMaterias(jTextField1.getText().trim());
            } else {
                jLabel2.setText("Necesitamos una boleta válida");
            }
        }
    }//GEN-LAST:event_jTextField1KeyPressed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
