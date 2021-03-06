/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package orthodent.agenda;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.Paciente;
import orthodent.db.AgendaDB;
import orthodent.db.PacienteDB;

/**
 *
 * @author Maha
 */
public class BuscarCita extends javax.swing.JDialog {

    /**
     * Creates new form BuscarCita
     */
    private AgendaSchedulerModel modelo;
    public BuscarCita(java.awt.Frame parent, boolean modal, AgendaSchedulerModel modelo) {
        super(parent, modal);
        initComponents();
        this.modelo = modelo;
        this.centrarVentana();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campoBusqueda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEncontrados = new javax.swing.JTable();
        volver = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        aviso = new javax.swing.JLabel();
        todas = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        campoBusqueda.setColumns(1);
        campoBusqueda.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        campoBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoBusquedaActionPerformed(evt);
            }
        });
        campoBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoBusquedaKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel1.setText("Ingrese Su Búsqueda");

        tablaEncontrados.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        tablaEncontrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rut", "Nombre", "Fecha", "Hora"
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
        tablaEncontrados.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaEncontrados);

        volver.setText("Cerrar");
        volver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                volverMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel2.setText("Resultados");

        aviso.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        aviso.setForeground(new java.awt.Color(255, 0, 0));
        aviso.setText(" ");

        todas.setText("Todas");
        todas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                todasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(aviso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(volver))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(campoBusqueda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(todas)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(todas))
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(volver)
                    .addComponent(aviso))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoBusquedaActionPerformed

    private void campoBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoBusquedaKeyTyped
        char c = evt.getKeyChar();
        
        if(c==KeyEvent.VK_ENTER){
            evt.consume();
            this.updateModelo(this.campoBusqueda.getText());
        }
        else if(c==KeyEvent.VK_BACK_SPACE){
            if(this.campoBusqueda.getText().equals(""))
                this.updateModelo(this.campoBusqueda.getText());
        }
        
        
    }//GEN-LAST:event_campoBusquedaKeyTyped

    private void volverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverMouseClicked
        this.dispose();
    }//GEN-LAST:event_volverMouseClicked

    private void todasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_todasMouseClicked
        this.updateModelo(this.campoBusqueda.getText());
    }//GEN-LAST:event_todasMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BuscarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BuscarCita dialog = new BuscarCita(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    private void updateModelo(String buscado){
        System.out.println("Buscando +"+buscado+"+");
        if(!buscado.equals("")){
            ArrayList<Cita> citas = null;
            citas = AgendaDB.buscarCitas(buscado, modelo, this.todas.isSelected());
            if(citas!=null){
                this.aviso.setText(" ");
                int col = 4;
                String[] columnas = new String [] {"Rut", "Nombre", "Fecha", "Hora"};
                ArrayList<Object []> objetos = new ArrayList<Object []>();
                for(Cita c : citas){
                    Paciente p = null;
                    try {
                        p = PacienteDB.getPaciente(c.getPacienteId());
                    } catch (Exception ex) {
                        Logger.getLogger(BuscarCita.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Object [] fila = new Object [] {p.getRut() ,p.getNombre()+" "+p.getApellido_pat()+" "+p.getApellido_pat(), c.getFecha(), c.getRealDateTime().toString("H:m")};
                    objetos.add(fila);
                }

                Object[][] fil = new Object [objetos.size()][col];
                int i = 0;
                for(Object [] o : objetos){
                    fil[i] = o;
                    i++;
                }
                this.tablaEncontrados.setModel(new DefaultTableModel(fil, columnas){
                    Class[] types = new Class [] {
                        String.class, String.class, String.class, String.class
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

            }
            else{
                this.aviso.setText("No se encontró ninguna coincidencia");
                String[] columnas = new String [] {"Rut", "Nombre", "Fecha", "Hora"};
                Object[][] fil = new Object [0][3];
                this.tablaEncontrados.setModel( new DefaultTableModel(fil, columnas){
                    Class[] types = new Class [] {
                        String.class, String.class, String.class, String.class
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
            }
        }
        else{
            String[] columnas = new String [] {"Rut", "Nombre", "Fecha", "Hora"};
            Object[][] fil = new Object [0][3];
            this.tablaEncontrados.setModel( new DefaultTableModel(fil, columnas){
                    Class[] types = new Class [] {
                        String.class, String.class, String.class, String.class
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
        }
    }
    
    private void centrarVentana(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getSize().width) / 2 ,
                (screenSize.height - this.getSize().height) / 2);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aviso;
    private javax.swing.JTextField campoBusqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaEncontrados;
    private javax.swing.JCheckBox todas;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
