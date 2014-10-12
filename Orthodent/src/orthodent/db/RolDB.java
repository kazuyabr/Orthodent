/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Rol;

/**
 *
 * @author Mary
 */
public class RolDB {
    
    static public Rol getRol(int idRol){
        Rol rol = null;
        try {
                DbConnection db = new DbConnection();
                Connection con = db.getConnection();

                java.sql.Statement st = con.createStatement();

                ResultSet rs = st.executeQuery("SELECT * from roles where id_rol="+idRol);
                if (rs.next())
                {
                    int id_rol = rs.getInt("id_rol");
                    String nombre = rs.getString("nombre");
                    
                    rol = new Rol(id_rol, nombre);
                }
                else{
                    rol = null;
                }
                rs.close();             
                con.close();
                return rol;
            }

            catch ( SQLException e) { 
                return null;  
            }       
    }
    
}
