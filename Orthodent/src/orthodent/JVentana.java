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
import modelo.Usuario;

public class JVentana extends JFrame{
    
    private Image icono;
    private Usuario usuario;//Usuario Actual Logeado!!
    private PanelOpciones panelOpciones;

    public JVentana(Usuario usuario){
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.setTitle("Orthodent");
        
        this.usuario = usuario;
        this.crearElementosVentana();
        
        this.defineVentana();
        this.setFocusable(true);
        //this.setResizable(false);
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }

    public void crearElementosVentana(){
        this.icono = new ImageIcon("src/imagenes/icono.png").getImage();
        this.setIconImage(icono);
        
        this.panelOpciones = new PanelOpciones(this);
        this.add(this.panelOpciones,BorderLayout.NORTH);
    }
    
    private void defineVentana(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        super.setMinimumSize(new Dimension(screenSize.width-50, screenSize.height-50));
        this.setLocation((screenSize.width - this.getSize().width) / 2 ,
                (screenSize.height - this.getSize().height) / 2 - 20);
    }
}