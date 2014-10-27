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
public class TratamientoPiezaPresupuestoDB {
    
    public static boolean crearTratamientoPiezaPresupuesto(int id_tratamiento, int id_presupuesto, String pieza){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO tratamiento_piezapresupuesto (id_tratamiento, id_presupuesto, pieza)\n" +
                                        "VALUES ("+id_tratamiento+","+id_presupuesto+",'"+pieza+"')");
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
