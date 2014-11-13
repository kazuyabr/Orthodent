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
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import modelo.Paciente;
import modelo.PlanTratamiento;
import modelo.Presupuesto;
import modelo.Tratamiento;
import modelo.TratamientoPiezaPresupuesto;
import modelo.Usuario;
import orthodent.Item;
import orthodent.db.Autenticacion;
import orthodent.db.PlanTratamientoDB;
import orthodent.db.PresupuestoDB;
import orthodent.db.TratamientoDB;
import orthodent.db.TratamientoPiezaPlanDB;
import orthodent.db.TratamientoPiezaPresupuestoDB;

/**
 *
 * @author Mary
 */
public class FichasClinicas extends JPanel{

    private Usuario actual;
    private Paciente paciente;
    private boolean cambios;
    private String [] columnasPlanesTratamiento;
    private Object [][] filasPlanesTratamiento;
    private TableModel modeloPlanesTratamiento;
    private String [] columnasNombrePiezaTratamiento;
    private Object [][] filasPiezaTratamiento;
    private DefaultTableModel modeloPiezaTratamiento;
    private String profesionalSelected;
    private String estadoSelected;
    private Paciente pacienteSelected;
    private PlanTratamiento tratamientotoSelected;
    private ArrayList<Tratamiento> auxiliar;
    private int rowSelected;
    private boolean nuevoPresupuestoSel;
    
    public FichasClinicas(Paciente paciente, Usuario actual) throws Exception {
        initComponents();
        this.setCursor();
        
        this.paciente = paciente;
        this.actual = actual;
        this.cambios = false;
        this.nuevoPresupuestoSel = false;
        
        this.addInfo();
        this.guardar.setEnabled(false);
        this.tablaTratamiento.getTableHeader().setReorderingAllowed(false);
        this.tablaFichaEvolucion.getTableHeader().setReorderingAllowed(false);
    }
    
    private void setCursor(){
        //this.nuevoPresupuesto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.eliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.aprobar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.guardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
    
    public void iniciarTablaPiezaTratamiento() throws Exception{
        this.columnasNombrePiezaTratamiento = new String [] {"Pieza", "Tratamiento", "Valor Colegio O.", "Valor Orthodent"};
        //this.updateTablaPiezaTratamiento();
        this.tablaFichaEvolucion.getTableHeader().setReorderingAllowed(false);
        
        this.tablaFichaEvolucion.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 1) {
                    int col = table.columnAtPoint(p);
                    if(col==1){
                        rowSelected = row;
                    }
                }
                if (me.getClickCount() == 2) {
                    habilitarBoton();
                }
            }
        });
    }
    
    public void updateTablaPiezaTratamiento() throws Exception{
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        TratamientoPiezaPresupuesto t1 = new TratamientoPiezaPresupuesto(1, 2, 3);
        TratamientoPiezaPresupuesto t2 = new TratamientoPiezaPresupuesto(1, 2, 3);
        TratamientoPiezaPresupuesto t3 = new TratamientoPiezaPresupuesto(1, 2, 3);
        ArrayList<TratamientoPiezaPresupuesto> piezasPresupuesto = new ArrayList<TratamientoPiezaPresupuesto>();
        piezasPresupuesto.add(t1);
        piezasPresupuesto.add(t2);
        piezasPresupuesto.add(t3);
        if(!this.nuevoPresupuestoSel){
             //piezasPresupuesto = TratamientoPiezaPresupuestoDB.listarTratamientosPiezaPresupuesto(this.tratamientotoSelected.getIdPresupuesto());
            //piezasPresupuesto = null;
        }
        int m = this.columnasNombrePiezaTratamiento.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(TratamientoPiezaPresupuesto piezaPresupuesto : piezasPresupuesto){
            String pieza = piezaPresupuesto.getPieza()+"";
            Tratamiento tratamiento = TratamientoDB.getTratamiento(piezaPresupuesto.getId_tratamiento());
            Object [] fila = new Object [] {pieza, 
                new Item(tratamiento.getNombre(),tratamiento.getIdTratamiento()), "$"+tratamiento.getValorColegio(), "$"+tratamiento.getValorClinica()};

            objetos.add(fila);
        }
        this.filasPiezaTratamiento = new Object [objetos.size()][m];
        int i = 0;
        for(Object [] o : objetos){
            this.filasPiezaTratamiento[i] = o;
            i++;
        }
        
        this.modeloPiezaTratamiento = new DefaultTableModel(this.filasPiezaTratamiento, this.columnasNombrePiezaTratamiento) {
            Class[] types = new Class [] {
                String.class, Item.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        this.tablaFichaEvolucion.setModel(modeloPiezaTratamiento);
        
        TableColumn tratamientos = tablaFichaEvolucion.getColumnModel().getColumn(1);
        JComboBox comboBox = new JComboBox(){
            public void fireItemStateChanged(ItemEvent evt){
                for(Tratamiento trat : auxiliar){
                    if(trat.getNombre().equals(((Item)evt.getItem()).getValue())){
                        modeloPiezaTratamiento.setValueAt("$"+trat.getValorColegio(), rowSelected, 2);
                        modeloPiezaTratamiento.setValueAt("$"+trat.getValorClinica(), rowSelected, 3);
                        
                        int total = 0;
                        for(int i=0; i<modeloPiezaTratamiento.getRowCount(); i++){
                            String valor = (String) modeloPiezaTratamiento.getValueAt(i, 3);
                            valor = valor.substring(valor.indexOf("$")+1, valor.length());
                            int precio = Integer.parseInt(valor);
                            total = total + precio;
                        }
                        
                        //costoTotal.setText("$"+total);
                        habilitarBoton();
                    }
                }
            }
        };
        
        Vector model = new Vector();
        this.auxiliar = TratamientoDB.listarTratamientos();
        if(auxiliar!=null && auxiliar.size()>0){
            for(Tratamiento trat : auxiliar){
                model.addElement(new Item(trat.getNombre(), trat.getIdTratamiento()));
            }
        }
        comboBox.setModel(new DefaultComboBoxModel(model));
        tratamientos.setCellEditor(new DefaultCellEditor(comboBox));
    }
    
    public void habilitarBtnAddRemove(){
        this.add.setEnabled(true);
        this.remove.setEnabled(true);
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
                if (me.getClickCount() == 1) { // cuando te toco suave!! 
                    Object [] fila = getRowAt(row);
                    try {
                        System.out.println(((Item)fila[1]));
                        habilitarBtnAddRemove();
                                
                        
                        //tratamientotoSelected = PlanTratamientoDB.getPlanTratamiento(((Item)fila[1]).getId());
                                               
                        if(tratamientotoSelected!=null){
                            
                            tablaFichaEvolucion.setEnabled(true);
                            iniciarTablaPiezaTratamiento();
                            guardar.setEnabled(false);
//                            Usuario profesionalNombre = Autenticacion.getUsuario(tratamientotoSelected.getIdProfesional());
//                            
//                            String nombre = profesionalNombre.getNombre();
//                            if(nombre.contains(" ")){
//                                nombre = nombre.substring(0,nombre.indexOf(" "));
//                            }
                            
//                            profesional.setText(nombre+" "+profesionalNombre.getApellido_pat());
//                            profesional.setEditable(false);
          
                            /*
                            estado.setModel(new DefaultComboBoxModel(new String [] {"Activo","Cancelado"}));
                            estado.setEnabled(true);
                            if(presupuestoSelected.getEstado()){
                                estadoSelected = "Activo";
                                estado.setSelectedItem("Activo");
                            }
                            else{
                                estadoSelected = "Cancelado";
                                estado.setSelectedItem("Cancelado");
                            }
                            
                            fechaCreacion.setText(presupuestoSelected.getFechaCreacion());
                            fechaUltimaModificacion.setText(presupuestoSelected.getFechaModificacion());
                            
                            tablaPiezaTratamiento.setEnabled(true);
                            iniciarTablaPiezaTratamiento();
                            guardar.setEnabled(false);*/
                        }
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
        guardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTratamiento = new javax.swing.JTable();
        eliminar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelProfesional = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaFichaEvolucion = new javax.swing.JTable();
        add = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        aprobar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(850, 551));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setPreferredSize(new java.awt.Dimension(845, 545));

        panelTitulo.setBackground(new java.awt.Color(247, 243, 248));
        panelTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(232, 230, 234), 1, true));

        labelTitulo.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(163, 159, 164));
        labelTitulo.setText("Fichas Clinicas");

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

        guardar.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        guardar.setText("Guardar Cambios");
        guardar.setEnabled(false);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

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

        eliminar.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        eliminar.setText("Eliminar");
        eliminar.setEnabled(false);
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        labelProfesional.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelProfesional.setText("Ficha Clinica");

        tablaFichaEvolucion.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        tablaFichaEvolucion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaFichaEvolucion.setEnabled(false);
        jScrollPane2.setViewportView(tablaFichaEvolucion);

        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_mini.png"))); // NOI18N
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

        jLabel1.setText("Agregar Ficha Clínica");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelProfesional)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(add))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(remove)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelProfesional)
                    .addComponent(add)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(remove))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        aprobar.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        aprobar.setText("Aprobar");
        aprobar.setEnabled(false);
        aprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aprobarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aprobar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guardar))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eliminar)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(guardar)
                        .addComponent(aprobar)))
                .addContainerGap(27, Short.MAX_VALUE))
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

    public void guardar(){
        this.guardarActionPerformed(null);
    }
    
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
    
    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        
//        if(this.tratamientotoSelected!=null){
//            int  id_profesional = ((Item)this.profesional.getSelectedItem()).getId();
//            
//            boolean estado = false;
//            if(((String)this.estado.getSelectedItem()).equals("Activo")){
//                estado = true;
//            }
//            else{
//                estado = false;
//            }
//            
//            int costoTotal = 0;
//            if(!this.costoTotal.getText().equals("$")){
//                costoTotal = Integer.parseInt(this.costoTotal.getText().substring(this.costoTotal.getText().indexOf("$")+1, this.costoTotal.getText().length()));
//            }
//            
//            int cantidadTratamientos = this.tablaPiezaTratamiento.getRowCount();
//            
//            String fechaModificacion = this.getCurrentDateTime();
//            
//            this.tratamientotoSelected.setIdProfesional(id_profesional);
//            this.tratamientotoSelected.setEstado(estado);
//            this.tratamientotoSelected.setCostoTotal(costoTotal);
//            this.tratamientotoSelected.setCantidadTratamiento(cantidadTratamientos);
//            this.tratamientotoSelected.setFechaModificacion(fechaModificacion);
//            
//            boolean respuesta = PresupuestoDB.editarPresupuesto(tratamientotoSelected);
//            if(respuesta){
//                
//                boolean error = false;
//                for(int i=0; i<this.tablaPiezaTratamiento.getRowCount(); i++){ //recorro las filas
//                    try {
//                        int pieza = Integer.parseInt((String) this.tablaPiezaTratamiento.getValueAt(i, 0));
//                        
//                        if(this.tablaPiezaTratamiento.getValueAt(i, 1)==null){
//                            JOptionPane.showMessageDialog(this,
//                                "Hay cambios en la tabla sin completar!",
//                                "Orthodent",
//                                JOptionPane.INFORMATION_MESSAGE);
//                            error = true;
//                            break;
//                        }
//                        else{
//                            if(!(this.tablaPiezaTratamiento.getValueAt(i, 1) instanceof Item)){
//                                JOptionPane.showMessageDialog(this,
//                                    "Hay cambios en la tabla sin completar!",
//                                    "Orthodent",
//                                    JOptionPane.INFORMATION_MESSAGE);
//                                error = true;
//                                break;
//                            }
//                        }
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(this,
//                            "Hay cambios en la tabla sin completar!",
//                            "Orthodent",
//                            JOptionPane.INFORMATION_MESSAGE);
//                        error = true;
//                        break;
//                    }
//                }
//                
//                if(!error){
//                    try {
//                        boolean resp = TratamientoPiezaPresupuestoDB.eliminarTratamientoPieza(tratamientotoSelected.getIdPresupuesto());
//
//                        if(resp){
//                            for(int i=0; i<this.tablaPiezaTratamiento.getRowCount(); i++){ //recorro las filas
//                                int pieza = Integer.parseInt((String)this.tablaPiezaTratamiento.getValueAt(i, 0));
//                                int id_tratamiento = ((Item)this.tablaPiezaTratamiento.getValueAt(i, 1)).getId();
//                                TratamientoPiezaPresupuestoDB.crearTratamientoPiezaPresupuesto(id_tratamiento, this.tratamientotoSelected.getIdPresupuesto(), pieza);
//                            }
//                            try {
//                                this.updateTablaPresupuestos();
//                            } catch (Exception ex) {
//                            }
//                            this.cambios = false;
//                            this.guardar.setEnabled(false);
//                        }
//                    } catch (SQLException ex) {
//                    }
//                }
//            }
//        }
//        else{
//            //Nuevo Presupuesto
//            int  id_profesional = ((Item)this.profesional.getSelectedItem()).getId();
//            
//            boolean estado = false;
//            if(((String)this.estado.getSelectedItem()).equals("Activo")){
//                estado = true;
//            }
//            else{
//                estado = false;
//            }
//            
//            int costoTotal = 0;
//            if(!this.costoTotal.getText().equals("$")){
//                costoTotal = Integer.parseInt(this.costoTotal.getText().substring(this.costoTotal.getText().indexOf("$")+1, this.costoTotal.getText().length()));
//            }
//            
//            int cantidadTratamientos = this.tablaPiezaTratamiento.getRowCount();
//            
//            String fechaModificacion = this.getCurrentDateTime();
//            
//            boolean respuesta = PresupuestoDB.crearPresupuesto(this.paciente.getId_paciente(), id_profesional, estado,
//                    costoTotal, cantidadTratamientos, true, fechaModificacion, fechaModificacion);
//            
//            if(respuesta){
//                
//                boolean error = false;
//                for(int i=0; i<this.tablaPiezaTratamiento.getRowCount(); i++){ //recorro las filas
//                    try {
//                        int pieza = Integer.parseInt((String) this.tablaPiezaTratamiento.getValueAt(i, 0));
//                        
//                        if(this.tablaPiezaTratamiento.getValueAt(i, 1)==null){
//                            JOptionPane.showMessageDialog(this,
//                                "Hay cambios en la tabla sin completar!",
//                                "Orthodent",
//                                JOptionPane.INFORMATION_MESSAGE);
//                            error = true;
//                            break;
//                        }
//                        else{
//                            if(!(this.tablaPiezaTratamiento.getValueAt(i, 1) instanceof Item)){
//                                JOptionPane.showMessageDialog(this,
//                                    "Hay cambios en la tabla sin completar!",
//                                    "Orthodent",
//                                    JOptionPane.INFORMATION_MESSAGE);
//                                error = true;
//                                break;
//                            }
//                        }
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(this,
//                            "Hay cambios en la tabla sin completar!",
//                            "Orthodent",
//                            JOptionPane.INFORMATION_MESSAGE);
//                        error = true;
//                        break;
//                    }
//                }
//                
//                if(!error){
//                    try {
//                        Presupuesto pre = PresupuestoDB.getPresupuesto(fechaModificacion, this.paciente.getId_paciente());
//                        
//                        for(int i=0; i<this.tablaPiezaTratamiento.getRowCount(); i++){ //recorro las filas
//                            int pieza = Integer.parseInt((String)this.tablaPiezaTratamiento.getValueAt(i, 0));
//                            int id_tratamiento = ((Item)this.tablaPiezaTratamiento.getValueAt(i, 1)).getId();
//                            TratamientoPiezaPresupuestoDB.crearTratamientoPiezaPresupuesto(id_tratamiento, pre.getIdPresupuesto(), pieza);
//                        }
//                        try {
//                            this.updateTablaPresupuestos();
//                        } catch (Exception ex) {
//                        }
//                        this.cambios = false;
//                        this.nuevoPresupuestoSel = false;
//                        this.guardar.setEnabled(false);
//                    } catch (Exception ex) {
//                    }
//                }
//            }
//        }
    }//GEN-LAST:event_guardarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
//        Object[] options = {"Sí","No"};
//        
//        int n = JOptionPane.showOptionDialog(this,
//                    "¿Esta seguro que desea eliminar el presupuesto?",
//                    "Orthodent",
//                    JOptionPane.YES_NO_CANCEL_OPTION,
//                    JOptionPane.QUESTION_MESSAGE,
//                    null,
//                    options,
//                    options[1]);
//        
//        if(n==0){
//            try {
//                boolean resul = PresupuestoDB.eliminarPresupuesto(this.tratamientotoSelected.getIdPresupuesto());
//                if(resul){
//                    try {
//                        this.updateTablaPresupuestos();
//                        this.profesional.setEnabled(false);
//                        this.profesionalSelected = null;
//                        this.profesional.setModel(new DefaultComboBoxModel(new String [] {""}));
//                        this.profesional.setSelectedItem("");
//                        this.costoTotal.setEnabled(false);
//                        this.costoTotal.setText("");
//                        this.estadoSelected = null;
//                        this.estado.setModel(new DefaultComboBoxModel(new String [] {""}));
//                        this.estado.setSelectedItem("");
//                        this.estado.setEnabled(false);
//                        this.fechaCreacion.setText("");
//                        this.fechaUltimaModificacion.setText("");
//                        this.tratamientotoSelected = null;
//                        this.eliminar.setEnabled(false);
//                        this.aprobar.setEnabled(false);
//                        this.guardar.setEnabled(false);
//                        this.tablaPiezaTratamiento.setEnabled(false);
//                        this.remove.setEnabled(false);
//                        this.add.setEnabled(false);
//                    } catch (Exception ex) {
//                    }
//                }
//            } catch (SQLException ex) {
//            }
//        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void aprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aprobarActionPerformed
        
//        //asd
//        
//        if(!this.nuevoPresupuestoSel){
//            int  id_profesional = ((Item)this.profesional.getSelectedItem()).getId();
//            
//            boolean estado = false;
//            if(((String)this.estado.getSelectedItem()).equals("Activo")){
//                estado = true;
//            }
//            else{
//                estado = false;
//            }
//
//            int costoTotal = 0;
//            if(!this.costoTotal.getText().equals("$")){
//                costoTotal = Integer.parseInt(this.costoTotal.getText().substring(this.costoTotal.getText().indexOf("$")+1, this.costoTotal.getText().length()));
//            }
//
//            int cantidadTratamientos = this.tablaPiezaTratamiento.getRowCount();
//
//            String fechaModificacion = this.getCurrentDateTime();
//
//            this.tratamientotoSelected.setIdProfesional(id_profesional);
//            this.tratamientotoSelected.setEstado(estado);
//            this.tratamientotoSelected.setCostoTotal(costoTotal);
//            this.tratamientotoSelected.setCantidadTratamiento(cantidadTratamientos);
//            this.tratamientotoSelected.setFechaModificacion(fechaModificacion);
//            
//            boolean respuesta = PlanTratamientoDB.crearPlanTratamiento(this.paciente.getId_paciente(), id_profesional, this.tratamientotoSelected.getFechaCreacion(),
//                    fechaModificacion, costoTotal, 0, 0, true, fechaModificacion, fechaModificacion);
//            
//            if(respuesta){
//                
//                boolean error = false;
//                for(int i=0; i<this.tablaPiezaTratamiento.getRowCount(); i++){ //recorro las filas
//                    try {
//                        int pieza = Integer.parseInt((String) this.tablaPiezaTratamiento.getValueAt(i, 0));
//                        
//                        if(this.tablaPiezaTratamiento.getValueAt(i, 1)==null){
//                            JOptionPane.showMessageDialog(this,
//                                "Hay cambios en la tabla sin completar!",
//                                "Orthodent",
//                                JOptionPane.INFORMATION_MESSAGE);
//                            error = true;
//                            break;
//                        }
//                        else{
//                            if(!(this.tablaPiezaTratamiento.getValueAt(i, 1) instanceof Item)){
//                                JOptionPane.showMessageDialog(this,
//                                    "Hay cambios en la tabla sin completar!",
//                                    "Orthodent",
//                                    JOptionPane.INFORMATION_MESSAGE);
//                                error = true;
//                                break;
//                            }
//                        }
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(this,
//                            "Hay cambios en la tabla sin completar!",
//                            "Orthodent",
//                            JOptionPane.INFORMATION_MESSAGE);
//                        error = true;
//                        break;
//                    }
//                }
//                
//                if(!error){
//                    try {
//                        PlanTratamiento plan = PlanTratamientoDB.getPlanTratamiento(fechaModificacion, this.paciente.getId_paciente());
//                        
//                        for(int i=0; i<this.tablaPiezaTratamiento.getRowCount(); i++){ //recorro las filas
//                            int pieza = Integer.parseInt((String)this.tablaPiezaTratamiento.getValueAt(i, 0));
//                            int id_tratamiento = ((Item)this.tablaPiezaTratamiento.getValueAt(i, 1)).getId();
//                            
//                            TratamientoPiezaPlanDB.crearTratamientoPiezaPlan(plan.getIdPlanTratamiento(), id_tratamiento, pieza, false);
//                        }
//                        
//                        TratamientoPiezaPresupuestoDB.eliminarTratamientoPieza(this.tratamientotoSelected.getIdPresupuesto());
//                        
//                        PresupuestoDB.purgarPresupuesto(this.tratamientotoSelected.getIdPresupuesto());
//                        
//                        try {
//                            this.updateTablaPresupuestos();
//                            this.profesional.setEnabled(false);
//                            this.profesionalSelected = null;
//                            this.profesional.setModel(new DefaultComboBoxModel(new String [] {""}));
//                            this.profesional.setSelectedItem("");
//                            this.costoTotal.setEnabled(false);
//                            this.costoTotal.setText("");
//                            this.estadoSelected = null;
//                            this.estado.setModel(new DefaultComboBoxModel(new String [] {""}));
//                            this.estado.setSelectedItem("");
//                            this.estado.setEnabled(false);
//                            this.fechaCreacion.setText("");
//                            this.fechaUltimaModificacion.setText("");
//                            this.tratamientotoSelected = null;
//                            this.eliminar.setEnabled(false);
//                            this.aprobar.setEnabled(false);
//                            this.guardar.setEnabled(false);
//                            this.tablaPiezaTratamiento.setEnabled(false);
//                            this.remove.setEnabled(false);
//                            this.add.setEnabled(false);
//                        } catch (Exception ex) {
//                        }
//                    } catch (Exception ex) {
//                    }
//                }
//            }
//        }
//        
//        
        
    }//GEN-LAST:event_aprobarActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
//        int SelectedRow = this.tablaFichaEvolucion.getSelectedRow();
//        this.modeloPiezaTratamiento.removeRow(this.tablaFichaEvolucion.convertRowIndexToModel(SelectedRow));
//        
//        int total = 0;
//        for(int i=0; i<modeloPiezaTratamiento.getRowCount(); i++){
//            String valor = (String) modeloPiezaTratamiento.getValueAt(i, 3);
//            valor = valor.substring(valor.indexOf("$")+1, valor.length());
//            int precio = Integer.parseInt(valor);
//            total = total + precio;
//        }
//
//        //costoTotal.setText("$"+total);
//        habilitarBoton();
    }//GEN-LAST:event_removeActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        NuevaFichaEvaluacion nf = new NuevaFichaEvaluacion(null, cambios);
        nf.setVisible(true);
    }//GEN-LAST:event_addActionPerformed
    
    private void habilitarBoton(){
        this.cambios = true;
        this.guardar.setEnabled(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton aprobar;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelProfesional;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JButton remove;
    private javax.swing.JTable tablaFichaEvolucion;
    private javax.swing.JTable tablaTratamiento;
    // End of variables declaration//GEN-END:variables
}
