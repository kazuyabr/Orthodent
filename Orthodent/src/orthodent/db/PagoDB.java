/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.SQLException;
import modelo.Pago;
import modelo.Presupuesto;

/**
 *
 * @author msanhuezal
 */
public class PagoDB {
    
    public static boolean crearPago(int idPlanTratamiento, String fechaCita, int abono){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO pago (id_plantratamiento, fecha, abono)\n" +
                                        "VALUES ("+idPlanTratamiento+",'"+fechaCita+"',"+abono+")");
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static boolean editarPago(Pago pago){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE pago\n" +
                                            "SET fecha="+pago.getFecha()+"\n" +
                                            ",abono="+pago.getAbono()+"\n" +
                                            "WHERE id_plantratamiento="+pago.getIdPlanTratamiento());
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
