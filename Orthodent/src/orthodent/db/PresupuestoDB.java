/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author msanhuezal
 */
public class PresupuestoDB {
    
    public static boolean crearPresupuesto(int idPaciente, int idProfesional, boolean estado, int costoTotal, int cantidadTratamiento, boolean activo){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO presupuesto (id_paciente, id_profesional, estado, costo_total, cantidad_tratamiento, activo)\n" +
                                        "VALUES ("+idPaciente+","+idProfesional+","+estado+","+costoTotal+","+
                                                    cantidadTratamiento+","+activo+")");
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
