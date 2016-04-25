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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
        postInitComponents();
        //jPanel5.add(jlIcon2, BorderLayout.EAST);
        //jPanel6.add(instruccionesUpiita, BorderLayout.CENTER);
    }
    
    private void postInitComponents(){
        setBackground(java.awt.Color.white);
        Dimension d = new Dimension();
        d.setSize(jPanel8.getWidth(), 0.01);
        jPanel8.setSize(d);
        jPanel8.setBackground(Color.black);
        ImageIcon icon = new ImageIcon("imgs/upiita_gris1.png");
        Image img = icon.getImage();
        icon.setImage(img.getScaledInstance(-1, jPanel5.getHeight() - 15, Image.SCALE_DEFAULT));
        JLabel jlIcon = new JLabel(icon);
        ImageIcon icon2 = new ImageIcon("imgs/ipn_logo_blk.png");
        Image img2 = icon2.getImage();
        icon2.setImage(img2.getScaledInstance(-1, jPanel5.getHeight() - 15, Image.SCALE_DEFAULT));
        JLabel jlIcon2 = new JLabel(icon2);
        JLabel encabezadoUpiita = new JLabel("Unidad Profesional Interdisciplinaria en Ingenierías y Tecnologías Avanzadas");
        JTextArea instruccionesUpiita = new JTextArea("Consulta a la comunidad estudiantil sobre la demanda de unidades de aprendizaje para el próximo periodo escolar");
        instruccionesUpiita.setEditable(false);
        instruccionesUpiita.setLineWrap(true);
        instruccionesUpiita.setWrapStyleWord(true);
        try {
            Font mFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Roboto-Black.ttf"));
            encabezadoUpiita.setForeground(java.awt.Color.white);
            encabezadoUpiita.setFont(mFont.deriveFont(18f));
            instruccionesUpiita.setFont(mFont.deriveFont(18f));
        } catch (FontFormatException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        jPanel5.setBackground(java.awt.Color.decode("#850060"));
        jPanel5.add(jlIcon, BorderLayout.WEST);
        jPanel5.add(encabezadoUpiita, BorderLayout.CENTER);
    }

    public void listMaterias(String boleta) {
        remove(jPanel4);
        remove(jPanel3);
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
            headingPanel.setBackground(java.awt.Color.white);
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
            panelDeBotonAceptar.setBackground(java.awt.Color.white);
            listPanel.add(encabezado, BorderLayout.NORTH);
            listPanel.add(lContent, BorderLayout.WEST);
            listPanel.add(panelDeBotonAceptar, BorderLayout.EAST);
            listPanel.setBackground(java.awt.Color.white);
            JScrollPane sPane = new JScrollPane(listPanel);
            sPane.setBackground(java.awt.Color.white);
            sPane.getVerticalScrollBar().setUnitIncrement(64);
            setLayout(new BorderLayout(10, 7));
            add(sPane, BorderLayout.CENTER);
            repaint();
            revalidate();
            aceptar.addActionListener((ActionEvent evt) -> {
                MainFrame.this.remove(sPane);
                MainFrame.this.initComponents();
                postInitComponents();
                repaint();
                revalidate();
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

        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consulta de demanda de unidades de aprendizaje");
        setPreferredSize(new java.awt.Dimension(780, 600));
        setResizable(false);

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(java.awt.Color.white);
        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 10));
        jPanel5.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel5.setLayout(new java.awt.BorderLayout(20, 0));
        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(java.awt.Color.white);
        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel6.setLayout(new java.awt.BorderLayout());
        jPanel3.add(jPanel6, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBackground(java.awt.Color.white);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 5, 20));
        jPanel1.setForeground(java.awt.Color.white);
        jPanel1.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel1.setPreferredSize(new java.awt.Dimension(240, 138));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Número de boleta");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 10, 1));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(193, 55));
        jPanel7.add(jLabel1, java.awt.BorderLayout.NORTH);

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));
        jTextField1.setPreferredSize(new java.awt.Dimension(2, 28));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        jPanel7.add(jTextField1, java.awt.BorderLayout.CENTER);

        jPanel8.setPreferredSize(new java.awt.Dimension(10, 1));
        jPanel7.add(jPanel8, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jPanel4.add(jPanel1, new java.awt.GridBagConstraints());

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == '\n') {
            if (!"".equals(jTextField1.getText().trim())) {
                listMaterias(jTextField1.getText().trim());
            } else {
                //jLabel2.setText("Necesitamos una boleta válida");
            }
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
