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
                                           rs.getString("contrasena"), rs.getString("email"), rs.getString("telefono"), rs.getString("especialidad"), 
                                           rs.getInt("tiempo_cita"), rs.getBoolean("activo"));
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
       
        
       
    
    
}