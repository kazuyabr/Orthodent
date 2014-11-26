/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.tratamientos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.Tratamiento;
import orthodent.Item;
import orthodent.JVentana;
import orthodent.db.FichaEvolucionDB;
import orthodent.db.TratamientoDB;
import orthodent.pacientes.FichasClinicas;
import orthodent.usuarios.MostrarInfoTratamiento;

/**
 *
 * @author Msanhuezal
 */
public class Tratamientos extends JPanel implements ActionListener{
    //Esta clase solo la ve el admin!!
    private Image bannerFondo;
    private JTextField buscador;
    private JButton botonBuscar;
    private JButton nuevoTratamiento;
    private JButton eliminarTratamiento;
    private JButton editarValorUco;
    private JTable tabla;
    private TableModel modelo;
    private Object [][] filas;
    private String [] columnasNombre;
    private MostrarInfoTratamiento infoTratamiento;
    private JPanel contenedorListarTratamientos;
    private boolean isListarTratamientos;
    private static int tratamientoSelected;
    
    public Tratamientos(){
        this.setBackground(new Color(243,242,243));
        this.setPreferredSize(new Dimension(1073, 561));
        this.tratamientoSelected = -1;
        this.setLayout(new BorderLayout());
        this.isListarTratamientos = true;
        this.initComponents();
        this.addComponents();
        this.botonBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.eliminarTratamiento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.nuevoTratamiento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.editarValorUco.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public boolean isIsListarTratamientos() {
        return isListarTratamientos;
    }

    public void setIsListarTratamientos(boolean isListarTratamientos) {
        this.isListarTratamientos = isListarTratamientos;
    }
    
    public MostrarInfoTratamiento getInfoTratamiento(){
        return this.infoTratamiento;
    }
    
    private void initComponents(){
        
        this.contenedorListarTratamientos = new JPanel();
        this.contenedorListarTratamientos.setLayout(new BorderLayout());
        
        this.infoTratamiento = null;
        this.bannerFondo = new ImageIcon("src/imagenes/directorioTratamientos.png").getImage();
        
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
        
        
        this.editarValorUco = new JButton();
        this.editarValorUco.setForeground(new Color(11, 146, 181));
        this.editarValorUco.setFont(new Font("Georgia", 1, 12));
        this.editarValorUco.setIcon(new ImageIcon("src/imagenes/edit_mini.png"));
        this.editarValorUco.setText("Editar Valor UCO  ");
        this.editarValorUco.setBorder(null);
        this.editarValorUco.setBorderPainted(false);
        this.editarValorUco.setContentAreaFilled(false);
        this.editarValorUco.addActionListener(this);        
        
        this.nuevoTratamiento = new JButton();
        this.nuevoTratamiento.setForeground(new Color(11, 146, 181));
        this.nuevoTratamiento.setFont(new Font("Georgia", 1, 12));
        this.nuevoTratamiento.setIcon(new ImageIcon("src/imagenes/add_mini.png"));
        this.nuevoTratamiento.setText("Nuevo Tratamiento  ");
        this.nuevoTratamiento.setBorder(null);
        this.nuevoTratamiento.setBorderPainted(false);
        this.nuevoTratamiento.setContentAreaFilled(false);
        this.nuevoTratamiento.addActionListener(this);
        
        this.eliminarTratamiento = new JButton();
        this.eliminarTratamiento.setForeground(new Color(11, 146, 181));
        this.eliminarTratamiento.setFont(new Font("Georgia", 1, 12));
        this.eliminarTratamiento.setIcon(new ImageIcon("src/imagenes/delete_mini.png"));
        this.eliminarTratamiento.setText("Eliminar Tratamiento");
        this.eliminarTratamiento.setBorder(null);
        this.eliminarTratamiento.setBorderPainted(false);
        this.eliminarTratamiento.setContentAreaFilled(false);
        this.eliminarTratamiento.addActionListener(this); 
        
        this.tabla = new JTable();
        this.tabla.setFont(new Font("Georgia", 0, 11));
        this.columnasNombre = new String [] {"Nombre", "Valor Colegio", "Valor Clínica"};
        this.updateModelo();
        this.tabla.getTableHeader().setReorderingAllowed(false);
        
        this.tabla.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if(me.getClickCount() == 1){
                    Object [] fila = getRowAt(row);
                    tratamientoSelected = ((Item)fila[0]).getId();
                    
                }
//                if (me.getClickCount() == 2) {
//                    Object [] fila = getRowAt(row);
//                    try {
//                        Usuario usuario = Autenticacion.getUsuario((String)fila[0], (String)fila[3]);
//                        if(usuario!=null){
//                            
//                            Usuario actual = ((JVentana)getTopLevelAncestor()).getUsuario();
//                            
//                            if(actual.getId_usuario()==usuario.getId_usuario()){
//                                infoTratamiento = new MostrarInfoTratamiento(usuario, true, true);
//                            }
//                            else{
//                                infoTratamiento = new MostrarInfoTratamiento(usuario, false, true);
//                            }
//                            
//                            remove(contenedorListarTratamientos);
//                            add(infoTratamiento, BorderLayout.CENTER);
//                            isListarTratamientos = false;
//                            updateUI();
//                        }
//                    } catch (Exception ex) {
//                    }
//                }
            }
        });
    }
    
    private Object[] getRowAt(int row) {
        Object[] result = new Object[this.columnasNombre.length];
        
        for (int i = 0; i < this.columnasNombre.length; i++) {
            result[i] = this.tabla.getModel().getValueAt(row, i);
        }
        return result;
    }
    
    public void updateModelo(){
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        ArrayList<Tratamiento> tratamientos = TratamientoDB.listarTratamientos();
      
        int m = this.columnasNombre.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(Tratamiento tratamiento : tratamientos){
            //if(tratamiento.isActivo()){

                Object [] fila = new Object [] {new Item(tratamiento.getNombre(), tratamiento.getIdTratamiento()), "$"+tratamiento.getValorColegio(), "$"+tratamiento.getValorClinica()};
                
                objetos.add(fila);
            //}
        }
        
        this.filas = new Object [objetos.size()][m];
        int i = 0;
        for(Object [] o : objetos){
            this.filas[i] = o;
            i++;
        }
        
        this.modelo = new DefaultTableModel(this.filas, this.columnasNombre) {
            Class[] types = new Class [] {
                Item.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
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
    
    public void volverTratamientos() throws Exception{
        if(!this.isListarTratamientos){
            this.remove(this.infoTratamiento);
            this.add(this.contenedorListarTratamientos, BorderLayout.CENTER);
            this.isListarTratamientos = true;
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
        
        this.contenedorListarTratamientos.add(auxiliar,BorderLayout.NORTH);
        
        //Inicio tabla
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this.tabla);
        
        this.contenedorListarTratamientos.add(scrollPane,BorderLayout.CENTER);
        //Fin tabla
        
        this.add(this.contenedorListarTratamientos,BorderLayout.CENTER);
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
                .addComponent(this.editarValorUco)
                .addComponent(this.nuevoTratamiento)
                .addComponent(this.eliminarTratamiento)
                .addContainerGap())
        );
        
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.buscador, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.botonBuscar)
                    .addComponent(this.editarValorUco)
                    .addComponent(this.nuevoTratamiento)
                    .addComponent(this.eliminarTratamiento))
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == this.editarValorUco){
            try {
                new EditarValorUco(((JVentana)this.getTopLevelAncestor()),true).setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(Tratamientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        
        if(e.getSource() == this.nuevoTratamiento){
            new NuevoTratamiento(((JVentana)this.getTopLevelAncestor()),true).setVisible(true);
        }
        
        if(e.getSource() == this.eliminarTratamiento){
            if(tabla.getSelectedRow() != -1){ // existe fila seleccionada
               Object[] options = {"Sí","No"};

               int n = JOptionPane.showOptionDialog(this,
                            "¿Esta seguro que desea eliminar el tratamiento?",
                            "Orthodent",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[1]);
                if(n==0){

                    boolean respuesta = TratamientoDB.eliminarTratamiento(tratamientoSelected);
                    System.out.println("respuesta "+respuesta);
                    this.updateModelo();

                    this.updateUI();

                }
            }
            else{
                    JOptionPane.showMessageDialog(this,
                                        "Debe seleccionar primero un tratamiento",
                                        "Orthodent",
                                        JOptionPane.INFORMATION_MESSAGE);                
            }
            

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
        
        ArrayList<Tratamiento> tratamientos = TratamientoDB.listarTratamientos();
        
        int m = this.columnasNombre.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(Tratamiento tratamiento : tratamientos){
            //if(usuario.isActivo()){

                Object [] fila = new Object [] {tratamiento.getNombre(), "$"+tratamiento.getValorColegio(), "$"+tratamiento.getValorClinica()};
                
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
           //}
        }
        
        this.filas = new Object [objetos.size()][m];
        
        int i = 0;
        for(Object [] el : objetos){
            this.filas[i] = el;
            i++;
        }
        
        this.modelo = new DefaultTableModel(this.filas, this.columnasNombre) {
            Class[] types = new Class [] {
                Item.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
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
