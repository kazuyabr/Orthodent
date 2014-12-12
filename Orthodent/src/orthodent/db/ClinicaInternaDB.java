/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.ClinicaInterna;

/**
 *
 * @author msanhuezal
 */
public class ClinicaInternaDB {
    
public static ArrayList<ClinicaInterna> listarClinicas(){
        ArrayList<ClinicaInterna> clinicas = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM clinica WHERE activo="+1);
            clinicas = new ArrayList<ClinicaInterna>();
            while (rs.next())
            {
                ClinicaInterna u = new ClinicaInterna(rs.getString("nombre"), rs.getInt("id"), rs.getBoolean("activo"));
                clinicas.add(u);
            }
            rs.close();
            con.close();
            return clinicas;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
    public static boolean crearClinica(ClinicaInterna clinica){    
        boolean resultado = false;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            int aux = st.executeUpdate("INSERT INTO clinica (nombre) VALUES ('"+clinica.getNombre()+"')");
            ResultSet rs = st.getGeneratedKeys();
            if(rs!=null){
                while(rs.next()){
                    clinica.setId(Integer.parseInt(rs.getObject(1).toString()));
                }
            }
            
            resultado = (aux==1) ? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean eliminarClinica(ClinicaInterna clinica){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE clinica\n" +
                                        "SET activo='"+0+"'\n" +
                                        "WHERE id="+clinica.getId());
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }     
    
    public static boolean actualizarClinica(ClinicaInterna clinica){
        boolean resultado = false;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            
            int aux = st.executeUpdate("UPDATE clinica SET nombre='"+clinica.getNombre()+"' WHERE id="+clinica.getId());
            resultado = (aux == 1)? true : false;
        } catch (SQLException ex) {
            return false;
        }
        return resultado;
    }
    
    public static ClinicaInterna getClinica(String nombre)throws Exception{
        ClinicaInterna clinica = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from clinica where nombre='" + nombre + "'");
            if (rs.next()){
               
                String nombreClinica = rs.getString("nombre");
                int id = rs.getInt("id");
                boolean activo = rs.getBoolean("activo");
                
                clinica = new ClinicaInterna(nombreClinica,id, activo);
            }
            else{
                clinica = null;
            }
            rs.close();
            con.close();
            return clinica;
        }
        catch ( SQLException e) {
            return null;
        }
        
    }    
    
}
