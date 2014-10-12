/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;

/**
 *
 * @author msanhuezal
 */
public class Autenticacion {
    
    static public Usuario logIn(String nombreUsuario, String contrasena){
        Usuario usuario = null;
        try {
                DbConnection db = new DbConnection();
                Connection con = db.getConnection();

                java.sql.Statement st = con.createStatement();

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
    
    static public void enviarCorreo(String destino, String asunto, String mensaje){
        JavaMail mail = new JavaMail();
        mail.send(destino, asunto, mensaje);        
    }
    
    static public boolean recuperarContrasena(String email){
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
                    
                    String nuevaContrasena = "12345678";
                    st.executeUpdate("UPDATE usuario\n" +
                                            "SET contrasena='"+nuevaContrasena+"'\n" +
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
    
}