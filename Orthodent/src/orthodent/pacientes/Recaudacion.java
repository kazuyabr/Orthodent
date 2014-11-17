/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.pacientes;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import modelo.FichaEvolucion;
import modelo.Pago;
import modelo.Paciente;
import modelo.PlanTratamiento;
import modelo.Rol;
import modelo.Tratamiento;
import modelo.Usuario;
import orthodent.Item;
import orthodent.JVentana;
import orthodent.db.Autenticacion;
import orthodent.db.FichaEvolucionDB;
import orthodent.db.PagoDB;
import orthodent.db.PlanTratamientoDB;
import orthodent.db.RolDB;
import orthodent.db.TratamientoDB;

/**
 *
 * @author Msanhuezal
 */
public class Recaudacion extends JPanel{

    private Usuario actual;
    private Paciente paciente;
    private boolean cambios;
    private String [] columnasPlanesTratamiento;
    private Object [][] filasPlanesTratamiento;
    private TableModel modeloPlanesTratamiento;
    private String [] columnasNombrePagoAbono;
    private Object [][] filasPagoAbono;
    private DefaultTableModel modeloPagoAbono;
    private String profesionalSelected;
    private String estadoSelected;
    private Paciente pacienteSelected;
    private PlanTratamiento tratamientotoSelected;
    private Pago pagoAbonoSelected;
    private ArrayList<Tratamiento> auxiliar;
    private int rowSelected;
    private boolean nuevoPresupuestoSel;
    
    public Recaudacion(Paciente paciente, Usuario actual) throws Exception {
        initComponents();
        this.setCursor();
        
        this.paciente = paciente;
        this.actual = actual;
        this.cambios = false;
        this.nuevoPresupuestoSel = false;
        
        this.addInfo();
        this.tablaTratamiento.getTableHeader().setReorderingAllowed(false);
        this.tablaPagoAbono.getTableHeader().setReorderingAllowed(false);
    }
    
    private void setCursor(){
        this.add.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.remove.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    public boolean getCambios(){
        return this.cambios;
    }
    
    public void setCambios(boolean cambios){
        this.cambios = cambios;
    }
    
    private void addInfo() throws Exception{
        this.iniciarTablaPlanesTratamientos();
    }
    
    public void iniciarTablaPagoAbono() throws Exception{
        this.columnasNombrePagoAbono = new String [] {"Fecha", "Cantidad"};
        //this.updateTablaPiezaTratamiento();
        this.tablaPagoAbono.getTableHeader().setReorderingAllowed(false);
        
        this.tablaPagoAbono.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                Object [] fila = getRowAt2(row);
                if (me.getClickCount() == 1) { 
                    try {
                        pagoAbonoSelected = PagoDB.getPago(((Item)fila[1]).getId());
                        if(pagoAbonoSelected != null){
                            habilitarBtnRemove();
                        }

                    } catch (Exception ex) {
                    }
                }
                else if(me.getClickCount() == 2){
                    try {
                        pagoAbonoSelected = PagoDB.getPago(((Item)fila[1]).getId());
                        if(pagoAbonoSelected != null){
                            habilitarBtnRemove();
                            editarPagoAbono();
                        }                        
                    } catch (Exception ex) {
                        Logger.getLogger(Recaudacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    public void editarPagoAbono(){
        //System.out.println("problem");
        new VentanaAbono(((JVentana)this.getTopLevelAncestor()),true, 
                tratamientotoSelected.getIdPlanTratamiento(), this, false, pagoAbonoSelected).setVisible(true);
    }
    
    public void updateTablaPagoAbono() throws Exception{

        ArrayList<Pago> pagoAbono = new ArrayList<Pago>();

        pagoAbono = PagoDB.listarPagosDePlanTratamiento(tratamientotoSelected.getIdPlanTratamiento());

        int m = this.columnasNombrePagoAbono.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        int total=0;
        for(Pago pagAbn : pagoAbono){
            String fecha = girarFecha(pagAbn.getFecha());
            String descripcion = pagAbn.getAbono()+"";
            total = total + pagAbn.getAbono();
            Object [] fila = new Object [] {fecha, 
                new Item(descripcion,pagAbn.getIdPago()), "hora", "fecha"};

            objetos.add(fila);
        }
        this.filasPagoAbono = new Object [objetos.size()][m];
        int i = 0;
        for(Object [] o : objetos){
            this.filasPagoAbono[i] = o;
            i++;
        }
        
        this.modeloPagoAbono = new DefaultTableModel(this.filasPagoAbono, this.columnasNombrePagoAbono) {
            Class[] types = new Class [] {
                String.class, Item.class, String.class, String.class
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
        
        costoAbono.setText("$"+total);
        int id = tratamientotoSelected.getIdPlanTratamiento();
        PlanTratamiento planTrat = PlanTratamientoDB.getPlanTratamiento(id);
        planTrat.setTotalAbonos(total);
        PlanTratamientoDB.editarPlanTratamiento(planTrat);
        //System.out.println("total 2:"+total+"id:"+id);
        this.tablaPagoAbono.setModel(modeloPagoAbono);
        this.iniciarTablaPlanesTratamientos();
    }
    
    public void habilitarBtnAdd(){
        this.add.setEnabled(true);
        //this.remove.setEnabled(true);
    }
    
    public void habilitarBtnRemove(){
        this.remove.setEnabled(true);
    }  
    
    public void deshabilitarBtnRemove(){
        this.remove.setEnabled(false);
    }
    
     public void iniciarTablaPlanesTratamientos() throws Exception{
        
        this.columnasPlanesTratamiento = new String [] {"Profesional",  "Costo Total", "Total Abonos", "Avance"};
        this.updateTablaPlanesTratamientos();
        this.tablaTratamiento.getTableHeader().setReorderingAllowed(false);//paque no se menee papi! la columna
        
        this.tablaTratamiento.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 1) { 
                    Object [] fila = getRowAt(row);
                    try {
                        habilitarBtnAdd();
                        deshabilitarBtnRemove();
                        
                        tratamientotoSelected = PlanTratamientoDB.getPlanTratamiento(((Item)fila[1]).getId());
                                               
                        if(tratamientotoSelected!=null){
                            tablaPagoAbono.setEnabled(true);
                            iniciarTablaPagoAbono();
                            updateTablaPagoAbono();
                        }
                        costoTotal.setText("$"+tratamientotoSelected.getCostoTotal());
                    } catch (Exception ex) {
                    }
                }
            }
        });
    }
    
    private Object[] getRowAt(int row) {
        Object[] result = new Object[this.columnasPlanesTratamiento.length];
        
        for (int i = 0; i < this.columnasPlanesTratamiento.length; i++) {
            result[i] = this.tablaTratamiento.getModel().getValueAt(row, i);
            
        }   
        
        return result;
    }
    
    private Object[] getRowAt2(int row) {
        Object[] result = new Object[this.columnasNombrePagoAbono.length];
        
        for (int i = 0; i < this.columnasNombrePagoAbono.length; i++) {
            result[i] = this.tablaPagoAbono.getModel().getValueAt(row, i);
            
        }   
        
        return result;
    }    
    
    public void updateTablaPlanesTratamientos() throws Exception{
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        ArrayList<PlanTratamiento> tratamientos = null;
        
        if(this.actual.getId_rol()==3){
            //Profesional
            tratamientos = PlanTratamientoDB.listarPlanesTratamientoPaciente(paciente.getId_paciente(),actual.getId_usuario());
        }
        else{
            tratamientos = PlanTratamientoDB.listarPlanesTratamientoPaciente(paciente.getId_paciente());
        }
        
        int m = this.columnasPlanesTratamiento.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(PlanTratamiento tratamiento : tratamientos){
            if(tratamiento.isActivo()){
                
                Usuario profesional1 = Autenticacion.getUsuario(tratamiento.getIdProfesional());
                
                String nombre = profesional1.getNombre();
        
                if(nombre.contains(" ")){
                    nombre = nombre.substring(0,nombre.indexOf(" "));
                }
                
                nombre = nombre + " " + profesional1.getApellido_pat();
                
                String costoTotal = this.getMoneda(tratamiento.getCostoTotal());
                String abonos = this.getMoneda(tratamiento.getTotalAbonos());
                String avance = tratamiento.getAvance()+"%";
                
                Object [] fila = new Object [] {new Item(nombre, profesional1.getId_usuario()), new Item(costoTotal+"",tratamiento.getIdPlanTratamiento()), abonos, avance};
                
                objetos.add(fila);
            }
        }
        
        this.filasPlanesTratamiento = new Object [objetos.size()][m];
        int i = 0;
        for(Object [] o : objetos){
            this.filasPlanesTratamiento[i] = o;
            i++;
        }
        
        this.modeloPlanesTratamiento = new DefaultTableModel(this.filasPlanesTratamiento, this.columnasPlanesTratamiento) {
            Class[] types = new Class [] {
                Item.class, Item.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        this.tablaTratamiento.setModel(modeloPlanesTratamiento);
    }
    
    private String getMoneda(int costo){
        
        String costoTotal = "$" + costo;
        return costoTotal;
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        panelTitulo = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTratamiento = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        labelProfesional = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPagoAbono = new javax.swing.JTable();
        add = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        costoAbono = new javax.swing.JTextField();
        labelAbono = new javax.swing.JLabel();
        costoTotal = new javax.swing.JTextField();
        labelTotal = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(850, 551));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setPreferredSize(new java.awt.Dimension(845, 545));

        panelTitulo.setBackground(new java.awt.Color(247, 243, 248));
        panelTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(232, 230, 234), 1, true));

        labelTitulo.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(163, 159, 164));
        labelTitulo.setText("Recaudaciones de los Pago");

        javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
        panelTitulo.setLayout(panelTituloLayout);
        panelTituloLayout.setHorizontalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaTratamiento.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        tablaTratamiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Profesional", "Costo Total", "Total Abonos", "Avance"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaTratamiento);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        labelProfesional.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelProfesional.setText("Abonos");

        tablaPagoAbono.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        tablaPagoAbono.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaPagoAbono.setEnabled(false);
        jScrollPane2.setViewportView(tablaPagoAbono);

        add.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_mini.png"))); // NOI18N
        add.setText("Nueva Ficha Clínica");
        add.setBorder(null);
        add.setBorderPainted(false);
        add.setContentAreaFilled(false);
        add.setEnabled(false);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete_mini.png"))); // NOI18N
        remove.setBorder(null);
        remove.setBorderPainted(false);
        remove.setContentAreaFilled(false);
        remove.setEnabled(false);
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });

        costoAbono.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        costoAbono.setEnabled(false);
        costoAbono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costoAbonoActionPerformed(evt);
            }
        });

        labelAbono.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelAbono.setText("Abonos");

        costoTotal.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        costoTotal.setEnabled(false);
        costoTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costoTotalActionPerformed(evt);
            }
        });

        labelTotal.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelTotal.setText("Total");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(labelProfesional)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(add))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(labelTotal)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(labelAbono)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(costoAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(remove)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelProfesional)
                            .addComponent(add))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(remove))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(costoAbono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelAbono)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTotal))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private String getCurrentDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        
        String fecha = "";
        
        if(date.getDate()<9){
            fecha = fecha + "0";
        }
        fecha = fecha + date.getDate() + "-";
        
        if((date.getMonth()+1)<9){
            fecha = fecha + "0";
        }
        fecha = fecha + (date.getMonth()+1) + "-";
        fecha = fecha + (date.getYear()+1900) + " ";
        
        if(date.getHours()<9){
            fecha = fecha + "0";
        }
        fecha = fecha + date.getHours() + ":";
        
        if(date.getMinutes()<9){
            fecha = fecha + "0";
        }
        fecha = fecha + date.getMinutes() + ":";
        
        if(date.getSeconds()<9){
            fecha = fecha + "0";
        }
        fecha = fecha + date.getSeconds();
        
        return fecha;
    }
        
    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        if(pagoAbonoSelected != null){
            Object[] options = {"Sí","No"};

                    int n = JOptionPane.showOptionDialog(this,
                                "¿Esta seguro que desea eliminar la ficha clínica?",
                                "Orthodent",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[1]);
            if(n==0){
                try {
                    boolean respuesta = PagoDB.eliminarPago(pagoAbonoSelected.getIdPago());
                    updateModeloPagoAbono();
                    this.jPanel1.updateUI();
                }
                catch (SQLException ex){
                    Logger.getLogger(Recaudacion.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Recaudacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_removeActionPerformed

        public void updateModeloPagoAbono() throws Exception{
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        ArrayList<Pago> pgAbono = PagoDB.listarPagosDePlanTratamiento(tratamientotoSelected.getIdPlanTratamiento());
        
        
        int m = this.columnasNombrePagoAbono.length;
        int total=0;
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        for(Pago pgAbn : pgAbono){
            String fecha = girarFecha(pgAbn.getFecha());
            String descripcion = pgAbn.getAbono()+"";
            total = total + pgAbn.getAbono();
            Object [] fila = new Object [] {fecha, 
                new Item(descripcion,pgAbn.getIdPago()), "hora", "fecha"};

            objetos.add(fila);
        }
        costoAbono.setText("$"+total);
        int id = tratamientotoSelected.getIdPlanTratamiento();
        PlanTratamiento planTrat = PlanTratamientoDB.getPlanTratamiento(id);
        planTrat.setTotalAbonos(total);
        PlanTratamientoDB.editarPlanTratamiento(planTrat);
        //System.out.println("total 1:"+total+"id:"+id);
        
        this.filasPagoAbono = new Object [objetos.size()][m];
        int i = 0;
        for(Object [] o : objetos){
            this.filasPagoAbono[i] = o;
            i++;
        }
        
        this.modeloPagoAbono = new DefaultTableModel(this.filasPagoAbono, this.columnasNombrePagoAbono) {
            Class[] types = new Class [] {
                String.class, Item.class, String.class, String.class
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
        
        this.tablaPagoAbono.setModel(modeloPagoAbono);
      
        this.iniciarTablaPlanesTratamientos();
        //habilitarBoton();
    }
    
    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
      new VentanaAbono(((JVentana)this.getTopLevelAncestor()),true, 
              tratamientotoSelected.getIdPlanTratamiento(), this, true, pagoAbonoSelected).setVisible(true);
    }//GEN-LAST:event_addActionPerformed

    private void costoAbonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costoAbonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_costoAbonoActionPerformed

    private void costoTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costoTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_costoTotalActionPerformed
//     public void guardar(){
//        this.guardarActionPerformed(null);
//    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JTextField costoAbono;
    private javax.swing.JTextField costoTotal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelAbono;
    private javax.swing.JLabel labelProfesional;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JButton remove;
    private javax.swing.JTable tablaPagoAbono;
    private javax.swing.JTable tablaTratamiento;
    // End of variables declaration//GEN-END:variables
}
