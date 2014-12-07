/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Comuna;
import modelo.Region;

/**
 *
 * @author msanhuezal
 */
public class ComunaDB {
    
    public static ArrayList<Comuna> listarComunasPorRegion(int idRegion){
        ArrayList<Comuna> comunas = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM comunas WHERE padre="+idRegion);
            comunas = new ArrayList<Comuna>();
            while (rs.next())
            {
                Comuna rg = new Comuna(rs.getInt("codigoInterno"), rs.getString("nombre"), rs.getInt("padre"));
                comunas.add(rg);
            }
            rs.close();
            con.close();
            return comunas;
        }
        catch ( SQLException e) {
            return null;
        }
    }     
    
}
