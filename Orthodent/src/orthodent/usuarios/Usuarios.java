/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.usuarios;

import modelo.ClinicaInterna;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.Rol;
import modelo.Usuario;
import orthodent.JVentana;
import orthodent.db.Autenticacion;
import orthodent.db.ClinicaInternaDB;
import orthodent.db.RolDB;

/**
 * 
 * @author Mary
 */
public class Usuarios extends JPanel implements ActionListener{
    //Esta clase solo la ve el admin!!
    private Image bannerFondo;
    private Image bannerClinicas;
    private JTextField buscador;
    private JTextField buscadorClinicas;
    private JButton botonBuscar;
    private JButton botonBuscarClinicas;
    private JButton nuevoUsuario;
    private JButton nuevaClinica;
    private JTable tabla;
    private JTable tablaClinicas;
    private TableModel modelo;
    private TableModel modeloClinicas;
    private Object [][] filas;
    private Object [][] filasClinicas;
    private String [] columnasNombre;
    private String [] columnasNombreClinicas;
    private MostrarInfoTratamiento infoUsuario;
    private DatosClinica infoClinica;
    private JPanel contenedorListarUsuarios;
    private JPanel contenedorListarClinicas;
    private boolean isListarUsuarios;
    private boolean mostrandoClinica;
    private Usuario usuarioActual;
    
    public Usuarios(Usuario usuario){
        this.usuarioActual = usuario;
        this.setBackground(new Color(243,242,243));
        this.setPreferredSize(new Dimension(1073, 561));
        
        this.setLayout(new BorderLayout());
        
        this.isListarUsuarios = true;
        this.initComponents();
        this.addComponents();
        this.mostrandoClinica = false;
        this.botonBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.botonBuscarClinicas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.nuevoUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.nuevaClinica.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public boolean isIsListarUsuarios() {
        return isListarUsuarios;
    }

    public void setIsListarUsuarios(boolean isListarUsuarios) {
        this.isListarUsuarios = isListarUsuarios;
    }
    
    public MostrarInfoTratamiento getInfoUsuario(){
        return this.infoUsuario;
    }
    
    private void initComponents(){
        
        this.contenedorListarUsuarios = new JPanel();
        this.contenedorListarUsuarios.setLayout(new BorderLayout());
        
        this.infoUsuario = null;
        this.bannerFondo = new ImageIcon("src/imagenes/directorioUsuarios.png").getImage();
        
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
        this.tabla.setFont(new Font("Georgia", 0, 11));
        this.columnasNombre = new String [] {"Nombre", "Apellido Paterno", "Apellido Materno", "Email", "Rol", "Activo"};
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
                        Usuario usuario = Autenticacion.getUsuario((String)fila[0], (String)fila[3]);
                        if(usuario!=null){
                            
                            Usuario actual = ((JVentana)getTopLevelAncestor()).getUsuario();
                            
                            if(actual.getId_usuario()==usuario.getId_usuario()){
                                infoUsuario = new MostrarInfoTratamiento(usuario, usuarioActual, true, true);
                            }
                            else{
                                infoUsuario = new MostrarInfoTratamiento(usuario, usuarioActual, false, true);
                            }
                            
                            remove(contenedorListarUsuarios);
                            remove(contenedorListarClinicas);
                            add(infoUsuario, BorderLayout.CENTER);
                            isListarUsuarios = false;
                            updateUI();
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        });

        this.initComponentsClinicas();
        this.addComponentsClinicas();
    }
    
    private Object[] getRowAt(int row) {
        Object[] result = new String[this.columnasNombre.length];
        
        for (int i = 0; i < this.columnasNombre.length; i++) {
            result[i] = this.tabla.getModel().getValueAt(row, i);
        }
        
        return result;
    }
    
    private Object[] getRowAtClinicas(int row) {
        Object[] result = new String[this.columnasNombreClinicas.length];
        
        for (int i = 0; i < this.columnasNombreClinicas.length; i++) {
            result[i] = this.tablaClinicas.getModel().getValueAt(row, i);
        }
        
        return result;
    }
    
    public void updateModelo(){
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        ArrayList<Usuario> usuarios;
        if(usuarioActual.getId_rol() == 1){
            usuarios = Autenticacion.listarUsuarios();
        }
        else{
            usuarios = Autenticacion.listarUsuariosActivos();
        }
        
        int m = this.columnasNombre.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(Usuario usuario : usuarios){
//            if(usuario.isActivo()){
                Rol rol = RolDB.getRol(usuario.getId_rol());
                String activo = (usuario.isActivo())? "SI" : "NO";
                Object [] fila = new Object [] {usuario.getNombre(), usuario.getApellido_pat(), usuario.getApellido_mat(),
                                            usuario.getEmail(), rol.getNombre().toLowerCase(), activo};
                
                objetos.add(fila);
//            }
        }
        
        this.filas = new Object [objetos.size()][m];
        int i = 0;
        for(Object [] o : objetos){
            this.filas[i] = o;
            i++;
        }
        
        this.modelo = new DefaultTableModel(this.filas, this.columnasNombre) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
    
    public void volverUsuarios() throws Exception{
        if(!this.isListarUsuarios){
            this.remove(this.infoUsuario);
            this.add(this.contenedorListarUsuarios, BorderLayout.CENTER);
            this.add(this.contenedorListarClinicas, BorderLayout.SOUTH);
            this.isListarUsuarios = true;
            this.updateModelo();
            this.updateUI();
        }
    }
    
    public void VolverUsuariosDesdeClinica(){
        if(!this.isListarUsuarios){
            this.remove(this.infoClinica);
            this.add(this.contenedorListarUsuarios, BorderLayout.CENTER);
            this.add(this.contenedorListarClinicas, BorderLayout.SOUTH);
            this.isListarUsuarios = true;
            this.mostrandoClinica = false;
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
        
        this.contenedorListarUsuarios.add(auxiliar,BorderLayout.NORTH);
        
        //Inicio tabla
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this.tabla);
        
        this.contenedorListarUsuarios.add(scrollPane,BorderLayout.CENTER);
        //Fin tabla
        
        this.add(this.contenedorListarUsuarios,BorderLayout.CENTER);
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
            this.buscar();
        }
        
        if(e.getSource() == this.nuevaClinica){
            new NuevaClinica(((JVentana)this.getTopLevelAncestor()),true).setVisible(true);
        }
        
        if(e.getSource() == this.botonBuscarClinicas){
            this.buscarClinica();
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
    
    private void buscadorClinicasKeyTyped(KeyEvent evt) {
        char c = evt.getKeyChar();
        
        if(c==KeyEvent.VK_ENTER){
            evt.consume();
            
            if(this.buscadorClinicas.getText().equals("")){
                this.updateModeloClinica();
            }
            else{
                this.buscarClinica();
            }
        }
        else if(c==KeyEvent.VK_BACK_SPACE){
            if(this.buscadorClinicas.getText().equals("")){
                this.updateModeloClinica();
            }
        }
    }    
    
    private void buscar(){
        String value = this.buscador.getText();
        
        ArrayList<Usuario> usuarios;
        if(usuarioActual.getId_rol() == 1){
            usuarios = Autenticacion.listarUsuarios();
        }
        else{
            usuarios = Autenticacion.listarUsuariosActivos();
        }
        
        int m = this.columnasNombre.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(Usuario usuario : usuarios){
//            if(usuario.isActivo()){
                Rol rol = RolDB.getRol(usuario.getId_rol());
                String activo = (usuario.isActivo())? "SI" : "NO";
                Object [] fila = new Object [] {usuario.getNombre(), usuario.getApellido_pat(), usuario.getApellido_mat(),
                                            usuario.getEmail(), rol.getNombre().toLowerCase(), activo};
                
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
//            }
        }
        
        this.filas = new Object [objetos.size()][m];
        
        int i = 0;
        for(Object [] el : objetos){
            this.filas[i] = el;
            i++;
        }
        
        this.modelo = new DefaultTableModel(this.filas, this.columnasNombre) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
    
    
    private void buscarClinica(){
        String value = this.buscadorClinicas.getText();
        
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        ArrayList<ClinicaInterna> clinicas;
        if(usuarioActual.getId_rol() == 1){
            clinicas = ClinicaInternaDB.listarClinicas();
        }
        else{
            clinicas = ClinicaInternaDB.listarClinicasActivas();
        }
        
        int m = this.columnasNombre.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(ClinicaInterna clinica : clinicas){
                String activo = (clinica.isActivo())? "SI" : "NO";
                Object [] fila = new Object [] {clinica.getNombre(), activo};
                
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
        
        this.filasClinicas = new Object [objetos.size()][m];
        int i = 0;
        for(Object [] o : objetos){
            this.filasClinicas[i] = o;
            i++;
        }
        
        this.modeloClinicas = new DefaultTableModel(this.filasClinicas, this.columnasNombreClinicas) {
            Class[] types = new Class [] {
                String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        this.tablaClinicas.setModel(modeloClinicas);
    }    

    private void initComponentsClinicas() {
        
        this.contenedorListarClinicas = new JPanel();
        this.contenedorListarClinicas.setLayout(new BorderLayout());
        
        this.bannerClinicas = new ImageIcon("src/imagenes/directorioClinicas.png").getImage();
        
        this.buscadorClinicas = new JTextField();
        
        this.buscadorClinicas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                buscadorClinicasKeyTyped(evt);
            }
        });
        
        this.botonBuscarClinicas = new JButton();
        this.botonBuscarClinicas.setForeground(new Color(11, 146, 181));
        this.botonBuscarClinicas.setFont(new Font("Georgia", 1, 12));
        this.botonBuscarClinicas.setIcon(new ImageIcon("src/imagenes/lupa_mini.png"));
        this.botonBuscarClinicas.setText("Buscar");
        this.botonBuscarClinicas.setBorder(null);
        this.botonBuscarClinicas.setBorderPainted(false);
        this.botonBuscarClinicas.setContentAreaFilled(false);
        this.botonBuscarClinicas.addActionListener(this);        
        
        this.nuevaClinica = new JButton();
        this.nuevaClinica.setForeground(new Color(11, 146, 181));
        this.nuevaClinica.setFont(new Font("Georgia", 1, 12));
        this.nuevaClinica.setIcon(new ImageIcon("src/imagenes/add_mini.png"));
        this.nuevaClinica.setText("Nueva Clínica");
        this.nuevaClinica.setBorder(null);
        this.nuevaClinica.setBorderPainted(false);
        this.nuevaClinica.setContentAreaFilled(false);
        this.nuevaClinica.addActionListener(this);
        
        this.tablaClinicas = new JTable();
        this.tablaClinicas.setFont(new Font("Georgia", 0, 11));
        this.columnasNombreClinicas = new String [] {"Nombre", "Activo"};
        this.updateModeloClinica();
        this.tablaClinicas.getTableHeader().setReorderingAllowed(false);
        
        this.tablaClinicas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table2 =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table2.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    Object [] fila = getRowAtClinicas(row);
                    try {
                        ClinicaInterna clinica = ClinicaInternaDB.getClinica((String)fila[0]);
                        System.out.println(fila[0]);
                        if(clinica!=null){
                            
                            infoClinica = new DatosClinica(clinica, usuarioActual);
                            
                            remove(contenedorListarUsuarios);
                            remove(contenedorListarClinicas);
                            add(infoClinica, BorderLayout.CENTER);
                            mostrandoClinica = true;
                            isListarUsuarios = false;
                            updateUI();
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        });
        
    }

    public void updateModeloClinica() {
        
        ArrayList<ClinicaInterna> clinicas;
        if(usuarioActual.getId_rol() == 1){
            clinicas = ClinicaInternaDB.listarClinicas();
        }
        else{
            clinicas = ClinicaInternaDB.listarClinicasActivas();
        }
        
        int m = this.columnasNombreClinicas.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(ClinicaInterna clinica : clinicas){
            String activo = (clinica.isActivo())? "SI" : "NO";
            Object [] fila = new Object [] {clinica.getNombre(), activo};
            objetos.add(fila);
        }
        
        this.filasClinicas = new Object [objetos.size()][m];
        int i = 0;
        for(Object [] o : objetos){
            this.filasClinicas[i] = o;
            i++;
        }
        
        this.modeloClinicas = new DefaultTableModel(this.filasClinicas, this.columnasNombreClinicas) {
            Class[] types = new Class [] {
                String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        this.tablaClinicas.setModel(modeloClinicas);
    }
    
    private void addComponentsClinicas(){
        JPanel auxiliar = new JPanel();
        auxiliar.setLayout(new BorderLayout());
        
        //Inicio Buscador y boton agregar
        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(1106, 47));
        panel1.setBackground(new Color(255,255,255));
        this.addComponentPanel2(panel1);
        auxiliar.add(panel1,BorderLayout.NORTH);
        //Fin Buscador y boton agregar
        
        //Inicio Panel azul, con el nombre del Directorio
        JPanel banner = new JPanel(){
            @Override
            public void paint(Graphics g){
                paintComponent(g);
                g.drawImage(bannerClinicas, 0, 0, 1106, 61, this);
            }
        };
        banner.setBackground(new Color(255,255,255));
        banner.setPreferredSize(new Dimension(1106, 61));
        auxiliar.add(banner,BorderLayout.CENTER);
        //Fin Panel azul, con el nombre del Directorio
        
        this.contenedorListarClinicas.add(auxiliar,BorderLayout.NORTH);
        
        //Inicio tabla
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this.tablaClinicas);
        
        this.contenedorListarClinicas.add(scrollPane,BorderLayout.CENTER);
        //Fin tabla
        this.contenedorListarClinicas.setPreferredSize(new Dimension(1106,300));
        this.add(this.contenedorListarClinicas,BorderLayout.SOUTH);
    }

    private void addComponentPanel2(JPanel panel1) {
        GroupLayout groupLayout = new GroupLayout(panel1);
        panel1.setLayout(groupLayout);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.buscadorClinicas, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.botonBuscarClinicas)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(this.nuevaClinica)
                .addContainerGap())
        );
        
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.buscadorClinicas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.botonBuscarClinicas)
                    .addComponent(this.nuevaClinica))
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }

    public boolean isMostrandoClinica() {
        return mostrandoClinica;
    }

    public void setMostrandoClinica(boolean mostrandoClinica) {
        this.mostrandoClinica = mostrandoClinica;
    }

    void actualizarClinicas() {
        this.updateModeloClinica();
    }
    
    
    
}