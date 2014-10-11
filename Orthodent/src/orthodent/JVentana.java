/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;

public class JVentana extends JFrame{
    
    private Image icono;

    public JVentana(){
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.setTitle("Orthodent");
        
        this.crearElementosVentana();
        
        this.defineVentana();
        this.setFocusable(true);
        //this.setResizable(false);
    }

    private void crearElementosVentana(){
        this.icono = new ImageIcon("imagenes/icono.png").getImage();
        this.setIconImage(icono);
    }
    
    private void defineVentana(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        super.setMinimumSize(new Dimension(screenSize.width-50, screenSize.height-50));
        this.setLocation((screenSize.width - this.getSize().width) / 2 ,
                (screenSize.height - this.getSize().height) / 2 - 20);
    }
}