/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Paciente;
import modelo.Presupuesto;

/**
 *
 * @author msanhuezal
 */
public class PresupuestoDB {
    
    public static boolean crearPresupuesto(int idPaciente, int idProfesional, boolean estado, int costoTotal, int cantidadTratamiento, boolean activo){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO presupuesto (id_paciente, id_profesional, estado, costo_total, cantidad_tratamiento, activo)\n" +
                                        "VALUES ("+idPaciente+","+idProfesional+","+estado+","+costoTotal+","+
                                                    cantidadTratamiento+","+activo+")");
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static boolean eliminarPresupuesto(int idPresupuesto) throws SQLException{
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE presupuesto\n" +
                                            "SET activo="+0+"\n" +
                                            "WHERE id_presupuesto="+idPresupuesto);
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }    
    
    public static ArrayList<Presupuesto> listarPresupuestos(){
        ArrayList<Presupuesto> presupuestos = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM presupuesto");
            presupuestos = new ArrayList<Presupuesto>();
            while (rs.next())
            {
                Presupuesto p = new Presupuesto(rs.getInt("id_presupuesto"), rs.getInt("id_paciente"), rs.getInt("id_profesional"),
                                                rs.getBoolean("estado"), rs.getInt("costo_total"), rs.getInt("cantidad_tratamiento"),
                                                rs.getBoolean("activo"));
                presupuestos.add(p);
            }
            rs.close();
            con.close();
            return presupuestos;
        }
        catch ( SQLException e) {
            return null;
        }
    }    
    
    public static ArrayList<Presupuesto> listarPresupuestosDePaciente(int id_paciente){
        ArrayList<Presupuesto> presupuestos = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM presupuesto where id_paciente=" + id_paciente);
            presupuestos = new ArrayList<Presupuesto>();
            while (rs.next())
            {
                Presupuesto p = new Presupuesto(rs.getInt("id_presupuesto"), rs.getInt("id_paciente"), rs.getInt("id_profesional"),
                                                rs.getBoolean("estado"), rs.getInt("costo_total"), rs.getInt("cantidad_tratamiento"),
                                                rs.getBoolean("activo"));
                presupuestos.add(p);
            }
            rs.close();
            con.close();
            return presupuestos;
        }
        catch ( SQLException e) {
            return null;
        }
    }     
    
}
