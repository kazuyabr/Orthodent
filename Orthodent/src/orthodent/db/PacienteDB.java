/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import modelo.Paciente;

/**
 *
 * @author msanhuezal
 */
public class PacienteDB {
    
        public static ArrayList<Paciente> listarPacientes(){
        ArrayList<Paciente> pacientes = null;        
        try {
                DbConnection db = new DbConnection();
                Connection con = db.getConnection();
               
                java.sql.Statement st = con.createStatement();

                ResultSet rs = st.executeQuery("SELECT * FROM paciente");
                pacientes = new ArrayList<Paciente>();
                while (rs.next())
                {
                   Paciente p = new Paciente(rs.getInt("id_paciente"), rs.getString("nombre"), rs.getString("apellido_pat"), rs.getString("apellido_mat"),
                                             rs.getString("rut"), rs.getString("fecha_nacimiento"), rs.getString("antecedente_medico"), rs.getString("telefono"), 
                                             rs.getBoolean("activo"));
                   pacientes.add(p);
                }
                rs.close();             
                con.close();
                return pacientes;
            }

            catch ( SQLException e) {
                return null;
            }
        }
        
        public static boolean habilitarPaciente(int id_paciente){
            try{
                DbConnection db = new DbConnection();
                Connection con = db.connection;

                java.sql.Statement st = con.createStatement();
                int aux = st.executeUpdate("UPDATE paciente\n" +
                                                "SET activo='"+1+"'\n" +
                                                "WHERE id_paciente="+id_paciente);           
                boolean resultado = (aux == 1)? true : false;
                st.close();
                con.close();             
                return resultado;           
            }
            catch ( SQLException e) { 
                return false;
            }            
        }
        
        public static boolean eliminarPaciente(int id_paciente) throws SQLException{
            try{
                DbConnection db = new DbConnection();
                Connection con = db.connection;

                java.sql.Statement st = con.createStatement();
                int aux = st.executeUpdate("UPDATE paciente\n" +
                                                "SET activo="+0+"\n" +
                                                "WHERE id_paciente="+id_paciente);
                boolean resultado = (aux == 1)? true : false;
                st.close();
                con.close();             
                return resultado;           
            }
            catch ( SQLException e) { 
                return false;
            } 
        }
        
        public static boolean crearPaciente(String nombre, String apellidoPat, String apellidoMat, String rut, String fechaNacimiento,
                                            String antecedenteMedico, String telefono){
            try{
                DbConnection db = new DbConnection();
                Connection con = db.connection;

                java.sql.Statement st = con.createStatement();
                int aux = st.executeUpdate("INSERT INTO paciente (nombre, apellido_pat, apellido_mat, rut, fecha_nacimiento, antecedente_medico, telefono)\n" +
                                           "VALUES ('"+nombre+"','"+apellidoPat+"','"+apellidoMat+"','"+rut+"','"+
                                                       fechaNacimiento+"','"+antecedenteMedico+"','"+telefono+"')");
                boolean resultado = (aux == 1)? true : false;
                st.close();
                con.close();             
                return resultado;           
            }
            catch ( SQLException e) { 
                return false;
            }             
            
        }
        
        public static boolean editarPaciente(Paciente paciente){
            try{
                DbConnection db = new DbConnection();
                Connection con = db.connection;

                java.sql.Statement st = con.createStatement();
                int aux = st.executeUpdate("UPDATE paciente\n" +
                                                "SET nombre='"+paciente.getNombre()+"'\n" +
                                                ",apellido_pat='"+paciente.getApellido_pat()+"'\n" +
                                                ",apellido_mat='"+paciente.getApellido_mat()+"'\n" +
                                                ",rut='"+paciente.getRut()+"'\n" +
                                                ",fecha_nacimiento='"+paciente.getFechaNacimiento()+"'\n" +
                                                ",antecedente_medico='"+paciente.getAntecedenteMedico()+"'\n" +
                                                ",telefono='"+paciente.getTelefono()+"'\n" +
                                                "WHERE id_paciente="+paciente.getId_paciente());
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
