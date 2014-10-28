/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.SQLException;
import modelo.FichaEvolucion;

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
    
    public static boolean editarFichaEvolucion(FichaEvolucion fichaEvolucion){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE ficha_evolucion\n" +
                                            "SET id_plantratamiento = "+fichaEvolucion.getIdPlanTratamiento()+"\n" +
                                            "fecha_cita="+fichaEvolucion.getFechaCita()+"\n" +
                                            ",descripcion="+fichaEvolucion.getDescripcion()+"\n" +
                                            "WHERE id_fichaevolucion="+fichaEvolucion.getIdFichaEvolucion());
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
