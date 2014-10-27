/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.TratamientoPiezaPresupuesto;

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
    
    public static ArrayList<TratamientoPiezaPresupuesto> listarTratamientosPiezaPresupuesto(){
        ArrayList<TratamientoPiezaPresupuesto> tratamientosPP = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM tratamiento_piezapresupuesto");
            tratamientosPP = new ArrayList<TratamientoPiezaPresupuesto>();
            while (rs.next())
            {
                TratamientoPiezaPresupuesto t = new TratamientoPiezaPresupuesto(rs.getInt("id_tratamiento"), rs.getInt("id_presupuesto"), rs.getString("pieza"));
                tratamientosPP.add(t);
            }
            rs.close();
            con.close();
            return tratamientosPP;
        }
        catch ( SQLException e) {
            return null;
        }
    }      
    
    
}
