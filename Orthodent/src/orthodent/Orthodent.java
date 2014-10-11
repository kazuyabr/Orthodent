/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent;

import de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel;

import javax.swing.UIManager;

/**
 *
 * @author Mary
 */
public class Orthodent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel()); // cambia la apariencia de la ventana
        }
        catch(Exception e){
        }
       
        JVentana ventana = new JVentana();
        new Login(ventana,true).setVisible(true);
    }
}
