/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordkeeper;

import Controlador.ManejaClave;
import Controlador.ManejaUsuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author usuario
 */
public class PasswordKeeper {

    private static ManejaClave mc = new ManejaClave();
    private static ManejaUsuario mu = new ManejaUsuario();
    private static Login lg = new Login();
    private static MainView view = new MainView();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        lg.dispose();
        lg.setUndecorated(true); // Remove title bar
        lg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lg.pack();

        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(PasswordKeeper.class.getName()).log(Level.SEVERE, null, ex);
        }
        lg.setVisible(true);

    }

    public static ManejaClave getMc() {
        return mc;
    }

    public static ManejaUsuario getMu() {
        return mu;
    }

    public static void cambiavista(int vista) {
        if (vista == 0) {
            view.setVisible(false);
            lg.dispose();
            lg.setUndecorated(true); // Remove title bar
            lg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            lg.pack();
            lg.setVisible(true);
        }
        if (vista == 1) {
            lg.setVisible(false);
            view.dispose();
            view.setUndecorated(true); // Remove title bar
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            view.pack();
            view.setVisible(true);
        } else {

        }

    }

}
