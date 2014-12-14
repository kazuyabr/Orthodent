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

/**
 *
 * @author msanhuezal
 */
public class PagoDB {
    
    public static boolean crearPago(int idPlanTratamiento, String tipoPago, String detalle, String fecha, int abono, int numBoleta, boolean isLab){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO pago (id_plantratamiento, tipo_pago, detalle, fecha, abono, num_boleta, is_lab)\n" +
                                        "VALUES ("+idPlanTratamiento+",'"+tipoPago+"','"+detalle+"','"+fecha+"',"+abono+
                                                 ","+numBoleta+","+isLab+")");
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
                                            "tipo_pago='"+pago.getTipoPago()+"'\n" +
                                            "detalle='"+pago.getDetalle()+"'\n" +
                                            "fecha='"+pago.getFecha()+"'\n" +
                                            ",abono="+pago.getAbono()+"\n" +
                                            ",num_boleta="+pago.getNumBoleta()+"\n" +
                                            ",is_lab="+pago.getIsLab()+"\n" +
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
            
            ResultSet rs = st.executeQuery("SELECT * FROM pago ORDER BY fecha DESC");
            pagos = new ArrayList<Pago>();
            while (rs.next())
            {
                Pago p = new Pago(rs.getInt("id_pago"), rs.getInt("id_plantratamiento"), rs.getString("tipo_pago"),
                                  rs.getString("detalle"), rs.getString("fecha"), rs.getInt("abono"),
                                  rs.getInt("num_boleta"), rs.getBoolean("is_lab"));
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
            
            ResultSet rs = st.executeQuery("SELECT * FROM pago where is_lab=0 AND id_plantratamiento=" + idPlanTratamiento);
            pagos = new ArrayList<Pago>();
            while (rs.next())
            {
                Pago p = new Pago(rs.getInt("id_pago"), rs.getInt("id_plantratamiento"), rs.getString("tipo_pago"),
                                  rs.getString("detalle"), rs.getString("fecha"), rs.getInt("abono"),
                                  rs.getInt("num_boleta"), rs.getBoolean("is_lab"));
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
    
    public static ArrayList<Pago> listarPagosDePlanTratamientoLab(int idPlanTratamiento){
        ArrayList<Pago> pagos = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM pago where is_lab=1 AND id_plantratamiento=" + idPlanTratamiento);
            pagos = new ArrayList<Pago>();
            while (rs.next())
            {
                Pago p = new Pago(rs.getInt("id_pago"), rs.getInt("id_plantratamiento"), rs.getString("tipo_pago"),
                                  rs.getString("detalle"), rs.getString("fecha"), rs.getInt("abono"),
                                  rs.getInt("num_boleta"), rs.getBoolean("is_lab"));
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
    
    static public Pago getPago(int idPago) throws Exception{
        Pago pago = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from pago where id_pago=" + idPago);
            if (rs.next())
            {
                int idPlanTratamiento = rs.getInt("id_plantratamiento");
                String tipoPago = rs.getString("tipo_pago");
                String detalle = rs.getString("detalle");
                String fecha = rs.getString("fecha");
                int abono = rs.getInt("abono");
                int numBoleta = rs.getInt("num_boleta");
                boolean isLab = rs.getBoolean("is_lab");

                pago = new Pago(idPago, idPlanTratamiento, tipoPago, detalle, fecha, abono, numBoleta, isLab);
            }
            else{
                pago = null;
            }
            rs.close();
            con.close();
            return pago;
        }

        catch ( SQLException e) {
            return null;
        }
    }     

    public static boolean eliminarPago(int idPago)  throws SQLException{
                try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("DELETE FROM pago\n" +
                                            "WHERE id_pago="+idPago);
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
