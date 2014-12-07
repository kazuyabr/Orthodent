/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.FichaEvolucion;

/**
 *
 * @author msanhuezal
 */
public class FichaEvolucionDB {
    
    public static boolean crearFichaEvolucion(int idUsuarioActual, int idPlanTratamiento, String fechaCita, String descripcion){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO ficha_evolucion (id_plantratamiento, fecha_cita, descripcion)\n" +
                                        "VALUES ("+idPlanTratamiento+",'"+fechaCita+"','"+descripcion+"')");
            boolean resultado = (aux == 1)? true : false;
            if(resultado){
                BitacoraDB.crearBitacora("CREAR FICHA EVOLUCION", idUsuarioActual, "FICHA EVOLUCION", 1);
            }
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static boolean editarFichaEvolucion(FichaEvolucion fichaEvolucion){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE ficha_evolucion\n" +
                                            "SET id_plantratamiento="+fichaEvolucion.getIdPlanTratamiento()+"\n" +
                                            ",fecha_cita='"+fichaEvolucion.getFechaCita()+"'\n" +
                                            ",descripcion='"+fichaEvolucion.getDescripcion()+"'\n" +
                                            "WHERE id_fichaevolucion="+fichaEvolucion.getIdFichaEvolucion());
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) { 
            return false;
        }
    }  
    
    public static ArrayList<FichaEvolucion> listarFichaEvolucion(){
        ArrayList<FichaEvolucion> fichasEvolucion = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM ficha_evolucion");
            fichasEvolucion = new ArrayList<FichaEvolucion>();
            while (rs.next())
            {
                FichaEvolucion f = new FichaEvolucion(rs.getInt("id_fichaevolucion"), rs.getInt("id_plantratamiento"), rs.getString("fecha_cita"), rs.getString("descripcion"));
                fichasEvolucion.add(f);
            }
            rs.close();
            con.close();
            return fichasEvolucion;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
    public static ArrayList<FichaEvolucion> listarFichasEvolucionPlanTratamiento(int idPlanTratamiento){
        ArrayList<FichaEvolucion> fichasEvolucion = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM ficha_evolucion WHERE id_plantratamiento="+idPlanTratamiento);
            fichasEvolucion = new ArrayList<FichaEvolucion>();
            while (rs.next())
            {
                FichaEvolucion f = new FichaEvolucion(rs.getInt("id_fichaevolucion"), rs.getInt("id_plantratamiento"), rs.getString("fecha_cita"), rs.getString("descripcion"));
                fichasEvolucion.add(f);
            }
            rs.close();
            con.close();
            return fichasEvolucion;
        }
        catch ( SQLException e) {
            return null;
        }
    }      
    
    public static boolean eliminarFichaEvolucion(int idFichaEvolucion) throws SQLException{
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("DELETE FROM ficha_evolucion\n" +
                                            "WHERE id_fichaevolucion="+idFichaEvolucion);
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }    
    
    static public FichaEvolucion getFichaEvolucion(int idFichaevolucion) throws Exception{
        FichaEvolucion fichaEvolucion = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from ficha_evolucion where id_fichaevolucion=" + idFichaevolucion);
            if (rs.next())
            {
                int idPlanTratamiento = rs.getInt("id_plantratamiento");
                String fechaCita = rs.getString("fecha_cita");
                String descripcion = rs.getString("descripcion");

                fichaEvolucion = new FichaEvolucion(idFichaevolucion, idPlanTratamiento, fechaCita, descripcion);
            }
            else{
                fichaEvolucion = null;
            }
            rs.close();
            con.close();
            return fichaEvolucion;
        }

        catch ( SQLException e) {
            return null;
        }
    }     
    
}
