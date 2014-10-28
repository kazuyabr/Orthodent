/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.pacientes;

import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.Paciente;
import modelo.Presupuesto;
import modelo.Usuario;
import orthodent.db.Autenticacion;
import orthodent.db.PresupuestoDB;

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
    private String profesionalSelected;
    private String estadoSelected;
    private Presupuesto presupuestoSelected;
    
    public Presupuestos(Paciente paciente, Usuario actual) throws Exception {
        initComponents();
        
        this.paciente = paciente;
        this.actual = actual;
        this.cambios = false;
        
        this.addInfo();
        this.guardar.setEnabled(false);
        this.tablaPresupuestos.getTableHeader().setReorderingAllowed(false);
        this.tablaPiezaTratamiento.getTableHeader().setReorderingAllowed(false);
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
    
    public void iniciarTablaPresupuestos() throws Exception{
        
        this.columnasNombrePresupuestos = new String [] {"Profesional", "Cantidad de Tratamientos", "Costo Total", "Estado", "Fecha de Creación"};
        this.updateTablaPresupuestos();
        this.tablaPresupuestos.getTableHeader().setReorderingAllowed(false);
        
        this.tablaPresupuestos.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 1) {
                    Object [] fila = getRowAt(row);
                    try {
                        
                        presupuestoSelected = PresupuestoDB.getPresupuesto((String)fila[4], paciente.getId_paciente());
                        
                        if(presupuestoSelected!=null){
                            eliminar.setEnabled(true);
                            
                            if(actual.getId_rol()==3){
                                //Profesional
                                String nombre = actual.getNombre();
        
                                if(nombre.contains(" ")){
                                    nombre = nombre.substring(0,nombre.indexOf(" "));
                                }
                                profesional.setModel(new DefaultComboBoxModel(new String[] {(nombre+" "+actual.getApellido_pat())}));
                                profesionalSelected = nombre+" "+actual.getApellido_pat();
                                profesional.setSelectedItem(nombre+" "+actual.getApellido_pat());
                            }
                            else{
                                profesional.setEnabled(true);
                                
                                ArrayList<Usuario> usuarios = Autenticacion.listarProfesionales();
                                
                                if(usuarios!=null && usuarios.size()>0){
                                    String [] profesionales = new String [usuarios.size()];
                                    
                                    int i = 0;
                                    for(Usuario user : usuarios){
                                        String name = user.getNombre();

                                        if(name.contains(" ")){
                                            name = name.substring(0,name.indexOf(" "));
                                        }
                                        name = name + " " + user.getApellido_pat();
                                        profesionales[i] = name;
                                        i++;
                                    }

                                    profesional.setModel(new DefaultComboBoxModel(profesionales));

                                    Usuario profesional1 = Autenticacion.getUsuario(presupuestoSelected.getIdProfesional());

                                    String nombre = profesional1.getNombre();

                                    if(nombre.contains(" ")){
                                        nombre = nombre.substring(0,nombre.indexOf(" "));
                                    }
                                    profesionalSelected = nombre+" "+profesional1.getApellido_pat();
                                    profesional.setSelectedItem(nombre+" "+profesional1.getApellido_pat());
                                }
                            }
                            
                            costoTotal.setText("$"+presupuestoSelected.getCostoTotal());
                            
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
                        }
                    } catch (Exception ex) {
                        System.out.println("");
                    }
                }
            }
        });
    }
    
    private Object[] getRowAt(int row) {
        Object[] result = new String[this.columnasNombrePresupuestos.length];
        
        for (int i = 0; i < this.columnasNombrePresupuestos.length; i++) {
            result[i] = this.tablaPresupuestos.getModel().getValueAt(row, i);
        }
        
        return result;
    }
    
    public void updateTablaPresupuestos() throws Exception{
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        ArrayList<Presupuesto> presupuestos = PresupuestoDB.listarPresupuestosDePaciente(paciente.getId_paciente());
        
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
                
                Object [] fila = new Object [] {nombre, presupuesto.getCantidadTratamiento()+"", costoTotal, estado, presupuesto.getFechaCreacion()};
                
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
                String.class, String.class, String.class, String.class, String.class
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
        labelFechaCreacion = new javax.swing.JLabel();
        fechaCreacion = new javax.swing.JTextField();
        labelFechaUltimaModificacion = new javax.swing.JLabel();
        fechaUltimaModificacion = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPiezaTratamiento = new javax.swing.JTable();
        labelTotal = new javax.swing.JLabel();
        costoTotal = new javax.swing.JTextField();
        nuevoPresupuesto = new javax.swing.JButton();

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
        jScrollPane2.setViewportView(tablaPiezaTratamiento);

        labelTotal.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelTotal.setText("Total");

        costoTotal.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        costoTotal.setEnabled(false);

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
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(labelTotal)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(122, Short.MAX_VALUE))
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
                        .addComponent(guardar))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(74, Short.MAX_VALUE))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardar)
                    .addComponent(eliminar))
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
    
    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        
    }//GEN-LAST:event_guardarActionPerformed

    private void profesionalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_profesionalPropertyChange
        
    }//GEN-LAST:event_profesionalPropertyChange

    private void profesionalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_profesionalFocusGained
        
    }//GEN-LAST:event_profesionalFocusGained

    private void profesionalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_profesionalItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object item = evt.getItem();
            
            if(this.profesionalSelected!=null){
                if(!this.profesionalSelected.equals((String)item)){
                    this.habilitarBoton();
                }
            }
       }
    }//GEN-LAST:event_profesionalItemStateChanged

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
                        this.fechaCreacion.setText("");
                        this.fechaUltimaModificacion.setText("");
                        this.presupuestoSelected = null;
                        this.eliminar.setEnabled(false);
                        this.guardar.setEnabled(false);
                    } catch (Exception ex) {
                        System.out.println("");
                    }
                }
            } catch (SQLException ex) {
            }
        }
    }//GEN-LAST:event_eliminarActionPerformed
    
    private void habilitarBoton(){
        this.cambios = true;
        this.guardar.setEnabled(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JTable tablaPiezaTratamiento;
    private javax.swing.JTable tablaPresupuestos;
    // End of variables declaration//GEN-END:variables
}
