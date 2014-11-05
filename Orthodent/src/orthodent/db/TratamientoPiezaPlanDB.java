/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.TratamientoPiezaPlan;

/**
 *
 * @author msanhuezal
 */
public class TratamientoPiezaPlanDB {
    
    public static boolean crearTratamientoPiezaPlan(int idPlanTratamiento, int idTratamiento, int pieza, boolean estado){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO tratamiento_piezaplan (id_plantratamiento, id_tratamiento, pieza, estado)\n" +
                                        "VALUES ("+idPlanTratamiento+","+idTratamiento+","+pieza+","+estado+")");
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
    
    public static ArrayList<TratamientoPiezaPlan> listarTratamientosPiezaPlan(){
        ArrayList<TratamientoPiezaPlan> tratamientosPiezaPlan = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM tratamiento_piezaplan");
            tratamientosPiezaPlan = new ArrayList<TratamientoPiezaPlan>();
            while (rs.next())
            {
                TratamientoPiezaPlan t = new TratamientoPiezaPlan(rs.getInt("id_tratamiento_piezaplan"), rs.getInt("id_plantratamiento"), 
                                                                            rs.getInt("id_tratamiento"), rs.getInt("pieza"),
                                                                            rs.getString("fecha_realizado"), rs.getBoolean("estado"));
                tratamientosPiezaPlan.add(t);
            }
            rs.close();
            con.close();
            return tratamientosPiezaPlan;
        }
        catch ( SQLException e) {
            return null;
        }
    }   
    
    public static ArrayList<TratamientoPiezaPlan> listarTratamientosPiezaPlanPT(int idPlanTratamiento){ // listar tratamiento pieza plan por plan de tratamiento
        ArrayList<TratamientoPiezaPlan> tratamientosPiezaPlan = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM tratamiento_piezaplan where id_plantratamiento=" + idPlanTratamiento);
            tratamientosPiezaPlan = new ArrayList<TratamientoPiezaPlan>();
            while (rs.next())
            {
                TratamientoPiezaPlan t = new TratamientoPiezaPlan(rs.getInt("id_tratamiento_piezaplan"), rs.getInt("id_plantratamiento"), 
                                                                            rs.getInt("id_tratamiento"), rs.getInt("pieza"),
                                                                            rs.getString("fecha_realizado"), rs.getBoolean("estado"));
                tratamientosPiezaPlan.add(t);
            }
            rs.close();
            con.close();
            return tratamientosPiezaPlan;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
    static public TratamientoPiezaPlan getTratamientoPiezaPlan(int idTratamientoPiezaPlan) throws Exception{
        TratamientoPiezaPlan tratamientoPiezaPlan = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from tratamiento_piezaplan where id_tratamiento_piezaplan=" + idTratamientoPiezaPlan);
            if (rs.next())
            {
                int idPlanTratamiento = rs.getInt("id_plantratamiento");
                int idTratamiento = rs.getInt("id_tratamiento");
                int pieza = rs.getInt("pieza");
                String fechaRealizado = rs.getString("fecha_realizado");
                boolean estado = rs.getBoolean("estado");

                tratamientoPiezaPlan = new TratamientoPiezaPlan(idTratamientoPiezaPlan, idPlanTratamiento, idTratamiento, pieza, fechaRealizado, estado);
            }
            else{
                tratamientoPiezaPlan = null;
            }
            rs.close();
            con.close();
            return tratamientoPiezaPlan;
        }

        catch ( SQLException e) {
            return null;
        }
    }     
    
    
}
