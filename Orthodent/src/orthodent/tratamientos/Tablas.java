/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.tratamientos;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.Categoria1;
import modelo.Categoria2;
import modelo.Tratamiento;
import orthodent.Item;
import orthodent.JVentana;
import orthodent.db.Categoria1DB;
import orthodent.db.Categoria2DB;
import orthodent.db.TratamientoDB;

/**
 *
 * @author msanhuezal
 */
public class Tablas extends javax.swing.JPanel {
    
    private TableModel modeloCat1;
    private Object [][] filasCat1;
    private String [] columnasNombreCat1;
    private int categoria1Selected;
    private TableModel modeloCat2;
    private Object [][] filasCat2;
    private String [] columnasNombreCat2;    
    private int categoria2Selected;
    private TableModel modeloTratamientos;
    private Object [][] filasTratamientos;
    private String [] columnasNombreTratamientos;    
    private int tratamientosSelected;
  
    public Tablas() {
        initComponents();
        this.iniciarTablaCategoria1();
        this.setCursor();
        
        this.nuevo2.setEnabled(false);
        this.nuevo3.setEnabled(false);
        
        this.editar1.setEnabled(false);
        this.editar2.setEnabled(false);
        this.editar3.setEnabled(false);
        
        this.eliminar1.setEnabled(false);
        this.eliminar2.setEnabled(false);
        this.eliminar3.setEnabled(false);
                
    }
    
    public void setCursor(){
        this.nuevo1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.nuevo2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.nuevo3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        this.editar1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.editar2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.editar3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        this.eliminar1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.eliminar2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.eliminar3.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    public void updateTablas(){
        this.updateModelo1();
        if(categoria1Selected > 0){
          this.updateModelo2(categoria1Selected);
        }
        if(categoria2Selected > 0){
          this.updateModeloTratamientos(categoria2Selected);
        }
    }
    
    private Object[] getRowAt1(int row) {
        Object[] result = new Object[this.columnasNombreCat1.length];
        
        for (int i = 0; i < this.columnasNombreCat1.length; i++) {
            result[i] = this.tablaCat1.getModel().getValueAt(row, i);
        }
        return result;
    }
   
    private Object[] getRowAt2(int row) {
        Object[] result = new Object[this.columnasNombreCat2.length];
        
        for (int i = 0; i < this.columnasNombreCat2.length; i++) {
            result[i] = this.tablaCat2.getModel().getValueAt(row, i);
        }
        return result;
    }
    
    private Object[] getRowAt3(int row) {
        Object[] result = new Object[this.columnasNombreTratamientos.length];
        
        for (int i = 0; i < this.columnasNombreTratamientos.length; i++) {
            result[i] = this.tablaTratamientos.getModel().getValueAt(row, i);
        }
        return result;
    }
   
    public void updateModelo1(){
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        ArrayList<Categoria1> categorias1 = Categoria1DB.listarCategoria1();
      
        int m = this.columnasNombreCat1.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(Categoria1 categoria1 : categorias1){
            if(categoria1.isActivo()){
                Object [] fila = new Object [] {new Item(categoria1.getNombre(), categoria1.getIdCategoria1())};
                objetos.add(fila);
            }
        }
        
        this.filasCat1 = new Object [objetos.size()][m];
        int i = 0;
        for(Object [] o : objetos){
            this.filasCat1[i] = o;
            i++;
        }
        
        this.modeloCat1 = new DefaultTableModel(this.filasCat1, this.columnasNombreCat1) {
            Class[] types = new Class [] {
                Item.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        this.tablaCat1.setModel(modeloCat1);
    }
    
    public void updateModelo2(int idCategoria1){
        //Podria ser ordenado!! -> una opcion es que la consulta ordene
        ArrayList<Categoria2> categorias2 = Categoria2DB.listarCategoria2(idCategoria1);
      
        int m = this.columnasNombreCat2.length;
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(Categoria2 categoria2 : categorias2){
            if(categoria2.isActivo()){
                Object [] fila = new Object [] {new Item(categoria2.getNombre(), categoria2.getIdCategoria2())};
                objetos.add(fila);
            }
        }
        
        this.filasCat2 = new Object [objetos.size()][m];
        int i = 0;
        for(Object [] o : objetos){
            this.filasCat2[i] = o;
            i++;
        }
        
        this.modeloCat2 = new DefaultTableModel(this.filasCat2, this.columnasNombreCat2) {
            Class[] types = new Class [] {
                Item.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        this.tablaCat2.setModel(modeloCat2);
    }
    
    public void updateModeloTratamientos(int idCategoria2){
        ArrayList<Tratamiento> tratamientos = new ArrayList<Tratamiento>();
        
        if(idCategoria2!=-1){
            //Podria ser ordenado!! -> una opcion es que la consulta ordene
            tratamientos = TratamientoDB.listarTratamientosCategoria2(idCategoria2);
        }
      
        int m = this.columnasNombreTratamientos.length;
        
        
        ArrayList<Object []> objetos = new ArrayList<Object []>();
        
        for(Tratamiento tratamiento : tratamientos){
            if(tratamiento.isActivo()){
                Object [] fila = new Object [] {new Item(tratamiento.getNombre(), tratamiento.getIdTratamiento()),
                                                tratamiento.getCantidadUCO(), "$"+tratamiento.getValorColegio(),
                                                "$"+tratamiento.getValorClinica()};
                objetos.add(fila);
            }
        }
        
        this.filasTratamientos = new Object [objetos.size()][m];
        int i = 0;
        for(Object [] o : objetos){
            this.filasTratamientos[i] = o;
            i++;
        }
        
        this.modeloTratamientos = new DefaultTableModel(this.filasTratamientos, this.columnasNombreTratamientos) {
            Class[] types = new Class [] {
                Item.class, String.class, String.class, String.class
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
        
        this.tablaTratamientos.setModel(modeloTratamientos);
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCat1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCat2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaTratamientos = new javax.swing.JTable();
        labelCategoria = new javax.swing.JLabel();
        labelSubCategoria = new javax.swing.JLabel();
        labelTratamiento = new javax.swing.JLabel();
        eliminar1 = new javax.swing.JButton();
        editar1 = new javax.swing.JButton();
        nuevo1 = new javax.swing.JButton();
        nuevo2 = new javax.swing.JButton();
        editar2 = new javax.swing.JButton();
        eliminar2 = new javax.swing.JButton();
        nuevo3 = new javax.swing.JButton();
        editar3 = new javax.swing.JButton();
        eliminar3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        tablaCat1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaCat1);

        tablaCat2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaCat2);

        tablaTratamientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Valor UCO", "Valor Colegio", "Valor Clínica"
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
        jScrollPane3.setViewportView(tablaTratamientos);

        labelCategoria.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelCategoria.setForeground(new java.awt.Color(11, 146, 181));
        labelCategoria.setText("Categoria");

        labelSubCategoria.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelSubCategoria.setForeground(new java.awt.Color(11, 146, 181));
        labelSubCategoria.setText("SubCategoria");

        labelTratamiento.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelTratamiento.setForeground(new java.awt.Color(11, 146, 181));
        labelTratamiento.setText("Tratamiento");

        eliminar1.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        eliminar1.setForeground(new java.awt.Color(11, 146, 181));
        eliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete_mini.png"))); // NOI18N
        eliminar1.setText("Eliminar");
        eliminar1.setBorder(null);
        eliminar1.setBorderPainted(false);
        eliminar1.setContentAreaFilled(false);
        eliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar1ActionPerformed(evt);
            }
        });

        editar1.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        editar1.setForeground(new java.awt.Color(11, 146, 181));
        editar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit_mini.png"))); // NOI18N
        editar1.setText("Editar");
        editar1.setBorder(null);
        editar1.setBorderPainted(false);
        editar1.setContentAreaFilled(false);
        editar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar1ActionPerformed(evt);
            }
        });

        nuevo1.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        nuevo1.setForeground(new java.awt.Color(11, 146, 181));
        nuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_mini.png"))); // NOI18N
        nuevo1.setText("Nuevo");
        nuevo1.setBorder(null);
        nuevo1.setBorderPainted(false);
        nuevo1.setContentAreaFilled(false);
        nuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevo1ActionPerformed(evt);
            }
        });

        nuevo2.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        nuevo2.setForeground(new java.awt.Color(11, 146, 181));
        nuevo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_mini.png"))); // NOI18N
        nuevo2.setText("Nuevo");
        nuevo2.setBorder(null);
        nuevo2.setBorderPainted(false);
        nuevo2.setContentAreaFilled(false);
        nuevo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevo2ActionPerformed(evt);
            }
        });

        editar2.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        editar2.setForeground(new java.awt.Color(11, 146, 181));
        editar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit_mini.png"))); // NOI18N
        editar2.setText("Editar");
        editar2.setBorder(null);
        editar2.setBorderPainted(false);
        editar2.setContentAreaFilled(false);
        editar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar2ActionPerformed(evt);
            }
        });

        eliminar2.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        eliminar2.setForeground(new java.awt.Color(11, 146, 181));
        eliminar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete_mini.png"))); // NOI18N
        eliminar2.setText("Eliminar");
        eliminar2.setBorder(null);
        eliminar2.setBorderPainted(false);
        eliminar2.setContentAreaFilled(false);
        eliminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar2ActionPerformed(evt);
            }
        });

        nuevo3.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        nuevo3.setForeground(new java.awt.Color(11, 146, 181));
        nuevo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_mini.png"))); // NOI18N
        nuevo3.setText("Nuevo");
        nuevo3.setBorder(null);
        nuevo3.setBorderPainted(false);
        nuevo3.setContentAreaFilled(false);
        nuevo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevo3ActionPerformed(evt);
            }
        });

        editar3.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        editar3.setForeground(new java.awt.Color(11, 146, 181));
        editar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit_mini.png"))); // NOI18N
        editar3.setText("Editar");
        editar3.setBorder(null);
        editar3.setBorderPainted(false);
        editar3.setContentAreaFilled(false);
        editar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar3ActionPerformed(evt);
            }
        });

        eliminar3.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        eliminar3.setForeground(new java.awt.Color(11, 146, 181));
        eliminar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete_mini.png"))); // NOI18N
        eliminar3.setText("Eliminar");
        eliminar3.setBorder(null);
        eliminar3.setBorderPainted(false);
        eliminar3.setContentAreaFilled(false);
        eliminar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nuevo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminar1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelCategoria)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(nuevo2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(editar2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(eliminar2))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelSubCategoria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTratamiento)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 87, Short.MAX_VALUE)
                        .addComponent(nuevo3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editar3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminar3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSubCategoria)
                    .addComponent(labelCategoria)
                    .addComponent(labelTratamiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(eliminar3)
                        .addComponent(editar3)
                        .addComponent(nuevo3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(eliminar1)
                        .addComponent(nuevo1)
                        .addComponent(editar1)
                        .addComponent(nuevo2)
                        .addComponent(editar2)
                        .addComponent(eliminar2)))
                .addGap(188, 188, 188))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nuevo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevo3ActionPerformed
        JVentana ventana = (JVentana)this.getTopLevelAncestor();
        new NuevoTratamiento(ventana, true, categoria2Selected).setVisible(true);
    }//GEN-LAST:event_nuevo3ActionPerformed

    private void eliminar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar3ActionPerformed
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
            boolean resul = TratamientoDB.eliminarTratamiento(tratamientosSelected);
            if(resul){
                this.updateModeloTratamientos(categoria2Selected);
            }
            
            this.editar3.setEnabled(false);
            this.eliminar3.setEnabled(false);
        }
    }//GEN-LAST:event_eliminar3ActionPerformed

    private void editar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar3ActionPerformed
        JVentana ventana = (JVentana)this.getTopLevelAncestor();
        try {
            new EditarTratamiento(ventana, true, categoria2Selected, tratamientosSelected).setVisible(true);
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_editar3ActionPerformed

    private void nuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevo1ActionPerformed
        JVentana ventana = (JVentana)this.getTopLevelAncestor();
        try {
            new NuevaCategoria(ventana, true).setVisible(true);
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_nuevo1ActionPerformed

    private void nuevo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevo2ActionPerformed
        JVentana ventana = (JVentana)this.getTopLevelAncestor();
        try {
            new NuevaSubCategoria(ventana, true, this.categoria1Selected).setVisible(true);
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_nuevo2ActionPerformed

    private void editar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar1ActionPerformed
        JVentana ventana = (JVentana)this.getTopLevelAncestor();
        try {
            new EditarCategoria(ventana, true, this.categoria1Selected).setVisible(true);
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_editar1ActionPerformed

    private void editar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar2ActionPerformed
        JVentana ventana = (JVentana)this.getTopLevelAncestor();
        try {
            new EditarSubCategoria(ventana, true, this.categoria2Selected).setVisible(true);
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_editar2ActionPerformed

    private void eliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar1ActionPerformed
        Object[] options = {"Sí","No"};
        
        int n = JOptionPane.showOptionDialog(this,
                    "¿Esta seguro que desea eliminar la categoria?",
                    "Orthodent",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
        
        if(n==0){
            boolean resul = Categoria1DB.eliminarCategoria1(categoria1Selected);
            //aqui!!
            if(resul){
                this.updateModelo1();
            }
            boolean resul2 = Categoria2DB.eliminarCategoria2DeCategoria1(categoria1Selected);
            if(resul2){
                this.updateModelo2(categoria2Selected);
                //this.updateModeloTratamientos(categoria1Selected);
            }
            this.editar1.setEnabled(false);
            this.eliminar1.setEnabled(false);
        }
    }//GEN-LAST:event_eliminar1ActionPerformed

    private void eliminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar2ActionPerformed
        //MODIFICANDO   
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
            
            //boolean resul = TratamientoDB.eliminarTratamiento(tratamientosSelected);
            boolean resul = Categoria2DB.eliminarCategoria2(categoria2Selected);
            if(resul){
                this.updateModelo2(categoria1Selected);
                //this.updateModeloTratamientos(categoria1Selected);
            }
            boolean resul2 = TratamientoDB.eliminarTratamientoCategoria2(categoria2Selected);
            if(resul2){
                this.updateModeloTratamientos(tratamientosSelected);
                //this.updateModeloTratamientos(categoria1Selected);
            }
//            this.editar3.setEnabled(false);
//            this.eliminar3.setEnabled(false);
            this.editar2.setEnabled(false);
            this.eliminar2.setEnabled(false);
        }
    }//GEN-LAST:event_eliminar2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editar1;
    private javax.swing.JButton editar2;
    private javax.swing.JButton editar3;
    private javax.swing.JButton eliminar1;
    private javax.swing.JButton eliminar2;
    private javax.swing.JButton eliminar3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelCategoria;
    private javax.swing.JLabel labelSubCategoria;
    private javax.swing.JLabel labelTratamiento;
    private javax.swing.JButton nuevo1;
    private javax.swing.JButton nuevo2;
    private javax.swing.JButton nuevo3;
    private javax.swing.JTable tablaCat1;
    private javax.swing.JTable tablaCat2;
    private javax.swing.JTable tablaTratamientos;
    // End of variables declaration//GEN-END:variables
    
    private void iniciarTablaCategoria1() {
        this.columnasNombreCat1 = new String [] {"Nombre"};
        this.updateModelo1();
        this.tablaCat1.getTableHeader().setReorderingAllowed(false);
        
        this.tablaCat1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 1) { 
                    Object [] fila = getRowAt1(row);
                    try {
                        categoria1Selected = ((Item)fila[0]).getId();
                        iniciarTablaCategoria2();
                        iniciarTablaTratamientos(-1);
                        editar1.setEnabled(true);
                        eliminar1.setEnabled(true);
                        nuevo2.setEnabled(true);
                        editar2.setEnabled(false);
                        eliminar2.setEnabled(false);
                        nuevo3.setEnabled(false);
                        editar3.setEnabled(false);
                        eliminar3.setEnabled(false);
                    } catch (Exception ex) {
                    }
                }
            }
        });        
    }

    private void iniciarTablaCategoria2() {
        this.columnasNombreCat2 = new String [] {"Nombre"};
        this.updateModelo2(categoria1Selected);
        this.tablaCat2.getTableHeader().setReorderingAllowed(false);
        
        this.tablaCat2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 1) { 
                    Object [] fila = getRowAt2(row);
                    try {
                        categoria2Selected = ((Item)fila[0]).getId();
                        iniciarTablaTratamientos(categoria2Selected);
                        editar2.setEnabled(true);
                        eliminar2.setEnabled(true);
                        nuevo3.setEnabled(true);
                        editar3.setEnabled(false);
                        eliminar3.setEnabled(false);
                    } catch (Exception ex) {
                    }
                }
            }
        }); 
    }
    
    private void iniciarTablaTratamientos(int aux) {
        if(aux==-1){
            this.categoria2Selected = -1;
        }
        this.columnasNombreTratamientos = new String [] {"Nombre","Valor UCO", "Valor Colegio", "Valor Clínica"};
        this.updateModeloTratamientos(categoria2Selected);
        this.tablaTratamientos.getTableHeader().setReorderingAllowed(false);
        
        this.tablaTratamientos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 1) { 
                    Object [] fila = getRowAt3(row);
                    try {
                        tratamientosSelected = ((Item)fila[0]).getId();
                        editar3.setEnabled(true);
                        eliminar3.setEnabled(true);
                    } catch (Exception ex) {
                    }
                }
            }
        }); 
    }
}
