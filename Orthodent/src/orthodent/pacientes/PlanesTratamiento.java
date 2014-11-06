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
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.Paciente;
import modelo.PlanTratamiento;
import modelo.Presupuesto;
import modelo.Tratamiento;
import modelo.Usuario;
import orthodent.Item;
import orthodent.db.Autenticacion;
import orthodent.db.PlanTratamientoDB;
import orthodent.db.PresupuestoDB;

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
    private String profesionalSelected;
    private String estadoSelected;
    private Paciente pacienteSelected;
    private PlanTratamiento tratamientotoSelected;
    private ArrayList<Tratamiento> auxiliar;
    private int rowSelected;
    
    public PlanesTratamiento(Paciente paciente, Usuario actual) throws Exception {
        initComponents();
        this.setCursor();
        this.paciente = paciente;
        this.actual = actual;
        this.cambios = false;        
        this.addInfo();
        this.guardar.setEnabled(false);
        this.tablaTratamiento.getTableHeader().setReorderingAllowed(false);
        this.tablaPiezaTratamiento.getTableHeader().setReorderingAllowed(false);
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
        this.iniciarTablaPlanesTratamientos();
        
    }
    
    public void iniciarTablaPiezaTratamiento() throws Exception{
        this.columnasNombrePiezaTratamiento = new String [] {"Pieza", "Tratamiento", "Valor Colegio O.", "Valor Orthodent"};
      //  this.updateTablaPiezaTratamiento();
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
    
    /*public void updateTablaPiezaTratamiento() throws Exception{
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        //ArrayList<TratamientoPiezaPresupuesto> piezasPresupuesto = TratamientoPiezaPresupuestoDB.listarTratamientosPiezaPresupuesto(this.presupuestoSelected.getIdPresupuesto());
        ArrayList<PlanTratamiento> planesTratamiento = PlanTratamientoDB.listarPlanesTratamientoPaciente(this.pacienteSelected.getId_paciente());
        
        int m = this.columnasNombrePiezaTratamiento.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(TratamientoPiezaPresupuesto piezaPresupuesto : piezasPresupuesto){
            String pieza = piezaPresupuesto.getPieza()+"";
            
            Tratamiento tratamiento = TratamientoDB.getTratamiento(piezaPresupuesto.getId_tratamiento());
            
            Object [] fila = new Object [] {pieza, tratamiento.getNombre(), "$"+tratamiento.getValorColegio(), "$"+tratamiento.getValorClinica()};

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
                String.class, String.class, String.class, String.class
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
                    if(trat.getNombre().equals((String)evt.getItem())){
                        modeloPiezaTratamiento.setValueAt("$"+trat.getValorColegio(), rowSelected, 2);
                        modeloPiezaTratamiento.setValueAt("$"+trat.getValorClinica(), rowSelected, 3);
                        
                        int total = 0;
                        for(int i=0; i<modeloPiezaTratamiento.getRowCount(); i++){
                            String valor = (String) modeloPiezaTratamiento.getValueAt(i, 3);
                            valor = valor.substring(valor.indexOf("$")+1, valor.length());
                            int precio = Integer.parseInt(valor);
                            total = total + precio;
                        }
                        
                       
                        habilitarBoton();
                    }
                }
            }
        };
        
        this.auxiliar = TratamientoDB.listarTratamientos();
        if(auxiliar!=null && auxiliar.size()>0){
            for(Tratamiento trat : auxiliar){
                comboBox.addItem(trat.getNombre());
            }
        }
        tratamientos.setCellEditor(new DefaultCellEditor(comboBox));
    }*/
    //la de arriba 
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
                        tratamientotoSelected = PlanTratamientoDB.getPlanTratamiento(((Item)fila[1]).getId());
                                               
                        if(tratamientotoSelected!=null){
                            Usuario profesionalNombre = Autenticacion.getUsuario(tratamientotoSelected.getIdProfesional());
                            profesional.setText(profesionalNombre.getNombre()+" "+profesionalNombre.getApellido_pat());
                            profesional.setEditable(false);
          
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
        Object[] result = new String[this.columnasPlanesTratamiento.length];
        
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
                
                String estado = "";
                
                String costoTotal = this.getMoneda(tratamiento.getCostoTotal());
                
               
                
                Object [] fila = new Object [] {new Item(nombre, profesional1.getId_usuario()), new Item(tratamiento.getCostoTotal()+"",tratamiento.getIdPlanTratamiento()), tratamiento.getTotalAbonos(), tratamiento.getAvance()};
                
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
        jPanel1 = new javax.swing.JPanel();
        labelProfesional = new javax.swing.JLabel();
        labelEstado = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox();
        labelFechaCreacion = new javax.swing.JLabel();
        fechaCreacion = new javax.swing.JTextField();
        labelFechaUltimaModificacion = new javax.swing.JLabel();
        fechaUltimaModificacion = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPiezaTratamiento = new javax.swing.JTable();
        profesional = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(850, 551));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setPreferredSize(new java.awt.Dimension(845, 545));

        panelTitulo.setBackground(new java.awt.Color(247, 243, 248));
        panelTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(232, 230, 234), 1, true));

        labelTitulo.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(163, 159, 164));
        labelTitulo.setText("Plan de Tratamiento");

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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        labelProfesional.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelProfesional.setText("Profesional");

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

        labelFechaCreacion.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelFechaCreacion.setText("Fecha de Creación");

        fechaCreacion.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        fechaCreacion.setEnabled(false);

        labelFechaUltimaModificacion.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelFechaUltimaModificacion.setText("Fecha Modificación");

        fechaUltimaModificacion.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        fechaUltimaModificacion.setEnabled(false);

        tablaPiezaTratamiento.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        tablaPiezaTratamiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pieza", "Tratamiento", "Valor", "Fecha", "Estado"
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

        profesional.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        profesional.setEnabled(false);

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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fechaCreacion)
                                    .addComponent(fechaUltimaModificacion)
                                    .addComponent(estado, 0, 175, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(profesional, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
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
                    .addComponent(guardar)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(guardar)
                .addContainerGap(30, Short.MAX_VALUE))
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
    
    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        
      /* Item selecProfesional = (Item)this.profesional.getSelectedItem();
        
        System.out.println("id"+selecProfesional.getId());
        System.out.println("nombre: "+selecProfesional.getValue());*/
        
        /*String nombre = this.nombres.getText();
        String apellidoPat = this.apellidoPat.getText();
        String apellidoMat = this.apellidoMat.getText();
        String email = this.email.getText();
        
        Date date = this.fechaNacimiento.getDate();
        
        String fechaNacimiento = getFechaString(date);
        
        int edad = Integer.parseInt(this.edad.getText());
        
        int sexo = 0;
        if(((String)this.sexo.getSelectedItem()).equals("Femenino")){
            sexo = 1;
        }
        else{
            sexo = 2;
        }
        
        String antecedenteMedico = this.antecedentesMedicos.getText();
        String telefono = this.telefono.getText();
        String ciudad = (String)this.ciudad.getSelectedItem();
        String comuna = (String)this.comuna.getSelectedItem();
        String direccion = this.direccion.getText();
        
        boolean aux = validarCamposObligatorios(nombre,apellidoPat,fechaNacimiento,telefono);
        
        if(aux){
            try {
                this.paciente.setNombre(nombre);
                this.paciente.setApellido_pat(apellidoPat);
                this.paciente.setApellido_mat(apellidoMat);
                this.paciente.setEmail(email);
                this.paciente.setFechaNacimiento(fechaNacimiento);
                this.paciente.setEdad(edad);
                this.paciente.setSexo(sexo);
                this.paciente.setAntecedenteMedico(antecedenteMedico);
                this.paciente.setTelefono(telefono);
                this.paciente.setCiudad(ciudad);
                this.paciente.setComuna(comuna);
                this.paciente.setDireccion(direccion);
                
                boolean respuesta = PacienteDB.editarPaciente(paciente);

                if(respuesta){
                    
                    JPanel contenedor = (JPanel)this.getParent();
                    
                    ((MostrarInfoPaciente)contenedor.getParent()).updateNombre();
                    
                    this.cambios = false;
                    this.guardar.setEnabled(false);
                }
            } catch (Exception ex) {
                System.out.println("");
            }
        }
        else{
            JOptionPane.showMessageDialog(this,
                    "Faltan campos obligatorios",
                    "Orthodent",
                    JOptionPane.INFORMATION_MESSAGE);
        }*/
    }//GEN-LAST:event_guardarActionPerformed

    private void estadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_estadoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object item = evt.getItem();
            
            if(this.estadoSelected!=null){
                if(!this.estadoSelected.equals((String)item)){
                    this.habilitarBoton();
                }
            }
       }
    }//GEN-LAST:event_estadoItemStateChanged
    
    private void habilitarBoton(){
        this.cambios = true;
        this.guardar.setEnabled(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JTextField profesional;
    private javax.swing.JTable tablaPiezaTratamiento;
    private javax.swing.JTable tablaTratamiento;
    // End of variables declaration//GEN-END:variables
}
