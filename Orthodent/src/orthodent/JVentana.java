/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent;

import orthodent.pagos.Pagos;
import orthodent.historial.Historial;
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
import orthodent.db.Autenticacion;
import orthodent.usuarios.MostrarInfoUsuario;

public class JVentana extends JFrame{
    
    private Image icono;
    private Usuario usuario;//Usuario Actual Logeado!!
    private PanelOpciones panelOpciones;
    private JPanel contenedorAgenda;
    private JPanel contenedorPacientes;
    private JPanel contenedorPagos;
    private JPanel contenedorUsuarios;
    private JPanel contenedorHistorial;
    private JPanel contenedorConfigurarCuenta;
    private Agenda agenda;
    private Pacientes pacientes;
    private Pagos pagos;
    private Usuarios usuarios;
    private Historial historial;
    private MostrarInfoUsuario configurarCuenta;
    
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
        this.contenedorPagos = new JPanel();
        this.contenedorUsuarios = new JPanel();
        this.contenedorHistorial = new JPanel();
        this.contenedorConfigurarCuenta = new JPanel();
        
        this.crearAgenda();
        this.add(this.contenedorAgenda,BorderLayout.CENTER);
        
        this.pacientes = null;
        this.pagos = null;
        this.usuarios = null;
        this.historial = null;
        this.configurarCuenta = null;
    }
    
    private void crearAgenda(){
        this.contenedorAgenda.setLayout(new BorderLayout());
        this.agenda = new Agenda();
        
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
        
        this.contenedorAgenda.add(panel,BorderLayout.NORTH);
    }
    
    private void crearPacientes(){
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
    
    private void crearPagos(){
        this.contenedorPagos.setLayout(new BorderLayout());
        this.pagos = new Pagos();
        
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
    
    private void crearUsuarios() throws Exception{
        this.contenedorUsuarios.setLayout(new BorderLayout());
        this.usuarios = new Usuarios();
        
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
    
    private void crearHistorial(){
        this.contenedorHistorial.setLayout(new BorderLayout());
        this.historial = new Historial();
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(this.historial,BorderLayout.CENTER);
        
        JPanel borde1 = new JPanel();
        borde1.setBackground(Color.white);
        borde1.setPreferredSize(new Dimension(130, 488));
        JPanel borde2 = new JPanel();
        borde2.setBackground(Color.white);
        borde2.setPreferredSize(new Dimension(130, 488));
        
        panel.add(borde1,BorderLayout.WEST);
        panel.add(borde2,BorderLayout.EAST);
        
        this.contenedorHistorial.add(panel,BorderLayout.NORTH);
    }
    
    private void crearConfigurarCuenta(){
        this.contenedorConfigurarCuenta.setLayout(new BorderLayout());
        this.configurarCuenta = new MostrarInfoUsuario(this.usuario, true, false);
        
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
            this.remove(this.contenedorUsuarios);
        }
        catch(Exception e){
        }
        
        try{
            this.remove(this.contenedorHistorial);
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
    
    public void cambiarPacientes(){
        if(opActual!=2){
            this.removeAncestor();
            this.panelOpciones.setIconButton(2);
            
            if(this.pacientes==null){
                this.crearPacientes();
            }
            
            this.add(this.contenedorPacientes,BorderLayout.CENTER);
            this.contenedorPacientes.updateUI();
            this.opActual = 2;
        }
    }
    
    public void cambiarPagos(){
        if(opActual!=3){
            this.removeAncestor();
            this.panelOpciones.setIconButton(3);
            
            if(this.pagos==null){
                this.crearPagos();
            }
            
            this.add(this.contenedorPagos,BorderLayout.CENTER);
            this.contenedorPagos.updateUI();
            this.opActual = 3;
        }
    }
    
    public void cambiarUsuarios() throws Exception{
        if(opActual!=4){
            this.removeAncestor();
            this.panelOpciones.setIconButton(4);
            
            if(this.usuarios==null){
                this.crearUsuarios();
            }
            
            this.add(this.contenedorUsuarios,BorderLayout.CENTER);
            this.contenedorUsuarios.updateUI();
            this.opActual = 4;
        }
    }
    
    public void cambiarHistorial(){
        if(opActual!=5){
            this.removeAncestor();
            this.panelOpciones.setIconButton(5);
            
            if(this.historial==null){
                this.crearHistorial();
            }
            
            this.add(this.contenedorHistorial,BorderLayout.CENTER);
            this.contenedorHistorial.updateUI();
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
    
    public Usuarios getUsuarios(){
        return this.usuarios;
    }
    
    public Historial getHistorial(){
        return this.historial;
    }
    
    public JPanel getContenedorUsuarios(){
        return this.contenedorUsuarios;
    }
}