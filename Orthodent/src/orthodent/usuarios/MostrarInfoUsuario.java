/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.usuarios;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.Rol;
import modelo.Usuario;
import orthodent.JVentana;
import orthodent.db.Autenticacion;
import orthodent.db.RolDB;

/**
 * 
 * @author Mary
 */
public class MostrarInfoUsuario extends JPanel implements ActionListener{
    
    private JLabel nombreUsuario;
    private JButton userInfo;
    private JButton datosPersonales;
    private JButton datosProfesionales;
    private JButton horario;
    private Usuario usuario;
    
    public MostrarInfoUsuario(Usuario usuario){
        this.usuario = usuario;
        this.setBackground(new Color(243,242,243));
        this.setPreferredSize(new Dimension(1073, 561));
        
        this.setLayout(new BorderLayout());
        
        this.initComponents();
        this.addComponents();
    }
    
    private void initComponents(){
        
        this.nombreUsuario = new JLabel("Gonzalo Sotomayor");
        this.nombreUsuario.setFont(new Font("Georgia", 1, 14));
        this.nombreUsuario.setForeground(new Color(255, 255, 255));
        
        this.userInfo = new JButton();
        this.userInfo.setIcon(new ImageIcon("src/imagenes/userInfo.png"));
        this.userInfo.setBorder(null);
        this.userInfo.setBorderPainted(false);
        this.userInfo.setContentAreaFilled(false);
        
        this.datosPersonales = new JButton();
        this.datosPersonales.setIcon(new ImageIcon("src/imagenes/datosPersonalesInfoSelec.png"));
        this.datosPersonales.setBorder(null);
        this.datosPersonales.setBorderPainted(false);
        this.datosPersonales.setContentAreaFilled(false);
        this.datosPersonales.addActionListener(this);
        
        this.datosProfesionales = new JButton();
        this.datosProfesionales.setIcon(new ImageIcon("src/imagenes/datosProfesionalInfo.png"));
        this.datosProfesionales.setBorder(null);
        this.datosProfesionales.setBorderPainted(false);
        this.datosProfesionales.setContentAreaFilled(false);
        this.datosProfesionales.addActionListener(this);
        
        this.horario = new JButton();
        this.horario.setIcon(new ImageIcon("src/imagenes/horarioInfo.png"));
        this.horario.setBorder(null);
        this.horario.setBorderPainted(false);
        this.horario.setContentAreaFilled(false);
        this.horario.addActionListener(this);
    }
    
    private void addComponents(){
        
        JPanel panelIzq = panelLateral();
        this.add(panelIzq,BorderLayout.WEST);
        
        JPanel panelCentral = panelCentral();
        this.add(panelCentral,BorderLayout.CENTER);
    }
    
    private JPanel panelLateral(){
        
        JPanel panelIzq = new JPanel();
        panelIzq.setBackground(new Color(255,255,255));
        JPanel panel1 = this.addPanelConNombre();
        
        GroupLayout layout = new GroupLayout(panelIzq);
        panelIzq.setLayout(layout);
                
        GroupLayout.ParallelGroup horizontalGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        GroupLayout.SequentialGroup verticalGroup = layout.createSequentialGroup();
        
        horizontalGroup.addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        horizontalGroup.addComponent(this.datosPersonales);
        
        verticalGroup.addContainerGap();
        verticalGroup.addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        verticalGroup.addComponent(this.datosPersonales);
        
        if(this.usuario.getId_rol()==3){
            //Profesional
            horizontalGroup.addComponent(this.datosProfesionales);
            horizontalGroup.addComponent(this.horario);
            
            verticalGroup.addComponent(this.datosProfesionales);
            verticalGroup.addComponent(this.horario);
        }
        
        verticalGroup.addContainerGap(411, Short.MAX_VALUE);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(horizontalGroup)
                .addContainerGap())
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(verticalGroup)
        );
        
        return panelIzq;
    }
    
    private JPanel panelCentral(){
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(new Color(255, 255, 255));
        
        JPanel border = new JPanel();
        border.setBackground(new Color(255, 255, 255));
        border.setBorder(BorderFactory.createTitledBorder(""));
        border.setPreferredSize(new Dimension(824,572));
        
         
        
        
        
        
        GroupLayout layout = new GroupLayout(panelCentral);
        panelCentral.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(border, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(212, Short.MAX_VALUE))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(border, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        return panelCentral;
    }
    
    private JPanel addPanelConNombre(){
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(9, 133, 179));
        panel.setPreferredSize(new Dimension(222, 73));
        
        GroupLayout groupLayout = new GroupLayout(panel);
        panel.setLayout(groupLayout);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.userInfo)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.nombreUsuario)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(userInfo))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(this.nombreUsuario)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        
        return panel;
    }
    
    public void setIconButton(int value){
        
        this.datosPersonales.setIcon(new ImageIcon("src/imagenes/datosPersonalesInfo.png"));
        this.datosProfesionales.setIcon(new ImageIcon("src/imagenes/datosProfesionalInfo.png"));
        this.horario.setIcon(new ImageIcon("src/imagenes/horarioInfo.png"));
        
        switch(value){
            case 1:
                this.datosPersonales.setIcon(new ImageIcon("src/imagenes/datosPersonalesInfoSelec.png"));
                break;
            case 2:
                this.datosProfesionales.setIcon(new ImageIcon("src/imagenes/datosProfesionalInfoSelec.png"));
                break;
            case 3:
                this.horario.setIcon(new ImageIcon("src/imagenes/horarioInfoSelec.png"));
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == this.datosPersonales){
            this.setIconButton(1);
        }
        if(e.getSource() == this.datosProfesionales){
            this.setIconButton(2);
        }
        if(e.getSource() == this.horario){
            this.setIconButton(3);
        }
    }
}
