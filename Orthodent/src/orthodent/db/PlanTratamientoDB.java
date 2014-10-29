/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.SQLException;
import modelo.PlanTratamiento;

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
    
    
}
