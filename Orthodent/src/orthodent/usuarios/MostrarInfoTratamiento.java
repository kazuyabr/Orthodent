/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.usuarios;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import modelo.Usuario;
import orthodent.JVentana;

/**
 * 
 * @author Mary
 */
public class MostrarInfoTratamiento extends JPanel implements ActionListener{
    
    private JLabel nombreUsuario;
    private JButton userInfo;
    private JButton datosPersonales;
    private JButton datosProfesionales;
    private JButton horario;
    private Usuario usuario;
    private Usuario usuarioActual;
    private int opActual;
    private JPanel datosPersonalesPanel;
    private DatosProfesional datosProfesionalPanel;
    private HorarioPanel horarioPanel;
    private JPanel contenedor;
    private boolean configurarCuenta;
    private boolean desdeUsuario;
    
    public MostrarInfoTratamiento(Usuario usuario, Usuario usuarioActual, boolean configurarCuenta, boolean desdeUsuario){
        this.usuario = usuario;
        this.usuarioActual = usuarioActual;
        this.configurarCuenta = configurarCuenta;
        this.desdeUsuario = desdeUsuario;
        this.setBackground(new Color(255,255,255));
        this.setPreferredSize(new Dimension(1073, 561));
        
        this.setLayout(new BorderLayout());
        
        this.initComponents();
        this.opActual = 1;
        this.addComponents();
        
        this.setCursor();
    }
    
    private void setCursor(){
        this.datosPersonales.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.datosProfesionales.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.horario.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public int getOpActual() {
        return opActual;
    }

    public void setOpActual(int opActual) {
        this.opActual = opActual;
    }

    public JPanel getDatosPersonalesPanel() {
        return datosPersonalesPanel;
    }

    public void setDatosPersonalesPanel(JPanel datosPersonalesPanel) {
        this.datosPersonalesPanel = datosPersonalesPanel;
    }

    public DatosProfesional getDatosProfesionalPanel() {
        return datosProfesionalPanel;
    }

    public void setDatosProfesionalPanel(DatosProfesional datosProfesionalPanel) {
        this.datosProfesionalPanel = datosProfesionalPanel;
    }

    public HorarioPanel getHorarioPanel() {
        return horarioPanel;
    }

    public void setHorarioPanel(HorarioPanel horarioPanel) {
        this.horarioPanel = horarioPanel;
    }
    
    private void initComponents(){
        
        String nombre = this.usuario.getNombre();
        
        if(nombre.contains(" ")){
            nombre = nombre.substring(0,nombre.indexOf(" "));
        }
        
        this.nombreUsuario = new JLabel();
        this.nombreUsuario.setText(nombre+" "+this.usuario.getApellido_pat());
        this.nombreUsuario.setFont(new Font("Georgia", 1, 14));
        this.nombreUsuario.setForeground(new Color(255, 255, 255));
        
        this.userInfo = new JButton();
        this.userInfo.setIcon(new ImageIcon(getClass().getResource("/imagenes/userInfo.png")));
        this.userInfo.setBorder(null);
        this.userInfo.setBorderPainted(false);
        this.userInfo.setContentAreaFilled(false);
        
        this.datosPersonales = new JButton();
        this.datosPersonales.setIcon(new ImageIcon(getClass().getResource("/imagenes/datosPersonalesInfoSelec.png")));
        this.datosPersonales.setBorder(null);
        this.datosPersonales.setBorderPainted(false);
        this.datosPersonales.setContentAreaFilled(false);
        this.datosPersonales.addActionListener(this);
        
        this.datosProfesionales = new JButton();
        this.datosProfesionales.setIcon(new ImageIcon(getClass().getResource("/imagenes/datosProfesionalInfo.png")));
        this.datosProfesionales.setBorder(null);
        this.datosProfesionales.setBorderPainted(false);
        this.datosProfesionales.setContentAreaFilled(false);
        this.datosProfesionales.addActionListener(this);
        
        this.horario = new JButton();
        this.horario.setIcon(new ImageIcon(getClass().getResource("/imagenes/horarioInfo.png")));
        this.horario.setBorder(null);
        this.horario.setBorderPainted(false);
        this.horario.setContentAreaFilled(false);
        this.horario.addActionListener(this);
        
        if(!this.configurarCuenta){
            this.datosPersonalesPanel = new DatosPersonales(this.usuario, this.usuarioActual);
        }
        else{
            this.datosPersonalesPanel = new ConfigurarCuenta(this.usuario, this.usuarioActual);
        }
        
        this.datosProfesionalPanel = null;
        this.horarioPanel = null;
        
        this.contenedor = new JPanel();
        this.contenedor.setBackground(new Color(255,255,255));
        this.contenedor.setLayout(new BorderLayout());
    }
    
    public void updateNombre(){
        String nombre = this.usuario.getNombre();
        
        if(nombre.contains(" ")){
            nombre = nombre.substring(0,nombre.indexOf(" "));
        }
        
        this.nombreUsuario.setText(nombre+" "+this.usuario.getApellido_pat());
        this.contenedor.updateUI();
    }
    
    private void addComponents(){
        
        JPanel panelIzq = panelLateral();
        this.contenedor.add(panelIzq,BorderLayout.WEST);
        this.contenedor.add(this.datosPersonalesPanel,BorderLayout.CENTER);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(this.contenedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(212, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(this.contenedor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
    }
    
    private void removeAncestor(){
        
        try{
            this.contenedor.remove(this.datosPersonalesPanel);
        }
        catch(Exception e){
        }
        
        try{
            this.contenedor.remove(this.datosProfesionalPanel);
        }
        catch(Exception e){
        }
        
        try{
            this.contenedor.remove(this.horarioPanel);
        }
        catch(Exception e){
        }
    }
    
    public void cambiarDatosPersonales(){
        if(opActual!=1){
            this.removeAncestor();
            
            this.datosPersonalesPanel = new DatosPersonales(this.usuario, this.usuarioActual);
            
            this.contenedor.add(this.datosPersonalesPanel,BorderLayout.CENTER);
            this.updateUI();
            this.opActual = 1;
        }
    }
    
    public void cambiarDatosProfesional(){
        if(opActual!=2){
            this.removeAncestor();
            
            this.datosProfesionalPanel = new DatosProfesional(this.usuario);
            
            this.contenedor.add(this.datosProfesionalPanel,BorderLayout.CENTER);
            this.updateUI();
            this.opActual = 2;
        }
    }
    
    public void cambiarHorario(){
        if(opActual!=3){
            this.removeAncestor();
            
            this.horarioPanel = new HorarioPanel(this.usuario);
            
            this.contenedor.add(this.horarioPanel,BorderLayout.CENTER);
            this.updateUI();
            this.opActual = 3;
        }
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
        
        this.datosPersonales.setIcon(new ImageIcon(getClass().getResource("/imagenes/datosPersonalesInfo.png")));
        this.datosProfesionales.setIcon(new ImageIcon(getClass().getResource("/imagenes/datosProfesionalInfo.png")));
        this.horario.setIcon(new ImageIcon(getClass().getResource("/imagenes/horarioInfo.png")));
        
        switch(value){
            case 1:
                this.datosPersonales.setIcon(new ImageIcon(getClass().getResource("/imagenes/datosPersonalesInfoSelec.png")));
                break;
            case 2:
                this.datosProfesionales.setIcon(new ImageIcon(getClass().getResource("/imagenes/datosProfesionalInfoSelec.png")));
                break;
            case 3:
                this.horario.setIcon(new ImageIcon(getClass().getResource("/imagenes/horarioInfoSelec.png")));
                break;
        }
    }
    
    public void guardarAntes(){
        if(this.opActual==1){
            //Datos Personales
            
            if(this.datosPersonalesPanel instanceof DatosPersonales){
                
                DatosPersonales panelDatosPersonales = (DatosPersonales) this.datosPersonalesPanel;
                
                if(panelDatosPersonales.getCambios()){
                    Object[] options = {"Sí","No"};

                    int n = JOptionPane.showOptionDialog(this,
                                "Hay cambios que no se han guardardo\n\n"+
                                "¿Desea guardar?",
                                "Orthodent",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);

                    if(n==0){
                        panelDatosPersonales.guardar();
                    }
                }
            }
            else{
                ConfigurarCuenta panelConfigurarCuenta = (ConfigurarCuenta) this.datosPersonalesPanel;
                
                if(panelConfigurarCuenta.getCambios()){
                    Object[] options = {"Sí","No"};

                    int n = JOptionPane.showOptionDialog(this,
                                "Hay cambios que no se han guardardo\n\n"+
                                "¿Desea guardar?",
                                "Orthodent",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);

                    if(n==0){
                        panelConfigurarCuenta.guardar();
                    }
                }
            }
        }
        else if(this.opActual==2){
            //Datos profesional
            if(this.datosProfesionalPanel.getCambios()){
                Object[] options = {"Sí","No"};
        
                int n = JOptionPane.showOptionDialog(this,
                            "Hay cambios que no se han guardardo\n\n"+
                            "¿Desea guardar?",
                            "Orthodent",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);

                if(n==0){
                    this.datosProfesionalPanel.guardar();
                }
            }
        }
        else{
            //Horario
            if(this.horarioPanel.getCambios()){
                Object[] options = {"Sí","No"};
        
                int n = JOptionPane.showOptionDialog(this,
                            "Hay cambios que no se han guardardo\n\n"+
                            "¿Desea guardar?",
                            "Orthodent",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);

                if(n==0){
                    this.horarioPanel.guardar();
                }
            }
        }
    }
    
    public void volver(){
        
        if(!this.configurarCuenta || this.desdeUsuario){
            try {
                ((Usuarios)this.getParent()).volverUsuarios();
            } catch (Exception ex) {
            }
        }
        else{
            JPanel panel1 = (JPanel) this.getParent();
            JPanel contenedor = (JPanel) panel1.getParent();
            
            ((JVentana)contenedor.getTopLevelAncestor()).cambiarAgenda();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == this.datosPersonales){
            this.guardarAntes();
            this.setIconButton(1);
            this.cambiarDatosPersonales();
        }
        if(e.getSource() == this.datosProfesionales){
            this.guardarAntes();
            this.setIconButton(2);
            this.cambiarDatosProfesional();
        }
        if(e.getSource() == this.horario){
            this.guardarAntes();
            this.setIconButton(3);
            this.cambiarHorario();
        }
    }
}
