/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Region;

/**
 *
 * @author msanhuezal
 */
public class RegionDB {
    
    public static ArrayList<Region> listarRegiones(){
        ArrayList<Region> regiones = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM regiones");
            regiones = new ArrayList<Region>();
            while (rs.next())
            {
                Region rg = new Region(rs.getInt("codigo"), rs.getString("nombre"));
                regiones.add(rg);
            }
            rs.close();
            con.close();
            return regiones;
        }
        catch ( SQLException e) {
            return null;
        }
    }    
    
}
