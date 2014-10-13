/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                                             rs.getString("rut"), rs.getDate("fecha_nacimiento"), rs.getString("antecedente_medico"), rs.getString("telefono"), 
                                             rs.getBoolean("activo"));
                   pacientes.add(p);
                }
                rs.close();             
                con.close();
                return pacientes;
            }

            catch ( SQLException e) {
                System.out.println(e);
                return null;
            }
}    
    
}
