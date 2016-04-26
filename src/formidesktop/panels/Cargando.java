/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formidesktop.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author azaraf
 */
public class Cargando {

    private JPanel glass;
    private JFrame frame;
    private JLabel icon;
    private int x;

    public Cargando(JFrame frame) {
        this.frame = frame;
        x = 0;
    }

    public Cargando(JFrame frame, int x) {
        this.frame = frame;
        this.x = x;
    }

    private void crearGlassPanel() {
        try {
            glass = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
                    g.setColor(new Color(255, 255, 255, 230));
                    g.fillRect(x, 0, frame.getWidth(), frame.getHeight());
                }
            };
            glass.setOpaque(false);
            glass.setLayout(new GridBagLayout());
            icon = new javax.swing.JLabel();
            Random randi = new Random(new java.util.Date().getTime());
            int rand = (int) (11 * randi.nextDouble());
            System.out.println("El namber es> " + rand);
            switch (rand) {
                case 0:
                    icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("gifs/0.gif")));
                    break;
                case 1:
                    icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("gifs/1.gif")));
                    break;
                case 2:
                    icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("gifs/2.gif")));
                    break;
                case 3:
                    icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("gifs/3.gif")));
                    break;
                case 4:
                    icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("gifs/4.gif")));
                    break;
                case 5:
                    icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("gifs/5.gif")));
                    break;
                case 6:
                    icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("gifs/6.gif")));
                    break;
                case 7:
                    icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("gifs/7.gif")));
                    break;
                case 8:
                    icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("gifs/8.gif")));
                    break;
                case 9:
                    icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("gifs/9.gif")));
                    break;
                default:
                    icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("gifs/default.gif")));
                    break;
            }
            icon.setText("Espere...");
            Font mFont = Font.createFont(Font.TRUETYPE_FONT, RowList.class.getResourceAsStream("Roboto-Black.ttf"));
            icon.setFont(mFont.deriveFont(18f));
            icon.setForeground(new Color(55, 55, 55));
            icon.setVerticalTextPosition(JLabel.BOTTOM);
            icon.setHorizontalTextPosition(JLabel.CENTER);
            glass.add(icon);
            glass.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    e.consume(); //To change body of generated methods, choose Tools | Templates.
                    Toolkit.getDefaultToolkit().beep();
                }

            });
            frame.setGlassPane(glass);
        } catch (FontFormatException ex) {
            Logger.getLogger(Cargando.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cargando.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void bakeAzucarGlassPanel() {
        try {
            glass = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
                    g.setColor(new Color(255, 255, 255, 230));
                    g.fillRect(x, 0, frame.getWidth(), frame.getHeight());
                }
            };
            glass.setOpaque(false);
            glass.setLayout(new GridBagLayout());
            icon = new javax.swing.JLabel();
            icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("gifs/descontrolar.gif")));
            icon.setText("Esto se va a descontrolá!!");
            Font mFont = Font.createFont(Font.TRUETYPE_FONT, RowList.class.getResourceAsStream("Roboto-Black.ttf"));
            icon.setFont(mFont.deriveFont(18f));
            icon.setForeground(new Color(55, 55, 55));
            icon.setVerticalTextPosition(JLabel.BOTTOM);
            icon.setHorizontalTextPosition(JLabel.CENTER);
            glass.add(icon);
            glass.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    e.consume(); //To change body of generated methods, choose Tools | Templates.
                    Toolkit.getDefaultToolkit().beep();
                }

            });
            frame.setGlassPane(glass);
        } catch (FontFormatException ex) {
            Logger.getLogger(Cargando.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cargando.class.getName()).log(Level.SEVERE, null, ex);
        }
        glass.setVisible(true);
    }

    public void setLoadingPanel() {
        crearGlassPanel();
        glass.setVisible(true);
    }

    public void removeLoadingPanel() {
        glass.remove(icon);
        glass.setVisible(false);
    }

    public JPanel getGlass() {
        return glass;
    }

}
