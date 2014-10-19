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
public class Usuarios extends JPanel implements ActionListener{
    //Esta clase solo la ve el admin!!
    private Image bannerFondo;
    private JTextField buscador;
    private JButton botonBuscar;
    private JButton nuevoUsuario;
    private JTable tabla;
    private TableModel modelo;
    private Object [][] filas;
    private String [] columnasNombre;
    
    public Usuarios(){
        this.setBackground(new Color(243,242,243));
        this.setPreferredSize(new Dimension(1073, 561));
        
        this.setLayout(new BorderLayout());
        
        this.initComponents();
        this.addComponents();
    }
    
    private void initComponents(){
        this.bannerFondo = new ImageIcon("src/imagenes/directorioUsuarios.png").getImage();
        
        this.buscador = new JTextField();
        this.botonBuscar = new JButton();
        this.botonBuscar.setForeground(new Color(11, 146, 181));
        this.botonBuscar.setFont(new Font("Georgia", 1, 12));
        this.botonBuscar.setIcon(new ImageIcon("src/imagenes/lupa_mini.png"));
        this.botonBuscar.setText("Buscar");
        this.botonBuscar.setBorder(null);
        this.botonBuscar.setBorderPainted(false);
        this.botonBuscar.setContentAreaFilled(false);
        
        this.nuevoUsuario = new JButton();
        this.nuevoUsuario.setForeground(new Color(11, 146, 181));
        this.nuevoUsuario.setFont(new Font("Georgia", 1, 12));
        this.nuevoUsuario.setIcon(new ImageIcon("src/imagenes/add_mini.png"));
        this.nuevoUsuario.setText("Nuevo Usuario");
        this.nuevoUsuario.setBorder(null);
        this.nuevoUsuario.setBorderPainted(false);
        this.nuevoUsuario.setContentAreaFilled(false);
        this.nuevoUsuario.addActionListener(this);
        
        this.tabla = new JTable();
        this.initTabla();
    }
    
    private void initTabla(){
        
        ArrayList<Usuario> usuarios = Autenticacion.listarUsuarios();
        
        this.columnasNombre = new String [] {"Nombres", "Apellido Paterno", "Apellido Materno", "Nombre de Usuario", "Rol"};
        
        int n = usuarios.size();
        int m = this.columnasNombre.length;
        
        this.filas = new Object [n][m];
        
        int i = 0;
        for(Usuario usuario : usuarios){
            Rol rol = RolDB.getRol(usuario.getId_rol());
            
            Object [] fila = new Object [] {usuario.getNombre(), usuario.getApellido_pat(), usuario.getApellido_mat(),
                                        usuario.getNombreUsuario(), rol.getNombre().toLowerCase()};
            
            filas[i] = fila;
            i++;
        }
        
        this.modelo = new DefaultTableModel(this.filas, this.columnasNombre) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        this.tabla.setModel(modelo);
        this.tabla.getTableHeader().setReorderingAllowed(false) ;
    }
    
    private void addComponents(){
        JPanel auxiliar = new JPanel();
        auxiliar.setLayout(new BorderLayout());
        
        //Inicio Buscador y boton agregar
        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(1106, 47));
        panel1.setBackground(new Color(255,255,255));
        this.addComponentPanel1(panel1);
        auxiliar.add(panel1,BorderLayout.NORTH);
        //Fin Buscador y boton agregar
        
        //Inicio Panel azul, con el nombre del Directorio
        JPanel banner = new JPanel(){
            @Override
            public void paint(Graphics g){
                paintComponent(g);
                g.drawImage(bannerFondo, 0, 0, 1106, 61, this);
            }
        };
        banner.setBackground(new Color(255,255,255));
        banner.setPreferredSize(new Dimension(1106, 61));
        auxiliar.add(banner,BorderLayout.CENTER);
        //Fin Panel azul, con el nombre del Directorio
        
        this.add(auxiliar,BorderLayout.NORTH);
        
        //Inicio tabla
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this.tabla);
        
        this.add(scrollPane,BorderLayout.CENTER);
        //Fin tabla
    }
    
    private void addComponentPanel1(JPanel panel1){
        
        GroupLayout groupLayout = new GroupLayout(panel1);
        panel1.setLayout(groupLayout);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.buscador, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.botonBuscar)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(this.nuevoUsuario)
                .addContainerGap())
        );
        
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.buscador, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.botonBuscar)
                    .addComponent(this.nuevoUsuario))
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == this.nuevoUsuario){
            new NuevoUsuario(((JVentana)this.getTopLevelAncestor()),true).setVisible(true);
        }
        
        if(e.getSource() == this.botonBuscar){
            System.out.println("Boton Buscar");
        }
    }
}
