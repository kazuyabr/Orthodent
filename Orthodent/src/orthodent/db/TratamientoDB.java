/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Tratamiento;

/**
 *
 * @author msanhuezal
 */
public class TratamientoDB {
    
    public static boolean crearTratamiento(int idCategoria2, String nombre, int cantidadUCO){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO tratamiento (id_categoria2, nombre, cantidad_uco)\n" +
                                        "VALUES ("+idCategoria2+",'"+nombre+"',"+cantidadUCO+")");
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static ArrayList<Tratamiento> listarTratamientosCategoria2(int idCategoria2){
        ArrayList<Tratamiento> tratamientos = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet ru = st.executeQuery("SELECT * from valor_uco where id_valor_uco=1");
            
            int valorUCO = 0;
            int porcentaje = 0;
            if(ru.next()){
                valorUCO = ru.getInt("valor");
                porcentaje = ru.getInt("porcentaje");
            }
            ru.close();
            
            ResultSet rs = st.executeQuery("SELECT * FROM tratamiento where activo="+1+" AND tratamiento.id_categoria2="+idCategoria2);
            tratamientos = new ArrayList<Tratamiento>();
            
            while (rs.next())
            {
                int idTratamiento = rs.getInt("id_tratamiento");
                String nombre = rs.getString("nombre");
                float cantidadUCO = rs.getFloat("cantidad_uco");
                int valorColegio = (int)cantidadUCO*valorUCO;
                int valorClinica = (valorColegio*porcentaje)/100;
                boolean activo = rs.getBoolean("activo");

                Tratamiento t = new Tratamiento(idTratamiento, idCategoria2, nombre, cantidadUCO, valorColegio, valorClinica, activo);
                tratamientos.add(t);
            }
            rs.close();
            con.close();
            return tratamientos;
        }
        catch ( SQLException e) {
            return null;
        }
    }    
    
    public static ArrayList<Tratamiento> listarTratamientos(){
        ArrayList<Tratamiento> tratamientos = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet ru = st.executeQuery("SELECT * from valor_uco where id_valor_uco=1");
            
            int valorUCO = 0;
            int porcentaje = 0;
            if(ru.next()){
                valorUCO = ru.getInt("valor");
                porcentaje = ru.getInt("porcentaje");
            }
            ru.close();
            
            ResultSet rs = st.executeQuery("SELECT * FROM tratamiento where activo="+1);
            tratamientos = new ArrayList<Tratamiento>();
            
            while (rs.next())
            {
                int idTratamiento = rs.getInt("id_tratamiento");
                int idCategoria2 = rs.getInt("id_categoria2");
                String nombre = rs.getString("nombre");
                float cantidadUCO = rs.getFloat("cantidad_uco");
                int valorColegio = (int)cantidadUCO*valorUCO;
                int valorClinica = (valorColegio*porcentaje)/100;
                boolean activo = rs.getBoolean("activo");

                Tratamiento t = new Tratamiento(idTratamiento, idCategoria2, nombre, cantidadUCO, valorColegio, valorClinica, activo);
                tratamientos.add(t);
            }
            rs.close();
            con.close();
            return tratamientos;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
    static public Tratamiento getTratamiento(int idTratamiento) throws Exception{
        Tratamiento tratamiento = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from tratamiento where id_tratamiento=" + idTratamiento);
            ResultSet ru = st.executeQuery("SELECT * from valor_uco where id_valor_uco=1");
            
            int valorUCO = 0;
            if(ru.next()){
                valorUCO = ru.getInt("valor");
            }
            if (rs.next())
            {
                int idCategoria2 = rs.getInt("id_categoria2");
                String nombre = rs.getString("nombre");
                float cantidadUCO = rs.getFloat("cantidad_uco");
                int valorColegio = (int)cantidadUCO*valorUCO;
                int valorClinica = valorColegio/2;
                boolean activo = rs.getBoolean("activo");

                tratamiento = new Tratamiento(idTratamiento, idCategoria2, nombre, cantidadUCO, valorColegio, valorClinica, activo);
            }
            else{
                tratamiento = null;
            }
            rs.close();
            con.close();
            return tratamiento;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
    public static boolean eliminarTratamiento(int idTratamiento){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE tratamiento\n" +
                                        "SET activo='"+0+"'\n" +
                                        "WHERE id_tratamiento="+idTratamiento);
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
