/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import modelo.Rol;
import orthodent.db.RolDB;

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
    private JButton diente1;
    private JButton diente2;
    private JButton titulo;
    private JButton miniUsuario;
    private JLabel nombreUsuario;
    private Rol rol;
    
    public PanelOpciones(JVentana ventana){
        super();
        this.ventana = ventana;
        this.setPreferredSize(new Dimension(1441,115));
        this.setBackground(new Color(9,133,179));
        
        int idRol = this.ventana.getUsuario().getId_rol();
        this.rol = RolDB.getRol(idRol);
        
        if(rol!=null){
            this.initOpciones();
            this.addListeners();
            this.addOpcionesPanel();
        }
    }
    
    private void initOpciones(){
        
        this.agenda = new JButton();
        this.pacientes = new JButton();
        this.pagos = new JButton();
        this.historial = new JButton();
        this.usuarios = new JButton();
        this.diente1 = new JButton();
        this.diente2 = new JButton();
        this.titulo = new JButton();
        this.miniUsuario = new JButton();
        
        if(rol.getNombre().equals("ADMINISTRADOR")){
            this.nombreUsuario = new JLabel(ventana.getUsuario().getNombre());
        }
        else{
            this.nombreUsuario = new JLabel(ventana.getUsuario().getNombre()+" "+ventana.getUsuario().getApellido_pat());
        }
        
        this.nombreUsuario.setForeground(new Color(255, 255, 255));
        this.nombreUsuario.setFont(new Font("Georgia", 1, 11));
        
        this.agenda.setForeground(new Color(255, 255, 255));
        this.agenda.setIcon(new ImageIcon("src/imagenes/pacientes.png"));
        this.agenda.setBorder(null);
        this.agenda.setBorderPainted(false);
        this.agenda.setContentAreaFilled(false);
        
        this.pacientes.setForeground(new Color(255, 255, 255));
        this.pacientes.setIcon(new ImageIcon("src/imagenes/pacientes.png"));
        this.pacientes.setBorder(null);
        this.pacientes.setBorderPainted(false);
        this.pacientes.setContentAreaFilled(false);
        
        this.pagos.setForeground(new Color(255, 255, 255));
        this.pagos.setIcon(new ImageIcon("src/imagenes/pagos.png"));
        this.pagos.setBorder(null);
        this.pagos.setBorderPainted(false);
        this.pagos.setContentAreaFilled(false);
        
        this.historial.setForeground(new Color(255, 255, 255));
        this.historial.setIcon(new ImageIcon("src/imagenes/historial.png"));
        this.historial.setBorder(null);
        this.historial.setBorderPainted(false);
        this.historial.setContentAreaFilled(false);
        
        this.usuarios.setForeground(new Color(255, 255, 255));
        this.usuarios.setIcon(new ImageIcon("src/imagenes/usuarios.png"));
        this.usuarios.setBorder(null);
        this.usuarios.setBorderPainted(false);
        this.usuarios.setContentAreaFilled(false);
        
        this.diente1.setForeground(new Color(255, 255, 255));
        this.diente1.setIcon(new ImageIcon("src/imagenes/diente_mini.png"));
        this.diente1.setBorder(null);
        this.diente1.setBorderPainted(false);
        this.diente1.setContentAreaFilled(false);
        
        this.diente2.setForeground(new Color(255, 255, 255));
        this.diente2.setIcon(new ImageIcon("src/imagenes/diente2_mini.png"));
        this.diente2.setBorder(null);
        this.diente2.setBorderPainted(false);
        this.diente2.setContentAreaFilled(false);
        
        this.titulo.setForeground(new Color(255, 255, 255));
        this.titulo.setIcon(new ImageIcon("src/imagenes/orthodent.png"));
        this.titulo.setBorder(null);
        this.titulo.setBorderPainted(false);
        this.titulo.setContentAreaFilled(false);
        
        this.miniUsuario.setForeground(new Color(255, 255, 255));
        this.miniUsuario.setIcon(new ImageIcon("src/imagenes/user_mini2.png"));
        this.miniUsuario.setBorder(null);
        this.miniUsuario.setBorderPainted(false);
        this.miniUsuario.setContentAreaFilled(false);
    }
    
    private void addListeners(){
        this.agenda.addActionListener(this);
        this.pacientes.addActionListener(this);
        this.pagos.addActionListener(this);
        this.historial.addActionListener(this);
        this.usuarios.addActionListener(this);
    }
    
    private void addOpcionesPanel(){
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        SequentialGroup horizontalGroup = layout.createSequentialGroup();
        ParallelGroup verticalGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        
        if(rol.getNombre().equals("ADMINISTRADOR")){
            horizontalGroup.addComponent(this.agenda);
            horizontalGroup.addGap(18,18,18);
            horizontalGroup.addComponent(this.pacientes);
            horizontalGroup.addGap(18, 18, 18);
            horizontalGroup.addComponent(this.pagos);
            horizontalGroup.addGap(18, 18, 18);
            horizontalGroup.addComponent(this.usuarios);
            horizontalGroup.addGap(18, 18, 18);
            horizontalGroup.addComponent(this.historial);
            
            verticalGroup.addComponent(this.agenda);
            verticalGroup.addComponent(this.pacientes);
            verticalGroup.addComponent(this.pagos);
            verticalGroup.addComponent(this.usuarios);
            verticalGroup.addComponent(this.historial);
        }
        else if(rol.getNombre().equals("ASISTENTE")){
            horizontalGroup.addComponent(this.agenda);
            horizontalGroup.addGap(18,18,18);
            horizontalGroup.addComponent(this.pacientes);
            horizontalGroup.addGap(18, 18, 18);
            horizontalGroup.addComponent(this.pagos);
            
            verticalGroup.addComponent(this.agenda);
            verticalGroup.addComponent(this.pacientes);
            verticalGroup.addComponent(this.pagos);
        }
        else{
            horizontalGroup.addComponent(this.agenda);
            horizontalGroup.addGap(18,18,18);
            horizontalGroup.addComponent(this.pacientes);
            horizontalGroup.addGap(18, 18, 18);
            horizontalGroup.addComponent(this.pagos);
            horizontalGroup.addGap(18, 18, 18);
            
            verticalGroup.addComponent(this.agenda);
            verticalGroup.addComponent(this.pacientes);
            verticalGroup.addComponent(this.pagos);
        }
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(109,109,109)
                .addComponent(this.diente1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(horizontalGroup)
                    .addComponent(this.titulo))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addComponent(this.diente2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.miniUsuario)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.nombreUsuario)
                .addContainerGap()
            )
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(this.titulo)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(verticalGroup))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(this.nombreUsuario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.miniUsuario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(this.diente2, GroupLayout.Alignment.TRAILING)
                    .addComponent(this.diente1, GroupLayout.Alignment.TRAILING)))
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.agenda){
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
        }
    }
    
}
