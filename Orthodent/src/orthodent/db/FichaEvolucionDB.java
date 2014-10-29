/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                                            "fecha_cita='"+fichaEvolucion.getFechaCita()+"'\n" +
                                            ",descripcion='"+fichaEvolucion.getDescripcion()+"'\n" +
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
    
    public static ArrayList<FichaEvolucion> listarFichaEvolucion(){
        ArrayList<FichaEvolucion> fichasEvolucion = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM ficha_evolucion");
            fichasEvolucion = new ArrayList<FichaEvolucion>();
            while (rs.next())
            {
                FichaEvolucion f = new FichaEvolucion(rs.getInt("id_fichaevolucion"), rs.getInt("id_plantratamiento"), rs.getString("fecha_cita"), rs.getString("descripcion"));
                fichasEvolucion.add(f);
            }
            rs.close();
            con.close();
            return fichasEvolucion;
        }
        catch ( SQLException e) {
            return null;
        }
    }     
    
}
