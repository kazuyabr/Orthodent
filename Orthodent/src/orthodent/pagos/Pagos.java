/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.pagos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.Paciente;
import modelo.Pago;
import modelo.Usuario;
import orthodent.Item;
import orthodent.db.Autenticacion;
import orthodent.db.PacienteDB;
import orthodent.db.PagoDB;
import orthodent.pacientes.MostrarInfoPaciente;

/**
 *
 * @author felipe
 */
public class Pagos extends JPanel implements ActionListener
{  
    private Image bannerFondo;
    private JComboBox profesionales;
    private JTable tabla;
    private TableModel modelo;
    private Object [][] filas;
    private String [] columnasNombre;
    private ArrayList<Usuario> auxiliar;
    
    private MostrarInfoPaciente infoPaciente;
    private JPanel contenedorListarPagos;
    private boolean isListarBitacoras;
    
    public Pagos() throws Exception{
        
        this.setBackground(new Color(243,242,243));
        this.setPreferredSize(new Dimension(1073, 561));
        
        this.setLayout(new BorderLayout());
        
        this.isListarBitacoras = true;
        this.initComponents();
        this.addComponents();
     
    }

    public boolean isIsListarBitacoras() {
        return isListarBitacoras;
    }

    public void setIsListarBitacoras(boolean isListarBitacoras) {
        this.isListarBitacoras = isListarBitacoras;
    }
    
    public MostrarInfoPaciente getInfoPaciente(){
        return this.infoPaciente;
    }
    
    private void initComponents() throws Exception{
        
        this.contenedorListarPagos = new JPanel();
        this.contenedorListarPagos.setLayout(new BorderLayout());
        
        this.infoPaciente = null;
        this.bannerFondo = new ImageIcon("src/imagenes/directorioPagos.png").getImage();
        
        this.profesionales = new JComboBox(){
            public void fireItemStateChanged(ItemEvent evt){
                for(Usuario prof : auxiliar){
                    if(((Item)evt.getItem()).getId()==prof.getId_usuario()){
                        System.out.println("=)");
                    }
                }
            }
        };
        
        Vector model = new Vector();
        this.auxiliar = Autenticacion.listarProfesionales();
        model.addElement(new Item("TODOS", -1));
        if(auxiliar!=null && auxiliar.size()>0){
            for(Usuario prof : auxiliar){
                String nombre = prof.getNombre();
        
                if(nombre.contains(" ")){
                    nombre = nombre.substring(0,nombre.indexOf(" "));
                }

                Item item = new Item(nombre+" "+prof.getApellido_pat(), prof.getId_usuario());
                model.addElement(item);
            }
        }
        this.profesionales.setModel(new DefaultComboBoxModel(model));
        
        this.tabla = new JTable();
        this.tabla.setFont(new Font("Georgia", 0, 11));
        this.columnasNombre = new String [] {"Fecha", "Tipo de Pago", "Detalle", "Valor", "Numero de Boleta", "Laboratorio"};
        ArrayList<Pago> pagos = PagoDB.listarPagos();
        this.updateModelo(pagos);
        this.tabla.getTableHeader().setReorderingAllowed(false);
        
        this.tabla.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
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
    
    public void updateModelo(ArrayList<Pago> pagos) throws Exception{
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        
        int m = this.columnasNombre.length;
        
        this.filas = new Object [pagos.size()][m];
        int i = 0;
        for(Pago pago : pagos){
            String fecha = pago.getFecha();
            String fechaBitacora = this.girarFecha(fecha);
            
            String tipoPago = pago.getTipoPago();
            String detalle = pago.getDetalle();
            String valor = "$"+pago.getAbono();
            String numBoleta = pago.getNumBoleta()+"";
            Boolean lab = pago.getIsLab();

            Object [] fila = new Object [] {new Item(fechaBitacora, pago.getIdPago()), tipoPago, detalle, valor,
                                            numBoleta, lab};
            
            this.filas[i] = fila;
            i++;
        }
        
        this.modelo = new DefaultTableModel(this.filas, this.columnasNombre) {
            Class[] types = new Class [] {
                Item.class, String.class, String.class, String.class, String.class, Boolean.class
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
    
    public void volverPacientes() throws Exception{
        if(!this.isListarBitacoras){
            this.remove(this.infoPaciente);
            this.add(this.contenedorListarPagos, BorderLayout.CENTER);
            this.isListarBitacoras = true;
            ArrayList<Pago> pagos = PagoDB.listarPagos();
            this.updateModelo(pagos);
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
        
        this.contenedorListarPagos.add(auxiliar,BorderLayout.NORTH);
        
        //Inicio tabla
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this.tabla);
        
        this.contenedorListarPagos.add(scrollPane,BorderLayout.CENTER);
        //Fin tabla
        
        this.add(this.contenedorListarPagos,BorderLayout.CENTER);
    }
    
    private void addComponentPanel1(JPanel panel1){
        
        GroupLayout groupLayout = new GroupLayout(panel1);
        panel1.setLayout(groupLayout);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap()
                //.addComponent(this.buscador, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                //.addComponent(this.botonBuscar)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                //.addComponent(this.nuevoPaciente)
                .addContainerGap())
        );
        
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE))
                    //.addComponent(this.buscador, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    //.addComponent(this.botonBuscar))
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    private void buscadorKeyTyped(KeyEvent evt) throws Exception {
        char c = evt.getKeyChar();
        
        /*if(c==KeyEvent.VK_ENTER){
            evt.consume();
            
            if(this.buscador.getText().equals("")){
                ArrayList<Pago> pagos = PagoDB.listarPagos();
                this.updateModelo(pagos);
            }
            else{
                this.buscar();
            }
        }
        else if(c==KeyEvent.VK_BACK_SPACE){
            if(this.buscador.getText().equals("")){
                ArrayList<Pago> pagos = PagoDB.listarPagos();
                this.updateModelo(pagos);
            }
        }*/
    }
}
