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
import java.util.Date;
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
    
    public static boolean editarPresupuesto(Presupuesto presupuesto){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE presupuesto\n" +
                                            "SET estado="+presupuesto.getEstado()+"\n" +
                                            ",costo_total="+presupuesto.getCostoTotal()+"\n" +
                                            ",cantidad_tratamiento="+presupuesto.getCantidadTratamiento()+"\n" +
                                            ",update_at='"+presupuesto.getFechaModificacion()+"'\n" +
                                            "WHERE id_presupueto="+presupuesto.getIdPresupuesto());
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
                                                rs.getBoolean("activo"), rs.getString("created_at"), rs.getString("update_at"));
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
                                                rs.getBoolean("activo"), PresupuestoDB.convertTimestampToString(rs.getTimestamp("created_at")),
                                                PresupuestoDB.convertTimestampToString(rs.getTimestamp("update_at")));
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

            System.out.println(""+fecha);

            return fecha;
        }
        return "";
    }
    
    static public Presupuesto getPresupuesto(String fechaCreacion) throws Exception{
        Presupuesto presupuesto = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from presupuesto where created_at='" + fechaCreacion + "'");
            if (rs.next())
            {
                int idPresupuesto = rs.getInt("id_presupuesto");
                int idPaciente = rs.getInt("id_paciente");
                int idProfesional = rs.getInt("id_profesional");
                boolean estado = rs.getBoolean("estado");
                int costoTotal = rs.getInt("costo_total");
                int cantidadTratamiento = rs.getInt("cantidad_tratamiento");
                boolean activo = rs.getBoolean("activo");
                String fechaModificacion = PresupuestoDB.convertTimestampToString(rs.getTimestamp("update_at"));

                presupuesto = new Presupuesto(idPresupuesto, idPaciente, idProfesional, estado, costoTotal, cantidadTratamiento, activo, fechaCreacion, fechaModificacion);
            }
            else{
                presupuesto = null;
            }
            rs.close();
            con.close();
            return presupuesto;
        }

        catch ( SQLException e) {
            return null;
        }
    }    
    
}
