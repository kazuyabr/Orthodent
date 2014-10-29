/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.PlanTratamiento;
import modelo.Presupuesto;

/**
 *
 * @author msanhuezal
 */
public class PlanTratamientoDB {
    
    public static boolean crearPlanTratamiento(int idPaciente, int idProfesional, String fechaCreacionPresupuesto, String fechaModificacionPresupuesto,
                                               int costoTotal, int totalAbonos, int avance, boolean activo){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO plan_tratamiento (id_paciente, id_profesional, fecha_creacion_presupuesto, fecha_modificacion_presupuesto, costo_total, total_abonos, avance, activo)\n" +
                                        "VALUES ("+idPaciente+","+idProfesional+",'"+fechaCreacionPresupuesto+"','"+fechaModificacionPresupuesto+"',"+
                                                    costoTotal+","+totalAbonos+","+avance+","+activo+")");
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static boolean editarPlanTratamiento(PlanTratamiento planTratamiento){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE plan_tratamiento\n" +
                                            "SET id_paciente="+planTratamiento.getIdPaciente()+"\n" +
                                            ",id_profesional="+planTratamiento.getIdProfesional()+"\n" +
                                            ",fecha_creacion_presupuesto='"+planTratamiento.getFechaCreacionPresupuesto()+"'\n" +
                                            ",fecha_modificacion_presupuesto='"+planTratamiento.getFechaModificacionPresupuesto()+"'\n" +
                                            ",costo_total="+planTratamiento.getCostoTotal()+"\n" +
                                            ",total_abonos="+planTratamiento.getTotalAbonos()+"\n" +
                                            ",avance="+planTratamiento.getAvance()+"\n" +
                                            ",activo="+planTratamiento.isActivo()+"\n" +
                                            "WHERE id_plantratamiento="+planTratamiento.getIdPlanTratamiento());
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }    
    
    public static boolean eliminarPlanTratamiento(int idPlanTratamiento) throws SQLException{
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE plan_tratamiento\n" +
                                            "SET activo="+0+"\n" +
                                            "WHERE id_plantratamiento="+idPlanTratamiento);
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static ArrayList<PlanTratamiento> listarPlanesTratamiento(){
        ArrayList<PlanTratamiento> planesTratamiento = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM plan_tratamiento");
            planesTratamiento = new ArrayList<PlanTratamiento>();
            while (rs.next())
            {
                PlanTratamiento p = new PlanTratamiento(rs.getInt("id_plantratamiento"), rs.getInt("id_paciente"), rs.getInt("id_profesional"), 
                                        rs.getString("fecha_creacion_presupuesto"), rs.getString("fecha_modificacion_presupuesto"), rs.getInt("costo_total"),
                                        rs.getInt("total_abonos"), rs.getInt("avance"), rs.getBoolean("activo"));
                planesTratamiento.add(p);
            }
            rs.close();
            con.close();
            return planesTratamiento;
        }
        catch ( SQLException e) {
            return null;
        }
    }    
    
    public static ArrayList<PlanTratamiento> listarPlanesTratamientoPaciente(int id_paciente, int id_usuario){
        ArrayList<PlanTratamiento> planesTratamiento = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM plan_tratamiento WHERE id_paciente=" + id_paciente + " AND "
                    + "id_profesional="+id_usuario);
            planesTratamiento = new ArrayList<PlanTratamiento>();
            while (rs.next())
            {
                PlanTratamiento p = new PlanTratamiento(rs.getInt("id_plantratamiento"), rs.getInt("id_paciente"), rs.getInt("id_profesional"), 
                                        rs.getString("fecha_creacion_presupuesto"), rs.getString("fecha_modificacion_presupuesto"), rs.getInt("costo_total"),
                                        rs.getInt("total_abonos"), rs.getInt("avance"), rs.getBoolean("activo"));
                planesTratamiento.add(p);
            }
            rs.close();
            con.close();
            return planesTratamiento;
        }
        catch ( SQLException e) {
            return null;
        }
    }      
    
    
}
