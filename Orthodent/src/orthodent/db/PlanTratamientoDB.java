/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import modelo.PlanTratamiento;
import static orthodent.db.PresupuestoDB.getTimestamp;

/**
 *
 * @author msanhuezal
 */
public class PlanTratamientoDB {
    
    public static boolean crearPlanTratamiento(int idPaciente, int idProfesional, String fechaCreacionPresupuesto, String fechaModificacionPresupuesto,
                                               int costoTotal, int totalAbonos, int avance, boolean activo, String created_at, String update_at){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO plan_tratamiento (id_paciente, id_profesional, fecha_creacion_presupuesto, fecha_modificacion_presupuesto, costo_total, total_abonos, avance, activo, created_at, update_at)\n" +
                                        "VALUES ("+idPaciente+","+idProfesional+",'"+getTimestamp(fechaCreacionPresupuesto)+"','"+getTimestamp(fechaModificacionPresupuesto)+"',"+
                                                    costoTotal+","+totalAbonos+","+avance+","+activo+","+
                                                    "'"+getTimestamp(created_at)+"','"+getTimestamp(update_at)+"')");
            
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static Timestamp getTimestamp(String fecha){
        
        int dia = Integer.parseInt(fecha.substring(0, fecha.indexOf("-")));
        fecha = fecha.substring(fecha.indexOf("-")+1, fecha.length());
        
        int mes = Integer.parseInt(fecha.substring(0,fecha.indexOf("-")))-1;
        fecha = fecha.substring(fecha.indexOf("-")+1, fecha.length());
        
        int año = Integer.parseInt(fecha.substring(0, fecha.indexOf(" ")))-1900;
        fecha = fecha.substring(fecha.indexOf(" ")+1, fecha.length());
        
        int hora = Integer.parseInt(fecha.substring(0, fecha.indexOf(":")));
        fecha = fecha.substring(fecha.indexOf(":")+1, fecha.length());
        
        int minuto = Integer.parseInt(fecha.substring(0, fecha.indexOf(":")));
        fecha = fecha.substring(fecha.indexOf(":")+1, fecha.length());
        
        int segundo = Integer.parseInt(fecha);
        
        Timestamp date = new Timestamp(año, mes, dia, hora, minuto, segundo, 0);
        
        return date;
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
                                            ",update_at='"+getTimestamp(planTratamiento.getFechaModificacion())+"'\n" +
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
                                        convertTimestampToString(rs.getTimestamp("fecha_creacion_presupuesto")),convertTimestampToString(rs.getTimestamp("fecha_modificacion_presupuesto")),
                                        rs.getInt("costo_total"), rs.getInt("total_abonos"), rs.getInt("avance"), rs.getBoolean("activo"),
                                        convertTimestampToString(rs.getTimestamp("created_at")), convertTimestampToString(rs.getTimestamp("update_at")));
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
    
    public static String convertTimestampToString(Timestamp date){
        
        if(date!=null){
            String fecha = "";

            if(date.getDate()<9){
                fecha = fecha + "0";
            }
            fecha = fecha + date.getDate() + "-";

            if((date.getMonth()+1)<9){
                fecha = fecha + "0";
            }
            fecha = fecha + (date.getMonth()+1) + "-";

            fecha = fecha + (date.getYear()+1900) + " ";

            if((date.getHours())<9){
                fecha = fecha + "0";
            }
            fecha = fecha + date.getHours() + ":";

            if((date.getMinutes())<9){
                fecha = fecha + "0";
            }
            fecha = fecha + date.getMinutes()+ ":";

            if((date.getSeconds())<9){
                fecha = fecha + "0";
            }
            fecha = fecha + date.getSeconds();

            return fecha;
        }
        return "";
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
                                        convertTimestampToString(rs.getTimestamp("fecha_creacion_presupuesto")),convertTimestampToString(rs.getTimestamp("fecha_modificacion_presupuesto")),
                                        rs.getInt("costo_total"), rs.getInt("total_abonos"), rs.getInt("avance"), rs.getBoolean("activo"),
                                        convertTimestampToString(rs.getTimestamp("created_at")), convertTimestampToString(rs.getTimestamp("update_at")));
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
    
public static ArrayList<PlanTratamiento> listarPlanesTratamientoPaciente(int id_paciente){
        ArrayList<PlanTratamiento> planesTratamiento = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM plan_tratamiento WHERE id_paciente=" + id_paciente);
            planesTratamiento = new ArrayList<PlanTratamiento>();
            while (rs.next())
            {
                PlanTratamiento p = new PlanTratamiento(rs.getInt("id_plantratamiento"), rs.getInt("id_paciente"), rs.getInt("id_profesional"), 
                                        convertTimestampToString(rs.getTimestamp("fecha_creacion_presupuesto")),convertTimestampToString(rs.getTimestamp("fecha_modificacion_presupuesto")),
                                        rs.getInt("costo_total"), rs.getInt("total_abonos"), rs.getInt("avance"), rs.getBoolean("activo"),
                                        convertTimestampToString(rs.getTimestamp("created_at")), convertTimestampToString(rs.getTimestamp("update_at")));
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

static public PlanTratamiento getPlanTratamiento(int idPaciente) throws Exception{
        PlanTratamiento planTratamiento = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from plan_tratamiento where id_paciente="+idPaciente);
            if (rs.next())
            {
                int idPlantratamiento = rs.getInt("id_plantratamiento");
                int idProfesional = rs.getInt("id_profesional");
                String fechaCreacionPresupuesto = convertTimestampToString(rs.getTimestamp("fecha_creacion_presupuesto"));
                String fechaModificacionPresupuesto = convertTimestampToString(rs.getTimestamp("fecha_modificacion_presupuesto"));
                int costoTotal = rs.getInt("costo_total");
                int totalAbonos = rs.getInt("total_abonos");
                int avance = rs.getInt("avance");
                boolean activo = rs.getBoolean("activo");
                String created_at = convertTimestampToString(rs.getTimestamp("created_at"));
                String update_at = convertTimestampToString(rs.getTimestamp("update_at"));

                planTratamiento = new PlanTratamiento(idPlantratamiento, idPaciente, idProfesional, fechaCreacionPresupuesto, fechaModificacionPresupuesto,
                                                  costoTotal, totalAbonos, avance, activo, created_at, update_at);
            }
            else{
                planTratamiento = null;
            }
            rs.close();
            con.close();
            return planTratamiento;
        }
        catch ( SQLException e) {
            return null;
        }
    }
}
