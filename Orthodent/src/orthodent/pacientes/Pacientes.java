/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.pacientes;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.Paciente;
import modelo.Usuario;
import orthodent.JVentana;
import orthodent.db.PacienteDB;

/**
 * 
 * @author Mary
 */
public class Pacientes extends JPanel implements ActionListener{
    
    private Usuario actual;
    private Image bannerFondo;
    private JTextField buscador;
    private JButton botonBuscar;
    private JButton nuevoPaciente;
    private JTable tabla;
    private TableModel modelo;
    private Object [][] filas;
    private String [] columnasNombre;
    private MostrarInfoPaciente infoPaciente;
    private JPanel contenedorListarPacientes;
    private boolean isListarPacientes;
    
    public Pacientes(Usuario actual){
        this.actual = actual;
        this.setBackground(new Color(243,242,243));
        this.setPreferredSize(new Dimension(1073, 561));
        
        this.setLayout(new BorderLayout());
        
        this.isListarPacientes = true;
        this.initComponents();
        this.addComponents();
    }

    public boolean isIsListarPacientes() {
        return isListarPacientes;
    }

    public void setIsListarPacientes(boolean isListarPacientes) {
        this.isListarPacientes = isListarPacientes;
    }
    
    public MostrarInfoPaciente getInfoPaciente(){
        return this.infoPaciente;
    }
    
    private void initComponents(){
        
        this.contenedorListarPacientes = new JPanel();
        this.contenedorListarPacientes.setLayout(new BorderLayout());
        
        this.infoPaciente = null;
        this.bannerFondo = new ImageIcon("src/imagenes/directorioPacientes.png").getImage();
        
        this.buscador = new JTextField();
        
        this.buscador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                buscadorKeyTyped(evt);
            }
        });
        
        this.botonBuscar = new JButton();
        this.botonBuscar.setForeground(new Color(11, 146, 181));
        this.botonBuscar.setFont(new Font("Georgia", 1, 12));
        this.botonBuscar.setIcon(new ImageIcon("src/imagenes/lupa_mini.png"));
        this.botonBuscar.setText("Buscar");
        this.botonBuscar.setBorder(null);
        this.botonBuscar.setBorderPainted(false);
        this.botonBuscar.setContentAreaFilled(false);
        this.botonBuscar.addActionListener(this);
        
        this.nuevoPaciente = new JButton();
        this.nuevoPaciente.setForeground(new Color(11, 146, 181));
        this.nuevoPaciente.setFont(new Font("Georgia", 1, 12));
        this.nuevoPaciente.setIcon(new ImageIcon("src/imagenes/add_mini.png"));
        this.nuevoPaciente.setText("Nuevo Paciente");
        this.nuevoPaciente.setBorder(null);
        this.nuevoPaciente.setBorderPainted(false);
        this.nuevoPaciente.setContentAreaFilled(false);
        this.nuevoPaciente.addActionListener(this);
        
        this.tabla = new JTable();
        this.tabla.setFont(new Font("Georgia", 0, 11));
        this.columnasNombre = new String [] {"Nombre", "Apellido Paterno", "Apellido Materno", "Rut", "Fecha de Nacimiento", "Telefono", "Antecedentes Médicos"};
        this.updateModelo();
        this.tabla.getTableHeader().setReorderingAllowed(false);
        
        this.tabla.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    Object [] fila = getRowAt(row);
                    try {
                        
                        Paciente paciente = PacienteDB.getPaciente((String)fila[3]);
                        
                        if(paciente!=null){
                            
                            infoPaciente = new MostrarInfoPaciente(paciente, actual);
                            
                            remove(contenedorListarPacientes);
                            add(infoPaciente, BorderLayout.CENTER);
                            isListarPacientes = false;
                            updateUI();
                        }
                    } catch (Exception ex) {
                        System.out.println("");
                    }
                }
            }
        });
    }
    
    private Object[] getRowAt(int row) {
        Object[] result = new String[this.columnasNombre.length];
        
        for (int i = 0; i < this.columnasNombre.length; i++) {
            result[i] = this.tabla.getModel().getValueAt(row, i);
        }
        
        return result;
    }
    
    private String girarFecha(String fecha){
        
        if(fecha!=null){
            String año = fecha.substring(0, fecha.indexOf("-"));
            fecha = fecha.substring(fecha.indexOf("-")+1, fecha.length());

            String mes = fecha.substring(0, fecha.indexOf("-"));
            fecha = fecha.substring(fecha.indexOf("-")+1, fecha.length());

            String fechaNueva = fecha+"-"+mes+"-"+año;
            return fechaNueva;
        }
        
        return fecha;
    }
    
    public void updateModelo(){
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        ArrayList<Paciente> pacientes = null;
        
        if(this.actual.getId_rol()==3){
            //Profesional
            pacientes = PacienteDB.listarPacientes(this.actual.getId_usuario());
        }
        else{
            pacientes = PacienteDB.listarPacientes();
        }
        
        int m = this.columnasNombre.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(Paciente paciente : pacientes){
            if(paciente.isActivo()){
                
                String fechaNacimiento = this.girarFecha(paciente.getFechaNacimiento());
                
                Object [] fila = new Object [] {paciente.getNombre(), paciente.getApellido_pat(), paciente.getApellido_mat(),
                                            paciente.getRut(), fechaNacimiento, paciente.getTelefono(), paciente.getAntecedenteMedico()};
                
                objetos.add(fila);
            }
        }
        
        this.filas = new Object [objetos.size()][m];
        int i = 0;
        for(Object [] o : objetos){
            this.filas[i] = o;
            i++;
        }
        
        this.modelo = new DefaultTableModel(this.filas, this.columnasNombre) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        this.tabla.setModel(modelo);
    }
    
    public void volverPacientes() throws Exception{
        if(!this.isListarPacientes){
            this.remove(this.infoPaciente);
            this.add(this.contenedorListarPacientes, BorderLayout.CENTER);
            this.isListarPacientes = true;
            this.updateModelo();
            this.updateUI();
        }
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
        
        this.contenedorListarPacientes.add(auxiliar,BorderLayout.NORTH);
        
        //Inicio tabla
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this.tabla);
        
        this.contenedorListarPacientes.add(scrollPane,BorderLayout.CENTER);
        //Fin tabla
        
        this.add(this.contenedorListarPacientes,BorderLayout.CENTER);
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
                .addComponent(this.nuevoPaciente)
                .addContainerGap())
        );
        
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.buscador, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.botonBuscar)
                    .addComponent(this.nuevoPaciente))
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == this.nuevoPaciente){
            new NuevoPaciente(((JVentana)this.getTopLevelAncestor()),true).setVisible(true);
        }
        
        if(e.getSource() == this.botonBuscar){
            this.buscar();
        }
    }
    
    private void buscadorKeyTyped(KeyEvent evt) {
        char c = evt.getKeyChar();
        
        if(c==KeyEvent.VK_ENTER){
            evt.consume();
            
            if(this.buscador.getText().equals("")){
                this.updateModelo();
            }
            else{
                this.buscar();
            }
        }
        else if(c==KeyEvent.VK_BACK_SPACE){
            if(this.buscador.getText().equals("")){
                this.updateModelo();
            }
        }
    }
    
    private void buscar(){
        String value = this.buscador.getText();
        
        ArrayList<Paciente> pacientes = PacienteDB.listarPacientes();
        
        int m = this.columnasNombre.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(Paciente paciente : pacientes){
            if(paciente.isActivo()){
                String fechaNacimiento = this.girarFecha(paciente.getFechaNacimiento());
                
                Object [] fila = new Object [] {paciente.getNombre(), paciente.getApellido_pat(), paciente.getApellido_mat(),
                    paciente.getRut(), fechaNacimiento, paciente.getTelefono(), paciente.getAntecedenteMedico()};
                
                boolean aux = false;
                
                for(Object o : fila){
                    String obj = (String) o;
                    obj = obj.toLowerCase();
                    if(obj.contains(value)){
                        aux = true;
                        break;
                    }
                }
                
                if(aux){
                    objetos.add(fila);
                }
            }
        }
        
        this.filas = new Object [objetos.size()][m];
        
        int i = 0;
        for(Object [] el : objetos){
            this.filas[i] = el;
            i++;
        }
        
        this.modelo = new DefaultTableModel(this.filas, this.columnasNombre) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        this.tabla.setModel(modelo);
    }
}