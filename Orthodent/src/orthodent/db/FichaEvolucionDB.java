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
public class FichaEvolucionDB {
    
    public static boolean crearFichaEvolucion(int idPlanTratamiento, String fechaCita, String descripcion){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO ficha_evolucion (id_plantratamiento, fecha_cita, descripcion)\n" +
                                        "VALUES ("+idPlanTratamiento+",'"+fechaCita+"','"+descripcion+"')");
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
