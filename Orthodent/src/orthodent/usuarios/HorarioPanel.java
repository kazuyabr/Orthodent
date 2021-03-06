/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.usuarios;

import java.awt.Cursor;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.Horario;
import modelo.Usuario;
import orthodent.db.HorarioDB;

/**
 *
 * @author Mary
 */
public class HorarioPanel extends JPanel{

    private Usuario usuario;
    private boolean cambios;
    
    public HorarioPanel(Usuario usuario) {
        initComponents();
        
        this.usuario = usuario;
        
        this.addInfo();
        this.guardar.setEnabled(false);
        this.guardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.cambios = false;
    }
    
    private void addInfo(){
        ArrayList<Horario> horarios = HorarioDB.getHorarios(this.usuario.getId_usuario());
        
        if(horarios!=null){
            for(Horario o : horarios){
                
                String dia = o.getDia();
                
                if(dia.equals("Lunes")){
                    this.lunes.setSelected(true);
                    
                    String horaInicio = o.getHoraInicio();
                    this.inicioHoraLunes.setSelectedItem(horaInicio);
                    
                    String minInicio = o.getMinInicio();
                    this.inicioMinLunes.setSelectedItem(minInicio);
                    
                    String horaFin = o.getHoraFin();
                    this.finHoraLunes.setSelectedItem(horaFin);
                    
                    String minFin = o.getMinFin();
                    this.finMinLunes.setSelectedItem(minFin);
                    
                    this.inicioHoraLunes.setEnabled(true);
                    this.inicioMinLunes.setEnabled(true);
                    this.finHoraLunes.setEnabled(true);
                    this.finMinLunes.setEnabled(true);
                }
                else if(dia.equals("Martes")){
                    this.martes.setSelected(true);
                    
                    String horaInicio = o.getHoraInicio();
                    this.inicioHoraMartes.setSelectedItem(horaInicio);
                    
                    String minInicio = o.getMinInicio();
                    this.inicioMinMartes.setSelectedItem(minInicio);
                    
                    String horaFin = o.getHoraFin();
                    this.finHoraMartes.setSelectedItem(horaFin);
                    
                    String minFin = o.getMinFin();
                    this.finMinMartes.setSelectedItem(minFin);
                    
                    this.inicioHoraMartes.setEnabled(true);
                    this.inicioMinMartes.setEnabled(true);
                    this.finHoraMartes.setEnabled(true);
                    this.finMinMartes.setEnabled(true);
                }
                else if(dia.equals("Miercoles")){
                    this.miercoles.setSelected(true);
                    
                    String horaInicio = o.getHoraInicio();
                    this.inicioHoraMiercoles.setSelectedItem(horaInicio);
                    
                    String minInicio = o.getMinInicio();
                    this.inicioMinMiercoles.setSelectedItem(minInicio);
                    
                    String horaFin = o.getHoraFin();
                    this.finHoraMiercoles.setSelectedItem(horaFin);
                    
                    String minFin = o.getMinFin();
                    this.finMinMiercoles.setSelectedItem(minFin);
                    
                    this.inicioHoraMiercoles.setEnabled(true);
                    this.inicioMinMiercoles.setEnabled(true);
                    this.finHoraMiercoles.setEnabled(true);
                    this.finMinMiercoles.setEnabled(true);
                }
                else if(dia.equals("Jueves")){
                    this.jueves.setSelected(true);
                    
                    String horaInicio = o.getHoraInicio();
                    this.inicioHoraJueves.setSelectedItem(horaInicio);
                    
                    String minInicio = o.getMinInicio();
                    this.inicioMinJueves.setSelectedItem(minInicio);
                    
                    String horaFin = o.getHoraFin();
                    this.finHoraJueves.setSelectedItem(horaFin);
                    
                    String minFin = o.getMinFin();
                    this.finMinJueves.setSelectedItem(minFin);
                    
                    this.inicioHoraJueves.setEnabled(true);
                    this.inicioMinJueves.setEnabled(true);
                    this.finHoraJueves.setEnabled(true);
                    this.finMinJueves.setEnabled(true);
                }
                else if(dia.equals("Viernes")){
                    this.viernes.setSelected(true);
                    
                    String horaInicio = o.getHoraInicio();
                    this.inicioHoraViernes.setSelectedItem(horaInicio);
                    
                    String minInicio = o.getMinInicio();
                    this.inicioMinViernes.setSelectedItem(minInicio);
                    
                    String horaFin = o.getHoraFin();
                    this.finHoraViernes.setSelectedItem(horaFin);
                    
                    String minFin = o.getMinFin();
                    this.finMinViernes.setSelectedItem(minFin);
                    
                    this.inicioHoraViernes.setEnabled(true);
                    this.inicioMinViernes.setEnabled(true);
                    this.finHoraViernes.setEnabled(true);
                    this.finMinViernes.setEnabled(true);
                }
                else{
                    this.sabado.setSelected(true);
                    
                    String horaInicio = o.getHoraInicio();
                    this.inicioHoraSabado.setSelectedItem(horaInicio);
                    
                    String minInicio = o.getMinInicio();
                    this.inicioMinSabado.setSelectedItem(minInicio);
                    
                    String horaFin = o.getHoraFin();
                    this.finHoraSabado.setSelectedItem(horaFin);
                    
                    String minFin = o.getMinFin();
                    this.finMinSabado.setSelectedItem(minFin);
                    
                    this.inicioHoraSabado.setEnabled(true);
                    this.inicioMinSabado.setEnabled(true);
                    this.finHoraSabado.setEnabled(true);
                    this.finMinSabado.setEnabled(true);
                }
            }
        }
    }
    
    public boolean getCambios(){
        return this.cambios;
    }
    
    public void setCambios(boolean cambios){
        this.cambios = cambios;
    }
    
    public void guardar(){
        this.guardarActionPerformed(null);
    }
    
    private void habilitarBoton(){
        this.cambios = true;
        this.guardar.setEnabled(true);
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
        martes = new javax.swing.JCheckBox();
        lunes = new javax.swing.JCheckBox();
        miercoles = new javax.swing.JCheckBox();
        jueves = new javax.swing.JCheckBox();
        viernes = new javax.swing.JCheckBox();
        sabado = new javax.swing.JCheckBox();
        labelInicio = new javax.swing.JLabel();
        labelFin = new javax.swing.JLabel();
        inicioHoraLunes = new javax.swing.JComboBox();
        inicioMinLunes = new javax.swing.JComboBox();
        finHoraLunes = new javax.swing.JComboBox();
        finMinLunes = new javax.swing.JComboBox();
        inicioHoraMartes = new javax.swing.JComboBox();
        inicioMinMartes = new javax.swing.JComboBox();
        finHoraMartes = new javax.swing.JComboBox();
        finMinMartes = new javax.swing.JComboBox();
        inicioHoraMiercoles = new javax.swing.JComboBox();
        inicioMinMiercoles = new javax.swing.JComboBox();
        finHoraMiercoles = new javax.swing.JComboBox();
        finMinMiercoles = new javax.swing.JComboBox();
        inicioHoraJueves = new javax.swing.JComboBox();
        inicioMinJueves = new javax.swing.JComboBox();
        finHoraJueves = new javax.swing.JComboBox();
        finMinJueves = new javax.swing.JComboBox();
        inicioHoraViernes = new javax.swing.JComboBox();
        inicioMinViernes = new javax.swing.JComboBox();
        finHoraViernes = new javax.swing.JComboBox();
        finMinViernes = new javax.swing.JComboBox();
        inicioHoraSabado = new javax.swing.JComboBox();
        inicioMinSabado = new javax.swing.JComboBox();
        finHoraSabado = new javax.swing.JComboBox();
        finMinSabado = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(850, 551));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setPreferredSize(new java.awt.Dimension(845, 545));

        panelTitulo.setBackground(new java.awt.Color(247, 243, 248));
        panelTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(232, 230, 234), 1, true));

        labelTitulo.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(163, 159, 164));
        labelTitulo.setText("Horario");

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
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        martes.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        martes.setText("Martes");
        martes.setBorder(null);
        martes.setContentAreaFilled(false);
        martes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                martesActionPerformed(evt);
            }
        });

        lunes.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        lunes.setText("Lunes");
        lunes.setBorder(null);
        lunes.setContentAreaFilled(false);
        lunes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lunesActionPerformed(evt);
            }
        });

        miercoles.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        miercoles.setText("Miércoles");
        miercoles.setBorder(null);
        miercoles.setContentAreaFilled(false);
        miercoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miercolesActionPerformed(evt);
            }
        });

        jueves.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jueves.setText("Jueves");
        jueves.setBorder(null);
        jueves.setContentAreaFilled(false);
        jueves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                juevesActionPerformed(evt);
            }
        });

        viernes.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        viernes.setText("Viernes");
        viernes.setBorder(null);
        viernes.setContentAreaFilled(false);
        viernes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viernesActionPerformed(evt);
            }
        });

        sabado.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        sabado.setText("Sábado");
        sabado.setBorder(null);
        sabado.setContentAreaFilled(false);
        sabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sabadoActionPerformed(evt);
            }
        });

        labelInicio.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        labelInicio.setText("Inicio");

        labelFin.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        labelFin.setText("Fin");

        inicioHoraLunes.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        inicioHoraLunes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
        inicioHoraLunes.setEnabled(false);
        inicioHoraLunes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inicioHoraLunesItemStateChanged(evt);
            }
        });
        inicioHoraLunes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioHoraLunesActionPerformed(evt);
            }
        });

        inicioMinLunes.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        inicioMinLunes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
        inicioMinLunes.setEnabled(false);
        inicioMinLunes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inicioMinLunesItemStateChanged(evt);
            }
        });

        finHoraLunes.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        finHoraLunes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
        finHoraLunes.setEnabled(false);
        finHoraLunes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                finHoraLunesItemStateChanged(evt);
            }
        });

        finMinLunes.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        finMinLunes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
        finMinLunes.setEnabled(false);
        finMinLunes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                finMinLunesItemStateChanged(evt);
            }
        });

        inicioHoraMartes.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        inicioHoraMartes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
        inicioHoraMartes.setEnabled(false);
        inicioHoraMartes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inicioHoraMartesItemStateChanged(evt);
            }
        });

        inicioMinMartes.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        inicioMinMartes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
        inicioMinMartes.setEnabled(false);
        inicioMinMartes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inicioMinMartesItemStateChanged(evt);
            }
        });

        finHoraMartes.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        finHoraMartes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
        finHoraMartes.setEnabled(false);
        finHoraMartes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                finHoraMartesItemStateChanged(evt);
            }
        });

        finMinMartes.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        finMinMartes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
        finMinMartes.setEnabled(false);
        finMinMartes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                finMinMartesItemStateChanged(evt);
            }
        });

        inicioHoraMiercoles.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        inicioHoraMiercoles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
        inicioHoraMiercoles.setEnabled(false);
        inicioHoraMiercoles.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inicioHoraMiercolesItemStateChanged(evt);
            }
        });

        inicioMinMiercoles.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        inicioMinMiercoles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
        inicioMinMiercoles.setEnabled(false);
        inicioMinMiercoles.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inicioMinMiercolesItemStateChanged(evt);
            }
        });

        finHoraMiercoles.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        finHoraMiercoles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
        finHoraMiercoles.setEnabled(false);
        finHoraMiercoles.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                finHoraMiercolesItemStateChanged(evt);
            }
        });

        finMinMiercoles.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        finMinMiercoles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
        finMinMiercoles.setEnabled(false);
        finMinMiercoles.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                finMinMiercolesItemStateChanged(evt);
            }
        });

        inicioHoraJueves.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        inicioHoraJueves.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
        inicioHoraJueves.setEnabled(false);
        inicioHoraJueves.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inicioHoraJuevesItemStateChanged(evt);
            }
        });

        inicioMinJueves.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        inicioMinJueves.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
        inicioMinJueves.setEnabled(false);
        inicioMinJueves.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inicioMinJuevesItemStateChanged(evt);
            }
        });

        finHoraJueves.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        finHoraJueves.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
        finHoraJueves.setEnabled(false);
        finHoraJueves.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                finHoraJuevesItemStateChanged(evt);
            }
        });

        finMinJueves.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        finMinJueves.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
        finMinJueves.setEnabled(false);
        finMinJueves.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                finMinJuevesItemStateChanged(evt);
            }
        });

        inicioHoraViernes.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        inicioHoraViernes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
        inicioHoraViernes.setEnabled(false);
        inicioHoraViernes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inicioHoraViernesItemStateChanged(evt);
            }
        });
        inicioHoraViernes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioHoraViernesActionPerformed(evt);
            }
        });

        inicioMinViernes.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        inicioMinViernes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
        inicioMinViernes.setEnabled(false);
        inicioMinViernes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inicioMinViernesItemStateChanged(evt);
            }
        });

        finHoraViernes.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        finHoraViernes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
        finHoraViernes.setEnabled(false);
        finHoraViernes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                finHoraViernesItemStateChanged(evt);
            }
        });

        finMinViernes.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        finMinViernes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
        finMinViernes.setEnabled(false);
        finMinViernes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                finMinViernesItemStateChanged(evt);
            }
        });

        inicioHoraSabado.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        inicioHoraSabado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09", "10", "11", "12" }));
        inicioHoraSabado.setEnabled(false);
        inicioHoraSabado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inicioHoraSabadoItemStateChanged(evt);
            }
        });
        inicioHoraSabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioHoraSabadoActionPerformed(evt);
            }
        });

        inicioMinSabado.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        inicioMinSabado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
        inicioMinSabado.setEnabled(false);
        inicioMinSabado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inicioMinSabadoItemStateChanged(evt);
            }
        });
        inicioMinSabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioMinSabadoActionPerformed(evt);
            }
        });

        finHoraSabado.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        finHoraSabado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09", "10", "11", "12" }));
        finHoraSabado.setEnabled(false);
        finHoraSabado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                finHoraSabadoItemStateChanged(evt);
            }
        });

        finMinSabado.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        finMinSabado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
        finMinSabado.setEnabled(false);
        finMinSabado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                finMinSabadoItemStateChanged(evt);
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
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(miercoles)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lunes)
                            .addComponent(martes)
                            .addComponent(jueves)
                            .addComponent(viernes)
                            .addComponent(sabado))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inicioHoraMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inicioHoraMartes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inicioHoraLunes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inicioHoraJueves, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inicioHoraViernes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inicioHoraSabado, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(inicioMinLunes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(inicioMinMartes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(inicioMinMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(inicioMinJueves, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inicioMinViernes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inicioMinSabado, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(finHoraSabado, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(finMinSabado, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(finHoraViernes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(finMinViernes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(finHoraLunes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(finHoraMartes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(finHoraMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(finMinLunes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(finMinMartes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(finMinMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(finHoraJueves, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(finMinJueves, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(labelInicio)
                        .addGap(109, 109, 109)
                        .addComponent(labelFin)))
                .addContainerGap(456, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(guardar)
                .addGap(83, 83, 83))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelInicio)
                    .addComponent(labelFin))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(finMinLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finHoraLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inicioMinLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inicioHoraLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lunes))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(finMinMartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(finHoraMartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(finMinMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(finHoraMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inicioMinMartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inicioHoraMartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(martes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inicioMinMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inicioHoraMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(miercoles))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inicioHoraJueves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inicioMinJueves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finHoraJueves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finMinJueves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jueves))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inicioHoraViernes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inicioMinViernes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finHoraViernes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finMinViernes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viernes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inicioHoraSabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inicioMinSabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finHoraSabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finMinSabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sabado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                .addComponent(guardar)
                .addContainerGap())
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
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private int convertToMin(String hora){
        
        int min = Integer.parseInt(hora);
        
        min = min*60;
        
        return min;
    }
    
    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        
        try {
            boolean aux = HorarioDB.eliminarHorario(this.usuario.getId_usuario());
        } catch (SQLException ex) {
        }
        boolean cmbs = false;
        if(this.lunes.isSelected()){
            int horaInicio = this.convertToMin((String)this.inicioHoraLunes.getSelectedItem());
            horaInicio = horaInicio + Integer.parseInt((String)this.inicioMinLunes.getSelectedItem());
            
            int horaFin = this.convertToMin((String)this.finHoraLunes.getSelectedItem());
            horaFin = horaFin + Integer.parseInt((String)this.finMinLunes.getSelectedItem());
            if(horaInicio<horaFin){
                HorarioDB.crearHorario(this.usuario.getId_usuario(), "Lunes", horaInicio, horaFin);
                cmbs = true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Ingrese un horario valido");
                cmbs = false;
            }
        }
        if(this.martes.isSelected()){
            int horaInicio = this.convertToMin((String)this.inicioHoraMartes.getSelectedItem());
            horaInicio = horaInicio + Integer.parseInt((String)this.inicioMinMartes.getSelectedItem());
            
            int horaFin = this.convertToMin((String)this.finHoraMartes.getSelectedItem());
            horaFin = horaFin + Integer.parseInt((String)this.finMinMartes.getSelectedItem());
            if(horaInicio<horaFin){
                HorarioDB.crearHorario(this.usuario.getId_usuario(), "Martes", horaInicio, horaFin);
                cmbs = true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Ingrese un horario valido");
                cmbs = false;
            }
        }
        if(this.miercoles.isSelected()){
            int horaInicio = this.convertToMin((String)this.inicioHoraMiercoles.getSelectedItem());
            horaInicio = horaInicio + Integer.parseInt((String)this.inicioMinMiercoles.getSelectedItem());
            
            int horaFin = this.convertToMin((String)this.finHoraMiercoles.getSelectedItem());
            horaFin = horaFin + Integer.parseInt((String)this.finMinMiercoles.getSelectedItem());
            if(horaInicio<horaFin){
                HorarioDB.crearHorario(this.usuario.getId_usuario(), "Miercoles", horaInicio, horaFin);
                cmbs = true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Ingrese un horario valido");
                cmbs = false;
            }
        }
        if(this.jueves.isSelected()){
            int horaInicio = this.convertToMin((String)this.inicioHoraJueves.getSelectedItem());
            horaInicio = horaInicio + Integer.parseInt((String)this.inicioMinJueves.getSelectedItem());
            
            int horaFin = this.convertToMin((String)this.finHoraJueves.getSelectedItem());
            horaFin = horaFin + Integer.parseInt((String)this.finMinJueves.getSelectedItem());
            if(horaInicio<horaFin){
                HorarioDB.crearHorario(this.usuario.getId_usuario(), "Jueves", horaInicio, horaFin);
                cmbs = true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Ingrese un horario valido");
                cmbs = false;
            }
        }
        if(this.viernes.isSelected()){
            int horaInicio = this.convertToMin((String)this.inicioHoraViernes.getSelectedItem());
            horaInicio = horaInicio + Integer.parseInt((String)this.inicioMinViernes.getSelectedItem());
            
            int horaFin = this.convertToMin((String)this.finHoraViernes.getSelectedItem());
            horaFin = horaFin + Integer.parseInt((String)this.finMinViernes.getSelectedItem());
            if(horaInicio<horaFin){
                HorarioDB.crearHorario(this.usuario.getId_usuario(), "Viernes", horaInicio, horaFin);
                cmbs = true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Ingrese un horario valido");
                cmbs = false;
            }
        }
        if(this.sabado.isSelected()){
            int horaInicio = this.convertToMin((String)this.inicioHoraSabado.getSelectedItem());
            horaInicio = horaInicio + Integer.parseInt((String)this.inicioMinSabado.getSelectedItem());
            
            int horaFin = this.convertToMin((String)this.finHoraSabado.getSelectedItem());
            horaFin = horaFin + Integer.parseInt((String)this.finMinSabado.getSelectedItem());
            
            if(horaInicio<horaFin){
                HorarioDB.crearHorario(this.usuario.getId_usuario(), "Sabado", horaInicio, horaFin);
                cmbs = true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Ingrese un horario valido");
                cmbs = false;
            }
            
        }
        if(cmbs){
            this.cambios = false;
            this.guardar.setEnabled(false);
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void lunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lunesActionPerformed
        this.habilitarBoton();
        
        if(this.lunes.isSelected()){
            //Ahora lo marco
            this.inicioHoraLunes.setEnabled(true);
            this.inicioMinLunes.setEnabled(true);
            this.finHoraLunes.setEnabled(true);
            this.finMinLunes.setEnabled(true);
        }
        else{
            //Ahora lo desmarco
            this.inicioHoraLunes.setEnabled(false);
            this.inicioMinLunes.setEnabled(false);
            this.finHoraLunes.setEnabled(false);
            this.finMinLunes.setEnabled(false);
        }
    }//GEN-LAST:event_lunesActionPerformed

    private void martesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_martesActionPerformed
        this.habilitarBoton();
        
        if(this.martes.isSelected()){
            //Ahora lo marco
            this.inicioHoraMartes.setEnabled(true);
            this.inicioMinMartes.setEnabled(true);
            this.finHoraMartes.setEnabled(true);
            this.finMinMartes.setEnabled(true);
        }
        else{
            //Ahora lo desmarco
            this.inicioHoraMartes.setEnabled(false);
            this.inicioMinMartes.setEnabled(false);
            this.finHoraMartes.setEnabled(false);
            this.finMinMartes.setEnabled(false);
        }
    }//GEN-LAST:event_martesActionPerformed

    private void miercolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miercolesActionPerformed
        this.habilitarBoton();
        
        if(this.miercoles.isSelected()){
            //Ahora lo marco
            this.inicioHoraMiercoles.setEnabled(true);
            this.inicioMinMiercoles.setEnabled(true);
            this.finHoraMiercoles.setEnabled(true);
            this.finMinMiercoles.setEnabled(true);
        }
        else{
            //Ahora lo desmarco
            this.inicioHoraMiercoles.setEnabled(false);
            this.inicioMinMiercoles.setEnabled(false);
            this.finHoraMiercoles.setEnabled(false);
            this.finMinMiercoles.setEnabled(false);
        }
    }//GEN-LAST:event_miercolesActionPerformed

    private void juevesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_juevesActionPerformed
        this.habilitarBoton();
        
        if(this.jueves.isSelected()){
            //Ahora lo marco
            this.inicioHoraJueves.setEnabled(true);
            this.inicioMinJueves.setEnabled(true);
            this.finHoraJueves.setEnabled(true);
            this.finMinJueves.setEnabled(true);
        }
        else{
            //Ahora lo desmarco
            this.inicioHoraJueves.setEnabled(false);
            this.inicioMinJueves.setEnabled(false);
            this.finHoraJueves.setEnabled(false);
            this.finMinJueves.setEnabled(false);
        }
    }//GEN-LAST:event_juevesActionPerformed

    private void viernesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viernesActionPerformed
        this.habilitarBoton();
        
        if(this.viernes.isSelected()){
            //Ahora lo marco
            this.inicioHoraViernes.setEnabled(true);
            this.inicioMinViernes.setEnabled(true);
            this.finHoraViernes.setEnabled(true);
            this.finMinViernes.setEnabled(true);
        }
        else{
            //Ahora lo desmarco
            this.inicioHoraViernes.setEnabled(false);
            this.inicioMinViernes.setEnabled(false);
            this.finHoraViernes.setEnabled(false);
            this.finMinViernes.setEnabled(false);
        }
    }//GEN-LAST:event_viernesActionPerformed

    private void sabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sabadoActionPerformed
        this.habilitarBoton();
        
        if(this.sabado.isSelected()){
            //Ahora lo marco
            this.inicioHoraSabado.setEnabled(true);
            this.inicioMinSabado.setEnabled(true);
            this.finHoraSabado.setEnabled(true);
            this.finMinSabado.setEnabled(true);
        }
        else{
            //Ahora lo desmarco
            this.inicioHoraSabado.setEnabled(false);
            this.inicioMinSabado.setEnabled(false);
            this.finHoraSabado.setEnabled(false);
            this.finMinSabado.setEnabled(false);
        }
    }//GEN-LAST:event_sabadoActionPerformed

    private void inicioHoraSabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioHoraSabadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inicioHoraSabadoActionPerformed

    private void inicioMinSabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioMinSabadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inicioMinSabadoActionPerformed

    private void inicioHoraViernesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioHoraViernesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inicioHoraViernesActionPerformed

    private void inicioHoraSabadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inicioHoraSabadoItemStateChanged
        this.finHoraSabado.setSelectedIndex(this.inicioHoraSabado.getSelectedIndex());
        this.finMinSabado.setSelectedIndex(1);
        this.habilitarBoton();
    }//GEN-LAST:event_inicioHoraSabadoItemStateChanged

    private void inicioHoraLunesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inicioHoraLunesItemStateChanged
        this.finHoraLunes.setSelectedIndex(this.inicioHoraLunes.getSelectedIndex());
        this.finMinLunes.setSelectedIndex(1);
        this.habilitarBoton();
    }//GEN-LAST:event_inicioHoraLunesItemStateChanged

    private void inicioMinLunesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inicioMinLunesItemStateChanged
        if(this.inicioMinLunes.getSelectedIndex() >= 11){
            this.finHoraLunes.setSelectedIndex(this.inicioHoraLunes.getSelectedIndex()+1);
            this.finMinLunes.setSelectedIndex(0);        
        }
        else{
            this.finHoraLunes.setSelectedIndex(this.inicioHoraLunes.getSelectedIndex());
            this.finMinLunes.setSelectedIndex(this.inicioMinLunes.getSelectedIndex()+1);
        }
        this.habilitarBoton();
    }//GEN-LAST:event_inicioMinLunesItemStateChanged

    private void finHoraLunesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_finHoraLunesItemStateChanged
        if(Integer.parseInt(this.finHoraLunes.getSelectedItem().toString())==19){
            this.finMinLunes.removeAllItems();
            int i = 0;
            while(i<=30){
                if(i<10) this.finMinLunes.addItem("0"+i);
                else this.finMinLunes.addItem(""+i);
                i+=5;
            }
        }
        else{
            this.finMinLunes.removeAllItems();
            int i = 0;
            while(i<=55){
                if(i<10) this.finMinLunes.addItem("0"+i);
                else this.finMinLunes.addItem(""+i);
                i+=5;
            }
        }
        this.habilitarBoton();
    }//GEN-LAST:event_finHoraLunesItemStateChanged

    private void finMinLunesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_finMinLunesItemStateChanged
        this.habilitarBoton();
    }//GEN-LAST:event_finMinLunesItemStateChanged

    private void inicioHoraMartesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inicioHoraMartesItemStateChanged
        this.finHoraMartes.setSelectedIndex(this.inicioHoraMartes.getSelectedIndex());
        this.finMinMartes.setSelectedIndex(1);
        this.habilitarBoton();
    }//GEN-LAST:event_inicioHoraMartesItemStateChanged

    private void inicioMinMartesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inicioMinMartesItemStateChanged
        if(this.inicioMinMartes.getSelectedIndex() >= 11){
            this.finHoraMartes.setSelectedIndex(this.inicioHoraMartes.getSelectedIndex()+1);
            this.finMinMartes.setSelectedIndex(0);        
        }
        else{
            this.finHoraMartes.setSelectedIndex(this.inicioHoraMartes.getSelectedIndex());
            this.finMinMartes.setSelectedIndex(this.inicioMinMartes.getSelectedIndex()+1);
        }
        this.habilitarBoton();
    }//GEN-LAST:event_inicioMinMartesItemStateChanged

    private void finHoraMartesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_finHoraMartesItemStateChanged
        if(Integer.parseInt(this.finHoraMartes.getSelectedItem().toString())==19){
            this.finMinMartes.removeAllItems();
            int i = 0;
            while(i<=30){
                if(i<10) this.finMinMartes.addItem("0"+i);
                else this.finMinMartes.addItem(""+i);
                i+=5;
            }
        }
        else{
            this.finMinMartes.removeAllItems();
            int i = 0;
            while(i<=55){
                if(i<10) this.finMinMartes.addItem("0"+i);
                else this.finMinMartes.addItem(""+i);
                i+=5;
            }
        }
        this.habilitarBoton();
    }//GEN-LAST:event_finHoraMartesItemStateChanged

    private void finMinMartesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_finMinMartesItemStateChanged
        this.habilitarBoton();
    }//GEN-LAST:event_finMinMartesItemStateChanged

    private void inicioHoraMiercolesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inicioHoraMiercolesItemStateChanged
        this.finHoraMiercoles.setSelectedIndex(this.inicioHoraMiercoles.getSelectedIndex());
        this.finMinMiercoles.setSelectedIndex(1);
        this.habilitarBoton();
    }//GEN-LAST:event_inicioHoraMiercolesItemStateChanged

    private void inicioMinMiercolesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inicioMinMiercolesItemStateChanged
        if(this.inicioMinMiercoles.getSelectedIndex() >= 11){
            this.finHoraMiercoles.setSelectedIndex(this.inicioHoraMiercoles.getSelectedIndex()+1);
            this.finMinMiercoles.setSelectedIndex(0);        
        }
        else{
            this.finHoraMiercoles.setSelectedIndex(this.inicioHoraMiercoles.getSelectedIndex());
            this.finMinMiercoles.setSelectedIndex(this.inicioMinMiercoles.getSelectedIndex()+1);
        }
        this.habilitarBoton();
    }//GEN-LAST:event_inicioMinMiercolesItemStateChanged

    private void finHoraMiercolesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_finHoraMiercolesItemStateChanged
        if(Integer.parseInt(this.finHoraMiercoles.getSelectedItem().toString())==19){
            this.finMinMiercoles.removeAllItems();
            int i = 0;
            while(i<=30){
                if(i<10) this.finMinMiercoles.addItem("0"+i);
                else this.finMinMiercoles.addItem(""+i);
                i+=5;
            }
        }
        else{
            this.finMinMiercoles.removeAllItems();
            int i = 0;
            while(i<=55){
                if(i<10) this.finMinMiercoles.addItem("0"+i);
                else this.finMinMiercoles.addItem(""+i);
                i+=5;
            }
        }this.habilitarBoton();
    }//GEN-LAST:event_finHoraMiercolesItemStateChanged

    private void finMinMiercolesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_finMinMiercolesItemStateChanged
        this.habilitarBoton();
    }//GEN-LAST:event_finMinMiercolesItemStateChanged

    private void inicioHoraJuevesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inicioHoraJuevesItemStateChanged
        this.finHoraJueves.setSelectedIndex(this.inicioHoraJueves.getSelectedIndex());
        this.finMinJueves.setSelectedIndex(1);
        this.habilitarBoton();
    }//GEN-LAST:event_inicioHoraJuevesItemStateChanged

    private void inicioMinJuevesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inicioMinJuevesItemStateChanged
        if(this.inicioMinJueves.getSelectedIndex() >= 11){
            this.finHoraJueves.setSelectedIndex(this.inicioHoraJueves.getSelectedIndex()+1);
            this.finMinJueves.setSelectedIndex(0);        
        }
        else{
            this.finHoraJueves.setSelectedIndex(this.inicioHoraJueves.getSelectedIndex());
            this.finMinJueves.setSelectedIndex(this.inicioMinJueves.getSelectedIndex()+1);
        }
        this.habilitarBoton();
    }//GEN-LAST:event_inicioMinJuevesItemStateChanged

    private void finHoraJuevesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_finHoraJuevesItemStateChanged
        if(Integer.parseInt(this.finHoraJueves.getSelectedItem().toString())==19){
            this.finMinJueves.removeAllItems();
            int i = 0;
            while(i<=30){
                if(i<10) this.finMinJueves.addItem("0"+i);
                else this.finMinJueves.addItem(""+i);
                i+=5;
            }
        }
        else{
            this.finMinJueves.removeAllItems();
            int i = 0;
            while(i<=55){
                if(i<10) this.finMinJueves.addItem("0"+i);
                else this.finMinJueves.addItem(""+i);
                i+=5;
            }
        }
        this.habilitarBoton();
    }//GEN-LAST:event_finHoraJuevesItemStateChanged

    private void finMinJuevesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_finMinJuevesItemStateChanged
        this.habilitarBoton();
    }//GEN-LAST:event_finMinJuevesItemStateChanged

    private void inicioHoraViernesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inicioHoraViernesItemStateChanged
        this.finHoraViernes.setSelectedIndex(this.inicioHoraViernes.getSelectedIndex());
        this.finMinViernes.setSelectedIndex(1);
        this.habilitarBoton();
    }//GEN-LAST:event_inicioHoraViernesItemStateChanged

    private void inicioMinViernesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inicioMinViernesItemStateChanged
        if(this.inicioMinViernes.getSelectedIndex() >= 11){
            this.finHoraViernes.setSelectedIndex(this.inicioHoraViernes.getSelectedIndex()+1);
            this.finMinViernes.setSelectedIndex(0);        
        }
        else{
            this.finHoraViernes.setSelectedIndex(this.inicioHoraViernes.getSelectedIndex());
            this.finMinViernes.setSelectedIndex(this.inicioMinViernes.getSelectedIndex()+1);
        }
        this.habilitarBoton();
    }//GEN-LAST:event_inicioMinViernesItemStateChanged

    private void finHoraViernesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_finHoraViernesItemStateChanged
        if(Integer.parseInt(this.finHoraViernes.getSelectedItem().toString())==19){
            this.finMinViernes.removeAllItems();
            int i = 0;
            while(i<=30){
                if(i<10) this.finMinViernes.addItem("0"+i);
                else this.finMinViernes.addItem(""+i);
                i+=5;
            }
        }
        else{
            this.finMinViernes.removeAllItems();
            int i = 0;
            while(i<=55){
                if(i<10) this.finMinViernes.addItem("0"+i);
                else this.finMinViernes.addItem(""+i);
                i+=5;
            }
        }
        this.habilitarBoton();
    }//GEN-LAST:event_finHoraViernesItemStateChanged

    private void finMinViernesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_finMinViernesItemStateChanged
        this.habilitarBoton();
    }//GEN-LAST:event_finMinViernesItemStateChanged

    private void inicioMinSabadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inicioMinSabadoItemStateChanged
        if(this.inicioMinSabado.getSelectedIndex() >= 11){
            this.finHoraSabado.setSelectedIndex(this.inicioHoraSabado.getSelectedIndex()+1);
            this.finMinSabado.setSelectedIndex(0);        
        }
        else{
            this.finHoraSabado.setSelectedIndex(this.inicioHoraSabado.getSelectedIndex());
            this.finMinSabado.setSelectedIndex(this.inicioMinSabado.getSelectedIndex()+1);
        }
        this.habilitarBoton();
    }//GEN-LAST:event_inicioMinSabadoItemStateChanged

    private void finHoraSabadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_finHoraSabadoItemStateChanged
        this.habilitarBoton();
    }//GEN-LAST:event_finHoraSabadoItemStateChanged

    private void finMinSabadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_finMinSabadoItemStateChanged
        this.habilitarBoton();
    }//GEN-LAST:event_finMinSabadoItemStateChanged

    private void inicioHoraLunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioHoraLunesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inicioHoraLunesActionPerformed
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox finHoraJueves;
    private javax.swing.JComboBox finHoraLunes;
    private javax.swing.JComboBox finHoraMartes;
    private javax.swing.JComboBox finHoraMiercoles;
    private javax.swing.JComboBox finHoraSabado;
    private javax.swing.JComboBox finHoraViernes;
    private javax.swing.JComboBox finMinJueves;
    private javax.swing.JComboBox finMinLunes;
    private javax.swing.JComboBox finMinMartes;
    private javax.swing.JComboBox finMinMiercoles;
    private javax.swing.JComboBox finMinSabado;
    private javax.swing.JComboBox finMinViernes;
    private javax.swing.JButton guardar;
    private javax.swing.JComboBox inicioHoraJueves;
    private javax.swing.JComboBox inicioHoraLunes;
    private javax.swing.JComboBox inicioHoraMartes;
    private javax.swing.JComboBox inicioHoraMiercoles;
    private javax.swing.JComboBox inicioHoraSabado;
    private javax.swing.JComboBox inicioHoraViernes;
    private javax.swing.JComboBox inicioMinJueves;
    private javax.swing.JComboBox inicioMinLunes;
    private javax.swing.JComboBox inicioMinMartes;
    private javax.swing.JComboBox inicioMinMiercoles;
    private javax.swing.JComboBox inicioMinSabado;
    private javax.swing.JComboBox inicioMinViernes;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JCheckBox jueves;
    private javax.swing.JLabel labelFin;
    private javax.swing.JLabel labelInicio;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JCheckBox lunes;
    private javax.swing.JCheckBox martes;
    private javax.swing.JCheckBox miercoles;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JCheckBox sabado;
    private javax.swing.JCheckBox viernes;
    // End of variables declaration//GEN-END:variables
}
