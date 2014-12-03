/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import modelo.Paciente;
import modelo.Usuario;
import orthodent.usuarios.ClinicaInterna;

/**
 *
 * @author msanhuezal
 */
public class Autenticacion {
    
    static public Usuario logIn(String nombreUsuario, String contrasena) throws Exception{
        Usuario usuario = null;
        try {
                DbConnection db = new DbConnection();
                Connection con = db.getConnection();

                java.sql.Statement st = con.createStatement();
                contrasena = encripMd5(contrasena);
                
                ResultSet rs = st.executeQuery("SELECT * from usuario where nombre_usuario='" + nombreUsuario + "' AND contrasena='" + contrasena + "'");
                if (rs.next())
                {
                    int id_usuario = rs.getInt("id_usuario");
                    int id_rol = rs.getInt("id_rol");
                    String nombre = rs.getString("nombre");
                    String apellido_pat = rs.getString("apellido_pat");
                    String apellido_mat = rs.getString("apellido_mat");
                    String userName = rs.getString("nombre_usuario");
                    String contraseña = rs.getString("contrasena");
                    String email  = rs.getString("email");
                    String telefono  = rs.getString("telefono");
                    String especialidad  = rs.getString("especialidad");
                    int tiempoCita = rs.getInt("tiempo_cita");
                    boolean activo = rs.getBoolean("activo");  
                    
                    usuario = new Usuario(id_usuario, id_rol, nombre, apellido_pat, apellido_mat, userName, contraseña, email, telefono, especialidad, tiempoCita, activo);
                }
                else{
                    usuario = null;
                }
                rs.close();             
                con.close();
                return usuario;
            }

            catch ( SQLException e) { 
                return null;  
            }       
    }
    
    static public String generarContrasenaAleatoria(){
        int longitud = 6;
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while ( i < longitud){
            char c = (char)r.nextInt(255);
            if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
                cadenaAleatoria += c;
                i ++;
            }
        }
        return cadenaAleatoria;
    }
    
    static public void enviarCorreo(String destino, String asunto, String mensaje){
        JavaMail mail = new JavaMail();
        mail.send(destino, asunto, mensaje);        
    }
    
    static public String encripMd5(String clear) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(clear.getBytes());
        int size = b.length;
        StringBuffer h = new StringBuffer(size);
        //algoritmo y arreglo md5
            for (int i = 0; i < size; i++) {
                int u = b[i] & 255;
                    if (u < 16) {
                        h.append("0" + Integer.toHexString(u));
                    }
                   else {
                        h.append(Integer.toHexString(u));
                   }
               }
          //clave encriptada
          return h.toString();
    }    
    
    static public boolean recuperarContrasena(String email) throws Exception{
        boolean resultado = false;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT id_usuario, email from usuario where email='" + email +"'");
            if (rs.next())
            {
                int id_usuario = rs.getInt("id_usuario");
                String destino = rs.getString("email");
                String nuevaContrasena = generarContrasenaAleatoria();
                String auxNuevaContrasena = encripMd5(nuevaContrasena);
                st.executeUpdate("UPDATE usuario\n" +
                        "SET contrasena='"+auxNuevaContrasena+"'\n" +
                        "WHERE id_usuario="+id_usuario);
                enviarCorreo(destino, "CAMBIO CONTRASEÑA", "EL SISTEMA ORTHODENT HA CAMBIADO SU CONTRASEÑA A: " + nuevaContrasena);
                resultado = true;
            }
            rs.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }   
    }
    
    public static ArrayList<Usuario> listarUsuarios(){
        ArrayList<Usuario> usuarios = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM usuario");
            usuarios = new ArrayList<Usuario>();
            while (rs.next())
            {
                Usuario u = new Usuario(rs.getInt("id_usuario"), rs.getInt("id_rol"), rs.getString("nombre"),
                        rs.getString("apellido_pat"), rs.getString("apellido_mat"), rs.getString("nombre_usuario"),
                        rs.getString("contrasena"), rs.getString("email"), rs.getString("telefono"),
                        rs.getString("especialidad"),rs.getInt("tiempo_cita"), rs.getBoolean("activo"));
                usuarios.add(u);
            }
            rs.close();
            con.close();
            return usuarios;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
    public static ArrayList<Usuario> listarProfesionales(){
        ArrayList<Usuario> usuarios = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM usuario WHERE id_rol=3");
            usuarios = new ArrayList<Usuario>();
            while (rs.next())
            {
                Usuario u = new Usuario(rs.getInt("id_usuario"), rs.getInt("id_rol"), rs.getString("nombre"),
                        rs.getString("apellido_pat"), rs.getString("apellido_mat"), rs.getString("nombre_usuario"),
                        rs.getString("contrasena"), rs.getString("email"), rs.getString("telefono"),
                        rs.getString("especialidad"),rs.getInt("tiempo_cita"), rs.getBoolean("activo"));
                usuarios.add(u);
            }
            rs.close();
            con.close();
            return usuarios;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
    public static boolean habilitarUsuario(int id_usuario){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE usuario\n" +
                    "SET activo='"+1+"'\n" +
                    "WHERE id_usuario="+id_usuario);
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static boolean eliminarUsuario(int id_usuario){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE usuario\n" +
                    "SET activo="+0+"\n" +
                    "WHERE id_usuario="+id_usuario);
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    //Profesional
    public static boolean crearUsuario(int idRol, String nombre, String apellidoPat, String apellidoMat, String nombreUsuario, String contrasena,
                                        String email, String telefono, String especialidad, int tiempoCita) throws Exception{
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            contrasena = encripMd5(contrasena);
            
            int aux = st.executeUpdate("INSERT INTO usuario (id_rol, nombre, apellido_pat, apellido_mat, nombre_usuario, contrasena,"
                                        + "email, telefono, especialidad, tiempo_cita)\n" +
                                        "VALUES ("+idRol+",'"+nombre+"','"+apellidoPat+"','"+apellidoMat+"','"+nombreUsuario+"','"+contrasena
                                        +"','"+email+"','"+telefono+"','"+especialidad+"',"+tiempoCita+")");
            
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    //Basico
    public static boolean crearUsuario(int idRol, String nombre, String apellidoPat, String apellidoMat, String nombreUsuario, String contrasena,
                                        String email, String telefono) throws Exception{
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            contrasena = encripMd5(contrasena);
            
            int aux = st.executeUpdate("INSERT INTO usuario (id_rol, nombre, apellido_pat, apellido_mat, nombre_usuario, contrasena,"
                                        + "email, telefono)\n" +
                                        "VALUES ("+idRol+",'"+nombre+"','"+apellidoPat+"','"+apellidoMat+"','"+nombreUsuario+"','"+contrasena
                                        +"','"+email+"','"+telefono+"')");
            
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static boolean editarUsuario(Usuario usuario) throws Exception{
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            
            int aux = st.executeUpdate("UPDATE usuario\n" +
                    "SET id_rol="+usuario.getId_rol()+"\n" +
                    ",nombre='"+usuario.getNombre()+"'\n" +
                    ",apellido_pat='"+usuario.getApellido_pat()+"'\n" +
                    ",apellido_mat='"+usuario.getApellido_mat()+"'\n" +
                    ",nombre_usuario='"+usuario.getNombreUsuario()+"'\n" +
                    ",contrasena='"+encripMd5(usuario.getContraseña())+"'\n" +
                    ",email='"+usuario.getEmail()+"'\n" +
                    ",telefono='"+usuario.getTelefono()+"'\n" +
                    ",especialidad='"+usuario.getEspecialidad()+"'\n" +
                    ",tiempo_cita="+usuario.getTiempoCita()+"\n" +
                    "WHERE id_usuario="+usuario.getId_usuario());
            
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    static public Usuario getUsuario(String nombreB, String emailB) throws Exception{
        Usuario usuario = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from usuario where nombre='" + nombreB + "' AND email='" + emailB + "'");
            if (rs.next()){
                int id_usuario = rs.getInt("id_usuario");
                int id_rol = rs.getInt("id_rol");
                String nombre = rs.getString("nombre");
                String apellido_pat = rs.getString("apellido_pat");
                String apellido_mat = rs.getString("apellido_mat");
                String userName = rs.getString("nombre_usuario");
                String contraseña = rs.getString("contrasena");
                String email  = rs.getString("email");
                String telefono  = rs.getString("telefono");
                String especialidad  = rs.getString("especialidad");
                int tiempoCita = rs.getInt("tiempo_cita");
                boolean activo = rs.getBoolean("activo");

                usuario = new Usuario(id_usuario, id_rol, nombre, apellido_pat, apellido_mat, userName, contraseña, email, telefono, especialidad, tiempoCita, activo);
            }
            else{
                usuario = null;
            }
            rs.close();
            con.close();
            return usuario;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
    static public Usuario getUsuario(int id_usuario) throws Exception{
        Usuario usuario = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from usuario where id_usuario=" + id_usuario);
            if (rs.next()){
                int id_rol = rs.getInt("id_rol");
                String nombre = rs.getString("nombre");
                String apellido_pat = rs.getString("apellido_pat");
                String apellido_mat = rs.getString("apellido_mat");
                String userName = rs.getString("nombre_usuario");
                String contraseña = rs.getString("contrasena");
                String email  = rs.getString("email");
                String telefono  = rs.getString("telefono");
                String especialidad  = rs.getString("especialidad");
                int tiempoCita = rs.getInt("tiempo_cita");
                boolean activo = rs.getBoolean("activo");

                usuario = new Usuario(id_usuario, id_rol, nombre, apellido_pat, apellido_mat, userName, contraseña, email, telefono, especialidad, tiempoCita, activo);
            }
            else{
                usuario = null;
            }
            rs.close();
            con.close();
            return usuario;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
    public static ArrayList<ClinicaInterna> listarClinicas(){
        ArrayList<ClinicaInterna> clinicas = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM clinica");
            clinicas = new ArrayList<ClinicaInterna>();
            while (rs.next())
            {
                ClinicaInterna u = new ClinicaInterna(rs.getString("nombre"), rs.getInt("id"));
                clinicas.add(u);
            }
            rs.close();
            con.close();
            return clinicas;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
}