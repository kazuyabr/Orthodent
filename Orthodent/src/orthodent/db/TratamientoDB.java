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
public class TratamientoDB {
    
    public static boolean crearTratamiento(String nombre, int ValorColegio, int ValorClinica){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO tratamiento (nombre, valor_colegio, valor_clinica)\n" +
                                        "VALUES ('"+nombre+"',"+ValorColegio+","+ValorClinica+")");
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
