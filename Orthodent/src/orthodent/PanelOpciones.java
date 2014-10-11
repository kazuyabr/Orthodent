/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Mary
 */
public class PanelOpciones extends JPanel implements ActionListener{
    
    private JVentana ventana;
    private JButton agenda;
    private JButton pacientes;
    private JButton pagos;
    private JButton historial;
    private JButton usuarios;
    
    public PanelOpciones(JVentana ventana){
        super();
        this.ventana = ventana;
        this.setPreferredSize(new Dimension(1441,115));
        this.setBackground(new Color(9,133,179));
        
        initOpciones();
        
        this.agenda.addActionListener(this);
        this.pacientes.addActionListener(this);
        this.pagos.addActionListener(this);
        this.historial.addActionListener(this);
        this.usuarios.addActionListener(this);
    }
    
    public void initOpciones(){
        
        this.agenda = new JButton();
        this.pacientes = new JButton();
        this.pagos = new JButton();
        this.historial = new JButton();
        this.usuarios = new JButton();
        
        agenda.setForeground(new Color(255, 255, 255));
        agenda.setIcon(new ImageIcon("src/imagenes/pacientes.png"));
        agenda.setBorder(null);
        agenda.setBorderPainted(false);
        agenda.setContentAreaFilled(false);
        
        pacientes.setForeground(new Color(255, 255, 255));
        pacientes.setIcon(new ImageIcon("src/imagenes/pacientes.png"));
        pacientes.setBorder(null);
        pacientes.setBorderPainted(false);
        pacientes.setContentAreaFilled(false);
        
        pagos.setForeground(new Color(255, 255, 255));
        pagos.setIcon(new ImageIcon("src/imagenes/pagos.png"));
        pagos.setBorder(null);
        pagos.setBorderPainted(false);
        pagos.setContentAreaFilled(false);
        
        historial.setForeground(new Color(255, 255, 255));
        historial.setIcon(new ImageIcon("src/imagenes/historial.png"));
        historial.setBorder(null);
        historial.setBorderPainted(false);
        historial.setContentAreaFilled(false);
        
        usuarios.setForeground(new Color(255, 255, 255));
        usuarios.setIcon(new ImageIcon("src/imagenes/usuarios.png"));
        usuarios.setBorder(null);
        usuarios.setBorderPainted(false);
        usuarios.setContentAreaFilled(false);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*if (e.getSource() == this.agenda){
            ((JVentana)this.getTopLevelAncestor()).cambiarAgenda();
        }
        if (e.getSource() == this.pacientes){
            ((JVentana)this.getTopLevelAncestor()).cambiarPacientes();
        }
        if (e.getSource() == this.pagos){
            ((JVentana)this.getTopLevelAncestor()).cambiarPagos();
        }
        if (e.getSource() == this.usuarios){
            ((JVentana)this.getTopLevelAncestor()).cambiarUsuarios();
        }
        if (e.getSource() == this.historial){
            ((JVentana)this.getTopLevelAncestor()).cambiarHistorial();
        }*/
    }
    
}
