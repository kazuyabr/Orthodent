/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.pacientes;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Comuna;
import modelo.Region;
import orthodent.JVentana;
import orthodent.agenda.Agenda;
import orthodent.agenda.NuevaCita;
import orthodent.db.ComunaDB;
import orthodent.db.PacienteDB;
import orthodent.db.RegionDB;

/**
 *
 * @author Mary
 */
public class NuevoPaciente extends javax.swing.JDialog {
    ArrayList<Region> regiones;
    /**
     * Creates new form NuevoUsuario
     */
    private NuevaCita nuevaCita;
    public NuevoPaciente(java.awt.Frame parent, boolean modal, NuevaCita nuevaCita) {
        super(parent, modal);
        initComponents();
        this.nuevaCita = nuevaCita;
        this.aceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.cancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getSize().width) / 2 ,
                (screenSize.height - this.getSize().height) / 2);
        
        
        this.setTitle("Nuevo Paciente");
        regiones = RegionDB.listarRegiones();
        for(int i=0; i<regiones.size(); i++){
            this.ciudad.addItem(regiones.get(i).getNombre());
        }
        
        comuna.setSelectedItem("TALCA");
        
    }
    
    public int buscarIdRegion(String regionSeleccionada){
        for(int i=0; i<regiones.size(); i++){
            if(regiones.get(i).getNombre().equals(regionSeleccionada)){
                return regiones.get(i).getCodigo();
            }
        }
        return 0;
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
        jPanel3 = new javax.swing.JPanel();
        apellidoMat = new javax.swing.JTextField();
        labelApellidoPat = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        labelApellidoMat = new javax.swing.JLabel();
        apellidoPat = new javax.swing.JTextField();
        nombres = new javax.swing.JTextField();
        labelNombre = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        labelTelefono = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        camposObligatorios = new javax.swing.JLabel();
        labelRut = new javax.swing.JLabel();
        rut = new javax.swing.JTextField();
        sexo = new javax.swing.JComboBox();
        labelSexo = new javax.swing.JLabel();
        fechaNacimiento = new com.toedter.calendar.JDateChooser();
        labelFechaNacimiento = new javax.swing.JLabel();
        labelCiudad = new javax.swing.JLabel();
        ciudad = new javax.swing.JComboBox();
        comuna = new javax.swing.JComboBox();
        labelComuna = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        labelDireccion = new javax.swing.JLabel();
        cancelar = new javax.swing.JButton();
        aceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Usuario");

        jPanel2.setBackground(new java.awt.Color(9, 133, 179));

        jPanel3.setBackground(new java.awt.Color(9, 133, 179));

        apellidoMat.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        apellidoMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoMatKeyTyped(evt);
            }
        });

        labelApellidoPat.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        labelApellidoPat.setForeground(new java.awt.Color(255, 255, 255));
        labelApellidoPat.setText("Apellido Paterno (*)");

        email.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emailKeyTyped(evt);
            }
        });

        labelApellidoMat.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        labelApellidoMat.setForeground(new java.awt.Color(255, 255, 255));
        labelApellidoMat.setText("Apellido Materno");

        apellidoPat.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        apellidoPat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoPatKeyTyped(evt);
            }
        });

        nombres.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        nombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombresKeyTyped(evt);
            }
        });

        labelNombre.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        labelNombre.setForeground(new java.awt.Color(255, 255, 255));
        labelNombre.setText("Nombre (*)");

        telefono.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefonoKeyTyped(evt);
            }
        });

        labelTelefono.setBackground(new java.awt.Color(9, 133, 179));
        labelTelefono.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        labelTelefono.setForeground(new java.awt.Color(255, 255, 255));
        labelTelefono.setText("Telefono (*)");

        labelEmail.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        labelEmail.setForeground(new java.awt.Color(255, 255, 255));
        labelEmail.setText("Email");

        titulo.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setText("Nuevo Paciente");

        camposObligatorios.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        camposObligatorios.setForeground(new java.awt.Color(255, 255, 255));
        camposObligatorios.setText("(*) Campos Obligatorios");

        labelRut.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        labelRut.setForeground(new java.awt.Color(255, 255, 255));
        labelRut.setText("Rut (*)");

        rut.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        rut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rutKeyTyped(evt);
            }
        });

        sexo.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Femenino", "Masculino" }));

        labelSexo.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        labelSexo.setForeground(new java.awt.Color(255, 255, 255));
        labelSexo.setText("Sexo (*)");

        labelFechaNacimiento.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        labelFechaNacimiento.setForeground(new java.awt.Color(255, 255, 255));
        labelFechaNacimiento.setText("Fecha de Nacimiento (*)");

        labelCiudad.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        labelCiudad.setForeground(new java.awt.Color(255, 255, 255));
        labelCiudad.setText("Región");

        ciudad.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        ciudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ciudadActionPerformed(evt);
            }
        });

        comuna.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        comuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comunaActionPerformed(evt);
            }
        });

        labelComuna.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        labelComuna.setForeground(new java.awt.Color(255, 255, 255));
        labelComuna.setText("Comuna");

        direccion.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                direccionKeyTyped(evt);
            }
        });

        labelDireccion.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        labelDireccion.setForeground(new java.awt.Color(255, 255, 255));
        labelDireccion.setText("Dirección");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelEmail)
                                    .addComponent(labelRut)
                                    .addComponent(labelSexo)
                                    .addComponent(labelFechaNacimiento))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(rut)
                                            .addComponent(nombres, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                            .addComponent(apellidoPat, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(apellidoMat, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(labelDireccion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(camposObligatorios))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(labelTelefono)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelApellidoMat)
                                            .addComponent(labelApellidoPat)
                                            .addComponent(labelNombre)
                                            .addComponent(labelCiudad))
                                        .addGap(42, 42, 42))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(labelComuna)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comuna, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(titulo)
                        .addGap(113, 113, 113))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidoPat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelApellidoPat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidoMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelApellidoMat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSexo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFechaNacimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTelefono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCiudad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelComuna))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDireccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(camposObligatorios)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cancelar.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        aceptar.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        aceptar.setText("Aceptar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptar)
                    .addComponent(cancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    private String getFechaString(Date date){
        if(date!=null){
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
        return null;
    }
    
    //Recibe la fecha en fomato DD-MM-AAAA
    private int calculaEdad(String fecha){
        
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
    
    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        
        String nombre = this.nombres.getText();
        String apellidoPat = this.apellidoPat.getText();
        String apellidoMat = this.apellidoMat.getText();
        String rut = this.rut.getText();
        
        int sexo = 0;
        if(((String)this.sexo.getSelectedItem()).equals("Femenino")){
            sexo = 1;
        }
        else{
            sexo = 2;
        }
        
        Date date = this.fechaNacimiento.getDate();
        
        String fechaNacimiento = getFechaString(date);
        
        String email = this.email.getText();
        
        String telefono = this.telefono.getText();
        String ciudad = (String)this.ciudad.getSelectedItem();
        String comuna = (String)this.comuna.getSelectedItem();
        String direccion = this.direccion.getText();
        
        boolean aux = validarCamposObligatorios(nombre,apellidoPat,rut,fechaNacimiento, telefono);
        
        if(aux){
            
            if(this.rutCorrecto(rut)){
                
                try {
                    boolean valida = PacienteDB.validaRut(rut);
                    if(valida){
                        try {
                            int edad = this.calculaEdad(this.girarFecha(fechaNacimiento));
                            boolean respuesta = PacienteDB.crearPaciente(nombre,apellidoPat,apellidoMat,rut,email,fechaNacimiento,edad,
                                                                        sexo,"",telefono,ciudad,comuna,direccion);

                            /*if(respuesta){
                                System.out.println("Agregado :)");
                            }
                            else{
                                System.out.println("Algo ocurrio mal =/");
                            }*/
                        } catch (Exception ex) {
                        }
                        
                        Pacientes pacientesPanel = ((JVentana)this.getParent()).getPacientes();
                        if(pacientesPanel!=null){
                            pacientesPanel.updateModelo();
                            pacientesPanel.updateUI();
                        }
                        else if(this.nuevaCita!=null){
                            this.nuevaCita.initPacientes();
                        }
                        
                        
                        this.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(this,
                            "El rut ya existe en nuestros registros",
                            "Orthodent",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(this,
                    "Faltan campos obligatorios",
                    "Orthodent",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_aceptarActionPerformed

    private boolean rutCorrecto(String rut){
        
        if(!rut.contains("-")){
            JOptionPane.showMessageDialog(this,
                    "Falta el dígito verficador",
                    "Orthodent",
                    JOptionPane.ERROR_MESSAGE);
            
            return false;
        }
        else{
            String digitoVerficador = calcularDigitoVerificador(rut.substring(0, rut.indexOf("-")));
            String dig = rut.substring(rut.indexOf("-")+1, rut.length()).toLowerCase();
            
            if(dig.equals(digitoVerficador)){
                return true;
            }
            else{
                JOptionPane.showMessageDialog(this,
                    "El rut es incorrecto",
                    "Orthodent",
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }
    
    private String calcularDigitoVerificador(String rut){
        int i = rut.length()-1;
        int j = 7;
        int suma = 0;
        int array [] = {8,9,4,5,6,7,8,9};
        
        while(i>=0){
            suma = suma + Integer.parseInt(rut.charAt(i)+"")*array[j];
            i--;
            j--;
        }
        
        if(suma%11==10){
            return "k";
        }
        else{
            return (suma%11)+"";
        }
    }
    
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
        }
        else if(!((c>='a' && c<='z') || (c>='A' && c<='Z'))){
            evt.consume();
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
        }
        else if(!((c>='a' && c<='z') || (c>='A' && c<='Z'))){
            evt.consume();
        }
    }//GEN-LAST:event_apellidoPatKeyTyped

    private void apellidoMatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoMatKeyTyped
        char c = evt.getKeyChar();
        
        if(c==KeyEvent.VK_ENTER){
            evt.consume();
        }
        else if(c==KeyEvent.VK_SPACE){
            String antes = this.nombres.getText();
            
            if(antes.equals("")){
                evt.consume();
            }
        }
        else if(!((c>='a' && c<='z') || (c>='A' && c<='Z'))){
            evt.consume();
        }
    }//GEN-LAST:event_apellidoMatKeyTyped

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
    }//GEN-LAST:event_emailKeyTyped

    private void telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyTyped
        char c = evt.getKeyChar();
        if (!(c>='0' && c<='9')){
            evt.consume();
        }
    }//GEN-LAST:event_telefonoKeyTyped

    private void rutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rutKeyTyped
        char c = evt.getKeyChar();
        
        if(c==KeyEvent.VK_ENTER){
            evt.consume();
        }
        else if(!((c>='0' && c<='9') || (c=='-') || (c=='k') || (c=='K'))){
            evt.consume();
        }
        else{
            if(c=='k' || c=='K'){
                String ultimo = this.rut.getText().charAt(this.rut.getText().length()-1)+"";
                if(!ultimo.equals("-")){
                    evt.consume();
                }
            }
            if(c=='-'){
                int largo = this.rut.getText().length();
                String ultimo = this.rut.getText().charAt(this.rut.getText().length()-1)+"";
                if(!(largo==7 || largo==8) || ultimo.equals("-")){
                    evt.consume();
                }
            }
            if(c>='0' && c<='9'){
                int largo = this.rut.getText().length();
                
                if(!this.rut.getText().contains("-")){
                    if(!(largo<8)){
                        evt.consume();
                    }
                }
                else{
                    char ultimo = this.rut.getText().charAt(this.rut.getText().length()-1);
                    if((ultimo>='0' && ultimo<='9') || ultimo=='k' || ultimo=='K'){
                        evt.consume();
                    }
                }
            }
        }
    }//GEN-LAST:event_rutKeyTyped

    private void direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direccionKeyTyped
        char c = evt.getKeyChar();
        
        if(c==KeyEvent.VK_ENTER){
            evt.consume();
        }
        else if(c==KeyEvent.VK_SPACE){
            String antes = this.direccion.getText();
            
            if(antes.equals("")){
                evt.consume();
            }
        }
    }//GEN-LAST:event_direccionKeyTyped

    private void ciudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ciudadActionPerformed
        int id = buscarIdRegion(ciudad.getSelectedItem().toString());
        if(id != 0){
            ArrayList<Comuna> comunas = ComunaDB.listarComunasPorRegion(id);
            this.comuna.removeAllItems();
            for(int i=0; i<comunas.size(); i++){
                this.comuna.addItem(comunas.get(i).getNombre());
            }
        }        
        
    }//GEN-LAST:event_ciudadActionPerformed

    private void comunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comunaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comunaActionPerformed
    
    private boolean validarCamposObligatorios(String nombre, String apellidoPat, String rut, String fechaNacimiento, String telefono){
        
        if(nombre.equals("")){
            return false;
        }
        
        if(apellidoPat.equals("")){
            return false;
        }
        
        if(rut.equals("")){
            return false;
        }
        
        if(fechaNacimiento==null){
            return false;
        }
        
        if(telefono.equals("")){
            return false;
        }
        
        return true;
    }
    
    
    public void setNombresTextField(String nombres){
        this.nombres.setText(nombres);
    }
    
    public void setApelidoPaternoTextField(String apellido){
        this.apellidoPat.setText(apellido);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JTextField apellidoMat;
    private javax.swing.JTextField apellidoPat;
    private javax.swing.JLabel camposObligatorios;
    private javax.swing.JButton cancelar;
    private javax.swing.JComboBox ciudad;
    private javax.swing.JComboBox comuna;
    private javax.swing.JTextField direccion;
    private javax.swing.JTextField email;
    private com.toedter.calendar.JDateChooser fechaNacimiento;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelApellidoMat;
    private javax.swing.JLabel labelApellidoPat;
    private javax.swing.JLabel labelCiudad;
    private javax.swing.JLabel labelComuna;
    private javax.swing.JLabel labelDireccion;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelFechaNacimiento;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelRut;
    private javax.swing.JLabel labelSexo;
    private javax.swing.JLabel labelTelefono;
    private javax.swing.JTextField nombres;
    private javax.swing.JTextField rut;
    private javax.swing.JComboBox sexo;
    private javax.swing.JTextField telefono;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
