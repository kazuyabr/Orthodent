/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.tratamientos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.TableModel;
import orthodent.JVentana;
import orthodent.usuarios.MostrarInfoTratamiento;

/**
 *
 * @author Msanhuezal
 */
public class Tratamientos extends JPanel implements ActionListener{
    //Esta clase solo la ve el admin!!
    private Image bannerFondo;
    private JButton editarValorUco;
    private JTable tabla;
    private TableModel modelo;
    private Object [][] filas;
    private String [] columnasNombre;
    private JPanel contenedorListarTratamientos;
    private Tablas tablas;
    
    public Tratamientos(){
        this.setBackground(new Color(243,242,243));
        this.setPreferredSize(new Dimension(1073, 561));
        this.setLayout(new BorderLayout());
        this.initComponents();
        this.addComponents();
        
        this.editarValorUco.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    public Tablas getTablas(){
        return this.tablas;
    }
    
    private void initComponents(){
        
        this.contenedorListarTratamientos = new JPanel();
        this.contenedorListarTratamientos.setLayout(new BorderLayout());
        
        this.bannerFondo = new ImageIcon("src/imagenes/directorioTratamientos.png").getImage();
        
        this.editarValorUco = new JButton();
        this.editarValorUco.setForeground(new Color(11, 146, 181));
        this.editarValorUco.setFont(new Font("Georgia", 1, 12));
        this.editarValorUco.setIcon(new ImageIcon("src/imagenes/edit_mini.png"));
        this.editarValorUco.setText("Editar Valor UCO  ");
        this.editarValorUco.setBorder(null);
        this.editarValorUco.setBorderPainted(false);
        this.editarValorUco.setContentAreaFilled(false);
        this.editarValorUco.addActionListener(this);
    }
    
    private void addComponents(){
        JPanel auxiliar = new JPanel();
        auxiliar.setLayout(new BorderLayout());
        
        //Inicio boton agregar
        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(1106, 47));
        panel1.setBackground(new Color(255,255,255));
        this.addComponentPanel1(panel1);
        auxiliar.add(panel1,BorderLayout.NORTH);
        //Fin boton agregar
        
        //Inicio Panel azul, con el nombre del Directorio
        JPanel banner = new JPanel(){
            @Override
            public void paint(Graphics g){
                paintComponent(g);
                g.drawImage(bannerFondo, 0, 0, 1106, 61, this);
            }
        };
        banner.setBackground(new Color(255,255,255));
        banner.setPreferredSize(new Dimension(1106, 61));
        auxiliar.add(banner,BorderLayout.CENTER);
        //Fin Panel azul, con el nombre del Directorio
        
        this.contenedorListarTratamientos.add(auxiliar,BorderLayout.NORTH);
        
        //Inicio tabla
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this.tabla);
        this.tablas = new Tablas();
        this.contenedorListarTratamientos.add(this.tablas,BorderLayout.CENTER);
        //Fin tabla
        
        this.add(this.contenedorListarTratamientos,BorderLayout.CENTER);
    }
    
    private void addComponentPanel1(JPanel panel1){
        
        GroupLayout groupLayout = new GroupLayout(panel1);
        panel1.setLayout(groupLayout);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(this.editarValorUco)
                .addContainerGap())
        );
        
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.editarValorUco))
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == this.editarValorUco){
            try {
                new EditarValorUco(((JVentana)this.getTopLevelAncestor()),true).setVisible(true);
                this.getTablas().updateTablas();
                this.tablas.updateUI();
            } catch (Exception ex) {
                Logger.getLogger(Tratamientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
