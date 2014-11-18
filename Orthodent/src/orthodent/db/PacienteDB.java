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
import modelo.Usuario;

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
                                            rs.getString("rut"), rs.getString("email"), rs.getString("fecha_nacimiento"), rs.getInt("edad"), rs.getInt("sexo"),
                                            rs.getString("antecedente_medico"), rs.getString("telefono"), rs.getString("ciudad"),
                                            rs.getString("comuna"), rs.getString("direccion"), rs.getBoolean("activo"));
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
    
    public static ArrayList<Paciente> listarPacientes(int id_usuario){
        ArrayList<Paciente> pacientes = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT paciente.id_paciente, paciente.nombre, paciente.apellido_pat,"
                    + "paciente.apellido_mat, paciente.rut, paciente.email, paciente.fecha_nacimiento, paciente.edad,"
                    + "paciente.sexo, paciente.antecedente_medico, paciente.telefono, paciente.ciudad, paciente.comuna, "
                    + "paciente.direccion, paciente.activo FROM `paciente`, `usuario`, `presupuesto` WHERE "
                    + "paciente.activo=1 AND presupuesto.activo=1 AND "
                    + "usuario.id_usuario="+id_usuario+" AND presupuesto.id_profesional=usuario.id_usuario AND "
                    + "presupuesto.id_paciente=paciente.id_paciente UNION "
                    + "SELECT paciente.id_paciente, paciente.nombre, paciente.apellido_pat,"
                    + "paciente.apellido_mat, paciente.rut, paciente.email, paciente.fecha_nacimiento, paciente.edad,"
                    + "paciente.sexo, paciente.antecedente_medico, paciente.telefono, paciente.ciudad, paciente.comuna, "
                    + "paciente.direccion, paciente.activo FROM `paciente`, `usuario`, `plan_tratamiento` WHERE "
                    + "paciente.activo=1 AND plan_tratamiento.activo=1 AND "
                    + "usuario.id_usuario="+id_usuario+" AND plan_tratamiento.id_profesional=usuario.id_usuario AND "
                    + "plan_tratamiento.id_paciente=paciente.id_paciente");
            
            pacientes = new ArrayList<Paciente>();
            while (rs.next())
            {
                Paciente p = new Paciente(rs.getInt("id_paciente"), rs.getString("nombre"), rs.getString("apellido_pat"), rs.getString("apellido_mat"),
                                            rs.getString("rut"), rs.getString("email"), rs.getString("fecha_nacimiento"), rs.getInt("edad"), rs.getInt("sexo"),
                                            rs.getString("antecedente_medico"), rs.getString("telefono"), rs.getString("ciudad"),
                                            rs.getString("comuna"), rs.getString("direccion"), rs.getBoolean("activo"));
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
        
    public static boolean crearPaciente(String nombre, String apellidoPat, String apellidoMat, String rut, String email,
                                        String fechaNacimiento, int edad, int sexo, String antecedenteMedico, String telefono,
                                        String ciudad, String comuna, String direccion){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO paciente (nombre, apellido_pat, apellido_mat, rut, email, fecha_nacimiento, edad, sexo, antecedente_medico, telefono, ciudad, comuna, direccion)\n" +
                                        "VALUES ('"+nombre+"','"+apellidoPat+"','"+apellidoMat+"','"+rut+"','"+
                                                    email+"','"+fechaNacimiento+"',"+edad+","+sexo+",'"+antecedenteMedico+"','"+
                                                    telefono+"','"+ciudad+"','"+comuna+"','"+direccion+"')");
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
                                            ",email='"+paciente.getEmail()+"'\n" +
                                            ",fecha_nacimiento='"+paciente.getFechaNacimiento()+"'\n" +
                                            ",edad='"+paciente.getEdad()+"'\n" +
                                            ",sexo="+paciente.getSexo()+"\n" +
                                            ",antecedente_medico='"+paciente.getAntecedenteMedico()+"'\n" +
                                            ",telefono='"+paciente.getTelefono()+"'\n" +
                                            ",ciudad='"+paciente.getCiudad()+"'\n" +
                                            ",comuna='"+paciente.getComuna()+"'\n" +
                                            ",direccion='"+paciente.getDireccion()+"'\n" +
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
    
    static public Paciente getPaciente(String rutB) throws Exception{
        Paciente paciente = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from paciente where rut='" + rutB + "'");
            if (rs.next())
            {
                int id_paciente = rs.getInt("id_paciente");
                String nombre = rs.getString("nombre");
                String apellido_pat = rs.getString("apellido_pat");
                String apellido_mat = rs.getString("apellido_mat");
                String rut = rs.getString("rut");
                String email = rs.getString("email");
                String fecha_nacimiento = rs.getString("fecha_nacimiento");
                int edad = rs.getInt("edad");
                int sexo = rs.getInt("sexo");
                String antecedente_medico = rs.getString("antecedente_medico");
                String telefono  = rs.getString("telefono");
                String ciudad = rs.getString("ciudad");
                String comuna = rs.getString("comuna");
                String direccion = rs.getString("direccion");
                boolean activo = rs.getBoolean("activo");

                paciente = new Paciente(id_paciente, nombre, apellido_pat, apellido_mat, rut, email, fecha_nacimiento, edad, sexo, antecedente_medico, telefono, ciudad, comuna, direccion, activo);
            }
            else{
                paciente = null;
            }
            rs.close();
            con.close();
            return paciente;
        }

        catch ( SQLException e) {
            return null;
        }
    }
    
    static public boolean validaRut(String rutB) throws Exception{
        boolean aux = true;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from paciente where rut='" + rutB + "'");
            if (rs.next())
            {
                aux = false;
            }
            rs.close();
            con.close();
            return aux;
        }

        catch ( SQLException e) {
            return false;
        }
    }
    
    static public Paciente getPaciente(int id) throws Exception{
        Paciente paciente = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from paciente where id_paciente=" + id + "");
            if (rs.next())
            {
                int id_paciente = rs.getInt("id_paciente");
                String nombre = rs.getString("nombre");
                String apellido_pat = rs.getString("apellido_pat");
                String apellido_mat = rs.getString("apellido_mat");
                String rut = rs.getString("rut");
                String email = rs.getString("email");
                String fecha_nacimiento = rs.getString("fecha_nacimiento");
                int edad = rs.getInt("edad");
                int sexo = rs.getInt("sexo");
                String antecedente_medico = rs.getString("antecedente_medico");
                String telefono  = rs.getString("telefono");
                String ciudad = rs.getString("ciudad");
                String comuna = rs.getString("comuna");
                String direccion = rs.getString("direccion");
                boolean activo = rs.getBoolean("activo");

                paciente = new Paciente(id_paciente, nombre, apellido_pat, apellido_mat, rut, email, fecha_nacimiento, edad, sexo, antecedente_medico, telefono, ciudad, comuna, direccion, activo);
            }
            else{
                paciente = null;
            }
            rs.close();
            con.close();
            return paciente;
        }

        catch ( SQLException e) {
            return null;
        }
    }
    
}