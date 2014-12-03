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
import modelo.LaboratorioPiezaPresupuesto;
import modelo.Paciente;
import modelo.PlanTratamiento;
import modelo.Presupuesto;
import modelo.Tratamiento;
import modelo.TratamientoPiezaPresupuesto;
import modelo.Usuario;
import orthodent.Item;
import orthodent.db.Autenticacion;
import orthodent.db.LaboratorioPiezaPresupuestoDB;
import orthodent.db.PlanTratamientoDB;
import orthodent.db.PresupuestoDB;
import orthodent.db.TratamientoDB;
import orthodent.db.TratamientoPiezaPlanDB;
import orthodent.db.TratamientoPiezaPresupuestoDB;

/**
 *
 * @author Mary
 */
public class Presupuestos extends JPanel{

    private Usuario actual;
    private Paciente paciente;
    private boolean cambios;
    private String [] columnasNombrePresupuestos;
    private Object [][] filasPresupuesto;
    private TableModel modeloPresupuesto;
    private String [] columnasNombrePiezaTratamiento;
    private Object [][] filasPiezaTratamiento;
    private DefaultTableModel modeloPiezaTratamiento;
    private String [] columnasNombreLaboratorio;
    private Object [][] filasLaboratorio;
    private DefaultTableModel modeloLaboratorio;
    private String profesionalSelected;
    private String estadoSelected;
    private Presupuesto presupuestoSelected;
    private ArrayList<Tratamiento> auxiliar;
    private int rowSelected;
    private int rowSelectedLaboratorio;
    private boolean nuevoPresupuestoSel;
    
    public Presupuestos(Paciente paciente, Usuario actual) throws Exception {
        initComponents();
        this.setCursor();
        
        this.paciente = paciente;
        this.actual = actual;
        this.cambios = false;
        this.nuevoPresupuestoSel = false;
        
        this.addInfo();
        this.guardar.setEnabled(false);
        this.tablaPresupuestos.getTableHeader().setReorderingAllowed(false);
        this.tablaPiezaTratamiento.getTableHeader().setReorderingAllowed(false);
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
        this.iniciarTablaPresupuestos();
    }
    
    public void iniciarTablaPiezaTratamiento() throws Exception{
        this.columnasNombrePiezaTratamiento = new String [] {"Pieza", "Tratamiento", "Valor Colegio O.", "Valor Orthodent"};
        this.updateTablaPiezaTratamiento();
        this.tablaPiezaTratamiento.getTableHeader().setReorderingAllowed(false);
        
        this.tablaPiezaTratamiento.addMouseListener(new MouseAdapter() {
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
        if(!this.nuevoPresupuestoSel){
            piezasPresupuesto = TratamientoPiezaPresupuestoDB.listarTratamientosPiezaPresupuesto(this.presupuestoSelected.getIdPresupuesto());
        }
        int m = this.columnasNombrePiezaTratamiento.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(TratamientoPiezaPresupuesto piezaPresupuesto : piezasPresupuesto){
            String pieza = piezaPresupuesto.getPieza()+"";
            Tratamiento tratamiento = TratamientoDB.getTratamiento(piezaPresupuesto.getId_tratamiento());
            Object [] fila = new Object [] {pieza, new Item(tratamiento.getNombre(),tratamiento.getIdTratamiento()), "$"+piezaPresupuesto.getValorColegio(), "$"+piezaPresupuesto.getValorClinica()};

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
        
        this.tablaPiezaTratamiento.setModel(modeloPiezaTratamiento);
        
        TableColumn tratamientos = tablaPiezaTratamiento.getColumnModel().getColumn(1);
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
    
    public void iniciarTablaLaboratorio() throws Exception{
        this.columnasNombreLaboratorio = new String [] {"Pieza", "Prestación", "Valor"};
        this.updateTablaLaboratorio();
        this.tablaLaboratorio.getTableHeader().setReorderingAllowed(false);
        
        this.tablaLaboratorio.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 1) {
                    
                    int total = 0;
                    for(int i=0; i<modeloLaboratorio.getRowCount(); i++){
                        Object valor = modeloLaboratorio.getValueAt(i, 2);
                        Integer lalala = 0;
                        if(valor instanceof Integer){
                            lalala = (Integer)valor;
                            
                        }
                        total = total + lalala;
                    }
                    costoTotalLaboratorio.setText("$"+total);
                    
                    int col = table.columnAtPoint(p);
                    if(col==1){
                        rowSelectedLaboratorio = row;
                    }
                }
                if (me.getClickCount() == 2) {
                    habilitarBoton();
                }
            }
        });
    }
    
    public void updateTablaLaboratorio() throws Exception{
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        ArrayList<LaboratorioPiezaPresupuesto> laboratorios = LaboratorioPiezaPresupuestoDB.listarLaboratoriosPiezaPresupuesto(this.presupuestoSelected.getIdPresupuesto());
        
        int m = this.columnasNombreLaboratorio.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        this.filasPresupuesto = new Object [laboratorios.size()][m];
        int i = 0;
        for(LaboratorioPiezaPresupuesto labs : laboratorios){
            this.filasPresupuesto[i] = new Object[]{new Item(labs.getPieza()+"", labs.getId()),labs.getPrestacion(), "$"+labs.getValor()};
            i++;
        }
        
        this.modeloLaboratorio = new DefaultTableModel(this.filasLaboratorio, this.columnasNombreLaboratorio) {
            Class[] types = new Class [] {
                Integer.class, String.class, Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        this.tablaLaboratorio.setModel(modeloLaboratorio);
    }
    
    public void iniciarTablaPresupuestos() throws Exception{
        
        this.columnasNombrePresupuestos = new String [] {"Profesional", "Cantidad de Tratamientos", "Costo Total", "Estado", "Fecha de Creación"};
        this.updateTablaPresupuestos();
        this.tablaPresupuestos.getTableHeader().setReorderingAllowed(false);
        
        this.tablaPresupuestos.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                
                if(cambios && !nuevoPresupuestoSel){
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
                        System.out.println((String)fila[4]);
                        if(presupuestoSelected!=null){
                            eliminar.setEnabled(true);
                            remove.setEnabled(true);
                            add.setEnabled(true);
                            addLab.setEnabled(true);
                            removeLab.setEnabled(true);
                            
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
                            
                            tablaPiezaTratamiento.setEnabled(true);
                            iniciarTablaPiezaTratamiento();
                            
                            tablaLaboratorio.setEnabled(true);
                            iniciarTablaLaboratorio();
                            costoTotalLaboratorio.setText("$"+presupuestoSelected.getCostoLab());
                            
                            guardar.setEnabled(false);
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        });
    }
    
    private Object[] getRowAt(int row) {
        Object[] result = new Object[this.columnasNombrePresupuestos.length];
        
        for (int i = 0; i < this.columnasNombrePresupuestos.length; i++) {
            result[i] = this.tablaPresupuestos.getModel().getValueAt(row, i);
            
        }   
        
        return result;
    }
    
    public void updateTablaPresupuestos() throws Exception{
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        ArrayList<Presupuesto> presupuestos = null;
        
        if(this.actual.getId_rol()==3){
            //Profesional
            presupuestos = PresupuestoDB.listarPresupuestosDePaciente(paciente.getId_paciente(),actual.getId_usuario());
        }
        else{
            presupuestos = PresupuestoDB.listarPresupuestosDePaciente(paciente.getId_paciente());
        }
        
        int m = this.columnasNombrePresupuestos.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(Presupuesto presupuesto : presupuestos){
            if(presupuesto.isActivo()){
                
                Usuario profesional = Autenticacion.getUsuario(presupuesto.getIdProfesional());
                
                String nombre = profesional.getNombre();
        
                if(nombre.contains(" ")){
                    nombre = nombre.substring(0,nombre.indexOf(" "));
                }
                
                nombre = nombre + " " + profesional.getApellido_pat();
                
                String estado = "";
                
                String costoTotal = this.getMoneda(presupuesto.getCostoTotal());
                
                if(presupuesto.getEstado()){
                    estado = "Activo";
                }
                else{
                    estado = "Cancelado";
                }
                
                Object [] fila = new Object [] {new Item(nombre,profesional.getId_usuario()), 
                    new Item(presupuesto.getCantidadTratamiento()+"",presupuesto.getIdPresupuesto()), costoTotal, estado, presupuesto.getFechaCreacion()};
                
                objetos.add(fila);
            }
        }
        
        this.filasPresupuesto = new Object [objetos.size()][m];
        int i = 0;
        for(Object [] o : objetos){
            this.filasPresupuesto[i] = o;
            i++;
        }
        
        this.modeloPresupuesto = new DefaultTableModel(this.filasPresupuesto, this.columnasNombrePresupuestos) {
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
        
        this.tablaPresupuestos.setModel(modeloPresupuesto);
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
        tablaPresupuestos = new javax.swing.JTable();
        eliminar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelProfesional = new javax.swing.JLabel();
        profesional = new javax.swing.JComboBox();
        labelEstado = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPiezaTratamiento = new javax.swing.JTable();
        labelTotal = new javax.swing.JLabel();
        costoTotal = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        labelLaboratorio = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaLaboratorio = new javax.swing.JTable();
        costoTotalLaboratorio = new javax.swing.JTextField();
        labelTotal1 = new javax.swing.JLabel();
        addLab = new javax.swing.JButton();
        removeLab = new javax.swing.JButton();
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

        tablaPresupuestos.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        tablaPresupuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Profesional", "Cantidad de Tratamientos", "Costo Total", "Estado", "Fecha Creación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        });
        jScrollPane1.setViewportView(tablaPresupuestos);

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

        tablaPiezaTratamiento.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        tablaPiezaTratamiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pieza", "Tratamiento", "Valor Colegio O.", "Valor Orthodent"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPiezaTratamiento.setEnabled(false);
        jScrollPane2.setViewportView(tablaPiezaTratamiento);

        labelTotal.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelTotal.setText("Total");

        costoTotal.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        costoTotal.setEnabled(false);
        costoTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costoTotalActionPerformed(evt);
            }
        });

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

        labelLaboratorio.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelLaboratorio.setText("Laboratorio");

        tablaLaboratorio.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        tablaLaboratorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pieza", "Prestación", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaLaboratorio);

        costoTotalLaboratorio.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        costoTotalLaboratorio.setEnabled(false);
        costoTotalLaboratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costoTotalLaboratorioActionPerformed(evt);
            }
        });

        labelTotal1.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelTotal1.setText("Total");

        addLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_mini.png"))); // NOI18N
        addLab.setBorder(null);
        addLab.setBorderPainted(false);
        addLab.setContentAreaFilled(false);
        addLab.setEnabled(false);
        addLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLabActionPerformed(evt);
            }
        });

        removeLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete_mini.png"))); // NOI18N
        removeLab.setBorder(null);
        removeLab.setBorderPainted(false);
        removeLab.setContentAreaFilled(false);
        removeLab.setEnabled(false);
        removeLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeLabActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLaboratorio)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(profesional, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelEstado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add)
                            .addComponent(remove)))
                    .addComponent(labelProfesional)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelTotal1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(costoTotalLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addLab)
                            .addComponent(removeLab))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelProfesional)
                    .addComponent(profesional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(remove))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTotal)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelEstado)
                            .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLaboratorio)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(addLab)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(removeLab)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(costoTotalLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTotal1)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            
            int cantidadTratamientos = this.tablaPiezaTratamiento.getRowCount();
            
            String fechaModificacion = this.getCurrentDateTime();
            
            this.presupuestoSelected.setIdProfesional(id_profesional);
            this.presupuestoSelected.setEstado(estado);
            this.presupuestoSelected.setCostoTotal(costoTotal);
            this.presupuestoSelected.setCantidadTratamiento(cantidadTratamientos);
            this.presupuestoSelected.setFechaModificacion(fechaModificacion);
            
            int costoLabs = 0;
            if(!this.costoTotalLaboratorio.getText().equals("$")){
                costoLabs = Integer.parseInt(this.costoTotalLaboratorio.getText().substring(this.costoTotal.getText().indexOf("$")+1, this.costoTotalLaboratorio.getText().length()));
            }
            this.presupuestoSelected.setCostoLab(costoLabs);
            
            boolean respuesta = PresupuestoDB.editarPresupuesto(presupuestoSelected);
            if(respuesta){
                
                boolean error = false;
                for(int i=0; i<this.tablaPiezaTratamiento.getRowCount(); i++){ //recorro las filas
                    try {
                        int pieza = Integer.parseInt((String) this.tablaPiezaTratamiento.getValueAt(i, 0));
                        
                        if(this.tablaPiezaTratamiento.getValueAt(i, 1)==null){
                            JOptionPane.showMessageDialog(this,
                                "Hay cambios en la tabla sin completar!",
                                "Orthodent",
                                JOptionPane.INFORMATION_MESSAGE);
                            error = true;
                            break;
                        }
                        else{
                            if(!(this.tablaPiezaTratamiento.getValueAt(i, 1) instanceof Item)){
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
                            for(int i=0; i<this.tablaPiezaTratamiento.getRowCount(); i++){ //recorro las filas
                                int pieza = Integer.parseInt((String)this.tablaPiezaTratamiento.getValueAt(i, 0));
                                int id_tratamiento = ((Item)this.tablaPiezaTratamiento.getValueAt(i, 1)).getId();
                                String valorColegio = ((String)this.tablaPiezaTratamiento.getValueAt(i, 2));
                                int valColegio = Integer.parseInt(valorColegio.substring(1, valorColegio.length()));
                                String valorClinica = ((String)this.tablaPiezaTratamiento.getValueAt(i, 3));
                                int valClinica = Integer.parseInt(valorClinica.substring(1, valorClinica.length()));
                                TratamientoPiezaPresupuestoDB.crearTratamientoPiezaPresupuesto(id_tratamiento, this.presupuestoSelected.getIdPresupuesto(), pieza, valColegio, valClinica);
                            }
                            try {
                                this.updateTablaPresupuestos();
                                
                                boolean resp2 = LaboratorioPiezaPresupuestoDB.eliminarLaboratorioPieza(presupuestoSelected.getIdPresupuesto());
                                if(resp2){
                                    for(int i=0; i<this.tablaLaboratorio.getRowCount(); i++){ //recorro las filas
                                        
                                        int pieza = Integer.parseInt((String)this.tablaLaboratorio.getValueAt(i, 0));
                                        String prestacion = (String)this.tablaLaboratorio.getValueAt(i, 1);
                                        int valor = Integer.parseInt((String)this.tablaLaboratorio.getValueAt(i, 2));
                                        LaboratorioPiezaPresupuestoDB.crearLaboratorioPiezaPresupuesto(this.presupuestoSelected.getIdPresupuesto(), pieza, prestacion, valor);
                                    }
                                    try {
                                        this.updateTablaLaboratorio();
                                    } catch (Exception ex) {
                                    }
                                }
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
            
            int cantidadTratamientos = this.tablaPiezaTratamiento.getRowCount();
            
            String fechaModificacion = this.getCurrentDateTime();
            
            int costoLabs = 0;
            if(!this.costoTotalLaboratorio.getText().equals("$")){
                costoLabs = Integer.parseInt(this.costoTotalLaboratorio.getText().substring(this.costoTotal.getText().indexOf("$")+1, this.costoTotalLaboratorio.getText().length()));
            }
            
            boolean respuesta = PresupuestoDB.crearPresupuesto(this.paciente.getId_paciente(), id_profesional, estado,
                    costoTotal, cantidadTratamientos, costoLabs, true, fechaModificacion, fechaModificacion);
            
            if(respuesta){
                boolean error = false;
                for(int i=0; i<this.tablaPiezaTratamiento.getRowCount(); i++){ //recorro las filas
                    try {
                        int pieza = Integer.parseInt((String) this.tablaPiezaTratamiento.getValueAt(i, 0));
                        
                        if(this.tablaPiezaTratamiento.getValueAt(i, 1)==null){
                            JOptionPane.showMessageDialog(this,
                                "Hay cambios en la tabla sin completar!",
                                "Orthodent",
                                JOptionPane.INFORMATION_MESSAGE);
                            error = true;
                            break;
                        }
                        else{
                            if(!(this.tablaPiezaTratamiento.getValueAt(i, 1) instanceof Item)){
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
                        
                        for(int i=0; i<this.tablaPiezaTratamiento.getRowCount(); i++){ //recorro las filas
                            int pieza = Integer.parseInt((String)this.tablaPiezaTratamiento.getValueAt(i, 0));
                            int id_tratamiento = ((Item)this.tablaPiezaTratamiento.getValueAt(i, 1)).getId();
                            String valorColegio = ((String)this.tablaPiezaTratamiento.getValueAt(i, 2));
                            int valColegio = Integer.parseInt(valorColegio.substring(1, valorColegio.length()));
                            String valorClinica = ((String)this.tablaPiezaTratamiento.getValueAt(i, 3));
                            int valClinica = Integer.parseInt(valorClinica.substring(1, valorClinica.length()));
                            TratamientoPiezaPresupuestoDB.crearTratamientoPiezaPresupuesto(id_tratamiento, pre.getIdPresupuesto(), pieza, valColegio, valClinica);
                        }
                        try {
                            this.updateTablaPresupuestos();
                            try {
                                for(int i=0; i<this.tablaLaboratorio.getRowCount(); i++){ //recorro las filas
                                    int pieza = Integer.parseInt(((Item)this.tablaLaboratorio.getValueAt(i, 0)).getValue());
                                    String prestacion = (String)this.tablaLaboratorio.getValueAt(i, 1);
                                    int valor = Integer.parseInt((String)this.tablaLaboratorio.getValueAt(i, 2));
                                    LaboratorioPiezaPresupuestoDB.crearLaboratorioPiezaPresupuesto(this.presupuestoSelected.getIdPresupuesto(), pieza, prestacion, valor);
                                }
                                try {
                                    this.updateTablaLaboratorio();
                                } catch (Exception ex) {
                                }
                            } catch (Exception ex) {
                            }
                        } catch (Exception ex) {
                        }
                        this.cambios = false;
                        this.nuevoPresupuestoSel = false;
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
                        this.updateTablaPresupuestos();
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
                        this.presupuestoSelected = null;
                        this.eliminar.setEnabled(false);
                        this.aprobar.setEnabled(false);
                        this.guardar.setEnabled(false);
                        this.tablaPiezaTratamiento.setEnabled(false);
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
        
        if(!this.nuevoPresupuestoSel){
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

            int cantidadTratamientos = this.tablaPiezaTratamiento.getRowCount();

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
                for(int i=0; i<this.tablaPiezaTratamiento.getRowCount(); i++){ //recorro las filas
                    try {
                        int pieza = Integer.parseInt((String) this.tablaPiezaTratamiento.getValueAt(i, 0));
                        
                        if(this.tablaPiezaTratamiento.getValueAt(i, 1)==null){
                            JOptionPane.showMessageDialog(this,
                                "Hay cambios en la tabla sin completar!",
                                "Orthodent",
                                JOptionPane.INFORMATION_MESSAGE);
                            error = true;
                            break;
                        }
                        else{
                            if(!(this.tablaPiezaTratamiento.getValueAt(i, 1) instanceof Item)){
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
                        
                        for(int i=0; i<this.tablaPiezaTratamiento.getRowCount(); i++){ //recorro las filas
                            int pieza = Integer.parseInt((String)this.tablaPiezaTratamiento.getValueAt(i, 0));
                            int id_tratamiento = ((Item)this.tablaPiezaTratamiento.getValueAt(i, 1)).getId();
                            
                            TratamientoPiezaPlanDB.crearTratamientoPiezaPlan(plan.getIdPlanTratamiento(), id_tratamiento, pieza, false);
                        }
                        
                        TratamientoPiezaPresupuestoDB.eliminarTratamientoPieza(this.presupuestoSelected.getIdPresupuesto());
                        
                        PresupuestoDB.purgarPresupuesto(this.presupuestoSelected.getIdPresupuesto());
                        
                        try {
                            this.updateTablaPresupuestos();
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
                            this.presupuestoSelected = null;
                            this.eliminar.setEnabled(false);
                            this.aprobar.setEnabled(false);
                            this.guardar.setEnabled(false);
                            this.tablaPiezaTratamiento.setEnabled(false);
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
        int SelectedRow = this.tablaPiezaTratamiento.getSelectedRow();
        this.modeloPiezaTratamiento.removeRow(this.tablaPiezaTratamiento.convertRowIndexToModel(SelectedRow));
        
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
        this.nuevoPresupuestoSel = true;
        this.cambios = true;
        this.costoTotal.setText("$");
        this.tablaPiezaTratamiento.setEnabled(true);
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

    private void costoTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costoTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_costoTotalActionPerformed

    private void costoTotalLaboratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costoTotalLaboratorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_costoTotalLaboratorioActionPerformed

    private void addLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLabActionPerformed
        this.modeloLaboratorio.addRow(new Object []{"","",""});
        this.habilitarBoton();
    }//GEN-LAST:event_addLabActionPerformed

    private void removeLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeLabActionPerformed
        int SelectedRow = this.tablaLaboratorio.getSelectedRow();
        this.modeloLaboratorio.removeRow(this.tablaLaboratorio.convertRowIndexToModel(SelectedRow));
        
        int total = 0;
        for(int i=0; i<modeloLaboratorio.getRowCount(); i++){
            String valor = (String) modeloLaboratorio.getValueAt(i, 2);
            if(!valor.equals("")){
                if(valor.contains("$")){
                    valor = valor.substring(valor.indexOf("$")+1, valor.length());
                }
                int precio = Integer.parseInt(valor);
                total = total + precio;
            }
        }
        costoTotalLaboratorio.setText("$"+total);
        habilitarBoton();
    }//GEN-LAST:event_removeLabActionPerformed
    
    private void habilitarBoton(){
        this.cambios = true;
        this.guardar.setEnabled(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton addLab;
    private javax.swing.JButton aprobar;
    private javax.swing.JTextField costoTotal;
    private javax.swing.JTextField costoTotalLaboratorio;
    private javax.swing.JButton eliminar;
    private javax.swing.JComboBox estado;
    private javax.swing.JButton guardar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelLaboratorio;
    private javax.swing.JLabel labelProfesional;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelTotal1;
    private javax.swing.JButton nuevoPresupuesto;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JComboBox profesional;
    private javax.swing.JButton remove;
    private javax.swing.JButton removeLab;
    private javax.swing.JTable tablaLaboratorio;
    private javax.swing.JTable tablaPiezaTratamiento;
    private javax.swing.JTable tablaPresupuestos;
    // End of variables declaration//GEN-END:variables
}
