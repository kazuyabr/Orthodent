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
import modelo.Presupuesto;

/**
 *
 * @author msanhuezal
 */
public class PresupuestoDB {
    
    public static boolean crearPresupuesto(int idPaciente, int idProfesional, boolean estado, int costoTotal, int cantidadTratamiento, boolean activo, String created_at, String update_at){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO presupuesto (id_paciente, id_profesional, estado, costo_total, cantidad_tratamiento, activo, created_at, update_at)\n" +
                                        "VALUES ("+idPaciente+","+idProfesional+","+estado+","+costoTotal+","+
                                                    cantidadTratamiento+","+activo+","+
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
    
    public static boolean editarPresupuesto(Presupuesto presupuesto){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE presupuesto\n" +
                                            "SET estado="+presupuesto.getEstado()+"\n" +
                                            ",costo_total="+presupuesto.getCostoTotal()+"\n" +
                                            ",cantidad_tratamiento="+presupuesto.getCantidadTratamiento()+"\n" +
                                            ",update_at='"+getTimestamp(presupuesto.getFechaModificacion())+"'\n" +
                                            ",id_profesional="+presupuesto.getIdProfesional()+"\n"+
                                            "WHERE id_presupuesto="+presupuesto.getIdPresupuesto());
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
    
    public static ArrayList<Presupuesto> listarPresupuestosDePaciente(int id_paciente, int id_usuario){
        ArrayList<Presupuesto> presupuestos = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM presupuesto WHERE id_paciente=" + id_paciente + " AND "
                    + "id_profesional="+id_usuario);
            
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

            return fecha;
        }
        return "";
    }
    
    public static String girarFecha(String fecha){
        
        if(fecha!=null){
            String dia = fecha.substring(0, fecha.indexOf("-"));
            fecha = fecha.substring(fecha.indexOf("-")+1, fecha.length());
            
            String mes = fecha.substring(0, fecha.indexOf("-"));
            fecha = fecha.substring(fecha.indexOf("-")+1, fecha.length());
            
            String año = fecha.substring(0, fecha.indexOf(" "));
            fecha = fecha.substring(fecha.indexOf(" ")+1, fecha.length());

            return (año+"-"+mes+"-"+dia+" "+fecha);
        }
        return "";
    }
    
    static public Presupuesto getPresupuesto(String fechaCreacion, int id_paciente) throws Exception{
        Presupuesto presupuesto = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from presupuesto where created_at='" + girarFecha(fechaCreacion) + "' AND id_paciente="+id_paciente);
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
