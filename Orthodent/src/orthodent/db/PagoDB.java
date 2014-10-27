/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Pago;
import modelo.Presupuesto;

/**
 *
 * @author msanhuezal
 */
public class PagoDB {
    
    public static boolean crearPago(int idPlanTratamiento, String fechaCita, int abono){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO pago (id_plantratamiento, fecha, abono)\n" +
                                        "VALUES ("+idPlanTratamiento+",'"+fechaCita+"',"+abono+")");
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static boolean editarPago(Pago pago){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE pago\n" +
                                            "SET id_plantratamiento = "+pago.getIdPlanTratamiento()+"\n" +
                                            "fecha="+pago.getFecha()+"\n" +
                                            ",abono="+pago.getAbono()+"\n" +
                                            "WHERE id_pago="+pago.getIdPago());
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static ArrayList<Pago> listarPagos(){
        ArrayList<Pago> pagos = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM pago");
            pagos = new ArrayList<Pago>();
            while (rs.next())
            {
                Pago p = new Pago(rs.getInt("id_pago"), rs.getInt("id_plantratamiento"), rs.getString("fecha"), rs.getInt("abono"));
                pagos.add(p);
            }
            rs.close();
            con.close();
            return pagos;
        }
        catch ( SQLException e) {
            return null;
        }
    }   
    
    public static ArrayList<Pago> listarPagosDePlanTratamiento(int idPlanTratamiento){
        ArrayList<Pago> pagos = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM pago where id_plantratamiento=" + idPlanTratamiento);
            pagos = new ArrayList<Pago>();
            while (rs.next())
            {
                Pago p = new Pago(rs.getInt("id_pago"), rs.getInt("id_plantratamiento"), rs.getString("fecha"), rs.getInt("abono"));
                pagos.add(p);
            }
            rs.close();
            con.close();
            return pagos;
        }
        catch ( SQLException e) {
            return null;
        }
    }     
    
}
