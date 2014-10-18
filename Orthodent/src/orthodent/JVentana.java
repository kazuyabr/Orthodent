/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import modelo.Usuario;

public class JVentana extends JFrame{
    
    private Image icono;
    private Usuario usuario;//Usuario Actual Logeado!!
    private PanelOpciones panelOpciones;
    private JPanel contenedorAgenda;
    private JPanel contenedorPacientes;
    private JPanel contenedorPagos;
    private JPanel contenedorUsuarios;
    private JPanel contenedorHistorial;
    private int opActual;

    public JVentana(Usuario usuario){
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.setTitle("Orthodent");
        
        this.usuario = usuario;
        this.opActual = 1;
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
    
    private void defineVentana(){
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
        
        this.agregarAgenda();
    }
    
    private void agregarAgenda(){
        this.contenedorAgenda.setBackground(Color.white);
        
        //Introducir código aquí
        JLabel mensaje = new JLabel("Agenda... No disponible aún");
        this.contenedorAgenda.add(mensaje);
        
        this.add(this.contenedorAgenda,BorderLayout.CENTER);
    }
    
    private void agregarPacientes(){
        this.contenedorPacientes.setBackground(Color.white);
        
        //Introducir código aquí
        JLabel mensaje = new JLabel("Pacientes... No disponible aún");
        this.contenedorPacientes.add(mensaje);
        
        this.add(this.contenedorPacientes,BorderLayout.CENTER);
    }
    
    private void agregarPagos(){
        this.contenedorPagos.setBackground(Color.white);
        
        //Introducir código aquí
        JLabel mensaje = new JLabel("Pagos... No disponible aún");
        this.contenedorPagos.add(mensaje);
        
        this.add(this.contenedorPagos,BorderLayout.CENTER);
    }
    
    private void agregarUsuarios(){
        this.contenedorUsuarios.setBackground(Color.white);
        
        //Introducir código aquí
        JLabel mensaje = new JLabel("Usuarios... No disponible aún");
        this.contenedorUsuarios.add(mensaje);
        
        this.add(this.contenedorUsuarios,BorderLayout.CENTER);
    }
    
    private void agregarHistorial(){
        this.contenedorHistorial.setBackground(Color.white);
        
        //Introducir código aquí
        JLabel mensaje = new JLabel("Historial... No disponible aún");
        this.contenedorHistorial.add(mensaje);
        
        this.add(this.contenedorHistorial,BorderLayout.CENTER);
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
    }
    
    public void cambiarAgenda(){
        if(opActual!=1){
            this.removeAncestor();
            this.add(this.contenedorAgenda,BorderLayout.CENTER);
            this.contenedorAgenda.updateUI();
            this.opActual = 1;
        }
    }
    
    public void cambiarPacientes(){
        if(opActual!=2){
            this.removeAncestor();
            
            /*if(this.pacientes==null){
                this.agregarPacientes();
            }*/
            
            this.add(this.contenedorPacientes,BorderLayout.CENTER);
            this.contenedorPacientes.updateUI();
            this.opActual = 2;
        }
    }
    
    public void cambiarPagos(){
        if(opActual!=3){
            this.removeAncestor();
            
            /*if(this.pagos==null){
                this.agregarPagos();
            }*/
            
            this.add(this.contenedorPagos,BorderLayout.CENTER);
            this.contenedorPagos.updateUI();
            this.opActual = 3;
        }
    }
    
    public void cambiarUsuarios(){
        if(opActual!=4){
            this.removeAncestor();
            
            /*if(this.usuarios==null){
                this.agregarUsuarios();
            }*/
            
            this.add(this.contenedorUsuarios,BorderLayout.CENTER);
            this.contenedorUsuarios.updateUI();
            this.opActual = 4;
        }
    }
    
    public void cambiarHistorial(){
        if(opActual!=5){
            this.removeAncestor();
            
            /*if(this.historial==null){
                this.agregarHistorial();
            }*/
            
            this.add(this.contenedorHistorial,BorderLayout.CENTER);
            this.contenedorHistorial.updateUI();
            this.opActual = 5;
        }
    }
}