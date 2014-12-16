/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.pagos;

import com.toedter.calendar.JDateChooser;
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
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.Pago;
import modelo.PlanTratamiento;
import modelo.Usuario;
import orthodent.Item;
import orthodent.db.Autenticacion;
import orthodent.db.PagoDB;
import orthodent.db.PlanTratamientoDB;

/**
 *
 * @author Mary
 */
public class Pagos extends JPanel implements ActionListener
{  
    private Image bannerFondo;
    private JLabel labelProfesionales;
    private JComboBox profesionales;
    private JLabel desde;
    private JDateChooser fechaDesde;
    private JLabel hasta;
    private JDateChooser fechaHasta;
    private JButton imprimir;
    private JTable tabla;
    private TableModel modelo;
    private Object [][] filas;
    private String [] columnasNombre;
    private ArrayList<Usuario> auxiliar;
    private JPanel contenedorListarPagos;
    
    public Pagos() throws Exception{
        
        this.setBackground(new Color(243,242,243));
        this.setPreferredSize(new Dimension(1073, 561));
        
        this.setLayout(new BorderLayout());
        
        this.initComponents();
        this.addComponents();
        this.imprimir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        this.updateFilter();
    }
    
    private void initComponents() throws Exception{
        
        this.contenedorListarPagos = new JPanel();
        this.contenedorListarPagos.setLayout(new BorderLayout());
        
        this.bannerFondo = new ImageIcon("src/imagenes/directorioPagos.png").getImage();
        
        this.labelProfesionales = new JLabel("Profesional");
        this.labelProfesionales.setFont(new Font("Georgia", 1, 12));
        this.labelProfesionales.setForeground(new Color(11, 146, 181));
        
        this.profesionales = new JComboBox(){
            public void fireItemStateChanged(ItemEvent evt){
                int id = ((Item)evt.getItem()).getId();
                if(id==-1){
                    //TODOS
                    Date date1 = fechaDesde.getDate();
                    Date date2 = fechaHasta.getDate();
                    String fecha1 = getFechaString(date1);
                    String fecha2 = getFechaString(date2);

                    ArrayList<Pago> pagos = PagoDB.listarPagos(-1, fecha1, fecha2);

                    try {
                        updateModelo(pagos);
                    } catch (Exception ex) {
                    }
                }
                else{
                    for(Usuario prof : auxiliar){
                        if(id==prof.getId_usuario()){
                            //algún profesional en particular
                            Date date1 = fechaDesde.getDate();
                            Date date2 = fechaHasta.getDate();
                            String fecha1 = getFechaString(date1);
                            String fecha2 = getFechaString(date2);
                            
                            ArrayList<Pago> pagos = PagoDB.listarPagos(id, fecha1, fecha2);
                            try {
                                updateModelo(pagos);
                            } catch (Exception ex) {
                            }
                        }
                    }
                }
            }
        };
        
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        
        Date inicio = this.primerDiaDelMes(date);
        Date fin = this.ultimoDiaDelMes(date);
        
        this.desde = new JLabel("Desde");
        this.desde.setFont(new Font("Georgia", 1, 12));
        this.desde.setForeground(new Color(11, 146, 181));
        
        this.fechaDesde = new JDateChooser(inicio);
        this.fechaDesde.setBackground(new Color(255, 255, 255));
        this.fechaDesde.setFont(new Font("Georgia", 0, 12));
        this.fechaDesde.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fechaDesdePropertyChange(evt);
            }
        });
        
        this.hasta = new JLabel("Hasta");
        this.hasta.setFont(new Font("Georgia", 1, 12));
        this.hasta.setForeground(new Color(11, 146, 181));
        
        this.fechaHasta = new JDateChooser(fin);
        this.fechaHasta.setBackground(new Color(255, 255, 255));
        this.fechaHasta.setFont(new Font("Georgia", 0, 12));
        this.fechaHasta.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fechaHastaPropertyChange(evt);
            }
        });
        
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
        this.profesionales.setFont(new Font("Georgia", 0, 12)); // NOI18N
        
        this.imprimir = new JButton();
        this.imprimir.setFont(new Font("Georgia", 1, 12));
        this.imprimir.setText("Imprimir");
        this.imprimir.addActionListener(this);
        
        this.tabla = new JTable();
        this.tabla.setFont(new Font("Georgia", 0, 11));
        this.columnasNombre = new String [] {"Fecha", "Profesional", "Tipo de Pago", "Detalle", "Valor", "Numero de Boleta", "Laboratorio"};
        this.tabla.getTableHeader().setReorderingAllowed(false);
        
        this.tabla.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
            }
        });
    }
    
    public void updateFilter() throws Exception{
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        
        Date inicio = this.primerDiaDelMes(date);
        Date fin = this.ultimoDiaDelMes(date);
        
        this.fechaDesde.setDate(inicio);
        this.fechaHasta.setDate(fin);
        
        this.profesionales.setSelectedIndex(0);
        
        String fecha1 = getFechaString(inicio);
        String fecha2 = getFechaString(fin);
        
        ArrayList<Pago> pagos = PagoDB.listarPagos(-1, fecha1, fecha2);
        this.updateModelo(pagos);
    }
    
    private String getFechaString(Date date){
        
        String fechaNacimiento = (date.getYear()+1900) + "-";
        
        if((date.getMonth()+1)<9){
            fechaNacimiento = fechaNacimiento + "0";
        }
        
        fechaNacimiento = fechaNacimiento +(date.getMonth()+1)+"-";
        
        if(date.getDate()<9){
            fechaNacimiento = fechaNacimiento + "0";
        }
        
        fechaNacimiento = fechaNacimiento + date.getDate();
        
        return fechaNacimiento;
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
            
            PlanTratamiento plan = PlanTratamientoDB.getPlanTratamiento(pago.getIdPlanTratamiento());
            
            Usuario profes = Autenticacion.getUsuario(plan.getIdProfesional());
            
            String nombre = profes.getNombre();
        
            if(nombre.contains(" ")){
                nombre = nombre.substring(0,nombre.indexOf(" "));
            }
            
            nombre = nombre+" "+profes.getApellido_pat();

            Object [] fila = new Object [] {new Item(fechaBitacora, pago.getIdPago()), nombre, tipoPago, detalle, valor,
                                            numBoleta, lab};
            
            this.filas[i] = fila;
            i++;
        }
        
        this.modelo = new DefaultTableModel(this.filas, this.columnasNombre) {
            Class[] types = new Class [] {
                Item.class, String.class, String.class, String.class, String.class, String.class, Boolean.class
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
                .addComponent(this.labelProfesionales, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.profesionales, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(this.desde, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addComponent(this.fechaDesde, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(this.hasta, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addComponent(this.fechaHasta, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(this.imprimir)
                .addContainerGap())
        );
        
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.labelProfesionales, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.profesionales)
                    .addComponent(this.desde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.fechaDesde)
                    .addComponent(this.hasta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.fechaHasta)
                    .addComponent(this.imprimir))
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.imprimir){
            //ACA TIENES QUE AGREGAR EL CODIGO ASTROZA!!
        }
    }
    
    public void fechaDesdePropertyChange(PropertyChangeEvent evt) {
        Date date1 = fechaDesde.getDate();
        Date date2 = fechaHasta.getDate();
        String fecha1 = getFechaString(date1);
        String fecha2 = getFechaString(date2);

        int id = ((Item)this.profesionales.getSelectedItem()).getId();

        ArrayList<Pago> pagos = PagoDB.listarPagos(id, fecha1, fecha2);
        try {
            updateModelo(pagos);
        } catch (Exception ex) {
        }
    }
    
    public void fechaHastaPropertyChange(PropertyChangeEvent evt) {
        Date date1 = fechaDesde.getDate();
        Date date2 = fechaHasta.getDate();
        String fecha1 = getFechaString(date1);
        String fecha2 = getFechaString(date2);
        
        int id = ((Item)this.profesionales.getSelectedItem()).getId();

        ArrayList<Pago> pagos = PagoDB.listarPagos(id, fecha1, fecha2);
        try {
            updateModelo(pagos);
        } catch (Exception ex) {
        }
    }
    
    public Date primerDiaDelMes(Date dia){
        Date primero = null;
        Calendar c = Calendar.getInstance();
        c.setTime(dia);
        int mes = c.get(Calendar.MONTH);
        int año = c.get(Calendar.YEAR);
        
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, año);
        cal.set(Calendar.MONTH, mes);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        primero = new Date(cal.getTimeInMillis());
        return primero;
    }
    
    public Date ultimoDiaDelMes(Date dia){
        Date ultimo = null;
        Calendar c = Calendar.getInstance();
        c.setTime(dia);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        ultimo = new Date(c.getTimeInMillis());
        return ultimo;
    }
    
}
