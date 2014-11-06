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
    private String [] columnasPlanTratamiento;
    private Object [][] filasPresupuesto;
    private TableModel modeloPresupuesto;
    private String [] columnasFichaClinica;
    private Object [][] filasPiezaTratamiento;
    private DefaultTableModel modeloPiezaTratamiento;
    private String profesionalSelected;
    private String estadoSelected;
    private Presupuesto presupuestoSelected;
    private ArrayList<Tratamiento> auxiliar;
    private int rowSelected;
    private boolean nuevoPlanTratamientoSelected;
    
    public FichasClinicas(Paciente paciente, Usuario actual) throws Exception {
        initComponents();
        this.setCursor();
        
        this.paciente = paciente;
        this.actual = actual;
        this.cambios = false;
        this.nuevoPlanTratamientoSelected = false;
        
        this.addInfo();
        this.guardar.setEnabled(false);
        this.tablaPlanTratamiento.getTableHeader().setReorderingAllowed(false);
        this.tablaFichaClinica.getTableHeader().setReorderingAllowed(false);
    }
    
    private void setCursor(){
        this.nuevoPresupuesto.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
        this.iniciarTablaPlanTratamiento();
    }
    
    public void iniciarTablaPiezaTratamiento() throws Exception{
        this.columnasFichaClinica = new String [] {"Fecha", "Descripción"};
        this.updateTablaPiezaTratamiento();
        this.tablaFichaClinica.getTableHeader().setReorderingAllowed(false);
        
        this.tablaFichaClinica.addMouseListener(new MouseAdapter() {
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
        ArrayList<TratamientoPiezaPresupuesto> piezasPresupuesto = new ArrayList<TratamientoPiezaPresupuesto>();
        if(!this.nuevoPlanTratamientoSelected){
             piezasPresupuesto = TratamientoPiezaPresupuestoDB.listarTratamientosPiezaPresupuesto(this.presupuestoSelected.getIdPresupuesto());
        }
        int m = this.columnasFichaClinica.length;
        
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
        
        this.modeloPiezaTratamiento = new DefaultTableModel(this.filasPiezaTratamiento, this.columnasFichaClinica) {
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
        
        this.tablaFichaClinica.setModel(modeloPiezaTratamiento);
        
        TableColumn tratamientos = tablaFichaClinica.getColumnModel().getColumn(1);
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
                        
                        costoTotal.setText("$"+total);
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
    
    public void iniciarTablaPlanTratamiento() throws Exception{
        
        this.columnasPlanTratamiento = new String [] {"Profesional", "Costo Total", "Total Abonos", "Avance"};
        this.updateTablaPanTratamiento();
        this.tablaPlanTratamiento.getTableHeader().setReorderingAllowed(false);
        
        this.tablaPlanTratamiento.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                
                if(cambios && !nuevoPlanTratamientoSelected){
                    Object[] options = {"Sí","No"};

                    int n = JOptionPane.showOptionDialog(getParent(),
                                "Hay cambios que no se han guardardo\n\n"+
                                "¿Desea guardar?",
                                "Orthodent",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);

                    if(n==0){
                        guardar();
                    }
                    else{
                        cambios = false;
                    }
                }
                
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 1) {
                    Object [] fila = getRowAt(row);
                    try {
                        presupuestoSelected = PresupuestoDB.getPresupuesto((String)fila[4], paciente.getId_paciente());
                        
                        if(presupuestoSelected!=null){
                            eliminar.setEnabled(true);
                            remove.setEnabled(true);
                            add.setEnabled(true);
                            
                            if(actual.getId_rol()==3){
                                //Profesional
                                String nombre = actual.getNombre();
        
                                if(nombre.contains(" ")){
                                    nombre = nombre.substring(0,nombre.indexOf(" "));
                                }
                                
                                Vector model = new Vector();
                                Item item = new Item(nombre+" "+actual.getApellido_pat(), actual.getId_usuario());
                                model.addElement(item);
                                
                                profesional.setModel(new DefaultComboBoxModel(model));
                                profesionalSelected = nombre+" "+actual.getApellido_pat();
                                profesional.setSelectedItem(item);
                            }
                            else{
                                profesional.setEnabled(true);
                                
                                ArrayList<Usuario> usuarios = Autenticacion.listarProfesionales();
                                
                                if(usuarios!=null && usuarios.size()>0){
                                    Usuario profesional1 = Autenticacion.getUsuario(presupuestoSelected.getIdProfesional());

                                    String nombre = profesional1.getNombre();

                                    if(nombre.contains(" ")){
                                        nombre = nombre.substring(0,nombre.indexOf(" "));
                                    }
                                    profesionalSelected = nombre+" "+profesional1.getApellido_pat();
                                    
                                    Vector model = new Vector();
                                    Item item = null;
                                    int i = 0;
                                    for(Usuario user : usuarios){
                                        String name = user.getNombre();

                                        if(name.contains(" ")){
                                            name = name.substring(0,name.indexOf(" "));
                                        }
                                        name = name + " " + user.getApellido_pat();
                                        
                                        if(profesionalSelected.equals(name)){
                                            item = new Item(name,user.getId_usuario());
                                            model.addElement(item);
                                        }
                                        else{
                                            model.addElement(new Item(name,user.getId_usuario()));
                                        }
                                        i++;
                                    }
                                    profesional.setModel(new DefaultComboBoxModel(model));
                                    profesional.setSelectedItem(item);
                                }
                            }
                            
                            costoTotal.setText("$"+presupuestoSelected.getCostoTotal());
                            
                            estado.setModel(new DefaultComboBoxModel(new String [] {"Activo","Cancelado"}));
                            estado.setEnabled(true);
                            if(presupuestoSelected.getEstado()){
                                estadoSelected = "Activo";
                                estado.setSelectedItem("Activo");
                                aprobar.setEnabled(true);
                            }
                            else{
                                estadoSelected = "Cancelado";
                                estado.setSelectedItem("Cancelado");
                                aprobar.setEnabled(false);
                            }
                            
                            fechaCreacion.setText(presupuestoSelected.getFechaCreacion());
                            fechaUltimaModificacion.setText(presupuestoSelected.getFechaModificacion());
                            
                            tablaFichaClinica.setEnabled(true);
                            iniciarTablaPiezaTratamiento();
                            guardar.setEnabled(false);
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        });
    }
    
    private Object[] getRowAt(int row) {
        Object[] result = new Object[this.columnasPlanTratamiento.length];
        
        for (int i = 0; i < this.columnasPlanTratamiento.length; i++) {
            result[i] = this.tablaPlanTratamiento.getModel().getValueAt(row, i);
            
        }   
        
        return result;
    }
    
    public void updateTablaPanTratamiento() throws Exception{
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        ArrayList<PlanTratamiento> planesTratamiento = null;
        
        if(this.actual.getId_rol()==3){
            //Profesional
            planesTratamiento = PlanTratamientoDB.listarPlanesTratamientoPaciente(paciente.getId_paciente(), actual.getId_usuario());
        }
        else{
            planesTratamiento = PlanTratamientoDB.listarPlanesTratamientoPaciente(paciente.getId_paciente());
        }
        
        int m = this.columnasPlanTratamiento.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(PlanTratamiento planTratamiento : planesTratamiento){
            if(planTratamiento.isActivo()){
                
                Usuario profesional = Autenticacion.getUsuario(planTratamiento.getIdProfesional());
                
                String nombre = profesional.getNombre();
        
                if(nombre.contains(" ")){
                    nombre = nombre.substring(0,nombre.indexOf(" "));
                }
                
                nombre = nombre + " " + profesional.getApellido_pat(); //profesional
                
               
                
                
                Object [] fila = new Object [] {new Item(nombre,profesional.getId_usuario()), 
                    new Item(planTratamiento.getCostoTotal()+"",planTratamiento.getIdPlanTratamiento()), planTratamiento.getTotalAbonos(), planTratamiento.getAvance()};
                
                objetos.add(fila);
            }
        }
        
        this.filasPresupuesto = new Object [objetos.size()][m];
        int i = 0;
        for(Object [] o : objetos){
            this.filasPresupuesto[i] = o;
            i++;
        }
        
        this.modeloPresupuesto = new DefaultTableModel(this.filasPresupuesto, this.columnasPlanTratamiento) {
            Class[] types = new Class [] {
                Item.class, Item.class, String.class, String.class, String.class
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
        
        this.tablaPlanTratamiento.setModel(modeloPresupuesto);
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
        tablaPlanTratamiento = new javax.swing.JTable();
        eliminar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelProfesional = new javax.swing.JLabel();
        profesional = new javax.swing.JComboBox();
        labelEstado = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox();
        labelFechaCreacion = new javax.swing.JLabel();
        fechaCreacion = new javax.swing.JTextField();
        labelFechaUltimaModificacion = new javax.swing.JLabel();
        fechaUltimaModificacion = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaFichaClinica = new javax.swing.JTable();
        labelTotal = new javax.swing.JLabel();
        costoTotal = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        nuevoPresupuesto = new javax.swing.JButton();
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
        labelTitulo.setText("Presupuestos");

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

        tablaPlanTratamiento.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        tablaPlanTratamiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Profesional", "Costo Total", "Total Abonos", "Avance"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane1.setViewportView(tablaPlanTratamiento);

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
        labelProfesional.setText("Profesional");

        profesional.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        profesional.setEnabled(false);
        profesional.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                profesionalItemStateChanged(evt);
            }
        });
        profesional.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                profesionalFocusGained(evt);
            }
        });
        profesional.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                profesionalPropertyChange(evt);
            }
        });

        labelEstado.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelEstado.setText("Estado");

        estado.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Activo", "Cancelado" }));
        estado.setEnabled(false);
        estado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                estadoItemStateChanged(evt);
            }
        });
        estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoActionPerformed(evt);
            }
        });

        labelFechaCreacion.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelFechaCreacion.setText("Fecha de Creación");

        fechaCreacion.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        fechaCreacion.setEnabled(false);

        labelFechaUltimaModificacion.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelFechaUltimaModificacion.setText("Fecha Modificación");

        fechaUltimaModificacion.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        fechaUltimaModificacion.setEnabled(false);

        tablaFichaClinica.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        tablaFichaClinica.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaFichaClinica.setEnabled(false);
        jScrollPane2.setViewportView(tablaFichaClinica);

        labelTotal.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelTotal.setText("Total");

        costoTotal.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        costoTotal.setEnabled(false);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelProfesional)
                            .addComponent(labelEstado)
                            .addComponent(labelFechaUltimaModificacion)
                            .addComponent(labelFechaCreacion))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fechaCreacion)
                            .addComponent(fechaUltimaModificacion)
                            .addComponent(estado, 0, 175, Short.MAX_VALUE)
                            .addComponent(profesional, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add)
                            .addComponent(remove))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelProfesional)
                    .addComponent(profesional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(remove)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFechaCreacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFechaUltimaModificacion)
                    .addComponent(fechaUltimaModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        nuevoPresupuesto.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        nuevoPresupuesto.setText("Nuevo Presupuesto");
        nuevoPresupuesto.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                nuevoPresupuestoMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                nuevoPresupuestoMouseMoved(evt);
            }
        });
        nuevoPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoPresupuestoActionPerformed(evt);
            }
        });

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
                    .addComponent(nuevoPresupuesto)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aprobar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guardar))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nuevoPresupuesto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        
        if(this.presupuestoSelected!=null){
            int  id_profesional = ((Item)this.profesional.getSelectedItem()).getId();
            
            boolean estado = false;
            if(((String)this.estado.getSelectedItem()).equals("Activo")){
                estado = true;
            }
            else{
                estado = false;
            }
            
            int costoTotal = 0;
            if(!this.costoTotal.getText().equals("$")){
                costoTotal = Integer.parseInt(this.costoTotal.getText().substring(this.costoTotal.getText().indexOf("$")+1, this.costoTotal.getText().length()));
            }
            
            int cantidadTratamientos = this.tablaFichaClinica.getRowCount();
            
            String fechaModificacion = this.getCurrentDateTime();
            
            this.presupuestoSelected.setIdProfesional(id_profesional);
            this.presupuestoSelected.setEstado(estado);
            this.presupuestoSelected.setCostoTotal(costoTotal);
            this.presupuestoSelected.setCantidadTratamiento(cantidadTratamientos);
            this.presupuestoSelected.setFechaModificacion(fechaModificacion);
            
            boolean respuesta = PresupuestoDB.editarPresupuesto(presupuestoSelected);
            if(respuesta){
                
                boolean error = false;
                for(int i=0; i<this.tablaFichaClinica.getRowCount(); i++){ //recorro las filas
                    try {
                        int pieza = Integer.parseInt((String) this.tablaFichaClinica.getValueAt(i, 0));
                        
                        if(this.tablaFichaClinica.getValueAt(i, 1)==null){
                            JOptionPane.showMessageDialog(this,
                                "Hay cambios en la tabla sin completar!",
                                "Orthodent",
                                JOptionPane.INFORMATION_MESSAGE);
                            error = true;
                            break;
                        }
                        else{
                            if(!(this.tablaFichaClinica.getValueAt(i, 1) instanceof Item)){
                                JOptionPane.showMessageDialog(this,
                                    "Hay cambios en la tabla sin completar!",
                                    "Orthodent",
                                    JOptionPane.INFORMATION_MESSAGE);
                                error = true;
                                break;
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this,
                            "Hay cambios en la tabla sin completar!",
                            "Orthodent",
                            JOptionPane.INFORMATION_MESSAGE);
                        error = true;
                        break;
                    }
                }
                
                if(!error){
                    try {
                        boolean resp = TratamientoPiezaPresupuestoDB.eliminarTratamientoPieza(presupuestoSelected.getIdPresupuesto());

                        if(resp){
                            for(int i=0; i<this.tablaFichaClinica.getRowCount(); i++){ //recorro las filas
                                int pieza = Integer.parseInt((String)this.tablaFichaClinica.getValueAt(i, 0));
                                int id_tratamiento = ((Item)this.tablaFichaClinica.getValueAt(i, 1)).getId();
                                TratamientoPiezaPresupuestoDB.crearTratamientoPiezaPresupuesto(id_tratamiento, this.presupuestoSelected.getIdPresupuesto(), pieza);
                            }
                            try {
                                this.updateTablaPanTratamiento();
                            } catch (Exception ex) {
                            }
                            this.cambios = false;
                            this.guardar.setEnabled(false);
                        }
                    } catch (SQLException ex) {
                    }
                }
            }
        }
        else{
            //Nuevo Presupuesto
            int  id_profesional = ((Item)this.profesional.getSelectedItem()).getId();
            
            boolean estado = false;
            if(((String)this.estado.getSelectedItem()).equals("Activo")){
                estado = true;
            }
            else{
                estado = false;
            }
            
            int costoTotal = 0;
            if(!this.costoTotal.getText().equals("$")){
                costoTotal = Integer.parseInt(this.costoTotal.getText().substring(this.costoTotal.getText().indexOf("$")+1, this.costoTotal.getText().length()));
            }
            
            int cantidadTratamientos = this.tablaFichaClinica.getRowCount();
            
            String fechaModificacion = this.getCurrentDateTime();
            
            boolean respuesta = PresupuestoDB.crearPresupuesto(this.paciente.getId_paciente(), id_profesional, estado,
                    costoTotal, cantidadTratamientos, true, fechaModificacion, fechaModificacion);
            
            if(respuesta){
                
                boolean error = false;
                for(int i=0; i<this.tablaFichaClinica.getRowCount(); i++){ //recorro las filas
                    try {
                        int pieza = Integer.parseInt((String) this.tablaFichaClinica.getValueAt(i, 0));
                        
                        if(this.tablaFichaClinica.getValueAt(i, 1)==null){
                            JOptionPane.showMessageDialog(this,
                                "Hay cambios en la tabla sin completar!",
                                "Orthodent",
                                JOptionPane.INFORMATION_MESSAGE);
                            error = true;
                            break;
                        }
                        else{
                            if(!(this.tablaFichaClinica.getValueAt(i, 1) instanceof Item)){
                                JOptionPane.showMessageDialog(this,
                                    "Hay cambios en la tabla sin completar!",
                                    "Orthodent",
                                    JOptionPane.INFORMATION_MESSAGE);
                                error = true;
                                break;
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this,
                            "Hay cambios en la tabla sin completar!",
                            "Orthodent",
                            JOptionPane.INFORMATION_MESSAGE);
                        error = true;
                        break;
                    }
                }
                
                if(!error){
                    try {
                        Presupuesto pre = PresupuestoDB.getPresupuesto(fechaModificacion, this.paciente.getId_paciente());
                        
                        for(int i=0; i<this.tablaFichaClinica.getRowCount(); i++){ //recorro las filas
                            int pieza = Integer.parseInt((String)this.tablaFichaClinica.getValueAt(i, 0));
                            int id_tratamiento = ((Item)this.tablaFichaClinica.getValueAt(i, 1)).getId();
                            TratamientoPiezaPresupuestoDB.crearTratamientoPiezaPresupuesto(id_tratamiento, pre.getIdPresupuesto(), pieza);
                        }
                        try {
                            this.updateTablaPanTratamiento();
                        } catch (Exception ex) {
                        }
                        this.cambios = false;
                        this.nuevoPlanTratamientoSelected = false;
                        this.guardar.setEnabled(false);
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void profesionalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_profesionalPropertyChange
        
    }//GEN-LAST:event_profesionalPropertyChange

    private void profesionalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_profesionalFocusGained
        
    }//GEN-LAST:event_profesionalFocusGained

    private void profesionalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_profesionalItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Item item = (Item)evt.getItem();
            
            if(this.profesionalSelected!=null){
                if(!this.profesionalSelected.equals(item.getValue())){
                    this.habilitarBoton();
                }
            }
       }
    }//GEN-LAST:event_profesionalItemStateChanged

    private void estadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_estadoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object item = evt.getItem();
            
            if(((String)item).equals("Cancelado")){
                this.aprobar.setEnabled(false);
            }
            else{
                this.aprobar.setEnabled(true);
            }
            
            if(this.estadoSelected!=null){
                if(!this.estadoSelected.equals((String)item)){
                    this.habilitarBoton();
                }
            }
       }
    }//GEN-LAST:event_estadoItemStateChanged

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        Object[] options = {"Sí","No"};
        
        int n = JOptionPane.showOptionDialog(this,
                    "¿Esta seguro que desea eliminar el presupuesto?",
                    "Orthodent",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
        
        if(n==0){
            try {
                boolean resul = PresupuestoDB.eliminarPresupuesto(this.presupuestoSelected.getIdPresupuesto());
                if(resul){
                    try {
                        this.updateTablaPanTratamiento();
                        this.profesional.setEnabled(false);
                        this.profesionalSelected = null;
                        this.profesional.setModel(new DefaultComboBoxModel(new String [] {""}));
                        this.profesional.setSelectedItem("");
                        this.costoTotal.setEnabled(false);
                        this.costoTotal.setText("");
                        this.estadoSelected = null;
                        this.estado.setModel(new DefaultComboBoxModel(new String [] {""}));
                        this.estado.setSelectedItem("");
                        this.estado.setEnabled(false);
                        this.fechaCreacion.setText("");
                        this.fechaUltimaModificacion.setText("");
                        this.presupuestoSelected = null;
                        this.eliminar.setEnabled(false);
                        this.aprobar.setEnabled(false);
                        this.guardar.setEnabled(false);
                        this.tablaFichaClinica.setEnabled(false);
                        this.remove.setEnabled(false);
                        this.add.setEnabled(false);
                    } catch (Exception ex) {
                    }
                }
            } catch (SQLException ex) {
            }
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void aprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aprobarActionPerformed
        
        //asd
        
        if(!this.nuevoPlanTratamientoSelected){
            int  id_profesional = ((Item)this.profesional.getSelectedItem()).getId();
            
            boolean estado = false;
            if(((String)this.estado.getSelectedItem()).equals("Activo")){
                estado = true;
            }
            else{
                estado = false;
            }

            int costoTotal = 0;
            if(!this.costoTotal.getText().equals("$")){
                costoTotal = Integer.parseInt(this.costoTotal.getText().substring(this.costoTotal.getText().indexOf("$")+1, this.costoTotal.getText().length()));
            }

            int cantidadTratamientos = this.tablaFichaClinica.getRowCount();

            String fechaModificacion = this.getCurrentDateTime();

            this.presupuestoSelected.setIdProfesional(id_profesional);
            this.presupuestoSelected.setEstado(estado);
            this.presupuestoSelected.setCostoTotal(costoTotal);
            this.presupuestoSelected.setCantidadTratamiento(cantidadTratamientos);
            this.presupuestoSelected.setFechaModificacion(fechaModificacion);
            
            boolean respuesta = PlanTratamientoDB.crearPlanTratamiento(this.paciente.getId_paciente(), id_profesional, this.presupuestoSelected.getFechaCreacion(),
                    fechaModificacion, costoTotal, 0, 0, true, fechaModificacion, fechaModificacion);
            
            if(respuesta){
                
                boolean error = false;
                for(int i=0; i<this.tablaFichaClinica.getRowCount(); i++){ //recorro las filas
                    try {
                        int pieza = Integer.parseInt((String) this.tablaFichaClinica.getValueAt(i, 0));
                        
                        if(this.tablaFichaClinica.getValueAt(i, 1)==null){
                            JOptionPane.showMessageDialog(this,
                                "Hay cambios en la tabla sin completar!",
                                "Orthodent",
                                JOptionPane.INFORMATION_MESSAGE);
                            error = true;
                            break;
                        }
                        else{
                            if(!(this.tablaFichaClinica.getValueAt(i, 1) instanceof Item)){
                                JOptionPane.showMessageDialog(this,
                                    "Hay cambios en la tabla sin completar!",
                                    "Orthodent",
                                    JOptionPane.INFORMATION_MESSAGE);
                                error = true;
                                break;
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this,
                            "Hay cambios en la tabla sin completar!",
                            "Orthodent",
                            JOptionPane.INFORMATION_MESSAGE);
                        error = true;
                        break;
                    }
                }
                
                if(!error){
                    try {
                        PlanTratamiento plan = PlanTratamientoDB.getPlanTratamiento(fechaModificacion, this.paciente.getId_paciente());
                        
                        for(int i=0; i<this.tablaFichaClinica.getRowCount(); i++){ //recorro las filas
                            int pieza = Integer.parseInt((String)this.tablaFichaClinica.getValueAt(i, 0));
                            int id_tratamiento = ((Item)this.tablaFichaClinica.getValueAt(i, 1)).getId();
                            
                            TratamientoPiezaPlanDB.crearTratamientoPiezaPlan(plan.getIdPlanTratamiento(), id_tratamiento, pieza, false);
                        }
                        
                        TratamientoPiezaPresupuestoDB.eliminarTratamientoPieza(this.presupuestoSelected.getIdPresupuesto());
                        
                        PresupuestoDB.purgarPresupuesto(this.presupuestoSelected.getIdPresupuesto());
                        
                        try {
                            this.updateTablaPanTratamiento();
                            this.profesional.setEnabled(false);
                            this.profesionalSelected = null;
                            this.profesional.setModel(new DefaultComboBoxModel(new String [] {""}));
                            this.profesional.setSelectedItem("");
                            this.costoTotal.setEnabled(false);
                            this.costoTotal.setText("");
                            this.estadoSelected = null;
                            this.estado.setModel(new DefaultComboBoxModel(new String [] {""}));
                            this.estado.setSelectedItem("");
                            this.estado.setEnabled(false);
                            this.fechaCreacion.setText("");
                            this.fechaUltimaModificacion.setText("");
                            this.presupuestoSelected = null;
                            this.eliminar.setEnabled(false);
                            this.aprobar.setEnabled(false);
                            this.guardar.setEnabled(false);
                            this.tablaFichaClinica.setEnabled(false);
                            this.remove.setEnabled(false);
                            this.add.setEnabled(false);
                        } catch (Exception ex) {
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        }
        
        
        
    }//GEN-LAST:event_aprobarActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        int SelectedRow = this.tablaFichaClinica.getSelectedRow();
        this.modeloPiezaTratamiento.removeRow(this.tablaFichaClinica.convertRowIndexToModel(SelectedRow));
        
        int total = 0;
        for(int i=0; i<modeloPiezaTratamiento.getRowCount(); i++){
            String valor = (String) modeloPiezaTratamiento.getValueAt(i, 3);
            valor = valor.substring(valor.indexOf("$")+1, valor.length());
            int precio = Integer.parseInt(valor);
            total = total + precio;
        }

        costoTotal.setText("$"+total);
        habilitarBoton();
    }//GEN-LAST:event_removeActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        this.modeloPiezaTratamiento.addRow(new Object []{"","","",""});
        this.habilitarBoton();
    }//GEN-LAST:event_addActionPerformed

    private void nuevoPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoPresupuestoActionPerformed
        //Habilitar profesional
        this.nuevoPlanTratamientoSelected = true;
        this.cambios = true;
        this.costoTotal.setText("$");
        this.tablaFichaClinica.setEnabled(true);
        this.profesional.setEnabled(true);
        this.remove.setEnabled(true);
        this.add.setEnabled(true);
        this.estado.setEnabled(true);
        this.guardar.setEnabled(true);
        
        if(actual.getId_rol()==3){
            //Profesional
            String nombre = actual.getNombre();

            if(nombre.contains(" ")){
                nombre = nombre.substring(0,nombre.indexOf(" "));
            }

            Vector model = new Vector();
            Item item = new Item(nombre+" "+actual.getApellido_pat(), actual.getId_usuario());
            model.addElement(item);

            profesional.setModel(new DefaultComboBoxModel(model));
            profesionalSelected = nombre+" "+actual.getApellido_pat();
            profesional.setSelectedItem(item);
        }
        else{
            profesional.setEnabled(true);

            ArrayList<Usuario> usuarios = Autenticacion.listarProfesionales();

            if(usuarios!=null && usuarios.size()>0){
                Vector model = new Vector();
                int i = 0;
                for(Usuario user : usuarios){
                    String name = user.getNombre();

                    if(name.contains(" ")){
                        name = name.substring(0,name.indexOf(" "));
                    }
                    name = name + " " + user.getApellido_pat();
                    
                    model.addElement(new Item(name,user.getId_usuario()));
                    i++;
                }
                profesional.setModel(new DefaultComboBoxModel(model));
                profesional.setSelectedIndex(0);
            }
        }
        
        estado.setModel(new DefaultComboBoxModel(new String [] {"Activo","Cancelado"}));
        estado.setSelectedIndex(0);
        
        try {
            this.iniciarTablaPiezaTratamiento();
        } catch (Exception ex) {
        }
        
    }//GEN-LAST:event_nuevoPresupuestoActionPerformed

    private void estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estadoActionPerformed

    private void nuevoPresupuestoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoPresupuestoMouseMoved
        
    }//GEN-LAST:event_nuevoPresupuestoMouseMoved

    private void nuevoPresupuestoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoPresupuestoMouseDragged
        
    }//GEN-LAST:event_nuevoPresupuestoMouseDragged
    
    private void habilitarBoton(){
        this.cambios = true;
        this.guardar.setEnabled(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton aprobar;
    private javax.swing.JTextField costoTotal;
    private javax.swing.JButton eliminar;
    private javax.swing.JComboBox estado;
    private javax.swing.JTextField fechaCreacion;
    private javax.swing.JTextField fechaUltimaModificacion;
    private javax.swing.JButton guardar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelFechaCreacion;
    private javax.swing.JLabel labelFechaUltimaModificacion;
    private javax.swing.JLabel labelProfesional;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JButton nuevoPresupuesto;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JComboBox profesional;
    private javax.swing.JButton remove;
    private javax.swing.JTable tablaFichaClinica;
    private javax.swing.JTable tablaPlanTratamiento;
    // End of variables declaration//GEN-END:variables
}
