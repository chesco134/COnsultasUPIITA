/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formidesktop;

/**
 *
 * @author jcapiz
 */
public class FormiDesktop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MainFrame mf = new MainFrame();
        if(args.length == 1)
            MainFrame.HOST = args[0];
        mf.setVisible(true);
    }
    
}
