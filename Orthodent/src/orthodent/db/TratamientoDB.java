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
    
    public static ArrayList<Tratamiento> listarTratamientos(){
        ArrayList<Tratamiento> tratamientos = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM tratamiento");
            tratamientos = new ArrayList<Tratamiento>();
            while (rs.next())
            {
                Tratamiento t = new Tratamiento(rs.getInt("id_tratamiento"), rs.getString("nombre"), rs.getInt("valor_colegio"), rs.getInt("valor_clinica"));
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
            if (rs.next())
            {
                String nombre = rs.getString("nombre");
                int valorColegio = rs.getInt("valor_colegio");
                int valorClinica = rs.getInt("valor_clinica");

                tratamiento = new Tratamiento(idTratamiento, nombre, valorColegio, valorClinica);
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
    
    
    
}
