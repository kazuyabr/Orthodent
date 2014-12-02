/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.bitacora;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author felipe
 */
public class Bitacoras extends JPanel
{ 
  
    public Bitacoras()
    {
         this.setBackground(new Color(243,242,243));
        this.setPreferredSize(new Dimension(1073, 561));
        
        JLabel mensaje = new JLabel("Historial... No disponible aún");
        mensaje.setFont(new Font("Georgia", 0, 11));
        this.add(mensaje);
    }
    
}
