/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.SQLException;
import modelo.TratamientoPiezaPlan;

/**
 *
 * @author msanhuezal
 */
public class TratamientoPiezaPlanDB {
    
    public static boolean crearTratamientoPiezaPlan(int idPlanTratamiento, int idTratamiento, int pieza, String fechaRealizado, boolean estado){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO tratamiento_piezaplan (id_plantratamiento, id_tratamiento, pieza, fecha_realizado, estado)\n" +
                                        "VALUES ("+idPlanTratamiento+","+idTratamiento+","+pieza+",'"+fechaRealizado+"',"+estado+")");
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }  
    
    public static boolean editarTratamientoPiezaPlan(TratamientoPiezaPlan tratamientoPiezaPlan){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE tratamiento_piezaplan\n" +
                                            "SET id_plantratamiento = "+tratamientoPiezaPlan.getIdPlanTratamiento()+"\n" +
                                            ",id_tratamiento="+tratamientoPiezaPlan.getIdTratamiento()+"\n" +
                                            ",pieza="+tratamientoPiezaPlan.getPieza()+"\n" +
                                            "fecha_realizado='"+tratamientoPiezaPlan.getFechaRealizado()+"'\n" +
                                            ",estado="+tratamientoPiezaPlan.isEstado()+"\n" +
                                            "WHERE id_tratamiento_piezaplan="+tratamientoPiezaPlan.getIdTratamientoPiezaPlan());
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
