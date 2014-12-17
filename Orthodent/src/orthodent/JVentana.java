/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent;

import orthodent.tratamientos.Tratamientos;
import orthodent.agenda.Agenda;
import orthodent.pacientes.Pacientes;
import orthodent.usuarios.Usuarios;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import modelo.Usuario;
import orthodent.pagos.Pagos;
import orthodent.usuarios.MostrarInfoTratamiento;

public class JVentana extends JFrame{
    
    private Image icono;
    private Usuario usuario;//Usuario Actual Logeado!!
    private PanelOpciones panelOpciones;
    private JPanel contenedorAgenda;
    private JPanel contenedorPacientes;
    private JPanel contenedorPagos;
    private JPanel contenedorTratamiento;
    private JPanel contenedorUsuarios;
    private JPanel contenedorConfigurarCuenta;
    private Agenda agenda;
    private Pacientes pacientes;
    private Pagos pagos;
    private Tratamientos tratamientos;
    private Usuarios usuarios;
    private MostrarInfoTratamiento configurarCuenta;
    
    private int opActual;

    public JVentana(Usuario usuario){
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.setTitle("Orthodent");
        
        this.usuario = usuario;
        this.opActual = 1;
        
        this.crearElementosVentana();
        
        this.centrarVentana();
        this.setFocusable(true);
        //this.setResizable(false);
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }
    
    private void centrarVentana(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        super.setMinimumSize(new Dimension(screenSize.width-50, screenSize.height-50));
        this.setLocation((screenSize.width - this.getSize().width) / 2 ,
                (screenSize.height - this.getSize().height) / 2 - 20);
    }

    public void crearElementosVentana(){
        this.icono = new ImageIcon("src/imagenes/icono.png").getImage();
        this.setIconImage(icono);
        
        this.panelOpciones = new PanelOpciones(this);
        this.add(this.panelOpciones,BorderLayout.NORTH);
        
        this.contenedorAgenda = new JPanel();
        this.contenedorPacientes = new JPanel();
        this.contenedorTratamiento = new JPanel();
        this.contenedorUsuarios = new JPanel();
        this.contenedorPagos = new JPanel();
        this.contenedorConfigurarCuenta = new JPanel();
        
        this.crearAgenda();
        this.add(this.contenedorAgenda,BorderLayout.CENTER);
        this.agenda.iniciado = true;
        this.pacientes = null;
        this.tratamientos = null;
        this.usuarios = null;
        this.pagos = null;
        this.configurarCuenta = null;
    }
    
    private void crearAgenda(){
        this.contenedorAgenda.setLayout(new BorderLayout());
        this.agenda = new Agenda(this.usuario);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(this.agenda,BorderLayout.CENTER);
        
        JPanel borde1 = new JPanel();
        borde1.setBackground(Color.white);
        borde1.setPreferredSize(new Dimension(130, 488));
        JPanel borde2 = new JPanel();
        borde2.setBackground(Color.white);
        borde2.setPreferredSize(new Dimension(130, 488));
        
        panel.add(borde1,BorderLayout.WEST);
        panel.add(borde2,BorderLayout.EAST);
        //panel.setSize(1043, 1200);
        
        this.contenedorAgenda.add(panel,BorderLayout.NORTH);
        
    }
    
    private void crearPacientes() throws Exception{
        this.contenedorPacientes.setLayout(new BorderLayout());
        this.pacientes = new Pacientes(this.usuario);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(this.pacientes,BorderLayout.CENTER);
        
        JPanel borde1 = new JPanel();
        borde1.setBackground(Color.white);
        borde1.setPreferredSize(new Dimension(130, 488));
        JPanel borde2 = new JPanel();
        borde2.setBackground(Color.white);
        borde2.setPreferredSize(new Dimension(130, 488));
        
        panel.add(borde1,BorderLayout.WEST);
        panel.add(borde2,BorderLayout.EAST);
        
        this.contenedorPacientes.add(panel,BorderLayout.NORTH);
    }
    
    private void crearPagos() throws Exception{
        this.contenedorPagos.setLayout(new BorderLayout());
        this.pagos = new Pagos(this.usuario);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(this.pagos,BorderLayout.CENTER);
        
        JPanel borde1 = new JPanel();
        borde1.setBackground(Color.white);
        borde1.setPreferredSize(new Dimension(130, 488));
        JPanel borde2 = new JPanel();
        borde2.setBackground(Color.white);
        borde2.setPreferredSize(new Dimension(130, 488));
        
        panel.add(borde1,BorderLayout.WEST);
        panel.add(borde2,BorderLayout.EAST);
        
        this.contenedorPagos.add(panel,BorderLayout.NORTH);
    }
    
    private void crearTratamientos(){
        this.contenedorTratamiento.setLayout(new BorderLayout());
        this.tratamientos = new Tratamientos();
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(this.tratamientos,BorderLayout.CENTER);
        
        JPanel borde1 = new JPanel();
        borde1.setBackground(Color.white);
        borde1.setPreferredSize(new Dimension(130, 488));
        JPanel borde2 = new JPanel();
        borde2.setBackground(Color.white);
        borde2.setPreferredSize(new Dimension(130, 488));
        
        panel.add(borde1,BorderLayout.WEST);
        panel.add(borde2,BorderLayout.EAST);
        
        this.contenedorTratamiento.add(panel,BorderLayout.NORTH);
    }
    
    private void crearUsuarios() throws Exception{
        this.contenedorUsuarios.setLayout(new BorderLayout());
        this.usuarios = new Usuarios(usuario);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(this.usuarios,BorderLayout.CENTER);
        
        JPanel borde1 = new JPanel();
        borde1.setBackground(Color.white);
        borde1.setPreferredSize(new Dimension(130, 488));
        JPanel borde2 = new JPanel();
        borde2.setBackground(Color.white);
        borde2.setPreferredSize(new Dimension(130, 488));
        
        panel.add(borde1,BorderLayout.WEST);
        panel.add(borde2,BorderLayout.EAST);
        
        this.contenedorUsuarios.add(panel,BorderLayout.NORTH);
    }
    
    private void crearConfigurarCuenta(){
        this.contenedorConfigurarCuenta.setLayout(new BorderLayout());
        this.configurarCuenta = new MostrarInfoTratamiento(this.usuario, this.usuario, true, false);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(this.configurarCuenta,BorderLayout.CENTER);
        
        JPanel borde1 = new JPanel();
        borde1.setBackground(Color.white);
        borde1.setPreferredSize(new Dimension(130, 488));
        JPanel borde2 = new JPanel();
        borde2.setBackground(Color.white);
        borde2.setPreferredSize(new Dimension(130, 488));
        
        panel.add(borde1,BorderLayout.WEST);
        panel.add(borde2,BorderLayout.EAST);
        
        this.contenedorConfigurarCuenta.add(panel,BorderLayout.NORTH);
    }
    
    private void removeAncestor(){
        
        try{
            this.remove(this.contenedorAgenda);
        }
        catch(Exception e){
        }
        
        try{
            this.remove(this.contenedorPacientes);
        }
        catch(Exception e){
        }
        
        try{
            this.remove(this.contenedorPagos);
        }
        catch(Exception e){
        }
        
        try{
            this.remove(this.contenedorTratamiento);
        }
        catch(Exception e){
        }
        
        try{
            this.remove(this.contenedorUsuarios);
        }
        catch(Exception e){
        }
        
        try{
            this.remove(this.contenedorConfigurarCuenta);
        }
        catch(Exception e){
        }
    }
    
    public void cambiarAgenda(){
        if(opActual!=1){
            this.removeAncestor();
            this.panelOpciones.setIconButton(1);
            
            this.add(this.contenedorAgenda,BorderLayout.CENTER);
            this.contenedorAgenda.updateUI();
            this.opActual = 1;
        }
    }
    
    public void cambiarPacientes() throws Exception{
        if(opActual!=2){
            this.removeAncestor();
            this.panelOpciones.setIconButton(2);
            
            if(this.pacientes==null){
                this.crearPacientes();
            }
            
            this.add(this.contenedorPacientes,BorderLayout.CENTER);
            this.getPacientes().updateModelo();
            this.contenedorPacientes.updateUI();            
            this.opActual = 2;
        }
    }
    
    public void cambiarPagos() throws Exception{
        if(opActual!=3){
            this.removeAncestor();
            this.panelOpciones.setIconButton(3);
            
            if(this.pagos==null){
                this.crearPagos();
            }
            this.pagos.updateFilter();
            this.add(this.contenedorPagos,BorderLayout.CENTER);
            this.contenedorPagos.updateUI();
            this.opActual = 3;
        }
    }
    
    public void cambiarTratamientos(){
        if(opActual!=4){
            this.removeAncestor();
            this.panelOpciones.setIconButton(4);
            
            if(this.tratamientos==null){
                this.crearTratamientos();
            }
            this.add(this.contenedorTratamiento,BorderLayout.CENTER);
            this.getTratamientos().getTablas().updateTablas();
            this.contenedorTratamiento.updateUI();
            //this.getTratamientos().updateModelo(); //actualiza el modelo cuando se cambia de pestañas
            this.opActual = 4;
        }
    }
    
    public void cambiarUsuarios() throws Exception{
        if(opActual!=5){
            this.removeAncestor();
            this.panelOpciones.setIconButton(5);
            
            if(this.usuarios==null){
                this.crearUsuarios();
            }
            
            this.add(this.contenedorUsuarios,BorderLayout.CENTER);
            this.getUsuarios().updateModelo(); 
            this.getUsuarios().updateModeloClinica();
            this.contenedorUsuarios.updateUI();
            
            this.opActual = 5;
        }
    }
    
    public void cambiarConfigurarCuenta(){
        if(opActual!=6){
            this.removeAncestor();
            this.panelOpciones.setIconButton(6);
            
            if(this.configurarCuenta==null){
                this.crearConfigurarCuenta();
            }
            
            this.add(this.contenedorConfigurarCuenta,BorderLayout.CENTER);
            this.contenedorConfigurarCuenta.updateUI();
            this.opActual = 6;
        }
    }
    
    public Agenda getAgenda(){
        return this.agenda;
    }
    
    public Pacientes getPacientes(){
        return this.pacientes;
    }
    
    public Pagos getPagos(){
        return this.pagos;
    }
    
    public Tratamientos getTratamientos(){
        return this.tratamientos;
    }
    
    public Usuarios getUsuarios(){
        return this.usuarios;
    }
    
    public JPanel getContenedorUsuarios(){
        return this.contenedorUsuarios;
    }

    public int getOpActual() {
        return opActual;
    }

    public void setOpActual(int opActual) {
        this.opActual = opActual;
    }
}