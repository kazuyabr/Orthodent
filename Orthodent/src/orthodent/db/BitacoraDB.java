/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import modelo.Bitacora;
import static orthodent.db.PresupuestoDB.getTimestamp;

/**
 *
 * @author felipe
 */
public class BitacoraDB 
{
 
    
     public static boolean crearBitacora(int idBitacora, String accion, int idUsuario, String tabla, int pk, String fecha, String created_at, String update_at){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO bitacora (id_bitacora, operacion, usuario, tabla, primary_key, created_at, update_at)\n" +
                                        "VALUES ("+idBitacora+","+accion+","+idUsuario+","+tabla+","+
                                                    pk+","+
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
     
     public static ArrayList<Bitacora> listarBitacora(){
        ArrayList<Bitacora> bitacoras = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM bitacora");
            bitacoras = new ArrayList<Bitacora>();
            while (rs.next())
            {
                Bitacora b = new Bitacora(rs.getInt("id_bitacora"), rs.getString("operacion"),rs.getInt("usuario"),
                                                rs.getString("tabla"), rs.getInt("primary_key"), BitacoraDB.convertTimestampToString(rs.getTimestamp("created_at")));
                                                
                bitacoras.add(b);
            }
            rs.close();
            con.close();
            return bitacoras;
        }
        catch ( SQLException e) {
            return null;
        }
    }
     
     public static boolean eliminarBitacora(int idBitacora) throws SQLException{
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE bitacora\n" +
                                            "SET activo="+0+"\n" +
                                            "WHERE id_bitacora="+idBitacora);
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
     
    
}
