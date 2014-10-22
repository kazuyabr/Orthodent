/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.pagos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Mary
 */
public class Pagos extends JPanel{
    
    public Pagos(){
        //Introducir código aquí
        this.setBackground(new Color(243,242,243));
        this.setPreferredSize(new Dimension(1073, 561));
        
        JLabel mensaje = new JLabel("Pagos... No disponible aún");
        mensaje.setFont(new Font("Georgia", 0, 11));
        this.add(mensaje);
    }
    
}
