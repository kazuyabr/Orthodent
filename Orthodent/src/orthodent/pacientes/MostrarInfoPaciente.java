/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.pacientes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import modelo.Paciente;
import modelo.Usuario;

/**
 * 
 * @author Mary
 */
public class MostrarInfoPaciente extends JPanel implements ActionListener{
    
    private JLabel nombrePaciente;
    private JButton pacienteInfo;
    private JButton datosPersonales;
    private JButton clinico;
    private JButton planesTratamiento;
    private JButton presupuesto;
    private JButton fichasClinicas;
    private JButton facturacion;
    private JButton recaudacion;
    private JButton pagosRecibidos;
    private Paciente paciente;
    private Usuario actual;
    private int opActual;
    private DatosPersonales datosPersonalesPanel;
    private PlanesTratamiento planesTratamientoPanel;
    private Presupuestos presupuestoPanel;
    private FichasClinicas fichasClinicasPanel;
    private Recaudacion recaudacionPanel;
    private PagosRecibidos pagosRecibidosPanel;
    private JPanel contenedor;
    
    public MostrarInfoPaciente(Paciente paciente, Usuario actual){
        this.paciente = paciente;
        this.actual = actual;
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
        this.planesTratamiento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.presupuesto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.fichasClinicas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.recaudacion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.pagosRecibidos.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private void initComponents(){
        
        this.nombrePaciente = new JLabel();
        
        String nombre = this.paciente.getNombre();
        
        if(nombre.contains(" ")){
            nombre = nombre.substring(0,nombre.indexOf(" "));
        }
        
        this.nombrePaciente.setText(nombre+" "+this.paciente.getApellido_pat());
        this.nombrePaciente.setFont(new Font("Georgia", 1, 14));
        this.nombrePaciente.setForeground(new Color(255, 255, 255));
        
        this.pacienteInfo = new JButton();
        this.pacienteInfo.setIcon(new ImageIcon("src/imagenes/userInfo.png"));
        this.pacienteInfo.setBorder(null);
        this.pacienteInfo.setBorderPainted(false);
        this.pacienteInfo.setContentAreaFilled(false);
        
        this.datosPersonales = new JButton();
        this.datosPersonales.setIcon(new ImageIcon("src/imagenes/datosPersonalesInfoSelec.png"));
        this.datosPersonales.setBorder(null);
        this.datosPersonales.setBorderPainted(false);
        this.datosPersonales.setContentAreaFilled(false);
        this.datosPersonales.addActionListener(this);
        
        this.clinico = new JButton();
        this.clinico.setIcon(new ImageIcon("src/imagenes/clinico.png"));
        this.clinico.setBorder(null);
        this.clinico.setBorderPainted(false);
        this.clinico.setContentAreaFilled(false);
        
        this.planesTratamiento = new JButton();
        this.planesTratamiento.setIcon(new ImageIcon("src/imagenes/planesDeTratamiento.png"));
        this.planesTratamiento.setBorder(null);
        this.planesTratamiento.setBorderPainted(false);
        this.planesTratamiento.setContentAreaFilled(false);
        this.planesTratamiento.addActionListener(this);
        
        this.presupuesto = new JButton();
        this.presupuesto.setIcon(new ImageIcon("src/imagenes/presupuesto.png"));
        this.presupuesto.setBorder(null);
        this.presupuesto.setBorderPainted(false);
        this.presupuesto.setContentAreaFilled(false);
        this.presupuesto.addActionListener(this);
        
        this.fichasClinicas = new JButton();
        this.fichasClinicas.setIcon(new ImageIcon("src/imagenes/fichasClinicas.png"));
        this.fichasClinicas.setBorder(null);
        this.fichasClinicas.setBorderPainted(false);
        this.fichasClinicas.setContentAreaFilled(false);
        this.fichasClinicas.addActionListener(this);
        
        this.facturacion = new JButton();
        this.facturacion.setIcon(new ImageIcon("src/imagenes/facturacion.png"));
        this.facturacion.setBorder(null);
        this.facturacion.setBorderPainted(false);
        this.facturacion.setContentAreaFilled(false);
        
        this.recaudacion = new JButton();
        this.recaudacion.setIcon(new ImageIcon("src/imagenes/recaudacion.png"));
        this.recaudacion.setBorder(null);
        this.recaudacion.setBorderPainted(false);
        this.recaudacion.setContentAreaFilled(false);
        this.recaudacion.addActionListener(this);
        
        this.pagosRecibidos = new JButton();
        this.pagosRecibidos.setIcon(new ImageIcon("src/imagenes/pagosRecibidos.png"));
        this.pagosRecibidos.setBorder(null);
        this.pagosRecibidos.setBorderPainted(false);
        this.pagosRecibidos.setContentAreaFilled(false);
        this.pagosRecibidos.addActionListener(this);
        
        this.datosPersonalesPanel = new DatosPersonales(this.paciente);
        
        this.planesTratamientoPanel = null;
        this.presupuestoPanel = null;
        this.fichasClinicasPanel = null;
        this.recaudacionPanel = null;
        this.pagosRecibidosPanel = null;
        
        this.contenedor = new JPanel();
        this.contenedor.setBackground(new Color(255,255,255));
        this.contenedor.setLayout(new BorderLayout());
    }
    
    public void updateNombre(){
        String nombre = this.paciente.getNombre();
        
        if(nombre.contains(" ")){
            nombre = nombre.substring(0,nombre.indexOf(" "));
        }
        
        this.nombrePaciente.setText(nombre+" "+this.paciente.getApellido_pat());
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
            this.contenedor.remove(this.planesTratamientoPanel);
        }
        catch(Exception e){
        }
        
        try{
            this.contenedor.remove(this.presupuestoPanel);
        }
        catch(Exception e){
        }
        try{
            this.contenedor.remove(this.fichasClinicasPanel);
        }
        catch(Exception e){
        }
        try{
            this.contenedor.remove(this.recaudacionPanel);
        }
        catch(Exception e){
        }
        try{
            this.contenedor.remove(this.pagosRecibidosPanel);
        }
        catch(Exception e){
        }
    }
    
    public void cambiarDatosPersonales(){
        if(opActual!=1){
            this.removeAncestor();
            
            this.datosPersonalesPanel = new DatosPersonales(this.paciente);
            
            this.contenedor.add(this.datosPersonalesPanel,BorderLayout.CENTER);
            this.updateUI();
            this.opActual = 1;
        }
    }
    
    public void cambiarPlanesTratamiento() throws Exception{
        if(opActual!=2){
            this.removeAncestor();
            
            this.planesTratamientoPanel = new PlanesTratamiento(this.paciente, this.actual);
            
            this.contenedor.add(this.planesTratamientoPanel,BorderLayout.CENTER);
            this.updateUI();
            this.opActual = 2;
        }
    }
    
    public void cambiarPresupuesto() throws Exception{
        if(opActual!=3){
            this.removeAncestor();
            
            this.presupuestoPanel = new Presupuestos(this.paciente, this.actual);
            
            this.contenedor.add(this.presupuestoPanel,BorderLayout.CENTER);
            this.updateUI();
            this.opActual = 3;
        }
    }
    
    public void cambiarFichasClinicas(){
        if(opActual!=4){
            this.removeAncestor();
            
            this.fichasClinicasPanel = new FichasClinicas(this.paciente);
            
            this.contenedor.add(this.fichasClinicasPanel,BorderLayout.CENTER);
            this.updateUI();
            this.opActual = 4;
        }
    }
    
    public void cambiarRecaudacion(){
        if(opActual!=5){
            this.removeAncestor();
            
            this.recaudacionPanel = new Recaudacion(this.paciente);
            
            this.contenedor.add(this.recaudacionPanel,BorderLayout.CENTER);
            this.updateUI();
            this.opActual = 5;
        }
    }
    
    public void cambiarPagosRecibidos(){
        if(opActual!=6){
            this.removeAncestor();
            
            this.pagosRecibidosPanel = new PagosRecibidos(this.paciente);
            
            this.contenedor.add(this.pagosRecibidosPanel,BorderLayout.CENTER);
            this.updateUI();
            this.opActual = 6;
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
        horizontalGroup.addComponent(this.clinico);
        horizontalGroup.addComponent(this.planesTratamiento);
        horizontalGroup.addComponent(this.presupuesto);
        horizontalGroup.addComponent(this.fichasClinicas);
        horizontalGroup.addComponent(this.facturacion);
        horizontalGroup.addComponent(this.recaudacion);
        horizontalGroup.addComponent(this.pagosRecibidos);
        
        verticalGroup.addContainerGap();
        verticalGroup.addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        verticalGroup.addComponent(this.datosPersonales);
        verticalGroup.addComponent(this.clinico);
        verticalGroup.addComponent(this.planesTratamiento);
        verticalGroup.addComponent(this.presupuesto);
        verticalGroup.addComponent(this.fichasClinicas);
        verticalGroup.addComponent(this.facturacion);
        verticalGroup.addComponent(this.recaudacion);
        verticalGroup.addComponent(this.pagosRecibidos);
        
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
                .addComponent(this.pacienteInfo)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.nombrePaciente)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pacienteInfo))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(this.nombrePaciente)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        
        return panel;
    }
    
    public void setIconButton(int value){
        
        this.datosPersonales.setIcon(new ImageIcon("src/imagenes/datosPersonalesInfo.png"));
        this.planesTratamiento.setIcon(new ImageIcon("src/imagenes/planesDeTratamiento.png"));
        this.presupuesto.setIcon(new ImageIcon("src/imagenes/presupuesto.png"));
        this.fichasClinicas.setIcon(new ImageIcon("src/imagenes/fichasClinicas.png"));
        this.recaudacion.setIcon(new ImageIcon("src/imagenes/recaudacion.png"));
        this.pagosRecibidos.setIcon(new ImageIcon("src/imagenes/pagosRecibidos.png"));
        
        switch(value){
            case 1:
                this.datosPersonales.setIcon(new ImageIcon("src/imagenes/datosPersonalesInfoSelec.png"));
                break;
            case 2:
                this.planesTratamiento.setIcon(new ImageIcon("src/imagenes/planesDeTratamientoSelec.png"));
                break;
            case 3:
                this.presupuesto.setIcon(new ImageIcon("src/imagenes/presupuestoSelec.png"));
                break;
            case 4:
                this.fichasClinicas.setIcon(new ImageIcon("src/imagenes/fichasClinicasSelec.png"));
                break;
            case 5:
                this.recaudacion.setIcon(new ImageIcon("src/imagenes/recaudacionSelec.png"));
                break;
            case 6:
                this.pagosRecibidos.setIcon(new ImageIcon("src/imagenes/pagosRecibidosSelec.png"));
                break;
        }
    }
    
    public void guardarAntes(){
        if(this.opActual==1){
            //Datos Personales
            if(this.datosPersonalesPanel.getCambios()){
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
                    this.datosPersonalesPanel.guardar();
                }
            }
        }
        else if(this.opActual==2){
            //Planes de Tratamiento
            if(this.planesTratamientoPanel.getCambios()){
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
                    this.planesTratamientoPanel.guardar();
                }
            }
        }
        else if(this.opActual==3){
            //Sesiones
            if(this.presupuestoPanel.getCambios()){
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
                    this.presupuestoPanel.guardar();
                }
            }
        }
        else if(this.opActual==4){
            //Fichas Clinicas
            if(this.fichasClinicasPanel.getCambios()){
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
                    this.fichasClinicasPanel.guardar();
                }
            }
        }
        else if(this.opActual==5){
            //Recaudacion
            if(this.recaudacionPanel.getCambios()){
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
                    this.recaudacionPanel.guardar();
                }
            }
        }
        else{
            //Pagos Recibidos
            if(this.pagosRecibidosPanel.getCambios()){
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
                    this.pagosRecibidosPanel.guardar();
                }
            }
        }
    }
    
    public void volver(){
        
        try {
            ((Pacientes)this.getParent()).volverPacientes();
        } catch (Exception ex) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == this.datosPersonales){
            this.guardarAntes();
            this.setIconButton(1);
            this.cambiarDatosPersonales();
        }
        if(e.getSource() == this.planesTratamiento){
            this.guardarAntes();
            this.setIconButton(2);
            try {
                this.cambiarPlanesTratamiento();
            } catch (Exception ex) {
            }
        }
        if(e.getSource() == this.presupuesto){
            this.guardarAntes();
            this.setIconButton(3);
            try {
                this.cambiarPresupuesto();
            } catch (Exception ex) {
            }
        }
        if(e.getSource() == this.fichasClinicas){
            this.guardarAntes();
            this.setIconButton(4);
            this.cambiarFichasClinicas();
        }
        if(e.getSource() == this.recaudacion){
            this.guardarAntes();
            this.setIconButton(5);
            this.cambiarRecaudacion();
        }
        if(e.getSource() == this.pagosRecibidos){
            this.guardarAntes();
            this.setIconButton(6);
            this.cambiarPagosRecibidos();
        }
    }
}
