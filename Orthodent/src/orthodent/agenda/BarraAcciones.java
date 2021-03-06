/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.agenda;

import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import modelo.Usuario;
import orthodent.Item;
import orthodent.db.Autenticacion;

/**
 *
 * @author Lucy
 */
public class BarraAcciones extends javax.swing.JPanel {

    /**
     * Creates new form BarraAcciones
     */
    private Usuario usuarioActual;
    private Agenda contenedor;
    public BarraAcciones(Usuario usuario, Agenda cont) {
        this.usuarioActual = usuario;
        this.contenedor = cont;
        initComponents();
        initProfesionales();
        //this.fechaAgenda.setDate(new Date());
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        profesionales = new javax.swing.JComboBox();
        fechaAgenda = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        hoy = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        buscar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(11, 146, 181));
        jLabel1.setText("Profesional");

        profesionales.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        profesionales.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        profesionales.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                profesionalesItemStateChanged(evt);
            }
        });
        profesionales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profesionalesActionPerformed(evt);
            }
        });

        fechaAgenda.setBackground(new java.awt.Color(255, 255, 255));
        fechaAgenda.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        fechaAgenda.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fechaAgendaPropertyChange(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(11, 146, 181));
        jLabel2.setText("Fecha");
        jLabel2.setToolTipText("");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
        });

        hoy.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        hoy.setText("Actual");
        hoy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoyActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/navigate-left_mini.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setPreferredSize(new java.awt.Dimension(65, 65));
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/navigate-left_mini_pressed.png"))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/navigate-right_mini2.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setPreferredSize(new java.awt.Dimension(65, 65));
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/navigate-right_mini2_pressed.png"))); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/printer_mini.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/printer_mini_pressed.png"))); // NOI18N
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(11, 146, 181));
        jLabel3.setText("Semanas");

        jLabel4.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(11, 146, 181));
        jLabel4.setText("-");

        jLabel5.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(11, 146, 181));
        jLabel5.setText("+");

        buscar.setBackground(new java.awt.Color(255, 255, 255));
        buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa_mini.png"))); // NOI18N
        buscar.setContentAreaFilled(false);
        buscar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa_mini_pressed.png"))); // NOI18N
        buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(51, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(profesionales, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 45, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(fechaAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(hoy)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5))
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hoy)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(profesionales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton3)
                    .addComponent(buscar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fechaAgendaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fechaAgendaPropertyChange
        // TODO add your handling code here:
        if(this.fechaAgenda.getDate()!=null){
            //System.out.println("ACA TENGO QUE CAMBIAR "+this.contenedor.obtenerSemana(this.fechaAgenda.getDate()));
            this.contenedor.cambiarSemanaDeAgenda(this.fechaAgenda.getDate());
        }
    }//GEN-LAST:event_fechaAgendaPropertyChange

    private void hoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoyActionPerformed
        // TODO add your handling code here:
        Date hoy = new Date();
        this.fechaAgenda.setDate(this.contenedor.obtenerLunes(hoy));
        
    }//GEN-LAST:event_hoyActionPerformed

    private void profesionalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profesionalesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profesionalesActionPerformed

    private void profesionalesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_profesionalesItemStateChanged
        // TODO add your handling code here:
        if(this.fechaAgenda.getDate()!=null)
            this.contenedor.cambiarProfesional(this.fechaAgenda.getDate());
    }//GEN-LAST:event_profesionalesItemStateChanged

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        this.contenedor.retrocederSemana();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        this.contenedor.avanzarSemana();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        this.contenedor.printComponent();
    }//GEN-LAST:event_jButton3MouseClicked

    private void buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarMouseClicked
        BuscarCita bc = new BuscarCita(((JFrame)this.contenedor.getTopLevelAncestor()),false, contenedor.getModelo());
        bc.setVisible(true);
    }//GEN-LAST:event_buscarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar;
    private com.toedter.calendar.JDateChooser fechaAgenda;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton hoy;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox profesionales;
    // End of variables declaration//GEN-END:variables

    public void initProfesionales() {
        ArrayList<Usuario> usuarios = null;
        if(this.usuarioActual.getId_rol()==4)
            usuarios = Autenticacion.listarProfesionales(this.usuarioActual.getId_clinica());
        else if(this.usuarioActual.getId_rol()==3)
            usuarios = Autenticacion.listarProfesional(this.usuarioActual.getId_usuario());
        else
            usuarios = Autenticacion.listarProfesionales();
            
        if(usuarios!=null && usuarios.size()>0){
            
            if(this.usuarioActual.getId_rol()==3){
                Vector model = new Vector();
                Item item = null;
                String name = this.usuarioActual.getNombre();

                if(name.contains(" ")){
                    name = name.substring(0,name.indexOf(" "));
                }
                name = name + " " + this.usuarioActual.getApellido_pat();
                item = new Item(name,usuarioActual.getId_usuario());
                model.addElement(item);
                profesionales.setModel(new DefaultComboBoxModel(model));
                profesionales.setSelectedItem(item);
                profesionales.setEnabled(false);
            }
            else{
                Vector model = new Vector();
                //tem item = null;
                for(Usuario user : usuarios){
                    String name = user.getNombre();

                    if(name.contains(" ")){
                        name = name.substring(0,name.indexOf(" "));
                    }
                    name = name + " " + user.getApellido_pat();
                    model.addElement(new Item(name,user.getId_usuario()));
                }
                profesionales.setModel(new DefaultComboBoxModel(model));
                profesionales.setSelectedIndex(0);
                //profesionales.setSelectedItem(item);
            }
        }
        else{
            Vector model = new Vector();
            profesionales.setModel(new DefaultComboBoxModel(model));
            //profesionales.setSelectedItem(item);
            profesionales.setEnabled(false);
        }
        
        
    }

    public int getIdProfesional(){
        return ((Item)profesionales.getSelectedItem()).getId();
    }
    
    public void setFechaAgenda(Date fecha){
        this.fechaAgenda.setDate(fecha);
    }
    
    public Date getFechaAgenda(){
        return this.fechaAgenda.getDate();
    }
    
    public JDateChooser getDateChooser(){
        return this.fechaAgenda;
    }

    public JComboBox getProfesionales() {
        return profesionales;
    }

    public void setProfesionales(JComboBox profesionales) {
        this.profesionales = profesionales;
    }

    
    
}
