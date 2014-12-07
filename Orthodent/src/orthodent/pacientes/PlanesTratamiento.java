/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.pacientes;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.LaboratorioPiezaPlan;
import modelo.Paciente;
import modelo.PlanTratamiento;
import modelo.Tratamiento;
import modelo.TratamientoPiezaPlan;
import modelo.Usuario;
import orthodent.Item;
import orthodent.db.Autenticacion;
import orthodent.db.LaboratorioPiezaPlanDB;
import orthodent.db.PlanTratamientoDB;
import orthodent.db.TratamientoDB;
import orthodent.db.TratamientoPiezaPlanDB;

/**
 *
 * @author Mary
 */
public class PlanesTratamiento extends JPanel{

    private Usuario actual;
    private Paciente paciente;
    private boolean cambios;
    private String [] columnasPlanesTratamiento;
    private Object [][] filasPlanesTratamiento;
    private TableModel modeloPlanesTratamiento;
    private String [] columnasNombrePiezaTratamiento;
    private Object [][] filasPiezaTratamiento;
    private DefaultTableModel modeloPiezaTratamiento;
    private String [] columnasNombreLaboratorio;
    private Object [][] filasLaboratorio;
    private DefaultTableModel modeloLaboratorio;
    private PlanTratamiento tratamientoSelected;
    private int rowSelected;
    private int rowSelectedLaboratorio;
    private int avanceTotal;
    private int avanceCantidad;
    
    public PlanesTratamiento(Paciente paciente, Usuario actual) throws Exception {
        initComponents();
        this.setCursor();
        
        this.paciente = paciente;
        this.actual = actual;
        this.cambios = false;
        
        this.addInfo();
        this.guardar.setEnabled(false);
    }
    
    private void setCursor(){
        this.guardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    public boolean getCambios(){
        return this.cambios;
    }
    
    public void setCambios(boolean cambios){
        this.cambios = cambios;
    }
    
    private void addInfo() throws Exception{
        this.iniciarTablaTratamiento();
    }
    
    public void iniciarTablaPiezaTratamiento() throws Exception{
        this.columnasNombrePiezaTratamiento = new String [] {"Pieza", "Tratamiento", "Valor", "Fecha", "Realizado"};
        this.updateTablaPiezaTratamiento();
        this.tablaPiezaTratamiento.getTableHeader().setReorderingAllowed(false);
        
        this.tablaPiezaTratamiento.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 1) {
                    int col = table.columnAtPoint(p);
                    if(col==4){
                        rowSelected = row;
                        Boolean valor = (Boolean)tablaPiezaTratamiento.getModel().getValueAt(row, col);
                        cambiarEstadoPieza(valor, row);
                    }
                }
            }
        });
    }
    
    private void cambiarEstadoPieza(boolean oldValue, int row){
        if(oldValue){
            //Si el viejo valor es true, ahora lo desactivo!
            tablaPiezaTratamiento.getModel().setValueAt("", row, 3);
            this.avanceCantidad--;
            this.avance.setText((int)((this.avanceCantidad*1.0/this.avanceTotal)*100)+"%");
        }
        else{
            //Si el viejo valor es true, ahora lo activo...
            String fecha = this.getCurrentDateTime();
            tablaPiezaTratamiento.getModel().setValueAt(fecha, row, 3);
            this.avanceCantidad++;
            this.avance.setText((int)((this.avanceCantidad*1.0/this.avanceTotal)*100)+"%");
        }
        this.guardar.setEnabled(true);
        this.cambios = true;
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
        fecha = fecha + (date.getYear()+1900);
        
        return fecha;
    }
    
    public void updateTablaPiezaTratamiento() throws Exception{
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        ArrayList<TratamientoPiezaPlan> piezasTratamiento = TratamientoPiezaPlanDB.listarTratamientosPiezaPlan(this.tratamientoSelected.getIdPlanTratamiento());
        
        int m = this.columnasNombrePiezaTratamiento.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(TratamientoPiezaPlan piezaTratamiento : piezasTratamiento){
            String pieza = piezaTratamiento.getPieza()+"";
            Tratamiento tratamiento = TratamientoDB.getTratamiento(piezaTratamiento.getIdTratamiento());
            
            if(piezaTratamiento.getEstado()){
                this.avanceCantidad++;
            }
            this.avanceTotal++;
            
            String fecha = "";
            if(piezaTratamiento.getEstado()){
                fecha = this.getFecha(piezaTratamiento.getFechaRealizado());
            }
            
            Object [] fila = new Object [] {new Item(pieza, piezaTratamiento.getIdTratamientoPiezaPlan()),
                                            new Item(tratamiento.getNombre(),tratamiento.getIdTratamiento()),
                                            "$"+piezaTratamiento.getValor(), 
                                            fecha,
                                            piezaTratamiento.getEstado()};

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
                Item.class, Item.class, String.class, String.class, Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        this.tablaPiezaTratamiento.setModel(modeloPiezaTratamiento);
    }
    
    private String getFecha(String fecha){
        
        if(fecha!=null){
            
            String año = fecha.substring(0, fecha.indexOf("-"));
            fecha = fecha.substring(fecha.indexOf("-")+1, fecha.length());

            String mes = fecha.substring(0, fecha.indexOf("-"));
            fecha = fecha.substring(fecha.indexOf("-")+1, fecha.length());

            String dia = fecha;
            
            return dia+"-"+mes+"-"+año;
        }
        else{
            return null;
        }
    }
    
    public void iniciarTablaLaboratorio() throws Exception{
        this.columnasNombreLaboratorio = new String [] {"Pieza", "Prestación", "Valor", "Fecha", "Realizado"};
        this.updateTablaLaboratorio();
        this.tablaLaboratorio.getTableHeader().setReorderingAllowed(false);
        
        this.tablaLaboratorio.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 1) {
                    int col = table.columnAtPoint(p);
                    if(col==4){
                        rowSelectedLaboratorio = row;
                        Boolean valor = (Boolean)tablaLaboratorio.getModel().getValueAt(row, col);
                        cambiarEstadoPiezaLaboratorio(valor, row);
                    }
                }
            }
        });
    }
    
    private void cambiarEstadoPiezaLaboratorio(boolean oldValue, int row){
        if(oldValue){
            //Si el viejo valor es true, ahora lo desactivo!
            tablaLaboratorio.getModel().setValueAt("", row, 3);
            tablaLaboratorio.getModel().setValueAt(false, row, 4);
            this.avanceCantidad--;
            this.avance.setText((int)((this.avanceCantidad*1.0/this.avanceTotal)*100)+"%");
        }
        else{
            //Si el viejo valor es true, ahora lo activo...
            String fecha = this.getCurrentDateTime();
            tablaLaboratorio.getModel().setValueAt(fecha, row, 3);
            tablaLaboratorio.getModel().setValueAt(true, row, 4);
            this.avanceCantidad++;
            this.avance.setText((int)((this.avanceCantidad*1.0/this.avanceTotal)*100)+"%");
        }
        this.guardar.setEnabled(true);
        this.cambios = true;
    }
    
    public void updateTablaLaboratorio() throws Exception{
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        ArrayList<LaboratorioPiezaPlan> laboratorios = LaboratorioPiezaPlanDB.listarLaboratoriosPiezaPlan(this.tratamientoSelected.getIdPlanTratamiento());
        
        int m = this.columnasNombreLaboratorio.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        this.filasLaboratorio = new Object [laboratorios.size()][m];
        int i = 0;
        for(LaboratorioPiezaPlan labs : laboratorios){
            
            String pieza = labs.getPieza()+"";
            
            if(labs.getEstado()){
                this.avanceCantidad++;
            }
            this.avanceTotal++;
            
            String fecha = "";
            if(labs.getEstado()){
                fecha = this.getFecha(labs.getFechaRealizado());
            }
            
            this.filasLaboratorio[i] = new Object[]{new Item(pieza+"",labs.getId()),labs.getPrestacion()+"", labs.getValor()+"", fecha, labs.getEstado()};
            i++;
        }
        
        this.modeloLaboratorio = new DefaultTableModel(this.filasLaboratorio, this.columnasNombreLaboratorio) {
            Class[] types = new Class [] {
                Item.class, String.class, String.class, String.class, Boolean.class
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
    
    public void iniciarTablaTratamiento() throws Exception{
        
        this.columnasPlanesTratamiento = new String [] {"Profesional",  "Costo Total", "Total Abonos", "Avance"};
        this.updateTablaPlanesTratamientos();
        this.tablaTratamiento.getTableHeader().setReorderingAllowed(false);
        
        this.tablaTratamiento.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 1) {
                    Object [] fila = getRowAt(row);
                    try {
                        avanceTotal = 0;
                        avanceCantidad = 0;
                        tratamientoSelected = PlanTratamientoDB.getPlanTratamiento(((Item)fila[1]).getId());
                                               
                        if(tratamientoSelected!=null){
                            Usuario profesionalNombre = Autenticacion.getUsuario(tratamientoSelected.getIdProfesional());
                            
                            String nombre = profesionalNombre.getNombre();
                            if(nombre.contains(" ")){
                                nombre = nombre.substring(0,nombre.indexOf(" "));
                            }
                            
                            profesional.setText(nombre+" "+profesionalNombre.getApellido_pat());
                            profesional.setEditable(false);
                            
                            costoTotal.setText("$"+tratamientoSelected.getCostoTotal());
                            costoTotal.setEditable(false);
                            
                            totalAbonos.setText("$"+tratamientoSelected.getTotalAbonos());
                            totalAbonos.setEditable(false);
                            
                            avance.setText(tratamientoSelected.getAvance()+"%");
                            avance.setEditable(false);
                            
                            fechaCreacion.setText(tratamientoSelected.getFechaCreacion());
                            fechaUltimaModificacion.setText(tratamientoSelected.getFechaModificacion());
                            
                            tablaPiezaTratamiento.setEnabled(true);
                            iniciarTablaPiezaTratamiento();
                            iniciarTablaLaboratorio();
                            guardar.setEnabled(false);
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
        
        if(fecha!=null && !fecha.equals("")){
            String año = fecha.substring(0, fecha.indexOf("-"));
            fecha = fecha.substring(fecha.indexOf("-")+1, fecha.length());

            String mes = fecha.substring(0, fecha.indexOf("-"));
            fecha = fecha.substring(fecha.indexOf("-")+1, fecha.length());

            String fechaNueva = fecha+"-"+mes+"-"+año;
            return fechaNueva;
        }
        
        return "NULL";
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
        jPanel1 = new javax.swing.JPanel();
        labelProfesional = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPiezaTratamiento = new javax.swing.JTable();
        labelLaboratorio = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaLaboratorio = new javax.swing.JTable();
        costoTotal = new javax.swing.JTextField();
        labelCostoTotal = new javax.swing.JLabel();
        profesional = new javax.swing.JTextField();
        labelTotalAbonos = new javax.swing.JLabel();
        totalAbonos = new javax.swing.JTextField();
        labelAvance = new javax.swing.JLabel();
        avance = new javax.swing.JTextField();
        labelFechaCreacion = new javax.swing.JLabel();
        fechaCreacion = new javax.swing.JTextField();
        labelFechaUltimaModificacion = new javax.swing.JLabel();
        fechaUltimaModificacion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTratamiento = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(850, 551));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setPreferredSize(new java.awt.Dimension(845, 545));

        panelTitulo.setBackground(new java.awt.Color(247, 243, 248));
        panelTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(232, 230, 234), 1, true));

        labelTitulo.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(163, 159, 164));
        labelTitulo.setText("Plan de Tratamiento!!");

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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setPreferredSize(new java.awt.Dimension(663, 328));

        labelProfesional.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelProfesional.setText("Profesional");

        tablaPiezaTratamiento.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        tablaPiezaTratamiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pieza", "Tratamiento", "Valor", "Fecha", "Realizado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
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

        labelLaboratorio.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelLaboratorio.setText("Laboratorio");

        tablaLaboratorio.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        tablaLaboratorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pieza", "Prestación", "Valor", "Fecha", "Realizado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaLaboratorio.setEnabled(false);
        jScrollPane3.setViewportView(tablaLaboratorio);

        costoTotal.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        costoTotal.setEnabled(false);
        costoTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costoTotalActionPerformed(evt);
            }
        });

        labelCostoTotal.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelCostoTotal.setText("Costo Total");

        profesional.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        profesional.setEnabled(false);

        labelTotalAbonos.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelTotalAbonos.setText("Total Abonos");

        totalAbonos.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        totalAbonos.setEnabled(false);
        totalAbonos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalAbonosActionPerformed(evt);
            }
        });

        labelAvance.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelAvance.setText("Avance");

        avance.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        avance.setEnabled(false);
        avance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avanceActionPerformed(evt);
            }
        });

        labelFechaCreacion.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelFechaCreacion.setText("Fecha de Creación");

        fechaCreacion.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        fechaCreacion.setEnabled(false);

        labelFechaUltimaModificacion.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelFechaUltimaModificacion.setText("Fecha Modificación");
        labelFechaUltimaModificacion.setToolTipText("");

        fechaUltimaModificacion.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        fechaUltimaModificacion.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCostoTotal)
                            .addComponent(labelAvance))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(avance, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelFechaUltimaModificacion)
                                .addGap(8, 8, 8)
                                .addComponent(fechaUltimaModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelFechaCreacion)
                                .addGap(18, 18, 18)
                                .addComponent(fechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelTotalAbonos)
                                .addGap(53, 53, 53)
                                .addComponent(totalAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(labelLaboratorio)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelProfesional)
                        .addGap(102, 102, 102)
                        .addComponent(profesional, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelProfesional)
                    .addComponent(profesional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLaboratorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCostoTotal)
                    .addComponent(labelTotalAbonos)
                    .addComponent(costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAvance)
                    .addComponent(avance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFechaCreacion)
                    .addComponent(fechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFechaUltimaModificacion)
                    .addComponent(fechaUltimaModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jScrollPane1.setViewportView(tablaTratamiento);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(580, 580, 580)
                        .addComponent(guardar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guardar)
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void guardar(){
        this.guardarActionPerformed(null);
    }
    
    private String getCurrentDateTime2(){
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
        
        int avanceValor = Integer.parseInt(this.avance.getText().substring(0, this.avance.getText().length()-1));
        
        this.tratamientoSelected.setAvance(avanceValor);
        
        String fechaModificacion = this.getCurrentDateTime2();
        this.tratamientoSelected.setFechaModificacion(fechaModificacion);
        
        boolean respuesta = PlanTratamientoDB.editarPlanTratamiento(tratamientoSelected);
        
        if(respuesta){
            for(int i=0; i<this.tablaPiezaTratamiento.getRowCount(); i++){ //recorro las filas
                try{
                    int id = ((Item)this.tablaPiezaTratamiento.getValueAt(i, 0)).getId();

                    TratamientoPiezaPlan piezaPlan = TratamientoPiezaPlanDB.getTratamientoPiezaPlan(id);
                    piezaPlan.setEstado((Boolean)this.tablaPiezaTratamiento.getValueAt(i, 4));
                    
                    if(piezaPlan.getEstado()){
                        String fecha = (String)this.tablaPiezaTratamiento.getValueAt(i, 3);
                        piezaPlan.setFechaRealizado(this.girarFecha(fecha));
                    }
                    TratamientoPiezaPlanDB.editarTratamientoPiezaPlan(piezaPlan);
                }
                catch(Exception e){
                }
            }
            
            for(int i=0; i<this.tablaLaboratorio.getRowCount(); i++){ //recorro las filas
                try{
                    int id = ((Item)this.tablaLaboratorio.getValueAt(i, 0)).getId();
                    LaboratorioPiezaPlan piezaPlan = LaboratorioPiezaPlanDB.getLaboratorioPiezaPlan(id);
                    piezaPlan.setEstado((Boolean)this.tablaLaboratorio.getValueAt(i, 4));
                    
                    if(piezaPlan.getEstado()){
                        String fecha = (String)this.tablaLaboratorio.getValueAt(i, 3);
                        piezaPlan.setFechaRealizado(this.girarFecha(fecha));
                    }
                    
                    LaboratorioPiezaPlanDB.editarLaboratorioPiezaPlan(piezaPlan);
                }
                catch(Exception e){
                }
            }
            try {
                this.updateTablaPlanesTratamientos();
            } catch (Exception ex) {
                
            }
            this.cambios = false;
            this.guardar.setEnabled(false);
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void costoTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costoTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_costoTotalActionPerformed

    private void totalAbonosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalAbonosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalAbonosActionPerformed

    private void avanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_avanceActionPerformed
    
    private void habilitarBoton(){
        this.cambios = true;
        this.guardar.setEnabled(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField avance;
    private javax.swing.JTextField costoTotal;
    private javax.swing.JTextField fechaCreacion;
    private javax.swing.JTextField fechaUltimaModificacion;
    private javax.swing.JButton guardar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelAvance;
    private javax.swing.JLabel labelCostoTotal;
    private javax.swing.JLabel labelFechaCreacion;
    private javax.swing.JLabel labelFechaUltimaModificacion;
    private javax.swing.JLabel labelLaboratorio;
    private javax.swing.JLabel labelProfesional;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelTotalAbonos;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JTextField profesional;
    private javax.swing.JTable tablaLaboratorio;
    private javax.swing.JTable tablaPiezaTratamiento;
    private javax.swing.JTable tablaTratamiento;
    private javax.swing.JTextField totalAbonos;
    // End of variables declaration//GEN-END:variables
}
