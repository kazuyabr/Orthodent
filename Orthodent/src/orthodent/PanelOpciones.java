/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import modelo.Rol;
import orthodent.db.RolDB;
import orthodent.pacientes.MostrarInfoPaciente;
import orthodent.usuarios.ConfigurarCuenta;
import orthodent.usuarios.DatosPersonales;
import orthodent.usuarios.MostrarInfoTratamiento;

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
    private JPopupMenu menuUsuario;
    private JMenuItem configurarCuenta;
    private JMenuItem cerrarSesion;
    private JMenuItem salir;
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
            this.setCursor();
        }
    }
    
    private void setCursor(){
        this.agenda.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.pacientes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.pagos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.historial.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.usuarios.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.miniUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.menuUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.configurarCuenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.cerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.salir.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
        
        this.agenda.setForeground(new Color(255, 255, 255));
        this.agenda.setBorder(null);
        this.agenda.setBorderPainted(false);
        this.agenda.setContentAreaFilled(false);
        
        this.pacientes.setForeground(new Color(255, 255, 255));
        this.pacientes.setBorder(null);
        this.pacientes.setBorderPainted(false);
        this.pacientes.setContentAreaFilled(false);
        
        this.pagos.setForeground(new Color(255, 255, 255));
        this.pagos.setBorder(null);
        this.pagos.setBorderPainted(false);
        this.pagos.setContentAreaFilled(false);
        
        this.historial.setForeground(new Color(255, 255, 255));
        this.historial.setBorder(null);
        this.historial.setBorderPainted(false);
        this.historial.setContentAreaFilled(false);
        
        this.usuarios.setForeground(new Color(255, 255, 255));
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
        
        this.miniUsuario.setFont(new java.awt.Font("Georgia", 1, 11));
        this.miniUsuario.setForeground(new Color(255, 255, 255));
        this.miniUsuario.setIcon(new ImageIcon("src/imagenes/user_mini2.png"));
        this.miniUsuario.setBorder(null);
        this.miniUsuario.setBorderPainted(false);
        this.miniUsuario.setContentAreaFilled(false);
        this.miniUsuario.setRolloverEnabled(false);
        this.miniUsuario.setFocusPainted(false);
        
        if(rol.getNombre().equals("ADMINISTRADOR")){
            this.miniUsuario.setText(ventana.getUsuario().getNombre());
        }
        else{
            this.miniUsuario.setText(ventana.getUsuario().getNombre()+" "+ventana.getUsuario().getApellido_pat());
        }
        
        this.addPopupMenuMiniUsuario();
        this.setIconButton(1);//1, para que deje "seleccionado" la agenda
    }
    
    public void setIconButton(int value){
        
        this.agenda.setIcon(new ImageIcon("src/imagenes/agenda.png"));
        this.pacientes.setIcon(new ImageIcon("src/imagenes/pacientes.png"));
        this.pagos.setIcon(new ImageIcon("src/imagenes/tratamientos.png"));
        this.usuarios.setIcon(new ImageIcon("src/imagenes/usuarios.png"));
        this.historial.setIcon(new ImageIcon("src/imagenes/historial.png"));
        
        switch(value){
            case 1:
                this.agenda.setIcon(new ImageIcon("src/imagenes/agendaSelec.png"));
                break;
            case 2:
                this.pacientes.setIcon(new ImageIcon("src/imagenes/pacientesSelec.png"));
                break;
            case 3:
                this.pagos.setIcon(new ImageIcon("src/imagenes/tratamientosSelec.png"));
                break;
            case 4:
                this.usuarios.setIcon(new ImageIcon("src/imagenes/usuariosSelec.png"));
                break;
            case 5:
                this.historial.setIcon(new ImageIcon("src/imagenes/historialSelec.png"));
                break;
        }
    }
    
    private void addPopupMenuMiniUsuario(){
        this.menuUsuario = new JPopupMenu("Menu Usuario");
        
        this.configurarCuenta = new JMenuItem("Configurar Cuenta");
        this.configurarCuenta.setFont(new Font("Georgia", 0, 11));
        //this.configurarCuenta.setMnemonic('C');
        //this.configurarCuenta.setAccelerator( KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK  ) );
        this.configurarCuenta.addActionListener(this);
        this.menuUsuario.add(this.configurarCuenta);
        
        this.cerrarSesion = new JMenuItem("Cerrar Sesión");
        this.cerrarSesion.setFont(new Font("Georgia", 0, 11));
        this.cerrarSesion.addActionListener(this);
        this.menuUsuario.add(this.cerrarSesion);
        
        this.salir = new JMenuItem("Salir");
        this.salir.setFont(new Font("Georgia", 0, 11));
        this.salir.addActionListener(this);
        this.menuUsuario.add(this.salir);
        
        this.miniUsuario.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //miniUsuario.getWidth()/2
                //miniUsuario.getX()-menuUsuario.getWidth()
                menuUsuario.show(miniUsuario, miniUsuario.getWidth()/2, miniUsuario.getHeight());
            }
        } );
    }
    
    private void addListeners(){
        this.agenda.addActionListener(this);
        this.pacientes.addActionListener(this);
        this.pagos.addActionListener(this);
        this.usuarios.addActionListener(this);
        this.historial.addActionListener(this);
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
        
        //Botones
        if (e.getSource() == this.agenda){
            try {
                int op = this.revisarCambios();
                ((JVentana)this.getTopLevelAncestor()).cambiarAgenda();
            } catch (Exception ex) {
            }
        }
        if (e.getSource() == this.pacientes){
            try {
                int op = this.revisarCambios();
                
                if(op!=4){
                    if(((JVentana)this.getTopLevelAncestor()).getPacientes()!=null){
                        boolean aux = ((JVentana)this.getTopLevelAncestor()).getPacientes().isIsListarPacientes();

                        if(aux){
                            ((JVentana)this.getTopLevelAncestor()).cambiarPacientes();
                        }
                        else{
                            MostrarInfoPaciente infoPaciente = ((JVentana)this.getTopLevelAncestor()).getPacientes().getInfoPaciente();
                            infoPaciente.volver();
                            ((JVentana)this.getTopLevelAncestor()).cambiarPacientes();
                        }
                    }
                    else{
                        ((JVentana)this.getTopLevelAncestor()).cambiarPacientes();
                    }
                }
                else{
                    boolean aux = ((JVentana)this.getTopLevelAncestor()).getPacientes().isIsListarPacientes();

                    if(aux){
                        ((JVentana)this.getTopLevelAncestor()).cambiarPacientes();
                    }
                    else{
                        MostrarInfoPaciente infoPaciente = ((JVentana)this.getTopLevelAncestor()).getPacientes().getInfoPaciente();
                        infoPaciente.volver();
                    }
                }
            } catch (Exception ex) {
            }
        }
        if (e.getSource() == this.pagos){
            try {
                int op = this.revisarCambios();
                ((JVentana)this.getTopLevelAncestor()).cambiarTratamientos();
            } catch (Exception ex) {
            }
        }
        if (e.getSource() == this.usuarios){
            try {
                int op = this.revisarCambios();
                
                if(op!=4){
                    if(((JVentana)this.getTopLevelAncestor()).getUsuarios()!=null){
                        boolean aux = ((JVentana)this.getTopLevelAncestor()).getUsuarios().isIsListarUsuarios();

                        if(aux){
                            ((JVentana)this.getTopLevelAncestor()).cambiarUsuarios();
                        }
                        else{
                            MostrarInfoTratamiento infoUsuario = ((JVentana)this.getTopLevelAncestor()).getUsuarios().getInfoUsuario();
                            infoUsuario.volver();
                            ((JVentana)this.getTopLevelAncestor()).cambiarUsuarios();
                        }
                    }
                    else{
                        ((JVentana)this.getTopLevelAncestor()).cambiarUsuarios();
                    }
                }
                else{
                    boolean aux = ((JVentana)this.getTopLevelAncestor()).getUsuarios().isIsListarUsuarios();

                    if(aux){
                        ((JVentana)this.getTopLevelAncestor()).cambiarUsuarios();
                    }
                    else{
                        MostrarInfoTratamiento infoUsuario = ((JVentana)this.getTopLevelAncestor()).getUsuarios().getInfoUsuario();
                        infoUsuario.volver();
                    }
                }
            } catch (Exception ex) {
            }
        }
        if (e.getSource() == this.historial){
            try {
                int op = this.revisarCambios();
                ((JVentana)this.getTopLevelAncestor()).cambiarHistorial();
            } catch (Exception ex) {
            }
        }
        
        //Opciones del mini Usuario
        if(e.getSource() == this.configurarCuenta){
            try {
                int op = this.revisarCambios();
                ((JVentana)this.getTopLevelAncestor()).cambiarConfigurarCuenta();
            } catch (Exception ex) {
            }
        }
        if(e.getSource() == this.cerrarSesion){
            try {
                int op = this.revisarCambios();
                ((JVentana)this.getTopLevelAncestor()).dispose();
                new Login(null,true).setVisible(true);
            } catch (Exception ex) {
            }
        }
        if(e.getSource() == this.salir){
            try {
                int op = this.revisarCambios();
                System.exit(0);
            } catch (Exception ex) {
            }
        }
    }
    
    private int revisarCambios() throws Exception{
        int opVentana = ((JVentana)this.getTopLevelAncestor()).getOpActual();
        
        if(opVentana==2){//Revisar cambios en pacientes
            boolean aux = ((JVentana)this.getTopLevelAncestor()).getPacientes().isIsListarPacientes();
            
            if(!aux){
                MostrarInfoPaciente infoPaciente = ((JVentana)this.getTopLevelAncestor()).getPacientes().getInfoPaciente();
                infoPaciente.guardarAntes();
            }
        }
        if(opVentana==4){//Revisar cambios en usuarios
            boolean aux = ((JVentana)this.getTopLevelAncestor()).getUsuarios().isIsListarUsuarios();

            if(!aux){
                MostrarInfoTratamiento infoUsuario = ((JVentana)this.getTopLevelAncestor()).getUsuarios().getInfoUsuario();
                infoUsuario.guardarAntes();
            }
        }
        
        return opVentana;
    }
}
