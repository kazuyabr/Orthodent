/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.pacientes;

import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.Comuna;
import modelo.Paciente;
import modelo.Region;
import orthodent.db.ComunaDB;
import orthodent.db.PacienteDB;
import orthodent.db.RegionDB;
import orthodent.utils.Validaciones;

/**
 *
 * @author Mary
 */
public class DatosPersonales extends JPanel{

    private Paciente paciente;
    private boolean cambios;
    ArrayList<Region> regiones;
    
    public DatosPersonales(Paciente paciente) {
        initComponents();
        
        this.paciente = paciente;
        this.cambios = false;
        
        this.addInfo();
        this.guardar.setEnabled(false);
        this.guardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.eliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    public boolean getCambios(){
        return this.cambios;
    }
    
    public void setCambios(boolean cambios){
        this.cambios = cambios;
    }
    
    private void addInfo(){
        this.nombres.setText(this.paciente.getNombre());
        this.apellidoPat.setText(this.paciente.getApellido_pat());
        this.apellidoMat.setText(this.paciente.getApellido_mat());
        this.rut.setText(this.paciente.getRut());
        this.email.setText(this.paciente.getEmail());
        this.telefono.setText(this.paciente.getTelefono());
        this.antecedentesMedicos.setText(this.paciente.getAntecedenteMedico());
        
        if(this.paciente.getSexo()==1){
            //Femenino
            this.sexo.setSelectedItem("Femenino");
        }
        else{
            this.sexo.setSelectedItem("Masculino");
        }
        
        this.fechaNacimiento.setDate(this.getFecha(this.paciente.getFechaNacimiento()));
        
        this.edad.setText(this.paciente.getEdad()+"");
        
        regiones = RegionDB.listarRegiones();
        for(int i=0; i<regiones.size(); i++){
            this.ciudad.addItem(regiones.get(i).getNombre());
        }        
        ciudad.setSelectedItem(this.paciente.getRegion());
        
        int id = buscarIdRegion(this.paciente.getRegion());
        if(id != 0){
            ArrayList<Comuna> comunas = ComunaDB.listarComunasPorRegion(id);
            this.comuna.removeAllItems();
            for(int i=0; i<comunas.size(); i++){
                this.comuna.addItem(comunas.get(i).getNombre());
            }
        }  
        comuna.setSelectedItem(this.paciente.getComuna());
        this.direccion.setText(this.paciente.getDireccion());
        this.cambios = false;
    }
    
    public int buscarIdRegion(String regionSeleccionada){
        for(int i=0; i<regiones.size(); i++){
            if(regiones.get(i).getNombre().equals(regionSeleccionada)){
                return regiones.get(i).getCodigo();
            }
        }
        return 0;
    }    
    
    //Recibe la fecha en fomato DD-MM-AAAA
    private int calculaEdad(String fecha){//M2
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        
        int edad = 0;
        
        int a1 = date.getYear()+1900;
        int m1 = date.getMonth()+1;
        int d1 = date.getDate();
        
        int d2 = Integer.parseInt(fecha.substring(0, fecha.indexOf("-")));
        fecha = fecha.substring(fecha.indexOf("-")+1, fecha.length());
        int m2 = Integer.parseInt(fecha.substring(0, fecha.indexOf("-")));
        fecha = fecha.substring(fecha.indexOf("-")+1, fecha.length());
        int a2 = Integer.parseInt(fecha);
        
        if(m1>m2){
            edad = a1-a2;
        }
        else if(m1<m2){
            edad = a1-a2-1;
        }
        else{
            if(d1>=d2){
                edad = a1-a2;
            }
            else{
                edad = a1-a2-1;
            }
        }
        
        return edad;
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
    
    private Date getFecha(String fechaNacimiento){
        
        if(fechaNacimiento!=null){
            int año = Integer.parseInt(fechaNacimiento.substring(0, fechaNacimiento.indexOf("-")))-1900;
            fechaNacimiento = fechaNacimiento.substring(fechaNacimiento.indexOf("-")+1, fechaNacimiento.length());

            int mes = Integer.parseInt(fechaNacimiento.substring(0, fechaNacimiento.indexOf("-")))-1;
            fechaNacimiento = fechaNacimiento.substring(fechaNacimiento.indexOf("-")+1, fechaNacimiento.length());

            int dia = Integer.parseInt(fechaNacimiento);
            
            return new Date(año, mes, dia);
        }
        else{
            return null;
        }
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
        labelNombres = new javax.swing.JLabel();
        nombres = new javax.swing.JTextField();
        guardar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        labelApellidoPat = new javax.swing.JLabel();
        apellidoPat = new javax.swing.JTextField();
        apellidoMat = new javax.swing.JTextField();
        labelApellidoMat = new javax.swing.JLabel();
        rut = new javax.swing.JTextField();
        labelRut = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        labelAntecedentesMedicos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        antecedentesMedicos = new javax.swing.JTextArea();
        sexo = new javax.swing.JComboBox();
        labelSexo = new javax.swing.JLabel();
        labelFechaNacimiento = new javax.swing.JLabel();
        labelCiudad = new javax.swing.JLabel();
        labelComuna = new javax.swing.JLabel();
        labelDireccion = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        labelTelefono = new javax.swing.JLabel();
        ciudad = new javax.swing.JComboBox();
        comuna = new javax.swing.JComboBox();
        direccion = new javax.swing.JTextField();
        fechaNacimiento = new com.toedter.calendar.JDateChooser();
        edad = new javax.swing.JTextField();
        labelEdad = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(850, 551));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setPreferredSize(new java.awt.Dimension(845, 545));

        panelTitulo.setBackground(new java.awt.Color(247, 243, 248));
        panelTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(232, 230, 234), 1, true));

        labelTitulo.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(163, 159, 164));
        labelTitulo.setText("Datos Personales");

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

        labelNombres.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelNombres.setText("Nombres");

        nombres.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        nombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombresKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombresKeyTyped(evt);
            }
        });

        guardar.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        guardar.setText("Guardar Cambios");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        eliminar.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        labelApellidoPat.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelApellidoPat.setText("Apellido P.");

        apellidoPat.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        apellidoPat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoPatKeyTyped(evt);
            }
        });

        apellidoMat.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        apellidoMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoMatKeyTyped(evt);
            }
        });

        labelApellidoMat.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelApellidoMat.setText("Apellido M.");

        rut.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        rut.setEnabled(false);
        rut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rutKeyTyped(evt);
            }
        });

        labelRut.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelRut.setText("Rut");

        email.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emailKeyTyped(evt);
            }
        });

        labelEmail.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelEmail.setText("Email");

        labelAntecedentesMedicos.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelAntecedentesMedicos.setText("Antecedentes Medicos");

        antecedentesMedicos.setColumns(20);
        antecedentesMedicos.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        antecedentesMedicos.setLineWrap(true);
        antecedentesMedicos.setRows(4);
        antecedentesMedicos.setMaximumSize(new java.awt.Dimension(244, 5000));
        antecedentesMedicos.setName(""); // NOI18N
        antecedentesMedicos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                antecedentesMedicosKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(antecedentesMedicos);

        sexo.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Femenino", "Masculino" }));
        sexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexoActionPerformed(evt);
            }
        });

        labelSexo.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelSexo.setText("Sexo");

        labelFechaNacimiento.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelFechaNacimiento.setText("Fecha de Nacimiento");

        labelCiudad.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelCiudad.setText("Región");

        labelComuna.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelComuna.setText("Comuna");

        labelDireccion.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelDireccion.setText("Dirección");

        telefono.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefonoKeyTyped(evt);
            }
        });

        labelTelefono.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelTelefono.setText("Telefono");

        ciudad.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        ciudad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ciudadItemStateChanged(evt);
            }
        });
        ciudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ciudadActionPerformed(evt);
            }
        });

        comuna.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        comuna.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comunaItemStateChanged(evt);
            }
        });
        comuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comunaActionPerformed(evt);
            }
        });

        direccion.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                direccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                direccionKeyTyped(evt);
            }
        });

        fechaNacimiento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fechaNacimientoFocusLost(evt);
            }
        });
        fechaNacimiento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fechaNacimientoPropertyChange(evt);
            }
        });
        fechaNacimiento.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                fechaNacimientoAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        edad.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        edad.setEnabled(false);
        edad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edadActionPerformed(evt);
            }
        });

        labelEdad.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        labelEdad.setText("Edad");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guardar)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNombres)
                            .addComponent(labelAntecedentesMedicos)
                            .addComponent(labelSexo)
                            .addComponent(labelFechaNacimiento)
                            .addComponent(labelApellidoPat)
                            .addComponent(labelApellidoMat)
                            .addComponent(labelRut)
                            .addComponent(labelEmail)
                            .addComponent(labelTelefono)
                            .addComponent(labelEdad)
                            .addComponent(labelCiudad)
                            .addComponent(labelComuna)
                            .addComponent(labelDireccion))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nombres)
                                .addComponent(apellidoPat)
                                .addComponent(apellidoMat, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(rut)
                                .addComponent(email)
                                .addComponent(telefono)
                                .addComponent(fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1))
                            .addComponent(edad, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comuna, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(319, 319, 319))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombres)
                    .addComponent(nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidoPat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelApellidoPat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidoMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelApellidoMat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTelefono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAntecedentesMedicos)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSexo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFechaNacimiento))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEdad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCiudad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelComuna))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDireccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardar)
                    .addComponent(eliminar))
                .addContainerGap(65, Short.MAX_VALUE))
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

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        Object[] options = {"Sí","No"};
        
        int n = JOptionPane.showOptionDialog(this,
                    "¿Esta seguro que desea eliminar el paciente?",
                    "Orthodent",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
        
        if(n==0){
            try {
                boolean resul = PacienteDB.eliminarPaciente(this.paciente.getId_paciente());
                if(resul){
                    try {

                        JPanel contenedor = (JPanel)this.getParent();

                        ((MostrarInfoPaciente)contenedor.getParent()).volver();
                    } catch (Exception ex) {
                    }
                }
            } catch (SQLException ex) {
            }
        }
    }//GEN-LAST:event_eliminarActionPerformed

    public void guardar(){
        this.guardarActionPerformed(null);
    }
    
    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        
        String nombre = this.nombres.getText();
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
        String region = (String)this.ciudad.getSelectedItem();
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
                this.paciente.setRegion(region);
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
            }
        }
        else{
            JOptionPane.showMessageDialog(this,
                    "Faltan campos obligatorios",
                    "Orthodent",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_guardarActionPerformed
    
    private boolean validarCamposObligatorios(String nombre, String apellidoPat, String fechaNacimiento, String telefono){
        
        if(nombre.equals("")){
            return false;
        }
        
        if(apellidoPat.equals("")){
            return false;
        }
        
        if(fechaNacimiento.equals("")){
            return false;
        }
        
        if(telefono.equals("")){
            return false;
        }
        
        return true;
    }
    
    private void habilitarBoton(){
        this.cambios = true;
        this.guardar.setEnabled(true);
    }
    
    private void nombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombresKeyReleased
        
    }//GEN-LAST:event_nombresKeyReleased

    private void nombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombresKeyTyped
        char c = evt.getKeyChar();
        
        if(c==KeyEvent.VK_ENTER){
            evt.consume();
        }
        else if(c==KeyEvent.VK_SPACE){
            String antes = this.nombres.getText();
            
            if(antes.equals("")){
                evt.consume();
            }
            else{
                this.habilitarBoton();
            }
        }
        else if(!((c>='a' && c<='z') || (c>='A' && c<='Z'))){
            evt.consume();
        }
        else{
            this.habilitarBoton();
        }
    }//GEN-LAST:event_nombresKeyTyped

    private void apellidoPatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoPatKeyTyped
        char c = evt.getKeyChar();
        
        if(c==KeyEvent.VK_ENTER){
            evt.consume();
        }
        else if(c==KeyEvent.VK_SPACE){
            String antes = this.apellidoPat.getText();
            
            if(antes.equals("")){
                evt.consume();
            }
            else{
                this.habilitarBoton();
            }
        }
        else if(!((c>='a' && c<='z') || (c>='A' && c<='Z'))){
            evt.consume();
        }
        else{
            this.habilitarBoton();
        }
    }//GEN-LAST:event_apellidoPatKeyTyped

    private void apellidoMatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoMatKeyTyped
        char c = evt.getKeyChar();
        
        if(c==KeyEvent.VK_ENTER){
            evt.consume();
        }
        else if(c==KeyEvent.VK_SPACE){
            String antes = this.apellidoMat.getText();
            
            if(antes.equals("")){
                evt.consume();
            }
            else{
                this.habilitarBoton();
            }
        }
        else if(!((c>='a' && c<='z') || (c>='A' && c<='Z'))){
            evt.consume();
        }
        else{
            this.habilitarBoton();
        }
    }//GEN-LAST:event_apellidoMatKeyTyped

    private void rutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rutKeyTyped
        Validaciones.validarRut(this.rut.getText(), evt);
    }//GEN-LAST:event_rutKeyTyped

    private void emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailKeyTyped
        char c = evt.getKeyChar();
        
        if(c==KeyEvent.VK_ENTER){
            evt.consume();
        }
        else if(c==KeyEvent.VK_SPACE){
            evt.consume();
        }
        else if(!((c>='a' && c<='z') || (c>='A' && c<='Z') || (c>='0' && c<='9') || c=='_' || c=='@' || c=='-' || c=='.')){
            evt.consume();
        }
        else{
            this.habilitarBoton();
        }
    }//GEN-LAST:event_emailKeyTyped

    private void telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyTyped
        char c = evt.getKeyChar();
        if (!(c>='0' && c<='9')){
            evt.consume();
        }
        else{
            this.habilitarBoton();
        }
    }//GEN-LAST:event_telefonoKeyTyped

    private void antecedentesMedicosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_antecedentesMedicosKeyTyped
        this.habilitarBoton();
    }//GEN-LAST:event_antecedentesMedicosKeyTyped

    private void direccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direccionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_direccionKeyReleased

    private void direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direccionKeyTyped
        this.habilitarBoton();
    }//GEN-LAST:event_direccionKeyTyped

    private void sexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexoActionPerformed
        
    }//GEN-LAST:event_sexoActionPerformed

    private void fechaNacimientoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_fechaNacimientoAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaNacimientoAncestorAdded

    private void fechaNacimientoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fechaNacimientoFocusLost
        
    }//GEN-LAST:event_fechaNacimientoFocusLost

    private void fechaNacimientoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fechaNacimientoPropertyChange
        
        Date date = this.fechaNacimiento.getDate();
        if(date!=null){
            if(!this.paciente.getFechaNacimiento().equals(this.getFechaString(date))){
                String fechaNacimiento = getFechaString(date);
                int edad = this.calculaEdad(this.girarFecha(fechaNacimiento));
                this.edad.setText(edad+"");
                this.habilitarBoton();
            }
        }
    }//GEN-LAST:event_fechaNacimientoPropertyChange

    private void ciudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ciudadActionPerformed
        int id = buscarIdRegion(ciudad.getSelectedItem().toString());
        if(id != 0){
            ArrayList<Comuna> comunas = ComunaDB.listarComunasPorRegion(id);
            this.comuna.removeAllItems();
            for(int i=0; i<comunas.size(); i++){
                this.comuna.addItem(comunas.get(i).getNombre());
            }
        } 
        System.out.println("***");
        System.out.println(ciudad.getSelectedItem().toString()+"-"+this.paciente.getRegion().toString());
        System.out.println("***");
//        if(!ciudad.getSelectedItem().toString().equals(this.paciente.getRegion().toString())){
//             this.cambios = true;
//             this.guardar.setEnabled(true);
//        }
//        else{
//             this.cambios = false;
//             this.guardar.setEnabled(false);
//        }
    }//GEN-LAST:event_ciudadActionPerformed

    private void comunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comunaActionPerformed
//        System.out.println("***");
//        System.out.println(comuna.getSelectedItem().toString()+"-"+this.paciente.getComuna().toString());
//        System.out.println("***");       
//        if(!comuna.getSelectedItem().toString().equals(this.paciente.getComuna())){
//             this.cambios = true;
//             this.guardar.setEnabled(true);
//        }
//        else{
//             this.cambios = false;
//             this.guardar.setEnabled(false);
//        }
    }//GEN-LAST:event_comunaActionPerformed

    private void edadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edadActionPerformed

    private void ciudadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ciudadItemStateChanged
        this.cambios = true;
        this.guardar.setEnabled(true);
    }//GEN-LAST:event_ciudadItemStateChanged

    private void comunaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comunaItemStateChanged
        this.cambios = true;
        this.guardar.setEnabled(true);
    }//GEN-LAST:event_comunaItemStateChanged

    private String getFechaString(Date date){
        
        String fechaNacimiento = (date.getYear()+1900) + "-";
        
        if((date.getMonth()+1)<9){
            fechaNacimiento = fechaNacimiento + "0";
        }
        
        fechaNacimiento = fechaNacimiento +(date.getMonth()+1)+"-";
        
        if(date.getDate()<9){
            fechaNacimiento = fechaNacimiento + "0";
        }
        
        fechaNacimiento = fechaNacimiento + date.getDate();
        
        return fechaNacimiento;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea antecedentesMedicos;
    private javax.swing.JTextField apellidoMat;
    private javax.swing.JTextField apellidoPat;
    private javax.swing.JComboBox ciudad;
    private javax.swing.JComboBox comuna;
    private javax.swing.JTextField direccion;
    private javax.swing.JTextField edad;
    private javax.swing.JButton eliminar;
    private javax.swing.JTextField email;
    private com.toedter.calendar.JDateChooser fechaNacimiento;
    private javax.swing.JButton guardar;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAntecedentesMedicos;
    private javax.swing.JLabel labelApellidoMat;
    private javax.swing.JLabel labelApellidoPat;
    private javax.swing.JLabel labelCiudad;
    private javax.swing.JLabel labelComuna;
    private javax.swing.JLabel labelDireccion;
    private javax.swing.JLabel labelEdad;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelFechaNacimiento;
    private javax.swing.JLabel labelNombres;
    private javax.swing.JLabel labelRut;
    private javax.swing.JLabel labelSexo;
    private javax.swing.JLabel labelTelefono;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTextField nombres;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JTextField rut;
    private javax.swing.JComboBox sexo;
    private javax.swing.JTextField telefono;
    // End of variables declaration//GEN-END:variables
}
